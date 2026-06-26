package Presentation;

import Buisness.Entities.ProductForSale;
import Buisness.Entities.Provider;

import java.util.List;
import java.util.Scanner;

/**
 * Handles all main menu UI interactions for the application.
 */
public class UIMainMenu {

    private final Scanner scanner;
    private boolean validInput = false;

    public UIMainMenu (Scanner scanner) {
        this.scanner = scanner;
    }
    /**
     * Displays the main menu for the logged-in user.
     *
     * @param userName name of the current user
     * @return selected menu option
     */
    public int printMainMenu(String userName) {
        System.out.println();
        System.out.println("Welcome " + userName + "!");
        System.out.println();
        System.out.println("    1) User profile");
        System.out.println("    2) Find products by name");
        System.out.println("    3) Find products by provider");
        System.out.println("    4) Shopping cart");
        System.out.println();
        System.out.println("    0) Logout");
        System.out.print("\nChoose an option: ");

        return validatedInput();
    }

    /**
     * Displays the user profile information.
     *
     * @param clientId user ID
     * @param fullName user full name
     * @param phoneNumbers list of formatted phone numbers
     * @param purchaseHistory list of past purchases
     */
    public void printUserProfile(int clientId, String fullName, List<String> phoneNumbers, List<String> purchaseHistory) {
        System.out.println("\n--| User Profile |--\n");
        System.out.println("Client ID: " + clientId);
        System.out.println("Full name: " + fullName);
        System.out.println("Phone numbers:");
        phoneNumbers.forEach(p -> System.out.println("  " + p));

        System.out.println("\nShopping history:");
        if (purchaseHistory.isEmpty()) System.out.println("  No purchases yet.");
        else purchaseHistory.forEach(s -> System.out.println("  " + s));

        waitEnter();
    }

    /**
     * Displays online client profile information.
     *
     * @param clientId user ID
     * @param fullName user full name
     * @param phoneNumbers list of formatted phone numbers
     * @param address shipping address
     * @param contactEmail contact email
     * @param purchaseHistory list of past purchases
     */
    public void printOnlineClientProfile(int clientId, String fullName, List<String> phoneNumbers,
                                         String address, String contactEmail,
                                         List<String> purchaseHistory) {
        System.out.println("\n--| User Profile |--\n");
        System.out.println("Client ID: " + clientId);
        System.out.println("Full name: " + fullName);
        System.out.println("Phone numbers:");
        phoneNumbers.forEach(p -> System.out.println("  " + p));
        System.out.println("Shipping address: " + address);
        System.out.println("Contact email: " + contactEmail);

        System.out.println("\nShopping history:");
        if (purchaseHistory.isEmpty()) System.out.println("  No purchases yet.");
        else purchaseHistory.forEach(s -> System.out.println("  " + s));

        waitEnter();
    }

    /**
     * Displays corporate client profile information.
     *
     * @param clientId user ID
     * @param fullName company name
     * @param phoneNumbers list of formatted phone numbers
     * @param cif tax identification number
     * @param contactName contact person name
     * @param billingAddress billing address
     * @param mailingAddress shipping address
     * @param purchaseHistory list of past purchases
     */
    public void printCorporateClientProfile(int clientId, String fullName, List<String> phoneNumbers,
                                            String cif, String contactName,
                                            String billingAddress, String mailingAddress,
                                            List<String> purchaseHistory) {
        System.out.println("\n--| User Profile |--\n");
        System.out.println("Client ID: " + clientId);
        System.out.println("Full name: " + fullName);
        System.out.println("Phone numbers:");
        phoneNumbers.forEach(p -> System.out.println("  " + p));
        System.out.println("CIF/NIF: " + cif);
        System.out.println("Contact person: " + contactName);
        System.out.println("Billing address: " + billingAddress);
        System.out.println("Shipping address: " + mailingAddress);

        System.out.println("\nShopping history:");
        if (purchaseHistory.isEmpty()) System.out.println("  No purchases yet.");
        else purchaseHistory.forEach(s -> System.out.println("  " + s));

        waitEnter();
    }

    /**
     * Displays basic product information.
     *
     * @param productId product identifier
     * @param productName name of the product
     * @param brand product brand
     * @param model product model
     */
    public void printProductInformation(String productId, String productName, String brand, String model) {
        System.out.println("\nProduct ID: " + productId + ",");
        System.out.println("Name: " + productName + ",");
        System.out.println("Brand: " + brand + ",");
        System.out.println("Model: " + model + ",");
        System.out.println("Providers:");
    }

