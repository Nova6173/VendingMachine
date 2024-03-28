package se.JavaLexicon.model;

public class Soda extends Product {
    private SodaType type;

    // Constructor to initialize the soda with its ID, price, name, and type
    public Soda(int id, double price, String productName, SodaType type) {
        super(id, price, productName);
        this.type = type;
    }

    // Method to set the type of soda
    public void setType(SodaType type) {
        this.type = type;
    }

    // Method to get the type of soda
    public SodaType getType() {
        return type;
    }

    // Method to get the description of the soda
    @Override
    public String getDescription() {
        String description = "Soda: " + getProductName();
        if (type == SodaType.DIET) {
            description += " (diet)";
        }
        return description;
    }

    // Method to set the price of the soda (not implemented)
    @Override
    public void setPrice() {

    }
}
