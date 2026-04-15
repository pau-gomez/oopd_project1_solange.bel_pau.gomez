package Presentation;

/**
 * Entry point of the application.
 */
public class main {

    /**
     * Starts the program and launches the menu controller.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        MenuController controller = new MenuController();
        if(!controller.start()) System.out.println("\nERROR: Some files don't exist or contain errors in their structure.");
    }
}