package com.cynoteck.petofyOPHR.response.updateVetDetailsresponse;

public class ProviderServiceType
{
    private ServiceType serviceType;

    private String providerId;

    private String serviceTypeId;

    private String id;

    public ServiceType getServiceType ()
    {
        return serviceType;
    }

    public void setServiceType (ServiceType serviceType)
    {
        this.serviceType = serviceType;
    }

    public String getProviderId ()
    {
        return providerId;
    }

    public void setProviderId (String providerId)
    {
        this.providerId = providerId;
    }

    public String getServiceTypeId ()
    {
        return serviceTypeId;
    }

    public void setServiceTypeId (String serviceTypeId)
    {
        this.serviceTypeId = serviceTypeId;
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
        return "ClassPojo [serviceType = "+serviceType+", providerId = "+providerId+", serviceTypeId = "+serviceTypeId+", id = "+id+"]";
    }
}

