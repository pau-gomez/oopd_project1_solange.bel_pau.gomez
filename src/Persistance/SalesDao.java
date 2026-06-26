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
     * @throws PersistenceException if the storage cannot be accessed or parsed
     */
    List<Sale> loadAllSales() throws PersistenceException;

    /**
     * Updates sales storage with the given list.
     *
     * @param sales list of sales to persist
     * @throws PersistenceException if the storage cannot be accessed or parsed
     */
    void updateFile(List<Sale> sales) throws PersistenceException;
}