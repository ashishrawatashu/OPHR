package com.cynoteck.petofyOPHR.params.VaccinationTypeByVaccineName;

public class VaccinationTypeByVaccineNameParams {
    private String vaccineName;
    private String petId;

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getVaccineName ()
    {
        return vaccineName;
    }

    public void setVaccineName (String vaccineName)
    {
        this.vaccineName = vaccineName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [vaccineName = "+vaccineName+"]";
    }
}
