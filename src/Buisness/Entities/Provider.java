package Buisness.Entities;

import java.util.List;

public class Provider {
    private int provider_id;
    private String company_name;
    private String cif;
    private String contact_name;
    private PhoneNumber phone;
    private String email;
    private List<ProductForSale> products_for_sale;

    public Provider(int provider_id, String company_name, String cif, String contact_name, PhoneNumber phone, String email, List<ProductForSale> products_for_sale) {
        this.provider_id = provider_id;
        this.company_name = company_name;
        this.cif = cif;
        this.contact_name = contact_name;
        this.phone = phone;
        this.email = email;
        this.products_for_sale = products_for_sale;
    }

    public int getProviderId() {
        return this.provider_id;
    }

    public String getCompanyName() {
        return this.company_name;
    }

    public String getCif() {
        return this.cif;
    }

    public String getContactPersonName() {
        return this.contact_name;
    }

    public PhoneNumber getPhoneNumber() {
        return this.phone;
    }

    public String getEmail() {
        return this.email;
    }

    public List<ProductForSale> getProductsForSale() {
        return this.products_for_sale;
    }
}
