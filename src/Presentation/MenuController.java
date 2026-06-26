package Presentation;

import Buisness.*;
import Buisness.Entities.*;
import Persistance.Impl.*;
import Persistance.*;
import edu.salle.url.api.ApiHelper;
import edu.salle.url.api.exception.ApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Controls the application flow and user interactions between UI and business logic.
 */
public class MenuController {

    private final AuthenticationMenu authenticationMenu;
    private final Presentation.UIMainMenu mainMenu;

    private final ClientsManager clientsManager;
    private final ProductsManager productsManager;
    private final ProvidersManager providersManager;
    private final SalesManager salesManager;
    private final ShoppingCartManager shoppingCartManager;

    /**
     * Initializes all menus and business managers.
     * Tries to connect to the API first; falls back to local files if unavailable.
     */
    public MenuController() {
        Scanner scanner = new Scanner(System.in);
        authenticationMenu = new AuthenticationMenu(scanner);
        mainMenu = new Presentation.UIMainMenu(scanner);

        System.out.println("Checking API status...");
        ApiHelper apiHelper = tryConnectApi();

        ClientsDao clientsDao;
        ProductsDao productsDao;
        ProvidersDao providersDao;
        SalesDao salesDao;

        if (apiHelper != null) {
            System.out.println("API OK");
            clientsDao   = new ClientsApiDao(apiHelper);
            productsDao  = new ProductsApiDao(apiHelper);
            providersDao = new ProvidersApiDao(apiHelper);
            salesDao     = new SalesApiDao(apiHelper);
        } else {
            System.out.println("Error: The API isn't available.");
            System.out.println("Verifying local files...");
            clientsDao   = new ClientsJsonDao();
            productsDao  = new ProductsJsonDao();
            providersDao = new ProvidersJsonDao();
            salesDao     = new SalesCsvDao();
        }

        clientsManager      = new ClientsManager(clientsDao);
        productsManager     = new ProductsManager(productsDao);
        providersManager    = new ProvidersManager(providersDao);
        salesManager        = new SalesManager(salesDao);
        shoppingCartManager = new ShoppingCartManager(providersManager, productsManager);
    }

    /**
     * Tries to create an ApiHelper, which checks connectivity internally.
     *
     * @return a working ApiHelper, or null if the API is unavailable
     */
    private ApiHelper tryConnectApi() {
        try {
            return new ApiHelper();
        } catch (ApiException e) {
            return null;
        }
    }

    /**
     * Starts the application loop.
     *
     * @return true if system starts correctly, false otherwise
     */
    public boolean start() {

        if (!checkFiles()) return false;

        System.out.println("Starting program...");

        boolean running = true;

        while (running) {
            int option = authenticationMenu.printAuthenticationMenu();

            try {
                switch (option) {
                    case 1:
                        if (handleLogin()) {
                            userMenu();
                        } else {
                            mainMenu.UserNotFound();
                        }
                        break;
                    case 2:
                        if (handleRegister()) {
                            userMenu();
                        }
                        break;
                    case 0:
                        authenticationMenu.printGoodByeMessage();
                        running = false;
                        break;
                    default:
                        authenticationMenu.printInvalidOption();
                        break;
                }
            } catch (PersistenceException e) {
                mainMenu.printLine("\nError: A persistence error occurred: " + e.getMessage());
            }
        }
        return true;
    }

    /**
     * Handles login process.
     *
     * @return true if login successful
     */
    private boolean handleLogin() throws PersistenceException {
        int id = authenticationMenu.askClientId();
        return clientsManager.login(id);
    }

    /**
     * Handles client registration process. Asks for client type before asking for fields.
     *
     * @return true if registration successful
     */
    private boolean handleRegister() throws PersistenceException {
        int clientType = authenticationMenu.askClientType();

        String name = authenticationMenu.askFullName();
        List<PhoneNumber> phones = new ArrayList<>();

        do {
            String prefix = authenticationMenu.askCountryPrefix();
            String number = authenticationMenu.askPhoneNumber();
            phones.add(new PhoneNumber(prefix, number));
        } while (authenticationMenu.askAnotherPhone());

        switch (clientType) {
            case 1: // regular
                return clientsManager.registerClient(name, phones);
            case 2: // online
                String address = authenticationMenu.askAddress();
                String email = authenticationMenu.askEmail();
                return clientsManager.registerOnlineClient(name, phones, address, email);
            case 3: // corporate
                String cif = authenticationMenu.askCif();
                String contactName = authenticationMenu.askContactName();
                String billingAddress = authenticationMenu.askBillingAddress();
                String mailingAddress = authenticationMenu.askAddress();
                return clientsManager.registerCorporateClient(contactName, phones, cif,
                        billingAddress, mailingAddress);
            default:
                return false;
        }
    }

