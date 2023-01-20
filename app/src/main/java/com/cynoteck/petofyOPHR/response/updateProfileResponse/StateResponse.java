package com.cynoteck.petofyOPHR.response.updateProfileResponse;

import com.cynoteck.petofyOPHR.response.Header;
import com.cynoteck.petofyOPHR.response.Response;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StateResponse {
    private Header header;

    @SerializedName("data")
    private List<stateModel> data = null;

    private Response response;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public List<stateModel> getData() {
        return data;
    }

    public void setData(List<stateModel> data) {
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
        return "ClassPojo [stateModel = "+ data +", response = "+ response +", header = "+header+"]";
    }

}
