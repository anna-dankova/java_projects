package TechShopPet;

public class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (price <=0 || Double.isNaN(price) || Double.isInfinite(price)) {
            throw new IllegalArgumentException("Product price must be a positive number");
        }
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public void applyDiscount(double percentage) {

        if (percentage < 0 || percentage > 100|| Double.isNaN(percentage) || Double.isInfinite(percentage)) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100");
        }
        double discountAmount = price * percentage / 100;
        price -= discountAmount;
    }

    @Override
    public String toString() {
        return "Product : " + getName() + ", | Price : " + getPrice();
    }
}
