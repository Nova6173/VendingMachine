package se.JavaLexicon.model;


public class Cookie extends Product {

    private CookieFlavor flavor;


    public Cookie(int id, double price, String productName, CookieFlavor flavor) {

        super(id, price, productName);

        this.flavor = flavor;
    }

    // Getter and setter methods for the flavor
    public void setFlavor(CookieFlavor flavor) {
        this.flavor = flavor;
    }
    public CookieFlavor getFlavor() {
        return flavor;
    }


    @Override
    public String getDescription() {

        return "Cookie: " + getProductName() + " (" + getFlavor() + ")";
    }


    @Override
    public void setPrice() {

    }
}
