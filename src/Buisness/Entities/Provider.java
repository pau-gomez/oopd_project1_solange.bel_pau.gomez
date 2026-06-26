package Buisness.Entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Represents a provider company and its associated products.
 */
public class Provider {
    @SerializedName("provider_id")
    private final int providerId;

    @SerializedName("company_name")
    private final String companyName;
    private final String cif;

    @SerializedName("contact_name")
    private final String contactName;
    private final String phone;
    private final String email;

    @SerializedName("products_for_sale")
    private final List<ProductForSale> productForSales;

    /**
     * Constructs a Provider object.
     *
     * @param providerId unique provider ID
     * @param companyName name of the company
     * @param cif company tax identifier
     * @param contactName contact person name
     * @param phone contact phone number
     * @param email contact email address
     * @param productForSales list of products offered by the provider
     */
    public Provider(int providerId, String companyName, String cif, String contactName, String phone, String email, List<ProductForSale> productForSales) {
        this.providerId = providerId;
        this.companyName = companyName;
        this.cif = cif;
        this.contactName = contactName;
        this.phone = phone;
        this.email = email;
        this.productForSales = productForSales;
    }


    /**
     * @return company name
     */
    public String getCompanyName() {
        return this.companyName;
    }

    /**
     * @return list of products for sale
     */
    public List<ProductForSale> getProductsForSale() {
        return this.productForSales;
    }
}