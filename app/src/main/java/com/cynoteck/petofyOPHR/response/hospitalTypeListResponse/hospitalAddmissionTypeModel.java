package com.cynoteck.petofyOPHR.response.hospitalTypeListResponse;

import java.util.List;

public class hospitalAddmissionTypeModel {

    private String id;
    private String hospitalization;
    private List<String> petHospitalizationsSurgeries = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHospitalization() {
        return hospitalization;
    }

    public void setHospitalization(String hospitalization) {
        this.hospitalization = hospitalization;
    }

    public List<String> getPetHospitalizationsSurgeries() {
        return petHospitalizationsSurgeries;
    }

    public void setPetHospitalizationsSurgeries(List<String> petHospitalizationsSurgeries) {
        this.petHospitalizationsSurgeries = petHospitalizationsSurgeries;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", hospitalization = "+hospitalization+", petHospitalizationsSurgeries = "+petHospitalizationsSurgeries+"]";
    }

}
