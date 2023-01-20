package com.cynoteck.petofyOPHR.response.InPetVeterian;

public class InPetModel {
    private String success;
    private String petId;
    private String petParentContactNumber;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getPetParentContactNumber() {
        return petParentContactNumber;
    }

    public void setPetParentContactNumber(String petParentContactNumber) {
        this.petParentContactNumber = petParentContactNumber;
    }

    @Override
    public String toString() {
        return "ClassPojo [success = "+success+", petId= "+petId+", petParentContactNumber= "+petParentContactNumber+"]";

    }
}
