package com.cynoteck.petofyOPHR.response.searchRemaks;

public class SearchRemarksModel {
    
    private String id;
    private String composition;
    private String dosePerKgBodyWeight;
    private String doseFrom;
    private String brands;
    private String remarks;
    private String category;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getDosePerKgBodyWeight() {
        return dosePerKgBodyWeight;
    }

    public void setDosePerKgBodyWeight(String dosePerKgBodyWeight) {
        this.dosePerKgBodyWeight = dosePerKgBodyWeight;
    }

    public String getDoseFrom() {
        return doseFrom;
    }

    public void setDoseFrom(String doseFrom) {
        this.doseFrom = doseFrom;
    }

    public String getBrands() {
        return brands;
    }

    public void setBrands(String brands) {
        this.brands = brands;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return "ClassPojo[id= " + id +", composition= " + composition + ", dosePerKgBodyWeight= " + dosePerKgBodyWeight +", doseFrom= " + doseFrom + ", brands= " + brands +", remarks= " + remarks + ", category= " + category +"]";
    }
}
