package ua.grupo7.pi.prettycloud.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import ua.grupo7.pi.prettycloud.communication.PrettyCloudWebService;
import ua.grupo7.pi.prettycloud.communication.RestApi;
import ua.grupo7.pi.prettycloud.config.Config;
import ua.grupo7.pi.prettycloud.models.Client;
import ua.grupo7.pi.prettycloud.models.Review;
import ua.grupo7.pi.prettycloud.models.Salon;

public class SalonReviewsViewModel extends ViewModel {
  // TODO: Implement the ViewModel
  private MutableLiveData<List<Review>> reviewList;
  private RestApi restApi = new RestApi(Config.API_URL);
  private PrettyCloudWebService webService;
  private Executor executor = Executors.newSingleThreadExecutor();;
  //private PrettyCloudWebService webService = restApi.getWebservice();
  public LiveData<List<Review>> getReviews() {
    if (reviewList == null) {
      reviewList = new MutableLiveData<List<Review>>();
    }
    return reviewList;
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
