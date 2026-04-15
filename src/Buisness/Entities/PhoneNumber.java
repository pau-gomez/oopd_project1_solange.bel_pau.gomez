package Buisness.Entities;

/**
 * Represents a phone number with a country prefix and number.
 */
public class PhoneNumber {
    private String country_prefix;
    private String number;

    /**
     * Constructs a PhoneNumber object.
     *
     * @param country_prefix the international country prefix
     * @param phone_number the local phone number
     */
    public PhoneNumber(String country_prefix, String phone_number) {
        this.country_prefix = country_prefix;
        this.number = phone_number;
    }

        // Getters: //

    /**
     * @return the country prefix
     */
    public String getInternationalPrefix() {
        return this.country_prefix;
    }

    /**
     * @return the phone number
     */
    public String getPhoneNumber() {
        return this.number;
    }

}