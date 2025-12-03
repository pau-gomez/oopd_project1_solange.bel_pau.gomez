package Buisness.Entities;

public class Sale {
    private int clientId;
    private String productId;
    private double paidPrice;
    private long purchaseDate;

    public Sale(int clientId, String productId, double paidPrice, long purchaseDate) {
        this.clientId = clientId;
        this.productId = productId;
        this.paidPrice = paidPrice;
        this.purchaseDate = purchaseDate;
    }

    public int getClientId() {
        return this.clientId;
    }

    public String getProductId() {
        return this.productId;
    }

    public double getPaidPrice() {
        return this.paidPrice;
    }

    public long getPurchaseDate() {
        return this.purchaseDate;
    }
}
