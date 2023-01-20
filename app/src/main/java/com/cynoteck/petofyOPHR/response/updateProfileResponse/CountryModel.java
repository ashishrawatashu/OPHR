package com.cynoteck.petofyOPHR.response.updateProfileResponse;

public class CountryModel {
    private String id;
    private String countryName;
    private String isActive;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
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
        return "ClassPojo [id = "+id+", countryName = "+countryName+", isActive = "+isActive+"]";
    }

}
