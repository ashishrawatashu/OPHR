
package com.cynoteck.petofyOPHR.adapters;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.cynoteck.petofyOPHR.R;
        import com.cynoteck.petofyOPHR.response.getPetHospitalizationResponse.getHospitalizationListResponse.PetHospitalizationsList;
        import com.cynoteck.petofyOPHR.utils.ViewAndUpdateClickListener;

        import java.util.List;

public class UpdateHospitalizationAdapter extends RecyclerView.Adapter<UpdateHospitalizationAdapter.MyViewHolder> {

    Context context;
    List<PetHospitalizationsList> petHospitalizationsLists;
    ViewAndUpdateClickListener onProductItemClickListner;

    public UpdateHospitalizationAdapter(Context context, List<PetHospitalizationsList> petClinicVisitLists, ViewAndUpdateClickListener onProductItemClickListner) {
        this.context = context;
        this.petHospitalizationsLists = petClinicVisitLists;
        this.onProductItemClickListner = onProductItemClickListner;
    }

    @NonNull
    @Override
    public UpdateHospitalizationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.update_hospitalization_list, parent, false);
        UpdateHospitalizationAdapter.MyViewHolder vh = new UpdateHospitalizationAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull UpdateHospitalizationAdapter.MyViewHolder holder, int position) {
        holder.requesting_Vet_TV.setText(petHospitalizationsLists.get(position).getRequestingVeterinarian());
        holder.vet_phone_TV.setText(petHospitalizationsLists.get(position).getVeterinarianPhone());
        holder.hospital_type_TV.setText(petHospitalizationsLists.get(position).getHospitalizationType().getHospitalization());
        holder.hospital_name_TV.setText(petHospitalizationsLists.get(position).getHospitalName());
        holder.admission_date_TV.setText(petHospitalizationsLists.get(position).getAdmissionDate());

    }

    @Override
    public int getItemCount() {
        return petHospitalizationsLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView requesting_Vet_TV,vet_phone_TV,hospital_type_TV,hospital_name_TV,admission_date_TV,update_TV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            requesting_Vet_TV = itemView.findViewById(R.id.requesting_Vet_TV);
            vet_phone_TV = itemView.findViewById(R.id.vet_phone_TV);
            hospital_type_TV = itemView.findViewById(R.id.hospital_type_TV);
            hospital_name_TV = itemView.findViewById(R.id.hospital_name_TV);
            admission_date_TV = itemView.findViewById(R.id.admission_date_TV);
            update_TV=itemView.findViewById(R.id.update_TV);

            update_TV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onProductItemClickListner!=null){
                        onProductItemClickListner.onUpdateHospitalizationClick(getAdapterPosition());
                    }
                }
            });




        }
    }
}
