package com.cynoteck.petofyOPHR.params.saveVaccinationParameter;

public class SaveVaccineModel {
    private String vaccineName;
    private String vaccineType;
    private String vaccinationDate;
    private String petId;
    private String petClinicVisitId;

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

    public String getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(String vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getPetClinicVisitId() {
        return petClinicVisitId;
    }

    public void setPetClinicVisitId(String petClinicVisitId) {
        this.petClinicVisitId = petClinicVisitId;
    }

    @Override
    public String toString() {
        return "ClassPojo[" +
                "vaccineName=" + vaccineName +
                ", vaccineType=" + vaccineType + 
                ", vaccinationDate=" + vaccinationDate + 
                ", petId=" + petId + 
                ", petClinicVisitId=" + petClinicVisitId + 
                "]";
    }
}
