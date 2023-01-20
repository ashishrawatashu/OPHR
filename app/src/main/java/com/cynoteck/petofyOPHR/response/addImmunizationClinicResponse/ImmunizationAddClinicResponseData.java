package com.cynoteck.petofyOPHR.response.addImmunizationClinicResponse;

import java.util.List;

public class ImmunizationAddClinicResponseData {
    private  String id;
    private  String petId;
    private String veterinarian;
    private String visitDate;
    private  String natureOfVisitId;
    private String vaccine;
    private String description;
    private  String weightLbs;
    private  String weightOz;
    private  String temperature;
    private  String dateOfOnset;
    private  String diagnosisProcedure;
    private  String treatmentRemarks;
    private  String dewormerName;
    private  String documents;
    private String createdOn;
    private String createdBy;
    private String updatedOn;
    private String updatedBy;
    private  String followUpId;
    private  String followUpDate;
    private String veterinarianUserId;
    private  String dewormerDose;
    private  String isOnLineAppointment;
    private AddClinicImmunizationModel createdByNavigation;
    private  String followUp;
    private  String natureOfVisit;
    private Pet pet;
    private UpdatedByNavigation updatedByNavigation;
    private VeterinarianUser veterinarianUser;
    private List< String> veterinarianAppointment = null;
    private  String petVaccinationDetail;

    public  String getId() {
        return id;
    }

    public void setId( String id) {
        this.id = id;
    }

    public  String getPetId() {
        return petId;
    }

    public void setPetId( String petId) {
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

    public  String getNatureOfVisitId() {
        return natureOfVisitId;
    }

    public void setNatureOfVisitId( String natureOfVisitId) {
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

    public  String getWeightLbs() {
        return weightLbs;
    }

    public void setWeightLbs( String weightLbs) {
        this.weightLbs = weightLbs;
    }

    public  String getWeightOz() {
        return weightOz;
    }

    public void setWeightOz( String weightOz) {
        this.weightOz = weightOz;
    }

    public  String getTemperature() {
        return temperature;
    }

    public void setTemperature( String temperature) {
        this.temperature = temperature;
    }

    public  String getDateOfOnset() {
        return dateOfOnset;
    }

    public void setDateOfOnset( String dateOfOnset) {
        this.dateOfOnset = dateOfOnset;
    }

    public  String getDiagnosisProcedure() {
        return diagnosisProcedure;
    }

    public void setDiagnosisProcedure( String diagnosisProcedure) {
        this.diagnosisProcedure = diagnosisProcedure;
    }

    public  String getTreatmentRemarks() {
        return treatmentRemarks;
    }

    public void setTreatmentRemarks( String treatmentRemarks) {
        this.treatmentRemarks = treatmentRemarks;
    }

    public  String getDewormerName() {
        return dewormerName;
    }

    public void setDewormerName( String dewormerName) {
        this.dewormerName = dewormerName;
    }

    public  String getDocuments() {
        return documents;
    }

    public void setDocuments( String documents) {
        this.documents = documents;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public  String getFollowUpId() {
        return followUpId;
    }

    public void setFollowUpId( String followUpId) {
        this.followUpId = followUpId;
    }

    public  String getFollowUpDate() {
        return followUpDate;
    }

    public void setFollowUpDate( String followUpDate) {
        this.followUpDate = followUpDate;
    }

    public String getVeterinarianUserId() {
        return veterinarianUserId;
    }

    public void setVeterinarianUserId(String veterinarianUserId) {
        this.veterinarianUserId = veterinarianUserId;
    }

    public  String getDewormerDose() {
        return dewormerDose;
    }

    public void setDewormerDose( String dewormerDose) {
        this.dewormerDose = dewormerDose;
    }

    public  String getIsOnLineAppointment() {
        return isOnLineAppointment;
    }

    public void setIsOnLineAppointment( String isOnLineAppointment) {
        this.isOnLineAppointment = isOnLineAppointment;
    }

    public AddClinicImmunizationModel getCreatedByNavigation() {
        return createdByNavigation;
    }

    public void setCreatedByNavigation(AddClinicImmunizationModel createdByNavigation) {
        this.createdByNavigation = createdByNavigation;
    }

    public  String getFollowUp() {
        return followUp;
    }

    public void setFollowUp( String followUp) {
        this.followUp = followUp;
    }

    public  String getNatureOfVisit() {
        return natureOfVisit;
    }

    public void setNatureOfVisit( String natureOfVisit) {
        this.natureOfVisit = natureOfVisit;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public UpdatedByNavigation getUpdatedByNavigation() {
        return updatedByNavigation;
    }

    public void setUpdatedByNavigation(UpdatedByNavigation updatedByNavigation) {
        this.updatedByNavigation = updatedByNavigation;
    }

    public VeterinarianUser getVeterinarianUser() {
        return veterinarianUser;
    }

    public void setVeterinarianUser(VeterinarianUser veterinarianUser) {
        this.veterinarianUser = veterinarianUser;
    }

    public List< String> getVeterinarianAppointment() {
        return veterinarianAppointment;
    }

    public void setVeterinarianAppointment(List< String> veterinarianAppointment) {
        this.veterinarianAppointment = veterinarianAppointment;
    }

    public  String getPetVaccinationDetail() {
        return petVaccinationDetail;
    }

    public void setPetVaccinationDetail( String petVaccinationDetail) {
        this.petVaccinationDetail = petVaccinationDetail;
    }

    @Override
    public String toString() {
        return "ClassPojo[" +
                "id=" + id +
                ", petId=" + petId +
                ", veterinarian=" + veterinarian +
                ", visitDate=" + visitDate +
                ", natureOfVisitId=" + natureOfVisitId +
                ", vaccine=" + vaccine +
                ", description=" + description +
                ", weightLbs=" + weightLbs +
                ", weightOz=" + weightOz +
                ", temperature=" + temperature +
                ", dateOfOnset=" + dateOfOnset +
                ", diagnosisProcedure=" + diagnosisProcedure +
                ", treatmentRemarks=" + treatmentRemarks +
                ", dewormerName=" + dewormerName +
                ", documents=" + documents +
                ", createdOn=" + createdOn +
                ", createdBy=" + createdBy +
                ", updatedOn=" + updatedOn +
                ", updatedBy=" + updatedBy +
                ", followUpId=" + followUpId +
                ", followUpDate=" + followUpDate +
                ", veterinarianUserId=" + veterinarianUserId +
                ", dewormerDose=" + dewormerDose +
                ", isOnLineAppointment=" + isOnLineAppointment +
                ", createdByNavigation=" + createdByNavigation +
                ", followUp=" + followUp +
                ", natureOfVisit=" + natureOfVisit +
                ", pet=" + pet +
                ", updatedByNavigation=" + updatedByNavigation +
                ", veterinarianUser=" + veterinarianUser +
                ", veterinarianAppointment=" + veterinarianAppointment +
                ", petVaccinationDetail=" + petVaccinationDetail +
                "]";
    }
}
