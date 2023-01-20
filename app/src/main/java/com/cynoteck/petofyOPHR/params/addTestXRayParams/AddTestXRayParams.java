package com.cynoteck.petofyOPHR.params.addTestXRayParams;

public class AddTestXRayParams {
    private String followUpDate;

    private String petId;

    private String typeOfTestId;

    private String documents;

    private String dateTested;

    private String followUpId;

    private String results;

    public String getFollowUpDate ()
    {
        return followUpDate;
    }

    public void setFollowUpDate (String followUpDate)
    {
        this.followUpDate = followUpDate;
    }

    public String getPetId ()
    {
        return petId;
    }

    public void setPetId (String petId)
    {
        this.petId = petId;
    }

    public String getTypeOfTestId ()
    {
        return typeOfTestId;
    }

    public void setTypeOfTestId (String typeOfTestId)
    {
        this.typeOfTestId = typeOfTestId;
    }

    public String getDocuments ()
    {
        return documents;
    }

    public void setDocuments (String documents)
    {
        this.documents = documents;
    }

    public String getDateTested ()
    {
        return dateTested;
    }

    public void setDateTested (String dateTested)
    {
        this.dateTested = dateTested;
    }

    public String getFollowUpId ()
    {
        return followUpId;
    }

    public void setFollowUpId (String followUpId)
    {
        this.followUpId = followUpId;
    }

    public String getResults ()
    {
        return results;
    }

    public void setResults (String results)
    {
        this.results = results;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [followUpDate = "+followUpDate+", petId = "+petId+", typeOfTestId = "+typeOfTestId+", documents = "+documents+", dateTested = "+dateTested+", followUpId = "+followUpId+", results = "+results+"]";
    }
}

