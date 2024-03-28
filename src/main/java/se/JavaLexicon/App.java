package se.JavaLexicon;

import java.util.Scanner;
import se.JavaLexicon.model.*;

public class App {
    public static void main(String[] args) {
        // Create a Vending Machine
        VendingMachine vendingMachine = new VendingMachineImpl();

        // Add money to the machine
        vendingMachine.addCurrency(20);

        // Print out the available products with their numbers
        String[] availableProducts = vendingMachine.getProducts();
        System.out.println("Available products:");
        for (String product : availableProducts) {
            System.out.println(product);
        }


        // Ask the user to select a product
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of the product you want to buy: ");
        int productNumber = scanner.nextInt();

        // Request the selected product
        Product selectedProduct = vendingMachine.request(productNumber);

        // Check if the selected product is not null (i.e., a valid product)
        if (selectedProduct != null) {

            System.out.println(selectedProduct.getProductName() + " purchased.");


            double change = vendingMachine.endSession();


            System.out.println("Change returned: " + change + " SEK");
        } else {

            System.out.println("Product could not be purchased.");
        }
    }

    // Method to print a list of available products with their numbers
    private static void printProductList(VendingMachine vendingMachine) {
        String[] products = vendingMachine.getProducts();
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i]);
        }
    }
}
