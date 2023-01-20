package com.cynoteck.petofyOPHR.response.staffPermissionListResponse;

import com.cynoteck.petofyOPHR.response.Header;
import com.cynoteck.petofyOPHR.response.Response;

import java.util.List;

public class StaffPermissionResponse {

    private Header header;
    private List<StaffPermissionResponseModel> data = null;
    private Response response;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public List<StaffPermissionResponseModel> getData() {
        return data;
    }

    public void setData(List<StaffPermissionResponseModel> data) {
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
