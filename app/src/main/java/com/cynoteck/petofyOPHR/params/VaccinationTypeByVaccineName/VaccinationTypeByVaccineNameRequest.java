package com.cynoteck.petofyOPHR.params.VaccinationTypeByVaccineName;

public class VaccinationTypeByVaccineNameRequest {
    private VaccinationTypeByVaccineNameParams data;

    public VaccinationTypeByVaccineNameParams getData() {
        return data;
    }

    public void setData(VaccinationTypeByVaccineNameParams data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ClassPojo [data = " + data + "]";
    }
}
