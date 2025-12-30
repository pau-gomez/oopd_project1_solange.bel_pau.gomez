package Presentation;

public class AuthenticationMenu {
    private MenuController menuController;

    public AuthenticationMenu(MenuController menuController) {
        this.menuController = menuController;
    }

    public void printAuthenticationMenu() {
        System.out.println("- Authentication Menu -");
        System.out.println("    1) Login");
        System.out.println("    2) Sign up");
        System.out.println("    0) Exit");

        menuController.handleUserInput();
    }
}