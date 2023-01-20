package com.cynoteck.petofyOPHR.response.clinicVisist;

import java.util.List;

public class ClinicVisitsModel {

    private String id;
    private String followUpTitle;
    private String status;
    private List<String> petClinicVisit = null;
    private List<String> petTestsAndXrey = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFollowUpTitle() {
        return followUpTitle;
    }

    public void setFollowUpTitle(String followUpTitle) {
        this.followUpTitle = followUpTitle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getPetClinicVisit() {
        return petClinicVisit;
    }

    public void setPetClinicVisit(List<String> petClinicVisit) {
        this.petClinicVisit = petClinicVisit;
    }

    public List<String> getPetTestsAndXrey() {
        return petTestsAndXrey;
    }

    public void setPetTestsAndXrey(List<String> petTestsAndXrey) {
        this.petTestsAndXrey = petTestsAndXrey;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", followUpTitle = "+followUpTitle+", status = "+status+", petClinicVisit= "+petClinicVisit+", petTestsAndXrey= "+petTestsAndXrey+"]";
    }


}
