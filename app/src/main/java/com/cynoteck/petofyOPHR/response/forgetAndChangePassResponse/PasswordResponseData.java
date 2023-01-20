package com.cynoteck.petofyOPHR.response.forgetAndChangePassResponse;

public class PasswordResponseData {

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
