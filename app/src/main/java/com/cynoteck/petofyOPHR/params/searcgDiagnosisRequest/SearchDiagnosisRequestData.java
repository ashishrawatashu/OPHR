package com.cynoteck.petofyOPHR.params.searcgDiagnosisRequest;

public class SearchDiagnosisRequestData {
    private SearchDiagnosisParameter data;

    public SearchDiagnosisParameter getData() {
        return data;
    }

    public void setData(SearchDiagnosisParameter data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ClassPojo[" +
                "data=" + data +
                "]";
    }
}
