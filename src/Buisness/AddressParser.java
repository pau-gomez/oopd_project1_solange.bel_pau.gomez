package Buisness;

import geolocationAPI.Address;

/**
 * Parses an address strings into structured Address objects for the geolocation API to use.
 */
public class AddressParser {

    /**
     * Parses an address string into an Address object to be used by the geolocation API.
     *
     * @param addressString the complete address string
     * @return parsed Address object
     */
    public static Address parse(String addressString) {
        try {
            String[] parts = addressString.split(",");
            String streetPart = parts[0].trim();
            String cityPart = parts[1].trim();
            String country     = parts[2].trim();

            int lastSpace = streetPart.lastIndexOf(" ");
            String street = streetPart.substring(0, lastSpace).trim();
            int number = Integer.parseInt(streetPart.substring(lastSpace + 1).trim());

            int firstSpace = cityPart.indexOf(" ");
            String postalCode = cityPart.substring(0, firstSpace).trim();
            String city = cityPart.substring(firstSpace + 1).trim();

            return new Address(street, number, city, country, postalCode);
        } catch (Exception e) {
            throw new RuntimeException("Could not parse address: " + addressString, e);
        }   //  TODO: try catch ok?
    }
}
