package com.cynoteck.petofyOPHR.response.getXRayReports;

public class TypeOfTest
{
    private String testType;

    private String id;

    private String[] petTestsAndXrey;

    public String getTestType ()
    {
        return testType;
    }

    public void setTestType (String testType)
    {
        this.testType = testType;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String[] getPetTestsAndXrey ()
    {
        return petTestsAndXrey;
    }

    public void setPetTestsAndXrey (String[] petTestsAndXrey)
    {
        this.petTestsAndXrey = petTestsAndXrey;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [testType = "+testType+", id = "+id+", petTestsAndXrey = "+petTestsAndXrey+"]";
    }
}