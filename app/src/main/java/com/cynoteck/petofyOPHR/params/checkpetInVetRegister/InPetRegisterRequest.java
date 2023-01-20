package com.cynoteck.petofyOPHR.params.checkpetInVetRegister;

public class InPetRegisterRequest {
    private InPetregisterParams data;

    public InPetregisterParams getData ()
    {
        return data;
    }

    public void setData (InPetregisterParams data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+"]";
    }
}
