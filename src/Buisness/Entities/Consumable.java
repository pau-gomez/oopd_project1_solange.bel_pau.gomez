package Buisness.Entities;

/**
 * Represents a consumable product with its associated details.
 */
public class Consumable extends Product {
    private String brand;
    private String model;
    private int volume;
    private String expirationDate;
    private boolean preservatives;

    public Consumable(String productId, String productName, String brand, String model,
                      int volume, String expirationDate, boolean preservatives) {
        super(productId, productName, "consumable");
        this.brand = brand;
        this.model = model;
        this.volume = volume;
        this.expirationDate = expirationDate;
        this.preservatives = preservatives;
    }

    public String getBrand() { return brand; }

    public String getModel() { return model; }

    public String getExpirationDate() { return expirationDate; }

    public boolean hasPreservatives() { return preservatives; }
}