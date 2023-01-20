package com.cynoteck.petofyOPHR.response.updateProfileResponse;

public class PetTypesModel {

    private String id;
    private String petType1;
    private String isActive;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPetType1() {
        return petType1;
    }

    public void setPetType1(String petType1) {
        this.petType1 = petType1;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", petType1 = "+petType1+", isActive = "+isActive+"]";
    }

}
