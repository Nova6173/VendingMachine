package se.JavaLexicon.model;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineImpl implements VendingMachine {

    private double depositPool;
    private Product[] products;
    private Product[] productStock;
    private List<String> productNames = new ArrayList<>();
    private List<String> productFlavors = new ArrayList<>();
    
    
    
    public VendingMachineImpl() {
        this.productStock = new Product[10];
        this.products = new Product[10];
        initializeProducts();
        restockAllProducts(10);
    }

    public void restockAllProducts(int i) {
        // Restock all products
    }

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


        productNames.add(candy.getProductName());
        productNames.add(cookie.getProductName());
        productNames.add(cocaColaFlavor.getProductName());
        productNames.add(cactusFlavor.getProductName());
        productNames.add(redDelicious.getProductName());
        productNames.add(ingridMarie.getProductName());
        productNames.add(banana.getProductName());
        productNames.add(pear.getProductName());


        productFlavors.add(((Candy) candy).getFlavor().toString());
        productFlavors.add(((Cookie) cookie).getFlavor().toString());
        productFlavors.add(((Soda) cocaColaFlavor).getType().toString());
        productFlavors.add(((Soda) cactusFlavor).getType().toString());
        productFlavors.add(((Fruit) redDelicious).getSort());
        productFlavors.add(((Fruit) ingridMarie).getSort());
        productFlavors.add(((Fruit) banana).getSort());
        productFlavors.add(((Fruit) pear).getSort());

    }

    @Override
    public String[] getProducts() {
        String[] productDetails = new String[products.length];
        for (int i = 0; i < products.length; i++) {
            Product product = products[i];
            if (product != null) {
                String productName = product.getProductName();
                String productFlavor = getProductFlavor(productName);
                double productPrice = product.getPrice();


                productDetails[i] = (i + 1) + ". " + productName + " - " + product.getPrice() + " SEK" + " - " + productFlavor;
            }
        }
        return productDetails;
    }



    private String getProductFlavor(String productName) {
        for (int i = 0; i < productNames.size(); i++) {
            if (productNames.get(i).equals(productName)) {
                return productFlavors.get(i);
            }
        }
        return "";
    }

    public void addProduct(Product product) {
        for (int i = 0; i < productStock.length; i++) {
            if (productStock[i] == null) {
                productStock[i] = product;
                break;
            }
        }
    }

    @Override
    public void checkStock(int productId) {
        // Check stock for a specific product
        Product product = productStock[productId - 1]; // Indexet är productId - 1 eftersom produktnummer börjar från 1
        if (product != null) {
            System.out.println("Stock for " + product.getProductName() + ": " + product.getStock());
        } else {
            System.out.println("Product not found.");
        }
    }


    @Override
    public Product request(int productId) {
        for (Product product : productStock) {
            if (product != null && product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void addCurrency(int amount) {
        if (isValidCurrency(amount)) {
            depositPool += amount;
            System.out.println("Added " + amount + " SEK to deposit pool.");
        } else {
            System.out.println("Invalid currency. Please insert valid currency.");
        }
    }

    @Override
    public double endSession() {
        double returnedAmount = depositPool;
        depositPool = 0;
        return returnedAmount;
    }


    @Override
    public String getDescription(int productId) {
        for (Product product : productStock) {
            if (product != null && product.getId() == productId) {
                return product.getDescription();
            }
        }
        return null;
    }

    @Override
    public double getBalance() {
        return depositPool;
    }

    private boolean isValidCurrency(int amount) {
        int[] validCurrencies = {1, 2, 5, 10, 20, 50, 100, 200, 500, 1000};
        for (int currency : validCurrencies) {
            if (currency == amount) {
                return true;
            }
        }
        return false;
    }
}
