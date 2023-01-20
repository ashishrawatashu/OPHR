package com.cynoteck.petofyOPHR.response.getPetIdCardResponse;

import com.cynoteck.petofyOPHR.response.Header;
import com.cynoteck.petofyOPHR.response.Response;

public class PetIdCardResponse {
    private GetPetIdCardData data;

    private Response response;

    private Header header;

    public GetPetIdCardData getData ()
    {
        return data;
    }

    public void setData (GetPetIdCardData data)
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

