package Buisness.Entities;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a contact lens product with its associated details.
 */
public class ContactLenses extends Product {
    private final String brand;
    private final String model;
    private final double graduation;

    @SerializedName("number_of_lenses")
    private final int numberOfLenses;

    @SerializedName("uv_filter")
    private final boolean uvFilter;

    /**
     * Constructs a ContactLenses product.
     *
     * @param productId the unique product identifier
     * @param productName the name of the product
     * @param brand the brand of the contact lenses
     * @param model the model of the contact lenses
     * @param graduation the prescription value (between -12.00 and +8.00)
     * @param numberOfLenses the number of lenses in the package
     * @param uvFilter whether the lenses have a UV filter
     */
    public ContactLenses(String productId, String productName, String brand, String model,
                         double graduation, int numberOfLenses, boolean uvFilter) {
        super(productId, productName, "contact_lenses");
        this.brand = brand;
        this.model = model;
        this.graduation = graduation;
        this.numberOfLenses = numberOfLenses;
        this.uvFilter = uvFilter;
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
     * @return the graduation
     */
    public double getGraduation() { return graduation; }

    /**
     *
     * @return the number of lenses
     */
    public int getNumberOfLenses() { return numberOfLenses; }

    /**
     *
     * @return true if UV filter is present, false otherwise
     */
    public boolean hasUvFilter() { return uvFilter; }
}