package com.cynoteck.petofyOPHR.response.getMyVisitedPetRecordResponse;

import com.cynoteck.petofyOPHR.response.getPetReportsResponse.PagingHeader;

import java.util.ArrayList;

public class GetMyVisitPetRecordData {
    private PagingHeader pagingHeader;

    private ArrayList<GetMyVisitPetRecordPetClinicVisitList> petClinicVisitList;

    public PagingHeader getPagingHeader ()
    {
        return pagingHeader;
    }

    public void setPagingHeader (PagingHeader pagingHeader)
    {
        this.pagingHeader = pagingHeader;
    }

    public ArrayList<GetMyVisitPetRecordPetClinicVisitList> getPetClinicVisitList ()
    {
        return petClinicVisitList;
    }

    public void setPetClinicVisitList (ArrayList<GetMyVisitPetRecordPetClinicVisitList> petClinicVisitList)
    {
        this.petClinicVisitList = petClinicVisitList;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [pagingHeader = "+pagingHeader+", petClinicVisitList = "+petClinicVisitList+"]";
    }
}
