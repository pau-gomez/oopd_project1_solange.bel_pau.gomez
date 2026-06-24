package Buisness.Entities;

/**
 * Represents a consumable product with its associated details.
 */
public class Consumable extends Product {
    private String brand;
    private String model;
    private int volume;
    private String expiration_date;
    private boolean preservatives;

    public Consumable(String product_id, String product_name, String brand, String model,
                      int volume, String expiration_date, boolean preservatives) {
        super(product_id, product_name, "consumable");
        this.brand = brand;
        this.model = model;
        this.volume = volume;
        this.expiration_date = expiration_date;
        this.preservatives = preservatives;
    }

    public String getBrand() { return brand; }

    public String getModel() { return model; }

    public int getVolume() { return volume; }

    public String getExpirationDate() { return expiration_date; }

    public boolean hasPreservatives() { return preservatives; }
}