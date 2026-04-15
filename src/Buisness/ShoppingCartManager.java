package Buisness;

import Buisness.Entities.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages shopping cart operations such as adding products, checkout, and provider lookup.
 */
public class ShoppingCartManager {
    private List<ProductForSale> products =  new ArrayList<>();
    private List<Provider> providers;
    private ProvidersManager providersManager = new ProvidersManager();
    private ShoppingCart shoppingCart = new ShoppingCart(products, 0.21);

    /**
     * Adds a product to the shopping cart.
     *
     * @param selected the product to add
     */
    public void addProduct(ProductForSale selected) {
        shoppingCart.products.add(selected);
    }

    /**
     * Returns formatted information about products in the cart.
     *
     * @return list of product information strings
     */
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

    /**
     * Calculates total price and clears the cart.
     *
     * @return total price including VAT
     */
    public double checkout() {
        double total = 0.0;

        for (ProductForSale product : shoppingCart.products) {
            total += calculateSellingPrice(product);
        }
        clear();
        return total;
    }

    /**
     * Clears the shopping cart.
     */
    public void clear() {
        products.clear();
    }

    /**
     * Deletes a product from the cart by index.
     *
     * @param index position of the product in the cart (1-based)
     * @return true if product was removed, false otherwise
     */
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

    /**
     * Finds the provider of a given product.
     *
     * @param product the product to search for
     * @return provider company name, or null if not found
     */
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

    /**
     * Calculates selling price including VAT.
     *
     * @param product the product
     * @return final selling price
     */
    public double calculateSellingPrice(ProductForSale product) {
        return product.getSalePrice() * (1 + shoppingCart.getVat());
    }

    /**
     * Grants access to the shopping cart products.
     *
     * @return list of products in the cart
     */
    public List<ProductForSale> getProducts() {
        return shoppingCart.getProducts();
    }
}