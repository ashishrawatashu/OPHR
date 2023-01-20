package com.cynoteck.petofyOPHR.response.updateProfileResponse;

public class CityModel {
    private String city1;

    private String stateId;

    private String stateList;

    private String id;

    private String isActive;

    public String getCity1 ()
    {
        return city1;
    }

    public void setCity1 (String city1)
    {
        this.city1 = city1;
    }

    public String getStateId ()
    {
        return stateId;
    }

    public void setStateId (String stateId)
    {
        this.stateId = stateId;
    }

    public String getStateList ()
    {
        return stateList;
    }

    public void setStateList (String stateList)
    {
        this.stateList = stateList;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getIsActive ()
    {
        return isActive;
    }

    public void setIsActive (String isActive)
    {
        this.isActive = isActive;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [city1 = "+city1+", stateId = "+stateId+", stateList = "+stateList+", id = "+id+", isActive = "+isActive+"]";
    }
}
