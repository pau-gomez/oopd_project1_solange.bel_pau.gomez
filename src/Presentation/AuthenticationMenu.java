package Presentation;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Handles authentication-related user interface interactions.
 */
public class AuthenticationMenu {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the authentication menu and returns the selected option.
     *
     * @return selected menu option
     */
    public int printAuthenticationMenu() {

        printTitle();
        System.out.println();
        System.out.println("    1) Login");
        System.out.println("    2) Sign up");
        System.out.println();
        System.out.println("    0) Exit");
        System.out.print("\nChoose an option: ");

        return validatedInput();
    }

    /**
     * Prints the application title banner.
     */
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

    /**
     * Prompts user for client ID.
     *
     * @return entered client ID
     */
    public int askClientId() {
        System.out.print("Enter your client ID: ");
        return validatedInput();
    }

    /**
     * Prompts user for full name.
     *
     * @return string of user's full name
     */
    public String askFullName() {
        System.out.print("Full name: ");
        return scanner.nextLine();
    }

    /**
     * Prompts user for its phone's country prefix.
     *
     * @return country prefix
     */
    public String askCountryPrefix() {
        System.out.print("Country prefix (e.g. +34): ");
        return scanner.nextLine();
    }

    /**
     * Prompts user for a phone number.
     *
     * @return phone number
     */
    public String askPhoneNumber() {
        System.out.print("Phone number: ");
        return scanner.nextLine();
    }

    /**
     * Asks if the user wants to add another phone number.
     *
     * @return true if user wants to continue, false otherwise
     */
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

    /**
     * Prompts user to select client type.
     *
     * @return 1 for standard, 2 for online, 3 for corporate
     */
    public int askClientType() {
        System.out.println("\n    1) Standard client");
        System.out.println("    2) Online client");
        System.out.println("    3) Corporate client");
        System.out.print("\nChoose client type: ");
        return validatedInput();
    }

    /**
     * Prompts user for shipping address.
     *
     * @return shipping address
     */
    public String askAddress() {
        System.out.print("Shipping address: ");
        return scanner.nextLine();
    }

    /**
     * Prompts user for contact email.
     *
     * @return contact email
     */
    public String askEmail() {
        System.out.print("Contact email: ");
        return scanner.nextLine();
    }

    /**
     * Prompts user for CIF tax identification number.
     *
     * @return CIF
     */
    public String askCif() {
        System.out.print("CIF: ");
        return scanner.nextLine();
    }

    /**
     * Prompts user for contact person name.
     *
     * @return contact person name
     */
    public String askContactName() {
        System.out.print("Name of contact person: ");
        return scanner.nextLine();
    }

    /**
     * Prompts user for billing address.
     *
     * @return billing address
     */
    public String askBillingAddress() {
        System.out.print("Billing address: ");
        return scanner.nextLine();
    }

    /**
     * Prints goodbye message.
     */
    public void printGoodByeMessage() {
        System.out.print("\n We hope to see you again!");
    }

    /**
     * Prints invalid option message.
     */
    public void printInvalidOption() {
        System.out.print("\nERROR: Invalid option.");
    }

    /**
     * Reads and validates integer input from user.
     *
     * @return valid integer input
     */
    private int validatedInput() {
        int input = 0;
        boolean valid_input = false;

        while (!valid_input) {
            try{
                input = scanner.nextInt();
                scanner.nextLine();
                valid_input = true;
            }
            catch (InputMismatchException e) {
                System.out.print("\nERROR: Invalid input, try again: ");
            }
        }

        return input;
    }
}