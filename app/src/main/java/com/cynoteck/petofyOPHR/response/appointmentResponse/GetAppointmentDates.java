package com.cynoteck.petofyOPHR.response.appointmentResponse;

import java.util.ArrayList;

public class GetAppointmentDates {
    private String appointmentNumber;

    private ArrayList<AppointmentList> appointmentList;

    private String appointmentDate;

    private String appointmentDay;

    public String getAppointmentNumber ()
    {
        return appointmentNumber;
    }

    public void setAppointmentNumber (String appointmentNumber)
    {
        this.appointmentNumber = appointmentNumber;
    }

    public ArrayList<AppointmentList> getAppointmentList ()
    {
        return appointmentList;
    }

    public void setAppointmentList (ArrayList<AppointmentList> appointmentList)
    {
        this.appointmentList = appointmentList;
    }

    public String getAppointmentDate ()
    {
        return appointmentDate;
    }

    public void setAppointmentDate (String appointmentDate)
    {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentDay ()
    {
        return appointmentDay;
    }

    public void setAppointmentDay (String appointmentDay)
    {
        this.appointmentDay = appointmentDay;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [appointmentNumber = "+appointmentNumber+", appointmentList = "+appointmentList+", appointmentDate = "+appointmentDate+", appointmentDay = "+appointmentDay+"]";
    }
}
