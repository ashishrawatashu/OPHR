package com.cynoteck.petofyOPHR.response.immunizationVaccineType;

import java.util.List;

public class ImmunizationTypeModel {
  
    private List<String> primaryVaccine = null;
    private String nextVisitDate;
    private List<VaccineTypeList> vaccineTypeList = null;
    private NextVaccination nextVaccination;
    private String age;

    public List<String> getPrimaryVaccine() {
        return primaryVaccine;
    }

    public void setPrimaryVaccine(List<String> primaryVaccine) {
        this.primaryVaccine = primaryVaccine;
    }

    public String getNextVisitDate() {
        return nextVisitDate;
    }

    public void setNextVisitDate(String nextVisitDate) {
        this.nextVisitDate = nextVisitDate;
    }

    public List<VaccineTypeList> getVaccineTypeList() {
        return vaccineTypeList;
    }

    public void setVaccineTypeList(List<VaccineTypeList> vaccineTypeList) {
        this.vaccineTypeList = vaccineTypeList;
    }

    public NextVaccination getNextVaccination() {
        return nextVaccination;
    }

    public void setNextVaccination(NextVaccination nextVaccination) {
        this.nextVaccination = nextVaccination;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ClassPojo [primaryVaccine = "+primaryVaccine+", nextVisitDate= "+nextVisitDate+", vaccineTypeList= "+vaccineTypeList+", nextVaccination = "+", age= "+age+"]";

    }

}
