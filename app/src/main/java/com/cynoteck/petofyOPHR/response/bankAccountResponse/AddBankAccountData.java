package com.cynoteck.petofyOPHR.response.bankAccountResponse;

public class AddBankAccountData {
    private String funAccountId;

    private String active;

    private String bankName;

    private String type;

    private String batchId;

    private String accountNumber;

    private String referenceId;

    private String createdAt;

    private String contact;

    private String name;

    private String id;

    private String ifsc;

    private String entity;

    private String email;

    public String getFunAccountId ()
    {
        return funAccountId;
    }

    public void setFunAccountId (String funAccountId)
    {
        this.funAccountId = funAccountId;
    }

    public String getActive ()
    {
        return active;
    }

    public void setActive (String active)
    {
        this.active = active;
    }

    public String getBankName ()
    {
        return bankName;
    }

    public void setBankName (String bankName)
    {
        this.bankName = bankName;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getBatchId ()
    {
        return batchId;
    }

    public void setBatchId (String batchId)
    {
        this.batchId = batchId;
    }

    public String getAccountNumber ()
    {
        return accountNumber;
    }

    public void setAccountNumber (String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    public String getReferenceId ()
    {
        return referenceId;
    }

    public void setReferenceId (String referenceId)
    {
        this.referenceId = referenceId;
    }

    public String getCreatedAt ()
    {
        return createdAt;
    }

    public void setCreatedAt (String createdAt)
    {
        this.createdAt = createdAt;
    }

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

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getIfsc ()
    {
        return ifsc;
    }

    public void setIfsc (String ifsc)
    {
        this.ifsc = ifsc;
    }

    public String getEntity ()
    {
        return entity;
    }

    public void setEntity (String entity)
    {
        this.entity = entity;
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
        return "ClassPojo [funAccountId = "+funAccountId+", active = "+active+", bankName = "+bankName+", type = "+type+", batchId = "+batchId+", accountNumber = "+accountNumber+", referenceId = "+referenceId+", createdAt = "+createdAt+", contact = "+contact+", name = "+name+", id = "+id+", ifsc = "+ifsc+", entity = "+entity+", email = "+email+"]";
    }
}
