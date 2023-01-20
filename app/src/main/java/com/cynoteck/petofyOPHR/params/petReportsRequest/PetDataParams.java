package com.cynoteck.petofyOPHR.params.petReportsRequest;

public class PetDataParams {

    private int PageNumber;

    private String search_Data;

    private int pageSize;

    public int getPageNumber ()
    {
        return PageNumber;
    }

    public void setPageNumber (int PageNumber)
    {
        this.PageNumber = PageNumber;
    }

    public String getSearch_Data ()
    {
        return search_Data;
    }

    public void setSearch_Data (String search_Data)
    {
        this.search_Data = search_Data;
    }

    public int getPageSize ()
    {
        return pageSize;
    }

    public void setPageSize (int pageSize)
    {
        this.pageSize = pageSize;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [PageNumber = "+PageNumber+", search_Data = "+search_Data+", pageSize = "+pageSize+"]";
    }
}