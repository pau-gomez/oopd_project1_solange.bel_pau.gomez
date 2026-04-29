package Persistance.Impl;

import Buisness.Entities.Product;
import Buisness.Entities.Provider;
import Persistance.ProductsDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * JSON implementation of ProductsDao using Gson for persistence.
 */
public class ProductsJsonDao implements ProductsDao {
    private final String filepath = "src/Resources/products.json";

    /**
     * Loads all products from the JSON file.
     *
     * @return list of products
     */
    @Override
    public List<Product> loadAllProducts() {
        try (FileReader reader = new FileReader(this.filepath)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, (new TypeToken<List<Product>>() {}).getType());
        } catch (Exception e) {
            throw new RuntimeException("Could not load products.", e);
        }
    }

    /**
     * Validates the products file existence and format.
     *
     * @return true if file is readable and parseable, false otherwise
     */
    @Override
    public boolean validateProductsFile() {
        File file = new File(this.filepath);

        if (!file.exists() || !file.canRead()) {
            return false;
        }

        try (FileReader reader = new FileReader(this.filepath)) {
            Gson gson = new Gson();
            gson.fromJson(reader, new TypeToken<List<Provider>>() {}.getType());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Finds products by name (partial match).
     *
     * @param name product name filter
     * @return filtered list of products
     */
    @Override
    public List<Product> findProductsByName(String name) {
        List<Product> allProducts = loadAllProducts();
        List<Product> filteredProducts = new ArrayList<>();

        if (name == null || name.isBlank()) {
            return allProducts;
        }

        for (Product product : allProducts) {
            if (product.getProductName()
                    .toLowerCase()
                    .contains(name.toLowerCase())) {
                filteredProducts.add(product);
            }
        }

        return filteredProducts;
    }
}