package TechShopPet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class InventoryJsonStorage {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    /**
     * Converts a StoreInventory object into JSON format.
     *
     * @param inventory The StoreInventory object containing all products to be serialized
     * @return A JSON string representation of the entire inventory as a JSON array
     */
    public String toJson(StoreInventory inventory) {
        JsonArray jsonArray = new JsonArray();
        for (Product product : inventory.getAllProducts()) {
            JsonObject productObject = productToJson(product);
            jsonArray.add(productObject);
        }
        return gson.toJson(jsonArray);
    }
    /**
     * Converts the entire store inventory into a single JSON formatted string.
     * It loops through all products and builds a JSON array.
     *
     * @param product The inventory object containing the list of products.
     * @return A formatted JSON string ready to be saved.
     */
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
    /**
     * Saves the current inventory to a JSON file.
     *
     * @param inventory The StoreInventory object to be saved
     * @param filePath The name of the file where inventory will be saved

     */
    public void saveJson(StoreInventory inventory, String filePath)throws IOException {
        String json = toJson(inventory);
        String fileName = filePath + ".json";
        try (FileWriter writer = new FileWriter(filePath, StandardCharsets.UTF_8)) {
            writer.write(json);
        }


    }
    /**
     * Converts a JSON element into a Product object.
     *
     * @param jsonObject The JSON object containing product data
     * @return A Product object populated with data from the JSON element
     */
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
                int cameraCount = 0;
                if (jsonObject.has("cameraCount") && jsonObject.get("cameraCount").isJsonPrimitive()) {
                    cameraCount = jsonObject.get("cameraCount").getAsInt();
                }
                product = new Phone(name, price, id, cameraCount);
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
        if(filePath == null) {
            throw new IllegalArgumentException("File path is empty");
        }
        try (FileReader reader = new FileReader(filePath)) {
            JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);
            if(jsonElement == null || !jsonElement.isJsonArray()) { // Its Array
                throw new IllegalArgumentException("File is not a json array");
            }
            StoreInventory temporaryStore = new StoreInventory();
            JsonArray jsonArray = jsonElement.getAsJsonArray();  // мы сделали из этой штуки gson, читаемый список
            for(JsonElement element : jsonArray) { // get JsonElement
                element.isJsonObject();// products
                if(! temporaryStore.addProduct(productFromJson(element.getAsJsonObject()))){ // проверка на одинаковый id в addProduct --> t/f
                    throw new IllegalArgumentException("Duplicate product id " + element.getAsJsonObject().get("id"));
                }}
            return temporaryStore;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


}
}
