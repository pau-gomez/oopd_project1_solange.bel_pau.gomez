package Persistance.Impl;

import Buisness.Entities.Client;
import Persistance.ClientsDao;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import Persistance.PersistenceException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.GsonBuilder;

/**
 * JSON implementation of ClientsDao using Gson for persistence.
 */
public class ClientsJsonDao implements ClientsDao {
    private static final String filepath = "src/Resources/clients.json";

    /**
     * Builds a Gson instance with the ClientDeserializer registered
     * to correctly handle Client subclass deserialization.
     *
     * @return configured Gson instance
     */
    private Gson buildGson() {
        return new GsonBuilder()
                .registerTypeAdapter(Client.class, new ClientDeserializer())
                .create();
    }

    /**
     * Loads all clients from the JSON file.
     *
     * @return list of clients
     * @throws PersistenceException if the file cannot be created, read, or parsed
     */
    @Override
    public List<Client> loadAllClients() throws PersistenceException {

        File file = new File(this.filepath);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                throw new PersistenceException("Could not create clients file.", e);
            }
        }

        try (FileReader reader = new FileReader(this.filepath)) {
            Gson gson = buildGson();
            return gson.fromJson(reader, (new TypeToken<List<Client>>() {
            }).getType());
        } catch (Exception e) {
            throw new PersistenceException("Could not load clients.", e);
        }
    }

    /**
     * Writes the list of clients to the JSON file.
     *
     * @param clients list of clients to save
     * @throws PersistenceException if the file cannot be written
     */
    @Override
    public void updateFile(List<Client> clients) throws PersistenceException {
        File file = new File(this.filepath);

        try (FileWriter writer = new FileWriter(file)) {
            Gson gson = buildGson();
            gson.toJson(clients, writer);
        } catch (Exception e) {
            throw new PersistenceException("Could not update client file.", e);
        }
    }
}