package com.cynoteck.petofyOPHR.response.getLabTestReportResponse;

public class LabType
{
    private String[] petLabwork;

    private String id;

    private String lab;

    public String[] getPetLabwork ()
    {
        return petLabwork;
    }

    public void setPetLabwork (String[] petLabwork)
    {
        this.petLabwork = petLabwork;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getLab ()
    {
        return lab;
    }

    public void setLab (String lab)
    {
        this.lab = lab;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [petLabwork = "+petLabwork+", id = "+id+", lab = "+lab+"]";
    }
}

