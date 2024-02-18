public class Electronics extends Product {
    private String brand;
    private double warrantyPeriod;

    public Electronics(String productID, String productName, int noAvailableItems, double price, String brand, double warrantyPeriod) {
        super(productID, productName, noAvailableItems, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(double warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
    @Override
    public String getDetails() {
        return "Electronics: " + super.getDetails() + ", Warranty: " + warrantyPeriod + " months, Brand: " + brand;
    }

}
