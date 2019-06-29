package ua.grupo7.pi.prettycloud.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import ua.grupo7.pi.prettycloud.R;
import ua.grupo7.pi.prettycloud.SharedValues;
import ua.grupo7.pi.prettycloud.adapters.SalonAdapter;
import ua.grupo7.pi.prettycloud.viewModels.SalonsViewModel;
import ua.grupo7.pi.prettycloud.models.Salon;

public class SalonsFragment extends Fragment  {

    private SalonsViewModel mViewModel;
    private SalonAdapter salonAdapter;
    private RecyclerView recyclerView;
    private SharedPreferences.Editor editor;
    private SharedValues sharedValues;
    private SharedPreferences sharedPreferences;

    public static SalonsFragment newInstance() {
        return new SalonsFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.salon_fragment, container, false);
        recyclerView = view.findViewById(R.id.salons_recycler_view);
        sharedPreferences = getActivity().getSharedPreferences(getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        sharedValues = new SharedValues();
        editor = sharedPreferences.edit();
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SalonsViewModel.class);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(RecyclerView.VERTICAL);
        salonAdapter = new SalonAdapter(getContext(),new ArrayList<Salon>(), new SalonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Salon item) {

            }
        });
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(salonAdapter);

        mViewModel.getSalons().observe(getViewLifecycleOwner(), new Observer<ArrayList<Salon>>() {
            @Override
            public void onChanged(ArrayList<Salon> salons) {
                Log.d("SalonListFromFragment: ",String.valueOf(salons.size()));
                salonAdapter = new SalonAdapter(getContext(),salons,
                        new SalonAdapter.OnItemClickListener() {
                            @Override public void onItemClick(Salon item) {
                                editor.putLong(sharedValues.SALON_ID,item.getId()).commit();
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                Fragment fragment = new SalonDetailsFragment();
                                fragmentTransaction.replace(R.id.fragment_container, fragment);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();
                            }
                        });
                recyclerView.setLayoutManager(llm);
                recyclerView.setAdapter(salonAdapter);
            }
        });

    }


}
