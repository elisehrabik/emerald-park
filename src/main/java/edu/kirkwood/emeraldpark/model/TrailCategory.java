package edu.kirkwood.emeraldpark.model;


public class TrailCategory {
    private int id;
    private String name;
    private int numProducts;

    public TrailCategory() {
    }

    public TrailCategory(int id, String name, int numProducts) {
        this.id = id;
        this.name = name;
        this.numProducts = numProducts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumProducts() {
        return numProducts;
    }

    public void setNumProducts(int numProducts) {
        this.numProducts = numProducts;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numProducts=" + numProducts +
                '}';
    }
}