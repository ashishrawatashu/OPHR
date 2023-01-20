package com.cynoteck.petofyOPHR.response.ClinicVistResponse;

import com.cynoteck.petofyOPHR.response.getPetReportsResponse.PagingHeader;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.getPetClinicVisitsListsResponse.PetClinicVisitList;

import java.util.List;

public class ClinicVisitResponse {
    private List<PetClinicVisitList> petClinicVisitList = null;
    private PagingHeader pagingHeader;

    public List<PetClinicVisitList> getPetClinicVisitList() {
        return petClinicVisitList;
    }

    public void setPetClinicVisitList(List<PetClinicVisitList> petClinicVisitList) {
        this.petClinicVisitList = petClinicVisitList;
    }

    public PagingHeader getPagingHeader() {
        return pagingHeader;
    }

    public void setPagingHeader(PagingHeader pagingHeader) {
        this.pagingHeader = pagingHeader;
    }

    @Override
    public String toString() {
        return "ClassPojo[" +
                "petClinicVisitList=" + petClinicVisitList +
                ", pagingHeader=" + pagingHeader +
                "]";
    }
}
