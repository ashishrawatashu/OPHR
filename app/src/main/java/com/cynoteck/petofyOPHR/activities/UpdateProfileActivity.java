package com.cynoteck.petofyOPHR.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.bumptech.glide.Glide;
import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.updateRequest.getValue.UpdateParams;
import com.cynoteck.petofyOPHR.params.updateRequest.getValue.UpdateRequest;
import com.cynoteck.petofyOPHR.response.addPet.imageUpload.ImageResponse;
import com.cynoteck.petofyOPHR.response.updateProfileResponse.CityResponse;
import com.cynoteck.petofyOPHR.response.updateProfileResponse.CountryResponse;
import com.cynoteck.petofyOPHR.response.updateProfileResponse.PetServiceResponse;
import com.cynoteck.petofyOPHR.response.updateProfileResponse.PetTypeResponse;
import com.cynoteck.petofyOPHR.response.updateProfileResponse.StateResponse;
import com.cynoteck.petofyOPHR.response.updateProfileResponse.UserResponse;
import com.cynoteck.petofyOPHR.response.updateVetDetailsresponse.UpdateVetResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class UpdateProfileActivity extends AppCompatActivity implements View.OnClickListener, ApiResponse, TextWatcher {
    ProgressBar horizontal_progress_bar;
    EditText first_name_updt, last_name_updt, email_updt, phone_updt, address_updt, online_charges_ET,
            postal_code_updt, website_updt, social_media_url_updt, registration_num_updt, clinicCode_updt,
            vet_qualification_updt, description_updt, clinic_name_updt;
    AppCompatSpinner country_spnr_updt, state_spnr_updt, city_spnr_updt;
    Button update_profile;
    TextView select_Category, select_service_Category;
    MaterialCardView back_arrow_CV, logout_CV;
    CheckBox online_CB;
    CountryResponse stateResponse;
    Methods methods;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String id = "", password = "", activityType = "", strVetDese = "", strClinicName = "", strIsVet = "", strIsActive = "";
    ArrayList<String> state;
    ArrayList<String> countryList;
    ArrayList<String> city;
    String[] petCategory;
    String[] serviceCategory;
    boolean[] chkItems;
    ArrayList<Integer> muserItem = new ArrayList<>();

    boolean[] chkItemsSevice;
    ArrayList<Integer> muserItemService = new ArrayList<>();

    HashMap<String, String> cityHasmap = new HashMap<>();
    HashMap<String, String> stateHasmap = new HashMap<>();
    HashMap<String, String> countryHasmap = new HashMap<>();
    HashMap<String, String> categoryHasmap = new HashMap<>();
    HashMap<String, String> servcCatHasmap = new HashMap<>();

    ArrayList<String> listCategoryId = new ArrayList<>();
    ArrayList<String> listServiceCatId = new ArrayList<>();

    String imagename = "", strFirstNm = "", strLstNm = "", strEmlUpdt = "", strPhUpdt = "", strAddrsUpdt = "", strPostlUpdt = "",
            strWbUpdt = "", strSoclMdUelUpdt = "", strRegistNumUpdt = "", strVetQulafctnUpdt = "", strPetCatUpdt = "",
            strSrvcCatUpdt = "", strContrySpnr = "", strStateSpnr = "", strCitySpnr = "", strCountryId = "", strStringCityId = "",
            strStateId = "", strCatId = "", strSrvsCatId = "", strCatUrl1 = "", strCatUrl2 = "", strSrvsUrl1 = "", strSrvsUrl2 = "",
            strSrvsUrl3 = "", strSrvsUrl4 = "", strSrvsUrl5 = "", strClinicCode = "", intentService = "", intentType = "", strOnlineCharges = "10";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        methods = new Methods(this);




        Intent intent = getIntent();
        activityType = intent.getStringExtra("activityName");
        id = intent.getStringExtra("id");
        strIsActive = intent.getStringExtra("isActive");
        strIsVet = intent.getStringExtra("isVeterinarian");
        password = intent.getStringExtra("password");
        strFirstNm = intent.getStringExtra("firstName");
        strLstNm = intent.getStringExtra("lastName");
        strEmlUpdt = intent.getStringExtra("email");
        strPhUpdt = intent.getStringExtra("phone");
        strAddrsUpdt = intent.getStringExtra("address");
        strPostlUpdt = intent.getStringExtra("pincode");
        strWbUpdt = intent.getStringExtra("website");
        strSoclMdUelUpdt = intent.getStringExtra("socialMedia");
        strRegistNumUpdt = intent.getStringExtra("vetRegNo");
        strVetQulafctnUpdt = intent.getStringExtra("vetStudy");
        strPetCatUpdt = intent.getStringExtra("category");
        strSrvcCatUpdt = intent.getStringExtra("service");
        strVetDese = intent.getStringExtra("description");
        strClinicName = intent.getStringExtra("clinicName");

        if (intent.getStringExtra("country") != null)
            strContrySpnr = intent.getStringExtra("country");
        if (intent.getStringExtra("state") != null)
            strStateSpnr = intent.getStringExtra("state");
        if (intent.getStringExtra("city") != null)
            strCitySpnr = intent.getStringExtra("city");

        strSrvsUrl1 = intent.getStringExtra("serviceImage1");
        strSrvsUrl2 = intent.getStringExtra("serviceImage2");
        strSrvsUrl3 = intent.getStringExtra("serviceImage3");
        strSrvsUrl4 = intent.getStringExtra("serviceImage4");
        strSrvsUrl5 = intent.getStringExtra("serviceImage5");
        intentService = intent.getStringExtra("petService");
        intentType = intent.getStringExtra("petType");
        strCatId = intent.getStringExtra("petTypeValue");
        strSrvsCatId = intent.getStringExtra("petTypeValue");
        strOnlineCharges = intent.getStringExtra("onlineConsultationCharges");
        strClinicCode = intent.getStringExtra("clinicCode");


        init();

        if (activityType.equals("Edit")) {
            logout_CV.setVisibility(View.GONE);
        }

        if ((strOnlineCharges != null)) {
            if ((strOnlineCharges != null) || (!strOnlineCharges.equals("") || (!strOnlineCharges.equals("0")))) {
                online_CB.setChecked(true);
                online_charges_ET.setVisibility(View.VISIBLE);
                online_charges_ET.setText(strOnlineCharges);
            }
        }

        if ((strClinicCode != null)) {
            if ((!strClinicCode.equals("")))
                clinicCode_updt.setText(strClinicCode);
        }


        //setImages();
        setValueFromSharePref();
//        requestMultiplePermissions();
        if (methods.isInternetOn()) {
            getState();
            getCountry();
            petType();
            petServiceType();

        } else {
            methods.DialogInternet();
        }

    }
