package Buisness.Entities;

import java.util.List;

public class CorporateClient extends Client {
    private String cif;               // NIF/tax identification
    private String contact_name;      // contact person name
    private String billing_address;
    private String mailing_address;   // shipping address

    public CorporateClient(int client_id, String full_name, List<PhoneNumber> phone_numbers,
                           String cif, String contact_name,
                           String billing_address, String mailing_address) {
        super(client_id, "corporate", full_name, phone_numbers);
        this.cif = cif;
        this.contact_name = contact_name;
        this.billing_address = billing_address;
        this.mailing_address = mailing_address;
    }

    public String getCif() { return cif; }
    public String getContactName() { return contact_name; }
    public String getBillingAddress() { return billing_address; }
    public String getMailingAddress() { return mailing_address; }
}