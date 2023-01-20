package com.cynoteck.petofyOPHR.params.getMyVisitPetRecordRequest;

public class GetMyVisistPetRecordRequest {
    private GetMyVisistPetRecordParams data;

    public GetMyVisistPetRecordParams getData ()
    {
        return data;
    }

    public void setData (GetMyVisistPetRecordParams data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+"]";
    }
}


