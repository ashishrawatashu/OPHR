package com.cynoteck.petofyOPHR.params.onlineClinicVisitsParams;

public class OnlineClinicVisitisParameter {
    private String fromDate;
    private String toDate;

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "ClassPojo[ " +
                "fromDate= " + fromDate +
                ", toDate= " + toDate +
                "]";
    }
}
