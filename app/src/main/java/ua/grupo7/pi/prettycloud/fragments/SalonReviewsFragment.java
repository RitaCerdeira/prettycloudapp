package ua.grupo7.pi.prettycloud.fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ua.grupo7.pi.prettycloud.R;
import ua.grupo7.pi.prettycloud.adapters.ReviewAdapter;
import ua.grupo7.pi.prettycloud.models.Rating;
import ua.grupo7.pi.prettycloud.models.Review;
import ua.grupo7.pi.prettycloud.viewModels.SalonReviewsViewModel;
import ua.grupo7.pi.prettycloud.viewModels.SalonsViewModel;

public class SalonReviewsFragment extends Fragment {

    private SalonsViewModel mViewModel;
    private RecyclerView recyclerView;
    private ReviewAdapter reviewAdapter;
    private RatingBar ratingBar;
    private Button addReviewButton;
    private EditText reviewCommentEditText;
    private ArrayList<Review> reviewArrayList = new ArrayList<>();
    private float ratingVal;
    public static SalonReviewsFragment newInstance() {
        return new SalonReviewsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.salon_reviews_fragment, container, false);
        recyclerView = view.findViewById(R.id.salon_reviews_recycler_view);
        ratingBar = view.findViewById(R.id.salon_review_rating);
        addReviewButton = view.findViewById(R.id.salon_reviews_add_comment_button);
        reviewCommentEditText = view.findViewById(R.id.salon_reviews_comment_EditText);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SalonsViewModel.class);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(RecyclerView.VERTICAL);
        reviewArrayList = mViewModel.getTestReviews();
        reviewAdapter = new ReviewAdapter(reviewArrayList);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(reviewAdapter);
        /*mViewModel.getSalonReviews().observe(getViewLifecycleOwner(), new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {

            }
        });*/
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingVal = rating;
            }
        });
        addReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rating rating = new Rating(1,"asdkaso",ratingVal);
                Review review = new Review(1,rating,reviewCommentEditText.getText().toString());
                reviewAdapter.addReview(review);
                recyclerView.setAdapter(reviewAdapter);
                reviewCommentEditText.onEditorAction(EditorInfo.IME_ACTION_DONE);
            }
        });
    }

}
