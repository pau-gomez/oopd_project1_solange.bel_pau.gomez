package Buisness.Entities;

import java.util.List;

/**
 * Represents a client with an ID, full name, and associated phone numbers.
 */
public class Client {
    private int client_id;
    private String full_name;
    private List<PhoneNumber> phone_numbers;

    /**
     * Constructs a Client.
     *
     * @param client_id the unique client ID
     * @param full_name the client's full name
     * @param phone_numbers list of the client's phone numbers
     */
    public Client(int client_id, String full_name, List<PhoneNumber> phone_numbers) {
        this.client_id = client_id;
        this.full_name = full_name;
        this.phone_numbers = phone_numbers;
    }

        // Getters: //

    /**
     * @return the client ID
     */
    public int getClientId() {
        return this.client_id;
    }

    /**
     * @return the client's full name
     */
    public String getFullName() {
        return this.full_name;
    }

    /**
     * @return list of the client's phone numbers
     */
    public List<PhoneNumber> getPhoneNumbers() {
        return this.phone_numbers;
    }
}