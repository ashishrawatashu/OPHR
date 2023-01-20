package com.cynoteck.petofyOPHR.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.response.onlineClinicVisitResponse.VetAppointmentList;
import com.cynoteck.petofyOPHR.utils.ClinicOnlineVisitNotification;

import java.util.List;

public class ClinicVisitOnlineAdapter extends RecyclerView.Adapter<ClinicVisitOnlineAdapter.MyViewHolder> {

    Context context;
    List<VetAppointmentList> clinicVisitResponseData;
    List<VetAppointmentList> vetAppointmentLists;
    ClinicOnlineVisitNotification clinicOnlineVisitNotification;

    public ClinicVisitOnlineAdapter(Context context, List<VetAppointmentList> clinicVisitResponseData,List<VetAppointmentList> vetAppointmentLists,ClinicOnlineVisitNotification clinicOnlineVisitNotification) {
        this.context = context;
        this.clinicVisitResponseData = clinicVisitResponseData;
        this.clinicOnlineVisitNotification = clinicOnlineVisitNotification;
        this.vetAppointmentLists = vetAppointmentLists;

    }

    @NonNull
    @Override
    public ClinicVisitOnlineAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.online_clinic_visits, parent, false);
        ClinicVisitOnlineAdapter.MyViewHolder vh = new ClinicVisitOnlineAdapter.MyViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull ClinicVisitOnlineAdapter.MyViewHolder holder, int position) {

        holder.pet_name_TV.setText(clinicVisitResponseData.get(position).getPetName()+","+clinicVisitResponseData.get(position).getPetAge()+",\n"+clinicVisitResponseData.get(position).getPetUniqueId());
        holder.petParent_TV.setText(clinicVisitResponseData.get(position).getPetParentName()+"\n"+vetAppointmentLists.get(position).getPetParentContactNumber());
        holder.nextVisit_TV.setText(clinicVisitResponseData.get(position).getLastAppointmentdate());
        holder.typeOfVisit_TV.setText(vetAppointmentLists.get(position).getEventStartDate());

    }

    @Override
    public int getItemCount() {
        return clinicVisitResponseData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView pet_id_TV, pet_name_TV, petParent_TV,typeOfVisit_TV,lastVisit_TV,nextVisit_TV;
        Button notification;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nextVisit_TV = itemView.findViewById(R.id.nextVisit_TV);
            pet_id_TV = itemView.findViewById(R.id.pet_id_TV);
            pet_name_TV = itemView.findViewById(R.id.pet_name_TV);
            petParent_TV = itemView.findViewById(R.id.petParent_TV);
            typeOfVisit_TV = itemView.findViewById(R.id.typeOfVisit_TV);
            lastVisit_TV = itemView.findViewById(R.id.lastVisit_TV);
            notification = itemView.findViewById(R.id.notification);

            notification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clinicOnlineVisitNotification.ClickNotification(getAdapterPosition());
                }
            });

        }
    }
}
