package ua.grupo7.pi.prettycloud.models;

public class Review {

    private int idSalon;

    private Rating rating;

    private String comment;


    public Review(int idSalon, Rating rating, String comment) {
      this.idSalon = idSalon;
      this.rating = rating;
      this.comment = comment;
    }

    public int getIdSalon() {
      return idSalon;
    }

    public Rating getRating() {
      return rating;
    }

    public String getComment() {
      return comment;
    }
}
