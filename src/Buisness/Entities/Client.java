package Buisness.Entities;

import java.util.List;

public class Client {
    private int client_id;
    private String full_name;
    private List<PhoneNumber> phone_numbers;

    //constructor
    public Client(int client_id, String full_name, List<PhoneNumber> phone_numbers) {
        this.client_id = client_id;
        this.full_name = full_name;
        this.phone_numbers = phone_numbers;
    }

    public int getClientId(){ return this.client_id; }

    public String getFullName(){ return this.full_name; }

    public List<PhoneNumber> getPhoneNumbers(){ return this.phone_numbers; }

    public int getId() {
        int a = 0;

        return a;
    }
}