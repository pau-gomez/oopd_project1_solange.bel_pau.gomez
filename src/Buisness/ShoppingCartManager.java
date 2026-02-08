package Buisness;

import Buisness.Entities.Product;
import Buisness.Entities.ProductForSale;
import Buisness.Entities.Provider;
import Buisness.Entities.ShoppingCart;

import java.util.List;

public class ShoppingCartManager {
    private List<ProductForSale> products;
    ShoppingCart shoppingCart = new ShoppingCart(products, 0.21);

    public void addProduct(ProductForSale selected) {
        shoppingCart.products.add(selected);
        return;
    }

    public void addProductsFromProvider(Provider provider) {
    }

    public void printCart() {

    }

    public void checkout() {
        double total = 0.0, salePrice = 0.0;

        System.out.println("----- INVOICE -----");

        for (ProductForSale product : shoppingCart.products) {
            salePrice = calculateSellingPrice(product);
            total += salePrice;

            System.out.println(
                    "Product: " + product.getProductId() +
                            " | Supplier: " + product.getSupplier().getName() +
                            " | Price: " + String.format("%.2f", salePrice) + "€"
            );
        }

        System.out.println("-------------------");
        System.out.println("TOTAL: " + String.format("%.2f", total) + "€");
    }

    public void clear() {

    }

    public void deleteProduct() {
    }

    public double calculateSellingPrice(ProductForSale product) {
        return product.getSalePrice() * (1 + shoppingCart.getVat());
    }
}
