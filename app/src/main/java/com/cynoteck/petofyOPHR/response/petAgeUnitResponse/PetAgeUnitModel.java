package com.cynoteck.petofyOPHR.response.petAgeUnitResponse;

public class PetAgeUnitModel {
    
    private String ageUnit;
    private String age;

    public String getAgeUnit() {
        return ageUnit;
    }

    public void setAgeUnit(String ageUnit) {
        this.ageUnit = ageUnit;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "ClassPojo[" +
                "ageUnit= " + ageUnit +
                ", age= " + age +
                "]";
    }
}
