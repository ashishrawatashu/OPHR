package com.cynoteck.petofyOPHR.params.addBankAccountParams;

public class AddBankAccountRequest {
    private AddBankAccountParams data;

    public AddBankAccountParams getData ()
    {
        return data;
    }

    public void setData (AddBankAccountParams data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+"]";
    }
}

