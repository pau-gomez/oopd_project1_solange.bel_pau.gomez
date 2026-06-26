package Buisness.Entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Represents a corporate client with tax identification, contact person, and address details.
 */
public class CorporateClient extends Client {
    private final String cif;

    @SerializedName("contact_name")
    private final String contactName;

    @SerializedName("billing_address")
    private final String billingAddress;

    @SerializedName("mailing_address")
    private final String mailingAddress;

    /**
     * Constructs a CorporateClient.
     *
     * @param clientId the unique client ID
     * @param fullName the contact person's full name
     * @param phoneNumbers list of phone numbers
     * @param cif the company's tax identification number
     * @param contactName the name of the contact person
     * @param billingAddress the company's billing address
     * @param mailingAddress the company's mailing/shipping address
     */
    public CorporateClient(int clientId, String fullName, List<PhoneNumber> phoneNumbers,
                           String cif, String contactName,
                           String billingAddress, String mailingAddress) {
        super(clientId, "corporate", fullName, phoneNumbers);
        this.cif = cif;
        this.contactName = contactName;
        this.billingAddress = billingAddress;
        this.mailingAddress = mailingAddress;
    }

    /**
     *
     * @return the CIF
     */
    public String getCif() { return cif; }

    /**
     *
     * @return the contact name
     */
    public String getContactName() { return contactName; }

    /**
     *
     * @return the billing address
     */
    public String getBillingAddress() { return billingAddress; }

    /**
     *
     * @return the mailing address
     */
    public String getMailingAddress() { return mailingAddress; }
}