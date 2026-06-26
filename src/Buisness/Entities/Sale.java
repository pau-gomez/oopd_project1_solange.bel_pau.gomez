package Buisness.Entities;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a sale transaction between a client and a product.
 */
public class Sale {
    @SerializedName("client_id")
    private final int clientId;

    @SerializedName("product_id")
    private final String productId;

    @SerializedName("price_paid")
    private final double pricePaid;

    @SerializedName("purchase_date")
    private final long purchaseDate;

    /**
     * Constructs a Sale object.
     *
     * @param clientId the client identifier
     * @param productId the product identifier
     * @param pricePaid the price paid for the product
     * @param purchaseDate timestamp of the purchase
     */
    public Sale(int clientId, String productId, double pricePaid, long purchaseDate) {
        this.clientId = clientId;
        this.productId = productId;
        this.pricePaid = pricePaid;
        this.purchaseDate = purchaseDate;
    }

    /**
     * @return client ID
     */
    public int getClientId() {
        return this.clientId;
    }

    /**
     * @return product ID
     */
    public String getProductId() {
        return this.productId;
    }

    /**
     * @return price paid
     */
    public double getPaidPrice() {
        return this.pricePaid;
    }

    /**
     * @return purchase timestamp
     */
    public long getPurchaseDate() {
        return this.purchaseDate;
    }
}