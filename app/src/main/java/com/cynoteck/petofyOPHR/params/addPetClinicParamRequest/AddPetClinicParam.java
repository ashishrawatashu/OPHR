package com.cynoteck.petofyOPHR.params.addPetClinicParamRequest;

import com.google.gson.JsonArray;

import java.util.ArrayList;

public class AddPetClinicParam {
    private String petId;
    private String veterinarian;
    private String visitDate;
    private String natureOfVisitId;
    private String vaccine;
    private String description;
    private String weightLbs;
    private String weightOz;
    private String temperature;
    private String dateOfOnset;
    private String dewormerName;
    private String remarks;
    private String history;
    private String treatmentRemarks;
    private String diagnosisProcedure;
    private String followUpId;
    private String followUpDate;
    private String nextVaccineName;
    private String nextVaccinetype;
    private String documents;
    private String appointmentId;
    private String dewormerDose;
//    private JsonArray vaccinationModels = null;
    private ArrayList<VaccinationModel> vaccinationModels;

//    public JsonArray getVaccinationModels() {
//        return vaccinationModels;
//    }

//    public void setVaccinationModels(JsonArray vaccinationModels) {
//        this.vaccinationModels = vaccinationModels;
//    }


    public ArrayList<VaccinationModel> getVaccinationModels() {
        return vaccinationModels;
    }

    public void setVaccinationModels(ArrayList<VaccinationModel> vaccinationModels) {
        this.vaccinationModels = vaccinationModels;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(String veterinarian) {
        this.veterinarian = veterinarian;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public String getNatureOfVisitId() {
        return natureOfVisitId;
    }

    public void setNatureOfVisitId(String natureOfVisitId) {
        this.natureOfVisitId = natureOfVisitId;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeightLbs() {
        return weightLbs;
    }

    public void setWeightLbs(String weightLbs) {
        this.weightLbs = weightLbs;
    }

    public String getWeightOz() {
        return weightOz;
    }

    public void setWeightOz(String weightOz) {
        this.weightOz = weightOz;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getDateOfOnset() {
        return dateOfOnset;
    }

    public void setDateOfOnset(String dateOfOnset) {
        this.dateOfOnset = dateOfOnset;
    }

    public String getDewormerName() {
        return dewormerName;
    }

    public void setDewormerName(String dewormerName) {
        this.dewormerName = dewormerName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getNextVaccineName() {
        return nextVaccineName;
    }

    public void setNextVaccineName(String nextVaccineName) {
        this.nextVaccineName = nextVaccineName;
    }

    public String getNextVaccinetype() {
        return nextVaccinetype;
    }

    public void setNextVaccinetype(String nextVaccinetype) {
        this.nextVaccinetype = nextVaccinetype;
    }

    public String getTreatmentRemarks() {
        return treatmentRemarks;
    }

    public void setTreatmentRemarks(String treatmentRemarks) {
        this.treatmentRemarks = treatmentRemarks;
    }

    public String getDiagnosisProcedure() {
        return diagnosisProcedure;
    }

    public void setDiagnosisProcedure(String diagnosisProcedure) {
        this.diagnosisProcedure = diagnosisProcedure;
    }

    public String getFollowUpId() {
        return followUpId;
    }

    public void setFollowUpId(String followUpId) {
        this.followUpId = followUpId;
    }

    public String getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate(String followUpDate) {
        this.followUpDate = followUpDate;
    }

    public String getDocuments() {
        return documents;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getDewormerDose() {
        return dewormerDose;
    }

    public void setDewormerDose(String dewormerDose) {
        this.dewormerDose = dewormerDose;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [petId= "+petId+", veterinarian = "+veterinarian+", visitDate = "+visitDate+", natureOfVisitId = "+natureOfVisitId+", vaccine = "+vaccine+", description= "+description+", weightLbs = "+weightLbs+", weightOz = "+weightOz+", temperature = "+temperature+", dateOfOnset = "+dateOfOnset+", description = "+description+", dewormerName = "+dewormerName+", remarks= "+remarks+", history = "+history+", nextVaccineName ="+nextVaccineName+", nextVaccinetype = "+nextVaccinetype+", treatmentRemarks = "+treatmentRemarks+", diagnosisProcedure = "+diagnosisProcedure+", followUpId = "+followUpId+", followUpDate = "+followUpDate+", documents = "+documents+", appointmentId= "+appointmentId+",dewormerDose="+dewormerDose+", vaccinationModels= " + vaccinationModels +"]";
    }

}
