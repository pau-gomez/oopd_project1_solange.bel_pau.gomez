package Buisness.Entities;

import java.util.List;

public class Provider {
    private int providerId;
    private String companyName;
    private String cif;
    private String contactPersonName;
    private PhoneNumber phoneNumber;
    private String email;
    private List<ProductForSale> productsForSale;

    public Provider(int providerId, String companyName, String cif, String contactPersonName, PhoneNumber phoneNumber, String email, List<ProductForSale> productsForSale) {
        this.providerId = providerId;
        this.companyName = companyName;
        this.cif = cif;
        this.contactPersonName = contactPersonName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.productsForSale = productsForSale;
    }

    public int getProviderId() {
        return this.providerId;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public String getCif() {
        return this.cif;
    }

    public String getContactPersonName() {
        return this.contactPersonName;
    }

    public PhoneNumber getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public List<ProductForSale> getProductsForSale() {
        return this.productsForSale;
    }
}
