package Presentation;

import java.util.List;
import java.util.Scanner;

public class UIMainMenu {

    private final Scanner scanner = new Scanner(System.in);

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
        return scanner.nextInt();
    }

    public void printUserProfile(
            int clientId,
            String fullName,
            List<String> phoneNumbers,
            List<String> purchaseHistory
    ) {
        System.out.println("\n--| User Profile |--\n");
        System.out.println("Client ID: " + clientId);
        System.out.println("Full name: " + fullName);
        System.out.println("Phone numbers:");
        phoneNumbers.forEach(p -> System.out.println("  " + p));

        System.out.println("\nShopping history:");
        if (purchaseHistory.isEmpty()) {
            System.out.println("  No purchases yet.");
        } else {
            purchaseHistory.forEach(s -> System.out.println("  " + s));
        }

        waitEnter();
    }

    public String askSearchText() {
        scanner.nextLine();
        System.out.print("Search criteria: ");
        return scanner.nextLine();
    }

    public void printNumberedList(List<String> items) {
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ") " + items.get(i));
        }
        System.out.println("\n0) Back");
    }

    public int askOption() {
        System.out.print("\nChoose an option: ");
        return scanner.nextInt();
    }

    public boolean confirm(String message) {
        scanner.nextLine();
        System.out.print(message + " (yes/no): ");
        return scanner.nextLine().equalsIgnoreCase("yes");
    }

    public void logout() {
        System.out.println("\nWe hope to see you again!");
    }

    private void waitEnter() {
        System.out.print("\nPress ENTER to continue...");
        scanner.nextLine();
        scanner.nextLine();
    }
}
