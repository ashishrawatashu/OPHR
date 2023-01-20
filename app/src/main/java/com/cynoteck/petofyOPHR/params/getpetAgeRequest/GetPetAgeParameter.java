package com.cynoteck.petofyOPHR.params.getpetAgeRequest;

public class GetPetAgeParameter {
    private String dateOfBirth;

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "ClassPojo[" +
                "dateOfBirth= " + dateOfBirth +
                "]";
    }
}
