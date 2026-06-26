package Buisness;

import Buisness.Entities.*;
import Persistance.PersistenceException;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages shopping cart operations such as adding products, checkout, and provider lookup.
 */
public class ShoppingCartManager {
    private List<ProductForSale> products =  new ArrayList<>();
    private List<Provider> providers;
    private final ProvidersManager providersManager;
    private final ShoppingCart shoppingCart;
    private ShippingCalculator shippingCalculator = new ShippingCalculator();
    private final ProductsManager productsManager;

    public ShoppingCartManager(ProvidersManager providersManager, ProductsManager productsManager) {
        this.providersManager = providersManager;
        this.productsManager = productsManager;
        this.shoppingCart = new ShoppingCart(products, 0.21);
    }

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

    public double checkout(Client client) throws PersistenceException {
        double total = 0.0;

        for (ProductForSale product : shoppingCart.products) {
            total += calculateSellingPrice(product, client); // pass client here
        }

        if (client instanceof OnlineClient) {
            total += shippingCalculator.calculateShippingCost((OnlineClient) client);
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
        int realIndex = index - 1;

        if (realIndex >= 0 && realIndex < products.size()) {
            products.remove(realIndex);
            return true;
        }

        return false;
    }

    /**
     * Finds the provider of a given product.
     *
     * @param product the product to search for
     * @return provider company name, or null if not found
     */
    public String getProvider(ProductForSale product) throws PersistenceException {
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
     * Calculates the final price of a product applying the correct VAT rules
     * based on product type and client type.
     *
     * @param pfs    the product for sale (has ID and base price)
     * @param client the purchasing client (affects VAT exemption)
     * @return final price
     */
    public double calculateSellingPrice(ProductForSale pfs, Client client) throws PersistenceException {
        Product product = productsManager.findById(pfs.getProductId());
        double basePrice = pfs.getSalePrice();

        // Corporate clients with non-Spanish billing address are VAT exempt
        if (client instanceof CorporateClient) {
            CorporateClient cc = (CorporateClient) client;
            if (!cc.getBillingAddress().toLowerCase().contains("spain") && !cc.getBillingAddress().toLowerCase().contains("españa") && !cc.getBillingAddress().toLowerCase().contains("espanya")) {
                return basePrice; // no VAT
            }
        }

        if (product instanceof ContactLenses) {
            return basePrice * 1.10;
        } else if (product instanceof Service) {
            Service s = (Service) product;
            return basePrice * s.getDurationHours() * 1.21;
        } else if (product instanceof Consumable) {
            Consumable c = (Consumable) product;
            double price = basePrice * 1.21;
            if (isExpiringSoon(c)) {
                price = price * 0.60; // 40% discount
            }
            return price;
        } else {
            // Glasses and anything else: 21% VAT
            return basePrice * 1.21;
        }
    }

    /**
     * Checks if a consumable expires within 3 months from today.
     *
     * @param c the consumable product
     * @return true if expiring within 3 months
     */
    private boolean isExpiringSoon(Consumable c) {
        try {
            // expiration_date format expected: "YYYY-MM-DD"
            java.time.LocalDate expiry = java.time.LocalDate.parse(c.getExpirationDate());
            java.time.LocalDate threeMonthsFromNow = java.time.LocalDate.now().plusMonths(3);
            return expiry.isBefore(threeMonthsFromNow);
        } catch (Exception e) {
            return false;
        }
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