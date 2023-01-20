package com.cynoteck.petofyOPHR.params.appointmentParams;

public class GetAppointmentFromDateRequest {
    private GetAppointmentFromDateParams data;

    public GetAppointmentFromDateParams getData() {
        return data;
    }

    public void setData(GetAppointmentFromDateParams data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ClassPojo [data = " + data + "]";
    }
}
