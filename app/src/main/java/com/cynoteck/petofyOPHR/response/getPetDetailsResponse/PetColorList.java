package com.cynoteck.petofyOPHR.response.getPetDetailsResponse;

public class PetColorList {
    private Boolean disabled;
    private Object group;
    private Boolean selected;
    private String text;
    private String value;

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Object getGroup() {
        return group;
    }

    public void setGroup(Object group) {
        this.group = group;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
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
