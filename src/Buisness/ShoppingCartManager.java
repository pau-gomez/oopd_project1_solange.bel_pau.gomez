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
    private ProvidersManager providersManager = new ProvidersManager();
    //ProductsManager productsManager;
    private ShoppingCart shoppingCart = new ShoppingCart(products, 0.21);
    //private final Scanner scanner = new Scanner(System.in);


    public void addProduct(ProductForSale selected) {
        shoppingCart.products.add(selected);
    }

    public List<String> getCartInformation() {
        List<String> lines = new ArrayList<>();

        int i = 1;
        for (ProductForSale product : shoppingCart.products) {

            String line = "(" + i + ") " + product.getProductId() + " - "
                    + product.getSalePrice() + "€"
                    + " - stock: " + product.getUnitsInStock();

            lines.add(line);
            i++;
        }

        return lines;
    }


    public double checkout() {
        double total = 0.0;

        for (ProductForSale product : shoppingCart.products) {
            total += calculateSellingPrice(product);
        }
        clear();
        return total;
    }

    public void clear() {
        products.clear();
    }

    public boolean deleteProduct(int index) {
        boolean found = false;

        int i = 0;

        for (ProductForSale product: products) {
            i++;
            if(i == index) {
                products.remove(product);
                found = true;
            }
        }

        return found;
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

    public List<ProductForSale> getProducts() {
        return shoppingCart.getProducts();
    }
}
