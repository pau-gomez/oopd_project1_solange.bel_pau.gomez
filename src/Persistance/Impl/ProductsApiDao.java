package Persistance.Impl;

import Buisness.Entities.Product;
import Persistance.PersistenceException;
import Persistance.ProductsDao;
import edu.salle.url.api.ApiHelper;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * API implementation of ProductsDao.
 * Products are shared and read-only.
 */
public class ProductsApiDao implements ProductsDao {

    private static final String BASE_URL = "https://balandrau.salle.url.edu/dpoo";

    private final ApiHelper apiHelper;

    /**
     * Constructs a ProductsApiDao with the given ApiHelper.
     *
     * @param apiHelper a working ApiHelper instance for making HTTP requests
     */
    public ProductsApiDao(ApiHelper apiHelper) {
        this.apiHelper = apiHelper;
    }

    /**
     * Loads all products from the shared API endpoint.
     *
     * @return list of all products
     * @throws PersistenceException if the API request fails or the response cannot be parsed
     */
    @Override
    public List<Product> loadAllProducts() throws PersistenceException {
        try {
            String response = apiHelper.getFromUrl(BASE_URL + "/shared/products");
            if (response == null || response.isBlank() || response.equals("[]")) return new ArrayList<>();
            return buildGson().fromJson(response, new TypeToken<List<Product>>() {}.getType());
        } catch (Exception e) {
            throw new PersistenceException("Could not load products from API.", e);
        }
    }

    /**
     * Validates that the shared products API endpoint is reachable and returns data.
     *
     * @return true if the endpoint is accessible, false otherwise
     */
    @Override
    public boolean validateProductsFile() {
        try {
            String response = apiHelper.getFromUrl(BASE_URL + "/shared/products");
            return response != null && !response.isBlank();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Finds a product by its ID using the API query parameter.
     *
     * @param id the product ID to search for
     * @return the matching product, or null if not found
     * @throws PersistenceException if the API request fails
     */
    @Override
    public Product findById(String id) throws PersistenceException {
        try {
            String url = BASE_URL + "/shared/products?product_id=" + id;
            String response = apiHelper.getFromUrl(url);
            if (response == null || response.isBlank() || response.equals("[]")) return null;
            List<Product> results = buildGson().fromJson(response, new TypeToken<List<Product>>() {}.getType());
            if(results.isEmpty()) return null;
            else return results.get(0);
        } catch (Exception e) {
            throw new PersistenceException("Couldn't find a product.", e);
        }
    }

    /**
     * Finds products by name using the API query parameter (case-insensitive partial match).
     *
     * @param name the product name to search for, or null/blank to return all products
     * @return list of matching products
     * @throws PersistenceException if the API request fails
     */
    @Override
    public List<Product> findProductsByName(String name) throws PersistenceException {
        try {
            String url;
            if (name == null || name.isBlank()) url = BASE_URL + "/shared/products";
            else url = BASE_URL + "/shared/products?product_name=" + name.trim().replace(" ", "%20");

            String response = apiHelper.getFromUrl(url);
            if (response == null || response.isBlank() || response.equals("[]")) return new ArrayList<>();
            return buildGson().fromJson(response, new TypeToken<List<Product>>() {}.getType());
        } catch (Exception e) {
            throw new PersistenceException("Could not search products from API.", e);
        }
    }

    /**
     * Builds a Gson instance with the ProductDeserializer registered
     * to correctly handle Product subclass deserialization.
     *
     * @return configured Gson instance
     */
    private Gson buildGson() {
        return new GsonBuilder().registerTypeAdapter(Product.class, new ProductDeserializer()).create();
    }
}