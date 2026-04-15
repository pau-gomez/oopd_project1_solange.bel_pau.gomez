package Persistance.Impl;

import Buisness.Entities.Client;
import Persistance.ClientsDao;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * JSON implementation of ClientsDao using Gson for persistence.
 */
public class ClientsJsonDao implements ClientsDao {
    private static final String filepath = "src/Resources/clients.json";

    /**
     * Loads all clients from the JSON file.
     *
     * @return list of clients
     */
    @Override
    public List<Client> loadAllClients() {

        File file = new File(this.filepath);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Could not create clients file.", e);
            }
        }

        try (FileReader reader = new FileReader(this.filepath)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, (new TypeToken<List<Client>>() {
            }).getType());
        } catch (Exception e) {
            throw new RuntimeException("Could not load clients.", e);
        }
    }

    /**
     * Writes the list of clients to the JSON file.
     *
     * @param clients list of clients to save
     */
    @Override
    public void updateFile(List<Client> clients) {
        File file = new File(this.filepath);

        try (FileWriter writer = new FileWriter(file)) {
            Gson gson = new Gson();
            gson.toJson(clients, writer);
        } catch (IOException e) {
            throw new RuntimeException("Could not update client file.", e);
        }
    }
}