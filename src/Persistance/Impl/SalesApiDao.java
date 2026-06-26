package Persistance.Impl;

import Buisness.Entities.Sale;
import Persistance.PersistenceException;
import Persistance.SalesDao;
import edu.salle.url.api.ApiHelper;
import edu.salle.url.api.exception.ApiException;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * API implementation of SalesDao.
 */
public class SalesApiDao implements SalesDao {

    private static final String BASE_URL = "https://balandrau.salle.url.edu/dpoo";
    private static final String GROUP_ID = "S1-Project-186";

    private final ApiHelper apiHelper;

    public SalesApiDao(ApiHelper apiHelper) {
        this.apiHelper = apiHelper;
    }

    @Override
    public List<Sale> loadAllSales() throws PersistenceException {
        try {
            String response = apiHelper.getFromUrl(BASE_URL + "/" + GROUP_ID + "/sales");
            if (response == null || response.isBlank() || response.equals("[]")) return new ArrayList<>();
            return new Gson().fromJson(response, new TypeToken<List<Sale>>() {}.getType());
        } catch (ApiException e) {
            throw new PersistenceException("Could not load sales from API.", e);
        }
    }

    @Override
    public void updateFile(List<Sale> sales) throws PersistenceException {
        if (sales == null || sales.isEmpty()) return;
        try {
            // POST only the newest sale
            Sale newest = sales.get(sales.size() - 1);
            apiHelper.postToUrl(BASE_URL + "/" + GROUP_ID + "/sales", new Gson().toJson(newest));
        } catch (ApiException e) {
            throw new PersistenceException("Could not save sale to API.", e);
        }
    }
}