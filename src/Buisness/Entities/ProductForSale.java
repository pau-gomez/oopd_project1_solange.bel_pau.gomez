package Buisness.Entities;

import java.util.List;

public class ProductForSale {
    private String product_id;
    private double selling_price;
    private int units_in_stock;


    public ProductForSale(String product_id, double selling_price, int units_in_stock) {
        this.product_id = product_id;
        this.selling_price = selling_price;
        this.units_in_stock = units_in_stock;
    }

    public double getSalePrice() {
        return this.selling_price;
    }

    public int getUnitsInStock() {
        return this.units_in_stock;
    }

    public String getProductId() {
        return this.product_id;
    }

    public void setUnitsInStock(int newStock) {
        this.units_in_stock = newStock;
    }
}
