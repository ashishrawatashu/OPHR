package com.cynoteck.petofyOPHR.params.loginRequest;

public class LoginRequest {
    private String Email;

    private String DeviceId;

    private String DeviceIp;

    private String Password;

    public String getEmail ()
    {
        return Email;
    }

    public void setEmail (String Email)
    {
        this.Email = Email;
    }

    public String getDeviceId ()
    {
        return DeviceId;
    }

    public void setDeviceId (String DeviceId)
    {
        this.DeviceId = DeviceId;
    }

    public String getDeviceIp ()
    {
        return DeviceIp;
    }

    public void setDeviceIp (String DeviceIp)
    {
        this.DeviceIp = DeviceIp;
    }

    public String getPassword ()
    {
        return Password;
    }

    public void setPassword (String Password)
    {
        this.Password = Password;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Email = "+Email+", DeviceId = "+DeviceId+", DeviceIp = "+DeviceIp+", Password = "+Password+"]";
    }
}
