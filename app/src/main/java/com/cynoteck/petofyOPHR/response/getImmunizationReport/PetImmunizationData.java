package com.cynoteck.petofyOPHR.response.getImmunizationReport;

import java.util.ArrayList;
import java.util.List;

public class PetImmunizationData {
    private String isBack;

    private ArrayList<PetImmunizationDetailModels> petImmunizationDetailModels;

    private String petDetails;

    private String veterinarianDetails;

    private String visitDate;

    private String petParentDetails;

    private List<Object> petCompletedVaccinations = null;

    private List<PetPendingVaccination> petPendingVaccinations = null;

    public String getIsBack ()
    {
        return isBack;
    }

    public void setIsBack (String isBack)
    {
        this.isBack = isBack;
    }

    public ArrayList<PetImmunizationDetailModels> getPetImmunizationDetailModels ()
    {
        return petImmunizationDetailModels;
    }

    public void setPetImmunizationDetailModels (ArrayList<PetImmunizationDetailModels> petImmunizationDetailModels)
    {
        this.petImmunizationDetailModels = petImmunizationDetailModels;
    }

    public String getPetDetails ()
    {
        return petDetails;
    }

    public void setPetDetails (String petDetails)
    {
        this.petDetails = petDetails;
    }

    public String getVeterinarianDetails ()
    {
        return veterinarianDetails;
    }

    public void setVeterinarianDetails (String veterinarianDetails)
    {
        this.veterinarianDetails = veterinarianDetails;
    }

    public String getVisitDate ()
    {
        return visitDate;
    }

    public void setVisitDate (String visitDate)
    {
        this.visitDate = visitDate;
    }

    public String getPetParentDetails ()
    {
        return petParentDetails;
    }

    public void setPetParentDetails (String petParentDetails)
    {
        this.petParentDetails = petParentDetails;
    }

    public List<Object> getPetCompletedVaccinations() {
        return petCompletedVaccinations;
    }

    public void setPetCompletedVaccinations(List<Object> petCompletedVaccinations) {
        this.petCompletedVaccinations = petCompletedVaccinations;
    }

    public List<PetPendingVaccination> getPetPendingVaccinations() {
        return petPendingVaccinations;
    }

    public void setPetPendingVaccinations(List<PetPendingVaccination> petPendingVaccinations) {
        this.petPendingVaccinations = petPendingVaccinations;
    }

    @Override
    public String toString() {
        return "ClassPojo[" +
                "isBack=" + isBack + 
                ", petImmunizationDetailModels=" + petImmunizationDetailModels +
                ", petDetails=" + petDetails + 
                ", veterinarianDetails=" + veterinarianDetails + 
                ", visitDate=" + visitDate + 
                ", petParentDetails=" + petParentDetails + 
                ", petCompletedVaccinations=" + petCompletedVaccinations +
                ", petPendingVaccinations=" + petPendingVaccinations +
                "]";
    }
}
