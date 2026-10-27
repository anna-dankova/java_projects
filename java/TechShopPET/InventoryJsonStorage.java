package TechShopPet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;

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

    private Product productFromJson(JsonObject jsonObject) {
        Product product = null;
        if (!jsonObject.has("id") || !jsonObject.has("type")) {
            throw new IllegalArgumentException("Something wrong with your file");
        }
        int id = jsonObject.get("id").getAsInt();
        String type = jsonObject.get("type").getAsString();
        String name = jsonObject.get("name").getAsString();
        double price = jsonObject.get("price").getAsDouble();

        switch (type.toLowerCase()) {
            case "laptop": {
                int ram = 0;
                if (jsonObject.has("ram") && jsonObject.get("ram").isJsonPrimitive()) {
                    ram = jsonObject.get("ram").getAsInt();
                }
                product = new Laptop(name, price, id, ram);
                break;
            }

            case "phone": {
                int countCamers = 0;
                if (jsonObject.has("countCameras") && jsonObject.get("countCameras").isJsonPrimitive()) {
                    countCamers = jsonObject.get("countCameras").getAsInt();
                }
                product = new Laptop(name, price, id, countCamers);
                break;
            }
            case "headphones": {
                boolean isWireless = false;
                if (jsonObject.has("isWireless") && jsonObject.get("isWireless").isJsonPrimitive()) {
                    isWireless = jsonObject.get("isWireless").getAsBoolean();
                }
                product = new Headphones(name, price, id, isWireless);
                break;
            }
            default:
                System.out.println("Unknown type :" + type);
                return null;
        }
        return product;
    }

    public StoreInventory loadJson(String filePath) {
        StoreInventory inventory = new StoreInventory();
    }
}
