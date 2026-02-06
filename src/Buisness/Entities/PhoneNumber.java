package Buisness.Entities;

public class PhoneNumber {
    private String country_prefix;
    private String number;

    public PhoneNumber(String country_prefix, String phone_number) {
        this.country_prefix = country_prefix;
        this.number = phone_number;
    }

    public String getInternationalPrefix() { return this.country_prefix; }

    public String getPhoneNumber() { return this.number; }

}
