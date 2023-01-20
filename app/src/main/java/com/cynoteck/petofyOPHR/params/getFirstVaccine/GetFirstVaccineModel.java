package com.cynoteck.petofyOPHR.params.getFirstVaccine;

public class GetFirstVaccineModel {
    private String categoryId;
    private String petId;

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
                "categoryId=" + categoryId +
                ", petId=" + petId +
                "]";
    }
}
