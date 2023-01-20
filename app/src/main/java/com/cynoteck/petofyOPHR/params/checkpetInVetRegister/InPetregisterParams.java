package com.cynoteck.petofyOPHR.params.checkpetInVetRegister;

public class InPetregisterParams {
    private String uniqueId;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [uniqueId = "+uniqueId+"]";
    }
}
