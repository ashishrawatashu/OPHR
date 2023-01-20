package com.cynoteck.petofyOPHR.params.removeVccination;

public class RemoveRequest {

    private RemoveParams data;

    public RemoveParams getData() {
        return data;
    }

    public void setData(RemoveParams data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ClassPojo[" +
                "data=" + data +
                "]";
    }
}
