package com.cynoteck.petofyOPHR.response.searchRemaks;

import com.cynoteck.petofyOPHR.response.Header;
import com.cynoteck.petofyOPHR.response.Response;

import java.util.ArrayList;

public class SearchRemaksResponse {
    private ArrayList<SearchRemarksModel> data;

    private Response response;

    private Header header;

    public ArrayList<SearchRemarksModel> getData ()
    {
        return data;
    }

    public void setData (ArrayList<SearchRemarksModel> data)
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
