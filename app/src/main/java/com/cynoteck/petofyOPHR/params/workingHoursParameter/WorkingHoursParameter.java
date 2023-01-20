package com.cynoteck.petofyOPHR.params.workingHoursParameter;

import com.google.gson.JsonArray;

public class WorkingHoursParameter {
    private JsonArray data=null;

    public JsonArray getData ()
    {
        return data;
    }

    public void setData (JsonArray data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+"]";
    }
}
