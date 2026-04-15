package Persistance;

import Buisness.Entities.Sale;

import java.util.List;

/**
 * Data access interface for sales persistence operations.
 */
public interface SalesDao {

    /**
     * Loads all sales from storage.
     *
     * @return list of sales
     */
    List<Sale> loadAllSales();

    /**
     * Updates sales storage with the given list.
     *
     * @param sales list of sales to persist
     */
    void updateFile(List<Sale> sales);
}