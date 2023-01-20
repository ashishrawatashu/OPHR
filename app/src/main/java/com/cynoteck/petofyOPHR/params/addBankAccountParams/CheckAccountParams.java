package com.cynoteck.petofyOPHR.params.addBankAccountParams;

public class CheckAccountParams {
    private String accountNumber;

    public String getAccountNumber ()
    {
        return accountNumber;
    }

    public void setAccountNumber (String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [accountNumber = "+accountNumber+"]";
    }
}
