package Presentation;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AuthenticationMenu {

    private final Scanner scanner = new Scanner(System.in);

    public int printAuthenticationMenu() {
        Scanner scanner = new Scanner(System.in);
        int number = 0;
        boolean validInput = false;

        printTitle();
        System.out.println();
        System.out.println("    1) Login");
        System.out.println("    2) Sign up");
        System.out.println();
        System.out.println("    0) Exit");
        System.out.print("\nChoose an option: ");



        while (!validInput) {
            try {
                number = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: That is not a valid integer. Try again.");
                scanner.next();
            }
        }

        return number;
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
        Scanner scanner = new Scanner(System.in);
        int number = 0;
        boolean validInput = false;

        System.out.print("Enter your client ID: ");

        while (!validInput) {

            try {
                number = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.print("Error: That is not a valid integer. \nTry again: ");
                scanner.next();
            }
        }
        return number;
    }

    public String askFullName() {
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
        System.out.print("\n We hope to see you again!");
    }

    public void printInvalidOption() {
        System.out.print("\nERROR: Invalid option.");
    }
}
