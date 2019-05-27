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
import ua.grupo7.pi.prettycloud.models.Rating;
import ua.grupo7.pi.prettycloud.models.Review;
import ua.grupo7.pi.prettycloud.models.Salon;
import ua.grupo7.pi.prettycloud.models.Service;


public class SalonsViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<List<Salon>> salonList;
    private MutableLiveData<Salon> salon;
    private MutableLiveData<List<Review>> reviewList;
    private MutableLiveData<List<Service>> serviceList;
    private MutableLiveData<Long> salonId = new MutableLiveData<>();
    private RestApi restApi = new RestApi(BuildConfig.API_URL,8080);
    private PrettyCloudWebService webService;
    private Executor executor = Executors.newSingleThreadExecutor();;
    //private PrettyCloudWebService webService = restApi.getWebservice();

    public LiveData<List<Salon>> getSalons() {
        if (salonList == null){
            salonList = new MutableLiveData<>();
            loadSalons();
        }
        return salonList;
    }

    public LiveData<Salon> getSalon() {
        if (salon == null){
            salon = new MutableLiveData<>();
            loadSalon();
        }
        return salon;
    }

    public LiveData<List<Service>> getSalonServices() {
        if (serviceList == null){
            serviceList = new MutableLiveData<>();
            loadSalonServices();
        }
        return serviceList;
    }

    public LiveData<List<Review>> getSalonReviews() {
        if (reviewList == null){
            reviewList = new MutableLiveData<>();
            loadSalonReviews();
        }
        return reviewList;
    }

    public void selectSalon(Long id){
        salonId.setValue(id);
    }


    public void loadSalon(){
        Salon salon = null;
        webService.getSalon(salonId.getValue()).enqueue(new Callback<Salon>() {
            @Override
            public void onResponse(Call<Salon> call, Response<Salon> response) {
                Log.d("Sucess:","Got salons from api");
                    setSalon(response.body());
            }
            @Override
            public void onFailure(Call<Salon> call, Throwable t) {
                Log.d("SalonError:",t.getMessage());
            }
        });
    }

    public void setSalon(Salon s){
        salon.setValue(s);
    }


    private void loadSalons()  {
        List<Salon> salons = new ArrayList<>();
        webService.getSalons().enqueue(new Callback<List<Salon>>() {
                @Override
                public void onResponse(Call<List<Salon>> call, Response<List<Salon>> response) {
                    Log.d("Sucess:","Got salons from api");
                    executor.execute(() -> {
                    for (Salon salon:response.body()){
                        salons.add(salon);
                        Log.d("Salon: ",salon.toString());
                    }
                    });
                }
                @Override
                public void onFailure(Call<List<Salon>> call, Throwable t) {
                    Log.d("SalonError:",t.getMessage());
                }
            });
        salonList.setValue(salons);
    }

    public void loadSalonServices(){
        ArrayList<Service> services = new ArrayList<>();
        webService.getSalonServices(salonId.getValue()).enqueue(new Callback<List<Service>>() {
            @Override
            public void onResponse(Call<List<Service>> call, Response<List<Service>> response) {
                executor.execute(() ->{
                    for (Service service:response.body()){
                        services.add(service);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Service>> call, Throwable t) {
                Log.d("Error services:",t.getMessage());
            }
        });
        serviceList.setValue(services);
    }

    private void loadSalonReviews() {
        ArrayList<Review> reviews = new ArrayList<>();
        webService.getSalonReviews(salonId.getValue()).enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                Log.d("Sucess:","Got salons from api");
                executor.execute(() -> {
                    for (Review review:response.body()){
                        reviews.add(review);
                        Log.d("Review: ",review.toString());
                    }
                });
            }
            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {
                Log.d("ReviewError:",t.getMessage());
            }
        });
        reviewList.setValue(reviews);
    }

    public ArrayList<Salon> getTestSalons(){
      ArrayList<Salon> salons = new ArrayList<>();
      salons.add(new Salon("NEW1", "NEW", "address", "phoneNumber"
        , 3));
      salons.add(new Salon("NEW2", "NEW2", "address", "phoneNumber"
        , 3));
      salons.add(new Salon("NEW3", "NEW3", "address", "phoneNumber"
        , 3));
      salons.add(new Salon("NEW4", "NEW4", "address", "phoneNumber"
        , 3));
      return salons;
    }

    public Salon getTestSalon(){
        return new Salon("NEW1", "NEW", "address", "phoneNumber"
                , 3);
    }

    public ArrayList<Service> getTestServices(){
        ArrayList<Service> services = new ArrayList<>();
        Service service = new Service(1,"Cabeleireiro",20.0);services.add(service);
        Service service2 = new Service(2,"Maquilhagem",10.0);services.add(service2);
        Service service3 = new Service(3,"Manicure",15.0);services.add(service3);
        Service service4 = new Service(4,"Pedicure",25.0);services.add(service4);
        return services;
    }

    public ArrayList<Review> getTestReviews() {
        ArrayList<Review> reviews = new ArrayList<>();
        reviews.add(new Review(3, new Rating(), "Review 1"));
        reviews.add(new Review(3, new Rating(), "Review 2"));
        reviews.add(new Review(3, new Rating(), "Review 3"));
        reviews.add(new Review(3, new Rating(), "Review 4"));
        reviews.add(new Review(3, new Rating(), "Review 5"));
        return reviews;
    }

}
