package com.cynoteck.petofyOPHR.response.updateProfileResponse;

import com.cynoteck.petofyOPHR.response.Header;
import com.cynoteck.petofyOPHR.response.Response;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PetTypeResponse {
    private Header header;

    @SerializedName("data")
    private List<PetTypesModel> data = null;

    private Response response;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public List<PetTypesModel> getData() {
        return data;
    }

    public void setData(List<PetTypesModel> data) {
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
        return "ClassPojo [petTypeModel = "+ data +", response = "+ response +", header = "+header+"]";
    }
}
