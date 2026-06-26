package Persistance.Impl;

import Buisness.Entities.Provider;
import Persistance.PersistenceException;
import Persistance.ProvidersDao;
import edu.salle.url.api.ApiHelper;
import edu.salle.url.api.exception.ApiException;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * API implementation of ProvidersDao.
 * Providers are shared and read-only.
 */
public class ProvidersApiDao implements ProvidersDao {

    private static final String BASE_URL = "https://balandrau.salle.url.edu/dpoo";

    private final ApiHelper apiHelper;

    public ProvidersApiDao(ApiHelper apiHelper) {
        this.apiHelper = apiHelper;
    }

    @Override
    public List<Provider> loadAllProviders() throws PersistenceException {
        try {
            String response = apiHelper.getFromUrl(BASE_URL + "/shared/providers");
            if (response == null || response.isBlank() || response.equals("[]")) return new ArrayList<>();
            return new Gson().fromJson(response, new TypeToken<List<Provider>>() {}.getType());
        } catch (ApiException e) {
            throw new PersistenceException("Could not load providers from API.", e);
        }
    }

    @Override
    public void updateFile(List<Provider> providers) {
        // Providers are shared and immutable on the API — stock updates are not persisted remotely
    }

    @Override
    public boolean validateProvidersFile() {
        try {
            String response = apiHelper.getFromUrl(BASE_URL + "/shared/providers");
            return response != null && !response.isBlank();
        } catch (ApiException e) {
            return false;
        }
    }
}