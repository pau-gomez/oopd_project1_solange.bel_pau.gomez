package Persistance.Impl;

import Buisness.Entities.Sale;
import Persistance.PersistenceException;
import Persistance.SalesDao;
import edu.salle.url.api.ApiHelper;
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

    /**
     * Constructs a SalesApiDao with the given ApiHelper.
     *
     * @param apiHelper a working ApiHelper instance for making HTTP requests
     */
    public SalesApiDao(ApiHelper apiHelper) {
        this.apiHelper = apiHelper;
    }

    /**
     * Loads all sales from the API.
     *
     * @return list of sales, or empty list if none exist
     * @throws PersistenceException if the API request fails or the response cannot be parsed
     */
    @Override
    public List<Sale> loadAllSales() throws PersistenceException {
        try {
            String response = apiHelper.getFromUrl(BASE_URL + "/" + GROUP_ID + "/sales");
            if (response == null || response.isBlank() || response.equals("[]")) return new ArrayList<>();
            return new Gson().fromJson(response, new TypeToken<List<Sale>>() {}.getType());
        } catch (Exception e) {
            throw new PersistenceException("Could not load sales from API.", e);
        }
    }

    /**
     * Saves the newest sale to the API via a POST request.
     *
     * @param sales list of sales — only the last entry is posted
     * @throws PersistenceException if the API request fails
     */
    @Override
    public void updateFile(List<Sale> sales) throws PersistenceException {
        if (sales == null || sales.isEmpty()) return;
        try {
            Sale newest = sales.get(sales.size() - 1);
            apiHelper.postToUrl(BASE_URL + "/" + GROUP_ID + "/sales", new Gson().toJson(newest));
        } catch (Exception e) {
            throw new PersistenceException("Could not save sale to API.", e);
        }
    }
}