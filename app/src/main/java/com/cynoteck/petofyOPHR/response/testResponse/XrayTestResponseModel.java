package com.cynoteck.petofyOPHR.response.testResponse;

import java.util.List;

public class XrayTestResponseModel {
    private String id;
    private String testType;
    private List<String> petTestsAndXrey = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
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
        return "ClassPojo [id = "+id+", testType = "+testType+", petTestsAndXrey = "+petTestsAndXrey+"]";
    }

}
