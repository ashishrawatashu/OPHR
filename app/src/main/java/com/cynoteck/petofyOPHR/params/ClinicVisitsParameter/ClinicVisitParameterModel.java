package com.cynoteck.petofyOPHR.params.ClinicVisitsParameter;

public class ClinicVisitParameterModel {
    private String fromDate;

    private String toDate;

    private String natureOfVisiteId;

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

    public String getNatureOfVisiteId() {
        return natureOfVisiteId;
    }

    public void setNatureOfVisiteId(String natureOfVisiteId) {
        this.natureOfVisiteId = natureOfVisiteId;
    }

    @Override
    public String toString() {
        return "ClassPojo[" +
                "fromDate= " + fromDate +
                ", toDate= " + toDate +
                ", natureOfVisiteId= " + natureOfVisiteId +
                "]";
    }
}
