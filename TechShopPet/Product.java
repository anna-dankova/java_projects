package TechShopPet;

public class Product {
    private String type;
    private String name;
    private double price;
    final private int id;

    public Product(String name, String type, double price, int id) {
        if (name == null || name.strip().isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (price <= 0 || Double.isNaN(price) || Double.isInfinite(price)) {
            throw new IllegalArgumentException("Product price must be a positive number");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("Product ID must be a positive integer");
        }
        this.name = name;
        this.type = type;
        this.price = price;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getId() {
        return this.id;
    }

    public String getType() {
        return type;
    }

    public void applyDiscount(double percentage) {

        if (percentage < 1 || percentage > 99 || Double.isNaN(percentage) || Double.isInfinite(percentage)) {
            throw new IllegalArgumentException("Discount percentage must be between 1 and 99");
        }
        double discountAmount = price * percentage / 100;
        double newPrice = price - discountAmount;

        if (newPrice <= 0) {
            throw new IllegalArgumentException("The resulting price cannot be zero or negative");
        }
        price = newPrice;
    }

    @Override
    public String toString() {
        return "Product : " + getName() + ", | Price : " + getPrice() + ", | Id : " + getId();
    }
}
