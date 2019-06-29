package ua.grupo7.pi.prettycloud;


import android.util.Log;

import org.junit.Before;
import org.junit.Test;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import ua.grupo7.pi.prettycloud.communication.PrettyCloudWebService;
import ua.grupo7.pi.prettycloud.communication.RestApi;
import ua.grupo7.pi.prettycloud.config.Config;
import ua.grupo7.pi.prettycloud.models.Client;
import ua.grupo7.pi.prettycloud.models.Manager;
import ua.grupo7.pi.prettycloud.models.Review;
import ua.grupo7.pi.prettycloud.models.Salon;

import static junit.framework.TestCase.assertTrue;


public class RetrofitTest {

    private RestApi restApi = new RestApi(Config.API_URL);

    private PrettyCloudWebService webService;

    @Before
    public void init(){
        webService = restApi.getWebservice();
    }

    @Test
    public void checkGetSalons() throws Exception {

        Call<ArrayList<Salon>> call = webService.getSalons();
        try {
            Response<ArrayList<Salon>> response = call.execute();
            ArrayList<Salon> salons = response.body();

            assertTrue(response.isSuccessful() && salons.size()>0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkGetClients() throws Exception {

        Call<List<Client>> call = webService.getClients();
        try {
            Response<List<Client>> response = call.execute();
            List<Client> clients = response.body();
            Log.d("Clients: ",clients.toString());
            assertTrue(response.isSuccessful() && clients.size()>0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void checkGetManagers() throws Exception {

        Call<ArrayList<Manager>> call = webService.getManagers();
        try {
            Response<ArrayList<Manager>> response = call.execute();
            ArrayList<Manager> managers = response.body();
            assertTrue(response.isSuccessful() && managers.size()>0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkGetReviews() throws Exception {

        Call<ArrayList<Review>> call = webService.getReviews();
        try {
            Response<ArrayList<Review>> response = call.execute();
            List<Review> reviews = response.body();

            assertTrue(response.isSuccessful() && reviews.size()>0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
