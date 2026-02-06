package Persistance.Impl;


import Buisness.Entities.Sale;
import Persistance.SalesDao;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SalesCsvDao implements SalesDao {
    private static final String filepath = "src/Resources/sales.csv";

    //public SalesCsvDao(String filepath) { this.filepath = filepath;}

    @Override
    // @SuppressWarnings("unchecked")
    public List<Sale> loadAllSales() {

        File file = new File(this.filepath);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Could not create clients file.", e);
            }
        }

        try (FileReader reader = new FileReader(this.filepath)) {
            return (List<Sale>) new CsvToBeanBuilder(reader).withType(Sale.class).build().parse();
        } catch (Exception e) {
            throw new RuntimeException("Could not load sales.", e);
        }
    }

    @Override
    public void updateFile(List<Sale> sales) {
        File file = new File(this.filepath);

        try (FileWriter writer = new FileWriter(file)) {
            writer.append("client_id,product_id,price_paid,purchase_date\n");
            for (Sale sale : sales) {
                String saleLine = String.join(",",
                        String.valueOf(sale.getClientId()),
                        String.valueOf(sale.getProductId()),
                        String.valueOf(sale.getPricePaid()),
                        String.valueOf(sale.getPurchaseDate())) + "\n";
                writer.append(saleLine);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not update sales file.", e);
        }
    }
}
