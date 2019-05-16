package ua.grupo7.pi.prettycloud.models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("user")
    private String user;

    private String email;

    private String password;

    private String username;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
