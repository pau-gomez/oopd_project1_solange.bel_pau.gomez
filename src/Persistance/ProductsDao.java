package Persistance;

import Buisness.Entities.Product;
import Buisness.Entities.Provider;

import java.util.List;

/**
 * Data access interface for products.
 */
public interface ProductsDao {

    /**
     * Loads all products from storage.
     *
     * @return list of products
     */
    List<Product> loadAllProducts();

    /**
     * Validates that the products file exists and is readable/parsable.
     *
     * @return true if file is valid, false otherwise
     */
    boolean validateProductsFile();

    /**
     * Finds products by name (case-insensitive partial match).
     *
     * @param name search term
     * @return matching products
     */
    List<Product> findProductsByName(String name);
}