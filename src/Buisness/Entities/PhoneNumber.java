package Buisness.Entities;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a phone number with a country prefix and number.
 */
public class PhoneNumber {
    @SerializedName("country_prefix")
    private final String countryPrefix;
    private final String number;

    /**
     * Constructs a PhoneNumber object.
     *
     * @param country_prefix the international country prefix
     * @param phone_number the local phone number
     */
    public PhoneNumber(String country_prefix, String phone_number) {
        this.countryPrefix = country_prefix;
        this.number = phone_number;
    }

    /**
     * @return the country prefix
     */
    public String getInternationalPrefix() {
        return this.countryPrefix;
    }

    /**
     * @return the phone number
     */
    public String getPhoneNumber() {
        return this.number;
    }

}