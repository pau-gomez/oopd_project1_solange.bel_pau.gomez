package Persistance.Impl;

import Buisness.Entities.Client;
import Persistance.ClientsDao;
import edu.salle.url.api.ApiHelper;
import edu.salle.url.api.exception.ApiException;
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

    public ClientsApiDao(ApiHelper apiHelper) {
        this.apiHelper = apiHelper;
    }

    @Override
    public List<Client> loadAllClients() {
        try {
            String response = apiHelper.getFromUrl(BASE_URL + "/" + GROUP_ID + "/clients");
            if (response == null || response.isBlank() || response.equals("[]")) return new ArrayList<>();
            return buildGson().fromJson(response, new TypeToken<List<Client>>() {}.getType());
        } catch (ApiException e) {
            throw new RuntimeException("Could not load clients from API.", e);
        }
    }

    @Override
    public void updateFile(List<Client> clients) {
        if (clients == null || clients.isEmpty()) return;
        try {
            // The API doesn't support bulk updates — we POST only the newest client
            Client newest = clients.get(clients.size() - 1);
            apiHelper.postToUrl(BASE_URL + "/" + GROUP_ID + "/clients", buildGson().toJson(newest));
        } catch (ApiException e) {
            throw new RuntimeException("Could not save client to API.", e);
        }
    }

    private Gson buildGson() {
        return new GsonBuilder().registerTypeAdapter(Client.class, new ClientDeserializer()).create();
    }
}