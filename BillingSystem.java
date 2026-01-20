import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BillingSystem {
    private List<Item> inventory = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("Welcome to the Inventory Billing System");

        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Add Item");
            System.out.println("2. Update Stock");
            System.out.println("3. View Inventory");
            System.out.println("4. Generate Bill");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            if (scanner.hasNext()) {
                String choice = scanner.next();
                switch (choice) {
                    case "1": addItem(); break;
                    case "2": updateStock(); break;
                    case "3": viewInventory(); break;
                    case "4": generateBill(); break;
                    case "5":
                        System.out.println("Exiting system. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid option, please try again.");
                }
            }
        }
    }

    private void addItem() {
        try {
            System.out.println("\n--- Add New Item ---");
            System.out.print("Enter Item ID: ");
            int id = scanner.nextInt();
            
            for (Item item : inventory) {
                if (item.getId() == id) {
                    System.out.println("Error: Item with ID " + id + " already exists.");
                    return;
                }
            }

            System.out.print("Enter Item Name: ");
            String name = scanner.next();

            System.out.print("Enter Price: ");
            double price = scanner.nextDouble();

            System.out.print("Enter Stock Quantity: ");
            int stock = scanner.nextInt();

            inventory.add(new Item(id, name, price, stock));
            System.out.println("Item added successfully!");
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine(); // Clear buffer
        }
    }

    private void updateStock() {
        try {
            System.out.println("\n--- Update Stock ---");
            System.out.print("Enter Item ID to update: ");
            int id = scanner.nextInt();
            Item item = null;
            for (Item i : inventory) {
                if (i.getId() == id) {
                    item = i;
                    break;
                }
            }

            if (item != null) {
                System.out.print("Current stock for " + item.getName() + " is " + item.getStock() + ". Enter quantity to add: ");
                int quantity = scanner.nextInt();
                if (quantity < 0 && (item.getStock() + quantity < 0)) {
                    System.out.println("Error: Cannot reduce stock below zero.");
                } else {
                    item.setStock(item.getStock() + quantity);
                    System.out.println("Stock updated. New stock: " + item.getStock());
                }
            } else {
                System.out.println("Item not found.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input.");
            scanner.nextLine();
        }
    }

    private void viewInventory() {
        System.out.println("\n--- Current Inventory ---");
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.printf("%-5s %-15s %-10s %-10s%n", "ID", "Name", "Price", "Stock");
        for (Item item : inventory) {
            System.out.printf("%-5d %-15s %-10.2f %-10d%n", item.getId(), item.getName(), item.getPrice(), item.getStock());
        }
    }

    private void generateBill() {
        System.out.println("\n--- Generate Bill ---");
        Map<Item, Integer> cart = new HashMap<>();
        boolean generating = true;

        while (generating) {
            viewInventory();
            System.out.print("\nEnter Item ID to add to bill (or -1 to finish): ");
            try {
                int id = scanner.nextInt();
                if (id == -1) {
                    generating = false;
                } else {
                    Item item = null;
                    for (Item i : inventory) {
                        if (i.getId() == id) {
                            item = i;
                            break;
                        }
                    }

                    if (item != null) {
                        System.out.print("Enter Quantity: ");
                        int qty = scanner.nextInt();
                        if (qty > 0) {
                            int currentCartQty = cart.getOrDefault(item, 0);
                            if (item.getStock() >= currentCartQty + qty) {
                                cart.put(item, currentCartQty + qty);
                                System.out.println(item.getName() + " added to cart.");
                            } else {
                                System.out.println("Insufficient stock. Available: " + (item.getStock() - currentCartQty));
                            }
                        } else {
                            System.out.println("Quantity must be positive.");
                        }
                    } else {
                        System.out.println("Item not found.");
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid input.");
                scanner.nextLine();
            }
        }

        if (!cart.isEmpty()) {
            System.out.println("\n--- Final Bill ---");
            System.out.printf("%-15s %-10s %-10s %-10s%n", "Name", "Price", "Qty", "Total");
            double totalAmount = 0.0;

            for (Map.Entry<Item, Integer> entry : cart.entrySet()) {
                Item item = entry.getKey();
                int qty = entry.getValue();
                double lineTotal = item.getPrice() * qty;
                totalAmount += lineTotal;
                System.out.printf("%-15s %-10.2f %-10d %-10.2f%n", item.getName(), item.getPrice(), qty, lineTotal);

                // Deduct from inventory
                item.setStock(item.getStock() - qty);
            }
            System.out.println("------------------------------------------------");
            System.out.println("Grand Total: " + totalAmount);
            System.out.println("Inventory updated.");
        } else {
            System.out.println("No items purchased.");
        }
    }

    public static void main(String[] args) {
        new BillingSystem().start();
    }
}
