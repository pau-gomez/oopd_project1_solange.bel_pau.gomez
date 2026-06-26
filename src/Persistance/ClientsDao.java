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
     */
    List<Client> loadAllClients() throws PersistenceException;

    /**
     * Updates the storage with the given clients list.
     *
     * @param clients list of clients to persist
     */
    void updateFile(List<Client> clients) throws PersistenceException;
}