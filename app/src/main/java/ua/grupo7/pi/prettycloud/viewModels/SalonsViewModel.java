package ua.grupo7.pi.prettycloud.viewModels;



import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.grupo7.pi.prettycloud.communication.PrettyCloudWebService;
import ua.grupo7.pi.prettycloud.communication.RestApi;
import ua.grupo7.pi.prettycloud.config.Config;
import ua.grupo7.pi.prettycloud.models.Client;
import ua.grupo7.pi.prettycloud.models.Manager;
import ua.grupo7.pi.prettycloud.models.Review;
import ua.grupo7.pi.prettycloud.models.Salon;
import ua.grupo7.pi.prettycloud.models.SalonService;
import ua.grupo7.pi.prettycloud.models.Service;


public class SalonsViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<ArrayList<Salon>> salonList;
    private MutableLiveData<Salon> salon;
    private MutableLiveData<ArrayList<Review>> reviewList;
    private MutableLiveData<ArrayList<SalonService>> serviceList;
    private RestApi restApi = new RestApi(Config.API_URL);
    private PrettyCloudWebService webService = restApi.getWebservice();
    private Executor executor = Executors.newSingleThreadExecutor();;

    public LiveData<ArrayList<Salon>> getSalons() {
        if (salonList == null){
            salonList = new MutableLiveData<>();
            loadSalons();
        }
        return salonList;
    }

    public LiveData<Salon> getSalon(Long salonId) {
        if (salon == null){
            salon = new MutableLiveData<>();
            loadSalon(salonId);
        }
        return salon;
    }

    public LiveData<ArrayList<SalonService>> getSalonServices(Long salonId) {
        if (serviceList == null){
            serviceList = new MutableLiveData<>();
            loadSalonServices(salonId);
        }
        return serviceList;
    }

    public LiveData<ArrayList<Review>> getSalonReviews(Long salonId) {
        if (reviewList == null){
            reviewList = new MutableLiveData<>();
            loadSalonReviews(salonId);
        }
        return reviewList;
    }



    public void loadSalon(Long salonId){
        webService.getSalon(salonId).enqueue(new Callback<Salon>() {
            @Override
            public void onResponse(Call<Salon> call, Response<Salon> response) {
                Log.d("Sucess: ",response.body().toString());
                    salon.setValue(response.body());
            }
            @Override
            public void onFailure(Call<Salon> call, Throwable t) {
                Log.d("SalonError:",t.getMessage());
            }
        });
    }



    private void loadSalons()  {
        webService.getSalons().enqueue(new Callback<ArrayList<Salon>>() {
                @Override
                public void onResponse(Call<ArrayList<Salon>> call, Response<ArrayList<Salon>> response) {
                    Log.d("Sucess:","Got salons from api");
                    salonList.setValue(response.body());
                }
                @Override
                public void onFailure(Call<ArrayList<Salon>> call, Throwable t) {
                    Log.d("SalonError:",t.getMessage());
                }
            });

        }


    public void loadSalonServices(Long salonId){
        webService.getSalonServices(salonId).enqueue(new Callback<ArrayList<SalonService>>() {
            @Override
            public void onResponse(Call<ArrayList<SalonService>> call, Response<ArrayList<SalonService>> response) {
               // executor.execute(() ->{
                    serviceList.setValue(response.body());
               // });
            }
            @Override
            public void onFailure(Call<ArrayList<SalonService>> call, Throwable t) {
                Log.d("Error services:",t.getMessage());
            }
        });
    }
    public void postReview(Review review){
        webService.createReview(review).enqueue(new Callback<Review>() {
            @Override
            public void onResponse(Call<Review> call, Response<Review> response) {
                Log.d("sucess",response.body().toString());
            }

            @Override
            public void onFailure(Call<Review> call, Throwable t) {
                Log.d("fail",t.getMessage());
            }
        });
    }
    private void loadSalonReviews(Long salonId) {
        webService.getSalonReviews(salonId).enqueue(new Callback<ArrayList<Review>>() {
            @Override
            public void onResponse(Call<ArrayList<Review>> call, Response<ArrayList<Review>> response) {
                Log.d("Sucess:","Got salons from api");
                //executor.execute(() -> {
                    Log.d("Reviews:",response.body().toString());
                    reviewList.setValue(response.body());
                // });
            }
            @Override
            public void onFailure(Call<ArrayList<Review>> call, Throwable t) {
                Log.d("ReviewError:",t.getMessage());
            }
        });
    }

    public ArrayList<Salon> getTestSalons(){
      ArrayList<Salon> salons = new ArrayList<>();
      salons.add(new Salon(1L,"salon1","UA","231241234",new Manager(),"https://i.imgur.com/nW44HNc.jpg","DESC"));
      salons.add(new Salon(1L,"salon1","UA","231241234",new Manager(),"https://i.imgur.com/nW44HNc.jpg","DESC"));
      salons.add(new Salon(1L,"salon1","UA","231241234",new Manager(),"https://i.imgur.com/nW44HNc.jpg","DESC"));
      salons.add(new Salon(1L,"salon1","UA","231241234",new Manager(),"https://i.imgur.com/nW44HNc.jpg","DESC"));
      return salons;
    }

    public Salon getTestSalon(){
        return new Salon(1L,"salon1","UA","231241234",new Manager(),"https://i.imgur.com/nW44HNc.jpg","DESC");
    }

    public ArrayList<SalonService> getTestServices(){
        ArrayList<SalonService> salonServices = new ArrayList<>();
        SalonService salonService1 = new SalonService(new Salon(),new Service(),20.0);salonServices.add(salonService1);
        SalonService salonService2 = new SalonService(new Salon(),new Service(),20.0);salonServices.add(salonService2);
        SalonService salonService3 = new SalonService(new Salon(),new Service(),20.0);salonServices.add(salonService3);
        SalonService salonService4 = new SalonService(new Salon(),new Service(),20.0);salonServices.add(salonService4);
        return salonServices;
    }

    public ArrayList<Review> getTestReviews() {
        ArrayList<Review> reviews = new ArrayList<>();
        reviews.add(new Review(3L,new Client(),new Salon(),3,"nice salon"));
        reviews.add(new Review(3L,new Client(),new Salon(),3,"nice salon"));
        reviews.add(new Review(3L,new Client(),new Salon(),3,"nice salon"));
        reviews.add(new Review(3L,new Client(),new Salon(),3,"nice salon"));
        reviews.add(new Review(3L,new Client(),new Salon(),3,"nice salon"));
        return reviews;
    }

}
/*
package ua.grupo7.pi.prettycloud.viewModels;



        import android.content.SharedPreferences;
        import android.util.Log;

        import androidx.lifecycle.LiveData;
        import androidx.lifecycle.MutableLiveData;
        import androidx.lifecycle.ViewModel;

        import java.util.ArrayList;
        import java.util.concurrent.Executor;
        import java.util.concurrent.Executors;

        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;
        import ua.grupo7.pi.prettycloud.communication.PrettyCloudWebService;
        import ua.grupo7.pi.prettycloud.communication.RestApi;
        import ua.grupo7.pi.prettycloud.config.Config;
        import ua.grupo7.pi.prettycloud.models.Client;
        import ua.grupo7.pi.prettycloud.models.Manager;
        import ua.grupo7.pi.prettycloud.models.Review;
        import ua.grupo7.pi.prettycloud.models.Salon;
        import ua.grupo7.pi.prettycloud.models.SalonService;
        import ua.grupo7.pi.prettycloud.models.Service;


public class SalonsViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<ArrayList<Salon>> salonList;
    private Salon salon;
    private MutableLiveData<ArrayList<Review>> reviewList;
    private MutableLiveData<ArrayList<SalonService>> serviceList;
    private RestApi restApi = new RestApi(Config.API_URL);
    //private PrettyCloudWebService webService;
    private Executor executor = Executors.newSingleThreadExecutor();;
    private PrettyCloudWebService webService = restApi.getWebservice();

    public LiveData<ArrayList<Salon>> getSalons() {
        if (salonList == null){
            salonList = new MutableLiveData<>();
            loadSalons();
        }
        return salonList;
    }

    public Salon getSalon(Long salonId) {
        if (salon == null){
            salon = new Salon();
            loadSalon(salonId);
        }
        return salon;
    }

    public LiveData<ArrayList<SalonService>> getSalonServices(Long salonId) {
        if (serviceList == null){
            serviceList = new MutableLiveData<>();
            loadSalonServices(salonId);
        }
        return serviceList;
    }

    public LiveData<ArrayList<Review>> getSalonReviews(Long salonId) {
        if (reviewList == null){
            reviewList = new MutableLiveData<>();
            loadSalonReviews(salonId);
        }
        return reviewList;
    }



    public void loadSalon(Long salonId){
        webService.getSalon(salonId).enqueue(new Callback<Salon>() {
            @Override
            public void onResponse(Call<Salon> call, Response<Salon> response) {
                Log.d("Sucess: ",response.body().toString());
                salon = response.body();
            }
            @Override
            public void onFailure(Call<Salon> call, Throwable t) {
                Log.d("SalonError:",t.getMessage());
            }
        });
    }



    private void loadSalons()  {
        webService.getSalons().enqueue(new Callback<ArrayList<Salon>>() {
            @Override
            public void onResponse(Call<ArrayList<Salon>> call, Response<ArrayList<Salon>> response) {
                Log.d("Sucess:","Got salons from api");
                salonList.setValue(response.body());
            }
            @Override
            public void onFailure(Call<ArrayList<Salon>> call, Throwable t) {
                Log.d("SalonError:",t.getMessage());
            }
        });

    }


    public void loadSalonServices(Long salonId){
        webService.getSalonServices(salonId).enqueue(new Callback<ArrayList<SalonService>>() {
            @Override
            public void onResponse(Call<ArrayList<SalonService>> call, Response<ArrayList<SalonService>> response) {
                // executor.execute(() ->{
                serviceList.setValue(response.body());
                // });
            }
            @Override
            public void onFailure(Call<ArrayList<SalonService>> call, Throwable t) {
                Log.d("Error services:",t.getMessage());
            }
        });
    }

    private void loadSalonReviews(Long salonId) {
        webService.getSalonReviews(salonId).enqueue(new Callback<ArrayList<Review>>() {
            @Override
            public void onResponse(Call<ArrayList<Review>> call, Response<ArrayList<Review>> response) {
                Log.d("Sucess:","Got salons from api");
                //executor.execute(() -> {
                reviewList.setValue(response.body());
                // });
            }
            @Override
            public void onFailure(Call<ArrayList<Review>> call, Throwable t) {
                Log.d("ReviewError:",t.getMessage());
            }
        });
    }

    public ArrayList<Salon> getTestSalons(){
        ArrayList<Salon> salons = new ArrayList<>();
        salons.add(new Salon(1L,"salon1","UA","231241234",new Manager()));
        salons.add(new Salon(1L,"salon1","UA","231241234",new Manager()));
        salons.add(new Salon(1L,"salon1","UA","231241234",new Manager()));
        salons.add(new Salon(1L,"salon1","UA","231241234",new Manager()));
        return salons;
    }

    public Salon getTestSalon(){
        return new Salon(1L,"salon1","UA","231241234",new Manager());
    }

    public ArrayList<SalonService> getTestServices(){
        ArrayList<SalonService> salonServices = new ArrayList<>();
        SalonService salonService1 = new SalonService(new Salon(),new Service(),20.0);salonServices.add(salonService1);
        SalonService salonService2 = new SalonService(new Salon(),new Service(),20.0);salonServices.add(salonService2);
        SalonService salonService3 = new SalonService(new Salon(),new Service(),20.0);salonServices.add(salonService3);
        SalonService salonService4 = new SalonService(new Salon(),new Service(),20.0);salonServices.add(salonService4);
        return salonServices;
    }

    public ArrayList<Review> getTestReviews() {
        ArrayList<Review> reviews = new ArrayList<>();
        reviews.add(new Review(3L,new Client(),new Salon(),3,"nice salon"));
        reviews.add(new Review(3L,new Client(),new Salon(),3,"nice salon"));
        reviews.add(new Review(3L,new Client(),new Salon(),3,"nice salon"));
        reviews.add(new Review(3L,new Client(),new Salon(),3,"nice salon"));
        reviews.add(new Review(3L,new Client(),new Salon(),3,"nice salon"));
        return reviews;
    }

}
*/