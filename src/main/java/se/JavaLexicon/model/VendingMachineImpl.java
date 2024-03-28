package se.JavaLexicon.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachineImpl implements VendingMachine {

    private double depositPool; // The total amount of money deposited into the machine
    private List<Product> products; // List to store products available in the machine
    private Map<Integer, Product> productStock; // Keep track of product stock

    // Constructor initializes productStock, products list, and adds initial products
    public VendingMachineImpl() {
        this.productStock = new HashMap<>(); // Initialize productStock
        this.products = new ArrayList<>(); // Initialize products list
        initializeProducts(); // Add initial products to the machine
    }

    // Method to initialize products in the vending machine
    private void initializeProducts() {
        // Add initial products to the vending machine
        Product candy = new Candy(1, 10.0, "Chocolate Bar", true, CandyFlavor.CHOCOLATE);
        Product cookie = new Cookie(2, 8.0, "Oatmeal Cookie", CookieFlavor.OATMEAL_RAISIN);
        Product CocaColaFlavor = new Soda(3, 5.0, "Soda Drink CocaCola flavor", SodaType.REGULAR);
        Product CactusFlavor = new Soda(4, 5.0, "Soda Drink Cactus flavor", SodaType.DIET);


        addProduct(candy); // Add candy
        addProduct(cookie); // Add cookie
        addProduct(CocaColaFlavor); // Add soda
        addProduct(CactusFlavor); // Add soda

        // Add fruits to the vending machine
        Product redDelicious = new Fruit(5, 3.0, "Apple", "Red Delicious");
        Product ingridMarie = new Fruit(6, 3.0, "Apple", "Ingrid Marie");
        Product banana = new Fruit(7, 2.5, "Banana", "Cavendish");
        Product pear = new Fruit(8, 4.0, "Pear", "Bartlett");

        addProduct(redDelicious); // add apple
        addProduct(ingridMarie); // add apple
        addProduct(banana); // add banana
        addProduct(pear); // add pear
    }

    // Method to get a list of available products with details
    @Override
    public String[] getProducts() {
        List<String> productList = new ArrayList<>();

        for (Product product : products) {
            // Check if the product is an instance of Fruit
            if (product instanceof Fruit) {
                // Cast the product to Fruit and retrieve its variety
                String fruitVariety = ((Fruit) product).getSort();
                productList.add(product.getProductName() + " (" + fruitVariety + ")");
            } else {
                // For other types of products, simply add their names to the list
                productList.add(product.getProductName());
            }
        }

        // Convert the list to an array and return
        return productList.toArray(new String[0]);
    }


    // Method to add a product to the vending machine
    @Override
    public void addProduct(Product product) {
        products.add(product); // Add product to the products list
        productStock.put(product.getId(), product); // Add product to the productStock
    }

    // Method to request and purchase a product from the vending machine
    @Override
    public Product request(int productId) {

        // Find the product with the specified productId
        Product requestedProduct = findProduct(productId);

        // Check if the requested product exists
        if (requestedProduct == null) { // The product did not exist
            System.out.println("Product with ID " + productId + " does not exist.");
            return null;
        }

        // Check if the requested product is out of stock
        if (requestedProduct.getStock() <= 0) { // The product is out of stock
            System.out.println("Product " + requestedProduct.getProductName() + " is out of stock."); // Print if product does not exist

            return null;
        }

        // Check if the deposit pool has sufficient balance to purchase the product
        if (depositPool >= requestedProduct.getPrice()) { // Checks if there is enough money

            System.out.println("Dispensing product: " + requestedProduct.getProductName()); // Dispense the product
            depositPool -= requestedProduct.getPrice(); // Update the balance
            System.out.println("Remaining balance: " + depositPool); // Print remaining balance
            requestedProduct.setStock(requestedProduct.getStock() - 1); // Reduce the product stock count
            return requestedProduct; // Return the purchased product
        } else {
            System.out.println("Insufficient balance. Please add more currency."); // Print if balance is insufficient
            return null;
        }
    }

    // Method to find a product by ID
    private Product findProduct(int productId) {
        return productStock.get(productId); // Get the product from the productStock
    }

    // Method to add currency to the deposit pool
    @Override
    public void addCurrency(int amount) {
        if (isValidCurrency(amount)) {
            depositPool += amount;
            System.out.println("Added " + amount + "SEK to deposit pool."); // Print amount added to deposit pool
        } else {
            System.out.println("Invalid currency. Please insert valid currency."); // Print if currency is invalid
        }
    }

    // Method to end the current session and return the remaining deposit pool
    @Override
    public double endSession() {
        double returnedAmount = depositPool;
        depositPool = 0; // Reset the deposit pool to zero
        return returnedAmount; // Return the remaining amount
    }

    // Method to get the description of a product
    @Override
    public String getDescription(int productId) {

        return null;
    }

    // Method to get the current balance in the deposit pool
    @Override
    public double getBalance() {
        return depositPool; // Return the current balance in the deposit pool

    }

    // Method to check if the provided currency amount is valid
    private boolean isValidCurrency(int amount) {
        int[] validCurrencies = {1, 2, 5, 10, 20, 50, 100, 200, 500, 1000};
        for (int currency : validCurrencies) {
            if (currency == amount) {
                return true; // If the provided amount matches any of stated currency, return true

            }
        }
        return false; // If the provided amount does not matche any of stated currency, return false
    }

    // Method to check the stock of a product
    @Override
    public void checkStock(int productId) {
        Product product = findProduct (productId);
        if (product != null) {
            int stock = product.getStock ();
            System.out.println ("Stock for product " + product.getProductName () + ": " + stock); // Prints product stock
        } else {
            System.out.println ("Product with ID " + productId + " does not exist."); //Pronts if product does not exist
        }

    }

    }
