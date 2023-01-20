package com.cynoteck.petofyOPHR.response.saveResponse;

public class SaveVaccineModel {
    private String immunizationDate;

    private String brandName;

    private String errorMessage;

    private String nextVaccineDate;

    private String errorCode;

    private String vaccineType;

    private String responseStatus;

    private String vaccineDose;

    private String nextVaccineName;

    private String vaccine;

    private String isTemp;

    private String id;

    private String nextVaccineType;

    private String isAlreadyVaccinated;

    private String statusCode;

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

    public String getErrorMessage ()
    {
        return errorMessage;
    }

    public void setErrorMessage (String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public String getNextVaccineDate ()
    {
        return nextVaccineDate;
    }

    public void setNextVaccineDate (String nextVaccineDate)
    {
        this.nextVaccineDate = nextVaccineDate;
    }

    public String getErrorCode ()
    {
        return errorCode;
    }

    public void setErrorCode (String errorCode)
    {
        this.errorCode = errorCode;
    }

    public String getVaccineType ()
    {
        return vaccineType;
    }

    public void setVaccineType (String vaccineType)
    {
        this.vaccineType = vaccineType;
    }

    public String getResponseStatus ()
    {
        return responseStatus;
    }

    public void setResponseStatus (String responseStatus)
    {
        this.responseStatus = responseStatus;
    }

    public String getVaccineDose ()
    {
        return vaccineDose;
    }

    public void setVaccineDose (String vaccineDose)
    {
        this.vaccineDose = vaccineDose;
    }

    public String getNextVaccineName ()
    {
        return nextVaccineName;
    }

    public void setNextVaccineName (String nextVaccineName)
    {
        this.nextVaccineName = nextVaccineName;
    }

    public String getVaccine ()
    {
        return vaccine;
    }

    public void setVaccine (String vaccine)
    {
        this.vaccine = vaccine;
    }

    public String getIsTemp ()
    {
        return isTemp;
    }

    public void setIsTemp (String isTemp)
    {
        this.isTemp = isTemp;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getNextVaccineType ()
    {
        return nextVaccineType;
    }

    public void setNextVaccineType (String nextVaccineType)
    {
        this.nextVaccineType = nextVaccineType;
    }

    public String getIsAlreadyVaccinated ()
    {
        return isAlreadyVaccinated;
    }

    public void setIsAlreadyVaccinated (String isAlreadyVaccinated)
    {
        this.isAlreadyVaccinated = isAlreadyVaccinated;
    }

    public String getStatusCode ()
    {
        return statusCode;
    }

    public void setStatusCode (String statusCode)
    {
        this.statusCode = statusCode;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [immunizationDate = "+immunizationDate+", brandName = "+brandName+", errorMessage = "+errorMessage+", nextVaccineDate = "+nextVaccineDate+", errorCode = "+errorCode+", vaccineType = "+vaccineType+", responseStatus = "+responseStatus+", vaccineDose = "+vaccineDose+", nextVaccineName = "+nextVaccineName+", vaccine = "+vaccine+", isTemp = "+isTemp+", id = "+id+", nextVaccineType = "+nextVaccineType+", isAlreadyVaccinated = "+isAlreadyVaccinated+", statusCode = "+statusCode+"]";
    }
}
