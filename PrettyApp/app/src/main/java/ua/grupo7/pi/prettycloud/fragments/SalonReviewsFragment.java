package ua.grupo7.pi.prettycloud.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.grupo7.pi.prettycloud.R;
import ua.grupo7.pi.prettycloud.viewModels.SalonReviewsViewModel;

public class SalonReviewsFragment extends Fragment {

    private SalonReviewsViewModel mViewModel;

    public static SalonReviewsFragment newInstance() {
        return new SalonReviewsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.salon_reviews_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SalonReviewsViewModel.class);
        // TODO: Use the ViewModel
    }

}
