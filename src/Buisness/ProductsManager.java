package Buisness;

import Buisness.Entities.Product;

import Persistance.ProductsDao;

import java.util.List;

/**
 * Handles product-related business operations.
 */
public class ProductsManager {

    private final ProductsDao productsDao;

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


    // ADD this method:
    /**
     * Finds a product by its ID.
     *
     * @param id product ID
     * @return the product, or null if not found
     */
    public Product findById(String id) {
        return productsDao.findById(id);
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