package com.cynoteck.petofyOPHR.response.bankAccountResponse;

public class Bank_account
{
    private String account_number;

    private String[] notes;

    private String name;

    private String bank_name;

    private String ifsc;

    public String getAccount_number ()
    {
        return account_number;
    }

    public void setAccount_number (String account_number)
    {
        this.account_number = account_number;
    }

    public String[] getNotes ()
    {
        return notes;
    }

    public void setNotes (String[] notes)
    {
        this.notes = notes;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getBank_name ()
{
    return bank_name;
}

    public void setBank_name (String bank_name)
    {
        this.bank_name = bank_name;
    }

    public String getIfsc ()
    {
        return ifsc;
    }

    public void setIfsc (String ifsc)
    {
        this.ifsc = ifsc;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [account_number = "+account_number+", notes = "+notes+", name = "+name+", bank_name = "+bank_name+", ifsc = "+ifsc+"]";
    }
}
			
		