    /**
     * Asks for search text input.
     *
     * @return search query
     */
    public String askSearchText() {
        System.out.print("Search criteria: ");
        return scanner.nextLine();
    }

    /**
     * Asks user to select a provider.
     *
     * @return selected provider index
     */
    public int askForProvider(int numProviders) {
        boolean valid = false;
        int selectedOption;
        do{
            System.out.print("\nChoose an provider: ");
            selectedOption = validatedInput();
            if(selectedOption > numProviders) System.out.println("\n\tError: Invalid value.");
            else valid = true;
        } while(!valid);

        return selectedOption;
    }

    /**
     * Prints a numbered list of items.
     *
     * @param items list of strings to display
     */
    public void printNumberedList(List<String> items) {
        for (int i = 0; i < items.size(); i++) {
            System.out.println("\t" + (i + 1) + ") " + items.get(i));
        }
        System.out.println("\n\t0) Back");
    }

    /**
     * Prints provider-product list.
     *
     * @param items list of provider-product strings
     */
    public void printProductProviderList(List<String> items) {
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ") " + items.get(i));
        }
    }

    /**
     * Asks for a general option input.
     *
     * @param options integer number of options avaliable
     * @return selected option
     */
    public int askOption(int options) {
        boolean valid = false;
        int selected_option = 0;

        do{
            System.out.print("\nChoose an option: ");
            selected_option = validatedInput();
            if(selected_option > options) System.out.println("\n\tError: Invalid value.");
            else valid = true;
        } while(!valid);

        return selected_option;
    }

    /**
     * Confirms a yes/no action.
     *
     * @param message confirmation message
     * @return true if user confirms
     */
    public boolean confirm(String message) {
        System.out.print(message + " (yes/no): ");
        return scanner.nextLine().equalsIgnoreCase("yes");
    }

    /**
     * Prints a single line message.
     *
     * @param message text to print
     */
    public void printLine(String message) {
        System.out.println(message);
    }

    /**
     * Displays shopping cart contents.
     *
     * @param lines cart item lines
     */
    public void printShoppingCart(List<String> lines) {
        System.out.println("----SHOPPING CART----");

        if (lines.isEmpty()) {
            System.out.println("Shopping Cart is empty.");
            return;
        }

        for (String line : lines) {
            System.out.println(line);
        }
    }

    /**
     * Displays shopping cart menu options.
     *
     * @return selected option
     */
    public int shoppingCartOptions() {
        System.out.println("\nAvaliable options: \n\t(1) Remove products (2) Delete cart (3) Buy products (4) Exit");
        System.out.print("Option: ");
        return validatedInput();
    }

    /**
     * Prints logout message.
     */
    public void logout() {
        System.out.println("\nWe hope to see you again!");
    }

    /**
     * Waits for user to press ENTER.
     */
    private void waitEnter() {
        System.out.print("\nPress ENTER to continue...");
        scanner.nextLine();
    }

    /**
     * Prints products from a provider.
     *
     * @param provider provider object
     */
    public void printProductFromProvider(Provider provider) {
        int i = 0;

        for (ProductForSale productReal : provider.getProductsForSale()) {
            i++;
            System.out.print("(" + i + ") ");
            printProductInformation(productReal);
            System.out.print("\n");
        }
    }

    /**
     * Displays product details for a product for sale.
     *
     * @param product product for sale
     */
    public void printProductInformation(ProductForSale product) {
        System.out.println("\nProduct ID: " + product.getProductId() + ",");
        System.out.println("Price: " + product.getSalePrice() + ",");
        System.out.println("Stock: " + product.getUnitsInStock());
    }

    /**
     * Asks for product deletion index.
     *
     * @return selected index
     */
    public int deleteProductInterface() {
        System.out.print("Product to delete (number): ");
        return validatedInput();
    }

    /**
     * Displays user-not-found message.
     */
    public void UserNotFound() {
        System.out.println("\n\tThis ID doesn't belong to any user!\n");
    }

    /**
     * Reads and validates integer input.
     *
     * @return valid integer input
     */
    private int validatedInput() {
        int input = 0;
        validInput = false;

        while (!validInput) {
            try{
                String line = scanner.nextLine();
                input = Integer.parseInt(line.trim());
                validInput = true;
            }
            catch (NumberFormatException e) {
                InvalidInputException ex = new InvalidInputException("Input must be a number.");
                System.out.print("\nERROR: " + ex.getMessage() + " Try again: ");
            }
        }

        return input;
    }
}