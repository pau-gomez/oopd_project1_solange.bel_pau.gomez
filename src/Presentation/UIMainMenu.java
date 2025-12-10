package Presentation;

public class UIMainMenu {
    private MainController mainController;

    public UIMainMenu(MainController mainController) {
        this.mainController = mainController;
    }

    public void printMainMenu() {
        System.out.println("    1) User profile");
        System.out.println("    2) Find products by name");
        System.out.println("    3) Find products by provider");
        System.out.println("    4) Shopping cart");
        System.out.println("    0) Logout");

        mainController.handleUserInput();
    }
}
