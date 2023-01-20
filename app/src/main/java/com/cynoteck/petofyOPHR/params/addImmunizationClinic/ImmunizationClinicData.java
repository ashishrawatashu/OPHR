package com.cynoteck.petofyOPHR.params.addImmunizationClinic;

public class ImmunizationClinicData {
    private ImmunizationAddClinicModel data;

    public ImmunizationAddClinicModel getAddPetParams() {
        return data;
    }

    public void setAddPetParams(ImmunizationAddClinicModel data) {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+ data +"]";
    }
}
