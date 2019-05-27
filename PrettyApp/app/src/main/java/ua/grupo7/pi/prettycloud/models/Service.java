package ua.grupo7.pi.prettycloud.models;

public class Service {

    private int id;
    private String name;
    private Double price;

    public Service(int id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
}
