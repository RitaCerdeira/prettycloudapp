package ua.grupo7.pi.prettycloud.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ua.grupo7.pi.prettycloud.R;
import ua.grupo7.pi.prettycloud.viewModels.SalonContactsViewModel;

public class SalonContactsFragment extends Fragment {

    private SalonContactsViewModel mViewModel;

    public static SalonContactsFragment newInstance() {
        return new SalonContactsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Toast.makeText(getActivity(),getClass().getSimpleName(),Toast.LENGTH_LONG).show();
        return inflater.inflate(R.layout.salon_contacts_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SalonContactsViewModel.class);
        // TODO: Use the ViewModel
    }

}
