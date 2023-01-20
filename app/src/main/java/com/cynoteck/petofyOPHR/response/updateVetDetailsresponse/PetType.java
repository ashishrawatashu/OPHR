package com.cynoteck.petofyOPHR.response.updateVetDetailsresponse;

public class PetType
{
    private String[] petDonation;

    private String[] rescueInjuredAnimal;

    private String[] product;

    private String[] petAge;

    private String[] providerPetType;

    private String[] petColor;

    private String isActive;

    private String[] petName;

    private String[] petBreed;

    private String[] petDetail;

    private String id;

    private String petType1;

    private String[] petSize;

    public String[] getPetDonation ()
    {
        return petDonation;
    }

    public void setPetDonation (String[] petDonation)
    {
        this.petDonation = petDonation;
    }

    public String[] getRescueInjuredAnimal ()
    {
        return rescueInjuredAnimal;
    }

    public void setRescueInjuredAnimal (String[] rescueInjuredAnimal)
    {
        this.rescueInjuredAnimal = rescueInjuredAnimal;
    }

    public String[] getProduct ()
    {
        return product;
    }

    public void setProduct (String[] product)
    {
        this.product = product;
    }

    public String[] getPetAge ()
    {
        return petAge;
    }

    public void setPetAge (String[] petAge)
    {
        this.petAge = petAge;
    }

    public String[] getProviderPetType ()
    {
        return providerPetType;
    }

    public void setProviderPetType (String[] providerPetType)
    {
        this.providerPetType = providerPetType;
    }

    public String[] getPetColor ()
    {
        return petColor;
    }

    public void setPetColor (String[] petColor)
    {
        this.petColor = petColor;
    }

    public String getIsActive ()
    {
        return isActive;
    }

    public void setIsActive (String isActive)
    {
        this.isActive = isActive;
    }

    public String[] getPetName ()
    {
        return petName;
    }

    public void setPetName (String[] petName)
    {
        this.petName = petName;
    }

    public String[] getPetBreed ()
    {
        return petBreed;
    }

    public void setPetBreed (String[] petBreed)
    {
        this.petBreed = petBreed;
    }

    public String[] getPetDetail ()
    {
        return petDetail;
    }

    public void setPetDetail (String[] petDetail)
    {
        this.petDetail = petDetail;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getPetType1 ()
    {
        return petType1;
    }

    public void setPetType1 (String petType1)
    {
        this.petType1 = petType1;
    }

    public String[] getPetSize ()
    {
        return petSize;
    }

    public void setPetSize (String[] petSize)
    {
        this.petSize = petSize;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [petDonation = "+petDonation+", rescueInjuredAnimal = "+rescueInjuredAnimal+", product = "+product+", petAge = "+petAge+", providerPetType = "+providerPetType+", petColor = "+petColor+", isActive = "+isActive+", petName = "+petName+", petBreed = "+petBreed+", petDetail = "+petDetail+", id = "+id+", petType1 = "+petType1+", petSize = "+petSize+"]";
    }
}