    /**
     * Displays the main user menu loop.
     */
    private void userMenu() throws PersistenceException {
        boolean logged = true;

        while (logged) {
            int option = mainMenu.printMainMenu(clientsManager.getCurrentClient().getFullName());

            switch (option) {
                case 1:
                    showProfile();
                    break;
                case 2:
                    findProductsByName();
                    break;
                case 3:
                    findProductsByProvider();
                    break;
                case 4:
                    showShoppingCart();
                    break;
                case 0:
                    handleLogout();
                    logged = false;
                    break;
                default:
                    authenticationMenu.printInvalidOption();
                    break;
            }
        }
    }

    /**
     * Displays current client profile information.
     */
    private void showProfile() throws PersistenceException {
        Client c = clientsManager.getCurrentClient();

        List<String> phones = new ArrayList<>();
        for (PhoneNumber p : c.getPhoneNumbers()) {
            String formatted = "(" + p.getInternationalPrefix() + ") " + p.getPhoneNumber();
            phones.add(formatted);
        }

        List<Sale> clientSales = salesManager.filterSalesByClient(c.getClientId());
        List<String> purchases = new ArrayList<>();
        for (Sale s : clientSales) {
            String formatted = s.getProductId() + " - €" + s.getPaidPrice();
            purchases.add(formatted);
        }

        if (c instanceof OnlineClient) {
            OnlineClient oc = (OnlineClient) c;
            mainMenu.printOnlineClientProfile(c.getClientId(), c.getFullName(), phones,
                    oc.getAddress(), oc.getContactEmail(), purchases);
        } else if (c instanceof CorporateClient) {
            CorporateClient cc = (CorporateClient) c;
            mainMenu.printCorporateClientProfile(c.getClientId(), c.getFullName(), phones,
                    cc.getCif(), cc.getContactName(), cc.getBillingAddress(),
                    cc.getMailingAddress(), purchases);
        } else {
            mainMenu.printUserProfile(c.getClientId(), c.getFullName(), phones, purchases);
        }
    }

    /**
     * Searches products by name and allows selection.
     */
    private void findProductsByName() throws PersistenceException {
        String name = mainMenu.askSearchText();

        List<Product> products = productsManager.findProductsByName(name);
        List<String> display = new ArrayList<>();
        for (Product p : products) {
            String formatted = p.getProductId() + " - " + p.getProductName();
            display.add(formatted);
        }

        mainMenu.printNumberedList(display);

        int option = mainMenu.askOption(display.size());
        if (option == 0) return;

        Product selected = products.get(option - 1);
        showProductInformation(selected);
    }

    /**
     * Displays product details and available providers.
     *
     * @param product selected product
     */
    private void showProductInformation(Product product) throws PersistenceException {
        if (product instanceof Glasses) {
            Glasses g = (Glasses) product;
            mainMenu.printProductInformation(product.getProductId(), product.getProductName(),
                    g.getBrand(), g.getModel());
        } else if (product instanceof ContactLenses) {
            ContactLenses cl = (ContactLenses) product;
            mainMenu.printProductInformation(product.getProductId(), product.getProductName(),
                    cl.getBrand(), cl.getModel());
        } else if (product instanceof Consumable) {
            Consumable c = (Consumable) product;
            mainMenu.printProductInformation(product.getProductId(), product.getProductName(),
                    c.getBrand(), c.getModel());
        } else if (product instanceof Service) {
            mainMenu.printProductInformation(product.getProductId(), product.getProductName(),
                    "-", "-");
        }

        List<Provider> productSuppliers = providersManager.getProviderByProduct(product);
        List<String> display = new ArrayList<>();
        List<Provider> selectableProviders = new ArrayList<>();
        List<ProductForSale> selectableProductsForSale = new ArrayList<>();

        for (Provider provider : productSuppliers) {
            for (ProductForSale pfs : provider.getProductsForSale()) {
                if (pfs.getProductId().equals(product.getProductId())
                        && pfs.getUnitsInStock() > 0) {

                    String line = provider.getCompanyName() + "\n   - Sale price: " + pfs.getSalePrice() + "€," +
                            "\n   - Available stock: " + pfs.getUnitsInStock();

                    display.add(line);
                    selectableProviders.add(provider);
                    selectableProductsForSale.add(pfs);
                    break;
                }
            }
        }

        mainMenu.printProductProviderList(display);

        if (product instanceof Service && !isAllowedToBuy(product)) {
            mainMenu.printLine("\nERROR: Services can only be purchased in person by regular clients.");
            return;
        }

        if (mainMenu.confirm("Do you want to add this product to the shopping cart?")) {
            int chosenProvider = (mainMenu.askForProvider(display.size()) - 1);
            shoppingCartManager.addProduct(selectableProductsForSale.get(chosenProvider));
        }
    }