//    -----------------------------------------------------------------------------------------------------------------
//    private void content()
//    {
//        refresh(50);
//    }

//    private void refresh(int mill)
//    {
//        Handler handler=new Handler();
//        final Runnable runnable=new Runnable() {
//            @Override
//            public void run() {
//                content();
//
//            }
//        };
//
//    }
//
//--------------------------------------------------------------------------------------------------------------
    private void getState() {
        methods.showCustomProgressBarDialog(this);
        ApiService<StateResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getStateApi(), "GetState");

    }

    private void getCountry() {
        ApiService<CountryResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getCountryApi(), "GetCountry");

    }

    private void getCity(String stateId) {
        ApiService<CityResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getCityApi(stateId), "GetCity");
    }

    private void petType() {
        ApiService<PetTypeResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().petTypeApi(), "GetPetTypes");
    }

    private void petServiceType() {
        ApiService<PetServiceResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().petServiceTypeApi(Config.token), "GetServiceTypes");

    }

    private void init() {
        //TextInputEditText
        horizontal_progress_bar = findViewById(R.id.horizontal_progress_bar);
        first_name_updt = findViewById(R.id.first_name_updt);
        last_name_updt = findViewById(R.id.last_name_updt);
        email_updt = findViewById(R.id.email_updt);
        phone_updt = findViewById(R.id.phone_updt);
        address_updt = findViewById(R.id.address_updt);
        postal_code_updt = findViewById(R.id.postal_code_updt);
        website_updt = findViewById(R.id.website_updt);
        social_media_url_updt = findViewById(R.id.social_media_url_updt);
        registration_num_updt = findViewById(R.id.registration_num_updt);
        vet_qualification_updt = findViewById(R.id.vet_qualification_updt);
        clinicCode_updt = findViewById(R.id.clinicCode_updt);
        online_charges_ET = findViewById(R.id.online_charges_ET);
        description_updt = findViewById(R.id.description_updt);
        clinic_name_updt = findViewById(R.id.clinic_name_updt);


        //Spinner
        country_spnr_updt = findViewById(R.id.country_spnr_updt);
        state_spnr_updt = findViewById(R.id.state_spnr_updt);
        city_spnr_updt = findViewById(R.id.city_spnr_updt);

        //Image View
        logout_CV = findViewById(R.id.logout_CV);
        back_arrow_CV = findViewById(R.id.back_arrow_CV);

        //Check Box
        online_CB = findViewById(R.id.online_CB);

        logout_CV.setOnClickListener(this);
        back_arrow_CV.setOnClickListener(this);
        online_CB.setOnClickListener(this);

        //Button
        update_profile = findViewById(R.id.update_profile);
        select_Category = findViewById(R.id.select_Category);
        select_service_Category = findViewById(R.id.select_service_Category);

        update_profile.setOnClickListener(this);
        select_Category.setOnClickListener(this);
        select_service_Category.setOnClickListener(this);

        first_name_updt.addTextChangedListener(this);
        last_name_updt.addTextChangedListener(this);
        email_updt.addTextChangedListener(this);
        phone_updt.addTextChangedListener(this);
        clinic_name_updt.addTextChangedListener(this);
        clinicCode_updt.addTextChangedListener(this);
        description_updt.addTextChangedListener(this);
        online_charges_ET.addTextChangedListener(this);
        address_updt.addTextChangedListener(this);
        vet_qualification_updt.addTextChangedListener(this);
        registration_num_updt.addTextChangedListener(this);


        select_service_Category.setText(intentService);
        select_Category.setText(intentType);

    }

    private void setValueFromSharePref() {
        postal_code_updt.setText(strPostlUpdt);
        website_updt.setText(strWbUpdt);
        social_media_url_updt.setText(strSoclMdUelUpdt);
        email_updt.setText(strEmlUpdt);
        first_name_updt.setText(strFirstNm);
        last_name_updt.setText(strLstNm);
        phone_updt.setText(strPhUpdt);
        address_updt.setText(strAddrsUpdt);
        vet_qualification_updt.setText(strVetQulafctnUpdt);
        registration_num_updt.setText(strRegistNumUpdt);
        description_updt.setText(strVetDese);
        clinic_name_updt.setText(strClinicName);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_arrow_CV:
                onBackPressed();
                break;
            case R.id.online_CB:
                if (((CompoundButton) view).isChecked()) {
                    online_charges_ET.setVisibility(View.VISIBLE);
                } else {
                    online_charges_ET.setVisibility(View.GONE);
                }
                break;
            case R.id.update_profile:
                strFirstNm = first_name_updt.getText().toString().trim();
                strLstNm = last_name_updt.getText().toString().trim();
                strEmlUpdt = email_updt.getText().toString().trim();
                strPhUpdt = phone_updt.getText().toString().trim();
                strAddrsUpdt = address_updt.getText().toString().trim();
                strPostlUpdt = postal_code_updt.getText().toString().trim();
                strWbUpdt = website_updt.getText().toString().trim();
                strSoclMdUelUpdt = social_media_url_updt.getText().toString().trim();
                strRegistNumUpdt = registration_num_updt.getText().toString().trim();
                strVetQulafctnUpdt = vet_qualification_updt.getText().toString().trim();
                strPetCatUpdt = select_Category.getText().toString().trim();
                strSrvcCatUpdt = select_service_Category.getText().toString().trim();
                strClinicCode = clinicCode_updt.getText().toString().trim();
                strVetDese = description_updt.getText().toString();
                strClinicName = clinic_name_updt.getText().toString();

                if (online_CB.isChecked() == true)
                    strOnlineCharges = online_charges_ET.getText().toString();
                else
                    strOnlineCharges = "10";

                if (strFirstNm.isEmpty()) {
                    select_Category.setError(null);
                    select_service_Category.setError(null);
                    first_name_updt.setError("Name is empty");
                    last_name_updt.setError(null);
                    email_updt.setError(null);
                    phone_updt.setError(null);
                    address_updt.setError(null);
                    postal_code_updt.setError(null);
                    website_updt.setError(null);
                    social_media_url_updt.setError(null);
                    registration_num_updt.setError(null);
                    vet_qualification_updt.setError(null);
                    online_charges_ET.setError(null);
                    clinicCode_updt.setError(null);
                    description_updt.setError(null);
                    clinic_name_updt.setError(null);
                } else if (strLstNm.isEmpty()) {
                    select_Category.setError(null);
                    select_service_Category.setError(null);
                    first_name_updt.setError(null);
                    last_name_updt.setError("Last Name is empty");
                    email_updt.setError(null);
                    phone_updt.setError(null);
                    address_updt.setError(null);
                    postal_code_updt.setError(null);
                    website_updt.setError(null);
                    social_media_url_updt.setError(null);
                    registration_num_updt.setError(null);
                    vet_qualification_updt.setError(null);
                    online_charges_ET.setError(null);
                    clinicCode_updt.setError(null);
                    description_updt.setError(null);
                    clinic_name_updt.setError(null);
                } else if (strEmlUpdt.isEmpty()) {
                    select_Category.setError(null);
                    select_service_Category.setError(null);
                    first_name_updt.setError(null);
                    last_name_updt.setError(null);
                    email_updt.setError("Email Id is Empty");
                    phone_updt.setError(null);
                    address_updt.setError(null);
                    postal_code_updt.setError(null);
                    website_updt.setError(null);
                    social_media_url_updt.setError(null);
                    registration_num_updt.setError(null);
                    vet_qualification_updt.setError(null);
                    online_charges_ET.setError(null);
                    clinicCode_updt.setError(null);
                    description_updt.setError(null);
                    clinic_name_updt.setError(null);
                } else if (!strEmlUpdt.matches(emailPattern)) {
                    select_Category.setError(null);
                    select_service_Category.setError(null);
                    email_updt.setError("Invalid Email");
                    select_Category.setError(null);
                    select_service_Category.setError(null);
                    first_name_updt.setError(null);
                    last_name_updt.setError(null);
                    phone_updt.setError(null);
                    address_updt.setError(null);
                    postal_code_updt.setError(null);
                    website_updt.setError(null);
                    social_media_url_updt.setError(null);
                    registration_num_updt.setError(null);
                    vet_qualification_updt.setError(null);
                    online_charges_ET.setError(null);
                    clinicCode_updt.setError(null);
                    description_updt.setError(null);
                    clinic_name_updt.setError(null);
                } else if (strPhUpdt.isEmpty()) {
                    select_Category.setError(null);
                    select_service_Category.setError(null);
                    first_name_updt.setError(null);
                    last_name_updt.setError(null);
                    email_updt.setError(null);
                    phone_updt.setError("Phone number is empty");
                    address_updt.setError(null);
                    postal_code_updt.setError(null);
                    website_updt.setError(null);
                    social_media_url_updt.setError(null);
                    registration_num_updt.setError(null);
                    vet_qualification_updt.setError(null);
                    online_charges_ET.setError(null);
                    clinicCode_updt.setError(null);
                    description_updt.setError(null);
                    clinic_name_updt.setError(null);
                } else if (strAddrsUpdt.isEmpty()) {
                    select_Category.setError(null);
                    select_service_Category.setError(null);
                    first_name_updt.setError(null);
                    last_name_updt.setError(null);
                    email_updt.setError(null);
                    phone_updt.setError(null);
                    address_updt.setError("Address is empty");
                    postal_code_updt.setError(null);
                    website_updt.setError(null);
                    social_media_url_updt.setError(null);
                    registration_num_updt.setError(null);
                    vet_qualification_updt.setError(null);
                    online_charges_ET.setError(null);
                    clinicCode_updt.setError(null);
                    description_updt.setError(null);
                    clinic_name_updt.setError(null);
                } else if (strPostlUpdt.isEmpty()) {
                    select_Category.setError(null);
                    select_service_Category.setError(null);
                    first_name_updt.setError(null);
                    last_name_updt.setError(null);
                    email_updt.setError(null);
                    phone_updt.setError(null);
                    address_updt.setError(null);
                    postal_code_updt.setError("Postal code is empty");
                    website_updt.setError(null);
                    social_media_url_updt.setError(null);
                    registration_num_updt.setError(null);
                    vet_qualification_updt.setError(null);
                    online_charges_ET.setError(null);
                    clinicCode_updt.setError(null);
                    description_updt.setError(null);
                    clinic_name_updt.setError(null);
                } else if (strRegistNumUpdt.isEmpty()) {
                    select_Category.setError(null);
                    select_service_Category.setError(null);
                    first_name_updt.setError(null);
                    last_name_updt.setError(null);
                    email_updt.setError(null);
                    phone_updt.setError(null);
                    address_updt.setError(null);
                    postal_code_updt.setError(null);
                    website_updt.setError(null);
                    social_media_url_updt.setError(null);
                    registration_num_updt.setError("Registration no empty");
                    vet_qualification_updt.setError(null);
                    online_charges_ET.setError(null);
                    clinicCode_updt.setError(null);
                    description_updt.setError(null);
                    clinic_name_updt.setError(null);
                } else if (strVetQulafctnUpdt.isEmpty()) {
                    select_Category.setError(null);
                    select_service_Category.setError(null);
                    first_name_updt.setError(null);
                    last_name_updt.setError(null);
                    email_updt.setError(null);
                    phone_updt.setError(null);
                    address_updt.setError(null);
                    postal_code_updt.setError(null);
                    website_updt.setError(null);
                    social_media_url_updt.setError(null);
                    registration_num_updt.setError(null);
                    online_charges_ET.setError(null);
                    clinicCode_updt.setError(null);
                    vet_qualification_updt.setError("Qualification empty");
                    description_updt.setError(null);
                    clinic_name_updt.setError(null);
                } else if (strClinicCode.isEmpty()) {
                    select_Category.setError(null);
                    select_service_Category.setError(null);
                    first_name_updt.setError(null);
                    last_name_updt.setError(null);
                    email_updt.setError(null);
                    phone_updt.setError(null);
                    address_updt.setError(null);
                    postal_code_updt.setError(null);
                    website_updt.setError(null);
                    social_media_url_updt.setError(null);
                    registration_num_updt.setError(null);
                    online_charges_ET.setError(null);
                    vet_qualification_updt.setError(null);
                    clinicCode_updt.setError("Enter Your Clinic Code");
                    description_updt.setError(null);
                    clinic_name_updt.setError(null);
                }

//                else if(strVetDese.isEmpty())
//                {
//                    select_Category.setError(null);
//                    select_service_Category.setError(null);
//                    first_name_updt.setError(null);
//                    last_name_updt.setError(null);
//                    email_updt.setError(null);
//                    phone_updt.setError(null);
//                    address_updt.setError(null);
//                    postal_code_updt.setError(null);
//                    website_updt.setError(null);
//                    social_media_url_updt.setError(null);
//                    registration_num_updt.setError(null);
//                    online_charges_ET.setError(null);
//                    vet_qualification_updt.setError(null);
//                    clinicCode_updt.setError(null);
//                    description_updt.setError("null");
//                    clinic_name_updt.setError(null);
//                }

                else if (strClinicName.isEmpty()) {
                    select_Category.setError(null);
                    select_service_Category.setError(null);
                    first_name_updt.setError(null);
                    last_name_updt.setError(null);
                    email_updt.setError(null);
                    phone_updt.setError(null);
                    address_updt.setError(null);
                    postal_code_updt.setError(null);
                    website_updt.setError(null);
                    social_media_url_updt.setError(null);
                    registration_num_updt.setError(null);
                    online_charges_ET.setError(null);
                    vet_qualification_updt.setError(null);
                    clinicCode_updt.setError(null);
                    description_updt.setError(null);
                    clinic_name_updt.setError("Enter Clinic Name");
                } else if (strPetCatUpdt.isEmpty() || (strPetCatUpdt.equals("Set Category"))) {
                    select_Category.setError("Set Category");
                    select_service_Category.setError(null);
                    first_name_updt.setError(null);
                    last_name_updt.setError(null);
                    email_updt.setError(null);
                    phone_updt.setError(null);
                    address_updt.setError(null);
                    postal_code_updt.setError(null);
                    website_updt.setError(null);
                    social_media_url_updt.setError(null);
                    registration_num_updt.setError(null);
                    vet_qualification_updt.setError(null);
                    online_charges_ET.setError(null);
                    clinicCode_updt.setError(null);
                    description_updt.setError(null);
                    clinic_name_updt.setError(null);
                } else if (strSrvcCatUpdt.isEmpty() || (strSrvcCatUpdt.equals("Set Services"))) {
                    select_service_Category.setError("Set Services");
                    select_Category.setError(null);
                    first_name_updt.setError(null);
                    last_name_updt.setError(null);
                    email_updt.setError(null);
                    phone_updt.setError(null);
                    address_updt.setError(null);
                    postal_code_updt.setError(null);
                    website_updt.setError(null);
                    social_media_url_updt.setError(null);
                    registration_num_updt.setError(null);
                    vet_qualification_updt.setError(null);
                    online_charges_ET.setError(null);
                    clinicCode_updt.setError(null);
                    description_updt.setError(null);
                    clinic_name_updt.setError(null);
                } else if ((strCitySpnr.equals("Select City"))) {
                    Toast.makeText(this, "Select City!!", Toast.LENGTH_SHORT).show();
                } else if ((strStateSpnr.equals("Select State"))) {
                    Toast.makeText(this, "Select State!!", Toast.LENGTH_SHORT).show();
                } else if ((strContrySpnr.equals("Select Country"))) {
                    Toast.makeText(this, "Select Country!!", Toast.LENGTH_SHORT).show();
                } else if (strCatId.isEmpty()) {
                    Toast.makeText(this, "Select Pet Type !", Toast.LENGTH_SHORT).show();
                } else if (strSrvsCatId.isEmpty()) {
                    Toast.makeText(this, "Select Service Type !", Toast.LENGTH_SHORT).show();
                } else if ((online_CB.isChecked() == true) && (strOnlineCharges.isEmpty())) {
                    Toast.makeText(this, "Enter Consultant Charges", Toast.LENGTH_SHORT).show();
                    select_service_Category.setError(null);
                    select_Category.setError(null);
                    first_name_updt.setError(null);
                    last_name_updt.setError(null);
                    email_updt.setError(null);
                    phone_updt.setError(null);
                    address_updt.setError(null);
                    postal_code_updt.setError(null);
                    website_updt.setError(null);
                    social_media_url_updt.setError(null);
                    registration_num_updt.setError(null);
                    vet_qualification_updt.setError(null);
                    clinicCode_updt.setError(null);
                    online_charges_ET.setError("Enter Charges");
                    description_updt.setError(null);
                    clinic_name_updt.setError(null);
                } else {
                    //All data update api call from here.
                    select_service_Category.setError(null);
                    select_Category.setError(null);
                    first_name_updt.setError(null);
                    last_name_updt.setError(null);
                    email_updt.setError(null);
                    phone_updt.setError(null);
                    address_updt.setError(null);
                    postal_code_updt.setError(null);
                    website_updt.setError(null);
                    social_media_url_updt.setError(null);
                    registration_num_updt.setError(null);
                    vet_qualification_updt.setError(null);
                    clinicCode_updt.setError(null);
                    online_charges_ET.setError(null);
                    description_updt.setError(null);
                    clinic_name_updt.setError(null);

                    UpdateParams data = new UpdateParams();
                    data.setId(id);
                    data.setFirstName(strFirstNm.trim());
                    data.setLastName(strLstNm.trim());
                    data.setName(strFirstNm + " " + strLstNm);
                    data.setPassword("password");
                    data.setConfirmPassword("password");
                    data.setCompany(strClinicName);
                    data.setDescription(strVetDese);
                    data.setPhone(strPhUpdt);
                    data.setPhone2("");
                    data.setEmail(strEmlUpdt);
                    data.setAddress(strAddrsUpdt);
                    data.setAddress2("");
                    data.setWebsite(strWbUpdt);
                    data.setSocialMediaUrl(strSoclMdUelUpdt);
                    data.setCityId(strStringCityId);
                    data.setStateId(strStateId);
                    data.setCountryId(strCountryId);
                    data.setPostalCode(strPostlUpdt);
                    data.setSelectedPetTypeIds(strCatId);
                    data.setSelectedServiceTypeIds(strSrvsCatId);
                    data.setProfileImageUrl(Config.user_Veterian_url);
                    data.setServiceImageUrl("");
                    data.setFirstServiceImageUrl(strSrvsUrl1);
                    data.setSecondServiceImageUrl(strCatUrl2);
                    data.setThirdServiceImageUrl(strSrvsUrl3);
                    data.setFourthServiceImageUrl(strSrvsUrl4);
                    data.setFifthServiceImageUrl(strSrvsUrl5);
                    data.setCoverImageUrl("");
                    data.setIsVeterinarian("true");
                    data.setVetQualifications(strVetQulafctnUpdt);
                    data.setVetRegistrationNumber(strRegistNumUpdt);
                    data.setOnlineConsultationCharges(strOnlineCharges);
                    data.setClinicCode(strClinicCode);
                    if (strIsActive.equals("true")) {
                        data.setIsActive("true");
                    } else {
                        data.setIsActive("false");
                    }

//                    data.setId(id);
//                    data.setFirstName("aviral");
//                    data.setLastName("rana");
//                    data.setName("aviral rana");
//                    data.setPassword(password);
//                    data.setConfirmPassword(password);
//                    data.setCompany("");
//                    data.setDescription("");
//                    data.setPhone("999-788-7756");
//                    data.setPhone2("999-788-7756");
//                    data.setEmail(strEmlUpdt);
//                    data.setAddress(strAddrsUpdt);
//                    data.setAddress2("");
//                    data.setWebsite(strWbUpdt);
//                    data.setSocialMediaUrl(strSoclMdUelUpdt);
//                    data.setCityId(strStringCityId);
//                    data.setStateId(strStateId);
//                    data.setCountryId(strCountryId);
//                    data.setPostalCode(strPostlUpdt);
//                    data.setSelectedPetTypeIds("1,2");
//                    data.setSelectedServiceTypeIds("1,3");
//                    data.setProfileImageUrl(Config.user_Veterian_url);
//                    data.setServiceImageUrl("");
//                    data.setFirstServiceImageUrl(strSrvsUrl1);
//                    data.setSecondServiceImageUrl(strSrvsUrl2);
//                    data.setThirdServiceImageUrl(strSrvsUrl3);
//                    data.setFourthServiceImageUrl(strSrvsUrl4);
//                    data.setFifthServiceImageUrl(strSrvsUrl5);
//                    data.setCoverImageUrl(strCatUrl2);
//                    data.setIsVeterinarian("1");
//                    data.setVetQualifications(strVetQulafctnUpdt);
//                    data.setVetRegistrationNumber(strRegistNumUpdt);

                    final UpdateRequest updateRequest = new UpdateRequest();
                    updateRequest.setData(data);
                    if (methods.isInternetOn()) {

                        androidx.appcompat.app.AlertDialog.Builder confirmationDialog = new androidx.appcompat.app.AlertDialog.Builder(this);
                        confirmationDialog.setTitle("Confirmation!!");
                        confirmationDialog.setMessage("Are you want to update your profile..");
                        confirmationDialog.setCancelable(false);

                        confirmationDialog.setPositiveButton(
                                "Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        updateUser(updateRequest);
                                    }
                                });
                        confirmationDialog.setNegativeButton(
                                "No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                        androidx.appcompat.app.AlertDialog alert11 = confirmationDialog.create();
                        alert11.show();
                    } else {
                        methods.DialogInternet();
                    }
                }
                break;

            case R.id.select_Category:
                strCatId = "";
                select_Category.setText("Set Category");
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
                mBuilder.setTitle("Items available in a shop");
                mBuilder.setMultiChoiceItems(petCategory, chkItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if (isChecked) {
                            if (!muserItem.contains(position)) {
                                muserItem.add(position);
                            }
                        } else if (muserItem.contains(position)) {
                            muserItem.remove(position);
                        }
                    }
                });

                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String item = "";
                        for (int i = 0; i < muserItem.size(); i++) {
                            item = item + petCategory[muserItem.get(i)];
                            strCatId = strCatId + categoryHasmap.get(petCategory[muserItem.get(i)]);
                            if (i != muserItem.size() - 1) ;
                            {
                                item = item + ", ";
                                strCatId = strCatId + ",";
                            }
                        }
                        strCatId = methods.removeLastElement(strCatId);
                        Log.e("hhhhhh", strCatId);
                        //Log.d("Selected_item_category",""+item);
                        if (item.equals("")) {
                            select_Category.setText("Set Category");
                            int progress = horizontal_progress_bar.getProgress();
                            progress = progress - 7;
                            setProgressStatus(progress);
                        } else {
                            select_Category.setText(item);
                            int progress = horizontal_progress_bar.getProgress();
                            progress = progress + 7;
                            setProgressStatus(progress);

                        }

                    }
                });

                mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                mBuilder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for (int i = 0; i < chkItems.length; i++) {
                            chkItems[i] = false;
                            muserItem.clear();
                            listCategoryId.clear();
                            select_Category.setText("Set Category");
                        }
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
                break;

            case R.id.select_service_Category:
                strSrvsCatId = "";
                select_service_Category.setText("Set Services");
                AlertDialog.Builder mBuilderr = new AlertDialog.Builder(this);
                mBuilderr.setTitle("Items available in a shop");
                mBuilderr.setMultiChoiceItems(serviceCategory, chkItemsSevice, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if (isChecked) {
                            if (!muserItemService.contains(position)) {
                                muserItemService.add(position);
                            }
                        } else if (muserItemService.contains(position)) {
                            muserItemService.remove(position);
                        }
                    }
                });

                mBuilderr.setCancelable(false);
                mBuilderr.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String item = "";
                        for (int i = 0; i < muserItemService.size(); i++) {
                            item = item + serviceCategory[muserItemService.get(i)];
                            strSrvsCatId = strSrvsCatId + servcCatHasmap.get(serviceCategory[muserItemService.get(i)]);
                            if (i != muserItemService.size() - 1) ;
                            {
                                item = item + ", ";
                                strSrvsCatId = strSrvsCatId + ",";
                            }
                        }
                        strSrvsCatId = methods.removeLastElement(strSrvsCatId);
                        Log.d("Selected_item_category", "" + item);
                        if (item.equals("")) {
                            select_service_Category.setText("Set Services");
                            int progress = horizontal_progress_bar.getProgress();
                            progress = progress - 7;
                            setProgressStatus(progress);
                        } else {
                            select_service_Category.setText(item);
                            int progress = horizontal_progress_bar.getProgress();
                            progress = progress + 7;
                            setProgressStatus(progress);
                        }
                    }
                });

                mBuilderr.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                mBuilderr.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for (int i = 0; i < chkItemsSevice.length; i++) {
                            chkItemsSevice[i] = false;
                            muserItemService.clear();
                            listServiceCatId.clear();
                            select_service_Category.setText("Set Sevices");
                        }
                    }
                });

                AlertDialog mDialogg = mBuilderr.create();
                mDialogg.show();
                break;

            case R.id.logout_CV:
                SharedPreferences preferences = this.getSharedPreferences("userdetails", 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }

    }

    private void updateUser(UpdateRequest updateRequest) {
        methods.showCustomProgressBarDialog(this);
        ApiService<UpdateVetResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().updateUser(Config.token, updateRequest), "UpdateVeterinarian");
