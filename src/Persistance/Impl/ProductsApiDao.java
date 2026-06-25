package Persistance.Impl;

import Buisness.Entities.Product;
import Persistance.ProductsDao;
import edu.salle.url.api.ApiHelper;
import edu.salle.url.api.exception.ApiException;
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

    public ProductsApiDao(ApiHelper apiHelper) {
        this.apiHelper = apiHelper;
    }

    @Override
    public List<Product> loadAllProducts() {
        try {
            String response = apiHelper.getFromUrl(BASE_URL + "/shared/products");
            if (response == null || response.isBlank() || response.equals("[]")) return new ArrayList<>();
            return buildGson().fromJson(response, new TypeToken<List<Product>>() {}.getType());
        } catch (ApiException e) {
            throw new RuntimeException("Could not load products from API.", e);
        }
    }

    @Override
    public boolean validateProductsFile() {
        try {
            String response = apiHelper.getFromUrl(BASE_URL + "/shared/products");
            return response != null && !response.isBlank();
        } catch (ApiException e) {
            return false;
        }
    }

    @Override
    public Product findById(String id) {
        try {
            String url = BASE_URL + "/shared/products?product_id=" + id;
            String response = apiHelper.getFromUrl(url);
            if (response == null || response.isBlank() || response.equals("[]")) return null;
            List<Product> results = buildGson().fromJson(response, new TypeToken<List<Product>>() {}.getType());
            return results.isEmpty() ? null : results.get(0);
        } catch (ApiException e) {
            return null;
        }
    }

    @Override
    public List<Product> findProductsByName(String name) {
        try {
            String url;
            if (name == null || name.isBlank()) url = BASE_URL + "/shared/products";
            else url = BASE_URL + "/shared/products?product_name=" + name.trim().replace(" ", "%20");

            String response = apiHelper.getFromUrl(url);
            if (response == null || response.isBlank() || response.equals("[]")) return new ArrayList<>();
            return buildGson().fromJson(response, new TypeToken<List<Product>>() {}.getType());
        } catch (ApiException e) {
            throw new RuntimeException("Could not search products from API.", e);
        }
    }

    private Gson buildGson() {
        return new GsonBuilder().registerTypeAdapter(Product.class, new ProductDeserializer()).create();
    }
}