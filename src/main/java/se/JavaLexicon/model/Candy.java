package se.JavaLexicon.model;


public class Candy extends Product {

    private boolean chocolate; // Is the candy chocolate flavor or not
    private CandyFlavor flavor; // Candy flavor

    // Constructor to initialize a Candy object with specified attributes
    public Candy(int id, double price, String productName, boolean chocolate, CandyFlavor flavor) {

        super(id, price, productName);

        this.chocolate = chocolate;
        this.flavor = flavor;
    }

    // Getter and setters for the chocolate
    public void setChocolate(boolean chocolate) {
        this.chocolate = chocolate;
    }
    public boolean isChocolate() {
        return chocolate;
    }

    // Getter and setter methods for the flavor
    public void setFlavor(CandyFlavor flavor) {
        this.flavor = flavor;
    }
    public CandyFlavor getFlavor() {
        return flavor;
    }


    @Override
    public String getDescription() {
        String description = "Candy: " + getProductName();

        if (isChocolate()) {
            description += " (chocolate)";
        }

        description += " (" + getFlavor() + ")";
        return description;
    }


    @Override
    public void setPrice() {

    }
}
