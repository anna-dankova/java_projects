package TechShopPet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;

public class InventoryJsonStorage {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

    public String toJson(StoreInventory inventory) {
        JsonArray jsonArray = new JsonArray();
        for (Product product : inventory.getAllProducts()) {
            JsonObject productObject = productToJson(product);
            jsonArray.add(productObject);
        }
        return gson.toJson(jsonArray);
    }

    private JsonObject productToJson(Product product) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", product.getId());
        jsonObject.addProperty("type", product.getType());
        jsonObject.addProperty("name", product.getName());
        jsonObject.addProperty("price", product.getPrice());

        if (product instanceof Laptop) {
            jsonObject.addProperty("ram", ((Laptop) product).getRam());
        } else if (product instanceof Phone) {
            jsonObject.addProperty("cameraCount", ((Phone) product).getCameraCount());
        } else if (product instanceof Headphones) {
            jsonObject.addProperty("isWireless", ((Headphones) product).isWireless());
        } else {
            System.out.println("This product type is not supported");
        }
        return jsonObject;
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
