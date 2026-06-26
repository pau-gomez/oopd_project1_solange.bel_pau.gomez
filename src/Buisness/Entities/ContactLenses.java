package Buisness.Entities;

/**
 * Represents a contact lens product with its associated details.
 */
public class ContactLenses extends Product {
    private String brand;
    private String model;
    private double graduation;
    private int numberOfLenses;
    private boolean uvFilter;

    public ContactLenses(String productId, String productName, String brand, String model,
                         double graduation, int numberOfLenses, boolean uvFilter) {
        super(productId, productName, "contact_lenses");
        this.brand = brand;
        this.model = model;
        this.graduation = graduation;
        this.numberOfLenses = numberOfLenses;
        this.uvFilter = uvFilter;
    }

    public String getBrand() { return brand; }

    public String getModel() { return model; }

    public double getGraduation() { return graduation; }

    public int getNumberOfLenses() { return numberOfLenses; }

    public boolean hasUvFilter() { return uvFilter; }
}