//        content();

        Log.e("DATALOG", "checkUpdate=> " + methods.getRequestJson(updateRequest));
//        Log.e("DATALOG", "checkUpdate=> " + methods);

    }

    @Override
    public void onResponse(Response response, String key) {
        methods.customProgressDismiss();
        Log.d("kkdkkd", "" + key);
        switch (key) {

            case "GetUserDetails":
                try {
                    Log.d("GetUserDetails", response.body().toString());
                    UserResponse userResponse = (UserResponse) response.body();
                    int responseCode = Integer.parseInt(userResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        // Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

                        last_name_updt.setText(userResponse.getData().getLastName());
                        email_updt.setText(userResponse.getData().getEmail());
                        phone_updt.setText(userResponse.getData().getPhone());
                        address_updt.setText(userResponse.getData().getAddress());
                        postal_code_updt.setText(userResponse.getData().getPostalCode());
                        clinic_name_updt.setText(userResponse.getData().getCompany());
                        description_updt.setText(userResponse.getData().getDescription());


                    } else if (responseCode == 614) {
                        Toast.makeText(this, userResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Try Again  GetUserDetails!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            case "GetState":
                try {
                    Log.d("getState", response.body().toString());
                    StateResponse stateResponse = (StateResponse) response.body();
                    int responseCode = Integer.parseInt(stateResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        state = new ArrayList<>();
                        state.add("Select State");
                        stateHasmap.put("Select State", "0.0");
                        for (int i = 0; i < stateResponse.getData().size(); i++) {
                            Log.d("kakakka", "" + stateResponse.getData().get(i).getStateName());
                            state.add(stateResponse.getData().get(i).getStateName());
                            stateHasmap.put(stateResponse.getData().get(i).getStateName(), stateResponse.getData().get(i).getId());
                        }
                        setStateSpinner();

                    } else if (responseCode == 614) {
                        Toast.makeText(UpdateProfileActivity.this, stateResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UpdateProfileActivity.this, "Please Try Again GetState!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "GetCountry":
                try {
                    Log.d("GetCountry", response.body().toString());
                    stateResponse = (CountryResponse) response.body();
                    int responseCode = Integer.parseInt(stateResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        countryList = new ArrayList<>();
                        countryList.add("Select Country");
                        for (int i = 0; i < stateResponse.getData().size(); i++) {
                            Log.d("kakakka", "" + stateResponse.getData().get(i).getCountryName());
                            countryList.add(stateResponse.getData().get(i).getCountryName());
                            countryHasmap.put(stateResponse.getData().get(i).getCountryName(), stateResponse.getData().get(i).getId());
                        }
                        setCountrySpinner();

                    } else if (responseCode == 614) {
                        Toast.makeText(UpdateProfileActivity.this, stateResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UpdateProfileActivity.this, "Please Try Again GetCountry!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "GetCity":
                try {
                    Log.d("GetCity", response.body().toString());
                    CityResponse cityResponse = (CityResponse) response.body();
                    int responseCode = Integer.parseInt(cityResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        city = new ArrayList<>();
                        city.add("Select City");
                        Log.d("lalal", "" + cityResponse.getData().size());
                        for (int i = 0; i < cityResponse.getData().size(); i++) {
                            Log.d("kakakkajj", "" + cityResponse.getData().get(i).getCity1());
                            city.add(cityResponse.getData().get(i).getCity1());
                            cityHasmap.put(cityResponse.getData().get(i).getCity1(), cityResponse.getData().get(i).getId());
                        }
                        setCitySpinner();

                    } else if (responseCode == 614) {
                        Toast.makeText(UpdateProfileActivity.this, cityResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UpdateProfileActivity.this, "Please Try Again Get City!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "GetPetTypes":
                try {
                    Log.d("GetPetTypes", response.body().toString());
                    PetTypeResponse petTypeResponse = (PetTypeResponse) response.body();
                    int responseCode = Integer.parseInt(petTypeResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        petCategory = new String[petTypeResponse.getData().size()];
                        Log.d("lalal", "" + petTypeResponse.getData().size());
                        for (int i = 0; i < petTypeResponse.getData().size(); i++) {
                            Log.d("petttt", "" + petTypeResponse.getData().get(i).getPetType1());
                            petCategory[i] = petTypeResponse.getData().get(i).getPetType1();
                            categoryHasmap.put(petTypeResponse.getData().get(i).getPetType1(), petTypeResponse.getData().get(i).getId());
                        }
                        chkItems = new boolean[petCategory.length];
                        Log.d("CheckBox chkItems", chkItems.toString());
                        Log.d("CheckBox petCategory ", petCategory.toString());
                        Log.d("CheckBox Hasmap", categoryHasmap.toString());


                    } else if (responseCode == 614) {
                        Toast.makeText(UpdateProfileActivity.this, petTypeResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UpdateProfileActivity.this, "Please Try Again GetPetTypes!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "GetServiceTypes":
                try {
                    Log.d("GetPetServiceTypes", response.body().toString());
                    PetServiceResponse petServiceResponse = (PetServiceResponse) response.body();
                    int responseCode = Integer.parseInt(petServiceResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
//                        Toast.makeText(UpdateProfileActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        serviceCategory = new String[petServiceResponse.getData().size()];
                        Log.d("lalal", "" + petServiceResponse.getData().size());
                        for (int i = 0; i < petServiceResponse.getData().size(); i++) {
                            Log.d("petttt", "" + petServiceResponse.getData().get(i).getServiceType1());
                            serviceCategory[i] = petServiceResponse.getData().get(i).getServiceType1();
                            servcCatHasmap.put(petServiceResponse.getData().get(i).getServiceType1(), petServiceResponse.getData().get(i).getId());
                        }
                        chkItemsSevice = new boolean[serviceCategory.length];

                    } else if (responseCode == 614) {
                        Toast.makeText(UpdateProfileActivity.this, petServiceResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UpdateProfileActivity.this, "Please Try Again Get Servicecstypes!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
//----------------------------------------------------------------------------------------------------------------
            case "UpdateVeterinarian":
                try {
                    Log.d("UpdateVeterinarian", String.valueOf(response.code()));
//                    Log.d("ttttt", response.body().toString());
                    UpdateVetResponse userResponse = (UpdateVetResponse) response.body();
//                    Log.d("updateCode", String.valueOf(userResponse));
                    int responseCode = Integer.parseInt(userResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        Toast.makeText(UpdateProfileActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        if (activityType.equals("Update")) {
                            setResult(RESULT_OK);
                            finish();
                        } else if (activityType.equals("Edit")) {
                            setResult(RESULT_OK);
                            finish();
                        }
                    } else if (responseCode == 614) {
                        Toast.makeText(UpdateProfileActivity.this, userResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UpdateProfileActivity.this, "Please Try Again  UpdateVeterinarian!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("error", e.getMessage());
                    Log.e("error", e.getLocalizedMessage());

                }
                break;
//-----------------------------------------------------------------------------------------------------------------
//            case "UploadDocument":
//                try {
//                    methods.customProgressDismiss();
//                    Log.d("UploadDocument",response.body().toString());
//                    ImageResponse imageResponse = (ImageResponse) response.body();
//                    int responseCode = Integer.parseInt(imageResponse.getResponse().getResponseCode());
//                    if (responseCode== 109){
//                        //Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
//                        if(slctCatOneImage.equals("1")){
//                            strCatUrl1=imageResponse.getData().getDocumentUrl();
//                            slctCatOneImage="0";
//                        }
//                        if(slctCatTwoImage.equals("1")){
//                            strCatUrl2=imageResponse.getData().getDocumentUrl();
//                            slctCatTwoImage="0";
//                        }
//                        if(slctServcOneImage.equals("1")){
//                            strSrvsUrl1=imageResponse.getData().getDocumentUrl();
//                            slctServcOneImage="0";
//                        }
//                        if(slctServcTwoImage.equals("1")){
//                            strSrvsUrl2=imageResponse.getData().getDocumentUrl();
//                            slctServcTwoImage="0";
//                        }
//                        if(slctServcThreeImage.equals("1")){
//                            strSrvsUrl3=imageResponse.getData().getDocumentUrl();
//                            slctServcThreeImage="0";
//                        }
//                        if(slctServcfourImage.equals("1")){
//                            strSrvsUrl4=imageResponse.getData().getDocumentUrl();
//                            slctServcfourImage="0";
//                        }
//                        if(slctServcFiveImage.equals("1")){
//                            strSrvsUrl5=imageResponse.getData().getDocumentUrl();
//                            slctServcFiveImage="0";
//                        }
//
//                    }else if (responseCode==614){
//                        Toast.makeText(this, imageResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
//                    }else {
//                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                catch(Exception e) {
//                    e.printStackTrace();
//                }
//                break;
        }
    }

    @Override
    public void onError(Throwable t, String key) {
        methods.customProgressDismiss();
        Log.e("error", t.getMessage());
        Toast.makeText(this, "Please try again Error function! "+key, Toast.LENGTH_SHORT).show();
    }

    private void setCountrySpinner() {
        ArrayAdapter aa = new ArrayAdapter(UpdateProfileActivity.this, android.R.layout.simple_spinner_item, countryList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        country_spnr_updt.setAdapter(aa);
        if (!strContrySpnr.equals("")) {
            int spinnerPosition = aa.getPosition(strContrySpnr);
            country_spnr_updt.setSelection(spinnerPosition);
        }
        country_spnr_updt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                strContrySpnr = item;
                strCountryId = countryHasmap.get(strContrySpnr);
                if (strContrySpnr.equals("Select Country")) {
                    int progress = horizontal_progress_bar.getProgress();
                    progress = progress - 7;
                    setProgressStatus(progress);
                } else {
                    int progress = horizontal_progress_bar.getProgress();
                    progress = progress + 7;
                    setProgressStatus(progress);
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setStateSpinner() {
        ArrayAdapter aa = new ArrayAdapter(UpdateProfileActivity.this, android.R.layout.simple_spinner_item, state);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        state_spnr_updt.setAdapter(aa);
        if (!strStateSpnr.equals("")) {
            int spinnerPosition = aa.getPosition(strStateSpnr);
            state_spnr_updt.setSelection(spinnerPosition);
        }
        state_spnr_updt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                strStateSpnr = item;
                strStateId = stateHasmap.get(strStateSpnr);

                if (strStateSpnr.equals("Select State")) {
                    int progress = horizontal_progress_bar.getProgress();
                    progress = progress - 7;
                    setProgressStatus(progress);
                } else {
                    int progress = horizontal_progress_bar.getProgress();
                    progress = progress + 7;
                    setProgressStatus(progress);
                }

                if (strStateId.equals(null)) {

                } else {
                    String stateId = strStateId.substring(0, strStateId.length() - 2);
                    String url = "common/GetCity/" + stateId;
                    Log.e("URL", url);
                    getCity(url);
                }

            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setCitySpinner() {
        ArrayAdapter aa = new ArrayAdapter(UpdateProfileActivity.this, android.R.layout.simple_spinner_item, city);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner

        city_spnr_updt.setAdapter(aa);
        if (!strCitySpnr.equals("")) {
            int spinnerPosition = aa.getPosition(strCitySpnr);
            city_spnr_updt.setSelection(spinnerPosition);
        }
        city_spnr_updt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                strCitySpnr = item;
                Log.d("spnrCity", "" + strCitySpnr);
                strStringCityId = cityHasmap.get(strCitySpnr);

                if (strCitySpnr.equals("Select City")) {
                    int progress = horizontal_progress_bar.getProgress();
                    progress = progress - 7;
                    setProgressStatus(progress);
                } else {
                    int progress = horizontal_progress_bar.getProgress();
                    progress = progress + 7;
                    setProgressStatus(progress);
                }

            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

        if (charSequence.hashCode() == first_name_updt.getText().hashCode()) {
            Log.e("COUNT", String.valueOf(count));
            Log.e("START", String.valueOf(start));
            Log.e("BEFORE", String.valueOf(before));

            if (start == 0 && count == 1 && before == 0) {
                int progress = horizontal_progress_bar.getProgress();
                progress = progress + 3;
                setProgressStatus(progress);
            } else if (count == 0 && start == 0) {
                int progress = horizontal_progress_bar.getProgress();
                progress = progress - 3;
                setProgressStatus(progress);
            }
        }


        if (charSequence.hashCode() == last_name_updt.getText().hashCode()) {
            Log.e("COUNT", String.valueOf(count));
            Log.e("START", String.valueOf(start));
            Log.e("BEFORE", String.valueOf(before));

            if (start == 0 && count == 1 && before == 0) {
                int progress = horizontal_progress_bar.getProgress();
                progress = progress + 3;
                setProgressStatus(progress);
            } else if (count == 0 && start == 0) {
                int progress = horizontal_progress_bar.getProgress();
                progress = progress - 3;
                setProgressStatus(progress);
            }
        }

        if (charSequence.hashCode() == email_updt.getText().hashCode()) {
            Log.e("COUNT", String.valueOf(count));
            Log.e("START", String.valueOf(start));
            Log.e("BEFORE", String.valueOf(before));

            if (start == 0 && count == 1 && before == 0) {
                int progress = horizontal_progress_bar.getProgress();
                progress = progress + 7;
                setProgressStatus(progress);
            } else if (count == 0 && start == 0) {
                int progress = horizontal_progress_bar.getProgress();
                progress = progress - 7;
                setProgressStatus(progress);
            }
        }
        if (charSequence.hashCode() == phone_updt.getText().hashCode()) {
            Log.e("COUNT", String.valueOf(count));
            Log.e("START", String.valueOf(start));
            Log.e("BEFORE", String.valueOf(before));

            if (start == 0 && count == 1 && before == 0) {
                int progress = horizontal_progress_bar.getProgress();
                progress = progress + 7;
                setProgressStatus(progress);
            } else if (count == 0 && start == 0) {
                int progress = horizontal_progress_bar.getProgress();
                progress = progress - 7;
                setProgressStatus(progress);
            }
        }
        if (charSequence.hashCode() == clinic_name_updt.getText().hashCode()) {
            Log.e("COUNT", String.valueOf(count));
            Log.e("START", String.valueOf(start));
            Log.e("BEFORE", String.valueOf(before));

            if (start == 0 && count == 1 && before == 0) {
                int progress = horizontal_progress_bar.getProgress();
                progress = progress + 7;
                setProgressStatus(progress);
            } else if (count == 0 && start == 0) {
                int progress = horizontal_progress_bar.getProgress();
                progress = progress - 7;
                setProgressStatus(progress);
            }
        }
        if (charSequence.hashCode() == clinicCode_updt.getText().hashCode()) {
            Log.e("COUNT", String.valueOf(count));
            Log.e("START", String.valueOf(start));
            Log.e("BEFORE", String.valueOf(before));

            if (start == 0 && count == 1 && before == 0) {
                int progress = horizontal_progress_bar.getProgress();
                progress = progress + 7;
                setProgressStatus(progress);
            } else if (count == 0 && start == 0) {
                int progress = horizontal_progress_bar.getProgress();
                progress = progress - 7;
                setProgressStatus(progress);
            }
        }
        if (charSequence.hashCode() == description_updt.getText().hashCode()) {
            Log.e("COUNT", String.valueOf(count));
            Log.e("START", String.valueOf(start));
            Log.e("BEFORE", String.valueOf(before));

            if (start == 0 && count == 1 && before == 0) {
                int progress = horizontal_progress_bar.getProgress();
                progress = progress + 7;
                setProgressStatus(progress);
            } else if (count == 0 && start == 0) {
                int progress = horizontal_progress_bar.getProgress();
                progress = progress - 7;
                setProgressStatus(progress);
            }
        }
        if (charSequence.hashCode() == address_updt.getText().hashCode()) {
            Log.e("COUNT", String.valueOf(count));
            Log.e("START", String.valueOf(start));
            Log.e("BEFORE", String.valueOf(before));

            if (start == 0 && count == 1 && before == 0) {
                int progress = horizontal_progress_bar.getProgress();
                progress = progress + 7;
                setProgressStatus(progress);
            } else if (count == 0 && start == 0) {
                int progress = horizontal_progress_bar.getProgress();
                progress = progress - 7;
                setProgressStatus(progress);
            }
        }
        if (charSequence.hashCode() == postal_code_updt.getText().hashCode()) {
            Log.e("COUNT", String.valueOf(count));
            Log.e("START", String.valueOf(start));
            Log.e("BEFORE", String.valueOf(before));

            if (start == 0 && count == 1 && before == 0) {
                int progress = horizontal_progress_bar.getProgress();
                progress = progress + 7;
                setProgressStatus(progress);
            } else if (count == 0 && start == 0) {
                int progress = horizontal_progress_bar.getProgress();
                progress = progress - 7;
                setProgressStatus(progress);
            }
        }
        if (charSequence.hashCode() == registration_num_updt.getText().hashCode()) {
            Log.e("COUNT", String.valueOf(count));
            Log.e("START", String.valueOf(start));
            Log.e("BEFORE", String.valueOf(before));

            if (start == 0 && count == 1 && before == 0) {
                int progress = horizontal_progress_bar.getProgress();
                progress = progress + 7;
                setProgressStatus(progress);
            } else if (count == 0 && start == 0) {
                int progress = horizontal_progress_bar.getProgress();
                progress = progress - 7;
                setProgressStatus(progress);
            }
        }
        if (charSequence.hashCode() == vet_qualification_updt.getText().hashCode()) {
            Log.e("COUNT", String.valueOf(count));
            Log.e("START", String.valueOf(start));
            Log.e("BEFORE", String.valueOf(before));

            if (start == 0 && count == 1 && before == 0) {
                int progress = horizontal_progress_bar.getProgress();
                progress = progress + 7;
                setProgressStatus(progress);
            } else if (count == 0 && start == 0) {
                int progress = horizontal_progress_bar.getProgress();
                progress = progress - 7;
                setProgressStatus(progress);
            }
        }
        if (charSequence.hashCode() == online_charges_ET.getText().hashCode()) {
            Log.e("COUNT", String.valueOf(count));
            Log.e("START", String.valueOf(start));
            Log.e("BEFORE", String.valueOf(before));

            if (start == 0 && count == 1 && before == 0) {
                int progress = horizontal_progress_bar.getProgress();
                progress = progress + 7;
                setProgressStatus(progress);
            } else if (count == 0 && start == 0) {
                int progress = horizontal_progress_bar.getProgress();
                progress = progress - 7;
                setProgressStatus(progress);
            }
        }
    }

    private void setProgressStatus(int progress) {
        horizontal_progress_bar.setProgress(progress);

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}