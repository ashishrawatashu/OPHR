package com.cynoteck.petofyOPHR.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.response.upcommingVisitsResponse.UpcommingVisitsData;

import java.util.List;

public class UpcomingVisitsAdapter extends RecyclerView.Adapter<UpcomingVisitsAdapter.MyViewHolder> {
    Context context;
    List<UpcommingVisitsData> upcommingVisitsData;

    public UpcomingVisitsAdapter(Context context, List<UpcommingVisitsData> upcommingVisitsData) {
        this.context = context;
        this.upcommingVisitsData = upcommingVisitsData;
    }

    @NonNull
    @Override
    public UpcomingVisitsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_list, parent, false);
        UpcomingVisitsAdapter.MyViewHolder vh = new UpcomingVisitsAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingVisitsAdapter.MyViewHolder holder, int position) {
        holder.pet_id_TV.setText(upcommingVisitsData.get(position).getPetUniqueId());
        holder.pet_name_TV.setText(upcommingVisitsData.get(position).getPetName());
        holder.petParent_TV.setText(upcommingVisitsData.get(position).getPetParentName());
        holder.typeOfVisit_TV.setText(upcommingVisitsData.get(position).getNatureOfVisit().getNature());
        holder.lastVisit_TV.setText(upcommingVisitsData.get(position).getVisitDate());
        holder.nextVisit_TV.setText(upcommingVisitsData.get(position).getFollowUpDate());

    }

    @Override
    public int getItemCount() {
        return upcommingVisitsData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView pet_id_TV, pet_name_TV, petParent_TV,typeOfVisit_TV,lastVisit_TV,nextVisit_TV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nextVisit_TV = itemView.findViewById(R.id.nextVisit_TV);
            pet_id_TV = itemView.findViewById(R.id.pet_id_TV);
            pet_name_TV = itemView.findViewById(R.id.pet_name_TV);
            petParent_TV = itemView.findViewById(R.id.petParent_TV);
            typeOfVisit_TV = itemView.findViewById(R.id.typeOfVisit_TV);
            lastVisit_TV = itemView.findViewById(R.id.lastVisit_TV);

        }
    }
}
