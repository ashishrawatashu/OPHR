package com.cynoteck.petofyOPHR.params.getFirstVaccine;

public class GetFirstVaccineRequest {

    private GetFirstVaccineModel data;

    public GetFirstVaccineModel getData() {
        return data;
    }

    public void setData(GetFirstVaccineModel data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ClassPojo[" +
                "data=" + data +
                "]";
    }
}
