package ua.grupo7.pi.prettycloud.fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ua.grupo7.pi.prettycloud.R;
import ua.grupo7.pi.prettycloud.SharedValues;
import ua.grupo7.pi.prettycloud.adapters.ServiceAdapter;
import ua.grupo7.pi.prettycloud.models.SalonService;
import ua.grupo7.pi.prettycloud.viewModels.SalonsViewModel;

public class SalonServicesFragment extends Fragment {

    private SalonsViewModel mViewModel;
    private RecyclerView salonServiceRecyclerView;
    private ServiceAdapter serviceAdapter;
    private SharedValues sharedValues;
    private SharedPreferences sharedPreferences;

    public static SalonServicesFragment newInstance() {
        return new SalonServicesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.salon_services_fragment, container, false);
        salonServiceRecyclerView = view.findViewById(R.id.salon_services_recycler_view);
        sharedValues = new SharedValues();
        sharedPreferences = getActivity().getSharedPreferences(getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SalonsViewModel.class);
        Long salon_id = sharedPreferences.getLong(sharedValues.SALON_ID,0);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(RecyclerView.VERTICAL);
        serviceAdapter = new ServiceAdapter(mViewModel.getTestServices());
        salonServiceRecyclerView.setLayoutManager(llm);
        salonServiceRecyclerView.setAdapter(serviceAdapter);
        mViewModel.getSalonServices(salon_id).observe(getViewLifecycleOwner(), new Observer<ArrayList<SalonService>>() {
            @Override
            public void onChanged(ArrayList<SalonService> services) {
                Log.d("SalonService",services.get(0).toString());
                serviceAdapter.setServiceList(services);
                salonServiceRecyclerView.setAdapter(serviceAdapter);
            }
        });
    }

}
