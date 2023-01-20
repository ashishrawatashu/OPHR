package com.cynoteck.petofyOPHR.response;

public class Header
{
    private String deviceId;

    private String userId;

    private String platform;

    private String token;

    public String getDeviceId ()
{
    return deviceId;
}

    public void setDeviceId (String deviceId)
    {
        this.deviceId = deviceId;
    }

    public String getUserId ()
    {
        return userId;
    }

    public void setUserId (String userId)
    {
        this.userId = userId;
    }

    public String getPlatform ()
{
    return platform;
}

    public void setPlatform (String platform)
    {
        this.platform = platform;
    }

    public String getToken ()
    {
        return token;
    }

    public void setToken (String token)
    {
        this.token = token;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [deviceId = "+deviceId+", userId = "+userId+", platform = "+platform+", token = "+token+"]";
    }
}
