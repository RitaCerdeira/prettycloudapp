package ua.grupo7.pi.prettycloud.fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

import ua.grupo7.pi.prettycloud.R;
import ua.grupo7.pi.prettycloud.SharedValues;
import ua.grupo7.pi.prettycloud.adapters.ReviewAdapter;
import ua.grupo7.pi.prettycloud.models.Client;
import ua.grupo7.pi.prettycloud.models.Review;
import ua.grupo7.pi.prettycloud.models.Salon;
import ua.grupo7.pi.prettycloud.viewModels.SalonsViewModel;

public class SalonReviewsFragment extends Fragment {

    private SalonsViewModel mViewModel;
    private RecyclerView recyclerView;
    private ReviewAdapter reviewAdapter;
    private RatingBar ratingBar;
    private Long salonId;
    private Button addReviewButton;
    private EditText reviewCommentEditText;
    private SharedValues sharedValues;
    private Button saveReviewButton;
    private SharedPreferences sharedPreferences;
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
        sharedValues = new SharedValues();
        sharedPreferences = getActivity().getSharedPreferences(getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SalonsViewModel.class);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(RecyclerView.VERTICAL);
        reviewAdapter = new ReviewAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(reviewAdapter);
        mViewModel.getSalonReviews(sharedPreferences.getLong(sharedValues.SALON_ID,0)).observe(getViewLifecycleOwner(), new Observer<ArrayList<Review>>() {
            @Override
            public void onChanged(ArrayList<Review> reviews) {
                reviewAdapter = new ReviewAdapter(reviews);
                recyclerView.setAdapter(reviewAdapter);
            }

        });
        /*ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingVal = rating;
            }
        });*/
        addReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog1();
            }
        });
    }

    public void openDialog1() {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.comment_modal);
        dialog.setTitle("Add a Comment");
        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        int height = (int)(getResources().getDisplayMetrics().heightPixels*0.50);
        dialog.getWindow().setLayout(width, height);
        ratingBar = dialog.findViewById(R.id.salon_review_rating);
        saveReviewButton = dialog.findViewById(R.id.salon_reviews_save_comment_button);
        reviewCommentEditText = dialog.findViewById(R.id.salon_reviews_comment_EditText);

        dialog.show();
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingVal = rating;
            }
        });
        saveReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double d = new Double(String.valueOf(ratingVal));
                Review review = new Review(2L,new Client(),new Salon(),d,reviewCommentEditText.getText().toString());
                reviewAdapter.addReview(review);
                recyclerView.setAdapter(reviewAdapter);
                dialog.dismiss();

            }
        });
    }

}
