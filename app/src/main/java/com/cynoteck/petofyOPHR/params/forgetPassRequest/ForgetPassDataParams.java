package com.cynoteck.petofyOPHR.params.forgetPassRequest;

public class ForgetPassDataParams{
    private String email;

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [email = "+email+"]";
    }
}
