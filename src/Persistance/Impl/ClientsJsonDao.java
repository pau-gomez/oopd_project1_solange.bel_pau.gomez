package Persistance.Impl;

import Buisness.Entities.Client;
import Persistance.ClientsDao;

import java.io.FileReader;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ClientsJsonDao implements ClientsDao {
    private static final String filepath = "src/Resources/clients.json";

    //public ClientsJsonDao(String filepath) { this.filepath = filepath; }

    @Override
    public List<Client> loadAllClients() {
        try (FileReader reader = new FileReader(this.filepath)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, (new TypeToken<List<Client>>() {}).getType());
        } catch (Exception e) {
            throw new RuntimeException("Could not load clients.", e);
        }
    }

    @Override
    public void updateFile(List<Client> clients) {

    }

}
