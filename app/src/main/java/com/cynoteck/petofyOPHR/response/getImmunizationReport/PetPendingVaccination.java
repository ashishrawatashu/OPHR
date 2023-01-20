package com.cynoteck.petofyOPHR.response.getImmunizationReport;

public class PetPendingVaccination {
    private String serialnumber;
    private String vaccineName;
    private String vaccineType;
    private String vaccineDisplayType;
    private String days;
    private String isVaccinated;
    private String vaccinationDate;
    private String actualVaccinationStatus;
    private String nextVaccinationDate;
    private String isAlreadyVaccinated;

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
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

    public String getVaccineDisplayType() {
        return vaccineDisplayType;
    }

    public void setVaccineDisplayType(String vaccineDisplayType) {
        this.vaccineDisplayType = vaccineDisplayType;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getIsVaccinated() {
        return isVaccinated;
    }

    public void setIsVaccinated(String isVaccinated) {
        this.isVaccinated = isVaccinated;
    }

    public String getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(String vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public String getActualVaccinationStatus() {
        return actualVaccinationStatus;
    }

    public void setActualVaccinationStatus(String actualVaccinationStatus) {
        this.actualVaccinationStatus = actualVaccinationStatus;
    }

    public String getNextVaccinationDate() {
        return nextVaccinationDate;
    }

    public void setNextVaccinationDate(String nextVaccinationDate) {
        this.nextVaccinationDate = nextVaccinationDate;
    }

    public String getIsAlreadyVaccinated() {
        return isAlreadyVaccinated;
    }

    public void setIsAlreadyVaccinated(String isAlreadyVaccinated) {
        this.isAlreadyVaccinated = isAlreadyVaccinated;
    }

    @Override
    public String toString() {
        return "ClassPojo[" +
                "serialnumber=" + serialnumber +
                ", vaccineName=" + vaccineName +
                ", vaccineType=" + vaccineType +
                ", vaccineDisplayType=" + vaccineDisplayType +
                ", days=" + days +
                ", isVaccinated=" + isVaccinated +
                ", vaccinationDate=" + vaccinationDate +
                ", actualVaccinationStatus=" + actualVaccinationStatus +
                ", nextVaccinationDate=" + nextVaccinationDate +
                ", isAlreadyVaccinated=" + isAlreadyVaccinated +
                "]";
    }
}
