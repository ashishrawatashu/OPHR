package com.cynoteck.petofyOPHR.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.response.updateProfileResponse.ServiceTypeList;

import java.util.List;

public class ServiceTypeListAdpater extends RecyclerView.Adapter<ServiceTypeListAdpater.MyViewHolder> {
    Context context;
    List<ServiceTypeList> serviceTypeLists;

    public ServiceTypeListAdpater(Context context, List<ServiceTypeList> serviceTypeLists) {
        this.context = context;
        this.serviceTypeLists = serviceTypeLists;
    }

    @NonNull
    @Override
    public ServiceTypeListAdpater.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_type_date, parent, false);
        ServiceTypeListAdpater.MyViewHolder vh = new ServiceTypeListAdpater.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceTypeListAdpater.MyViewHolder holder, int position) {
        holder.service_type_TV.setText(serviceTypeLists.get(position).getText());
        if (serviceTypeLists.get(position).getText().equals("Consultation")){
            holder.image_service_IV.setImageResource(R.drawable.consultation_icon);
        }
        if (serviceTypeLists.get(position).getText().equals("Training")){
            holder.image_service_IV.setImageResource(R.drawable.training_icon);

        }if (serviceTypeLists.get(position).getText().equals("Hostels")){
            holder.image_service_IV.setImageResource(R.drawable.hostel_icon);

        }if (serviceTypeLists.get(position).getText().equals("Grooming")){
            holder.image_service_IV.setImageResource(R.drawable.gromming_icon);

        }if (serviceTypeLists.get(position).getText().equals("Pet Shops")){
            holder.image_service_IV.setImageResource(R.drawable.pet_shopes_icon);

        }
    }

    @Override
    public int getItemCount() {
        return serviceTypeLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView service_type_TV;
        ImageView image_service_IV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image_service_IV=itemView.findViewById(R.id.image_service_IV);
            service_type_TV=itemView.findViewById(R.id.service_data);
        }
    }
}
