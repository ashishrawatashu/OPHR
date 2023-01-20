package com.cynoteck.petofyOPHR.response.immunizationListResponse;

import java.util.ArrayList;

public class ImmunizationScheduleScheduleList
{
    private String serialNumber;
    private String petCategoryName;
    private String vaccinationPeriodText;

    private String boosterOneDaysGap;

    private String recommendedVaccinations;

    private String isPeriodicVaccine;

    private ArrayList<PetAgeList> petAgeList;

    private String veterinarianUserId;

    private String maximunAge;

    private String boosterTwoDaysGap;

    private String petAgeListId;

    private String boosterTwo;

    private ArrayList<PeriodicVaccineTypeList> periodicVaccineTypeList;

    private String encryptedId;

    private String boosterOne;

    private String minimunAge;

    private String vaccinationPeriod;

    private String id;

    private String petCategoryId;

    public String getPetCategoryName() {
        return petCategoryName;
    }

    public void setPetCategoryName(String petCategoryName) {
        this.petCategoryName = petCategoryName;
    }

    public String getVaccinationPeriodText() {
        return vaccinationPeriodText;
    }

    public void setVaccinationPeriodText(String vaccinationPeriodText) {
        this.vaccinationPeriodText = vaccinationPeriodText;
    }

    public String getSerialNumber ()
    {
        return serialNumber;
    }

    public void setSerialNumber (String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    public String getBoosterOneDaysGap ()
    {
        return boosterOneDaysGap;
    }

    public void setBoosterOneDaysGap (String boosterOneDaysGap)
    {
        this.boosterOneDaysGap = boosterOneDaysGap;
    }

    public String getRecommendedVaccinations ()
    {
        return recommendedVaccinations;
    }

    public void setRecommendedVaccinations (String recommendedVaccinations)
    {
        this.recommendedVaccinations = recommendedVaccinations;
    }

    public String getIsPeriodicVaccine ()
    {
        return isPeriodicVaccine;
    }

    public void setIsPeriodicVaccine (String isPeriodicVaccine)
    {
        this.isPeriodicVaccine = isPeriodicVaccine;
    }

    public ArrayList<PetAgeList> getPetAgeList ()
    {
        return petAgeList;
    }

    public void setPetAgeList (ArrayList<PetAgeList> petAgeList)
    {
        this.petAgeList = petAgeList;
    }

    public String getVeterinarianUserId ()
    {
        return veterinarianUserId;
    }

    public void setVeterinarianUserId (String veterinarianUserId)
    {
        this.veterinarianUserId = veterinarianUserId;
    }

    public String getMaximunAge ()
    {
        return maximunAge;
    }

    public void setMaximunAge (String maximunAge)
    {
        this.maximunAge = maximunAge;
    }

    public String getBoosterTwoDaysGap ()
    {
        return boosterTwoDaysGap;
    }

    public void setBoosterTwoDaysGap (String boosterTwoDaysGap)
    {
        this.boosterTwoDaysGap = boosterTwoDaysGap;
    }

    public String getPetAgeListId ()
    {
        return petAgeListId;
    }

    public void setPetAgeListId (String petAgeListId)
    {
        this.petAgeListId = petAgeListId;
    }

    public String getBoosterTwo ()
    {
        return boosterTwo;
    }

    public void setBoosterTwo (String boosterTwo)
    {
        this.boosterTwo = boosterTwo;
    }

    public ArrayList<PeriodicVaccineTypeList> getPeriodicVaccineTypeList ()
    {
        return periodicVaccineTypeList;
    }

    public void setPeriodicVaccineTypeList (ArrayList<PeriodicVaccineTypeList> periodicVaccineTypeList)
    {
        this.periodicVaccineTypeList = periodicVaccineTypeList;
    }

    public String getEncryptedId ()
{
    return encryptedId;
}

    public void setEncryptedId (String encryptedId)
    {
        this.encryptedId = encryptedId;
    }

    public String getBoosterOne ()
    {
        return boosterOne;
    }

    public void setBoosterOne (String boosterOne)
    {
        this.boosterOne = boosterOne;
    }

    public String getMinimunAge ()
    {
        return minimunAge;
    }

    public void setMinimunAge (String minimunAge)
    {
        this.minimunAge = minimunAge;
    }

    public String getVaccinationPeriod ()
    {
        return vaccinationPeriod;
    }

    public void setVaccinationPeriod (String vaccinationPeriod)
    {
        this.vaccinationPeriod = vaccinationPeriod;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
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
        return "ClassPojo [serialNumber = "+serialNumber+", boosterOneDaysGap = "+boosterOneDaysGap+", recommendedVaccinations = "+recommendedVaccinations+", isPeriodicVaccine = "+isPeriodicVaccine+", petAgeList = "+petAgeList+", veterinarianUserId = "+veterinarianUserId+", maximunAge = "+maximunAge+", boosterTwoDaysGap = "+boosterTwoDaysGap+", petAgeListId = "+petAgeListId+", boosterTwo = "+boosterTwo+", periodicVaccineTypeList = "+periodicVaccineTypeList+", encryptedId = "+encryptedId+", boosterOne = "+boosterOne+", minimunAge = "+minimunAge+", vaccinationPeriod = "+vaccinationPeriod+", id = "+id+", petCategoryId = "+petCategoryId+"]";
    }
}
