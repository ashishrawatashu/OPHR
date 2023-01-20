package com.cynoteck.petofyOPHR.params.petReportsRequest;

public class VisitTypeRequest {
    private VisitTypeData data;

    private PetDataParams header;

    public VisitTypeData getData ()
    {
        return data;
    }

    public void setData (VisitTypeData data)
    {
        this.data = data;
    }

    public PetDataParams getHeader ()
    {
        return header;
    }

    public void setHeader (PetDataParams header)
    {
        this.header = header;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+", header = "+header+"]";
    }
}
