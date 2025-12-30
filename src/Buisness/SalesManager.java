package Buisness;

import Buisness.Entities.Sale;
import Persistance.SalesDao;

import java.util.ArrayList;
import java.util.List;

public class SalesManager {
    private SalesDao salesDao;

    public SalesManager(SalesDao salesDao) {
        this.salesDao = salesDao;
    }

    public void addSale(Sale sale) {
        List<Sale> sales = salesDao.loadAllSales();
        sales.add(sale);
        salesDao.updateFile(sales);
    }

    public List<Sale> filterSales(int clientId) {
        List<Sale> sales = salesDao.loadAllSales();
        List<Sale> clientSales = new ArrayList<>();

        int i = 0;
        while (i < sales.size()) {
            if (sales.get(i).getClientId() == clientId) {
                clientSales.add(sales.get(i));
            }
            i++;
        }
        return clientSales;
    }
}
