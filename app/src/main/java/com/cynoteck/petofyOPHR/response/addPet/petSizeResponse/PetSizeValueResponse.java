package com.cynoteck.petofyOPHR.response.addPet.petSizeResponse;

import com.cynoteck.petofyOPHR.response.Header;
import com.cynoteck.petofyOPHR.response.Response;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PetSizeValueResponse {

    private Header header;

    @SerializedName("data")
    private List<PetSizeModel> data = null;

    private Response response;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public List<PetSizeModel> getData() {
        return data;
    }

    public void setData(List<PetSizeModel> data) {
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
