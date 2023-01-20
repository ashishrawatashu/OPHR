package com.cynoteck.petofyOPHR.response.getPetReportsResponse.getClinicVisitDetails;

public class PetAgeList
{
    private String disabled;

    private String text;

    private String value;

    private String selected;

    private String group;

    public String getDisabled ()
    {
        return disabled;
    }

    public void setDisabled (String disabled)
    {
        this.disabled = disabled;
    }

    public String getText ()
    {
        return text;
    }

    public void setText (String text)
    {
        this.text = text;
    }

    public String getValue ()
    {
        return value;
    }

    public void setValue (String value)
    {
        this.value = value;
    }

    public String getSelected ()
    {
        return selected;
    }

    public void setSelected (String selected)
    {
        this.selected = selected;
    }

    public String getGroup ()
{
    return group;
}

    public void setGroup (String group)
    {
        this.group = group;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [disabled = "+disabled+", text = "+text+", value = "+value+", selected = "+selected+", group = "+group+"]";
    }
}
			
			
