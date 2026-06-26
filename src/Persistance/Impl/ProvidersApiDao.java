package Persistance.Impl;

import Buisness.Entities.Provider;
import Persistance.PersistenceException;
import Persistance.ProvidersDao;
import edu.salle.url.api.ApiHelper;
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

    /**
     * Constructs a ProvidersApiDao with the given ApiHelper.
     *
     * @param apiHelper a working ApiHelper instance for making HTTP requests
     */
    public ProvidersApiDao(ApiHelper apiHelper) {
        this.apiHelper = apiHelper;
    }

    /**
     * Loads all providers from the shared API endpoint.
     *
     * @return list of all providers
     * @throws PersistenceException if the API request fails or the response cannot be parsed
     */
    @Override
    public List<Provider> loadAllProviders() throws PersistenceException {
        try {
            String response = apiHelper.getFromUrl(BASE_URL + "/shared/providers");
            if (response == null || response.isBlank() || response.equals("[]")) return new ArrayList<>();
            return new Gson().fromJson(response, new TypeToken<List<Provider>>() {}.getType());
        } catch (Exception e) {
            throw new PersistenceException("Could not load providers from API.", e);
        }
    }

    /**
     * No-op — providers are shared and read-only on the API,
     * so stock updates are not persisted remotely.
     *
     * @param providers list of providers (ignored in API mode)
     */
    @Override
    public void updateFile(List<Provider> providers) {
        // Providers are shared and immutable on the API — stock updates are not persisted remotely
    }

    /**
     * Validates that the shared providers API endpoint is reachable and returns data.
     *
     * @return true if the endpoint is accessible, false otherwise
     */
    @Override
    public boolean validateProvidersFile() {
        try {
            String response = apiHelper.getFromUrl(BASE_URL + "/shared/providers");
            return response != null && !response.isBlank();
        } catch (Exception e) {
            return false;
        }
    }
}