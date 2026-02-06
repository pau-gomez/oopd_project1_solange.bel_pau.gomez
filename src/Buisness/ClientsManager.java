package Buisness;

import Buisness.Entities.Client;
import Buisness.Entities.PhoneNumber;
import Persistance.ClientsDao;
import Persistance.Impl.ClientsJsonDao;

import java.util.List;

public class ClientsManager {
    private Client currentClient;
    private ClientsDao clientsDao;

    public ClientsManager() {
        this.clientsDao = new ClientsJsonDao();
        this.currentClient = null;
    }

    public boolean login(int clientId) {
        List<Client> clients = clientsDao.loadAllClients();
        boolean found = false;
        int i = 0;

        while (i < clients.size() && !found) {
            if (clients.get(i).getClientId() == clientId) {
                currentClient = clients.get(i);
                found = true;
            }
            i++;
        }
        return found;
    }

    public boolean registerClient(String fullName, List<PhoneNumber> phoneNumbers) {
        List<Client> clients = clientsDao.loadAllClients();

        int newClientId = generateNewClientId(clients);
        Client newClient = new Client(newClientId, fullName, phoneNumbers);

        clients.add(newClient);
        clientsDao.updateFile(clients);

        currentClient = newClient;

        if (newClient.getPhoneNumbers() == null) return false;

        return true;
    }

    public void logout() {
        currentClient = null;
    }

    // getters
    public Client getCurrentClient() {
        return currentClient;
    }

    public boolean isClientLoggedIn() {
        return currentClient != null;
    }

    // helpers
    private int generateNewClientId(List<Client> clients) {
        int maxId = 0;
        int i = 0;

        while (i < clients.size()) {
            if (clients.get(i).getClientId() > maxId) {
                maxId = clients.get(i).getClientId();
            }
            i++;
        }
        return maxId + 1;
    }

}
