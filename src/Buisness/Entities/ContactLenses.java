package Buisness.Entities;

/**
 * Represents a contact lens product with its associated details.
 */
public class ContactLenses extends Product {
    private String brand;
    private String model;
    private double graduation;
    private int number_of_lenses;
    private boolean uv_filter;

    public ContactLenses(String product_id, String product_name, String brand, String model,
                         double graduation, int number_of_lenses, boolean uv_filter) {
        super(product_id, product_name, "contact_lenses");
        this.brand = brand;
        this.model = model;
        this.graduation = graduation;
        this.number_of_lenses = number_of_lenses;
        this.uv_filter = uv_filter;
    }

    public String getBrand() { return brand; }

    public String getModel() { return model; }

    public double getGraduation() { return graduation; }

    public int getNumberOfLenses() { return number_of_lenses; }

    public boolean hasUvFilter() { return uv_filter; }
}