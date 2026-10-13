package TechShopPet;

import java.util.HashMap;

public class StoreInventory {
    private HashMap<String, Product> productsByName = new HashMap<>();

    public void addProduct(Product product) {
        productsByName.put(product.getName(), product);
    }

    public Product getProduct(String name) {
        return productsByName.get(name);

    }

    public boolean hasProduct(String name) {
        return productsByName.containsKey(name);
        
    }

    public boolean removeProduct(String name) {
        if (productsByName.containsKey(name)) {
            productsByName.remove(name);
            return true;
        }
        return false;
    }

    public int countInventoryPhone(){
        int phoneCount = 0;
        for (Product p : productsByName.values()) {
            if (p instanceof Phone) {
                phoneCount++;
            }
        }
        return phoneCount;
    }

    public int countInventoryLaptop(){
        int laptopCount = 0;
        for (Product p : productsByName.values()) {
            if (p instanceof Laptop) {
                laptopCount++;
            }
        }
        return laptopCount;
    }
    public int countInventoryHeadphones(){
        int headphonesCount = 0;
        for (Product p : productsByName.values()) {
            if (p instanceof Headphones) {
                headphonesCount++;
            }
        }
        return headphonesCount;
    }

    public int getProductCount() {
        return productsByName.size();
    }

    public void printAllProducts() {
        for (Product p : productsByName.values()) {
            System.out.println(p.toString());
        }

    }
}
