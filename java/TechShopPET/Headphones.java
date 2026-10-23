package TechShopPet;

public class Headphones extends Product {
    protected boolean isWireless;

    public Headphones(String name, double price, int ID, boolean isWireless) {
        super(name, "Headphones", price, ID);
        this.isWireless = isWireless;
    }

    public boolean isWireless() {
        return isWireless;
    }

    @Override
    public String toString() {
        return super.toString() + ", | Wireless : " + isWireless();
    }
}
