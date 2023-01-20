package com.cynoteck.petofyOPHR.response.addEditImmunizationResponse;

public class AddEditImmunizationData {
    private String serialNumber;

    private String boosterOneDaysGap;

    private String isPeriodicVaccine;

    private String primaryVaccine;

    private String veterinarianUserId;

    private String maximunAge;

    private String boosterTwoDaysGap;

    private String boosterTwo;

    private String boosterOne;

    private String minimunAge;

    private String vaccinationPeriod;

    private String id;

    private String petCategoryId;

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

    public String getIsPeriodicVaccine ()
    {
        return isPeriodicVaccine;
    }

    public void setIsPeriodicVaccine (String isPeriodicVaccine)
    {
        this.isPeriodicVaccine = isPeriodicVaccine;
    }

    public String getPrimaryVaccine ()
    {
        return primaryVaccine;
    }

    public void setPrimaryVaccine (String primaryVaccine)
    {
        this.primaryVaccine = primaryVaccine;
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

    public String getBoosterTwo ()
    {
        return boosterTwo;
    }

    public void setBoosterTwo (String boosterTwo)
    {
        this.boosterTwo = boosterTwo;
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
        return "ClassPojo [serialNumber = "+serialNumber+", boosterOneDaysGap = "+boosterOneDaysGap+", isPeriodicVaccine = "+isPeriodicVaccine+", primaryVaccine = "+primaryVaccine+", veterinarianUserId = "+veterinarianUserId+", maximunAge = "+maximunAge+", boosterTwoDaysGap = "+boosterTwoDaysGap+", boosterTwo = "+boosterTwo+", boosterOne = "+boosterOne+", minimunAge = "+minimunAge+", vaccinationPeriod = "+vaccinationPeriod+", id = "+id+", petCategoryId = "+petCategoryId+"]";
    }
}

