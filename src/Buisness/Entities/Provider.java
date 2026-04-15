package Buisness.Entities;

import java.util.List;

/**
 * Represents a provider company and its associated products.
 */
public class Provider {
    private int provider_id;
    private String company_name;
    private String cif;
    private String contact_name;
    private String phone;
    private String email;
    private List<ProductForSale> products_for_sale;

    /**
     * Constructs a Provider object.
     *
     * @param provider_id unique provider ID
     * @param company_name name of the company
     * @param cif company tax identifier
     * @param contact_name contact person name
     * @param phone contact phone number
     * @param email contact email address
     * @param products_for_sale list of products offered by the provider
     */
    public Provider(int provider_id, String company_name, String cif, String contact_name, String phone, String email, List<ProductForSale> products_for_sale) {
        this.provider_id = provider_id;
        this.company_name = company_name;
        this.cif = cif;
        this.contact_name = contact_name;
        this.phone = phone;
        this.email = email;
        this.products_for_sale = products_for_sale;
    }

        // Getters: //

    /**
     * @return company name
     */
    public String getCompanyName() {
        return this.company_name;
    }

    /**
     * @return list of products for sale
     */
    public List<ProductForSale> getProductsForSale() {
        return this.products_for_sale;
    }
}