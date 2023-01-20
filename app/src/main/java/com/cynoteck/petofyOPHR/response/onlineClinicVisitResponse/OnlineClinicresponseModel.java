package com.cynoteck.petofyOPHR.response.onlineClinicVisitResponse;

import com.cynoteck.petofyOPHR.response.getPetReportsResponse.PagingHeader;

import java.util.List;

public class OnlineClinicresponseModel {
    private List<VetAppointmentList> vetAppointmentList = null;
    private PagingHeader pagingHeader;

    public List<VetAppointmentList> getVetAppointmentList() {
        return vetAppointmentList;
    }

    public void setVetAppointmentList(List<VetAppointmentList> vetAppointmentList) {
        this.vetAppointmentList = vetAppointmentList;
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
                "vetAppointmentList=" + vetAppointmentList +
                ", pagingHeader=" + pagingHeader +
                "]";
    }
}
