package com.cynoteck.petofyOPHR.response.updateVetDetailsresponse;


public class ServiceType
{
    private String[] providerServiceType;

    private String imageUrl;

    private String id;

    private String isActive;

    private String iconClass;

    private String serviceType1;

    public String[] getProviderServiceType ()
    {
        return providerServiceType;
    }

    public void setProviderServiceType (String[] providerServiceType)
    {
        this.providerServiceType = providerServiceType;
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

    public String getIsActive ()
    {
        return isActive;
    }

    public void setIsActive (String isActive)
    {
        this.isActive = isActive;
    }

    public String getIconClass ()
    {
        return iconClass;
    }

    public void setIconClass (String iconClass)
    {
        this.iconClass = iconClass;
    }

    public String getServiceType1 ()
    {
        return serviceType1;
    }

    public void setServiceType1 (String serviceType1)
    {
        this.serviceType1 = serviceType1;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [providerServiceType = "+providerServiceType+", imageUrl = "+imageUrl+", id = "+id+", isActive = "+isActive+", iconClass = "+iconClass+", serviceType1 = "+serviceType1+"]";
    }
}

