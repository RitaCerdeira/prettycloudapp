package ua.grupo7.pi.prettycloud.models;


public class Salon {

    private Long id;

    private String name;

    private String address;

    private String phoneNumber;

  public Salon(String name, String address, String phoneNumber, String schedule, int priceRange) {
    this.name = name;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.schedule = schedule;
    this.priceRange = priceRange;
  }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String schedule;

    private int priceRange;


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

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public int getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(int priceRange) {
        this.priceRange = priceRange;
    }
}
