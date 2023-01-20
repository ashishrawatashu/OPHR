package com.cynoteck.petofyOPHR.params.updateClinicVisitsParams;

public class UpdateClinicReportsRequest {
    private UpdateClinicReportsParams data;

    public UpdateClinicReportsParams getAddPetParams() {
        return data;
    }

    public void setAddPetParams(UpdateClinicReportsParams data) {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+ data +"]";
    }
}
