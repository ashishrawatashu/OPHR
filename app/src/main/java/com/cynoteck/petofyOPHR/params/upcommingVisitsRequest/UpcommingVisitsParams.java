package com.cynoteck.petofyOPHR.params.upcommingVisitsRequest;

public class UpcommingVisitsParams {
    private String FromDate;

    private String Todate;

    public String getFromDate ()
    {
        return FromDate;
    }

    public void setFromDate (String FromDate)
    {
        this.FromDate = FromDate;
    }

    public String getTodate ()
    {
        return Todate;
    }

    public void setTodate (String Todate)
    {
        this.Todate = Todate;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [FromDate = "+FromDate+", Todate = "+Todate+"]";
    }
}

