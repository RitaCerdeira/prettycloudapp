package ua.grupo7.pi.prettycloud.communication;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ua.grupo7.pi.prettycloud.models.Login;
import ua.grupo7.pi.prettycloud.models.Manager;
import ua.grupo7.pi.prettycloud.models.Review;
import ua.grupo7.pi.prettycloud.models.Salon;
import ua.grupo7.pi.prettycloud.models.Client;
import ua.grupo7.pi.prettycloud.models.SalonService;
import ua.grupo7.pi.prettycloud.models.Service;

public interface PrettyCloudWebService {

    @GET("salons")
    Call<ArrayList<Salon>> getSalons();

    @GET("login/{email}/{password}")
    Call<Login> authenticate(@Path("email") String email, @Path("password") String password);

    @GET("salon/{id}")
    Call<Salon> getSalon(@Path("id") Long salonID);

    @GET("clients")
    Call<List<Client>> getClients();

    @GET("clients/{id}")
    Call<Client> getClientById(@Path("id") Long id);

    @DELETE("clients/{id}")
    Call<Client> deleteClientById(@Path("id") Long id);

    @POST("clients")
    Call<Client> createUser(@Body Client client);

    @GET("reviews")
    Call<ArrayList<Review>> getReviews();

    @GET("services")
    Call<List<Service>> getServices();

    @GET("services/{name}")
    Call<Service> getServiceByName(@Path("name") String name);

    @GET("salon/{id}/services")
    Call<ArrayList<SalonService>> getSalonServices(@Path("id") Long salonID);

    @GET("salon/{id}/reviews")
    Call<ArrayList<Review>> getSalonReviews(@Path("id") Long salonID);

    @POST("salon/{id}/reviews")
    Call<Review> createReview(@Body Review review);

    @GET("managers")
    Call<ArrayList<Manager>> getManagers();
}
