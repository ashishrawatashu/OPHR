package com.cynoteck.petofyOPHR.response.totalStaffPetsAppointment;

public class GetDashboardCountsData {
    private String numberOfAppointments;

    private String numberOfPets;

    private String numberOfStaffs;

    public String getNumberOfAppointments ()
    {
        return numberOfAppointments;
    }

    public void setNumberOfAppointments (String numberOfAppointments)
    {
        this.numberOfAppointments = numberOfAppointments;
    }

    public String getNumberOfPets ()
    {
        return numberOfPets;
    }

    public void setNumberOfPets (String numberOfPets)
    {
        this.numberOfPets = numberOfPets;
    }

    public String getNumberOfStaffs ()
    {
        return numberOfStaffs;
    }

    public void setNumberOfStaffs (String numberOfStaffs)
    {
        this.numberOfStaffs = numberOfStaffs;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [numberOfAppointments = "+numberOfAppointments+", numberOfPets = "+numberOfPets+", numberOfStaffs = "+numberOfStaffs+"]";
    }
}


