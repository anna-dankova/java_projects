package TechShopPet;


public class Main {
    public static void main(String[] args) {
        StoreInventory inventory = new StoreInventory();
        inventory.addProduct(new Phone("iPhone 15", 1000.0,101, 2));
        inventory.addProduct(new Phone("Samsung Galaxy S24", 900.0,102, 2));
        inventory.addProduct(new Phone("Google Pixel 8", 700.0,103, 2));
        inventory.addProduct(new Phone("Xiaomi 14 Pro", 600.0,104, 2));
        inventory.addProduct(new Phone("OnePlus 12", 800.0,105, 2));
        inventory.addProduct(new Phone("iPhone 15 Pro", 1200.0,106, 3));
        inventory.addProduct(new Phone("Asus ROG Phone 8", 1100.0,107, 2));
        inventory.addProduct(new Phone("Huawei Pura 70", 850.0,108, 2));
        inventory.addProduct(new Phone("Nothing Phone 2", 550.0,109, 2));
        inventory.addProduct(new Phone("Sony Xperia 1 VI", 1300.0,110,   2));

        inventory.addProduct(new Laptop("Asus Vivobook 14 Flip", 1688, 300,32));
        inventory.addProduct(new Laptop("MacBook Air M3", 1400.0,301, 16));
        inventory.addProduct(new Laptop("ASUS Zenbook 14", 1100.0,302, 16));
        inventory.addProduct(new Laptop("HP Spectre x360", 1500.0,303, 32));
        inventory.addProduct(new Laptop("Dell XPS 13", 1600.0,304, 16));
        inventory.addProduct(new Laptop("Acer Swift Go", 800.0,305, 8));
        inventory.addProduct(new Laptop("Lenovo Legion 5", 1300.0,306, 32));
        inventory.addProduct(new Laptop("MSI Katana 15", 1000.0,307, 16));
        inventory.addProduct(new Laptop("Huawei MateBook X", 1700.0,308, 16));
        inventory.addProduct(new Laptop("Gigabyte Aero 16", 2000.0,309, 32));

        inventory.addProduct(new Headphones("Sony WH-1000", 300.0,200, true));
        inventory.addProduct(new Headphones("Apple AirPods Max", 550.0,201, true));
        inventory.addProduct(new Headphones("Bose QuietComfort Ultra", 400.0,202, true));
        inventory.addProduct(new Headphones("Sennheiser Momentum 4", 350.0,203, true));
        inventory.addProduct(new Headphones("JBL Tune 720BT", 80.0,204, false));
        inventory.addProduct(new Headphones("Marshall Monitor II", 300.0,205, true));
        inventory.addProduct(new Headphones("Anker Soundcore Q45", 150.0,206, true));
        inventory.addProduct(new Headphones("Sony WF-1000XM5", 250.0,207, true));
        inventory.addProduct(new Headphones("Beats Studio Pro", 350.0,208, true));
        inventory.addProduct(new Headphones("Audio-Technica M50xBT2", 200.0,209, false));
        

        Cart cart = new Cart();
        Product chosenProduct = inventory.getProductByID(306);
        cart.addProduct(chosenProduct);
        

        System.out.println("Total cart value: " + cart.calculateTotal());
        System.out.println(cart.findMostExpensiveProduct());
        System.out.println(cart.getProducts());
      
    }
}