package com.cynoteck.petofyOPHR.params.updateRequest.updateParamRequest;


public class UpdatePetRequest {
    private UpdatePetParam data;

    public UpdatePetParam getAddPetParams() {
        return data;
    }

    public void setAddPetParams(UpdatePetParam data) {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+ data +"]";
    }
}
