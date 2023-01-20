package com.cynoteck.petofyOPHR.response.searchDiagnosisResponse;

import com.cynoteck.petofyOPHR.response.Header;
import com.cynoteck.petofyOPHR.response.Response;

import java.util.ArrayList;

public class SearchDiagnosisResponseData {
    private ArrayList<String> dewormerDose;

    private ArrayList<String> treammentRemarks;

    private ArrayList<String> diagnosis;

    private ArrayList<String> dewormer;

    public ArrayList<String> getDewormerDose ()
    {
        return dewormerDose;
    }

    public void setDewormerDose (ArrayList<String> dewormerDose)
    {
        this.dewormerDose = dewormerDose;
    }

    public ArrayList<String> getTreammentRemarks ()
    {
        return treammentRemarks;
    }

    public void setTreammentRemarks (ArrayList<String> treammentRemarks)
    {
        this.treammentRemarks = treammentRemarks;
    }

    public ArrayList<String> getDiagnosis ()
    {
        return diagnosis;
    }

    public void setDiagnosis (ArrayList<String> diagnosis)
    {
        this.diagnosis = diagnosis;
    }

    public ArrayList<String> getDewormer ()
    {
        return dewormer;
    }

    public void setDewormer (ArrayList<String> dewormer)
    {
        this.dewormer = dewormer;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [dewormerDose = "+dewormerDose+", treammentRemarks = "+treammentRemarks+", diagnosis = "+diagnosis+", dewormer = "+dewormer+"]";
    }
}
