package Buisness.Entities;

import java.util.List;

/**
 * Represents a product with its details and associated suppliers.
 */
public class Product {
    private String product_id;
    private String product_name;
    private String brand;
    private String model;
    private List<Provider> productSuppliers;

    /**
     * Constructs a Product object.
     *
     * @param product_id unique product identifier
     * @param product_name name of the product
     * @param brand product brand
     * @param model product model
     * @param productSuppliers list of suppliers for the product
     */
    public Product(String product_id, String product_name, String brand, String model, List<Provider> productSuppliers) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.brand = brand;
        this.model = model;
        this.productSuppliers = productSuppliers;
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
     * @return product brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @return product model
     */
    public String getModel() {
        return model;
    }

}