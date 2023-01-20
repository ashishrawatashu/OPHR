package com.cynoteck.petofyOPHR.response.getXRayReports.getPetTestAndXRayResponse;

import com.cynoteck.petofyOPHR.response.getPetReportsResponse.PagingHeader;

import java.util.ArrayList;

public class GetPetTestAndXRayData {
    private PagingHeader pagingHeader;

    private ArrayList<PetTestsAndXrayList> petTestsAndXrayList;

    public PagingHeader getPagingHeader ()
    {
        return pagingHeader;
    }

    public void setPagingHeader (PagingHeader pagingHeader)
    {
        this.pagingHeader = pagingHeader;
    }

    public ArrayList<PetTestsAndXrayList> getPetTestsAndXrayList ()
    {
        return petTestsAndXrayList;
    }

    public void setPetTestsAndXrayList (ArrayList<PetTestsAndXrayList> petTestsAndXrayList)
    {
        this.petTestsAndXrayList = petTestsAndXrayList;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [pagingHeader = "+pagingHeader+", petTestsAndXrayList = "+petTestsAndXrayList+"]";
    }
}
