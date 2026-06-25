package Buisness.Entities;

import java.util.List;

/**
 * Represents a product with its associated details.
 */
public class Product {
    private String product_id;
    private String product_name;
    private String product_type;

    /**
     * Constructs a Product object.
     *
     * @param product_id unique product identifier
     * @param product_name name of the product
     * @param product_type type of the product (glasses, contact_lenses, consumable, or service)
     */
    public Product(String product_id, String product_name, String product_type) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_type = product_type;
    }

        // Getters: //

    /**
     * @return product ID
     */
    public String getProductId() {
        return product_id;
    }

    /**
     * @return product name
     */
    public String getProductName() {
        return product_name;
    }

    /**
     * @return product type
     */
    public String getProductType() {
        return product_type;
    }
}