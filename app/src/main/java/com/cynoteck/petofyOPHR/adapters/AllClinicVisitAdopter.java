package com.cynoteck.petofyOPHR.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.getPetClinicVisitsListsResponse.PetClinicVisitList;
import com.cynoteck.petofyOPHR.utils.ClinicOnlineVisitNotification;

import java.util.List;

public class AllClinicVisitAdopter extends RecyclerView.Adapter<AllClinicVisitAdopter.MyViewHolder> {
    Context context;
    List<PetClinicVisitList> clinicVisitResponseData;
    ClinicOnlineVisitNotification clinicOnlineVisitNotification;

    public AllClinicVisitAdopter(Context context, List<PetClinicVisitList> clinicVisitResponseData,ClinicOnlineVisitNotification clinicOnlineVisitNotification) {
        this.context = context;
        this.clinicVisitResponseData = clinicVisitResponseData;
        this.clinicOnlineVisitNotification = clinicOnlineVisitNotification;

    }

    @NonNull
    @Override
    public AllClinicVisitAdopter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_list, parent, false);
        AllClinicVisitAdopter.MyViewHolder vh = new AllClinicVisitAdopter.MyViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull AllClinicVisitAdopter.MyViewHolder holder, int position) {

        holder.pet_id_TV.setText(clinicVisitResponseData.get(position).getPetUniqueId());
        holder.pet_name_TV.setText(clinicVisitResponseData.get(position).getPetName());
        holder.petParent_TV.setText(clinicVisitResponseData.get(position).getPetParentName());
        holder.typeOfVisit_TV.setText(clinicVisitResponseData.get(position).getNatureOfVisit().getNature());
        holder.lastVisit_TV.setText(clinicVisitResponseData.get(position).getVisitDate());
        holder.nextVisit_TV.setText(clinicVisitResponseData.get(position).getFollowUpDate());

    }

    @Override
    public int getItemCount() {
        return clinicVisitResponseData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView pet_id_TV, pet_name_TV, petParent_TV,typeOfVisit_TV,lastVisit_TV,nextVisit_TV;
        LinearLayout send_reminder_LL;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nextVisit_TV = itemView.findViewById(R.id.nextVisit_TV);
            pet_id_TV = itemView.findViewById(R.id.pet_id_TV);
            pet_name_TV = itemView.findViewById(R.id.pet_name_TV);
            petParent_TV = itemView.findViewById(R.id.petParent_TV);
            typeOfVisit_TV = itemView.findViewById(R.id.typeOfVisit_TV);
            lastVisit_TV = itemView.findViewById(R.id.lastVisit_TV);
            send_reminder_LL = itemView.findViewById(R.id.send_reminder_LL);

            send_reminder_LL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clinicOnlineVisitNotification.ClickNotification(getAdapterPosition());
                }
            });

        }
    }

}
