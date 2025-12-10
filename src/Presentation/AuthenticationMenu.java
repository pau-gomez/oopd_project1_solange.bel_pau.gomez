package Presentation;

public class AuthenticationMenu {
    private ClientController clientController;

    public AuthenticationMenu(ClientController clientController) {
        this.clientController = clientController;
    }

    public void printAuthenticationMenu() {
        System.out.println("- Authentication Menu -");
        System.out.println("    1) Login");
        System.out.println("    2) Sign up");
        System.out.println("    0) Exit");

        clientController.handleUserInput();
    }
}