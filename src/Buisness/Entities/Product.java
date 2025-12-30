package Buisness.Entities;

import java.util.List;

public abstract class Product {
    private String productId;
    private String productName;
    private String brand;
    private String model;
    private List<Provider> productSuppliers;

    public Product(String productId, String productName, String brand, String model, List<Provider> productSuppliers) {
        this.productId = productId;
        this.productName = productName;
        this.brand = brand;
        this.model = model;
        this.productSuppliers = productSuppliers;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
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

    public String getId() {
        String id = null;

        return id;
    }

    public String getName() {
        String name = null;

        return name;
    }
}
