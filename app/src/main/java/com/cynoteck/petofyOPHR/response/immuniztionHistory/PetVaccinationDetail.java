package com.cynoteck.petofyOPHR.response.immuniztionHistory;

public class PetVaccinationDetail {
    private String id;
    private String vaccineType;
    private String brandName;
    private String vaccine;
    private String immunizationDate;
    private String petAgeInDays;
    private String petClinicVisitId;
    private String petId;
    private String petClinicVisitId1;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public String getImmunizationDate() {
        return immunizationDate;
    }

    public void setImmunizationDate(String immunizationDate) {
        this.immunizationDate = immunizationDate;
    }

    public String getPetAgeInDays() {
        return petAgeInDays;
    }

    public void setPetAgeInDays(String petAgeInDays) {
        this.petAgeInDays = petAgeInDays;
    }

    public String getPetClinicVisitId() {
        return petClinicVisitId;
    }

    public void setPetClinicVisitId(String petClinicVisitId) {
        this.petClinicVisitId = petClinicVisitId;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getPetClinicVisitId1() {
        return petClinicVisitId1;
    }

    public void setPetClinicVisitId1(String petClinicVisitId1) {
        this.petClinicVisitId1 = petClinicVisitId1;
    }

    @Override
    public String toString() {
        return "ClassPojo[" +
                "id=" + id + 
                ", vaccineType=" + vaccineType + 
                ", brandName=" + brandName + 
                ", vaccine=" + vaccine + 
                ", immunizationDate=" + immunizationDate + 
                ", petAgeInDays=" + petAgeInDays + 
                ", petClinicVisitId=" + petClinicVisitId + 
                ", petId=" + petId + 
                ", petClinicVisitId1=" + petClinicVisitId1 + 
                "]";
    }
}
