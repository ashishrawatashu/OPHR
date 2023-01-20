package com.cynoteck.petofyOPHR.params.addLabRequest;

public class AddLabParams {
    private String petId;
    private String requestingVeterinarian;
    private String veterinarianPhone;
    private String visitDate;
    private String labTypeId;
    private String nameOfLab;
    private String labPhone;
    private String address1;
    private String address2;
    private String countryId;
    private String stateId;
    private String cityId;
    private String zipCode;
    private String testName;
    private String reasonOfTest;
    private String results;
    private String documents;

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getRequestingVeterinarian() {
        return requestingVeterinarian;
    }

    public void setRequestingVeterinarian(String requestingVeterinarian) {
        this.requestingVeterinarian = requestingVeterinarian;
    }

    public String getVeterinarianPhone() {
        return veterinarianPhone;
    }

    public void setVeterinarianPhone(String veterinarianPhone) {
        this.veterinarianPhone = veterinarianPhone;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getLabTypeId() {
        return labTypeId;
    }

    public void setLabTypeId(String labTypeId) {
        this.labTypeId = labTypeId;
    }

    public String getNameOfLab() {
        return nameOfLab;
    }

    public void setNameOfLab(String nameOfLab) {
        this.nameOfLab = nameOfLab;
    }

    public String getLabPhone() {
        return labPhone;
    }

    public void setLabPhone(String labPhone) {
        this.labPhone = labPhone;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getReasonOfTest() {
        return reasonOfTest;
    }

    public void setReasonOfTest(String reasonOfTest) {
        this.reasonOfTest = reasonOfTest;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getDocuments() {
        return documents;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }
    @Override
    public String toString()
    {
        return "ClassPojo [petId= "+petId+", requestingVeterinarian = "+requestingVeterinarian+", veterinarianPhone = "+veterinarianPhone+", visitDate = "+visitDate+", labTypeId = "+labTypeId+", nameOfLab= "+nameOfLab+", labPhone = "+labPhone+", address1 = "+address1+", address2 = "+address2+", countryId = "+countryId+", stateId = "+stateId+", cityId = "+cityId+", zipCode = "+zipCode+", testName = "+testName+", reasonOfTest = "+reasonOfTest+", results = "+results+", documents = "+documents+"]";
    }
}
