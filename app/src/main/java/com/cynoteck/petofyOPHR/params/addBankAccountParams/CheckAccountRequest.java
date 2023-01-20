package com.cynoteck.petofyOPHR.params.addBankAccountParams;

public class CheckAccountRequest {
    private CheckAccountParams data;

    public CheckAccountParams getData ()
    {
        return data;
    }

    public void setData (CheckAccountParams data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+"]";
    }
}

