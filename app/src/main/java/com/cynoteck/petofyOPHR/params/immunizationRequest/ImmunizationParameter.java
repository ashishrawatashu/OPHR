package com.cynoteck.petofyOPHR.params.immunizationRequest;

public class ImmunizationParameter {
    private String CategoryId;
    private String PetDOB;
    private String encryptedId;

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String CategoryId) {
        this.CategoryId = CategoryId;
    }

    public String getPetDOB() {
        return PetDOB;
    }

    public void setPetDOB(String petDOB) {
        this.PetDOB = petDOB;
    }

    public String getEncryptedId() {
        return encryptedId;
    }

    public void setEncryptedId(String encryptedId) {
        this.encryptedId = encryptedId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [CategoryId = "+CategoryId+", PetDOB = "+PetDOB+", encryptedId = "+encryptedId+"]";
    }
}
