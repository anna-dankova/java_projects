package TechShopPet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class StoreInventory {
    private Map<Integer, Product> productsByID = new HashMap<>();

   public boolean addProduct(Product product) {
    if (product == null) {
        throw new IllegalArgumentException("Product cannot be null");
    }
    if (productsByID.containsKey(product.getID())) {
        return false;
    }
        productsByID.put(product.getID(), product);
        return true;
    }


    public Product getProductByID(int id) {
        return productsByID.get(id) ;    
    }

    public boolean hasProduct(int id) {
        return productsByID.containsKey(id);
    }

    public boolean removeProduct(int id) {
        if (productsByID.containsKey(id)) {
            productsByID.remove(id);
            return true;
        }
        return false;
    }

    public int countInventoryPhone(){
        int phoneCount = 0;
        for (Product p : productsByID.values()) {
            if (p instanceof Phone) {
                phoneCount++;
            }
        }
        return phoneCount;
    }

    public int countInventoryLaptop(){
        int laptopCount = 0;
        for (Product p : productsByID.values()) {
            if (p instanceof Laptop) {
                laptopCount++;
            }
        }
        return laptopCount;
    }
    public int countInventoryHeadphones(){
        int headphonesCount = 0;
        for (Product p : productsByID.values()) {
            if (p instanceof Headphones) {
                headphonesCount++;
            }
        }
        return headphonesCount;
    }

    public int getProductCount() {
        return productsByID.size();
    }

    public List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<Product>(productsByID.values());
        return allProducts;
    }


}