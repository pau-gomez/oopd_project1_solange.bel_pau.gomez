package Persistance.Impl;

import Buisness.Entities.Product;
import Persistance.ProductsDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.util.List;

public class ProductsJsonDao implements ProductsDao {
    private final String filepath;

    public ProductsJsonDao(String filepath) { this.filepath = filepath; }

    @Override
    public List<Product> loadAllProducts() {
        try (FileReader reader = new FileReader(this.filepath)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, (new TypeToken<List<Product>>() {}).getType());
        } catch (Exception e) {
            throw new RuntimeException("Could not load products.", e);
        }
    }
}
