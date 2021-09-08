package com.cynoteck.petofyOPHR.params.addPetClinicParamRequest;

public class VaccinationModel {
    private String immunizationDate;
    private String vaccine;
    private String brandName;
    private String id;
    private String vaccineType;
    private String vaccineDose;

    public String getImmunizationDate() {
        return immunizationDate;
    }

    public void setImmunizationDate(String immunizationDate) {
        this.immunizationDate = immunizationDate;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

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

    public String getVaccineDose() {
        return vaccineDose;
    }

    public void setVaccineDose(String vaccineDose) {
        this.vaccineDose = vaccineDose;
    }

}
