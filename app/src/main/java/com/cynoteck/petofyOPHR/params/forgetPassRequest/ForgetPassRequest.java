package com.cynoteck.petofyOPHR.params.forgetPassRequest;

public class ForgetPassRequest {
    private ForgetPassDataParams data;

    public ForgetPassDataParams getData ()
    {
        return data;
    }

    public void setData (ForgetPassDataParams data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+"]";
    }
}
