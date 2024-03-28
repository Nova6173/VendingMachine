package se.JavaLexicon;

import java.util.Scanner; // Reads input from user
import se.JavaLexicon.model.*;

public class App {
    public static void main(String[] args) {

        // Created a Vending Machine
        VendingMachine vendingMachine = new VendingMachineImpl();

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

        // Request the selected product (tries to buy yhe selected product)
        Product selectedProduct = vendingMachine.request(productNumber);

        // Check if the selected product is not null (Checks if the product can be bought)
        if (selectedProduct != null) {

            System.out.println(selectedProduct.getProductName() + " purchased."); // Print purchased product name


            double change = vendingMachine.endSession(); // Get the change after purchasing


            System.out.println("Change returned: " + change + " SEK"); // Print returned change
        } else {

            System.out.println("Product could not be purchased."); // Print if product could not be purchased

        }
    }

    // Method to print a list of available products with their numbers
    private static void printProductList(VendingMachine vendingMachine) {
        String[] products = vendingMachine.getProducts();
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i]);  // Print product number and details
        }
    }
}
