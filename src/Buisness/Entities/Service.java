package Buisness.Entities;

/**
 * Represents a service its associated details.
 */
public class Service extends Product {
    private double durationHours;

    public Service(String productId, String productName, double durationHours) {
        super(productId, productName, "service");
        this.durationHours = durationHours;
    }

    public double getDurationHours() { return durationHours; }
}