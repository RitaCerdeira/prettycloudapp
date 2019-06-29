package ua.grupo7.pi.prettycloud.fragments;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import ua.grupo7.pi.prettycloud.R;
import ua.grupo7.pi.prettycloud.SharedValues;
import ua.grupo7.pi.prettycloud.models.Salon;
import ua.grupo7.pi.prettycloud.viewModels.SalonDetailsViewModel;
import ua.grupo7.pi.prettycloud.viewModels.SalonsViewModel;

public class SalonDetailsFragment extends Fragment {

    private SalonsViewModel mViewModel;
    private TextView salonNameTextView;
    private TextView salonDescriptionTextView;
    private ImageView salonImageView;
    private SharedValues sharedValues;
    private SharedPreferences sharedPreferences;

    public static SalonDetailsFragment newInstance() {
        return new SalonDetailsFragment();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getChildFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment fragment = new SalonContactsFragment();
            int id = item.getItemId();
            if (id == R.id.bottom_nav_contacts) {
                fragment = new SalonContactsFragment();
            } else if (id == R.id.bottom_nav_prices) {
                fragment = new SalonServicesFragment();
            } else if (id == R.id.bottom_nav_reviews) {
                fragment = new SalonReviewsFragment();
            }
            item.setChecked(true);
            fragmentTransaction.replace(R.id.salon_child_fragment, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            return false;
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.salon_details_fragment, container, false);
        BottomNavigationView navView = view.findViewById(R.id.salon_bottom_nav_view);
        sharedValues = new SharedValues();
        sharedPreferences = getActivity().getSharedPreferences(getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        salonNameTextView = view.findViewById(R.id.salon_details_name);
        salonDescriptionTextView = view.findViewById(R.id.salon_details_description);
        salonImageView = view.findViewById(R.id.salon_details_image);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SalonsViewModel.class);
        mViewModel.getSalon(sharedPreferences.getLong(sharedValues.SALON_ID,3)).observe(getViewLifecycleOwner(), new Observer<Salon>() {
            @Override
            public void onChanged(Salon salon) {
                salonNameTextView.setText(salon.getName());
                salonDescriptionTextView.setText(salon.getDescription());
                Picasso.get().load(salon.getImage()).into(salonImageView);
            }
        });
    }

}
