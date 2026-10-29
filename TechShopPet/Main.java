package TechShopPet;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InventoryJsonStorage storage = new InventoryJsonStorage();
        StoreInventory inventory = storage.loadJson("jsonInventory.json");

        Scanner myObj = new Scanner(System.in);

        boolean running = true;

        while (running) {
            System.out.println(
                    "\n --Menu:--\n1. Add new phone\n2. Add new laptop\n3. Add new headphones\n4. Catalog\n5. Save Catalog\n0. Exit ");
            int choice = readInt(myObj, "Enter your choice ", 0, 5);
            switch (choice) {
                case 1: {
                    boolean isAdded = addPhoneFromConsole(inventory, myObj);
                    showAddResult(isAdded);
                    break;
                }
                case 2: {
                    boolean isAdded = addLaptopFromConsole(inventory, myObj);
                    showAddResult(isAdded);
                    break;
                }
                case 3: {
                    boolean isAdded = addHeadphonesFromConsole(inventory, myObj);
                    showAddResult(isAdded);
                    break;
                }
                case 4: {
                    List<Product> products = inventory.getAllProducts();

                    if (products.isEmpty()) {
                        System.out.println("Catalog is empty");
                    } else {
                        for (Product p : products) {
                            System.out.println(p);
                        }
                    }
                    break;
                }
                case 5: {
                    final String filePath = "jsonInventory.json";
                    storage.saveJson(inventory, filePath);
                    break;
                }
                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("Wrong number. Enter number between 0 and 5.");
                    break;
            }

        }
    }

    public static void showAddResult(boolean isAdded) {
        if (isAdded) {
            System.out.println("Success : Product was added");
        } else {
            System.out.println("Error : This ID is already used");
        }
    }

    public static boolean addPhoneFromConsole(StoreInventory inventory, Scanner myObj) {
        String productName = readString(myObj, "Enter Name for phone: ");

        double productPrice = readDouble(myObj, "Enter Price (1-10000): ", 1, 10000);

        int productId = readInt(myObj, "Enter Id (100-1000): ", 100, 1000);

        int cameraCount = readInt(myObj, "Enter count of cameras(1-10) : ", 1, 10);

        return inventory.addProduct(new Phone(productName, productPrice, productId, cameraCount));
    }

    public static boolean addLaptopFromConsole(StoreInventory inventory, Scanner myObj) {
        String productName = readString(myObj, "Enter Name for laptop: ");

        double productPrice = readDouble(myObj, "Enter Price (1-10000): ", 1, 10000);

        int productId = readInt(myObj, "Enter Id (100-1000): ", 100, 1000);

        int RAMcount = readInt(myObj, "Enter count of RAM (0-128): ", 1, 128);

        return inventory.addProduct(new Laptop(productName, productPrice, productId, RAMcount));
    }

    public static boolean addHeadphonesFromConsole(StoreInventory inventory, Scanner myObj) {
        String productName = readString(myObj, "Enter Name for headphones: ");

        double productPrice = readDouble(myObj, "Enter Price (1-10000): ", 1, 10000);

        int productId = readInt(myObj, "Enter Id (100-1000): ", 100, 1000);

        boolean isWireless = readBoolean(myObj, "Enter is wireless (true/false)");

        return inventory.addProduct(new Headphones(productName, productPrice, productId, isWireless));
    }

    public static int readInt(Scanner myObj, String prompt, int min, int max) {
        while (true) {
            System.out.println(prompt);
            String input = myObj.nextLine().strip();

            try {
                int number = Integer.parseInt(input);
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.printf("Number has to be between from %d to %d\n", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong : Enter an integer in the 'int' range");
            }
        }
    }

    public static double readDouble(Scanner myObj, String prompt, double min, double max) {
        while (true) {
            System.out.println(prompt);
            String input = myObj.nextLine().strip();
            try {
                double price = Double.parseDouble(input);
                if (price > min && price <= max) {
                    return price;
                } else {
                    System.out.printf("Price has to be between %.2f and %.2f\n", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Price, decimal part separated by a dot");
            }
        }
    }

    public static boolean readBoolean(Scanner myObj, String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = myObj.nextLine().strip();

            if (input.equalsIgnoreCase("true")) {
                return true;
            } else if (input.equalsIgnoreCase("false")) {
                return false;
            } else {
                System.out.println("Wrong : True or False only!");
            }
        }
    }

    public static String readString(Scanner myObj, String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = myObj.nextLine();
            input = input.strip();
            if (input == null || input.isBlank()) {
                System.out.println("Wrong : name cant be null!");
            } else {
                return input;
            }
        }
    }
}
