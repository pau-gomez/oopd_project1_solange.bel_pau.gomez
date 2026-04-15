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

/**
 * JSON implementation of ProvidersDao using Gson for persistence.
 */
public class ProvidersJsonDao implements ProvidersDao {
    private static final String filepath = "src/Resources/providers.json";

    /**
     * Loads all providers from the JSON file.
     *
     * @return list of providers
     */
    @Override
    public List<Provider> loadAllProviders() {
        try (FileReader reader = new FileReader(this.filepath)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, (new TypeToken<List<Provider>>() {}).getType());
        } catch (Exception e) {
            throw new RuntimeException("Could not load providers.", e);
        }
    }

    /**
     * Not implemented.
     *
     * @param id provider ID
     * @return null
     */
    @Override
    public Provider getOneProvider(int id) {
        return null;
    }

    /**
     * Writes providers list to JSON file.
     *
     * @param providers list of providers to save
     */
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

    /**
     * Validates the providers file existence and format.
     *
     * @return true if file is readable and parseable, false otherwise
     */
    @Override
    public boolean validateProvidersFile() {
        File file = new File(this.filepath);

        if (!file.exists() || !file.canRead()) {
            return false;
        }

        try (FileReader reader = new FileReader(this.filepath)) {
            Gson gson = new Gson();
            gson.fromJson(reader, new TypeToken<List<Provider>>() {}.getType());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}