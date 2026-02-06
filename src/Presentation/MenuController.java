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
                        //error
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

        //System.out.print("we out the loop");

        if(!clientsManager.registerClient(name, phones)) return false;

        return true;
    }

    private void userMenu() {
        boolean logged = true;

        while (logged) {
            int option = mainMenu.printMainMenu(
                    clientsManager.getCurrentClient().getFullName()
            );

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

        List<Sale> clientSales = salesManager.filterSalesByClient(c.getId());
        List<String> purchases = new ArrayList<>();

        for (Sale s : clientSales) {
            String formatted = s.getProductId() + " - €" + s.getPricePaid();
            purchases.add(formatted);
        }

        mainMenu.printUserProfile(c.getId(), c.getFullName(), phones, purchases);
    }

    private void findProductsByName() {
        String text = mainMenu.askSearchText();

        List<Product> products = productsManager.findProductsByName(text);
        List<String> display = new ArrayList<>();

        for (Product p : products) {
            String formatted = p.getId() + " - " + p.getName();
            display.add(formatted);
        }


        mainMenu.printNumberedList(display);
        int choice = mainMenu.askOption();
        if (choice == 0) return;

        Product selected = products.get(choice - 1);
        shoppingCartManager.addProduct(selected);
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
        shoppingCartManager.addProductsFromProvider(provider);
    }

    private void showShoppingCart() {
        shoppingCartManager.printCart();

        if (mainMenu.confirm("Do you want to proceed with the purchase?")) {
            shoppingCartManager.checkout();
        }
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
