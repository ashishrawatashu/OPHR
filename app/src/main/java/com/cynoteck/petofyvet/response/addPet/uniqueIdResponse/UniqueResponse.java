package com.cynoteck.petofyvet.response.addPet.uniqueIdResponse;

import com.cynoteck.petofyvet.response.Header;
import com.cynoteck.petofyvet.response.Response;

public class UniqueResponse {
    private UniqueKeyModel data;

    private Response response;

    private Header header;

    public UniqueKeyModel getData() {
        return data;
    }

    public void setData(UniqueKeyModel data) {
        this.data = data;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+ data +", response = "+ response +", header = "+header+"]";
    }
}
