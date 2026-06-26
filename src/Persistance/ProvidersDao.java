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
    List<Provider> loadAllProviders() throws PersistenceException;

    /**
     * Retrieves a provider by ID.
     *
     * @param id provider identifier
     * @return provider object or null if not found
     */
    Provider getOneProvider(int id) throws PersistenceException;

    /**
     * Updates provider storage with the given list.
     *
     * @param providers list of providers to persist
     */
    void updateFile(List<Provider> providers) throws PersistenceException;

    /**
     * Validates the providers file.
     *
     * @return true if file is valid, false otherwise
     */
    boolean validateProvidersFile();
}