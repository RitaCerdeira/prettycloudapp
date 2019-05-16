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
import ua.grupo7.pi.prettycloud.viewModels.SalonDetailsViewModel;

public class SalonDetails extends Fragment {

    private SalonDetailsViewModel mViewModel;

    public static SalonDetails newInstance() {
        return new SalonDetails();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.salon_details_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SalonDetailsViewModel.class);
        // TODO: Use the ViewModel
    }

}
