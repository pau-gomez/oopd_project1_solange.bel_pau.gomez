package Persistance;

import Buisness.Entities.Provider;

import java.util.List;

/**
 * Data access interface for provider persistence operations.
 */
public interface ProvidersDao {

    /**
     * Loads all providers from storage.
     *
     * @return list of providers
     */
    List<Provider> loadAllProviders();

    /**
     * Retrieves a provider by ID.
     *
     * @param id provider identifier
     * @return provider object or null if not found
     */
    Provider getOneProvider(int id);

    /**
     * Updates provider storage with the given list.
     *
     * @param providers list of providers to persist
     */
    void updateFile(List<Provider> providers);

    /**
     * Validates the providers file.
     *
     * @return true if file is valid, false otherwise
     */
    boolean validateProvidersFile();
}