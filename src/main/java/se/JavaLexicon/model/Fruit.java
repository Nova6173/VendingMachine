package se.JavaLexicon.model;

public class Fruit extends Product {
    private String sort;
    public Fruit (int id, double price, String productName, String sort) {
        super(id, price, productName);
        this.sort = sort;
    }

    public String getSort () {
        return sort;
    }


    // Method to describe fruits
    public String getDescription() {
       return "Fruit: "+ getProductName () +  " (" + sort + ")";

    }
    // Method to set fruit price


    @Override
    public void setPrice () {

    }
}
