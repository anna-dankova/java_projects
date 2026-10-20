package TechShopPet;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StoreInventory inventory = new StoreInventory();
        Scanner myObj = new Scanner(System.in);
        // inventory.addProduct(new Phone("iPhone 15", 1000.0,101, 2));
        // inventory.addProduct(new Phone("Samsung Galaxy S24", 900.0,102, 2));
        // inventory.addProduct(new Phone("Google Pixel 8", 700.0,103, 2));
        // inventory.addProduct(new Phone("Xiaomi 14 Pro", 600.0,104, 2));
        // inventory.addProduct(new Phone("OnePlus 12", 800.0,105, 2));
        // inventory.addProduct(new Phone("iPhone 15 Pro", 1200.0,106, 3));
        // inventory.addProduct(new Phone("Asus ROG Phone 8", 1100.0,107, 2));
        // inventory.addProduct(new Phone("Huawei Pura 70", 850.0,108, 2));
        // inventory.addProduct(new Phone("Nothing Phone 2", 550.0,109, 2));
        // inventory.addProduct(new Phone("Sony Xperia 1 VI", 1300.0,110, 2));

        // inventory.addProduct(new Laptop("Asus Vivobook 14 Flip", 1688, 300,32));
        // inventory.addProduct(new Laptop("MacBook Air M3", 1400.0,301, 16));
        // inventory.addProduct(new Laptop("ASUS Zenbook 14", 1100.0,302, 16));
        // inventory.addProduct(new Laptop("HP Spectre x360", 1500.0,303, 32));
        // inventory.addProduct(new Laptop("Dell XPS 13", 1600.0,304, 16));
        // inventory.addProduct(new Laptop("Acer Swift Go", 800.0,305, 8));
        // inventory.addProduct(new Laptop("Lenovo Legion 5", 1300.0,306, 32));
        // inventory.addProduct(new Laptop("MSI Katana 15", 1000.0,307, 16));
        // inventory.addProduct(new Laptop("Huawei MateBook X", 1700.0,308, 16));
        // inventory.addProduct(new Laptop("Gigabyte Aero 16", 2000.0,309, 32));

        // inventory.addProduct(new Headphones("Sony WH-1000", 300.0,200, true));
        // inventory.addProduct(new Headphones("Apple AirPods Max", 550.0,201, true));
        // inventory.addProduct(new Headphones("Bose QuietComfort Ultra", 400.0,202,
        // true));
        // inventory.addProduct(new Headphones("Sennheiser Momentum 4", 350.0,203,
        // true));
        // inventory.addProduct(new Headphones("JBL Tune 720BT", 80.0,204, false));
        // inventory.addProduct(new Headphones("Marshall Monitor II", 300.0,205, true));
        // inventory.addProduct(new Headphones("Anker Soundcore Q45", 150.0,206, true));
        // inventory.addProduct(new Headphones("Sony WF-1000XM5", 250.0,207, true));
        // inventory.addProduct(new Headphones("Beats Studio Pro", 350.0,208, true));
        // inventory.addProduct(new Headphones("Audio-Technica M50xBT2", 200.0,209,
        // false));

        boolean running = true;

        while (running) {
            System.out.println(
                    "\n --Menu:--\n1. Add new phone\n2. Add new laptop\n3. Add new headphones\n4. Catalog\n0. Exit ");

            if (!myObj.hasNextInt()) {
                System.out.println("Wrong number.");
                myObj.nextLine();
                continue;
            }
            int choise = myObj.nextInt();
            myObj.nextLine();
            switch (choise) {
                case 1: {
                    boolean isAdded = phoneScanner(inventory, myObj);
                    showAddResult(isAdded);
                    break;
                }
                case 2: {
                    boolean isAdded = laptopScanner(inventory, myObj);
                    showAddResult(isAdded);
                    break;
                }
                case 3: {
                    boolean isAdded = headphonesScanner(inventory, myObj);
                    showAddResult(isAdded);
                    break;
                }
                case 4:
                    if (inventory.getAllProducts().isEmpty()) {
                        System.out.println("empty");
                    }
                    for (Product p : inventory.getAllProducts()) {
                        System.out.println(p);
                    }
                    break;

                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("Wrong number. Enter number between 0 and 4.");
                    break;
            }

        }
    }

    public static void showAddResult(boolean isAdded) {
        if (isAdded) {
            System.out.println("Succes : Product was added");
        } else {
            System.out.println("Error : This ID is already used");
        }
    }

    public static boolean phoneScanner(StoreInventory inventory, Scanner myObj) {
        String productName = stringCheck(myObj, "Enter Name : ");

        double productPrice = doubleCheck(myObj, "Enter Price : ");

        int productID = intCheck(myObj, "Enter ID : ");

        int cameraCount = intCheck(myObj, "Enter count of cameras : ");

        return inventory.addProduct(new Phone(productName, productPrice, productID, cameraCount));
    }

    public static boolean laptopScanner(StoreInventory inventory, Scanner myObj) {
        String productName = stringCheck(myObj, "Enter Name : ");

        double productPrice = doubleCheck(myObj, "Enter Price : ");

        int productID = intCheck(myObj, "Enter ID : ");

        int RAMcount = intCheck(myObj, "Enter count of RAM : ");

        return inventory.addProduct(new Laptop(productName, productPrice, productID, RAMcount));
    }

    public static boolean headphonesScanner(StoreInventory inventory, Scanner myObj) {
        String productName = stringCheck(myObj, "Enter Name : ");

        double productPrice = doubleCheck(myObj, "Enter Price : ");

        int productID = intCheck(myObj, "Enter ID : ");

        boolean isWireless = booleanCheck(myObj, "Enter is wireless ?");

        return inventory.addProduct(new Headphones(productName, productPrice, productID, isWireless));
    }

    public static int intCheck(Scanner myObj, String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = myObj.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Wrong : Enter an integer in the 'int' range");
            }
        }
    }

    public static double doubleCheck(Scanner myObj, String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = myObj.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Price, decimal part separated by a dot");
            }
        }
    }

    public static boolean booleanCheck(Scanner myObj, String prompt) {
        while (true) {
            System.out.println(prompt);

            if (myObj.hasNextBoolean()) {
                boolean input = myObj.nextBoolean();
                myObj.nextLine();
                return input;
            } else {
                System.out.println("Wrong : True or False only!");
                myObj.nextLine();
            }
        }
    }

    public static String stringCheck(Scanner myObj, String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = myObj.nextLine();
            input.trim();
            if (input == null || input.isEmpty()) {
                System.out.println("Wrong : name cant be null!");
                myObj.nextLine();
            } else {
                return input;
            }
        }
    }
}
