package fileio;

public class GiftsInputData {
    private String productName;
    private double price;
    private String category;

    public GiftsInputData(final String productName, final double price, final String category) {
        this.productName = productName;
        this.price = price;
        this.category = category;
    }

    /**
     * getter for productname
     */
    public String getProductName() {
        return productName;
    }

    /**
     * setter for product name
     */
    public void setProductName(final String productName) {
        this.productName = productName;
    }

    /**
     * getter for price
     */
    public double getPrice() {
        return price;
    }

    /**
     * setter for price
     */
    public void setPrice(final double price) {
        this.price = price;
    }

    /**
     * getter for category
     */
    public String getCategory() {
        return category;
    }

    /**
     * setter for category
     */
    public void setCategory(final String category) {
        this.category = category;
    }
}
