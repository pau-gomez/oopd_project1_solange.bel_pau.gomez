package Presentation;


import java.util.Scanner;

/**
 * Handles authentication-related user interface interactions.
 */
public class AuthenticationMenu {

    private final Scanner scanner;

    /**
     * Constructs an AuthenticationMenu with the given shared Scanner.
     *
     * @param scanner the shared Scanner instance for reading user input
     */
    public AuthenticationMenu(Scanner scanner) {
        this.scanner = scanner;
    }

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
        while (true) {
            System.out.print("Full name: ");
            String name = scanner.nextLine();
            if (isValidName(name)) return name;
            InvalidInputException ex = new InvalidInputException("Name cannot be empty and must contain only letters and spaces.");
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    /**
     * Prompts user for its phone's country prefix.
     *
     * @return country prefix
     */
    public String askCountryPrefix() {
        String prefix;
        while (true) {
            System.out.print("Country prefix (e.g. +34): ");
            prefix = scanner.nextLine();
            if (isValidPrefix(prefix)) {
                return prefix;
            }
            InvalidInputException ex = new InvalidInputException("Invalid prefix format. Must be '+' followed by 1-3 digits (e.g. +34).");
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    /**
     * Prompts user for a phone number.
     *
     * @return phone number
     */
    public String askPhoneNumber() {
        String number;
        while (true) {
            System.out.print("Phone number: ");
            number = scanner.nextLine();
            if (isValidPhoneNumber(number)) {
                return number;
            }
            InvalidInputException ex = new InvalidInputException(
                    "Invalid phone number. Must contain 7-15 digits (spaces allowed).");
            System.out.println("ERROR: " + ex.getMessage());
        }
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
        while (true) {
            System.out.print("Shipping address (e.g. Carrer de Mallorca 221, 08008 Barcelona, Spain): ");
            String address = scanner.nextLine();
            if (isValidAddress(address)) return address;
            InvalidInputException ex = new InvalidInputException("Address must follow the format: Street Number, PostalCode City, Country.");
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    /**
     * Prompts user for contact email.
     *
     * @return contact email
     */
    public String askEmail() {
        while (true) {
            System.out.print("Contact email: ");
            String email = scanner.nextLine();
            if (isValidEmail(email)) return email;
            InvalidInputException ex = new InvalidInputException("Invalid email format (e.g. user@example.com).");
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    /**
     * Prompts user for CIF tax identification number.
     *
     * @return CIF
     */
    public String askCif() {
        while (true) {
            System.out.print("CIF: ");
            String cif = scanner.nextLine().toUpperCase();
            if (isValidCif(cif)) return cif;
            InvalidInputException ex = new InvalidInputException("Invalid CIF format. Must be 1 letter followed by 8 digits (e.g. B12345678).");
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    /**
     * Prompts user for contact person name.
     *
     * @return contact person name
     */
    public String askContactName() {
        while (true) {
            System.out.print("Name of contact person: ");
            String name = scanner.nextLine();
            if (isValidName(name)) return name;
            InvalidInputException ex = new InvalidInputException("Name cannot be empty and must contain only letters and spaces.");
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    /**
     * Prompts user for billing address.
     *
     * @return billing address
     */
    public String askBillingAddress() {
        while (true) {
            System.out.print("Billing address (e.g. Carrer de Mallorca 221, 08008 Barcelona, Spain): ");
            String address = scanner.nextLine();
            if (isValidAddress(address)) return address;
            InvalidInputException ex = new InvalidInputException("Address must follow the format: Street Number, PostalCode City, Country.");
            System.out.println("ERROR: " + ex.getMessage());
        }
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
     * Validates a country prefix format (e.g. +34, +1, +33).
     *
     * @param prefix the prefix to validate
     * @return true if valid
     */
    private boolean isValidPrefix(String prefix) {
        if (prefix == null || prefix.isEmpty()) return false;
        if (prefix.charAt(0) != '+') return false;

        String digits = prefix.substring(1);
        if (digits.isEmpty() || digits.length() > 3) return false;

        for (int i = 0; i < digits.length(); i++) {
            if (!Character.isDigit(digits.charAt(i))) return false;
        }

        return true;
    }

    /**
     * Validates a phone number (digits and spaces only, 7-15 digits total).
     *
     * @param number the number to validate
     * @return true if valid
     */
    private boolean isValidPhoneNumber(String number) {
        if (number == null || number.isEmpty()) return false;

        int digitCount = 0;

        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);
            if (Character.isDigit(c)) {
                digitCount++;
            } else if (c != ' ' && c != '-') {
                return false; // invalid character
            }
        }

        return digitCount >= 7 && digitCount <= 15;
    }

    /**
     * Validates a full name (not empty, letters and spaces only).
     *
     * @param name the name to validate
     * @return true if valid, false otherwise
     */
    private boolean isValidName(String name) {
        if (name == null || name.isBlank()) return false;

        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (!Character.isLetter(c) && c != ' ') return false;
        }
        return true;
    }

    /**
     * Validates an email address (contains @, has domain with dot, no spaces).
     *
     * @param email the email to validate
     * @return true if valid, false otherwise
     */
    private boolean isValidEmail(String email) {
        if (email == null || email.isBlank()) return false;

        int atIndex = email.indexOf('@');
        if (atIndex <= 0) return false;

        String domain = email.substring(atIndex + 1);
        if (domain.indexOf('.') <= 0) return false;

        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == ' ') return false;
        }

        return true;
    }

    /**
     * Validates an address string (must have 3 comma-separated parts: street, postal+city, country).
     *
     * @param address the address to validate
     * @return true if valid, false otherwise
     */
    private boolean isValidAddress(String address) {
        if (address == null || address.isBlank()) return false;

        String[] parts = address.split(",");
        if (parts.length < 3) return false;

        for (String part : parts) {
            if (part.isBlank()) return false;
        }

        return true;
    }

    /**
     * Validates a Spanish CIF/NIF (1 letter + 8 digits).
     *
     * @param cif the CIF to validate
     * @return true if valid, false otherwise
     */
    private boolean isValidCif(String cif) {
        if (cif == null || cif.length() != 9) return false;
        if (!Character.isLetter(cif.charAt(0))) return false;

        for (int i = 1; i < cif.length(); i++) {
            if (!Character.isDigit(cif.charAt(i))) return false;
        }

        return true;
    }

    /**
     * Reads and validates integer input from user.
     *
     * @return valid integer input
     */
    private int validatedInput() {
        int input = 0;
        boolean validInput = false;

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