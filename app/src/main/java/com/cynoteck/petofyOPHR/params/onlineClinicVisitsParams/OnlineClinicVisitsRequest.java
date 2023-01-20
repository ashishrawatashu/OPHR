package com.cynoteck.petofyOPHR.params.onlineClinicVisitsParams;

public class OnlineClinicVisitsRequest {
    private OnlineClinicVisitisParameter data;

    public OnlineClinicVisitisParameter getData() {
        return data;
    }

    public void setData(OnlineClinicVisitisParameter data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ClassPojo[" +
                "data=" + data +
                "]";
    }
}
