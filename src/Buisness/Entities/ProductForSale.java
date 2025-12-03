package Buisness.Entities;

import java.util.List;

public class ProductForSale extends Product{
    private double salePrice;
    private int unitsInStock;
    private int providerId;
    private String companyName;


    public ProductForSale(String productId, String productName, String brand, String model, List<Provider> productSuppliers, double salePrice, int unitsInStock, int providerId, String companyName) {
        super(productId, productName, brand, model, productSuppliers);

        this.salePrice = salePrice;
        this.unitsInStock = unitsInStock;
        this.providerId = providerId;
        this.companyName = companyName;
    }

    public double getSalePrice() {
        return this.salePrice;
    }

    public int getUnitsInStock() {
        return this.unitsInStock;
    }

    public int getProviderId() {
        return this.providerId;
    }

    public String getCompanyName() {
        return this.companyName;
    }
}
