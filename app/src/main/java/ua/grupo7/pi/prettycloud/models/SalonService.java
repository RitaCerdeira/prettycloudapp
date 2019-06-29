package ua.grupo7.pi.prettycloud.models;

import com.google.gson.annotations.SerializedName;

public class SalonService {

    @SerializedName("salon")
    private Salon salon;

    @SerializedName("service")
    private Service service;

    @SerializedName("price")
    private Double price;



    public SalonService(Salon salon, Service service, Double price) {
        this.salon = salon;
        this.service = service;
        this.price = price;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }



}
