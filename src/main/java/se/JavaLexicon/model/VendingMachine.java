package se.JavaLexicon.model;


public interface VendingMachine {

    // Method to add currency to the vending machine
    void addCurrency (int amount);

    // Method to request a product by providing its ID
    Product request (int productId);

    // Method to end the current session and return the remaining balance
    double endSession ();

    // Method to get the description of a product by providing its ID
    String getDescription (int productId);

    // Method to get the current balance in the vending machine
    double getBalance ();

    // Method to get an array of strings representing the available products
    String[] getProducts ();

    // Method to add a product to the vending machine
    void addProduct (Product product);

    // Method to check the stock of a product
    void checkStock (int productId);


}
