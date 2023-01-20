package com.cynoteck.petofyOPHR.response.addPetClinicresponse;

public class AddPetClinicModel {
    private Integer id;
    private String encryptedId;
    private String status;

    public String getEncryptedId() {
        return encryptedId;
    }

    public void setEncryptedId(String encryptedId) {
        this.encryptedId = encryptedId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [encryptedId = "+encryptedId+", id = "+id+", status = "+status+"]";
    }

}
