package ua.grupo7.pi.prettycloud.models;

import com.google.gson.annotations.SerializedName;

public class Service {

    @SerializedName("id")
    private int id;

    @SerializedName("type")
    private String type;

    @SerializedName("image")
    private String image;

    public Service() {
    }

    public Service(int id, String type,String image) {
        this.id = id;
        this.type = type;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

