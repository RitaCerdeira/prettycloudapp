package ua.grupo7.pi.prettycloud.fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import ua.grupo7.pi.prettycloud.R;
import ua.grupo7.pi.prettycloud.models.Salon;
import ua.grupo7.pi.prettycloud.viewModels.SalonContactsViewModel;
import ua.grupo7.pi.prettycloud.viewModels.SalonsViewModel;

public class SalonContactsFragment extends Fragment {

    private SalonsViewModel mViewModel;
    private TextView salonAddressTextView;
    private TextView salonPhoneNumberTextView;
    private TextView salonScheduleTextView;

    public static SalonContactsFragment newInstance() {
        return new SalonContactsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.salon_contacts_fragment, container, false);

        salonAddressTextView = view.findViewById(R.id.salon_address_textView);
        salonPhoneNumberTextView = view.findViewById(R.id.salon_phoneNumber_textView);
        salonScheduleTextView = view.findViewById(R.id.salon_schedule_textView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SalonsViewModel.class);
        Salon s = mViewModel.getTestSalon();
        salonAddressTextView.setText(s.getAddress());
        salonPhoneNumberTextView.setText(s.getPhoneNumber());
        salonScheduleTextView.setText(String.valueOf(s.getPriceRange()));

    }




}
