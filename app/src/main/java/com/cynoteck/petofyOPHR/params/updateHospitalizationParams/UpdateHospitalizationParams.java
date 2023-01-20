package com.cynoteck.petofyOPHR.params.updateHospitalizationParams;

public class UpdateHospitalizationParams {

    private String id;
    private String petId;
    private String requestingVeterinarian;
    private String veterinarianPhone;
    private String hospitalizationTypeId;
    private String admissionDate;
    private String dischargeDate;
    private String hospitalName;
    private String hospitalPhone;
    private String address;
    private String countryId;
    private String stateId;
    private String cityId;
    private String zipCode;
    private String reasonForHospitalization;
    private String diagnosisTreatmentProcedure;
    private String documents;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getHospitalizationTypeId() {
        return hospitalizationTypeId;
    }

    public void setHospitalizationTypeId(String hospitalizationTypeId) {
        this.hospitalizationTypeId = hospitalizationTypeId;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(String dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalPhone() {
        return hospitalPhone;
    }

    public void setHospitalPhone(String hospitalPhone) {
        this.hospitalPhone = hospitalPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getReasonForHospitalization() {
        return reasonForHospitalization;
    }

    public void setReasonForHospitalization(String reasonForHospitalization) {
        this.reasonForHospitalization = reasonForHospitalization;
    }

    public String getDiagnosisTreatmentProcedure() {
        return diagnosisTreatmentProcedure;
    }

    public void setDiagnosisTreatmentProcedure(String diagnosisTreatmentProcedure) {
        this.diagnosisTreatmentProcedure = diagnosisTreatmentProcedure;
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
        return "ClassPojo [petId = "+petId+", requestingVeterinarian = "+requestingVeterinarian+", veterinarianPhone = "+veterinarianPhone+", hospitalizationTypeId = "+hospitalizationTypeId+", admissionDate = "+admissionDate+", dischargeDate = "+dischargeDate+", hospitalName = "+hospitalName+",hospitalPhone= "+hospitalPhone+", address= "+address+", countryId= "+countryId+", stateId= "+stateId+", cityId= "+cityId+", zipCode= "+zipCode+", reasonForHospitalization= "+reasonForHospitalization+", diagnosisTreatmentProcedure= "+diagnosisTreatmentProcedure+", documents= "+documents+"]";
    }

}
