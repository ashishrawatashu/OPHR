package com.cynoteck.petofyOPHR.params.newPetEntryParams;

public class NewPetParams {
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+"]";
    }
}
