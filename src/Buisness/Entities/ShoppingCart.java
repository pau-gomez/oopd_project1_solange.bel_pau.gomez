package Buisness.Entities;

import java.util.List;

public class ShoppingCart {
    private List<ProductForSale> products;
    private double vat;

    public ShoppingCart(List<ProductForSale> products, double vat) {
        this.products = products;
        this.vat = vat;
    }

    public List<ProductForSale> getProducts() {
        return this.products;
    }

    public double getVat() {
        return this.vat;
    }
}
