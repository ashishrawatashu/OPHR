package com.cynoteck.petofyOPHR.response.bankAccountResponse;

public class GetBankAccoutsData {
    private String account_type;

    private String batch_id;

    private String active;

    private String created_at;

    private String id;

    private String contact_id;

    private String entity;

    private Bank_account bank_account;

    public String getAccount_type ()
    {
        return account_type;
    }

    public void setAccount_type (String account_type)
    {
        this.account_type = account_type;
    }

    public String getBatch_id ()
    {
        return batch_id;
    }

    public void setBatch_id (String batch_id)
    {
        this.batch_id = batch_id;
    }

    public String getActive ()
    {
        return active;
    }

    public void setActive (String active)
    {
        this.active = active;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getContact_id ()
    {
        return contact_id;
    }

    public void setContact_id (String contact_id)
    {
        this.contact_id = contact_id;
    }

    public String getEntity ()
    {
        return entity;
    }

    public void setEntity (String entity)
    {
        this.entity = entity;
    }

    public Bank_account getBank_account ()
    {
        return bank_account;
    }

    public void setBank_account (Bank_account bank_account)
    {
        this.bank_account = bank_account;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [account_type = "+account_type+", batch_id = "+batch_id+", active = "+active+", created_at = "+created_at+", id = "+id+", contact_id = "+contact_id+", entity = "+entity+", bank_account = "+bank_account+"]";
    }
}

