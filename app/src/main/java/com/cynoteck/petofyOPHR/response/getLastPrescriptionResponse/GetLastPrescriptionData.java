package com.cynoteck.petofyOPHR.response.getLastPrescriptionResponse;

import com.cynoteck.petofyOPHR.response.getImmunizationReport.PetPendingVaccination;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.getClinicVisitDetails.PetClinicVisitDetails;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.getClinicVisitDetails.PetParentDetails;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.getClinicVisitDetails.VeterinarianDetails;

import java.util.List;

public class GetLastPrescriptionData {
    private String isBack;

    private String prescription;

    private VeterinarianDetails veterinarianDetails;

    private PetClinicVisitDetails petClinicVisitDetails;

    private String hasMultipleVeterinarian;

    private PetParentDetails petParentDetails;

    private List<PendingVaccinations> pendingVaccinations;

    private String isOnlineAppointment;

    private String secondVeterinarianDetails;

    private String appointmentDate;

    public String getIsBack ()
    {
        return isBack;
    }

    public void setIsBack (String isBack)
    {
        this.isBack = isBack;
    }

    public String getPrescription ()
    {
        return prescription;
    }

    public void setPrescription (String prescription)
    {
        this.prescription = prescription;
    }

    public VeterinarianDetails getVeterinarianDetails ()
    {
        return veterinarianDetails;
    }

    public void setVeterinarianDetails (VeterinarianDetails veterinarianDetails)
    {
        this.veterinarianDetails = veterinarianDetails;
    }

    public PetClinicVisitDetails getPetClinicVisitDetails ()
    {
        return petClinicVisitDetails;
    }

    public void setPetClinicVisitDetails (PetClinicVisitDetails petClinicVisitDetails)
    {
        this.petClinicVisitDetails = petClinicVisitDetails;
    }

    public String getHasMultipleVeterinarian ()
    {
        return hasMultipleVeterinarian;
    }

    public void setHasMultipleVeterinarian (String hasMultipleVeterinarian)
    {
        this.hasMultipleVeterinarian = hasMultipleVeterinarian;
    }

    public PetParentDetails getPetParentDetails ()
    {
        return petParentDetails;
    }

    public void setPetParentDetails (PetParentDetails petParentDetails)
    {
        this.petParentDetails = petParentDetails;
    }

    public List<PendingVaccinations>  getPendingVaccinations ()
    {
        return pendingVaccinations;
    }

    public void setPendingVaccinations (List<PendingVaccinations> pendingVaccinations)
    {
        this.pendingVaccinations = pendingVaccinations;
    }

    public String getIsOnlineAppointment ()
    {
        return isOnlineAppointment;
    }

    public void setIsOnlineAppointment (String isOnlineAppointment)
    {
        this.isOnlineAppointment = isOnlineAppointment;
    }

    public String getSecondVeterinarianDetails ()
    {
        return secondVeterinarianDetails;
    }

    public void setSecondVeterinarianDetails (String secondVeterinarianDetails)
    {
        this.secondVeterinarianDetails = secondVeterinarianDetails;
    }

    public String getAppointmentDate ()
    {
        return appointmentDate;
    }

    public void setAppointmentDate (String appointmentDate)
    {
        this.appointmentDate = appointmentDate;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [isBack = "+isBack+", prescription = "+prescription+", veterinarianDetails = "+veterinarianDetails+", petClinicVisitDetails = "+petClinicVisitDetails+", hasMultipleVeterinarian = "+hasMultipleVeterinarian+", petParentDetails = "+petParentDetails+", pendingVaccinations = "+pendingVaccinations+", isOnlineAppointment = "+isOnlineAppointment+", secondVeterinarianDetails = "+secondVeterinarianDetails+", appointmentDate = "+appointmentDate+"]";
    }
}
	
