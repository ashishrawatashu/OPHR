package com.cynoteck.petofyOPHR.params.appointmentParams;

public class AppointmentStatusParams {
    private String id;

    private String status;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", status = "+status+"]";
    }
}

