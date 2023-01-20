package com.cynoteck.petofyOPHR.response.getPetReportsResponse.getClinicVisitDetails;

public class GetClinicVisitsDetailsData {
    private String isBack;

    private VeterinarianDetails veterinarianDetails;

    private PetClinicVisitDetails petClinicVisitDetails;

    private PetParentDetails petParentDetails;

    public String getIsBack ()
    {
        return isBack;
    }

    public void setIsBack (String isBack)
    {
        this.isBack = isBack;
    }

    public VeterinarianDetails getVeterinarianDetails ()
    {
        return veterinarianDetails;
    }

    public void setVeterinarianDetails (VeterinarianDetails veterinarianDetails)
    {
        this.veterinarianDetails = veterinarianDetails;
    }

    public PetClinicVisitDetails getPetClinicVisitDetails ()
    {
        return petClinicVisitDetails;
    }

    public void setPetClinicVisitDetails (PetClinicVisitDetails petClinicVisitDetails)
    {
        this.petClinicVisitDetails = petClinicVisitDetails;
    }

    public PetParentDetails getPetParentDetails ()
    {
        return petParentDetails;
    }

    public void setPetParentDetails (PetParentDetails petParentDetails)
    {
        this.petParentDetails = petParentDetails;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [isBack = "+isBack+", veterinarianDetails = "+veterinarianDetails+", petClinicVisitDetails = "+petClinicVisitDetails+", petParentDetails = "+petParentDetails+"]";
    }
}
