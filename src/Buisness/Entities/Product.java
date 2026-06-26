package Buisness.Entities;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a product with its associated details.
 */
public class Product {
    @SerializedName("product_id")
    private final String productId;

    @SerializedName("product_name")
    private final String productName;

    @SerializedName("product_type")
    private final String productType;

    /**
     * Constructs a Product object.
     *
     * @param productId unique product identifier
     * @param productName name of the product
     * @param productType type of the product (glasses, contact_lenses, consumable, or service)
     */
    public Product(String productId, String productName, String productType) {
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
    }


    /**
     * @return product ID
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @return product name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @return product type
     */
    public String getProductType() {
        return productType;
    }
}