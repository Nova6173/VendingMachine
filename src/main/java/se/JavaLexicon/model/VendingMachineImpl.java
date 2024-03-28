package se.JavaLexicon.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachineImpl implements VendingMachine {

    private double depositPool;
    private List<Product> products;
    private Map<Integer, Product> productStock;

    // Constructor initializes productStock, products list, and adds initial products
    public VendingMachineImpl() {
        this.productStock = new HashMap<>();
        this.products = new ArrayList<>();
        initializeProducts();
    }

    // Method to initialize products in the vending machine
    private void initializeProducts() {
        // Add initial products to the vending machine
        Product candy = new Candy(1, 10.0, "Chocolate Bar", true, CandyFlavor.CHOCOLATE);
        Product cookie = new Cookie(2, 8.0, "Oatmeal Cookie", CookieFlavor.OATMEAL_RAISIN);
        Product soda = new Soda(3, 5.0, "Soda Drink Cola flavor", SodaType.REGULAR);

        addProduct(candy);
        addProduct(cookie);
        addProduct(soda);
    }

    // Method to get a list of available products with their details
    @Override
    public String[] getProducts() {
        List<String> productList = new ArrayList<>();

        for (Product product : products) {

            String productInfo = "ID: " + product.getId() + ", Name: " + product.getProductName() + ", Price: " + product.getPrice() + " SEK";
            productList.add(productInfo);
        }
        return productList.toArray(new String[0]);
    }

    // Method to add a product to the vending machine
    @Override
    public void addProduct(Product product) {
        products.add(product);
        productStock.put(product.getId(), product);
    }

    // Method to request and purchase a product from the vending machine
    @Override
    public Product request(int productId) {

        Product requestedProduct = findProduct(productId);

        // Check if the requested product exists
        if (requestedProduct == null) {
            System.out.println("Product with ID " + productId + " does not exist.");
            return null;
        }

        // Check if the requested product is out of stock
        if (requestedProduct.getStock() <= 0) {
            System.out.println("Product " + requestedProduct.getProductName() + " is out of stock.");
            return null;
        }

        // Check if the deposit pool has sufficient balance to purchase the product
        if (depositPool >= requestedProduct.getPrice()) {

            System.out.println("Dispensing product: " + requestedProduct.getProductName());
            depositPool -= requestedProduct.getPrice();
            System.out.println("Remaining balance: " + depositPool);
            requestedProduct.setStock(requestedProduct.getStock() - 1);
            return requestedProduct;
        } else {
            System.out.println("Insufficient balance. Please add more currency.");
            return null;
        }
    }

    // Method to find a product by ID
    private Product findProduct(int productId) {
        return productStock.get(productId);
    }

    // Method to add currency to the deposit pool
    @Override
    public void addCurrency(int amount) {
        if (isValidCurrency(amount)) {
            depositPool += amount;
            System.out.println("Added " + amount + "SEK to deposit pool.");
        } else {
            System.out.println("Invalid currency. Please insert valid currency.");
        }
    }

    // Method to end the current session and return the remaining deposit pool
    @Override
    public double endSession() {
        double returnedAmount = depositPool;
        depositPool = 0;
        return returnedAmount;
    }

    // Method to get the description of a product (not implemented)
    @Override
    public String getDescription(int productId) {

        return null;
    }

    // Method to get the current balance in the deposit pool
    @Override
    public double getBalance() {
        return depositPool;
    }

    // Method to check if the provided currency amount is valid
    private boolean isValidCurrency(int amount) {
        int[] validCurrencies = {1, 2, 5, 10, 20, 50, 100, 200, 500, 1000};
        for (int currency : validCurrencies) {
            if (currency == amount) {
                return true;
            }
        }
        return false;
    }

    // Method to check the stock of a product
    @Override
    public void checkStock(int productId) {
        Product product = findProduct(productId);
        if (product != null) {
            int stock = product.getStock();
            System.out.println("Stock for product " + product.getProductName() + ": " + stock);
        } else {
            System.out.println("Product with ID " + productId + " does not exist.");
        }
    }
}