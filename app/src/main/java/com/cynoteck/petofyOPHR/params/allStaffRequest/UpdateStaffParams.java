package com.cynoteck.petofyOPHR.params.allStaffRequest;

public class UpdateStaffParams {
    private String vetRegistrationNumber;

    private String vetQualification;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String initials;

    private String displayInPrescription;

    private String encryptedId;

    private String email;

    public String getVetRegistrationNumber ()
    {
        return vetRegistrationNumber;
    }

    public void setVetRegistrationNumber (String vetRegistrationNumber)
    {
        this.vetRegistrationNumber = vetRegistrationNumber;
    }

    public String getVetQualification ()
    {
        return vetQualification;
    }

    public void setVetQualification (String vetQualification)
    {
        this.vetQualification = vetQualification;
    }

    public String getFirstName ()
    {
        return firstName;
    }

    public void setFirstName (String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName ()
    {
        return lastName;
    }

    public void setLastName (String lastName)
    {
        this.lastName = lastName;
    }

    public String getPhoneNumber ()
    {
        return phoneNumber;
    }

    public void setPhoneNumber (String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getInitials ()
    {
        return initials;
    }

    public void setInitials (String initials)
    {
        this.initials = initials;
    }

    public String getDisplayInPrescription ()
    {
        return displayInPrescription;
    }

    public void setDisplayInPrescription (String displayInPrescription)
    {
        this.displayInPrescription = displayInPrescription;
    }

    public String getEncryptedId ()
    {
        return encryptedId;
    }

    public void setEncryptedId (String encryptedId)
    {
        this.encryptedId = encryptedId;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [vetRegistrationNumber = "+vetRegistrationNumber+", vetQualification = "+vetQualification+", firstName = "+firstName+", lastName = "+lastName+", phoneNumber = "+phoneNumber+", initials = "+initials+", displayInPrescription = "+displayInPrescription+", encryptedId = "+encryptedId+", email = "+email+"]";
    }
}
