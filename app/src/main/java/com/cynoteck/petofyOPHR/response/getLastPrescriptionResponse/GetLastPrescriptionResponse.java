package com.cynoteck.petofyOPHR.response.getLastPrescriptionResponse;

import com.cynoteck.petofyOPHR.response.Header;
import com.cynoteck.petofyOPHR.response.Response;
import com.cynoteck.petofyOPHR.response.recentVisitResponse.Data;

public class GetLastPrescriptionResponse {
    private GetLastPrescriptionData data;

    private Response response;

    private Header header;

    public GetLastPrescriptionData getData ()
    {
        return data;
    }

    public void setData (GetLastPrescriptionData data)
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
