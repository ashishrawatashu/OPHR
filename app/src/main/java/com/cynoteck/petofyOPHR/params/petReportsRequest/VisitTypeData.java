package com.cynoteck.petofyOPHR.params.petReportsRequest;

public class VisitTypeData {

    private String petId;

    private String visitType;

    public String getPetId ()
    {
        return petId;
    }

    public void setPetId (String petId)
    {
        this.petId = petId;
    }

    public String getVisitType ()
    {
        return visitType;
    }

    public void setVisitType (String visitType)
    {
        this.visitType = visitType;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [petId = "+petId+", visitType = "+visitType+"]";
    }
}

