package com.cynoteck.petofyOPHR.params.getPetListRequest;

public class GetPetListRequest {
    private GetPetListParams data;

    public GetPetListParams getData() {
        return data;
    }

    public void setData(GetPetListParams data) {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+"]";
    }
}
