package com.cynoteck.petofyOPHR.response.getPetDetailsResponse;

public class PetBreedList {
    private String disabled;
    private String group;
    private String selected;
    private String text;
    private String value;

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [disabled = "+disabled+", group = "+group+", selected= "+selected+", text= "+text+", value= "+value+"]";
    }

}
