package com.cynoteck.petofyOPHR.response.recentEntrys;

import java.util.List;

public class RecentEntrysData {
    private List<PetClinicVisitList> petClinicVisitList = null;
    private String pagingHeader;

    public List<PetClinicVisitList> getPetClinicVisitList() {
        return petClinicVisitList;
    }

    public void setPetClinicVisitList(List<PetClinicVisitList> petClinicVisitList) {
        this.petClinicVisitList = petClinicVisitList;
    }

    public Object getPagingHeader() {
        return pagingHeader;
    }

    public void setPagingHeader(String pagingHeader) {
        this.pagingHeader = pagingHeader;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [petClinicVisitList = "+petClinicVisitList+", pagingHeader = "+pagingHeader+"]";
    }

}
