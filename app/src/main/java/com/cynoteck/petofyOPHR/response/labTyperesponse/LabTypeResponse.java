package com.cynoteck.petofyOPHR.response.labTyperesponse;

import com.cynoteck.petofyOPHR.response.Header;
import com.cynoteck.petofyOPHR.response.Response;

import java.util.ArrayList;

public class LabTypeResponse {

    private ArrayList<LabTypeModel> data;

    private Response response;

    private Header header;

    public ArrayList<LabTypeModel> getData ()
    {
        return data;
    }

    public void setData (ArrayList<LabTypeModel> data)
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
