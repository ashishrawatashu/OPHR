package com.cynoteck.petofyOPHR.params.uploadVetProfileImageParams;

public class UploadProfileImageParams {
    private String profileImageUrl;

    public String getProfileImageUrl ()
    {
        return profileImageUrl;
    }

    public void setProfileImageUrl (String profileImageUrl)
    {
        this.profileImageUrl = profileImageUrl;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [profileImageUrl = "+profileImageUrl+"]";
    }
}
