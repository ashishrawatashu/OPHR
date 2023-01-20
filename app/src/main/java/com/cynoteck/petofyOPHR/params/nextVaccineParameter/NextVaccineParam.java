package com.cynoteck.petofyOPHR.params.nextVaccineParameter;

public class NextVaccineParam {
    private String nextVaccinationDate;
    private String vaccineName;
    private String vaccineType;
    private String categoryId;
    private String petId;

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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    @Override
    public String toString() {
        return "ClassPojo[" +
                "nextVaccinationDate=" + nextVaccinationDate +
                ", vaccineName=" + vaccineName +
                ", vaccineType=" + vaccineType +
                ", categoryId=" + categoryId +
                ", petId=" + petId +
                "]";
    }
}
