package TechShopPet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;

public class InventoryJsonStorage {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public String toJson(StoreInventory inventory) {
        return gson.toJson(inventory.getAllProducts());
    }

    public void saveJson(StoreInventory inventory, String filePath) {
        String json = toJson(inventory);
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(json);
        } catch (IOException e) {
            System.out.println("Cant save this file" + e.getMessage());
        }
    }
}
