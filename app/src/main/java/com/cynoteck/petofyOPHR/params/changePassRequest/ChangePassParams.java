package com.cynoteck.petofyOPHR.params.changePassRequest;

public class ChangePassParams {

    private String oldPassword;

    private String newPassword;

    private String confirmPassword;

    public String getOldPassword ()
    {
        return oldPassword;
    }

    public void setOldPassword (String oldPassword)
    {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword ()
    {
        return newPassword;
    }

    public void setNewPassword (String newPassword)
    {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword ()
    {
        return confirmPassword;
    }

    public void setConfirmPassword (String confirmPassword)
    {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [oldPassword = "+oldPassword+", newPassword = "+newPassword+", confirmPassword = "+confirmPassword+"]";
    }
}