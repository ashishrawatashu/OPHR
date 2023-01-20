package com.cynoteck.petofyOPHR.response.VaccinationTypeByVaccineNameResponse;

public class VaccinationTypeByVaccineNameData {
    private String vaccineType;

    private String vaccineCode;

    public String getVaccineType ()
    {
        return vaccineType;
    }

    public void setVaccineType (String vaccineType)
    {
        this.vaccineType = vaccineType;
    }

    public String getVaccineCode ()
    {
        return vaccineCode;
    }

    public void setVaccineCode (String vaccineCode)
    {
        this.vaccineCode = vaccineCode;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [vaccineType = "+vaccineType+", vaccineCode = "+vaccineCode+"]";
    }
}
