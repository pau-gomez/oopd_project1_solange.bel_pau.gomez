package Buisness.Entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Represents an online client with a shipping address and contact email.
 */
public class OnlineClient extends Client {
    private final String address;

    @SerializedName("contact_email")
    private final String contactEmail;

    /**
     * Constructs an OnlineClient.
     *
     * @param clientId the unique client ID
     * @param fullName the client's full name
     * @param phoneNumbers list of phone numbers
     * @param address the client's shipping address
     * @param contactEmail the client's contact email
     */
    public OnlineClient(int clientId, String fullName, List<PhoneNumber> phoneNumbers,
                        String address, String contactEmail) {
        super(clientId, "online", fullName, phoneNumbers);
        this.address = address;
        this.contactEmail = contactEmail;
    }

    /**
     * Returns the shipping address of the online client.
     *
     * @return the shipping address
     */
    public String getAddress() { return address; }

    /**
     * Returns the contact email of the online client.
     *
     * @return the contact email
     */
    public String getContactEmail() { return contactEmail; }
}