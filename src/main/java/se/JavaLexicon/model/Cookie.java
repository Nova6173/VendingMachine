package se.JavaLexicon.model;


public class Cookie extends Product {

    private CookieFlavor flavor; // Flavor of the cookie

    // Constructor to initialize a cookie with id, price, name and  flavor
    public Cookie(int id, double price, String productName, CookieFlavor flavor) {

        super(id, price, productName);

        this.flavor = flavor;
    }

    // Getter and setters for the flavor
    public void setFlavor(CookieFlavor flavor) {
        this.flavor = flavor;
    }
    public CookieFlavor getFlavor() {
        return flavor;
    }

    // Method to get the description of the cookie
    @Override
    public String getDescription() {

        return "Cookie: " + getProductName() + " (" + getFlavor() + ")";
    }

    // Method to set the price of the cookie
    @Override
    public void setPrice() {

    }
}
