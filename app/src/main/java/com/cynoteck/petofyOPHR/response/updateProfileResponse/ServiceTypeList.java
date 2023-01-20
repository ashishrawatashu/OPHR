package com.cynoteck.petofyOPHR.response.updateProfileResponse;


public class ServiceTypeList
{
    private String text;

    private String value;

    private String status;

    public String getText ()
    {
        return text;
    }

    public void setText (String text)
    {
        this.text = text;
    }

    public String getValue ()
    {
        return value;
    }

    public void setValue (String value)
    {
        this.value = value;
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
        return "ClassPojo [text = "+text+", value = "+value+", status = "+status+"]";
    }
}

