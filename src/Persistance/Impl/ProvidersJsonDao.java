package Persistance.Impl;

import Buisness.Entities.Provider;
import Persistance.ProvidersDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.util.List;

public class ProvidersJsonDao implements ProvidersDao {
    private final String filepath;

    public ProvidersJsonDao(String filepath) { this.filepath = filepath; }

    @Override
    public List<Provider> loadAllProviders() {
        try (FileReader reader = new FileReader(this.filepath)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, (new TypeToken<List<Provider>>() {}).getType());
        } catch (Exception e) {
            throw new RuntimeException("Could not load clients.", e);
        }
    }
}
