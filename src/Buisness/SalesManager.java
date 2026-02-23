package Buisness;

import Buisness.Entities.Sale;
import Persistance.Impl.SalesCsvDao;
import Persistance.SalesDao;

import java.util.ArrayList;
import java.util.List;

public class SalesManager {
    private SalesDao salesDao;

    public SalesManager() {
        this.salesDao = new SalesCsvDao();
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

    public List<Sale> getAllSales() {
        return salesDao.loadAllSales();
    }

    public boolean checkSalesFile() {

        return true;
    }

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
