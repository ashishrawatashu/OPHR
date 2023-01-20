package com.cynoteck.petofyOPHR.params.nextVaccineParameter;

public class NextVaccineRequest {
    private NextVaccineParam data;

    public NextVaccineParam getData() {
        return data;
    }

    public void setData(NextVaccineParam data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ClassPojo[" +
                "data=" + data +
                "]";
    }
}
