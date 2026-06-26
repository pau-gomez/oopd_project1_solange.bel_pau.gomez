package Buisness.Entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Represents a client with an ID, full name, and associated phone numbers.
 */
public class Client {
    @SerializedName("client_id")
    private final int clientId;

    @SerializedName("client_type")
    private final String clientType;

    @SerializedName("full_name")
    private final String fullName;

    @SerializedName("phone_numbers")
    private final List<PhoneNumber> phoneNumbers;

    /**
     * Constructs a Client.
     *
     * @param clientId the unique client ID
     * @param clientType the type of client
     * @param fullName the client's full name
     * @param phoneNumbers list of the client's phone numbers
     */
    public Client(int clientId, String clientType, String fullName, List<PhoneNumber> phoneNumbers) {
        this.clientId = clientId;
        this.clientType = clientType;
        this.fullName = fullName;
        this.phoneNumbers = phoneNumbers;
    }


    /**
     * @return the client ID
     */
    public int getClientId() {
        return this.clientId;
    }

    /**
     * @return the type of client the client is
     */
    public String getClientType() { return clientType; }

    /**
     * @return the client's full name
     */
    public String getFullName() {
        return this.fullName;
    }

    /**
     * @return list of the client's phone numbers
     */
    public List<PhoneNumber> getPhoneNumbers() {
        return this.phoneNumbers;
    }
}