package com.cynoteck.petofyOPHR.response.getImmunizationReport;

public class PetImmunizationDetailModels {
    private String immunizationDate;

    private String brandName;

    private String vaccine;

    private String petId;

    private String petAgeInDays;

    private String nextDueDate;

    private String petClinicalVisitId;

    private String id;

    private String vaccineType;

    public String getImmunizationDate ()
    {
        return immunizationDate;
    }

    public void setImmunizationDate (String immunizationDate)
    {
        this.immunizationDate = immunizationDate;
    }

    public String getBrandName ()
    {
        return brandName;
    }

    public void setBrandName (String brandName)
    {
        this.brandName = brandName;
    }

    public String getVaccine ()
    {
        return vaccine;
    }

    public void setVaccine (String vaccine)
    {
        this.vaccine = vaccine;
    }

    public String getPetId ()
    {
        return petId;
    }

    public void setPetId (String petId)
    {
        this.petId = petId;
    }

    public String getPetAgeInDays ()
    {
        return petAgeInDays;
    }

    public void setPetAgeInDays (String petAgeInDays)
    {
        this.petAgeInDays = petAgeInDays;
    }

    public String getNextDueDate ()
    {
        return nextDueDate;
    }

    public void setNextDueDate (String nextDueDate)
    {
        this.nextDueDate = nextDueDate;
    }

    public String getPetClinicalVisitId ()
    {
        return petClinicalVisitId;
    }

    public void setPetClinicalVisitId (String petClinicalVisitId)
    {
        this.petClinicalVisitId = petClinicalVisitId;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getVaccineType ()
    {
        return vaccineType;
    }

    public void setVaccineType (String vaccineType)
    {
        this.vaccineType = vaccineType;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [immunizationDate = "+immunizationDate+", brandName = "+brandName+", vaccine = "+vaccine+", petId = "+petId+", petAgeInDays = "+petAgeInDays+", nextDueDate = "+nextDueDate+", petClinicalVisitId = "+petClinicalVisitId+", id = "+id+", vaccineType = "+vaccineType+"]";
    }
}
