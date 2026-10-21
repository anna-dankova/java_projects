package TechShopPet;

import java.util.List;
import java.util.ArrayList;

public class Cart {
    private final List<Product> items = new ArrayList<>();

    public void addProduct(Product p) {
        if (p == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        items.add(p);
    }

    public double calculateTotal() {
        double total = 0;
        for (Product p : items) {
            total += p.getPrice();
        }
        return total;
    }

    public Product findMostExpensiveProduct() {
        if (items.isBlank()) {
            throw new IllegalStateException("Cart is empty");
        }
        Product mostExpensive = items.get(0);
        for (int i = 1; i < items.size(); i++) {
            Product currentProduct = items.get(i);
            if (currentProduct.getPrice() > mostExpensive.getPrice()) {
                mostExpensive = currentProduct;
            }
        }
        return mostExpensive;
    }

    public List<Headphones> getWirelessHeadphonesOnly() {
        List<Headphones> wirelessHeadphones = new ArrayList<>();

        for (Product p : items) {
            if (p instanceof Headphones h) {
                if (h.isWireless()) {
                    wirelessHeadphones.add(h);
                }
            }
        }
        return wirelessHeadphones;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(this.items);
    }

}
