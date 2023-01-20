package com.cynoteck.petofyOPHR.params.updateHospitalizationParams;

public class UpdateHospitalizationRequest {
    private UpdateHospitalizationParams data;

    public UpdateHospitalizationParams getData ()
    {
        return data;
    }

    public void setData (UpdateHospitalizationParams data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+"]";
    }
}
