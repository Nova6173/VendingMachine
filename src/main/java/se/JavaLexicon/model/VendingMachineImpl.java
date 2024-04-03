package se.JavaLexicon.model;

public class VendingMachineImpl implements VendingMachine {

    private double depositPool; // The total amount of money deposited into the machine
    private Product[] products; // List to store products available in the machine
    private Product[] productStock; // Keep track of product stock

    // Constructor initializes productStock, products list, and adds initial products
    public VendingMachineImpl() {
        this.productStock = new Product[10]; // Initialize productStock
        this.products = new Product[10]; // Initialize products list
        initializeProducts(); // Add initial products to the machine

        // Restock the inventory with 10 units of each product
        restockAllProducts(10);
    }

    public void restockAllProducts (int i) {
    }

    // Method to initialize products in the vending machine
    private void initializeProducts() {
        // Add initial products to the vending machine
        Product candy = new Candy(1, 10.0, "Candy Bar", true, CandyFlavor.CHOCOLATE);
        Product cookie = new Cookie(2, 8.0, "Cookie", CookieFlavor.OATMEAL_RAISIN);
        Product cocaColaFlavor = new Soda(3, 5.0, "Soda CocaCola", SodaType.REGULAR);
        Product cactusFlavor = new Soda(4, 5.0, "Soda Cactus", SodaType.DIET);
        Product redDelicious = new Fruit(5, 3.0, "Apple", "Red Delicious");
        Product ingridMarie = new Fruit(6, 3.0, "Apple", "Ingrid Marie");
        Product banana = new Fruit(7, 2.5, "Banana", "Cavendish");
        Product pear = new Fruit(8, 4.0, "Pear", "Bartlett");

        products[0] = candy;
        products[1] = cookie;
        products[2] = cocaColaFlavor;
        products[3] = cactusFlavor;
        products[4] = redDelicious;
        products[5] = ingridMarie;
        products[6] = banana;
        products[7] = pear;

        productStock[0] = candy;
        productStock[1] = cookie;
        productStock[2] = cocaColaFlavor;
        productStock[3] = cactusFlavor;
        productStock[4] = redDelicious;
        productStock[5] = ingridMarie;
        productStock[6] = banana;
        productStock[7] = pear;

        for (int i = 0; i < products.length; i++) {
            productStock[i] = products[i];
        }
    }

    // Method to get a list of available products with details
    @Override
    public String[] getProducts() {
        int count = 0;
        for (Product product : products){
            if (product != null){
                count++;
            }
        }

        String[] productDetails = new String[count];
        int index = 0;
        for (int i = 0; i < products.length; i++) {
            Product product = products[i];
            if (product != null) {
                productDetails[index] = (i + 1) + "." + product.getProductName() + " - " + product.getPrice() + " SEK" + " - " + product.getDescription();
                index++;
            }
        }

        return productDetails;
    }



    // Method to add a product to the vending machine
    @Override
    public void addProduct(Product product) { // Add product to the productStock
        for (int i = 0; i < productStock.length; i++) {
            if (productStock[i] == null) {
                productStock[i] = product;
                break;
            }
        }
    }

    @Override
    public void checkStock (int productId) {
        
    }

    // Method to request and purchase a product from the vending machine
    @Override
    public Product request(int productId) {
        // Find the product with the specified productId
        for (Product product : productStock) {
            if (product != null && product.getId() == productId) {
                return product;
            }
        }

        return null; // return null if product not found
    }

    // Method to add currency to the deposit pool
    @Override
    public void addCurrency(int amount) {
        if (isValidCurrency(amount)) {
            depositPool += amount;
            System.out.println("Added " + amount + " SEK to deposit pool."); // Print amount added to deposit pool
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
        for (Product product : productStock) {
            if (product != null && product.getId() == productId) {
                return product.getDescription();
            }
        }
        return null; // return null if product not found
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
        return false; // If the provided amount does not match any of stated currency, return false
    }
    }