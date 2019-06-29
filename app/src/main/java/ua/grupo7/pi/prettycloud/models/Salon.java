package ua.grupo7.pi.prettycloud.models;


import com.google.gson.annotations.SerializedName;

public class Salon {

    @SerializedName("id")
    private Long id;

    @SerializedName("name")
    private String name;

    @SerializedName("address")
    private String address;

    @SerializedName("phoneNumber")
    private String phoneNumber;

    @SerializedName("manager")
    private Manager manager;

    @SerializedName("image")
    private String image;

    @SerializedName("description")
    private String description;

    public Salon() {
    }

    public Salon(Long id, String name, String address, String phoneNumber, Manager manager, String image, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.manager = manager;
        this.image = image;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Salon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", manager=" + manager +
                '}';
    }
}