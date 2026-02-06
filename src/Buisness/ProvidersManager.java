package Buisness;

import Buisness.Entities.Product;
import Buisness.Entities.ProductForSale;
import Buisness.Entities.Provider;
import Persistance.Impl.ProvidersJsonDao;
import Persistance.ProvidersDao;

import java.util.ArrayList;
import java.util.List;

public class ProvidersManager {
    private ProvidersDao providersDao;

    public ProvidersManager() {
        this.providersDao = new ProvidersJsonDao();
    }

    public void updateProviderStock(ProductForSale productBought) {
        List<Provider> providers = providersDao.loadAllProviders();

        boolean updated = false;
        int i = 0;

        while (i < providers.size() && !updated) {
            Provider provider = providers.get(i);

            if (provider.getProviderId() == productBought.getProviderId()) {
                List<ProductForSale> products = provider.getProductsForSale();

                int j = 0;
                while (j < products.size() && !updated) {
                    ProductForSale pfs = products.get(j);

                    if (pfs.getProductId().equals(productBought.getProductId())) {
                        int newStock = pfs.getUnitsInStock() - 1;

                        if (newStock < 0) {
                            newStock = 0;
                        }

                        pfs.setUnitsInStock(newStock);
                        updated = true;
                    }
                    j++;
                }
            }
            i++;
        }

        if (updated) {
            providersDao.updateFile(providers);
        }
    }

    public List<Provider> getAllProviders() {
        return providersDao.loadAllProviders();
    }

    public Provider getProvider(int id) {
        return providersDao.getOneProvider(id);
    }

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

    // helper
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

    public boolean checkProvidersFile() {
        return providersDao.validateProvidersFile();
    }
}
