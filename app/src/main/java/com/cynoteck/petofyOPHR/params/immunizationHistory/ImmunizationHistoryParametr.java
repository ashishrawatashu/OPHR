package com.cynoteck.petofyOPHR.params.immunizationHistory;

public class ImmunizationHistoryParametr {
    private String EncryptedId;

    public String getEncryptedId() {
        return EncryptedId;
    }

    public void setEncryptedId(String encryptedId) {
        this.EncryptedId = EncryptedId;
    }

    @Override
    public String toString() {
        return "ClassPojo[" +
                "EncryptedId= " + EncryptedId +
                "]";
    }
}
