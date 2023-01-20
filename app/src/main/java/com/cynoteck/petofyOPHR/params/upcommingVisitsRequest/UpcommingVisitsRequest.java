package com.cynoteck.petofyOPHR.params.upcommingVisitsRequest;

public class UpcommingVisitsRequest {
    private UpcommingVisitsParams data;

    public UpcommingVisitsParams getData ()
    {
        return data;
    }

    public void setData (UpcommingVisitsParams data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+"]";
    }
}
