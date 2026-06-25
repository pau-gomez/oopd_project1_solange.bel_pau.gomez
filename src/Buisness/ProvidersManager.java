package Buisness;

import Buisness.Entities.Product;
import Buisness.Entities.ProductForSale;
import Buisness.Entities.Provider;
import Persistance.Impl.ProvidersJsonDao;
import Persistance.ProvidersDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles provider-related business operations.
 */
public class ProvidersManager {
    private final ProvidersDao providersDao;

    /**
     * Initializes the manager with a JSON-based providers DAO.
     */
    public ProvidersManager(ProvidersDao providersDao) {
        this.providersDao = providersDao;
    }

    /**
     * Updates stock for a purchased product across providers.
     *
     * @param productBought the product that was purchased
     */
    public void updateProviderStock(ProductForSale productBought) {
        List<Provider> providers = providersDao.loadAllProviders();
        boolean updated = false;

        for (Provider provider : providers) {
            List<ProductForSale> products = provider.getProductsForSale();

            if (products == null) continue;

            for (ProductForSale pfs : products) {
                if (pfs.getProductId().equals(productBought.getProductId())) {

                    int newStock = pfs.getUnitsInStock() - 1;
                    pfs.setUnitsInStock(Math.max(newStock, 0));

                    updated = true;
                    break;
                }
            }

            if (updated) break;
        }

        if (updated) {
            providersDao.updateFile(providers);
        }
    }

    /**
     * Grants access to the user of all providers.
     *
     * @return list of all providers
     */
    public List<Provider> getAllProviders() {
        return providersDao.loadAllProviders();
    }

    /**
     * Gets a provider by ID.
     *
     * @param id provider ID
     * @return provider or null if not found
     */
    public Provider getProvider(int id) {
        return providersDao.getOneProvider(id);
    }

    /**
     * Finds providers that sell a given product.
     *
     * @param product the product to search for
     * @return list of providers selling the product
     */
    public List<Provider> getProviderByProduct(Product product) {
        List<Provider> providers = providersDao.loadAllProviders();
        List<Provider> result = new ArrayList<>();

        if (product == null) {
            return result;
        }

        String productId = product.getProductId();
        int i = 0;

        while (i < providers.size()) {
            if (providerSellsProduct(providers.get(i), productId)) {
                result.add(providers.get(i));
            }
            i++;
        }
        return result;
    }

    /**
     * Checks if a provider sells a specific product.
     *
     * @param provider the provider
     * @param productId product ID
     * @return true if provider sells the product
     */
    private boolean providerSellsProduct(Provider provider, String productId) {
        boolean sells = false;

        if (provider != null && productId != null) {
            List<ProductForSale> productsForSale = provider.getProductsForSale();

            int j = 0;
            while (j < productsForSale.size() && !sells) {
                if (productsForSale.get(j).getProductId().equals(productId)) {
                    sells = true;
                }
                j++;
            }
        }
        return sells;
    }

    /**
     * Validates the providers file.
     *
     * @return true if valid, false otherwise
     */
    public boolean checkProvidersFile() {
        return providersDao.validateProvidersFile();
    }
}