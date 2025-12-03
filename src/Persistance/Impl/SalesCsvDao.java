package Persistance.Impl;

import Buisness.Entities.Sale;
import Persistance.SalesDao;

import java.util.List;

public class SalesCsvDao implements SalesDao {
    @Override
    public List<Sale> loadAllSales() {
        return List.of();
    }
}
