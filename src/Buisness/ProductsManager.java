package Buisness;

import Buisness.Entities.Product;
import Persistance.ProductsDao;
import Persistance.ProvidersDao;

import java.util.Collections;
import java.util.List;

public class  ProductsManager {

    private ProductsDao productsDao;

    public ProductsManager() {
        this.productsDao = productsDao;
    }

    public List<Product> findProductsByName(String text) {
        Product products = null;

        return Collections.singletonList(products);
    }

    public boolean checkProductsFile() {
        return productsDao.validateProductsFile();
    }
}
