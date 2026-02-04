package Persistance;

import Buisness.Entities.Product;
import Buisness.Entities.Provider;

import java.util.List;

public interface ProductsDao {
    List<Product> loadAllProducts();

    boolean validateProductsFile();

    List<Product> findProductsByName();

    List<Provider> findProductsBySupplier();
}
