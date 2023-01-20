package com.cynoteck.petofyOPHR.response;

public class FollowUp {
    private String followUpTitle;

    private String id;

    private String[] petClinicVisitModel;

    public String getFollowUpTitle ()
    {
        return followUpTitle;
    }

    public void setFollowUpTitle (String followUpTitle)
    {
        this.followUpTitle = followUpTitle;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String[] getPetClinicVisitModel ()
    {
        return petClinicVisitModel;
    }

    public void setPetClinicVisitModel (String[] petClinicVisitModel)
    {
        this.petClinicVisitModel = petClinicVisitModel;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [followUpTitle = "+followUpTitle+", id = "+id+", petClinicVisitModel = "+petClinicVisitModel+"]";
    }
}

