package ua.grupo7.pi.prettycloud.fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import ua.grupo7.pi.prettycloud.R;
import ua.grupo7.pi.prettycloud.SharedValues;
import ua.grupo7.pi.prettycloud.models.Salon;
import ua.grupo7.pi.prettycloud.viewModels.SalonContactsViewModel;
import ua.grupo7.pi.prettycloud.viewModels.SalonsViewModel;

public class SalonContactsFragment extends Fragment {

    private SalonsViewModel mViewModel;
    private TextView salonAddressTextView;
    private TextView salonPhoneNumberTextView;
    private TextView salonManagerNameTextView;
    private TextView salonManagerEmailTextView;
    private SharedValues sharedValues;
    private SharedPreferences sharedPreferences;

    public static SalonContactsFragment newInstance() {
        return new SalonContactsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.salon_contacts_fragment, container, false);

        salonAddressTextView = view.findViewById(R.id.salon_address_textView);
        salonPhoneNumberTextView = view.findViewById(R.id.salon_phoneNumber_textView);
        salonManagerNameTextView = view.findViewById(R.id.salon_manager_name_textView);
        salonManagerEmailTextView = view.findViewById(R.id.salon_manager_email_textView);
        sharedValues = new SharedValues();
        sharedPreferences = getActivity().getSharedPreferences(getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SalonsViewModel.class);
        Salon s = mViewModel.getTestSalon();
        salonAddressTextView.setText(s.getAddress());
        salonPhoneNumberTextView.setText(s.getPhoneNumber());
        Log.d("salonID on Contacts", String.valueOf(sharedPreferences.getLong(sharedValues.SALON_ID,0)));
        mViewModel.getSalon(sharedPreferences.getLong(sharedValues.SALON_ID,0)).observe(getViewLifecycleOwner(), new Observer<Salon>() {
            @Override
            public void onChanged(Salon salon) {
                salonAddressTextView.setText(salon.getAddress());
                salonPhoneNumberTextView.setText(salon.getPhoneNumber());
                salonManagerEmailTextView.setText(salon.getManager().getEmail());
                salonManagerNameTextView.setText(salon.getManager().getName());
            }
        });
    }




}
/*
package ua.grupo7.pi.prettycloud.fragments;

        import androidx.lifecycle.Observer;
        import androidx.lifecycle.ViewModelProviders;

        import android.content.Context;
        import android.content.SharedPreferences;
        import android.os.Bundle;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.fragment.app.Fragment;

        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;
        import android.widget.Toast;

        import ua.grupo7.pi.prettycloud.R;
        import ua.grupo7.pi.prettycloud.SharedValues;
        import ua.grupo7.pi.prettycloud.models.Salon;
        import ua.grupo7.pi.prettycloud.viewModels.SalonContactsViewModel;
        import ua.grupo7.pi.prettycloud.viewModels.SalonsViewModel;

public class SalonContactsFragment extends Fragment {

    private SalonsViewModel mViewModel;
    private TextView salonAddressTextView;
    private TextView salonPhoneNumberTextView;
    private TextView salonScheduleTextView;
    private SharedValues sharedValues;
    private SharedPreferences sharedPreferences;

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
        sharedValues = new SharedValues();
        sharedPreferences = getActivity().getSharedPreferences(getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SalonsViewModel.class);
        Log.d("salonID on Contacts", String.valueOf(sharedPreferences.getLong(sharedValues.SALON_ID,0)));
        Salon s = mViewModel.getSalon(sharedPreferences.getLong(sharedValues.SALON_ID,0));
        salonAddressTextView.setText(s.getAddress());
        salonPhoneNumberTextView.setText(s.getPhoneNumber());

    }




}
*/