package com.cynoteck.petofyOPHR.params.allStaffRequest;

public class StaffDetailsParams {
    private String encryptedId;

    public String getEncryptedId ()
    {
        return encryptedId;
    }

    public void setEncryptedId (String encryptedId)
    {
        this.encryptedId = encryptedId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [encryptedId = "+encryptedId+"]";
    }
}