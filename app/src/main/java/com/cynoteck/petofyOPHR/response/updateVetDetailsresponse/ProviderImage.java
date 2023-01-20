package com.cynoteck.petofyOPHR.response.updateVetDetailsresponse;

public class ProviderImage
{
    private String isProfileImage;

    private String providerId;

    private String imageUrl;

    private String id;

    public String getIsProfileImage ()
{
    return isProfileImage;
}

    public void setIsProfileImage (String isProfileImage)
    {
        this.isProfileImage = isProfileImage;
    }

    public String getProviderId ()
    {
        return providerId;
    }

    public void setProviderId (String providerId)
    {
        this.providerId = providerId;
    }

    public String getImageUrl ()
    {
        return imageUrl;
    }

    public void setImageUrl (String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [isProfileImage = "+isProfileImage+", providerId = "+providerId+", imageUrl = "+imageUrl+", id = "+id+"]";
    }
}
			
	
			
	