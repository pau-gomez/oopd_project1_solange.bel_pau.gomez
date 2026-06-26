package Buisness.Entities;

/**
 * Represents a glasses product with its associated details.
 */
public class Glasses extends Product {
    private String brand;
    private String model;

    public Glasses(String productId, String productName, String brand, String model) {
        super(productId, productName, "glasses");
        this.brand = brand;
        this.model = model;
    }

    public String getBrand() { return brand; }

    public String getModel() { return model; }
}