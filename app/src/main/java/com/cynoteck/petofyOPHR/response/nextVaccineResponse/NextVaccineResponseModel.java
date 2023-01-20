package com.cynoteck.petofyOPHR.response.nextVaccineResponse;

public class NextVaccineResponseModel {
    private String vaccineName;

    private String nextVaccinationDate;

    private String vaccinationStatusMessage;

    private String isVaccinated;

    private String vaccineType;

    private String nextDate;

    public String getVaccineName ()
    {
        return vaccineName;
    }

    public void setVaccineName (String vaccineName)
    {
        this.vaccineName = vaccineName;
    }

    public String getNextVaccinationDate ()
    {
        return nextVaccinationDate;
    }

    public void setNextVaccinationDate (String nextVaccinationDate)
    {
        this.nextVaccinationDate = nextVaccinationDate;
    }

    public String getVaccinationStatusMessage ()
    {
        return vaccinationStatusMessage;
    }

    public void setVaccinationStatusMessage (String vaccinationStatusMessage)
    {
        this.vaccinationStatusMessage = vaccinationStatusMessage;
    }

    public String getIsVaccinated ()
    {
        return isVaccinated;
    }

    public void setIsVaccinated (String isVaccinated)
    {
        this.isVaccinated = isVaccinated;
    }

    public String getVaccineType ()
    {
        return vaccineType;
    }

    public void setVaccineType (String vaccineType)
    {
        this.vaccineType = vaccineType;
    }

    public String getNextDate ()
    {
        return nextDate;
    }

    public void setNextDate (String nextDate)
    {
        this.nextDate = nextDate;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [vaccineName = "+vaccineName+", nextVaccinationDate = "+nextVaccinationDate+", vaccinationStatusMessage = "+vaccinationStatusMessage+", isVaccinated = "+isVaccinated+", vaccineType = "+vaccineType+", nextDate = "+nextDate+"]";
    }
}
