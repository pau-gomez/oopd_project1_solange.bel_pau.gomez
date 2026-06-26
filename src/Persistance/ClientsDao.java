package Persistance;

import Buisness.Entities.Client;

import java.util.List;

/**
 * Data access interface for client persistence operations.
 */
public interface ClientsDao {

    /**
     * Loads all clients from storage.
     *
     * @return list of clients
     * @throws PersistenceException if the storage cannot be accessed or parsed
     */
    List<Client> loadAllClients() throws PersistenceException;

    /**
     * Updates the storage with the given clients list.
     *
     * @param clients list of clients to persist
     * @throws PersistenceException if the storage cannot be accessed or parsed
     */
    void updateFile(List<Client> clients) throws PersistenceException;
}