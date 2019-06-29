package ua.grupo7.pi.prettycloud.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ua.grupo7.pi.prettycloud.R;
import ua.grupo7.pi.prettycloud.models.SalonService;
import ua.grupo7.pi.prettycloud.models.Service;

public class ServiceAdapter  extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>{

    private ArrayList<SalonService> serviceList;


    public ServiceAdapter(ArrayList<SalonService> serviceList){
        this.serviceList = serviceList;
    }

    public void setServiceList(ArrayList<SalonService> serviceList) {
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public ServiceAdapter.ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.service_card,viewGroup,false);
        final int pos = position;
        ServiceAdapter.ServiceViewHolder viewHolder = new ServiceAdapter.ServiceViewHolder(v);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceAdapter.ServiceViewHolder serviceViewHolder, int position) {
        SalonService service = serviceList.get(position);
        serviceViewHolder.serviceNameTextView.setText(service.getService().getType());
        serviceViewHolder.servicePriceTextView.setText(service.getPrice().toString());
        Picasso.get().load(service.getService().getImage()).into(serviceViewHolder.serviceImageView);
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    public static class ServiceViewHolder extends RecyclerView.ViewHolder {

        public TextView servicePriceTextView;
        public TextView serviceNameTextView;
        public ImageView serviceImageView;

        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceNameTextView = itemView.findViewById(R.id.salon_service_name);
            servicePriceTextView = itemView.findViewById(R.id.salon_service_price);
            serviceImageView = itemView.findViewById(R.id.salon_service_image);
        }
    }

}
