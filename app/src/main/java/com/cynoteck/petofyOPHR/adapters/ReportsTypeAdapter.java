package com.cynoteck.petofyOPHR.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.getPetClinicVisitsListsResponse.PetClinicVisitList;
import com.cynoteck.petofyOPHR.utils.ViewAndUpdateClickListener;

import java.util.List;

public class ReportsTypeAdapter extends RecyclerView.Adapter<ReportsTypeAdapter.MyViewHolder> {

    Context context;
    List<PetClinicVisitList> petClinicVisitLists;
    ViewAndUpdateClickListener onProductItemClickListner;

    public ReportsTypeAdapter(Context context, List<PetClinicVisitList> petClinicVisitLists, ViewAndUpdateClickListener onProductItemClickListner) {
        this.context = context;
        this.petClinicVisitLists = petClinicVisitLists;
        this.onProductItemClickListner = onProductItemClickListner;
    }

    @NonNull
    @Override
    public ReportsTypeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.reports_type_list, parent, false);
        ReportsTypeAdapter.MyViewHolder vh = new ReportsTypeAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ReportsTypeAdapter.MyViewHolder holder, int position) {
        holder.vetName_TV.setText(petClinicVisitLists.get(position).getVeterinarian());
        holder.vet_visit_date_TV.setText(petClinicVisitLists.get(position).getVisitDate());
        if (petClinicVisitLists.get(position).getDescription().equals("")) {
            holder.reason_of_visit_TV.setVisibility(View.INVISIBLE);
            holder.reason_TV.setVisibility(View.INVISIBLE);
            holder.reason_visit_dot_TV.setVisibility(View.INVISIBLE);
        } else {
            holder.reason_of_visit_TV.setVisibility(View.VISIBLE);
            holder.reason_TV.setVisibility(View.VISIBLE);
            holder.reason_visit_dot_TV.setVisibility(View.VISIBLE);
            holder.reason_of_visit_TV.setText(petClinicVisitLists.get(position).getDescription());
        }

    }

    @Override
    public int getItemCount() {
        return petClinicVisitLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView vetName_TV, vet_visit_date_TV, view_TV,reason_visit_dot_TV,reason_TV;
        TextView reason_of_visit_TV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            reason_visit_dot_TV = itemView.findViewById(R.id.reason_visit_dot_TV);
            reason_TV = itemView.findViewById(R.id.reason_TV);
            vetName_TV = itemView.findViewById(R.id.vetName_TV);
            vet_visit_date_TV = itemView.findViewById(R.id.vet_visit_date_TV);
            view_TV = itemView.findViewById(R.id.view_TV);
            reason_of_visit_TV = itemView.findViewById(R.id.reason_of_visit_TV);
            view_TV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onProductItemClickListner != null) {
                        onProductItemClickListner.onViewClick(getAdapterPosition());
                    }
                }
            });


        }
    }
}
