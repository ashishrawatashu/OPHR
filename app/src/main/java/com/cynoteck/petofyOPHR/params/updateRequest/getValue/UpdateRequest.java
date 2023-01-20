package com.cynoteck.petofyOPHR.params.updateRequest.getValue;

public class UpdateRequest {
    private UpdateParams data;

    public UpdateParams getData ()
    {
        return data;
    }

    public void setData (UpdateParams data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+"]";
    }
}


