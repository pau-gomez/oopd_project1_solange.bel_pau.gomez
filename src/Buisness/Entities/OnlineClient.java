package Buisness.Entities;

import java.util.List;

public class OnlineClient extends Client {
    private String address;        // shipping address
    private String contact_email;

    public OnlineClient(int client_id, String full_name, List<PhoneNumber> phone_numbers,
                        String address, String contact_email) {
        super(client_id, "online", full_name, phone_numbers);
        this.address = address;
        this.contact_email = contact_email;
    }

    public String getAddress() { return address; }
    public String getContactEmail() { return contact_email; }
}