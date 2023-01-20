package com.cynoteck.petofyOPHR.response;


public class Response
{
    private String redirectUrl;

    private String responseMessage;

    private String value;

    private String responseCode;

    private String token;

    public String getRedirectUrl ()
    {
        return redirectUrl;
    }

    public void setRedirectUrl (String redirectUrl)
    {
        this.redirectUrl = redirectUrl;
    }

    public String getResponseMessage ()
    {
        return responseMessage;
    }

    public void setResponseMessage (String responseMessage)
    {
        this.responseMessage = responseMessage;
    }

    public String getValue ()
{
    return value;
}

    public void setValue (String value)
    {
        this.value = value;
    }

    public String getResponseCode ()
    {
        return responseCode;
    }

    public void setResponseCode (String responseCode)
    {
        this.responseCode = responseCode;
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
        return "ClassPojo [redirectUrl = "+redirectUrl+", responseMessage = "+responseMessage+", value = "+value+", responseCode = "+responseCode+", token = "+token+"]";
    }
}
