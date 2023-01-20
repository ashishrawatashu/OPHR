package com.cynoteck.petofyOPHR.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.response.getLabTestReportResponse.getPetLabWorkListResponse.PetLabWorkList;
import com.cynoteck.petofyOPHR.utils.ViewAndUpdateClickListener;

import java.util.List;

public class LabTestReportsAdapter extends RecyclerView.Adapter<LabTestReportsAdapter.MyViewHolder> {

    Context context;
    List<PetLabWorkList> petLabWorkLists;
    ViewAndUpdateClickListener onProductItemClickListner;

    public LabTestReportsAdapter(Context context, List<PetLabWorkList> petClinicVisitLists, ViewAndUpdateClickListener onProductItemClickListner) {
        this.context = context;
        this.petLabWorkLists = petClinicVisitLists;
        this.onProductItemClickListner = onProductItemClickListner;
    }

    @NonNull
    @Override
    public LabTestReportsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lab_test_reports_list, parent, false);
        LabTestReportsAdapter.MyViewHolder vh = new LabTestReportsAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull LabTestReportsAdapter.MyViewHolder holder, int position) {
        holder.requesting_Vet_TV.setText(petLabWorkLists.get(position).getRequestingVeterinarian());
        holder.vet_phone_TV.setText(petLabWorkLists.get(position).getVeterinarianPhone());
        holder.visit_date_TV.setText(petLabWorkLists.get(position).getVisitDate());
        holder.lab_type_TV.setText(petLabWorkLists.get(position).getLabType().getLab());
        holder.lab_name_TV.setText(petLabWorkLists.get(position).getNameOfLab());
    }

    @Override
    public int getItemCount() {
        return petLabWorkLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView requesting_Vet_TV,vet_phone_TV,visit_date_TV,lab_type_TV,lab_name_TV,view_TV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            requesting_Vet_TV = itemView.findViewById(R.id.requesting_Vet_TV);
            vet_phone_TV = itemView.findViewById(R.id.vet_phone_TV);
            visit_date_TV = itemView.findViewById(R.id.visit_date_TV);
            lab_type_TV = itemView.findViewById(R.id.lab_type_TV);
            lab_name_TV = itemView.findViewById(R.id.lab_name_TV);

            view_TV=itemView.findViewById(R.id.view_TV);

            view_TV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onProductItemClickListner!=null){
                        onProductItemClickListner.onViewLabTestReportsClick(getAdapterPosition());
                    }
                }
            });




        }
    }
}
