package com.cynoteck.petofyOPHR.response.getVaccinationResponse;

import java.util.ArrayList;

public class GetVaccineResponseModel {
    
    private VaccinationSchedule _VaccinationSchedule;
    private ArrayList<VaccineChartDetail> vaccineChartDetails = null;
    private String petVaccinationScheduleAssociation;

    public VaccinationSchedule getVaccinationSchedule() {
        return _VaccinationSchedule;
    }

    public void setVaccinationSchedule(VaccinationSchedule _VaccinationSchedule) {
        this._VaccinationSchedule = _VaccinationSchedule;
    }

    public ArrayList<VaccineChartDetail> getVaccineChartDetails() {
        return vaccineChartDetails;
    }

    public void setVaccineChartDetails(ArrayList<VaccineChartDetail> vaccineChartDetails) {
        this.vaccineChartDetails = vaccineChartDetails;
    }

    public String getPetVaccinationScheduleAssociation() {
        return petVaccinationScheduleAssociation;
    }

    public void setPetVaccinationScheduleAssociation(String petVaccinationScheduleAssociation) {
        this.petVaccinationScheduleAssociation = petVaccinationScheduleAssociation;
    }

    public String toString() {
        return "ClassPojo[" +
                "_VaccinationSchedule=" + _VaccinationSchedule +
                ", vaccineChartDetails=" + vaccineChartDetails +
                ", petVaccinationScheduleAssociation=" + petVaccinationScheduleAssociation +
                "]";
    }

}
