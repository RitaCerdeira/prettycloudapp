package ua.grupo7.pi.prettycloud.viewModels;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.grupo7.pi.prettycloud.communication.PrettyCloudWebService;
import ua.grupo7.pi.prettycloud.communication.RestApi;
import ua.grupo7.pi.prettycloud.config.Config;
import ua.grupo7.pi.prettycloud.models.Client;

public class ProfileViewModel extends ViewModel {
    private MutableLiveData<Client> client;
    private RestApi restApi = new RestApi(Config.API_URL);
    private PrettyCloudWebService webService = restApi.getWebservice();
    private Executor executor = Executors.newSingleThreadExecutor();;


    public LiveData<Client> getClient(Long id) {
        if (client == null){
            client = new MutableLiveData<>();
            loadClient(id);
        }
        return client;
    }

    private void loadClient(Long id) {
        webService.getClientById(id).enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> cliente, Response<Client> response) {
                Log.d("Sucess:","Got salons from api");
                client.setValue(response.body());
            }
            @Override
            public void onFailure(Call<Client> client, Throwable t) {
                Log.d("SalonError:",t.getMessage());
            }
        });
    }

    public Client getTestClient(){
        Client client = new Client("Rodrigo","Moura","93992314","rodrigo@gmail.com");
        return client;
    }

}
