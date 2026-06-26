package Buisness.Entities;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a service with its associated details.
 */
public class Service extends Product {
    @SerializedName("duration_hours")
    private final double durationHours;

    /**
     * Constructs a Service product.
     *
     * @param productId the unique product identifier
     * @param productName the name of the service
     * @param durationHours the duration of the service in hours
     */
    public Service(String productId, String productName, double durationHours) {
        super(productId, productName, "service");
        this.durationHours = durationHours;
    }

    /**
     *
     * @return the duration in hours
     */
    public double getDurationHours() { return durationHours; }
}