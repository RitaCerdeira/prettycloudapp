package ua.grupo7.pi.prettycloud.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ua.grupo7.pi.prettycloud.R;
import ua.grupo7.pi.prettycloud.models.Review;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {


    private ArrayList<Review> reviewList;


    public ReviewAdapter(ArrayList<Review> reviewList){
      this.reviewList = reviewList;
    }

    public void addReview(Review review){
        reviewList.add(review);
    }
    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
      View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.review_card,viewGroup,false);
      final int pos = position;
      ReviewAdapter.ReviewViewHolder viewHolder = new ReviewAdapter.ReviewViewHolder(v);
      return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder reviewViewHolder, int position) {
      Review review = reviewList.get(position);
      reviewViewHolder.reviewCommentTextView.setText(review.getComment());
      reviewViewHolder.ratingCommentTextView.setText("Rating: " + String.valueOf(review.getRating()));
      reviewViewHolder.clientCommentTextView.setText("Client: " + review.getClient().getFirstName());
    }

    @Override
    public int getItemCount() {
      return reviewList.size();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {

      public TextView reviewCommentTextView;
      public TextView ratingCommentTextView;
      public TextView clientCommentTextView;

      public ReviewViewHolder(@NonNull View itemView) {
        super(itemView);
        reviewCommentTextView = itemView.findViewById(R.id.salon_review_comment_text);
        ratingCommentTextView = itemView.findViewById(R.id.salon_review_comment_rating);
        clientCommentTextView = itemView.findViewById(R.id.salon_review_comment_client_name);
      }
    }

}
