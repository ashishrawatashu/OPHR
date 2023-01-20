package com.cynoteck.petofyOPHR.params.searchPetParentRequest;

public class SearchPetParentRequestData {

    private SearchPetParentParameter data;

    public SearchPetParentParameter getData() {
        return data;
    }

    public void setData(SearchPetParentParameter data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ClassPojo[" +
                "data=" + data +
                "]";
    }
}
