package Buisness.Entities;

import java.util.List;

public class Product {
    private String product_id;
    private String product_name;
    private String brand;
    private String model;
    private List<Provider> productSuppliers;

    public Product(String product_id, String product_name, String brand, String model, List<Provider> productSuppliers) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.brand = brand;
        this.model = model;
        this.productSuppliers = productSuppliers;
    }

    public String getProductId() {
        return product_id;
    }

    public String getProductName() {
        return product_name;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public List<Provider> getProductSuppliers() {
        return productSuppliers;
    }

}
