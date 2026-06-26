package Buisness;

import Buisness.Entities.OnlineClient;
import Persistance.PersistenceException;
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
     * Calculates the shipping cost for an online client based on their distance from La Salle campus.
     * The cost is €2 base fee plus €0.01 per km.
     *
     * @param client the online client whose shipping address is used for the calculation
     * @return the shipping cost in euros
     * @throws PersistenceException if the address cannot be parsed or the geolocation API fails
     */
    public double calculateShippingCost(OnlineClient client) throws PersistenceException {
        try {
            Address address = AddressParser.parse(client.getAddress());

            Geo destination = geolocationApiManager.getGeolocationFromAddress(address);

            Geo origin = new Geo(LA_SALLE_LAT, LA_SALLE_LON);

            double distanceKm = geolocationApiManager.calculateDistance(origin, destination);

            return BASE_FEE + (FEE_PER_KM * distanceKm);

        } catch (IOException | InvalidAddressException e) {
            throw new PersistenceException("Could not calculate shipping cost.", e);
        }
    }
}