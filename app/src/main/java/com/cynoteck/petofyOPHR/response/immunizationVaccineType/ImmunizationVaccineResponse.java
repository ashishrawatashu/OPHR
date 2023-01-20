package com.cynoteck.petofyOPHR.response.immunizationVaccineType;

import com.cynoteck.petofyOPHR.response.Header;
import com.cynoteck.petofyOPHR.response.Response;

public class ImmunizationVaccineResponse {


    private Header header;
    private ImmunizationTypeModel data;
    private Response response;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public ImmunizationTypeModel getData() {
        return data;
    }

    public void setData(ImmunizationTypeModel data) {
        this.data = data;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
    @Override
    public String toString() {
        return "ClassPojo [header = "+header+", data= "+data+", response= "+response+"]";

    }
}
