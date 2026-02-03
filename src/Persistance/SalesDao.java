package Persistance;

import Buisness.Entities.Sale;

import java.util.List;

public interface SalesDao {
    List<Sale> loadAllSales();

    void updateFile(List<Sale> sales);
}
