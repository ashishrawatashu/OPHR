package com.cynoteck.petofyOPHR.response.getVaccinationResponse;

public class VaccinationSchedule {
    private String id;
    private String petCategoryId;
    private String serialNumber;
    private String minimunAge;
    private String maximunAge;
    private String primaryVaccine;
    private String boosterOne;
    private String boosterOneDaysGap;
    private String boosterTwo;
    private String boosterTwoDaysGap;
    private String isPeriodicVaccine;
    private String vaccinationPeriod;
    private String veterinarianUserId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPetCategoryId() {
        return petCategoryId;
    }

    public void setPetCategoryId(String petCategoryId) {
        this.petCategoryId = petCategoryId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMinimunAge() {
        return minimunAge;
    }

    public void setMinimunAge(String minimunAge) {
        this.minimunAge = minimunAge;
    }

    public String getMaximunAge() {
        return maximunAge;
    }

    public void setMaximunAge(String maximunAge) {
        this.maximunAge = maximunAge;
    }

    public String getPrimaryVaccine() {
        return primaryVaccine;
    }

    public void setPrimaryVaccine(String primaryVaccine) {
        this.primaryVaccine = primaryVaccine;
    }

    public String getBoosterOne() {
        return boosterOne;
    }

    public void setBoosterOne(String boosterOne) {
        this.boosterOne = boosterOne;
    }

    public String getBoosterOneDaysGap() {
        return boosterOneDaysGap;
    }

    public void setBoosterOneDaysGap(String boosterOneDaysGap) {
        this.boosterOneDaysGap = boosterOneDaysGap;
    }

    public String getBoosterTwo() {
        return boosterTwo;
    }

    public void setBoosterTwo(String boosterTwo) {
        this.boosterTwo = boosterTwo;
    }

    public String getBoosterTwoDaysGap() {
        return boosterTwoDaysGap;
    }

    public void setBoosterTwoDaysGap(String boosterTwoDaysGap) {
        this.boosterTwoDaysGap = boosterTwoDaysGap;
    }

    public String getIsPeriodicVaccine() {
        return isPeriodicVaccine;
    }

    public void setIsPeriodicVaccine(String isPeriodicVaccine) {
        this.isPeriodicVaccine = isPeriodicVaccine;
    }

    public String getVaccinationPeriod() {
        return vaccinationPeriod;
    }

    public void setVaccinationPeriod(String vaccinationPeriod) {
        this.vaccinationPeriod = vaccinationPeriod;
    }

    public String getVeterinarianUserId() {
        return veterinarianUserId;
    }

    public void setVeterinarianUserId(String veterinarianUserId) {
        this.veterinarianUserId = veterinarianUserId;
    }

    @Override
    public String toString() {
        return "ClassPojo[" +
                "id= " + id +   
                ", petCategoryId= " + petCategoryId +   
                ", serialNumber= " + serialNumber +   
                ", minimunAge= " + minimunAge +   
                ", maximunAge= " + maximunAge +   
                ", primaryVaccine= " + primaryVaccine +   
                ", boosterOne= " + boosterOne +   
                ", boosterOneDaysGap= " + boosterOneDaysGap +   
                ", boosterTwo= " + boosterTwo +   
                ", boosterTwoDaysGap= " + boosterTwoDaysGap +   
                ", isPeriodicVaccine= " + isPeriodicVaccine +   
                ", vaccinationPeriod= " + vaccinationPeriod +   
                ", veterinarianUserId= " + veterinarianUserId +   
                "]";
    }
}
