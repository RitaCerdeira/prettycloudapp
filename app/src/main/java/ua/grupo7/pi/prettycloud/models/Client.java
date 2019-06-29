package ua.grupo7.pi.prettycloud.models;

import com.google.gson.annotations.SerializedName;

public class Client {

    @SerializedName("id")
    private Long id;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("phoneNumber")
    private String phoneNumber;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public String image = "https://i.imgur.com/Tfyxm1H.png";

    public Client() {
    }

    public Client(String firstName, String lastName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Client(Long id, String firstName, String lastName, String phoneNumber, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
