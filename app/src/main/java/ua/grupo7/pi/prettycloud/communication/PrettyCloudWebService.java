package ua.grupo7.pi.prettycloud.communication;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ua.grupo7.pi.prettycloud.models.Login;
import ua.grupo7.pi.prettycloud.models.Review;
import ua.grupo7.pi.prettycloud.models.Salon;
import ua.grupo7.pi.prettycloud.models.Client;
import ua.grupo7.pi.prettycloud.models.Service;

public interface PrettyCloudWebService {

    @GET("salons")
    Call<List<Salon>> getSalons();

    @GET("login/{email}/{password}")
    Call<Login> authenticate(@Path("email") String email, @Path("password") String password);

    @GET("salon")
    Call<Salon> getSalon(@Query("id") Long salon_);

    @GET("clients")
    Call<List<Client>> getClients();

    @GET("clients/{id}")
    Call<Client> getClientById(@Path("id") Long id);

    @DELETE("clients/{id}")
    Call<Client> deleteClientById(@Path("id") Long id);

    @POST("clients")
    Call<Client> createUser(@Body Client client);

    @GET("reviews")
    Call<List<Review>> getReviews();

    @GET("services")
    Call<List<Service>> getServices();

    @GET("services/{name}")
    Call<Service> getServiceByName(@Path("name") String name);

    @GET("salon/{id}/services")
    Call<List<Service>> getSalonServices(@Path("id") Long salonID);

    @GET("salon/{id}/reviews")
    Call<List<Review>> getSalonReviews(@Path("id") Long salonID);

}
