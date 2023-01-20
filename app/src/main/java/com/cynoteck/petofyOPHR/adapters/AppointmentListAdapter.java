package com.cynoteck.petofyOPHR.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.response.appointmentResponse.AppointmentList;
import com.cynoteck.petofyOPHR.utils.AppointmentsClickListner;

import java.util.ArrayList;

public class AppointmentListAdapter extends RecyclerView.Adapter<AppointmentListAdapter.MyViewHolder> {

    ArrayList<AppointmentList> appointmentList;
    AppointmentsClickListner appointmentsClickListner;
    Context context;

    public AppointmentListAdapter(Context context,ArrayList<AppointmentList> appointmentList, AppointmentsClickListner appointmentsClickListner) {
        this.appointmentList = appointmentList;
        this.appointmentsClickListner = appointmentsClickListner;
        this.context = context;

    }

    @NonNull
    @Override
    public AppointmentListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list, parent, false);
        AppointmentListAdapter.MyViewHolder vh = new AppointmentListAdapter.MyViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentListAdapter.MyViewHolder holder, final int position)
    {

        if (appointmentList.get(position).getIsApproved().equals("true")){
            holder.join_BT.setVisibility(View.GONE);
            holder.payment_ststus_TV.setVisibility(View.GONE);
            if (appointmentList.get(position).getPaymentStatus().equals("true")){
                holder.join_BT.setVisibility(View.VISIBLE);
                holder.payment_ststus_TV.setVisibility(View.GONE);
            }else {
                holder.join_BT.setVisibility(View.GONE);
                holder.payment_ststus_TV.setVisibility(View.VISIBLE);
            }
        }else {
            holder.approve_BT.setVisibility(View.VISIBLE);
            holder.join_BT.setVisibility(View.GONE);
            holder.payment_ststus_TV.setVisibility(View.GONE);
        }


        holder.appoint_subject_TV.setText(appointmentList.get(position).getSubject());
        holder.timing_TV.setText(appointmentList.get(position).getStartDateString()+"-"+appointmentList.get(position).getEndDateString());
        holder.pet_parent_TV.setText(appointmentList.get(position).getPetParentName());

    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView appoint_subject_TV,timing_TV,pet_parent_TV,payment_ststus_TV;
        Button approve_BT,join_BT;
        CardView appointment_CV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            appoint_subject_TV = itemView.findViewById(R.id.appoint_subject_TV);
            timing_TV = itemView.findViewById(R.id.timing_TV);
            pet_parent_TV = itemView.findViewById(R.id.pet_parent_TV);
            approve_BT = itemView.findViewById(R.id.approve_BT);
            join_BT = itemView.findViewById(R.id.join_BT);
            appointment_CV=itemView.findViewById(R.id.appointment_CV);
            payment_ststus_TV=itemView.findViewById(R.id.payment_ststus_TV);

            appointment_CV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (appointmentsClickListner!=null){
                        appointmentsClickListner.onItemClick(getAdapterPosition(),appointmentList);
                    }
                }
            });

            join_BT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (appointmentsClickListner!=null){
                        appointmentsClickListner.onJoinClick(getAdapterPosition(),appointmentList,join_BT);
                    }
                }
            });

            approve_BT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (appointmentsClickListner!=null){
                        appointmentsClickListner.onApproveClick(getAdapterPosition(),appointmentList,approve_BT);
                    }
                }
            });

        }
    }
}
