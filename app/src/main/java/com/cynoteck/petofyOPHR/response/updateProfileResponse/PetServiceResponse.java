package com.cynoteck.petofyOPHR.response.updateProfileResponse;

import com.cynoteck.petofyOPHR.response.Header;
import com.cynoteck.petofyOPHR.response.Response;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PetServiceResponse {

    private Header header;

    @SerializedName("data")
    private ArrayList<PetServiceModel> data = null;

    private Response response;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public ArrayList<PetServiceModel> getData() {
        return data;
    }

    public void setData(ArrayList<PetServiceModel> data) {
        this.data = data;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [petservicemodel = "+ data +", response = "+ response +", header = "+header+"]";
    }
}
