package Buisness.Entities;


/**
 * Represents a product available for sale, including price and stock.
 */
public class ProductForSale {
    private String product_id;
    private double selling_price;
    private int units_in_stock;


    /**
     * Constructs a ProductForSale object.
     *
     * @param product_id the product identifier
     * @param selling_price the selling price of the product
     * @param units_in_stock available stock units
     */
    public ProductForSale(String product_id, double selling_price, int units_in_stock) {
        this.product_id = product_id;
        this.selling_price = selling_price;
        this.units_in_stock = units_in_stock;
    }

        // Getters: //

    /**
     * @return the selling price
     */
    public double getSalePrice() {
        return this.selling_price;
    }

    /**
     * @return units in stock
     */
    public int getUnitsInStock() {
        return this.units_in_stock;
    }

    /**
     * @return product ID
     */
    public String getProductId() {
        return this.product_id;
    }

    /**
     * Updates the stock quantity.
     *
     * @param newStock the new stock value
     */
    public void setUnitsInStock(int newStock) {
        this.units_in_stock = newStock;
    }
}