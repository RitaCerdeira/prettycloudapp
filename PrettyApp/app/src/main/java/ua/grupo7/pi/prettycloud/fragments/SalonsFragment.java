package ua.grupo7.pi.prettycloud.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import ua.grupo7.pi.prettycloud.R;
import ua.grupo7.pi.prettycloud.viewModels.SalonsViewModel;
import ua.grupo7.pi.prettycloud.models.Salon;

public class SalonsFragment extends Fragment  {

    private SalonsViewModel mViewModel;
    private ArrayList<Salon> salonList;
    private TextView mTextMessage;

    public static SalonsFragment newInstance() {
        return new SalonsFragment();
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
                fragment = new SalonPricesFragment();
            } else if (id == R.id.bottom_nav_reviews) {
                fragment = new SalonReviewsFragment();
            }
            fragmentTransaction.replace(R.id.salon_child_fragment, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
                return false;
        }
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.salon_fragment, container, false);
        BottomNavigationView navView = view.findViewById(R.id.salon_bottom_nav_view);
      //  mTextMessage = view.findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SalonsViewModel.class);
    }


}
