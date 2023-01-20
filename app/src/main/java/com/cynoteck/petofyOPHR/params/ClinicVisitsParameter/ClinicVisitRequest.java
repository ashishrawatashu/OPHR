package com.cynoteck.petofyOPHR.params.ClinicVisitsParameter;

public class ClinicVisitRequest {
    private ClinicVisitParameterModel data;

    public ClinicVisitParameterModel getData() {
        return data;
    }

    public void setData(ClinicVisitParameterModel data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ClassPojo[" +
                "data=" + data +
                "]";
    }
}
