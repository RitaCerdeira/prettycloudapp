package ua.grupo7.pi.prettycloud.models;

public class Rating {

    private int id;

    private String description;

    private float value;

    public Rating(int id, String description, float value) {
        this.id = id;
        this.description = description;
        this.value = value;
    }

    public Rating() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
