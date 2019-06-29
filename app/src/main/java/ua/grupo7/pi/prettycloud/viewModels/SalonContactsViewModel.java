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
import ua.grupo7.pi.prettycloud.models.Salon;

public class SalonContactsViewModel extends ViewModel {

    private LiveData<Salon> salon;
    private RestApi restApi = new RestApi(Config.API_URL);
    private PrettyCloudWebService webService;
    // PrettyCloudWebService webService = restApi.getWebservice();
    private Executor executor = Executors.newSingleThreadExecutor();;

    public LiveData<Salon> getSalon(Long id) {
        if (salon == null){
            salon = new MutableLiveData<>();
            loadSalon(id);
        }
        return salon;
    }

    private void loadSalon(Long id)  {
        webService.getSalon(id).enqueue(new Callback<Salon>() {
            @Override
            public void onResponse(Call<Salon> call, Response<Salon> response) {
                Log.d("Sucess:","Got salon from api");
                executor.execute(() -> {
                    //salon = (MutableLiveData<Salon>) response.body();
                });
            }
            @Override
            public void onFailure(Call<Salon> call, Throwable t) {
                Log.d("SalonError:",t.getMessage());
            }
        });

    }


}
