package com.cynoteck.petofyOPHR.response.getLabTestReportResponse.getPetLabWorkListResponse;

import java.util.ArrayList;

public class PetLabWorkData {
    private String pagingHeader;

    private ArrayList<PetLabWorkList> petLabWorkList;

    public String getPagingHeader ()
    {
        return pagingHeader;
    }

    public void setPagingHeader (String pagingHeader)
    {
        this.pagingHeader = pagingHeader;
    }

    public ArrayList<PetLabWorkList> getPetLabWorkList ()
    {
        return petLabWorkList;
    }

    public void setPetLabWorkList (ArrayList<PetLabWorkList> petLabWorkList)
    {
        this.petLabWorkList = petLabWorkList;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [pagingHeader = "+pagingHeader+", petLabWorkList = "+petLabWorkList+"]";
    }
}
			
			