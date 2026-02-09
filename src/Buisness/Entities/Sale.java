package Buisness.Entities;

public class Sale {
    public int client_id;
    public String product_id;
    public double price_paid;
    public long purchase_date;

    public Sale() {
    }

    public Sale(int client_id, String product_id, double price_paid, long purchase_date) {
        this.client_id = client_id;
        this.product_id = product_id;
        this.price_paid = price_paid;
        this.purchase_date = purchase_date;
    }

    public int getClientId() {
        return this.client_id;
    }

    public String getProductId() {
        return this.product_id;
    }

    public double getPaidPrice() {
        return this.price_paid;
    }

    public long getPurchaseDate() {
        return this.purchase_date;
    }


}
