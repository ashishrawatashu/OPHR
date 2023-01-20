package com.cynoteck.petofyOPHR.response.getPetReportsResponse;

import java.util.ArrayList;

public class GetReportsTypeData {
    private String nature;

    private ArrayList<String> petClinicVisit;

    private String id;

    public String getNature ()
    {
        return nature;
    }

    public void setNature (String nature)
    {
        this.nature = nature;
    }

    public ArrayList<String> getPetClinicVisit ()
    {
        return petClinicVisit;
    }

    public void setPetClinicVisit (ArrayList<String> petClinicVisit)
    {
        this.petClinicVisit = petClinicVisit;
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
        return "ClassPojo [nature = "+nature+", petClinicVisit = "+petClinicVisit+", id = "+id+"]";
    }
}
