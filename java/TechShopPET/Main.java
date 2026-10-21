package TechShopPet;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StoreInventory inventory = new StoreInventory();

        Scanner myObj = new Scanner(System.in);

        boolean running = true;

        while (running) {
            System.out.println(
                    "\n --Menu:--\n1. Add new phone\n2. Add new laptop\n3. Add new headphones\n4. Catalog\n0. Exit ");
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
                    List<Product> products = inventory.getAllProducts();

                    if (products.isEmpty()) {
                        System.out.println("Catalog is empty");
                    } else {
                        for (Product p : products) {
                            System.out.println(p);
                        }
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
        String productName = stringCheck(myObj, "Enter Name for phone: ");

        double productPrice = doubleCheck(myObj, "Enter Price (1-10000): ", 1, 10000);

        int productID = intCheck(myObj, "Enter ID (100-1000): ", 100, 1000);

        int cameraCount = intCheck(myObj, "Enter count of cameras(1-10) : ", 1, 10);

        return inventory.addProduct(new Phone(productName, productPrice, productID, cameraCount));
    }

    public static boolean laptopScanner(StoreInventory inventory, Scanner myObj) {
        String productName = stringCheck(myObj, "Enter Name for laptop: ");

        double productPrice = doubleCheck(myObj, "Enter Price (1-10000): ", 1, 10000);

        int productID = intCheck(myObj, "Enter ID (100-1000): ", 100, 1000);

        int RAMcount = intCheck(myObj, "Enter count of RAM (0-128): ", 0, 128);

        return inventory.addProduct(new Laptop(productName, productPrice, productID, RAMcount));
    }

    public static boolean headphonesScanner(StoreInventory inventory, Scanner myObj) {
        String productName = stringCheck(myObj, "Enter Name for headphones: ");

        double productPrice = doubleCheck(myObj, "Enter Price (1-10000): ", 1, 10000);

        int productID = intCheck(myObj, "Enter ID (100-1000): ", 100, 1000);

        boolean isWireless = booleanCheck(myObj, "Enter is wireless (true/false)");

        return inventory.addProduct(new Headphones(productName, productPrice, productID, isWireless));
    }

    public static int intCheck(Scanner myObj, String prompt, int min, int max) {
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

    public static double doubleCheck(Scanner myObj, String prompt, double min, double max) {
        while (true) {
            System.out.println(prompt);
            String input = myObj.nextLine().strip();
            try {
                double price = Double.parseDouble(input);
                if (price > 0 && price <= 10000) {
                    return price;
                } else {
                    System.out.printf("Price has to be between %d and %d\n", min, max);
                }
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
            if (input == null || input.isBlank()) {
                System.out.println("Wrong : name cant be null!");
                myObj.nextLine();
            } else {
                return input;
            }
        }
    }
}
