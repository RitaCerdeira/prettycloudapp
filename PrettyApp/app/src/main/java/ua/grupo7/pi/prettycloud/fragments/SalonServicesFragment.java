package ua.grupo7.pi.prettycloud.fragments;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.awt.font.TextAttribute;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import ua.grupo7.pi.prettycloud.R;
import ua.grupo7.pi.prettycloud.adapters.ServiceAdapter;
import ua.grupo7.pi.prettycloud.models.Service;
import ua.grupo7.pi.prettycloud.viewModels.SalonPricesViewModel;
import ua.grupo7.pi.prettycloud.viewModels.SalonsViewModel;

public class SalonServicesFragment extends Fragment {

    private SalonsViewModel mViewModel;
    private RecyclerView salonServiceRecyclerView;
    private ServiceAdapter serviceAdapter;

    public static SalonServicesFragment newInstance() {
        return new SalonServicesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.salon_services_fragment, container, false);
        salonServiceRecyclerView = view.findViewById(R.id.salon_services_recycler_view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SalonsViewModel.class);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(RecyclerView.VERTICAL);
        serviceAdapter = new ServiceAdapter(mViewModel.getTestServices());
        salonServiceRecyclerView.setLayoutManager(llm);
        salonServiceRecyclerView.setAdapter(serviceAdapter);
        /*mViewModel.getSalonServices().observe(getViewLifecycleOwner(), new Observer<List<Service>>() {
            @Override
            public void onChanged(List<Service> services) {
                serviceAdapter.setServiceList((ArrayList)services);
                salonServiceRecyclerView.setAdapter(serviceAdapter);
            }
        });*/
    }

}
