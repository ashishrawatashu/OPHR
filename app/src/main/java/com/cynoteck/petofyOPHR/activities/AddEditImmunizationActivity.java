package com.cynoteck.petofyOPHR.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.addEditImmunizationSchedule.AddEditImmunizationParams;
import com.cynoteck.petofyOPHR.params.addEditImmunizationSchedule.AddEditImmunizationRequest;
import com.cynoteck.petofyOPHR.params.immunizationRequest.ImmunizationParams;
import com.cynoteck.petofyOPHR.params.immunizationRequest.ImmunizationRequest;
import com.cynoteck.petofyOPHR.response.addEditImmunizationResponse.AddEditImmunizationResponse;
import com.cynoteck.petofyOPHR.response.immunizationListResponse.ImmunizationModelResponse;
import com.cynoteck.petofyOPHR.response.updateProfileResponse.PetTypeResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Response;

public class AddEditImmunizationActivity extends AppCompatActivity implements ApiResponse, View.OnClickListener {

    AppCompatSpinner pet_category_ACS,pet_age_unit_ACS,is_periodic_ACS;
    EditText serial_number_ET,minimum_age_ET,maxmum_age_ET,primary_vaccine_name_ET,booster_one_ET,booster_two_ET;
    CheckBox booster_one_CB,booster_two_CB, Is_Periodic_Vaccine_CB;
    Button create_Edit_immui_BT;
    Methods methods;
    TextView is_periodic_TV,days_gap_booster_one_TV,days_gap_booster_two_TV,create_headline_TV;
    MaterialCardView back_arrow_CV;
    //spinner Strings
    String vaacineID="",getStrSpnerItemIsPeriodicVaccineValue="0.0",strSpnerItemIsPeriodicVaccineNm="",strSpnerItemPetNm="Dog",getStrSpnerItemPetNmId="",strSpnerItemPetAgeUnitNm="",getStrSpnerItemPetAgeUnitValue="";
   // page String
    String booster_one_string_CB="false",booster_two_string_CB="false",is_Periodic_Vaccine_string_CB="false",serial_number_string="",minimum_age_string="",maxmimum_age_string="",primary_vaccine_name_string,booster_one_string="",booster_two_string="";
    ArrayList<String> petType = new ArrayList<>();
    ArrayList<String> ageUnit=new ArrayList<>();
    ArrayList<String> isPeriodicVaccine = new ArrayList<>();


    HashMap<String,String> petTypeHashMap=new HashMap<>();
    HashMap<String,String> ageUnitHashMap=new HashMap<>();
    HashMap<String,String> isPeriodicVaccineHashMap=new HashMap<>();

