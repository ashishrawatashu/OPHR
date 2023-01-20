package com.cynoteck.petofyOPHR.response.getFirstVaccineReponse;

public class GetFirstVaccineResponseModel {
    
    private String nextVaccinationDate;
    private String nextDate;
    private String vaccineName;
    private String vaccineType;
    private String vaccinationStatusMessage;
    private String isVaccinated;

    public String getNextVaccinationDate() {
        return nextVaccinationDate;
    }

    public void setNextVaccinationDate(String nextVaccinationDate) {
        this.nextVaccinationDate = nextVaccinationDate;
    }

    public String getNextDate() {
        return nextDate;
    }

    public void setNextDate(String nextDate) {
        this.nextDate = nextDate;
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

    public String getVaccinationStatusMessage() {
        return vaccinationStatusMessage;
    }

    public void setVaccinationStatusMessage(String vaccinationStatusMessage) {
        this.vaccinationStatusMessage = vaccinationStatusMessage;
    }

    public String getIsVaccinated() {
        return isVaccinated;
    }

    public void setIsVaccinated(String isVaccinated) {
        this.isVaccinated = isVaccinated;
    }

    @Override
    public String toString() {
        return "ClassPojp[" +
                "nextVaccinationDate=" + nextVaccinationDate +
                ", nextDate=" + nextDate +
                ", vaccineName=" + vaccineName +
                ", vaccineType=" + vaccineType +
                ", vaccinationStatusMessage=" + vaccinationStatusMessage +
                ", isVaccinated=" + isVaccinated +
                "]";
    }
}
