package com.cynoteck.petofyOPHR.response.getPetReportsResponse.getPetClinicVisitsListsResponse;

import com.cynoteck.petofyOPHR.response.getPetReportsResponse.PagingHeader;

import java.util.ArrayList;

public class GetPetClinicVisitListData {
    private PagingHeader pagingHeader;

    private ArrayList<PetClinicVisitList> petClinicVisitList;

    public PagingHeader getPagingHeader ()
    {
        return pagingHeader;
    }

    public void setPagingHeader (PagingHeader pagingHeader)
    {
        this.pagingHeader = pagingHeader;
    }

    public ArrayList<PetClinicVisitList> getPetClinicVisitList ()
    {
        return petClinicVisitList;
    }

    public void setPetClinicVisitList (ArrayList<PetClinicVisitList> petClinicVisitList)
    {
        this.petClinicVisitList = petClinicVisitList;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [pagingHeader = "+pagingHeader+", petClinicVisitList = "+petClinicVisitList+"]";
    }
}