package com.cynoteck.petofyOPHR.params.changePassRequest;

public class ChangePassRequest {

    private ChangePassParams data;

    public ChangePassParams getData ()
    {
        return data;
    }

    public void setData (ChangePassParams data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+"]";
    }
}