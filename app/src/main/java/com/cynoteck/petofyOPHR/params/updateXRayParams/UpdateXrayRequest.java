package com.cynoteck.petofyOPHR.params.updateXRayParams;

public class UpdateXrayRequest {
    private UpdateXRayParams data;

    public UpdateXRayParams getData ()
    {
        return data;
    }

    public void setData (UpdateXRayParams data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+"]";
    }
}