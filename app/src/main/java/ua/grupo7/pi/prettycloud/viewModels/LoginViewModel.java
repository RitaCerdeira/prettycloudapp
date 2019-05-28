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
import ua.grupo7.pi.prettycloud.config.BuildConfig;
import ua.grupo7.pi.prettycloud.models.Client;
import ua.grupo7.pi.prettycloud.models.Login;
import ua.grupo7.pi.prettycloud.models.Review;
import ua.grupo7.pi.prettycloud.models.Salon;

public class LoginViewModel extends ViewModel {

    private RestApi restApi = new RestApi(BuildConfig.API_URL,8080);
    private PrettyCloudWebService webService;
    //private PrettyCloudWebService webService = restApi.getWebservice();
    private Executor executor = Executors.newSingleThreadExecutor();
    private  boolean login = false;

    public  boolean authentication(String username,String password) {
        webService.authenticate(username, password).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                Log.d("Sucess:","Logged in");
                executor.execute(() -> {
                        login = true;
                    }
                );
            }
            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Log.d("LoginError:",t.getMessage());
            }
        });
        return login;
    }


    public Login getTestLogin(){
        Login login = new Login("rodrigo@gmail.com","Mouraasd2");
        return login;
    }
}
