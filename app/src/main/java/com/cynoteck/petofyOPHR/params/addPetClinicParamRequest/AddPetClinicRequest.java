package com.cynoteck.petofyOPHR.params.addPetClinicParamRequest;

public class AddPetClinicRequest {
    private AddPetClinicParam data;

    public AddPetClinicParam getAddPetParams() {
        return data;
    }

    public void setAddPetParams(AddPetClinicParam data) {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+ data +"]";
    }
}
