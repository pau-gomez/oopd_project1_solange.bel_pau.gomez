package Buisness.Entities;

import java.util.List;

public class ShoppingCart {
    public List<ProductForSale> products;
    public double vat;

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
