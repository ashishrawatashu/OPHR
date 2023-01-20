package com.cynoteck.petofyOPHR.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.response.appointmentResponse.GetAppointmentDates;
import com.cynoteck.petofyOPHR.utils.AppointmentsClickListner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DateListAdapter extends RecyclerView.Adapter<DateListAdapter.MyViewHolder>   {

    RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
    List<GetAppointmentDates> getAppointmentDates;
    AppointmentsClickListner appointmentsClickListner;
    Context context;

    public DateListAdapter(List<GetAppointmentDates> getAppointmentDates, Context context,AppointmentsClickListner appointmentsClickListner) {
        this.getAppointmentDates = getAppointmentDates;
        this.context = context;
        this.appointmentsClickListner = appointmentsClickListner;

    }

    @NonNull
    @Override
    public DateListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.appoint_date_list, parent, false);
        DateListAdapter.MyViewHolder vh = new DateListAdapter.MyViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull DateListAdapter.MyViewHolder holder, int position) {
        if (getAppointmentDates.get(position).getAppointmentList().isEmpty()){
            holder.empty_CV.setVisibility(View.VISIBLE);
        }else {
            holder.empty_CV.setVisibility(View.GONE);
        }


        holder.day_TV.setText(getAppointmentDates.get(position).getAppointmentDay().substring(0,3));
        if (position==0){
        holder.day_TV.setText("Today");
        }if (position==1){
        holder.day_TV.setText("Tomorrow");
        }
        Date date=new Date(getAppointmentDates.get(position).getAppointmentDate());
        SimpleDateFormat formatter5=new SimpleDateFormat("dd-MMM-yyyy h:mm:ss a");
        String formats1 = formatter5.format(date);
            holder.date_TV.setText(formats1.substring(0,6));
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.event_list.getContext(), LinearLayoutManager.VERTICAL, false);
            linearLayoutManager.setInitialPrefetchItemCount(getAppointmentDates.size());
            AppointmentListAdapter appointmentListAdapter = new AppointmentListAdapter(context,getAppointmentDates.get(position).getAppointmentList(),appointmentsClickListner);
            holder.event_list.setLayoutManager(linearLayoutManager);
            holder.event_list.setAdapter(appointmentListAdapter);
            holder.event_list.setRecycledViewPool(recycledViewPool);
            appointmentListAdapter.notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return getAppointmentDates.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView date_TV,day_TV;
        RecyclerView event_list;
        CardView empty_CV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            day_TV = itemView.findViewById(R.id.day_TV);
            date_TV = itemView.findViewById(R.id.date_TV);
            event_list = itemView.findViewById(R.id.event_list);
            empty_CV = itemView.findViewById(R.id.empty_CV);

        }
    }
}
