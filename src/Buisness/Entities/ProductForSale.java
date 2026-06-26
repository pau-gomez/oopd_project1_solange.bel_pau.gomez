package Buisness.Entities;


/**
 * Represents a product available for sale, including price and stock.
 */
public class ProductForSale {
    private String productId;
    private double sellingPrice;
    private int unitsInStock;


    /**
     * Constructs a ProductForSale object.
     *
     * @param productId the product identifier
     * @param sellingPrice the selling price of the product
     * @param unitsInStock available stock units
     */
    public ProductForSale(String productId, double sellingPrice, int unitsInStock) {
        this.productId = productId;
        this.sellingPrice = sellingPrice;
        this.unitsInStock = unitsInStock;
    }


    /**
     * @return the selling price
     */
    public double getSalePrice() {
        return this.sellingPrice;
    }

    /**
     * @return units in stock
     */
    public int getUnitsInStock() {
        return this.unitsInStock;
    }

    /**
     * @return product ID
     */
    public String getProductId() {
        return this.productId;
    }

    /**
     * Updates the stock quantity.
     *
     * @param newStock the new stock value
     */
    public void setUnitsInStock(int newStock) {
        this.unitsInStock = newStock;
    }
}