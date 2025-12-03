package Buisness.Entities;

public class PhoneNumber {
    private String internationalPrefix;
    private String phoneNumber;

    public PhoneNumber(String internationalPrefix, String phoneNumber) {
        this.internationalPrefix = internationalPrefix;
        this.phoneNumber = phoneNumber;
    }

    public String getInternationalPrefix() { return this.internationalPrefix; }

    public String getPhoneNumber() { return this.phoneNumber; }

}
