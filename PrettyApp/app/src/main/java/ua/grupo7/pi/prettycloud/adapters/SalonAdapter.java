package ua.grupo7.pi.prettycloud.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ua.grupo7.pi.prettycloud.R;
import ua.grupo7.pi.prettycloud.models.Salon;

public class SalonAdapter extends RecyclerView.Adapter<SalonAdapter.SalonViewHolder> {

    private ArrayList<Salon> salonsList;
    public static class SalonViewHolder extends RecyclerView.ViewHolder {

        public ImageView salonImage;
        public TextView salonTextView;

        public SalonViewHolder(@NonNull View itemView) {
            super(itemView);
            salonImage = itemView.findViewById(R.id.salon_image);
            salonTextView = itemView.findViewById(R.id.salon_description);
        }

    }
    public SalonAdapter(ArrayList<Salon> salonsList){
        this.salonsList = salonsList;
    }

    @NonNull
    @Override
    public SalonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.salon_card,viewGroup,false);
        SalonViewHolder viewHolder = new SalonViewHolder(v);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SalonViewHolder salonViewHolder, int position) {
        Salon currentSalon = salonsList.get(position);
        //salonViewHolder.salonImage.setImageResource(currentSalon.getImage());
        //salonViewHolder.salonTextView.setText(currentSalon.getDescription());
    }

    @Override
    public int getItemCount() {
        return salonsList.size();
    }



}
