package com.cynoteck.petofyOPHR.params.vaccinationSaveParams;

public class VaccinationRequest {

    private VaccinationParameter data;

    public VaccinationParameter getData() {
        return data;
    }

    public void setData(VaccinationParameter data) {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+"]";
    }
}
