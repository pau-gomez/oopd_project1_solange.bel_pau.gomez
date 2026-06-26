package Buisness;

import Buisness.Entities.Sale;
import Persistance.SalesDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles sales-related business operations.
 */
public class SalesManager {
    private final SalesDao salesDao;

    /**
     * Initializes the manager with a CSV-based sales DAO.
     */
    public SalesManager(SalesDao salesDao) {
        this.salesDao = salesDao;
    }

    /**
     * Adds a new sale to the system.
     *
     * @param sale the sale to add
     */
    public void addSale(Sale sale) {
        List<Sale> sales = salesDao.loadAllSales();
        sales.add(sale);
        salesDao.updateFile(sales);
    }

    /**
     * Filters sales by client ID.
     *
     * @param id client ID
     * @return list of sales belonging to the client
     */
    public List<Sale> filterSalesByClient(int id) {
        List<Sale> allSales;
        List<Sale> sales = new ArrayList<>();
        allSales = salesDao.loadAllSales();

        for(Sale s: allSales) {
            if (s.client_id == id) {
                sales.add(s);
            }
        }

        return sales;
    }

}