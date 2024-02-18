public abstract class Product {
    private String productID;
    private String productName;
    private int noAvailableItems;
    private double price;
    private String category;

    public Product(String productID, String productName, int noAvailableItems, double price) {
        this.productID = productID;
        this.productName = productName;
        this.noAvailableItems = noAvailableItems;
        this.price = price;
        this.category = category;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getNoAvailableItems() {
        return noAvailableItems;
    }

    public void setNoAvailableItems(int noAvailableItems) {
        this.noAvailableItems = noAvailableItems;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetails() {
        return "Product ID: " + productID + ", Product Name: " + productName + ", Price: " + price;
    }

    public Object getProductId() {
        return productID;
    }
}
