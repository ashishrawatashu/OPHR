package com.cynoteck.petofyOPHR.response.updateProfileResponse;

public class PetServiceModel {
    private int id;
    private String serviceType1;
    private boolean isActive;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceType1() {
        return serviceType1;
    }

    public void setServiceType1(String serviceType1) {
        this.serviceType1 = serviceType1;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", serviceType1 = "+serviceType1+", isActive = "+isActive+"]";
    }
}
