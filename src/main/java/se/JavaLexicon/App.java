package se.JavaLexicon;

import java.util.Scanner; // Reads input from user
import se.JavaLexicon.model.*;

public class App {
    public static void main(String[] args) {



        // Created a Vending Machine
        VendingMachine vendingMachine = new VendingMachineImpl();

        vendingMachine.checkStock(1);




        // Initialize the vending machine and restock the inventory for all products
        ((VendingMachineImpl) vendingMachine).restockAllProducts(10); // Restock all products with 10 units

        // Added money to the machine
        vendingMachine.addCurrency(20); // Add 20 Sek to the deposit pool

        // Prints out all the available products in the machine with their numbers
        String[] availableProducts = vendingMachine.getProducts();
        System.out.println("Available products:");
        for (String product : availableProducts) {
            System.out.println(product);
        }

        // Ask the user to select a product
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of the product you want to buy: ");
        int productNumber = scanner.nextInt();

        // Request the selected product (tries to buy the selected product)
        Product selectedProduct = vendingMachine.request(productNumber);

        // Check if the selected product is not null (Checks if the product can be bought)
        if (selectedProduct != null) {

            System.out.println(selectedProduct.getProductName() + " purchased."); // Print purchased product name

            double change = vendingMachine.endSession() - selectedProduct.getPrice(); // Get the change after purchasing

            System.out.println("Change returned: " + change + " SEK"); // Print returned change
            
        } else {

            System.out.println("Product could not be purchased."); // Print if product could not be purchased

        }
    }
}