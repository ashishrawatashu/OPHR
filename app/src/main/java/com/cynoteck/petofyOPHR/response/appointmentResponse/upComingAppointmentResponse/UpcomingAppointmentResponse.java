package com.cynoteck.petofyOPHR.response.appointmentResponse.upComingAppointmentResponse;

import com.cynoteck.petofyOPHR.response.Header;
import com.cynoteck.petofyOPHR.response.Response;
import com.cynoteck.petofyOPHR.response.appointmentResponse.GetAppointmentDates;

import java.util.ArrayList;

public class UpcomingAppointmentResponse {
    private ArrayList<UpcomingAppointmentData> data;

    private Response response;

    private Header header;

    public ArrayList<UpcomingAppointmentData> getData ()
    {
        return data;
    }

    public void setData (ArrayList<UpcomingAppointmentData> data)
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
