package com.cynoteck.petofyOPHR.utils;

import android.widget.Button;

import com.cynoteck.petofyOPHR.response.appointmentResponse.AppointmentList;

import java.util.ArrayList;

public interface AppointmentsClickListner {
    public void onItemClick(int position, ArrayList<AppointmentList> appointmentLists);
    public void onJoinClick(int position,ArrayList<AppointmentList> appointmentLists, Button button);
    public void onApproveClick(int position,ArrayList<AppointmentList> appointmentLists, Button button);
    

}
