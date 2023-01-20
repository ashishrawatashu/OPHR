package com.cynoteck.petofyOPHR.response.immunizationVaccineType;

public class NextVaccination {
    private String nextVaccinationDate;
    private String vaccineName;
    private String vaccineType;

    public String getNextVaccinationDate() {
        return nextVaccinationDate;
    }

    public void setNextVaccinationDate(String nextVaccinationDate) {
        this.nextVaccinationDate = nextVaccinationDate;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

    @Override
    public String toString() {
        return "ClassPojo[" +
                "nextVaccinationDate= " + nextVaccinationDate +
                ", vaccineName= " + vaccineName +
                ", vaccineType= " + vaccineType +
                "]";
    }
}
