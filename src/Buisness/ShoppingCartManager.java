package Buisness;

import Buisness.Entities.*;
import Buisness.ProvidersManager;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

public class ShoppingCartManager {
    private List<ProductForSale> products =  new ArrayList<>();
    private List<Provider> providers;
    ProvidersManager providersManager = new ProvidersManager();
    //ProductsManager productsManager;
    ShoppingCart shoppingCart = new ShoppingCart(products, 0.21);
    private final Scanner scanner = new Scanner(System.in);


    public void addProduct(ProductForSale selected) {
        shoppingCart.products.add(selected);
    }

    //public void addProductsFromProvider(Provider provider) {}

    public void printCart() {
        int i = 0;

        System.out.println("----SHOPPING CART----");

        for (ProductForSale product: shoppingCart.products) {
            i++;
            System.out.print("(" + i + ") ");
            printProductInformation(product);
            System.out.print("\n");
        }

    }

    public void checkout() {
        double total = 0.0, salePrice = 0.0;

        System.out.println("----- PURCHASE INFORMATION -----");

        for (ProductForSale product : shoppingCart.products) {
            salePrice = calculateSellingPrice(product);
            total += salePrice;

            System.out.println(
                    "Product: " + product.getProductId() +
                            " | Supplier: " + getProvider(product) +
                            " | Price: " + String.format("%.2f", salePrice) + "€"
            );
        }

        System.out.println("-------------------");
        System.out.println("TOTAL: " + String.format("%.2f", total) + "€");

        clear();
    }

    public void clear() {
        products.clear();
    }

    public void deleteProduct() {
        boolean found = false;

        System.out.print("Product to delete (number): ");
        int index = scanner.nextInt();

        int i = 0;

        for (ProductForSale product: products) {
            i++;
            if(i == index) {
                products.remove(product);
                found = true;
            }
        }

        if(!found) {
            System.out.println("No such product");
        }
        else{
            System.out.print("Product deleted");
        }
    }

    public String getProvider(ProductForSale product) {
        providers = providersManager.getAllProviders();

        for(Provider provider : providers) {
            for (ProductForSale productToFind: provider.getProductsForSale()) {
                if (productToFind.getProductId().equalsIgnoreCase(product.getProductId())) {
                    return provider.getCompanyName();
                }
            }
        }

        // There always should be one found
        return null;
    }

    public double calculateSellingPrice(ProductForSale product) {
        return product.getSalePrice() * (1 + shoppingCart.getVat());
    }

    public void printProductInformation(ProductForSale product) {
        System.out.println("\nProduct ID: " + product.getProductId() + ",");
        //System.out.println("Name: " + product_name + ",");
        System.out.println("Price: " + product.getSalePrice() + ",");
        System.out.println("Stock: " + product.getUnitsInStock());

    }
}
