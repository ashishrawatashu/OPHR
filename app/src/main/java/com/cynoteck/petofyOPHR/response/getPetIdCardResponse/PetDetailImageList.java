package com.cynoteck.petofyOPHR.response.getPetIdCardResponse;

public class PetDetailImageList {
    private String petDetailId;

    private String petDetailImageUrl;

    private String id;

    public String getPetDetailId ()
    {
        return petDetailId;
    }

    public void setPetDetailId (String petDetailId)
    {
        this.petDetailId = petDetailId;
    }

    public String getPetDetailImageUrl ()
    {
        return petDetailImageUrl;
    }

    public void setPetDetailImageUrl (String petDetailImageUrl)
    {
        this.petDetailImageUrl = petDetailImageUrl;
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
        return "ClassPojo [petDetailId = "+petDetailId+", petDetailImageUrl = "+petDetailImageUrl+", id = "+id+"]";
    }
}
			
			