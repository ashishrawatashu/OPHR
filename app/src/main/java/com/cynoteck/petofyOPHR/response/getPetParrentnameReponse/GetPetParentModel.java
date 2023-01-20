package com.cynoteck.petofyOPHR.response.getPetParrentnameReponse;

public class GetPetParentModel {

    private String petParentName;
    private String contactNumber;
    private String address;

    public String getPetParentName() {
        return petParentName;
    }

    public void setPetParentName(String petParentName) {
        this.petParentName = petParentName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ClassPojo[" +
                "petParentName= " + petParentName +
                ", contactNumber= " + contactNumber +
                ", address= " + address +
                "]";
    }
}
