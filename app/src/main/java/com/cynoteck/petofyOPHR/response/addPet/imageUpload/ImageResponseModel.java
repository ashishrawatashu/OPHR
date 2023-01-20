package com.cynoteck.petofyOPHR.response.addPet.imageUpload;

public class ImageResponseModel {
    private String documentUrl;
    private String documentName;

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [documentUrl = "+documentUrl+", documentName = "+documentName+"]";
    }
}
