package com.cynoteck.petofyOPHR.params.updateLapTestParams;

public class UpdateLabTestRequest {
    private UpdateLabTestParams data;

    public UpdateLabTestParams getAddPetParams() {
        return data;
    }

    public void setAddPetParams(UpdateLabTestParams data) {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+ data +"]";
    }
}
