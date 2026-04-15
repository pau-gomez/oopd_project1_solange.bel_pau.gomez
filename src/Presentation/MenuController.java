package Presentation;

import Buisness.*;
import Buisness.Entities.*;

import java.util.ArrayList;
import java.util.List;

public class MenuController {

    private final AuthenticationMenu authenticationMenu;
    private final Presentation.UIMainMenu mainMenu;

    private final ClientsManager clientsManager;
    private final ProductsManager productsManager;
    private final ProvidersManager providersManager;
    private final SalesManager salesManager;
    private final ShoppingCartManager shoppingCartManager;

    public MenuController() {
        authenticationMenu = new AuthenticationMenu();
        mainMenu = new Presentation.UIMainMenu();

        clientsManager = new ClientsManager();
        productsManager = new ProductsManager();
        providersManager = new ProvidersManager();
        salesManager = new SalesManager();
        shoppingCartManager = new ShoppingCartManager();
    }

    public boolean start() {

        if(!checkFiles()) return false;

        boolean running = true;

        while (running) {
            int option = authenticationMenu.printAuthenticationMenu();

            switch (option) {
                case 1:
                    if (handleLogin()) {
                        //System.out.print("you logged in");
                        userMenu();
                    }
                    else {
                        mainMenu.UserNotFound();
                    }
                    break;
                case 2:
                    if (handleRegister()) {
                        userMenu();
                    }
                    else {
                        //error
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

        }
        return true;
    }

    private boolean handleLogin() {
        int id = authenticationMenu.askClientId();
        return clientsManager.login(id);
    }

    private boolean handleRegister() {
        String name = authenticationMenu.askFullName();
        List<PhoneNumber> phones = new ArrayList<>();

        do {
            String prefix = authenticationMenu.askCountryPrefix();
            String number = authenticationMenu.askPhoneNumber();
            phones.add(new PhoneNumber(prefix, number));
        } while (authenticationMenu.askAnotherPhone());

        if(!clientsManager.registerClient(name, phones)) return false;

        return true;
    }

    private void userMenu() {
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
                    //authenticationMenu.printGoodByeMessage();
                    logged = false;
                    break;
                default:
                    authenticationMenu.printInvalidOption();
                    break;
            }

        }
    }

    private void showProfile() {
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

        mainMenu.printUserProfile(c.getClientId(), c.getFullName(), phones, purchases);
    }

    private void findProductsByName() {
        String name = mainMenu.askSearchText();

        List<Product> products = productsManager.findProductsByName(name);
        List<String> display = new ArrayList<>();

        for (Product p : products) {
            String formatted = p.getProductId() + " - " + p.getProductName();
            display.add(formatted);
        }

        mainMenu.printNumberedList(display);

        int option = mainMenu.askOption();
        if (option == 0) return;

        Product selected = products.get(option - 1);
        showProductInformation(selected);
    }

    private void showProductInformation(Product product) {
        mainMenu.printProductInformation(product.getProductId(), product.getProductName(), product.getBrand(), product.getModel());

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

        /*if (display.isEmpty()) {
            mainMenu.printLine("No providers have stock for this product.");
            return;
        }*/

        mainMenu.printProductProviderList(display);

        if (mainMenu.confirm("Do you want to add this product to the shopping cart?")) {
            int chosenProvider = (mainMenu.askForProvider() - 1);
            shoppingCartManager.addProduct(selectableProductsForSale.get(chosenProvider));
        }
    }

    private void findProductsByProvider() {
        List<Provider> providers = providersManager.getAllProviders();
        List<String> display = new ArrayList<>();

        for (Provider provider : providers) {
            // Get the company name from each Provider object
            String name = provider.getCompanyName();

            // Add it to the display list
            display.add(name);
        }

        mainMenu.printNumberedList(display);
        int choice = mainMenu.askOption();
        if (choice == 0) return;

        Provider provider = providers.get(choice - 1);

        mainMenu.printProductFromProvider(provider);

        int option = mainMenu.askOption();

        shoppingCartManager.addProduct(provider.getProductsForSale().get(option-1));
    }

    private void showShoppingCart() {
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
            }
        } while(!exit);
    }

    private void deleteProductFromCart() {
        if(!shoppingCartManager.deleteProduct(mainMenu.deleteProductInterface())) {
            mainMenu.printLine("No such product");
        }
        else{
            mainMenu.printLine("\tProduct deleted\n");
        }
    }

    private void checkout() {
        int i = 0;

        mainMenu.printLine("----- PURCHASE INFORMATION -----");

        for(ProductForSale product : shoppingCartManager.getProducts()){
            i++;
            mainMenu.printLine("(" + i + ") Product: " + product.getProductId() +
                    " | Supplier: " + shoppingCartManager.getProvider(product) +
                    " | Price: " + String.format("%.2f", product.getSalePrice()) + "€");

            providersManager.updateProviderStock(product);

            Sale sale = new Sale(clientsManager.getCurrentClient().getClientId(), product.getProductId(),
                    product.getSalePrice()*1.21, 9999);

            salesManager.addSale(sale);
        }
        mainMenu.printLine("-------------------\nTOTAL: " + String.format("%.2f", shoppingCartManager.checkout()) + "€");
    }

    private void handleLogout() {
        shoppingCartManager.clear();
        clientsManager.logout();
        mainMenu.logout();
    }

    private boolean checkFiles() {

        if(!productsManager.checkProductsFile()) return false;

        if(!providersManager.checkProvidersFile()) return false;

        return true;
    }
}
