package ua.grupo7.pi.prettycloud.viewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.grupo7.pi.prettycloud.communication.PrettyCloudWebService;
import ua.grupo7.pi.prettycloud.communication.RestApi;
import ua.grupo7.pi.prettycloud.config.BuildConfig;
import ua.grupo7.pi.prettycloud.models.Review;
import ua.grupo7.pi.prettycloud.models.Service;

public class SalonPricesViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    // TODO: Implement the ViewModel
    private MutableLiveData<List<Service>> serviceList;
    private RestApi restApi = new RestApi(BuildConfig.API_URL,8080);
    //private PrettyCloudWebService webService;
    private Executor executor = Executors.newSingleThreadExecutor();;

    private PrettyCloudWebService webService = restApi.getWebservice();
    public LiveData<List<Service>> getServices() {
        if (serviceList == null) {
            serviceList = new MutableLiveData<>();
            loadServices();
        }
        return serviceList;
    }


    private void loadServices() {
        List<Service> services = new ArrayList<>();
        //needs to be services of the salon
        webService.getServices().enqueue(new Callback<List<Service>>() {
            @Override
            public void onResponse(Call<List<Service>> call, Response<List<Service>> response) {
                Log.d("Sucess:","Got salons from api");
                executor.execute(() -> {
                    for (Service service:response.body()){
                        services.add(service);
                        Log.d("Review: ",service.toString());
                    }
                });
            }
            @Override
            public void onFailure(Call<List<Service>> call, Throwable t) {
                Log.d("ReviewError:",t.getMessage());
            }
        });
        serviceList = (MutableLiveData<List<Service>>) services;
    }

    public ArrayList<Service> getTestServices(){
        ArrayList<Service> services = new ArrayList<>();
        Service service = new Service(1,"Cabeleireiro",20.0);services.add(service);
        Service service2 = new Service(2,"Maquilhagem",10.0);services.add(service2);
        Service service3 = new Service(3,"Manicure",15.0);services.add(service3);
        Service service4 = new Service(4,"Pedicure",25.0);services.add(service4);
        return services;
    }

}
