package com.cynoteck.petofyOPHR.params.getMyVisitPetRecordRequest;

public class GetMyVisistPetRecordParams {
    private String pageNumber;

    private String pageSize;

    private String NatureOfVisiteId;

    private String FromDate;

    private String Todate;

    private String searchData;

    public String getPageNumber ()
    {
        return pageNumber;
    }

    public void setPageNumber (String pageNumber)
    {
        this.pageNumber = pageNumber;
    }

    public String getPageSize ()
    {
        return pageSize;
    }

    public void setPageSize (String pageSize)
    {
        this.pageSize = pageSize;
    }

    public String getNatureOfVisiteId ()
    {
        return NatureOfVisiteId;
    }

    public void setNatureOfVisiteId (String NatureOfVisiteId)
    {
        this.NatureOfVisiteId = NatureOfVisiteId;
    }

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

    public String getSearchData ()
    {
        return searchData;
    }

    public void setSearchData (String searchData)
    {
        this.searchData = searchData;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [pageNumber = "+pageNumber+", pageSize = "+pageSize+", NatureOfVisiteId = "+NatureOfVisiteId+", FromDate = "+FromDate+", Todate = "+Todate+", searchData = "+searchData+"]";
    }
}

