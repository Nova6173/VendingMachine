package se.JavaLexicon.model;


public abstract class Product {
    private int id; // Unique id for the product
    private double price; // Price of the product
    private String productName; // Name of the product
    private int stock; // Stock quantity of the product in the vending machine

    // Constructor to initialize basic info of a product
    public Product(int id, double price, String productName) {
        this.id = id;
        this.price = price;
        this.productName = productName;
    }

    // Constructor to initialize info and stock
    public Product(int id, double price, String productName, int stock) {
        this(id, price, productName);
        this.stock = stock;
    }

    // Method to get the stock quantity of the product
    public int getStock() {
        return stock;
    }

    // Method to set the stock quantity of the product
    public void setStock(int stock) {
        this.stock = stock;
    }

    // Method to get the ID of the product
    public int getId() {
        return id;
    }

    // Method to get the price of the product
    public double getPrice() {
        return price;
    }

    // Method to get the name of the product
    public String getProductName() {
        return productName;
    }

    // Method to get the description of the product
    public abstract String getDescription();

    // Method to examine the product
    public void examine() {
        System.out.println (productName + " " + price + "SEK"); // Print product name and price

    }

    // Method to use the product
    public void use() {
        System.out.println ("Thank you for your purchase! Enjoy & Welcome Back!"); // Print thank you message

    }

    // Abstract method to set the price of the product
    public abstract void setPrice();
}
