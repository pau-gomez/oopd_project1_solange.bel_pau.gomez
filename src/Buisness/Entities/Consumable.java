package Buisness.Entities;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a consumable product with its associated details.
 */
public class Consumable extends Product {
    private final String brand;
    private final String model;
    private final int volume;

    @SerializedName("expiration_date")
    private final String expirationDate;
    private final boolean preservatives;


    /**
     * Constructs a Consumable product.
     *
     * @param productId the unique product identifier
     * @param productName the name of the product
     * @param brand the brand of the consumable
     * @param model the model of the consumable
     * @param volume the volume in milliliters
     * @param expirationDate the expiration date in YYYY-MM-DD format
     * @param preservatives whether the consumable contains preservatives
     */
    public Consumable(String productId, String productName, String brand, String model,
                      int volume, String expirationDate, boolean preservatives) {
        super(productId, productName, "consumable");
        this.brand = brand;
        this.model = model;
        this.volume = volume;
        this.expirationDate = expirationDate;
        this.preservatives = preservatives;
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

    /**
     *
     * @return the expiration date in YYYY-MM-DD format
     */
    public String getExpirationDate() { return expirationDate; }

    /**
     *
     * @return true if it contains preservatives, false otherwise
     */
    public boolean hasPreservatives() { return preservatives; }
}