    /**
     * Displays products filtered by provider.
     */
    private void findProductsByProvider() throws PersistenceException {
        List<Provider> providers = providersManager.getAllProviders();
        List<String> display = new ArrayList<>();

        for (Provider provider : providers) {
            String name = provider.getCompanyName();
            display.add(name);
        }

        mainMenu.printNumberedList(display);
        int choice = mainMenu.askOption(display.size());
        if (choice == 0) return;

        Provider provider = providers.get(choice - 1);

        mainMenu.printProductFromProvider(provider);

        int option = mainMenu.askOption(provider.getProductsForSale().size());

        shoppingCartManager.addProduct(provider.getProductsForSale().get(option - 1));
    }

    /**
     * Handles shopping cart menu operations.
     */
    private void showShoppingCart() throws PersistenceException {
        int option;
        boolean exit = false;

        do {
            List<String> cartLines = shoppingCartManager.getCartInformation();
            mainMenu.printShoppingCart(cartLines);

            option = mainMenu.shoppingCartOptions();

            switch (option) {
                case 1:
                    deleteProductFromCart();
                    break;
                case 2:
                    shoppingCartManager.clear();
                    exit = true;
                    break;
                case 3:
                    checkout();
                    exit = true;
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    mainMenu.printLine("\n\tError: Invalid value.\n");
            }
        } while (!exit);
    }

    /**
     * Removes a product from the shopping cart.
     */
    private void deleteProductFromCart() {
        if (!shoppingCartManager.deleteProduct(mainMenu.deleteProductInterface())) {
            mainMenu.printLine("No such product");
        } else {
            mainMenu.printLine("\tProduct deleted\n");
        }
    }

    /**
     * Checks if the current client is allowed to purchase the given product.
     * Services can only be sold to regular (in-person) clients.
     *
     * @param product the product the client wants to buy
     * @return true if the purchase is allowed
     */
    private boolean isAllowedToBuy(Product product) {
        Client client = clientsManager.getCurrentClient();
        if (product instanceof Service) {
            return !(client instanceof OnlineClient) && !(client instanceof CorporateClient);
        }
        return true;
    }

    /**
     * Finalizes purchase and records the sale.
     */
    private void checkout() throws PersistenceException {
        int i = 0;

        mainMenu.printLine("----- PURCHASE INFORMATION -----");

        for (ProductForSale product : shoppingCartManager.getProducts()) {
            i++;
            mainMenu.printLine("(" + i + ") Product: " + product.getProductId()
                    + " | Supplier: " + shoppingCartManager.getProvider(product)
                    + " | Price: " + String.format("%.2f", product.getSalePrice()) + "€");

            providersManager.updateProviderStock(product);

            Sale sale = new Sale(
                    clientsManager.getCurrentClient().getClientId(),
                    product.getProductId(),
                    shoppingCartManager.calculateSellingPrice(product, clientsManager.getCurrentClient()),
                    System.currentTimeMillis()
            );

            salesManager.addSale(sale);
        }

        double total = shoppingCartManager.checkout(clientsManager.getCurrentClient());
        mainMenu.printLine("-------------------\nTOTAL: " + String.format("%.2f", total) + "€");
    }

    /**
     * Logs out the current user and clears session data.
     */
    private void handleLogout() {
        shoppingCartManager.clear();
        clientsManager.logout();
        mainMenu.logout();
    }

    /**
     * Validates required data sources.
     *
     * @return true if all data sources are valid and accessible
     */
    private boolean checkFiles() {
        if (!productsManager.checkProductsFile()) {
            System.out.println("Error: The products.json file can't be accessed.");
            System.out.println("Shutting down...");
            return false;
        }
        if (!providersManager.checkProvidersFile()) {
            System.out.println("Error: The providers.json file can't be accessed.");
            System.out.println("Shutting down...");
            return false;
        }
        return true;
    }
}