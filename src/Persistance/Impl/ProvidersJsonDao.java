package Persistance.Impl;

import Buisness.Entities.Provider;
import Persistance.ProvidersDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ProvidersJsonDao implements ProvidersDao {
    private static final String filepath = "src/Resources/providers.json";

    //public ProvidersJsonDao(String filepath) { this.filepath = filepath; }

    @Override
    public List<Provider> loadAllProviders() {
        try (FileReader reader = new FileReader(this.filepath)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, (new TypeToken<List<Provider>>() {}).getType());
        } catch (Exception e) {
            throw new RuntimeException("Could not load providers.", e);
        }
    }

    @Override
    public Provider getOneProvider(int id) {
        return null;
    }

    @Override
    public void updateFile(List<Provider> providers) {
        File file = new File(this.filepath);

        try (FileWriter writer = new FileWriter(file)) {
            Gson gson = new Gson();
            gson.toJson(providers, writer);
        } catch (IOException e) {
            throw new RuntimeException("Could not update provider file.", e);
        }
    }

    @Override
    public boolean validateProvidersFile() {
        File file = new File(this.filepath);
        // check if file exists or can be read
        if (!file.exists() || !file.canRead()) {
            return false;
        }
        // check if its parseable
        try (FileReader reader = new FileReader(this.filepath)) {
            Gson gson = new Gson();
            gson.fromJson(reader, new TypeToken<List<Provider>>() {}.getType());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
