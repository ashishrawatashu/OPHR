package com.cynoteck.petofyOPHR.response.addImmunizationClinicResponse;

import com.cynoteck.petofyOPHR.response.Header;
import com.cynoteck.petofyOPHR.response.Response;

public class ImmunizationAddResponse {

    private Header header;
    private ImmunizationAddClinicResponseData data;
    private Response response;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public ImmunizationAddClinicResponseData getData() {
        return data;
    }

    public void setData(ImmunizationAddClinicResponseData data) {
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
        return "ClassPojo [data = "+data+", response = "+response+", header = "+header+"]";
    }
}
