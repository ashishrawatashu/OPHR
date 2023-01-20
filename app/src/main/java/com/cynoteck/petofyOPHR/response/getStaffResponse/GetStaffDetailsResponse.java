package com.cynoteck.petofyOPHR.response.getStaffResponse;

import com.cynoteck.petofyOPHR.response.Header;
import com.cynoteck.petofyOPHR.response.Response;

public class GetStaffDetailsResponse {
    private GetStaffDeatilsData data;

    private Response response;

    private Header header;

    public GetStaffDeatilsData getData ()
    {
        return data;
    }

    public void setData (GetStaffDeatilsData data)
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
