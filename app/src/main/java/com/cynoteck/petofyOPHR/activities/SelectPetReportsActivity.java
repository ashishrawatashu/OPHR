package com.cynoteck.petofyOPHR.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.adapters.VisitTypesAdapter;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.immunizationRequest.ImmunizationParams;
import com.cynoteck.petofyOPHR.params.immunizationRequest.ImmunizationRequest;
import com.cynoteck.petofyOPHR.response.getImmunizationReport.PetImmunizationRecordResponse;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.GetReportsTypeData;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.GetReportsTypeResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.cynoteck.petofyOPHR.utils.RegisterRecyclerViewClickListener;
import com.google.android.material.card.MaterialCardView;

import org.json.JSONArray;

import java.util.ArrayList;

import retrofit2.Response;

public class SelectPetReportsActivity extends AppCompatActivity implements ApiResponse, View.OnClickListener, RegisterRecyclerViewClickListener {
    String pet_image_url, pet_unique_id, pet_name, pet_sex, pet_owner_name, pet_owner_contact, pet_id, pet_DOB, pet_encrypted_id, pet_age;
    MaterialCardView back_arrow_CV;
    TextView pet_reg_name_TV, pet_reg__id_TV, parent_name_TV, pet_reg_date_of_birth_TV;
    VisitTypesAdapter visitTypesAdapter;
    RecyclerView reports_types_RV;
    RelativeLayout reports_list_RL;
    ArrayList<GetReportsTypeData> getReportsTypeData;
    ConstraintLayout xray_layout, hospitalization_layout;
    Methods methods;
    WebView webview;
    ImageView petRegImage_IV;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.select_pet_reports_activity);
        methods = new Methods(this);
        init();
        setDeatils();
        getVisitTypes();

    }

    private void setDeatils() {
        pet_reg_name_TV.setText(pet_name.substring(0, 1).toUpperCase() + pet_name.substring(1) + " (" + pet_sex + ")");
        parent_name_TV.setText(pet_owner_name.substring(0, 1).toUpperCase() + pet_owner_name.substring(1));
        pet_reg__id_TV.setText(pet_unique_id);
        pet_reg_date_of_birth_TV.setText(pet_DOB);
        Glide.with(this)
                .load(pet_image_url)
                .placeholder(R.drawable.dummy_dog_image)
                .into(petRegImage_IV);

    }

    private void init() {
        Bundle extras = getIntent().getExtras();
        pet_id = extras.getString("pet_id");
        pet_image_url = extras.getString("pet_image_url");
        pet_unique_id = extras.getString("pet_unique_id");
        pet_name = extras.getString("pet_name");
        pet_sex = extras.getString("pet_sex");
        pet_owner_name = extras.getString("pet_owner_name");
        pet_DOB = extras.getString("pet_DOB");
        pet_encrypted_id = extras.getString("pet_encrypted_id");
        pet_age = extras.getString("pet_age");
        reports_types_RV = findViewById(R.id.reports_types_RV);
        back_arrow_CV = findViewById(R.id.back_arrow_CV);
        pet_reg_name_TV = findViewById(R.id.pet_reg_name_TV);
        pet_reg__id_TV = findViewById(R.id.pet_reg__id_TV);
        parent_name_TV = findViewById(R.id.parent_name_TV);
        pet_reg_date_of_birth_TV = findViewById(R.id.pet_reg_date_of_birth_TV);
        reports_list_RL = findViewById(R.id.reports_list_RL);
        xray_layout = findViewById(R.id.xray_layout);
        petRegImage_IV = findViewById(R.id.petRegImage_IV);
        hospitalization_layout = findViewById(R.id.hospitalization_layout);
        progressBar=findViewById(R.id.progressBar);
        webview = findViewById(R.id.webview);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        back_arrow_CV.setOnClickListener(this);
        xray_layout.setOnClickListener(this);
        hospitalization_layout.setOnClickListener(this);

    }

    private void getVisitTypes() {
        ApiService<GetReportsTypeResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getReportsType(Config.token), "GetReportsType");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrow_CV:
                onBackPressed();
                break;

            case R.id.xray_layout:
                intentStaticReports("7.0");

                break;

            case R.id.hospitalization_layout:
                intentStaticReports("9.0");

                break;
        }
    }

    private void intentStaticReports(String report_id) {
        Intent staticReportsIntent = new Intent(this, ReportsCommonActivity.class);
        Bundle staticReportsData = new Bundle();
        staticReportsData.putString("pet_id", pet_id);
        staticReportsData.putString("pet_name", pet_name);
        staticReportsData.putString("pet_unique_id", pet_unique_id);
        staticReportsData.putString("pet_sex", pet_sex);
        staticReportsData.putString("pet_owner_name", pet_owner_name);
        staticReportsData.putString("pet_owner_contact", pet_owner_contact);
        staticReportsData.putString("reports_id", report_id);
        staticReportsData.putString("pet_DOB", pet_DOB);
        staticReportsData.putString("pet_encrypted_id", pet_encrypted_id);
        staticReportsData.putString("button_type", "view");
        staticReportsData.putString("pet_image_url", pet_image_url);

        staticReportsIntent.putExtras(staticReportsData);
        startActivity(staticReportsIntent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

    }

    @Override
    public void onResponse(Response response, String key) {
        switch (key) {
            case "GetReportsType":
                try {
                    progressBar.setVisibility(View.GONE);
                    Log.d("GetPetServiceTypes", response.body().toString());
                    GetReportsTypeResponse petServiceResponse = (GetReportsTypeResponse) response.body();
                    int responseCode = Integer.parseInt(petServiceResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                        reports_types_RV.setLayoutManager(linearLayoutManager);
                        reports_types_RV.setNestedScrollingEnabled(false);
                        visitTypesAdapter = new VisitTypesAdapter(SelectPetReportsActivity.this, petServiceResponse.getData(), this);
                        getReportsTypeData = petServiceResponse.getData();
                        reports_types_RV.setAdapter(visitTypesAdapter);
                        visitTypesAdapter.notifyDataSetChanged();
                        reports_list_RL.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "GetImmunization":
                try {
                    Log.d("GetImmunization", response.body().toString());
                    PetImmunizationRecordResponse immunizationRecordResponse = (PetImmunizationRecordResponse) response.body();
                    methods.customProgressDismiss();
                    int responseCode = Integer.parseInt(immunizationRecordResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        if (immunizationRecordResponse.getData().equals("")) {
                            // methods.customProgressDismiss();
                            Toast.makeText(this, "No Record Found !", Toast.LENGTH_SHORT).show();
                        } else {
                            Intent lastPrescriptionIntent = new Intent(this, PdfEditorActivity.class);
                            lastPrescriptionIntent.putExtra("encryptId",immunizationRecordResponse.getData());
                            startActivity(lastPrescriptionIntent);
                        }

                    } else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onError(Throwable t, String key) {

    }

    @Override
    public void onProductClick(int position) {
        if (getReportsTypeData.get(position).getId().equals("4.0")) {
            methods.showCustomProgressBarDialog(this);
            ImmunizationParams immunizationParams = new ImmunizationParams();
            immunizationParams.setEncryptedId(pet_encrypted_id);
//        immunizationParams.setEncryptedId(getPetListResponse.getData().getPetClinicVisitList().get(position).getEncryptedId());
            ImmunizationRequest immunizationRequest = new ImmunizationRequest();
            immunizationRequest.setData(immunizationParams);

            ApiService<PetImmunizationRecordResponse> service = new ApiService<>();
            service.get(this, ApiClient.getApiInterface().viewPetVaccination(Config.token, immunizationRequest), "GetImmunization");
            Log.d("GetImmunization", methods.getRequestJson(immunizationRequest));
        } else {

            getReportsTypeData.get(position).getId();
            Intent selectReportsIntent = new Intent(this, ReportsCommonActivity.class);
            Bundle data = new Bundle();
            data.putString("pet_id", pet_id);
            data.putString("pet_name", pet_name);
            data.putString("pet_unique_id", pet_unique_id);
            data.putString("pet_sex", pet_sex);
            data.putString("pet_owner_name", pet_owner_name);
            data.putString("pet_owner_contact", pet_owner_contact);
            data.putString("reports_id", getReportsTypeData.get(position).getId());
            data.putString("button_type", "view");
            data.putString("pet_DOB", pet_DOB);
            data.putString("pet_encrypted_id", pet_encrypted_id);
            data.putString("pet_image_url", pet_image_url);
            selectReportsIntent.putExtras(data);
            startActivity(selectReportsIntent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

        }
    }

}
