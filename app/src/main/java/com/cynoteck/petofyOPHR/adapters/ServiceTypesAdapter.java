package com.cynoteck.petofyOPHR.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.response.updateProfileResponse.PetServiceModel;
import com.cynoteck.petofyOPHR.utils.ServiceTypeClicks;

import java.util.ArrayList;

public class ServiceTypesAdapter extends RecyclerView.Adapter<ServiceTypesAdapter.MyViewHolder> {

    ArrayList<PetServiceModel> petServiceModels;
    ServiceTypeClicks onServiceTypeClicks;
    Context context;

    public ServiceTypesAdapter(Context context, ArrayList<PetServiceModel> petServiceModels, ServiceTypeClicks onServiceTypeClicks) {
        this.petServiceModels           = petServiceModels;
        this.onServiceTypeClicks    = onServiceTypeClicks;
        this.context            = context;
    }

    @NonNull
    @Override
    public ServiceTypesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.check_box_layout, parent, false);
        ServiceTypesAdapter.MyViewHolder vh = new ServiceTypesAdapter.MyViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull ServiceTypesAdapter.MyViewHolder holder, final int position) {
        holder.check_box_id.setText(petServiceModels.get(position).getServiceType1());
        if (petServiceModels.get(position).getId()==1.0){
            holder.check_box_id.setChecked(true);
            holder.check_box_id.setClickable(false);
            holder.check_box_id.setFocusable(false);
            petServiceModels.get(position).setIsActive(true);
        }
        if (petServiceModels.get(position).getIsActive()){
            holder.check_box_id.setChecked(true);
        }else {
            holder.check_box_id.setChecked(false);
        }


    }

    @Override
    public int getItemCount() {
        return petServiceModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatCheckBox check_box_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            check_box_id   = itemView.findViewById(R.id.check_box_id);
            check_box_id.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onServiceTypeClicks!=null){
                        if (check_box_id.isChecked()){
                            petServiceModels.get(getAdapterPosition()).setIsActive(true);
                            onServiceTypeClicks.onServiceTypeClicks(getAdapterPosition(),true);
                        }else {
                            petServiceModels.get(getAdapterPosition()).setIsActive(false);
                            onServiceTypeClicks.onServiceTypeClicks(getAdapterPosition(),false);
                        }
                    }
                }
            });


        }
    }
}
