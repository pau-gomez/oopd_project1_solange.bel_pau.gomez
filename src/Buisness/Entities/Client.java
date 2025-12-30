package Buisness.Entities;

import java.util.List;

public class Client {
    private int clientId;
    private String fullName;
    private List<PhoneNumber> phoneNumbers;

    //constructor
    public Client(int clientId, String fullName, List<PhoneNumber> phoneNumbers) {
        this.clientId = clientId;
        this.fullName = fullName;
        this.phoneNumbers = phoneNumbers;
    }

    public int getClientId(){ return this.clientId; }

    public String getFullName(){ return this.fullName; }

    public List<PhoneNumber> getPhoneNumbers(){ return this.phoneNumbers; }

    public int getId() {
        int a = 0;

        return a;
    }
}