package ua.grupo7.pi.prettycloud.models;

import com.google.gson.annotations.SerializedName;

public class    Review {

    @SerializedName("id")
    private Long id;

    @SerializedName("client")
    private Client client;

    @SerializedName("salon")
    private Salon salon;

    @SerializedName("rating")
    private double rating;

    @SerializedName("comment")
    private String comment;

    public Long getId() {
        return id;
    }

    public Review() {
    }

    public Review(Long id, Client client, Salon salon, double rating, String comment) {
        this.id = id;
        this.client = client;
        this.salon = salon;
        this.rating = rating;
        this.comment = comment;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
