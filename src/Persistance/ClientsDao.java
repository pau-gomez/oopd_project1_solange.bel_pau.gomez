package Persistance;

import Buisness.Entities.Client;

import java.util.List;

public interface ClientsDao {
    List<Client> loadAllClients();

    void updateFile(List<Client> clients);
}
