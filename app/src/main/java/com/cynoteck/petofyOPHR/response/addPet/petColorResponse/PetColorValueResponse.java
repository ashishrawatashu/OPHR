package com.cynoteck.petofyOPHR.response.addPet.petColorResponse;

import com.cynoteck.petofyOPHR.response.Header;
import com.cynoteck.petofyOPHR.response.Response;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PetColorValueResponse {

    private Header header;

    @SerializedName("data")
    private List<PetColorModel> data = null;

    private Response response;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public List<PetColorModel> getData() {
        return data;
    }

    public void setData(List<PetColorModel> data) {
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
        return "ClassPojo [cityModel = "+ data +", response = "+ response +", header = "+header+"]";
    }
}
