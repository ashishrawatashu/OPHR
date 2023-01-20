package com.cynoteck.petofyOPHR.response.getPetHospitalizationResponse.getHospitalizationListResponse;

import com.cynoteck.petofyOPHR.response.getPetReportsResponse.PagingHeader;

import java.util.ArrayList;

public class GetPetHospitalizationData {
    private PagingHeader pagingHeader;

    private ArrayList<PetHospitalizationsList> petHospitalizationsList;

    public PagingHeader getPagingHeader ()
    {
        return pagingHeader;
    }

    public void setPagingHeader (PagingHeader pagingHeader)
    {
        this.pagingHeader = pagingHeader;
    }

    public ArrayList<PetHospitalizationsList> getPetHospitalizationsList ()
    {
        return petHospitalizationsList;
    }

    public void setPetHospitalizationsList (ArrayList<PetHospitalizationsList> petHospitalizationsList)
    {
        this.petHospitalizationsList = petHospitalizationsList;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [pagingHeader = "+pagingHeader+", petHospitalizationsList = "+petHospitalizationsList+"]";
    }
}