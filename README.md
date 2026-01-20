# Syntecxhub Inventory Billing System

A simple command-line based Inventory Billing System written in Java. This application allows users to manage inventory items, update stock levels, view current inventory, and generate bills for customers.

## Features

*   **Add Item**: Add new items to the inventory with details like ID, Name, Price, and Stock Quantity.
*   **Update Stock**: Increase or decrease the stock quantity of existing items.
*   **View Inventory**: Display a formatted list of all items currently in the inventory.
*   **Generate Bill**: Create a bill by selecting items and quantities. The system automatically calculates totals and updates inventory stock.

## Getting Started

### Prerequisites

*   Java Development Kit (JDK) installed (version 8 or higher recommended).

### How to Run

1.  Compile the Java files:
    ```bash
    javac BillingSystem.java Item.java
    ```

2.  Run the application:
    ```bash
    java BillingSystem
    ```

## Usage

Upon running the application, you will be presented with a main menu:

1.  **Add Item**: Follow the prompts to enter item details.
2.  **Update Stock**: Enter the Item ID and the quantity to add (or a negative number to reduce).
3.  **View Inventory**: See a table of all items.
4.  **Generate Bill**: Enter Item IDs and quantities to add to the cart. Enter `-1` to finish and print the final bill.
5.  **Exit**: Close the application.

## Project Structure

*   `BillingSystem.java`: Contains the main application logic and user interface.
*   `Item.java`: Defines the data model for an inventory item.
