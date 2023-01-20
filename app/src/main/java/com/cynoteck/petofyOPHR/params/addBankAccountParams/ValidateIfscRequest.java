package com.cynoteck.petofyOPHR.params.addBankAccountParams;

public class ValidateIfscRequest {
    private ValidateIfscParams data;

    public ValidateIfscParams getData ()
    {
        return data;
    }

    public void setData (ValidateIfscParams data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+"]";
    }
}
