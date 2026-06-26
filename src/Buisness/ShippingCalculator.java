package Buisness;

import Buisness.Entities.OnlineClient;
import geolocationAPI.Address;
import geolocationAPI.Geo;
import geolocationAPI.GeolocationApiManager;
import geolocationAPI.InvalidAddressException;

import java.io.IOException;

/**
 * Calculates the shipping cost for online clients.
 */
public class ShippingCalculator {

    private static final double LA_SALLE_LAT = 41.408879;
    private static final double LA_SALLE_LON = 2.130103;
    private static final double BASE_FEE = 2.0;
    private static final double FEE_PER_KM = 0.01;

    private final GeolocationApiManager geolocationApiManager = new GeolocationApiManager();

    /**
     * Calculates the shipping cost for online clients based on the client's distance from La Salle campus.
     *
     * @param client the online client
     * @return shipping cost
     */
    public double calculateShippingCost(OnlineClient client) {
        try {
            Address address = AddressParser.parse(client.getAddress());

            Geo destination = geolocationApiManager.getGeolocationFromAddress(address);

            Geo origin = new Geo(LA_SALLE_LAT, LA_SALLE_LON);

            double distanceKm = geolocationApiManager.calculateDistance(origin, destination);

            return BASE_FEE + (FEE_PER_KM * distanceKm);

        } catch (IOException | InvalidAddressException e) {
            throw new RuntimeException("Could not calculate shipping cost.", e);
        }
    }
}