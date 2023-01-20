package com.cynoteck.petofyOPHR.params.petReportsRequest;

public class PetClinicVisitDetailsRequest
{
    private PetClinicVistsDetailsParams data;

    public PetClinicVistsDetailsParams getData ()
    {
        return data;
    }

    public void setData (PetClinicVistsDetailsParams data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+"]";
    }
}
