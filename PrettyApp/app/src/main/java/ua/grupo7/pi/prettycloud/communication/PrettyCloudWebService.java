package ua.grupo7.pi.prettycloud.communication;

import java.util.List;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ua.grupo7.pi.prettycloud.models.Salon;
import ua.grupo7.pi.prettycloud.models.User;

public interface PrettyCloudWebService {

    @GET("salons")
    Call<List<Salon>> getSalons();

    @GET("salon")
    Call<List<Salon>> getSalon(@Query("name") String salon_name);

    @GET("users")
    Call<List<User>> getUsers();

    @POST("newUser")
    Call<User> createUser(@Body User user);

    @POST("user")
    Call<User> getUserByName(@Query("name") String name);

}
