package com.cynoteck.petofyOPHR.params.allStaffRequest;

public class AddStaffRequest {
    private AddStaffParams data;

    public AddStaffParams getData ()
    {
        return data;
    }

    public void setData (AddStaffParams data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+"]";
    }
}

