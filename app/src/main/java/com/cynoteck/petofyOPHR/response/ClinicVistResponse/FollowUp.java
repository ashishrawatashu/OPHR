package com.cynoteck.petofyOPHR.response.ClinicVistResponse;

import java.util.List;

public class FollowUp {
    private String id;
    private String followUpTitle;
    private List<String> petClinicVisitModel = null;

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

    public List<String> getPetClinicVisitModel() {
        return petClinicVisitModel;
    }

    public void setPetClinicVisitModel(List<String> petClinicVisitModel) {
        this.petClinicVisitModel = petClinicVisitModel;
    }

    @Override
    public String toString() {
        return "ClassPojo[" +
                "id= " + id +
                ", followUpTitle= " + followUpTitle +
                ", petClinicVisitModel= " + petClinicVisitModel +
                "]";
    }
}
