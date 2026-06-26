package Buisness.Entities;

import java.util.List;

public class OnlineClient extends Client {
    private String address;
    private String contactEmail;

    public OnlineClient(int clientId, String fullName, List<PhoneNumber> phoneNumbers,
                        String address, String contactEmail) {
        super(clientId, "online", fullName, phoneNumbers);
        this.address = address;
        this.contactEmail = contactEmail;
    }

    public String getAddress() { return address; }
    public String getContactEmail() { return contactEmail; }
}