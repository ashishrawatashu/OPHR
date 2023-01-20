package com.cynoteck.petofyOPHR.params.allStaffRequest;

public class ChangeStaffStatusRequest{
    private ChangeStaffStatusParams data;

    public ChangeStaffStatusParams getData ()
    {
        return data;
    }

    public void setData (ChangeStaffStatusParams data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+"]";
    }
}

