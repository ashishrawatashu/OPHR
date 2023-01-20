package com.cynoteck.petofyOPHR.params.getVaccinationDetails;

public class GetVaccinationRequest {
    private GetVaccinationModelParameter data;

    public GetVaccinationModelParameter getData() {
        return data;
    }

    public void setData(GetVaccinationModelParameter data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ClassPojo[" +
                "data=" + data +
                "]";
    }
}
