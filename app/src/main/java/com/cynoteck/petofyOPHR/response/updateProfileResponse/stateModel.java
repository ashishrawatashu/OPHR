package com.cynoteck.petofyOPHR.response.updateProfileResponse;

public class stateModel {
    private String id;

    private String stateName;

    private String countryId;

    private String isActive;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", stateName = "+stateName+", countryId = "+countryId+", isActive = "+isActive+"]";
    }

}
