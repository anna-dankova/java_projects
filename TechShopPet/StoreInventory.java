package TechShopPet;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class StoreInventory {
    private Map<Integer, Product> productsById = new HashMap<>();

    public boolean addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (productsById.containsKey(product.getId())) {
            return false;
        }
        productsById.put(product.getId(), product);
        return true;
    }

    public Product getProductById(int id) {
        return productsById.get(id);
    }

    public boolean hasProduct(int id) {
        return productsById.containsKey(id);
    }

    public boolean removeProduct(int id) {
        if (productsById.containsKey(id)) {
            productsById.remove(id);
            return true;
        }
        return false;
    }

    public int countInventoryPhone() {
        int phoneCount = 0;
        for (Product p : productsById.values()) {
            if (p instanceof Phone) {
                phoneCount++;
            }
        }
        return phoneCount;
    }

    public int countInventoryLaptop() {
        int laptopCount = 0;
        for (Product p : productsById.values()) {
            if (p instanceof Laptop) {
                laptopCount++;
            }
        }
        return laptopCount;
    }

    public int countInventoryHeadphones() {
        int headphonesCount = 0;
        for (Product p : productsById.values()) {
            if (p instanceof Headphones) {
                headphonesCount++;
            }
        }
        return headphonesCount;
    }

    public int getProductCount() {
        return productsById.size();
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(productsById.values());
    }

}