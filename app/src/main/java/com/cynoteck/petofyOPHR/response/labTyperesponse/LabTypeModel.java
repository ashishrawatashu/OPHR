package com.cynoteck.petofyOPHR.response.labTyperesponse;

import java.util.List;

public class LabTypeModel {
    private String id;
    private String lab;
    private List<String> petLabwork = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public List<String> getPetLabwork() {
        return petLabwork;
    }

    public void setPetLabwork(List<String> petLabwork) {
        this.petLabwork = petLabwork;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", lab = "+lab+", petLabwork = "+petLabwork+"]";
    }

}
