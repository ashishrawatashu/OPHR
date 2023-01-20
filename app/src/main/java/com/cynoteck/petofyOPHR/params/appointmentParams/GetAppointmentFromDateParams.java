package com.cynoteck.petofyOPHR.params.appointmentParams;

public class GetAppointmentFromDateParams {
    private String fromDate;
    private String toDate;

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }


    @Override
    public String toString() {
        return "ClassPojo [fromDate = " + fromDate + "toDate="+toDate+"]";
    }
}
