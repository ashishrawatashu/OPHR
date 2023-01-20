package com.cynoteck.petofyOPHR.params.petBreedRequest;


public class BreedRequest {

    private String getAll;

    private String petCategoryId;

    public String getGetAll() {
        return getAll;
    }

    public void setGetAll(String getAll) {
        this.getAll = getAll;
    }

    public String getPetCategoryId() {
        return petCategoryId;
    }

    public void setPetCategoryId(String petCategoryId) {
        this.petCategoryId = petCategoryId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [getAll = "+getAll+", petCategoryId = "+petCategoryId+"]";
    }
}
