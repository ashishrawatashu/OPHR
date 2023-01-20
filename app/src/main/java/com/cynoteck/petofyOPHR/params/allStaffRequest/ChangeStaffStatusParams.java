package com.cynoteck.petofyOPHR.params.allStaffRequest;

public class ChangeStaffStatusParams {
    private String encryptedId;

    private String status;

    public String getEncryptedId ()
    {
        return encryptedId;
    }

    public void setEncryptedId (String encryptedId)
    {
        this.encryptedId = encryptedId;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [encryptedId = "+encryptedId+", status = "+status+"]";
    }
}