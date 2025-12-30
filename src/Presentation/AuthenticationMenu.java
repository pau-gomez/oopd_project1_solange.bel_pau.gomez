package Presentation;

public class AuthenticationMenu {

    private MenuController menuController;

    public AuthenticationMenu(MenuController menuController) {
        this.menuController = menuController;
    }

    // authentication menu
    public void printAuthenticationMenu() {
        System.out.println();
        System.out.println("- Authentication Menu -");
        System.out.println("    1) Login");
        System.out.println("    2) Sign up");
        System.out.println("    0) Exit");
        System.out.print("Choose an option: ");
    }

    // login
    public void printLoginTitle() {
        System.out.println();
        System.out.println("-- Login --");
    }

    public void askClientId() {
        System.out.print("Client ID: ");
    }

    public void printLoginError() {
        System.out.println("Error: Client ID not found.");
    }

    // register
    public void printSignupTitle() {
        System.out.println();
        System.out.println("-- Register --");
    }

    public void askFullName() {
        System.out.print("Full name: ");
    }

    public void askPhonePrefix() {
        System.out.print("Country prefix (E.g. +34): ");
    }

    public void askPhoneNumber() {
        System.out.print("Phone number: ");
    }

    public void askAddAnotherPhone() {
        System.out.print("Add another phone number? (Y/N): ");
    }

    public void printSignupSuccess() {
        System.out.println("User registered successfully!");
    }

   // errors
    public void printInvalidOption() {
        System.out.println("Error: invalid option.");
    }

    public void printInvalidClientId() {
        System.out.println("Error: invalid client ID.");
    }

    public void printInvalidPhone() {
        System.out.println("Error: invalid phone number.");
    }

    public void printEmptyNameError() {
        System.out.println("Error: full name cannot be empty.");
    }

    // exit
    public void printExitMessage() {
        System.out.println("We hope to see you again!");
    }
}
