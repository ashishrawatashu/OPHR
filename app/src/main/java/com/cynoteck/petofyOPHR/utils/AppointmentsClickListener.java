package com.cynoteck.petofyOPHR.utils;

import com.cynoteck.petofyOPHR.response.appointmentResponse.requestPendingResponse.RequestPendingData;
import com.cynoteck.petofyOPHR.response.appointmentResponse.upComingAppointmentResponse.UpcomingAppointmentData;

import java.util.ArrayList;

public interface AppointmentsClickListener {

    public void onJoinClick(int position, ArrayList<UpcomingAppointmentData> appointmentLists);
    public void onReScheduleClickFromUpcoming(int position, ArrayList<UpcomingAppointmentData> appointmentLists);

    public void onConfirmClick(int position, ArrayList<RequestPendingData> requestPendingData);
    public void onCancelClick(int position, ArrayList<RequestPendingData> requestPendingData);
    public void onReScheduleClickFromPending(int position, ArrayList<RequestPendingData> appointmentLists);


}
