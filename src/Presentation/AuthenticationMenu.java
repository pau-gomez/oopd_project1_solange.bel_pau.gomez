package Presentation;

import java.util.Scanner;

public class AuthenticationMenu {

    private final Scanner scanner = new Scanner(System.in);

    public int printAuthenticationMenu() {
        printTitle();
        System.out.println();
        System.out.println("    1) Login");
        System.out.println("    2) Sign up");
        System.out.println();
        System.out.println("    0) Exit");
        System.out.print("\nChoose an option: ");
        return scanner.nextInt();
    }

    public void printTitle() {
        System.out.print(" _   _           _         _____ \n" +
                         "| \\ | |         | |       |  ___|\n" +
                         "|  \\| |_   _  __| | ___   | |__  _   _  ___\n" +
                         "| . ` | | | |/ _` |/ _ \\  |  __|| | | |/ _ \\\n" +
                         "| |\\  | |_| | (_| |  __/  | |___| |_| |  __/\n" +
                         "|_| \\_|\\__,_|\\__,_|\\___|  |____/\\___ / \\___|\n" +
                         "                                 __/ |\n" +
                         "                                 |___/");
    }

    public int askClientId() {
        System.out.print("Enter your client ID: ");
        return scanner.nextInt();
    }

    public String askFullName() {
        scanner.nextLine();
        System.out.print("Full name: ");
        return scanner.nextLine();
    }

    public String askCountryPrefix() {
        System.out.print("Country prefix (e.g. +34): ");
        return scanner.nextLine();
    }

    public String askPhoneNumber() {
        System.out.print("Phone number: ");
        return scanner.nextLine();
    }

    public boolean askAnotherPhone() {
        boolean loop = true;

        while(loop) {
            System.out.print("Add another phone number? (yes/no): ");
            String answer = scanner.nextLine();
            if (answer.equals("yes")) loop = false;
            else if (answer.equals("no")) return false;
            else {
                System.out.println("Wrong option!");
            }
        }
        return true;
    }

    public void printGoodByeMessage() {
        // print bye
    }

    public void printInvalidOption() {
        // print message
    }
}
