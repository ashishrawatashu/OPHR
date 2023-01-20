package com.cynoteck.petofyOPHR.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.response.appointmentResponse.upComingAppointmentResponse.UpcomingAppointmentData;
import com.cynoteck.petofyOPHR.utils.AppointmentsClickListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpcomingAppointmentsAdapter extends RecyclerView.Adapter<UpcomingAppointmentsAdapter.MyViewHolder> {

    ArrayList<UpcomingAppointmentData> upcomingAppointmentData;
    AppointmentsClickListener appointmentsClickListener;
    Context context;

    public UpcomingAppointmentsAdapter(ArrayList<UpcomingAppointmentData> upcomingAppointmentData, Context context, AppointmentsClickListener appointmentsClickListener) {
        this.upcomingAppointmentData = upcomingAppointmentData;
        this.context = context;
        this.appointmentsClickListener = appointmentsClickListener;

    }

    @NonNull
    @Override
    public UpcomingAppointmentsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_appointment_list_layout, parent, false);
        UpcomingAppointmentsAdapter.MyViewHolder vh = new UpcomingAppointmentsAdapter.MyViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingAppointmentsAdapter.MyViewHolder holder, int position) {

        holder.parent_name_TV.setText(upcomingAppointmentData.get(position).getPetParentName());
        holder.visit_type_TV.setText(upcomingAppointmentData.get(position).getSubject());
        holder.appointment_timing_TV.setText(upcomingAppointmentData.get(position).getStartDateString() + "-" + upcomingAppointmentData.get(position).getEndDateString());

        if (upcomingAppointmentData.get(position).getIsVideoCall().equals("true")){
            holder.is_video_call_RL.setVisibility(View.VISIBLE);
        }else {
            holder.is_video_call_RL.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return upcomingAppointmentData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CircleImageView appointment_image_CIV;
        TextView visit_type_TV, appointment_timing_TV, parent_name_TV;
        Button re_schedule_BT, join_BT;
        RelativeLayout is_video_call_RL;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            appointment_image_CIV = itemView.findViewById(R.id.appointment_image_CIV);
            visit_type_TV = itemView.findViewById(R.id.visit_type_TV);
            appointment_timing_TV = itemView.findViewById(R.id.appointment_timing_TV);
            parent_name_TV = itemView.findViewById(R.id.parent_name_TV);
            re_schedule_BT = itemView.findViewById(R.id.re_schedule_BT);
            join_BT = itemView.findViewById(R.id.join_BT);
            is_video_call_RL=itemView.findViewById(R.id.is_video_call_RL);

            join_BT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (appointmentsClickListener != null) {
                        appointmentsClickListener.onJoinClick(getAdapterPosition(), upcomingAppointmentData);
                    }
                }
            });

            re_schedule_BT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (appointmentsClickListener != null) {
                        appointmentsClickListener.onReScheduleClickFromUpcoming(getAdapterPosition(), upcomingAppointmentData);
                    }
                }
            });


        }
    }
}
