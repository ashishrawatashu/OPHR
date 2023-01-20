package com.cynoteck.petofyOPHR.params.uploadVetProfileImageParams;

public class UploadVetProfileImageData {
    private UploadProfileImageParams data;

    public UploadProfileImageParams getData ()
    {
        return data;
    }

    public void setData (UploadProfileImageParams data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+"]";
    }
}
