package Buisness;

import Buisness.Entities.Product;

import Persistance.ProductsDao;

import java.util.List;

/**
 * Handles product-related business operations.
 */
public class ProductsManager {

    private ProductsDao productsDao;

    /**
     * Initializes the manager with a JSON-based products DAO.
     */
    public ProductsManager(ProductsDao productsDao) {
        this.productsDao = productsDao;
    }

    /**
     * Finds products by name.
     *
     * @param name product name to search for
     * @return list of matching products
     */
    public List<Product> findProductsByName(String name) {
        return productsDao.findProductsByName(name);
    }

    /**
     * Validates the products file.
     *
     * @return true if the file is valid, false otherwise
     */
    public boolean checkProductsFile() {
        return productsDao.validateProductsFile();
    }
}