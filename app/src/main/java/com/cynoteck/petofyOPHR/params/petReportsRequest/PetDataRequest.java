package com.cynoteck.petofyOPHR.params.petReportsRequest;

public class PetDataRequest {
    private PetDataParams data;

    public PetDataParams getData ()
    {
        return data;
    }

    public void setData (PetDataParams data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+"]";
    }

}