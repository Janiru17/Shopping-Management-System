public class Clothing extends Product{
    private String size;
    private String color;

    public Clothing(String productID, String productName, int noAvailableItems, double price, String size, String color) {
        super(productID, productName, noAvailableItems, price);
        this.size = size;
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    @Override
    public String getDetails() {
        return "Clothing: " + super.getDetails() + ", Size: " + size + ", Color: " + color;
    }

}
