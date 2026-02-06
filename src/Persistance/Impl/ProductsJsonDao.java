package Persistance.Impl;

import Buisness.Entities.Product;
import Buisness.Entities.Provider;
import Persistance.ProductsDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.util.List;

public class ProductsJsonDao implements ProductsDao {
    private final String filepath = "src/Resources/products.json";

    //public ProductsJsonDao(String filepath) { this.filepath = filepath; }

    @Override
    public List<Product> loadAllProducts() {
        try (FileReader reader = new FileReader(this.filepath)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, (new TypeToken<List<Product>>() {}).getType());
        } catch (Exception e) {
            throw new RuntimeException("Could not load products.", e);
        }
    }

    @Override
    public boolean validateProductsFile() {
        File file = new File(this.filepath);
        // check if file exists or can be read
        if (!file.exists() || !file.canRead()) {
            return false;
        }
        // check if its parseable
        try (FileReader reader = new FileReader(this.filepath)) {
            Gson gson = new Gson();
            gson.fromJson(reader, new TypeToken<List<Provider>>() {}.getType());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Product> findProductsByName() {
        return List.of();
    }

    @Override
    public List<Provider> findProductsBySupplier() {
        return List.of();
    }
}
