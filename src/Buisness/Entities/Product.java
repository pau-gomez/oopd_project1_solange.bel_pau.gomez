package Buisness.Entities;

/**
 * Represents a product with its associated details.
 */
public class Product {
    private String productId;
    private String productName;
    private String productType;

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

        // Getters: //

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