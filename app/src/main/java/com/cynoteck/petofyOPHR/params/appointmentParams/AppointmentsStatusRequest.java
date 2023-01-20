package com.cynoteck.petofyOPHR.params.appointmentParams;

public class AppointmentsStatusRequest{
    private AppointmentStatusParams data;

    public AppointmentStatusParams getData ()
    {
        return data;
    }

    public void setData (AppointmentStatusParams data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+"]";
    }
}

