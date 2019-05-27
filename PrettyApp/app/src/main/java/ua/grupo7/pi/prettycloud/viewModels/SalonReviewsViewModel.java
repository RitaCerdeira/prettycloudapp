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

public class SalonReviewsViewModel extends ViewModel {
  // TODO: Implement the ViewModel
  private MutableLiveData<List<Review>> reviewList;
  private RestApi restApi = new RestApi(BuildConfig.API_URL,8080);
  private PrettyCloudWebService webService;
  private Executor executor = Executors.newSingleThreadExecutor();;
  //private PrettyCloudWebService webService = restApi.getWebservice();
  public LiveData<List<Review>> getReviews() {
    if (reviewList == null) {
      reviewList = new MutableLiveData<List<Review>>();
      loadReviews();
    }
    return reviewList;
  }


  private void loadReviews() {
    List<Review> reviews = new ArrayList<>();
    webService.getReviews().enqueue(new Callback<List<Review>>() {
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
