package com.cynoteck.petofyOPHR.params.immunizationHistory;

public class ImmunizationHistoryRequest {
    private ImmunizationHistoryParametr data;

    public ImmunizationHistoryParametr getData() {
        return data;
    }

    public void setData(ImmunizationHistoryParametr data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ClassPojo[" +
                "data=" + data +
                "]";
    }
}
