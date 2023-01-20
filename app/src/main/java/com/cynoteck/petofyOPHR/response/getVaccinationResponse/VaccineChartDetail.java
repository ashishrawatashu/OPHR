package com.cynoteck.petofyOPHR.response.getVaccinationResponse;

public class VaccineChartDetail {
    private  String id;
    private String vaccineName;
    private String vaccineType;
    private  String totalPeriodicVaccine;
    private  String status;

    public  String getId() {
        return id;
    }

    public void setId( String id) {
        this.id = id;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

    public  String getTotalPeriodicVaccine() {
        return totalPeriodicVaccine;
    }

    public void setTotalPeriodicVaccine( String totalPeriodicVaccine) {
        this.totalPeriodicVaccine = totalPeriodicVaccine;
    }

    public  String getStatus() {
        return status;
    }

    public void setStatus( String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "ClassPojo[" +
                "id=" + id +
                ", vaccineName=" + vaccineName +
                ", vaccineType=" + vaccineType +
                ", totalPeriodicVaccine=" + totalPeriodicVaccine +
                ", status=" + status +
                "]";
    }
}
