package Presentation;

import Buisness.Entities.ProductForSale;
import Buisness.Entities.Provider;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UIMainMenu {

    private final Scanner scanner = new Scanner(System.in);
    private static boolean valid_input = false;

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

    public void printProductInformation(String product_id, String product_name, String brand, String model) {
        System.out.println("\nProduct ID: " + product_id + ",");
        System.out.println("Name: " + product_name + ",");
        System.out.println("Brand: " + brand + ",");
        System.out.println("Model: " + model + ",");
        System.out.println("Providers:");

    }

    public String askSearchText() {
        scanner.nextLine();
        System.out.print("Search criteria: ");
        return scanner.nextLine();
    }

    public int askForProvider() {
        System.out.print("\nChoose a provider: ");

        return validatedInput();
    }

    private int validatedInput() {
        int input = 0;
        valid_input = false;

        while (!valid_input) {
            try{
                input = scanner.nextInt();
                valid_input = true;
            }
            catch (InputMismatchException e) {
                System.out.print("\nInvalid input, try again: ");
            }
        }

        return input;
    }

    public void printNumberedList(List<String> items) {
        for (int i = 0; i < items.size(); i++) {
            System.out.println("\t" + (i + 1) + ") " + items.get(i));
        }
        System.out.println("\n\t0) Back");
    }

    public void printProductProviderList(List<String> items) {
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ") " + items.get(i));
        }
    }

    public int askOption() {
        System.out.print("\nChoose an option: ");
        return validatedInput();
    }

    public boolean confirm(String message) {
        scanner.nextLine();
        System.out.print(message + " (yes/no): ");
        return scanner.nextLine().equalsIgnoreCase("yes");
    }

    public int printLine(String message) {
        System.out.println(message);
        return 0;
    }

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

    public int shoppingCartOptions() {

        System.out.println("\nAvaliable options: \n\t(1) Remove products (2) Delete cart (3) Buy products (4) Exit");
        System.out.print("Option: ");
        return validatedInput();
    }

    public void logout() {
        System.out.println("\nWe hope to see you again!");
    }

    private void waitEnter() {
        System.out.print("\nPress ENTER to continue...");
        scanner.nextLine();
        scanner.nextLine();
    }

    public void printProductFromProvider(Provider provider) {
        int i = 0;

        for (ProductForSale productReal : provider.getProductsForSale()) {
            i++;
            System.out.print("(" + i + ") ");
            printProductInformation(productReal);
            System.out.print("\n");
        }
    }

    public void printProductInformation(ProductForSale product) {
        System.out.println("\nProduct ID: " + product.getProductId() + ",");
        System.out.println("Price: " + product.getSalePrice() + ",");
        System.out.println("Stock: " + product.getUnitsInStock());

    }

    public int deleteProductInterface() {
        System.out.print("Product to delete (number): ");

        return validatedInput();
    }

    public void UserNotFound() {
        System.out.println("\n\tThis ID doesn't belong to any user!\n");
    }
}
