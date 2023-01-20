package com.cynoteck.petofyOPHR.response.getPetHospitalizationResponse;

public class HospitalizationType
{
    private String hospitalization;

    private String[] petHospitalizationsSurgeries;

    private String id;

    public String getHospitalization ()
    {
        return hospitalization;
    }

    public void setHospitalization (String hospitalization)
    {
        this.hospitalization = hospitalization;
    }

    public String[] getPetHospitalizationsSurgeries ()
    {
        return petHospitalizationsSurgeries;
    }

    public void setPetHospitalizationsSurgeries (String[] petHospitalizationsSurgeries)
    {
        this.petHospitalizationsSurgeries = petHospitalizationsSurgeries;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [hospitalization = "+hospitalization+", petHospitalizationsSurgeries = "+petHospitalizationsSurgeries+", id = "+id+"]";
    }
}
