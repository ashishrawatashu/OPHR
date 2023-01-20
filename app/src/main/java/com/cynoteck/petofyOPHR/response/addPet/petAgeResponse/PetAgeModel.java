package com.cynoteck.petofyOPHR.response.addPet.petAgeResponse;

import java.util.List;

public class PetAgeModel {
    private String id;
    private String age;
    private String isActive;
    private String petCategoryId;
    private String petCategory;
    private List<String> petDetail = null;
    private List<String> petDonation = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getPetCategoryId() {
        return petCategoryId;
    }

    public void setPetCategoryId(String petCategoryId) {
        this.petCategoryId = petCategoryId;
    }

    public String getPetCategory() {
        return petCategory;
    }

    public void setPetCategory(String petCategory) {
        this.petCategory = petCategory;
    }

    public List<String> getPetDetail() {
        return petDetail;
    }

    public void setPetDetail(List<String> petDetail) {
        this.petDetail = petDetail;
    }

    public List<String> getPetDonation() {
        return petDonation;
    }

    public void setPetDonation(List<String> petDonation) {
        this.petDonation = petDonation;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", age = "+age+", isActive= "+isActive+", petCategoryId= "+petCategoryId+", " +
                "petCategory= "+petCategory+", petDetail= "+petDetail+", petDonation= "+petDonation+"]";
    }

}
