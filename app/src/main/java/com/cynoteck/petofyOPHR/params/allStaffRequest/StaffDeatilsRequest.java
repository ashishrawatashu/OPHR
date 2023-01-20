package com.cynoteck.petofyOPHR.params.allStaffRequest;

public class StaffDeatilsRequest {
    private StaffDetailsParams data;

    public StaffDetailsParams getData ()
    {
        return data;
    }

    public void setData (StaffDetailsParams data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+"]";
    }
}

