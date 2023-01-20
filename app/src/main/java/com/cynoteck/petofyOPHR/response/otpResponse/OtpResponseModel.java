package com.cynoteck.petofyOPHR.response.otpResponse;

public class OtpResponseModel {
    private String success;
    private String otp;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "ClassPojo [success = "+success+", otp= "+otp+"]";

    }

}
