package com.cynoteck.petofyOPHR.response.recentEntrys;

import com.cynoteck.petofyOPHR.response.Header;
import com.cynoteck.petofyOPHR.response.Response;

public class RecentEntrysResponse {
    private Header header;
    private RecentEntrysData data;
    private Response response;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public RecentEntrysData getData() {
        return data;
    }

    public void setData(RecentEntrysData data) {
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
        return "ClassPojo[ header=" + header +", data=" + data +", response=" + response +"]";
    }
}
