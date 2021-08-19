package com.cynoteck.petofyOPHR.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.response.appointmentResponse.requestPendingResponse.RequestPendingData;
import com.cynoteck.petofyOPHR.utils.AppointmentsClickListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RequestPendingAdapter extends RecyclerView.Adapter<RequestPendingAdapter.MyViewHolder>   {

    ArrayList<RequestPendingData> requestPendingData;
    AppointmentsClickListener appointmentsClickListener;
    Context context;

    public RequestPendingAdapter(ArrayList<RequestPendingData> requestPendingData, Context context,AppointmentsClickListener appointmentsClickListener) {
        this.requestPendingData = requestPendingData;
        this.context = context;
        this.appointmentsClickListener = appointmentsClickListener;

    }

    @NonNull
    @Override
    public RequestPendingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_pending_list_layout, parent, false);
        RequestPendingAdapter.MyViewHolder vh = new RequestPendingAdapter.MyViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull RequestPendingAdapter.MyViewHolder holder, int position) {

        holder.parent_name_TV.setText(requestPendingData.get(position).getPetParentName());
        holder.visit_type_TV.setText(requestPendingData.get(position).getSubject());
        holder.appointment_timing_TV.setText(requestPendingData.get(position).getStartDateString()+"-"+requestPendingData.get(position).getEndDateString());



        if (requestPendingData.get(position).getIsApproved().equals("false")){
           holder.confirm_BT.setVisibility(View.VISIBLE);
           holder.payment_pending_LL.setVisibility(View.GONE);


//           holder.confirm_BT.setText("Booked");
//           holder.re_schedule_BT.setEnabled(false);
//           holder.confirm_BT.setEnabled(false);



        }else {
            holder.confirm_BT.setVisibility(View.GONE);
            holder.payment_pending_LL.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return requestPendingData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CircleImageView appointment_image_CIV;
        TextView visit_type_TV,appointment_timing_TV,parent_name_TV;
        Button re_schedule_BT,confirm_BT;
        RelativeLayout cancel_appointment_RL;
        LinearLayout payment_pending_LL;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            appointment_image_CIV = itemView.findViewById(R.id.appointment_image_CIV);
            visit_type_TV = itemView.findViewById(R.id.visit_type_TV);
            appointment_timing_TV = itemView.findViewById(R.id.appointment_timing_TV);
            parent_name_TV = itemView.findViewById(R.id.parent_name_TV);
            re_schedule_BT = itemView.findViewById(R.id.re_schedule_BT);
            confirm_BT = itemView.findViewById(R.id.confirm_BT);
            cancel_appointment_RL=itemView.findViewById(R.id.cancel_appointment_RL);
            payment_pending_LL = itemView.findViewById(R.id.payment_pending_LL);

//if(requestPendingData==null){
//    Log.d("TAG", "MyViewHolder: null");
//
//}
            confirm_BT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (appointmentsClickListener!=null){
                        appointmentsClickListener.onConfirmClick(getAdapterPosition(),requestPendingData);
                    }

                }
            });


            re_schedule_BT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (appointmentsClickListener!=null){
                        appointmentsClickListener.onReScheduleClickFromPending(getAdapterPosition(),requestPendingData);
                    }
                }
            });

            cancel_appointment_RL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (appointmentsClickListener!=null){
                        appointmentsClickListener.onCancelClick(getAdapterPosition(),requestPendingData);
                    }
                }
            });

        }
    }
}
