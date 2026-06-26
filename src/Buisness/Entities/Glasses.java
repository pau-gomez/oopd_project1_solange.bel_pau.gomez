package Buisness.Entities;

/**
 * Represents a glasses product with its associated details.
 */
public class Glasses extends Product {
    private final String brand;
    private final String model;

    /**
     * Constructs a Glasses product.
     *
     * @param productId the unique product identifier
     * @param productName the name of the product
     * @param brand the brand of the glasses
     * @param model the model of the glasses
     */
    public Glasses(String productId, String productName, String brand, String model) {
        super(productId, productName, "glasses");
        this.brand = brand;
        this.model = model;
    }

    /**
     *
     * @return the brand
     */
    public String getBrand() { return brand; }

    /**
     *
     * @return the model
     */
    public String getModel() { return model; }
}