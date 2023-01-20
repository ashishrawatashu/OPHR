package com.cynoteck.petofyOPHR.response.immuniztionHistory;

import com.cynoteck.petofyOPHR.response.Header;
import com.cynoteck.petofyOPHR.response.Response;

import java.util.ArrayList;

public class ImmunizationHistoryResponse {
    private Header header;
    private ArrayList<ImmunizationHistorymodel> data = null;
    private Response response;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public ArrayList<ImmunizationHistorymodel> getData() {
        return data;
    }

    public void setData(ArrayList<ImmunizationHistorymodel> data) {
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
