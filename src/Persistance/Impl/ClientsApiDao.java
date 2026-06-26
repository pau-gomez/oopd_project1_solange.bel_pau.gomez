package Persistance.Impl;

import Buisness.Entities.Client;
import Persistance.ClientsDao;
import Persistance.PersistenceException;
import edu.salle.url.api.ApiHelper;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * API implementation of ClientsDao.
 */
public class ClientsApiDao implements ClientsDao {

    private static final String BASE_URL = "https://balandrau.salle.url.edu/dpoo";
    private static final String GROUP_ID = "S1-Project-186";

    private final ApiHelper apiHelper;

    /**
     * Constructs a ClientsApiDao with the given ApiHelper.
     *
     * @param apiHelper a working ApiHelper instance for making HTTP requests
     */
    public ClientsApiDao(ApiHelper apiHelper) {
        this.apiHelper = apiHelper;
    }

    /**
     * Loads all clients from the API.
     *
     * @return list of clients, or empty list if none exist
     * @throws PersistenceException if the API request fails or the response cannot be parsed
     */
    @Override
    public List<Client> loadAllClients() throws PersistenceException {
        try {
            String response = apiHelper.getFromUrl(BASE_URL + "/" + GROUP_ID + "/clients");
            if (response == null || response.isBlank() || response.equals("[]")) return new ArrayList<>();
            return buildGson().fromJson(response, new TypeToken<List<Client>>() {}.getType());
        } catch (Exception e) {
            throw new PersistenceException("Could not load clients from API.", e);
        }
    }

    /**
     * Saves the newest client to the API via a POST request.
     *
     * @param clients list of clients — only the last entry is posted
     * @throws PersistenceException if the API request fails
     */
    @Override
    public void updateFile(List<Client> clients) throws PersistenceException {
        if (clients == null || clients.isEmpty()) return;
        try {
            Client newest = clients.get(clients.size() - 1);
            apiHelper.postToUrl(BASE_URL + "/" + GROUP_ID + "/clients", buildGson().toJson(newest));
        } catch (Exception e) {
            throw new PersistenceException("Could not save client to API.", e);
        }
    }

    /**
     * Builds a Gson instance with the ClientDeserializer registered
     * to correctly handle Client subclass deserialization.
     *
     * @return configured Gson instance
     */
    private Gson buildGson() {
        return new GsonBuilder().registerTypeAdapter(Client.class, new ClientDeserializer()).create();
    }
}