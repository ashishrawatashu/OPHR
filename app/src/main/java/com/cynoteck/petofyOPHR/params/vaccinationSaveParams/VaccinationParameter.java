package com.cynoteck.petofyOPHR.params.vaccinationSaveParams;

public class VaccinationParameter {

    private String encryptedId;
    private String vaccine;
    private String vaccinationScheduleId;
    private String vaccineType;

    public String getEncryptedId() {
        return encryptedId;
    }

    public void setEncryptedId(String encryptedId) {
        this.encryptedId = encryptedId;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public String getVaccinationScheduleId() {
        return vaccinationScheduleId;
    }

    public void setVaccinationScheduleId(String vaccinationScheduleId) {
        this.vaccinationScheduleId = vaccinationScheduleId;
    }

    public String getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [encryptedId ="+encryptedId+", vaccine = "+vaccine+", vaccinationScheduleId = "+vaccinationScheduleId+", vaccineType = "+vaccineType+"]";
    }
}
