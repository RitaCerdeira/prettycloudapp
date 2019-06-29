package ua.grupo7.pi.prettycloud.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import java.util.ArrayList;

import ua.grupo7.pi.prettycloud.R;
import ua.grupo7.pi.prettycloud.models.Salon;

public class SalonAdapter extends RecyclerView.Adapter<SalonAdapter.SalonViewHolder> {

    public interface OnItemClickListener {
      void onItemClick(Salon item);
    }

    private ArrayList<Salon> salonsList;
    private final OnItemClickListener listener;
    private Context context;


    public SalonAdapter(Context context,ArrayList<Salon> salonsList,OnItemClickListener listener){
        this.context = context;
        this.salonsList = salonsList;
        this.listener = listener;
      }

    @NonNull
    @Override
    public SalonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
      View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.salon_card,viewGroup,false);
      final int pos = position;
      v.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
          Log.d("Clicked salon number:" ,String.valueOf(pos));
        }
      });
      SalonViewHolder viewHolder = new SalonViewHolder(v);
      return  viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull SalonViewHolder salonViewHolder, int position) {
      Salon currentSalon = salonsList.get(position);
      Picasso.get().load(currentSalon.getImage()).into(salonViewHolder.salonImage);
      salonViewHolder.salonTextView.setText(currentSalon.getName());
      salonViewHolder.bind(currentSalon,listener);
    }


    @Override
    public int getItemCount() {
      return salonsList.size();
    }

    public static class SalonViewHolder extends RecyclerView.ViewHolder {

        public ImageView salonImage;
        public TextView salonTextView;

        public SalonViewHolder(@NonNull View itemView) {
            super(itemView);
            salonImage = itemView.findViewById(R.id.salon_image);
            salonTextView = itemView.findViewById(R.id.salon_description);
        }

        public void bind(final Salon salon, final OnItemClickListener listener) {
          itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
              listener.onItemClick(salon);
              Log.d("Salon name: ",salon.getName());
            }
          });
        }
    }





}
