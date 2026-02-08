package Presentation;

public class main {

    public static void main(String[] args) {
        MenuController controller = new MenuController();
        if(!controller.start()) System.out.println("\nERROR: Some files don't exist or contain errors in their structure.");
    }
}

