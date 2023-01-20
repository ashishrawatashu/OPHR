package com.cynoteck.petofyOPHR.params.saveVaccinationParameter;

public class SaveRequest {

    private SaveVaccineModel data;

    public SaveVaccineModel getData() {
        return data;
    }

    public void setData(SaveVaccineModel data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ClassPojo[" +
                "data=" + data +
                "]";
    }
}
