package Buisness.Entities;

import java.util.List;

public class CorporateClient extends Client {
    private String cif;
    private String contactName;
    private String billingAddress;
    private String mailingAddress;

    public CorporateClient(int clientId, String fullName, List<PhoneNumber> phoneNumbers,
                           String cif, String contactName,
                           String billingAddress, String mailingAddress) {
        super(clientId, "corporate", fullName, phoneNumbers);
        this.cif = cif;
        this.contactName = contactName;
        this.billingAddress = billingAddress;
        this.mailingAddress = mailingAddress;
    }

    public String getCif() { return cif; }
    public String getContactName() { return contactName; }
    public String getBillingAddress() { return billingAddress; }
    public String getMailingAddress() { return mailingAddress; }
}