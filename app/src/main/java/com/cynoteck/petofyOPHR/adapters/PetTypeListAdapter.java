package com.cynoteck.petofyOPHR.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.response.getPetDetailsResponse.PetTypeList;

import java.util.List;

public class PetTypeListAdapter extends RecyclerView.Adapter<PetTypeListAdapter.MyViewHolder> {
    Context context;
    List<PetTypeList> petTypeLists;

    public PetTypeListAdapter(Context context, List<PetTypeList> petTypeLists) {
        this.context = context;
        this.petTypeLists = petTypeLists;
    }

    @NonNull
    @Override
    public PetTypeListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_type_list, parent, false);
        PetTypeListAdapter.MyViewHolder vh = new PetTypeListAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PetTypeListAdapter.MyViewHolder holder, int position) {
        holder.type_data.setText(petTypeLists.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return petTypeLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView type_data;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            type_data=itemView.findViewById(R.id.pet_data);
        }
    }
}
