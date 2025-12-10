package Presentation;

public class AuthentificationMenu {
    private ClientController clientController;

    public AuthentificationMenu(ClientController clientController) {
        this.clientController = clientController;
    }

    public void printAuthenticationMenu() {
        System.out.println("- Authentication Menu -");
        System.out.println("    1) Login");
        System.out.println("    2) Sign up");
        System.out.println("    0) Exit");
        System.out.print("\nChoose an option: ");

        clientController.handleUserInput();
    }
}