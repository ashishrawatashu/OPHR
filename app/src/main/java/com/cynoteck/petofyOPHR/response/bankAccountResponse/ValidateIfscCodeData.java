package com.cynoteck.petofyOPHR.response.bankAccountResponse;

public class ValidateIfscCodeData {
    private String bank;

    private String address;

    private String city;

    private String contact;

    private String district;

    private String isValid;

    private String rtgs;

    private String state;

    private String ifsc;

    private String branch;

    public String getBank ()
    {
        return bank;
    }

    public void setBank (String bank)
    {
        this.bank = bank;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getContact ()
    {
        return contact;
    }

    public void setContact (String contact)
    {
        this.contact = contact;
    }

    public String getDistrict ()
    {
        return district;
    }

    public void setDistrict (String district)
    {
        this.district = district;
    }

    public String getIsValid ()
    {
        return isValid;
    }

    public void setIsValid (String isValid)
    {
        this.isValid = isValid;
    }

    public String getRtgs ()
    {
        return rtgs;
    }

    public void setRtgs (String rtgs)
    {
        this.rtgs = rtgs;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    public String getIfsc ()
    {
        return ifsc;
    }

    public void setIfsc (String ifsc)
    {
        this.ifsc = ifsc;
    }

    public String getBranch ()
    {
        return branch;
    }

    public void setBranch (String branch)
    {
        this.branch = branch;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [bank = "+bank+", address = "+address+", city = "+city+", contact = "+contact+", district = "+district+", isValid = "+isValid+", rtgs = "+rtgs+", state = "+state+", ifsc = "+ifsc+", branch = "+branch+"]";
    }
}
