package com.cynoteck.petofyOPHR.params.addBankAccountParams;

public class AddBankAccountParams {
    private String contact;

    private String name;

    private String ifsc;

    private String accountNumber;

    private String email;

    public String getContact ()
    {
        return contact;
    }

    public void setContact (String contact)
    {
        this.contact = contact;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getIfsc ()
    {
        return ifsc;
    }

    public void setIfsc (String ifsc)
    {
        this.ifsc = ifsc;
    }

    public String getAccountNumber ()
    {
        return accountNumber;
    }

    public void setAccountNumber (String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [contact = "+contact+", name = "+name+", ifsc = "+ifsc+", accountNumber = "+accountNumber+", email = "+email+"]";
    }
}

