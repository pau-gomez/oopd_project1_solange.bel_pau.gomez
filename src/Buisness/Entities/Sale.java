package Buisness.Entities;

/**
 * Represents a sale transaction between a client and a product.
 */
public class Sale {
    public int client_id;
    public String product_id;
    public double price_paid;
    public long purchase_date;

    public Sale() {}

    /**
     * Constructs a Sale object.
     *
     * @param client_id the client identifier
     * @param product_id the product identifier
     * @param price_paid the price paid for the product
     * @param purchase_date timestamp of the purchase
     */
    public Sale(int client_id, String product_id, double price_paid, long purchase_date) {
        this.client_id = client_id;
        this.product_id = product_id;
        this.price_paid = price_paid;
        this.purchase_date = purchase_date;
    }

        // Getters: //

    /**
     * @return client ID
     */
    public int getClientId() {
        return this.client_id;
    }

    /**
     * @return product ID
     */
    public String getProductId() {
        return this.product_id;
    }

    /**
     * @return price paid
     */
    public double getPaidPrice() {
        return this.price_paid;
    }

    /**
     * @return purchase timestamp
     */
    public long getPurchaseDate() {
        return this.purchase_date;
    }
}