    ImmunizationModelResponse immunizationResponse;
    //intent Strings
    String type="",immunization_id="",sNo="",minage="",maxAge="",vaccineName="",encrypt_id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_immunization);
        methods = new Methods(this);
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        initization();
        if (type.equals("add")){
            create_headline_TV.setText("Create Immunization");
            create_Edit_immui_BT.setText("Create");
            immunization_id = intent.getStringExtra("pet_encrpt_id");
            sNo = intent.getStringExtra("serialNumber");

        }else {
            immunization_id = intent.getStringExtra("id");
            strSpnerItemPetNm = intent.getStringExtra("petType");
            sNo = intent.getStringExtra("sNo");
            strSpnerItemPetAgeUnitNm = intent.getStringExtra("ageUnit");
            minage = intent.getStringExtra("minage");
            maxAge = intent.getStringExtra("maxAge");
            vaccineName = intent.getStringExtra("vaccineName");
            booster_one_string_CB = intent.getStringExtra("boosterOne");
            booster_one_string = intent.getStringExtra("gapBoosterOne");
            booster_two_string_CB = intent.getStringExtra("boosterTwo");
            booster_two_string = intent.getStringExtra("gapBoosterTwo");
            is_Periodic_Vaccine_string_CB = intent.getStringExtra("isPeriodicVaccine");
            strSpnerItemIsPeriodicVaccineNm  = intent.getStringExtra("vaccinePeriod");
            create_headline_TV.setText("Edit Immunization");
            create_Edit_immui_BT.setText("Update");
            if (booster_one_string_CB.equals("true")){
                booster_one_CB.isChecked();
            }

            if (booster_two_string_CB.equals("true")){
                booster_two_CB.isChecked();
            }

            if (is_Periodic_Vaccine_string_CB.equals("true")){

            }
        }

       if (methods.isInternetOn()){
           petType();
           getImmunizationModel();
        }else {
           methods.DialogInternet();
       }

    }

    private void getImmunizationModel() {
        ImmunizationParams immunizationParams = new ImmunizationParams();
        immunizationParams.setEncryptedId(immunization_id);
        ImmunizationRequest immunizationRequest = new ImmunizationRequest();
        immunizationRequest.setData(immunizationParams);
        ApiService<ImmunizationModelResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getImmunizationModel(Config.token, immunizationRequest), "ImmunizationModel");
        Log.e("Immuniztaion", methods.getRequestJson(immunizationRequest));
    }
    private void petType() {
        ApiService<PetTypeResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().petTypeApi(), "GetPetTypes");
    }
    private void initization() {
        create_headline_TV=findViewById(R.id.create_headline_TV);
        pet_age_unit_ACS = findViewById(R.id.pet_age_unit_ACS);
        pet_category_ACS = findViewById(R.id.pet_category_ACS);
        is_periodic_ACS = findViewById(R.id.is_periodic_ACS);
        serial_number_ET = findViewById(R.id.serial_number_ET);
        minimum_age_ET = findViewById(R.id.minimum_age_ET);
        maxmum_age_ET = findViewById(R.id.maxmum_age_ET);
        primary_vaccine_name_ET = findViewById(R.id.primary_vaccine_name_ET);
        booster_one_ET = findViewById(R.id.booster_one_ET);
        booster_two_ET = findViewById(R.id.booster_two_ET);
        booster_one_CB = findViewById(R.id.booster_one_CB);
        booster_two_CB = findViewById(R.id.booster_two_CB);
        is_periodic_TV= findViewById(R.id.is_periodic_TV);
        Is_Periodic_Vaccine_CB = findViewById(R.id.Is_Periodic_Vaccine_CB);
        create_Edit_immui_BT = findViewById(R.id.create_Edit_immui_BT);
        back_arrow_CV=findViewById(R.id.back_arrow_CV);
        days_gap_booster_one_TV=findViewById(R.id.days_gap_booster_one_TV);
        days_gap_booster_two_TV=findViewById(R.id.days_gap_booster_two_TV);

        create_Edit_immui_BT.setOnClickListener(this);
        booster_two_CB.setOnClickListener(this);
        booster_one_CB.setOnClickListener(this);
        Is_Periodic_Vaccine_CB.setOnClickListener(this);
        back_arrow_CV.setOnClickListener(this);
        booster_one_ET.setInputType(Configuration.KEYBOARD_12KEY);
        booster_one_ET.setKeyListener(new DigitsKeyListener());
        booster_one_ET.setInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL|InputType.TYPE_CLASS_NUMBER);

        booster_one_CB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (booster_one_CB.isChecked()){
                    booster_one_string_CB="true";
//                    booster_one_ET.setText(booster_one_string);
                    days_gap_booster_one_TV.setVisibility(View.VISIBLE);
                    booster_one_ET.setVisibility(View.VISIBLE);
                }else {
                    booster_one_string_CB="false";
//                    booster_one_ET.setText(booster_one_string);
                    days_gap_booster_one_TV.setVisibility(View.GONE);
                    booster_one_ET.setVisibility(View.GONE);
                }
            }
        });

        booster_two_CB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (booster_two_CB.isChecked()){
                    booster_two_string_CB="true";
                    booster_two_ET.setText(booster_two_string);
//                    days_gap_booster_two_TV.setVisibility(View.VISIBLE);
//                    booster_two_ET.setVisibility(View.VISIBLE);
                }else {
                    booster_two_ET.setText(booster_two_string);
                    booster_two_string_CB="false";
                    days_gap_booster_two_TV.setVisibility(View.GONE);
                    booster_two_ET.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.back_arrow_CV:
                onBackPressed();
                break;


            case R.id.booster_one_CB:


                break;

            case R.id.booster_two_CB:


                break;

            case R.id.Is_Periodic_Vaccine_CB:
                if (Is_Periodic_Vaccine_CB.isChecked()){
                    is_periodic_TV.setVisibility(View.VISIBLE);
                    is_periodic_ACS.setVisibility(View.VISIBLE);
                }else {
                    is_Periodic_Vaccine_string_CB="false";
                    is_periodic_ACS.setVisibility(View.GONE);
                    is_periodic_TV.setVisibility(View.GONE);
                }

                break;

            case R.id.create_Edit_immui_BT:
                Log.e("RUN","RRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
                serial_number_string=serial_number_ET.getText().toString().trim();
                minimum_age_string=minimum_age_ET.getText().toString().trim();
                maxmimum_age_string=maxmum_age_ET.getText().toString().trim();
                primary_vaccine_name_string=primary_vaccine_name_ET.getText().toString().trim();
                booster_one_string=booster_one_ET.getText().toString().trim();
//                booster_two_string=booster_two_ET.getText().toString().trim();

                if (serial_number_string.isEmpty()){
                    serial_number_ET.setError("Empty Serial Number !");
                    minimum_age_ET.setError(null);
                    maxmum_age_ET.setError(null);
                    booster_one_ET.setError(null);
                    booster_two_ET.setError(null);
                    primary_vaccine_name_ET.setError(null);

                }else if (minimum_age_string.isEmpty()){
                    serial_number_ET.setError(null);
                    minimum_age_ET.setError("Empty Age!");
                    maxmum_age_ET.setError(null);
                    booster_one_ET.setError(null);
                    booster_two_ET.setError(null);
                    primary_vaccine_name_ET.setError(null);
                }else if (maxmimum_age_string.isEmpty()){
                    serial_number_ET.setError(null);
                    minimum_age_ET.setError(null);
                    maxmum_age_ET.setError("Empty Age!");
                    booster_one_ET.setError(null);
                    booster_two_ET.setError(null);
                    primary_vaccine_name_ET.setError(null);
                }  else if (primary_vaccine_name_string.isEmpty()){
                    serial_number_ET.setError(null);
                    minimum_age_ET.setError(null);
                    maxmum_age_ET.setError(null);
                    booster_one_ET.setError(null);
                    booster_two_ET.setError(null);
                    primary_vaccine_name_ET.setError("Enter Vaccine Name !");
                }else if (booster_one_string_CB.equals("true")&&booster_one_string.isEmpty()){
                    Log.e("RUN","RRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
                        serial_number_ET.setError(null);
                        minimum_age_ET.setError(null);
                        maxmum_age_ET.setError(null);
                        booster_one_ET.setError("Enter Booster One!");
                        booster_two_ET.setError(null);
                        primary_vaccine_name_ET.setError(null);
                 } else if (!booster_one_string.isEmpty()&&Double.parseDouble(booster_one_string)>30){
                    Toast.makeText(this, "Booster one should less than or equal to 30.", Toast.LENGTH_SHORT).show();
                }else if (!booster_one_string.isEmpty()&&Double.parseDouble(booster_one_string)<21){
                    Toast.makeText(this, "Booster one should be greater than or equal to 21.", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("RUN","RRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
                        AddEditImmunizationParams addEditImmunizationParams = new AddEditImmunizationParams();
                        addEditImmunizationParams.setSerialNumber(serial_number_string);
                        addEditImmunizationParams.setPetCategoryId(getStrSpnerItemPetNmId);
                        addEditImmunizationParams.setPetAgeListId(getStrSpnerItemPetAgeUnitValue);
                        addEditImmunizationParams.setMinimunAge(minimum_age_string);
                        addEditImmunizationParams.setMaximunAge(maxmimum_age_string);
                        addEditImmunizationParams.setRecommendedVaccinations(primary_vaccine_name_string);
                        addEditImmunizationParams.setBoosterOne(booster_one_string_CB);
                        addEditImmunizationParams.setBoosterTwo(booster_two_string_CB);
                       if (booster_one_string.isEmpty()){
                           Log.e("RUN","RRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
                           addEditImmunizationParams.setBoosterOneDaysGap("0.0");
                            addEditImmunizationParams.setBoosterTwoDaysGap("0.0");

                        }else {
                           Log.e("RUN","RRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
                           addEditImmunizationParams.setBoosterOneDaysGap(booster_one_string);
                            addEditImmunizationParams.setBoosterTwoDaysGap(String.valueOf(Double.parseDouble(booster_one_string) + Double.parseDouble(booster_one_string)));
                        }
                        addEditImmunizationParams.setIsPeriodicVaccine(is_Periodic_Vaccine_string_CB);
                        addEditImmunizationParams.setVaccinationPeriod(getStrSpnerItemIsPeriodicVaccineValue);
                        addEditImmunizationParams.setEncryptedId(immunization_id);
                        addEditImmunizationParams.setVeterinarianUserId("");
                        addEditImmunizationParams.setId(immunization_id);
                        AddEditImmunizationRequest addEditImmunizationRequest = new AddEditImmunizationRequest();
                        addEditImmunizationRequest.setData(addEditImmunizationParams);
                    if (type.equals("add")) {
                        Log.e("RUN","RRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
                        methods.showCustomProgressBarDialog(this);
                        ApiService<AddEditImmunizationResponse> service = new ApiService<>();
                        service.get( this, ApiClient.getApiInterface().addImmunizationSchedule(Config.token,addEditImmunizationRequest), "AddImmunization");
                        Log.e("AddImmunization",""+methods.getRequestJson(addEditImmunizationRequest));

                    }else {
                        Log.e("RUN","RRRRRRRRRRRRRRRRRRRRRRRRRRRRR");
                        methods.showCustomProgressBarDialog(this);
                        ApiService<AddEditImmunizationResponse> service = new ApiService<>();
                        service.get( this, ApiClient.getApiInterface().editImmunizationSchedule(Config.token,addEditImmunizationRequest), "EditImmunization");
                        Log.e("AddImmunization",""+methods.getRequestJson(addEditImmunizationRequest));



                    }


                    }

                break;
        }

    }

    @Override
    public void onResponse(Response arg0, String key) {
        switch (key) {
            case "ImmunizationModel":
                try {

                    immunizationResponse = (ImmunizationModelResponse) arg0.body();
                    Log.d("ImmunizationModel", immunizationResponse.toString());
                    int responseCode = Integer.parseInt(immunizationResponse.getResponse().getResponseCode());

                    if (responseCode == 109) {
                        if (type.equals("add")) {
                            serial_number_ET.setText(sNo);
                            minimum_age_ET.setText(immunizationResponse.getData().getMinimunAge());
                            maxmum_age_ET.setText(immunizationResponse.getData().getMaximunAge());
                            booster_one_string_CB=immunizationResponse.getData().getBoosterOne();
                            booster_two_string_CB=immunizationResponse.getData().getBoosterTwo();
                            is_Periodic_Vaccine_string_CB=immunizationResponse.getData().getIsPeriodicVaccine();
                            checkBoxSection();

                        }else {
                            vaacineID = immunizationResponse.getData().getId();
                            serial_number_ET.setText(sNo);
                            minimum_age_ET.setText(minage);
                            maxmum_age_ET.setText(maxAge);
                            primary_vaccine_name_ET.setText(vaccineName);
                            String boosterOne_day_gap = immunizationResponse.getData().getBoosterOneDaysGap();
                            Log.e("BOOSTER_ONE_GAP",boosterOne_day_gap);
                            if (boosterOne_day_gap.equals("0.0")){
                                booster_one_ET.setText(" ");
                            }else {
                                booster_one_ET.setText(immunizationResponse.getData().getBoosterOneDaysGap());
                            }
                            booster_two_ET.setText(immunizationResponse.getData().getBoosterTwoDaysGap());
                            checkBoxSection();
                        }
                        getAgeUnit();
                        getVaccinationPeriod();
                    } else if (responseCode == 614) {
                        Toast.makeText(this, immunizationResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "GetPetTypes":
                try {
                    Log.d("GetPetTypes",arg0.body().toString());
                    PetTypeResponse petTypeResponse = (PetTypeResponse) arg0.body();
                    int responseCode = Integer.parseInt(petTypeResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        Log.d("lalal",""+petTypeResponse.getData().size());
                        for(int i=0; i<petTypeResponse.getData().size(); i++){
                            Log.d("petttt",""+petTypeResponse.getData().get(i).getPetType1());
                            petType.add(petTypeResponse.getData().get(i).getPetType1());
                            petTypeHashMap.put(petTypeResponse.getData().get(i).getPetType1(),petTypeResponse.getData().get(i).getId());
                        }
                        setPetTypeSpinner();

                    }else if (responseCode==614){
                        Toast.makeText(this, petTypeResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }

                break;

            case "AddImmunization":
                try {
                    methods.customProgressDismiss();
                    AddEditImmunizationResponse addEditImmunizationResponse = (AddEditImmunizationResponse) arg0.body();
                    Log.d("AddImmunization", addEditImmunizationResponse.toString());
                    int responseCode = Integer.parseInt(addEditImmunizationResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        Intent intent = new Intent();
                        intent.putExtra("petCat",strSpnerItemPetNm);
                        intent.putExtra("petCatId",getStrSpnerItemPetNmId);
                        setResult(RESULT_OK, intent);
                        finish();
                    } else if (responseCode == 614) {
                        Toast.makeText(this, addEditImmunizationResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "EditImmunization":
                try {
                    methods.customProgressDismiss();
                    AddEditImmunizationResponse addEditImmunizationResponse = (AddEditImmunizationResponse) arg0.body();
                    Log.d("EditImmunization", addEditImmunizationResponse.toString());
                    int responseCode = Integer.parseInt(addEditImmunizationResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        Intent intent = new Intent();
                        intent.putExtra("petCat",strSpnerItemPetNm);
                        intent.putExtra("petCatId",getStrSpnerItemPetNmId);
                        setResult(RESULT_OK, intent);
                        finish();
                    } else if (responseCode == 614) {
                        Toast.makeText(this, addEditImmunizationResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    private void getVaccinationPeriod() {
        Log.d("ageUnit",""+immunizationResponse.getData().getPeriodicVaccineTypeList().size());
        for(int i=0; i<immunizationResponse.getData().getPeriodicVaccineTypeList().size(); i++){
            Log.d("ageUnit",""+immunizationResponse.getData().getPeriodicVaccineTypeList().get(i).getText());
            isPeriodicVaccine.add(immunizationResponse.getData().getPeriodicVaccineTypeList().get(i).getText());
            isPeriodicVaccineHashMap.put(immunizationResponse.getData().getPeriodicVaccineTypeList().get(i).getText(),immunizationResponse.getData().getPeriodicVaccineTypeList().get(i).getValue());
        }setIsPeriodicVaccineSpinner();
    }

    private void setIsPeriodicVaccineSpinner() {
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,isPeriodicVaccine);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        is_periodic_ACS.setAdapter(aa);
        Log.d("nanannana",""+strSpnerItemIsPeriodicVaccineNm);
        if(strSpnerItemIsPeriodicVaccineNm!=null)
        {
            if (!strSpnerItemIsPeriodicVaccineNm.equals("")) {
                int spinnerPosition = aa.getPosition(strSpnerItemIsPeriodicVaccineNm);
                is_periodic_ACS.setSelection(spinnerPosition);
            }
        }

        is_periodic_ACS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                strSpnerItemIsPeriodicVaccineNm=item;
                Log.d("spnerType",""+strSpnerItemIsPeriodicVaccineNm);
                getStrSpnerItemIsPeriodicVaccineValue=isPeriodicVaccineHashMap.get(strSpnerItemIsPeriodicVaccineNm);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void checkBoxSection() {
        if (booster_one_string_CB.equals("true")){
            booster_one_CB.setChecked(true);
            booster_one_ET.setVisibility(View.VISIBLE);
        }else {
            booster_one_CB.setChecked(false);
            booster_one_ET.setVisibility(View.GONE);
        }
        if (booster_two_string_CB.equals("true")){
            booster_two_CB.setChecked(true);
//            booster_two_ET.setVisibility(View.VISIBLE);
        }else {
            booster_two_CB.setChecked(false);
            booster_two_ET.setVisibility(View.GONE);
        }

        if (is_Periodic_Vaccine_string_CB.equals("true")){
            Is_Periodic_Vaccine_CB.setChecked(true);
            is_periodic_TV.setVisibility(View.VISIBLE);
            is_periodic_ACS.setVisibility(View.VISIBLE);
        }else {
            Is_Periodic_Vaccine_CB.setChecked(false);
            is_periodic_TV.setVisibility(View.GONE);
            is_periodic_ACS.setVisibility(View.GONE);
        }

    }

    private void getAgeUnit() {
        Log.d("ageUnit",""+immunizationResponse.getData().getPetAgeList().size());
        for(int i=0; i<immunizationResponse.getData().getPetAgeList().size(); i++){
            Log.d("ageUnit",""+immunizationResponse.getData().getPetAgeList().get(i).getText());
            ageUnit.add(immunizationResponse.getData().getPetAgeList().get(i).getText());
            ageUnitHashMap.put(immunizationResponse.getData().getPetAgeList().get(i).getText(),immunizationResponse.getData().getPetAgeList().get(i).getValue());
        }setAgeUnitSpinner();
    }

    private void setAgeUnitSpinner() {
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,ageUnit);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        pet_age_unit_ACS.setAdapter(aa);
        if (!strSpnerItemPetAgeUnitNm.equals("")) {
            int spinnerPosition = aa.getPosition(strSpnerItemPetAgeUnitNm);
            pet_age_unit_ACS.setSelection(spinnerPosition);
        }
        pet_age_unit_ACS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                strSpnerItemPetAgeUnitNm=item;
                Log.d("spnerType",""+strSpnerItemPetAgeUnitNm);
                getStrSpnerItemPetAgeUnitValue=ageUnitHashMap.get(strSpnerItemPetAgeUnitNm);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setPetTypeSpinner() {
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,petType);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        pet_category_ACS.setAdapter(aa);
        if (!strSpnerItemPetNm.equals("")) {
            int spinnerPosition = aa.getPosition(strSpnerItemPetNm);
            pet_category_ACS.setSelection(spinnerPosition);
        }
        pet_category_ACS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                strSpnerItemPetNm=item;
                Log.d("spnerType",""+strSpnerItemPetNm);
                getStrSpnerItemPetNmId=petTypeHashMap.get(strSpnerItemPetNm);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void onError(Throwable t, String key) {

    }
}
