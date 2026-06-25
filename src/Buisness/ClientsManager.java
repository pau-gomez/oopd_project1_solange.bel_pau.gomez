package Buisness;

import Buisness.Entities.Client;
import Buisness.Entities.OnlineClient;
import Buisness.Entities.CorporateClient;
import Buisness.Entities.PhoneNumber;
import Persistance.ClientsDao;

import java.util.List;

/**
 * Handles client authentication and registration logic.
 */
public class ClientsManager {
    private Client currentClient;
    private final ClientsDao clientsDao;

    /**
     * Initializes the manager with whichever DAO is given, either API or JSON.
     */
    public ClientsManager(ClientsDao clientsDao) {
        this.clientsDao = clientsDao;
        this.currentClient = null;
    }

    /**
     * Logs in a client by ID.
     *
     * @param clientId the client ID
     * @return true if login is successful, false otherwise
     */
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

    /**
     * Registers a new client and sets them as the current client.
     *
     * @param fullName client's full name
     * @param phoneNumbers list of phone numbers
     * @return true if registration is successful
     */
    public boolean registerClient(String fullName, List<PhoneNumber> phoneNumbers) {
        List<Client> clients = clientsDao.loadAllClients();

        int newClientId = generateNewClientId(clients);
        Client newClient = new Client(newClientId, "regular", fullName, phoneNumbers);

        clients.add(newClient);
        clientsDao.updateFile(clients);

        currentClient = newClient;

        if (newClient.getPhoneNumbers() == null) return false;

        return true;
    }

    /**
     * Registers a new online client.
     *
     * @param fullName     client's full name
     * @param phoneNumbers list of phone numbers
     * @param address      shipping address
     * @param contactEmail contact email
     * @return true if registration is successful
     */
    public boolean registerOnlineClient(String fullName, List<PhoneNumber> phoneNumbers,
                                        String address, String contactEmail) {
        List<Client> clients = clientsDao.loadAllClients();
        int newClientId = generateNewClientId(clients);

        OnlineClient newClient = new OnlineClient(newClientId, fullName, phoneNumbers,
                address, contactEmail);
        clients.add(newClient);
        clientsDao.updateFile(clients);
        currentClient = newClient;

        return true;
    }

    /**
     * Registers a new corporate client.
     *
     * @param contactName    name of the contact person
     * @param phoneNumbers   list of phone numbers
     * @param cif            tax identification number
     * @param billingAddress billing address
     * @param mailingAddress shipping/mailing address
     * @return true if registration is successful
     */
    public boolean registerCorporateClient(String contactName, List<PhoneNumber> phoneNumbers,
                                           String cif, String billingAddress, String mailingAddress) {
        List<Client> clients = clientsDao.loadAllClients();
        int newClientId = generateNewClientId(clients);

        CorporateClient newClient = new CorporateClient(newClientId, contactName, phoneNumbers,
                cif, contactName, billingAddress, mailingAddress);
        clients.add(newClient);
        clientsDao.updateFile(clients);
        currentClient = newClient;

        return true;
    }

    /**
     * Logs out the current client.
     */
    public void logout() {
        currentClient = null;
    }

    /**
     * Gets current client object.
     *
     * @return the currently logged-in client
     */
    public Client getCurrentClient() {
        return currentClient;
    }

    /**
     * Generates a new unique client ID.
     *
     * @param clients list of existing clients
     * @return new unique client ID
     */
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