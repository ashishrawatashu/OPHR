package com.cynoteck.petofyOPHR.params.searchRemarksParameter;

public class SearchRemaksRequest {

    private SearchRemaksParametr data;

    public SearchRemaksParametr getAddPetParams() {
        return data;
    }

    public void setAddPetParams(SearchRemaksParametr data) {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+ data +"]";
    }
}
