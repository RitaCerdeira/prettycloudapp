package ua.grupo7.pi.prettycloud.viewModels;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.grupo7.pi.prettycloud.communication.PrettyCloudWebService;
import ua.grupo7.pi.prettycloud.communication.RestApi;
import ua.grupo7.pi.prettycloud.config.Config;
import ua.grupo7.pi.prettycloud.models.Client;
import ua.grupo7.pi.prettycloud.models.Login;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<List<Client>> clientList;
    private RestApi restApi = new RestApi(Config.API_URL);
    private PrettyCloudWebService webService = restApi.getWebservice();
    private Long clientId=0L;

    public LiveData<List<Client>> getClients() {
        if (clientList == null){
            clientList = new MutableLiveData<>();
            loadClients();
        }
        return clientList;
    }

    public void loadClients() {
        Log.d("Loading Clients: ","Loadding clients");
        webService.getClients().enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                clientList.setValue(response.body());
                Log.d("Nice",response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                Log.d("Faillllll",t.getMessage());
            }
        });
    }

    public boolean authenticate(String username,String password){
        for (Client client:getClients().getValue()){
            if(client.getEmail().equals(username) && password.equals(client.getPassword())){
                clientId = client.getId();
                return true;
            }
        }
        return false;
    }

    public Login getTestLogin(){
        Login login = new Login("rodrigo@gmail.com","Mourasd2");
        return login;
    }

    public Long getClientId() {
        return clientId;
    }
}
