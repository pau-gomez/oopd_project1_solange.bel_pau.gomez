package Persistance;

import Buisness.Entities.Product;

import java.util.List;

/**
 * Data access interface for products.
 */
public interface ProductsDao {

    /**
     * Loads all products from storage.
     *
     * @return list of products
     * @throws PersistenceException if the storage cannot be accessed or parsed
     */
    List<Product> loadAllProducts() throws PersistenceException;

    /**
     * Validates that the products file exists and is readable/parsable.
     *
     * @return true if file is valid, false otherwise
     */
    boolean validateProductsFile();

    /**
     * Finds a product by its ID.
     *
     * @param id product ID
     * @return the product, or null if not found
     * @throws PersistenceException if the storage cannot be accessed or parsed
     */
    Product findById(String id) throws PersistenceException;

    /**
     * Finds products by name (case-insensitive partial match).
     *
     * @param name search term
     * @return matching products
     * @throws PersistenceException if the storage cannot be accessed or parsed
     */
    List<Product> findProductsByName(String name) throws PersistenceException;
}