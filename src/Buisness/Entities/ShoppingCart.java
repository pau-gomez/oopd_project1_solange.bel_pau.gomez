package Buisness.Entities;

import java.util.List;

/**
 * Represents a shopping cart containing products and VAT information.
 */
public class ShoppingCart {
    public final List<ProductForSale> products;
    private final double vat;

    /**
     * Constructs a ShoppingCart object.
     *
     * @param products list of products in the cart
     * @param vat value-added tax rate
     */
    public ShoppingCart(List<ProductForSale> products, double vat) {
        this.products = products;
        this.vat = vat;
    }


    /**
     * @return list of products in the cart
     */
    public List<ProductForSale> getProducts() {
        return this.products;
    }

    /**
     * @return VAT rate
     */
    public double getVat() {
        return this.vat;
    }
}