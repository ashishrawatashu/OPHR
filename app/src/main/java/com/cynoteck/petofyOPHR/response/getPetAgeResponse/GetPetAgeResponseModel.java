package com.cynoteck.petofyOPHR.response.getPetAgeResponse;

public class GetPetAgeResponseModel {
    private String petAge;
    private String dateOfBirth;
    private String ageNumber;

    public String getPetAge() {
        return petAge;
    }

    public void setPetAge(String petAge) {
        this.petAge = petAge;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAgeNumber() {
        return ageNumber;
    }

    public void setAgeNumber(String ageNumber) {
        this.ageNumber = ageNumber;
    }

    @Override
    public String toString() {
        return "ClassPojo{" +
                "petAge= " + petAge +
                ", dateOfBirth= " + dateOfBirth +
                ", ageNumber= " + ageNumber +
                "]";
    }
}
