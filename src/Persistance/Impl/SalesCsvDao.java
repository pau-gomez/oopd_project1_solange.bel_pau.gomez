package Persistance.Impl;


import Buisness.Entities.Sale;
import Persistance.SalesDao;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.util.List;

public class SalesCsvDao implements SalesDao {
    private static final String filepath = "src/Resources/sales.csv";

    //public SalesCsvDao(String filepath) { this.filepath = filepath;}

    @Override
    // @SuppressWarnings("unchecked")
    public List<Sale> loadAllSales() {
        try (FileReader reader = new FileReader(this.filepath)) {
            return (List<Sale>) new CsvToBeanBuilder(reader).withType(Sale.class).build().parse();
        } catch (Exception e) {
            throw new RuntimeException("Could not load sales.", e);
        }
    }

    @Override
    public void updateFile(List<Sale> sales) {

    }
}
