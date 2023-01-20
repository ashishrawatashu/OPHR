package com.cynoteck.petofyOPHR.response.getPetReportsResponse;

public class NatureOfVisit {
    private String nature;

    private String[] petClinicVisit;

    private String id;

    public String getNature ()
    {
        return nature;
    }

    public void setNature (String nature)
    {
        this.nature = nature;
    }

    public String[] getPetClinicVisit ()
    {
        return petClinicVisit;
    }

    public void setPetClinicVisit (String[] petClinicVisit)
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
