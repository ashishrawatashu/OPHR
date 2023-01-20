package com.cynoteck.petofyOPHR.params.otpRequest;

public class SendOtpParameter {
    private String phoneNumber;
    private String emailId;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [phoneNumber = "+phoneNumber+",emailId= "+emailId+"]";
    }
}
