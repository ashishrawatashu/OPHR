package com.cynoteck.petofyOPHR.response.updateVetDetailsresponse;


public class ProviderPetType
{
    private String providerId;

    private PetType petType;

    private String petTypeId;

    private String id;

    public String getProviderId ()
    {
        return providerId;
    }

    public void setProviderId (String providerId)
    {
        this.providerId = providerId;
    }

    public PetType getPetType ()
    {
        return petType;
    }

    public void setPetType (PetType petType)
    {
        this.petType = petType;
    }

    public String getPetTypeId ()
    {
        return petTypeId;
    }

    public void setPetTypeId (String petTypeId)
    {
        this.petTypeId = petTypeId;
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
        return "ClassPojo [providerId = "+providerId+", petType = "+petType+", petTypeId = "+petTypeId+", id = "+id+"]";
    }
}

