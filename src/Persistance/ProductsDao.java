package Persistance;

import Buisness.Entities.Product;

import java.util.List;

public interface ProductsDao {
    List<Product> loadAllProducts();
}
