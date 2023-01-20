package com.cynoteck.petofyOPHR.params.addLabRequest;

public class AddLabRequest {
    private AddLabParams data;

    public AddLabParams getAddPetParams() {
        return data;
    }

    public void setAddPetParams(AddLabParams data) {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+ data +"]";
    }
}
