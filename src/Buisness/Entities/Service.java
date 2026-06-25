package Buisness.Entities;

/**
 * Represents a service its associated details.
 */
public class Service extends Product {
    private double duration_hours;

    public Service(String product_id, String product_name, double duration_hours) {
        super(product_id, product_name, "service");
        this.duration_hours = duration_hours;
    }

    public double getDurationHours() { return duration_hours; }
}