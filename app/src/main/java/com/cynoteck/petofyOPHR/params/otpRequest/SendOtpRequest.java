package com.cynoteck.petofyOPHR.params.otpRequest;

public class SendOtpRequest {

    private SendOtpParameter data;

    public SendOtpParameter getData() {
        return data;
    }

    public void setData(SendOtpParameter data) {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+"]";
    }
}
