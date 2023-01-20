package com.cynoteck.petofyOPHR.response.immunizationListResponse;

import java.util.ArrayList;

public class ImmunizationData {
    private ArrayList<ImmunizationScheduleScheduleList> immunizationScheduleScheduleList;

    private ArrayList<PetCategoryList> petCategoryList;

    private String encryptedId;

    private String petCategoryId;

    public ArrayList<ImmunizationScheduleScheduleList> getImmunizationScheduleScheduleList ()
    {
        return immunizationScheduleScheduleList;
    }

    public void setImmunizationScheduleScheduleList (ArrayList<ImmunizationScheduleScheduleList> immunizationScheduleScheduleList)
    {
        this.immunizationScheduleScheduleList = immunizationScheduleScheduleList;
    }

    public ArrayList<PetCategoryList> getPetCategoryList ()
    {
        return petCategoryList;
    }

    public void setPetCategoryList (ArrayList<PetCategoryList> petCategoryList)
    {
        this.petCategoryList = petCategoryList;
    }

    public String getEncryptedId ()
    {
        return encryptedId;
    }

    public void setEncryptedId (String encryptedId)
    {
        this.encryptedId = encryptedId;
    }

    public String getPetCategoryId ()
    {
        return petCategoryId;
    }

    public void setPetCategoryId (String petCategoryId)
    {
        this.petCategoryId = petCategoryId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [immunizationScheduleScheduleList = "+immunizationScheduleScheduleList+", petCategoryList = "+petCategoryList+", encryptedId = "+encryptedId+", petCategoryId = "+petCategoryId+"]";
    }
}
