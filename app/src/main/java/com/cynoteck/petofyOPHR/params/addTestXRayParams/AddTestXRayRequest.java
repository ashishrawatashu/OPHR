package com.cynoteck.petofyOPHR.params.addTestXRayParams;

public class AddTestXRayRequest {
    private AddTestXRayParams data;

    public AddTestXRayParams getData ()
    {
        return data;
    }

    public void setData (AddTestXRayParams data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+"]";
    }
}

