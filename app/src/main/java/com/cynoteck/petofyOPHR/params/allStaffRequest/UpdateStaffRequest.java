package com.cynoteck.petofyOPHR.params.allStaffRequest;

public class UpdateStaffRequest {
    private UpdateStaffParams data;

    public UpdateStaffParams getData ()
    {
        return data;
    }

    public void setData (UpdateStaffParams data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+"]";
    }
}

