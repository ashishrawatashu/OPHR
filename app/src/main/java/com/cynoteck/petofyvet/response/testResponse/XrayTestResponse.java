package com.cynoteck.petofyvet.response.testResponse;

import com.cynoteck.petofyvet.response.Header;
import com.cynoteck.petofyvet.response.Response;
import com.cynoteck.petofyvet.response.hospitalTypeListResponse.hospitalAddmissionTypeModel;

import java.util.ArrayList;

public class XrayTestResponse {
    private ArrayList<XrayTestResponseModel> data;

    private Response response;

    private Header header;

    public ArrayList<XrayTestResponseModel> getData ()
    {
        return data;
    }

    public void setData (ArrayList<XrayTestResponseModel> data)
    {
        this.data = data;
    }

    public Response getResponse ()
    {
        return response;
    }

    public void setResponse (Response response)
    {
        this.response = response;
    }

    public Header getHeader ()
    {
        return header;
    }

    public void setHeader (Header header)
    {
        this.header = header;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+", response = "+response+", header = "+header+"]";
    }
}
