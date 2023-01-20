package com.cynoteck.petofyOPHR.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.petReportsRequest.PetClinicVisitDetailsRequest;
import com.cynoteck.petofyOPHR.params.petReportsRequest.PetClinicVistsDetailsParams;
import com.cynoteck.petofyOPHR.response.getPetHospitalizationResponse.getHospitalizationDeatilsResponse.GetHospitalizationDeatilsResponse;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.AddUpdateDeleteClinicVisitResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.google.android.material.card.MaterialCardView;

import retrofit2.Response;

public class HospitalizationDetailsActivity extends AppCompatActivity implements ApiResponse, View.OnClickListener {

    TextView vet_name_textView,requesting_contact_textView,hospital_type_textView,hospital_name_textView,admission_date_textView,discharge_date_textView,hospital_phone_textView,reson_of_visit_textView,result_textView;
    Button view_file_BT, deleteReport_BT;
    ImageView petRegImage_IV;
    MaterialCardView back_arrow_CV;
    TextView pet_reg_name_TV,pet_reg_date_of_birth_TV,pet_reg__id_TV,parent_name_TV,pet_owner_phone_no_TV;
    String pet_DOB, pet_image_url, pet_unique_id, pet_name,pet_sex, pet_owner_name,pet_owner_contact,pet_id ,report_type_id,type;
    ProgressBar progressBar;
    Methods methods;
    RelativeLayout card_view;
    Uri localUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitalization_details);

        methods = new Methods(this);
        getIntentData();
        init();
        setdataInFields();
        getHospitalizationDeatils();
    }

    private void setdataInFields() {
        pet_reg_name_TV.setText(pet_name+" ("+pet_sex+")");
        pet_reg__id_TV.setText(pet_unique_id);
        parent_name_TV.setText(pet_owner_name);
        pet_reg_date_of_birth_TV.setText(pet_DOB);


        Glide.with(this)
                .load(pet_image_url)
                .placeholder(R.drawable.dummy_dog_image)
                .into(petRegImage_IV);

    }

    private void init() {
        view_file_BT=findViewById(R.id.view_file_BT);
        card_view = findViewById(R.id.card_view);
        progressBar = findViewById(R.id.progressBar);
        vet_name_textView = findViewById(R.id.vet_name_textView);
        requesting_contact_textView = findViewById(R.id.requesting_contact_textView);
        hospital_type_textView = findViewById(R.id.hospital_type_textView);
        hospital_name_textView = findViewById(R.id.hospital_name_textView);
        admission_date_textView = findViewById(R.id.admission_date_textView);
        discharge_date_textView = findViewById(R.id.discharge_date_textView);
        hospital_phone_textView = findViewById(R.id.hospital_phone_textView);
        reson_of_visit_textView = findViewById(R.id.reson_of_visit_textView);
        result_textView = findViewById(R.id.result_textView);
        petRegImage_IV=findViewById(R.id.petRegImage_IV);


        pet_reg_name_TV = findViewById(R.id.pet_reg_name_TV);
        pet_reg_date_of_birth_TV = findViewById(R.id.pet_reg_date_of_birth_TV);
        pet_reg__id_TV = findViewById(R.id.pet_reg__id_TV);
        parent_name_TV = findViewById(R.id.parent_name_TV);
        pet_owner_phone_no_TV = findViewById(R.id.pet_owner_phone_no_TV);
        deleteReport_BT = findViewById(R.id.deleteReport_BT);
        back_arrow_CV = findViewById(R.id.back_arrow_CV);

        back_arrow_CV.setOnClickListener(this);
        view_file_BT.setOnClickListener(this);
        deleteReport_BT.setOnClickListener(this);
    }

    private void getIntentData() {

        Intent extras = getIntent();
        pet_id = extras.getExtras().getString("pet_id");
        pet_owner_contact = extras.getExtras().getString("pet_owner_contact");
        pet_owner_name = extras.getExtras().getString("pet_owner_name");
        pet_sex = extras.getExtras().getString("pet_sex");
        pet_name = extras.getExtras().getString("pet_name");
        pet_unique_id = extras.getExtras().getString("pet_unique_id");
        report_type_id=extras.getExtras().getString("report_id");
        pet_image_url=extras.getExtras().getString("pet_image_url");
        pet_DOB = extras.getExtras().getString("pet_DOB");


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.view_file_BT:

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                i.setDataAndType(localUri, getContentResolver().getType(localUri));
                startActivity(i);
                break;

            case R.id.back_arrow_CV:
                onBackPressed();
                break;

            case R.id.deleteReport_BT:
                Log.d("Add Anotheer Veterian","vet");
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Are you sure?");
                alertDialog.setMessage("Do You Want to Delete This Report ?");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if (methods.isInternetOn()) {
                                    deleteHospitalization();
                                } else {
                                    methods.DialogInternet();
                                }
                                dialog.dismiss();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                alertDialog.show();

                break;
        }

    }

    private void deleteHospitalization() {
        PetClinicVistsDetailsParams petClinicVistsDetailsParams = new PetClinicVistsDetailsParams();
        petClinicVistsDetailsParams.setId(report_type_id.substring(0,report_type_id.length()-2));
        PetClinicVisitDetailsRequest petClinicVisitDetailsRequest = new PetClinicVisitDetailsRequest();
        petClinicVisitDetailsRequest.setData(petClinicVistsDetailsParams);
        Log.d("DeleteHospitalization",petClinicVisitDetailsRequest.toString());
        ApiService<AddUpdateDeleteClinicVisitResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().deletePetHospitalization(Config.token,petClinicVisitDetailsRequest), "DeleteHospitalization");


    }

    private void getHospitalizationDeatils() {
        PetClinicVistsDetailsParams petClinicVistsDetailsParams = new PetClinicVistsDetailsParams();
        petClinicVistsDetailsParams.setId(report_type_id.substring(0,report_type_id.length()-2));
        PetClinicVisitDetailsRequest petClinicVisitDetailsRequest = new PetClinicVisitDetailsRequest();
        petClinicVisitDetailsRequest.setData(petClinicVistsDetailsParams);
        Log.d("petClinicVisitDetail",petClinicVisitDetailsRequest.toString());

        ApiService<GetHospitalizationDeatilsResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getPetHospitalizationDetails(Config.token,petClinicVisitDetailsRequest), "GetHospitalizationDetails");

    }

    @Override
    public void onResponse(Response response, String key) {
        switch (key){
            case "GetHospitalizationDetails":
                try {
                    progressBar.setVisibility(View.GONE);
                    Log.d("GetHospitalization",response.body().toString());
                    GetHospitalizationDeatilsResponse getHospitalizationDeatilsResponse = (GetHospitalizationDeatilsResponse) response.body();
                    int responseCode = Integer.parseInt(getHospitalizationDeatilsResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        card_view.setVisibility(View.VISIBLE);

                        vet_name_textView.setText(getHospitalizationDeatilsResponse.getData().getRequestingVeterinarian());
                        requesting_contact_textView.setText(getHospitalizationDeatilsResponse.getData().getVeterinarianPhone());
                        hospital_type_textView.setText(getHospitalizationDeatilsResponse.getData().getHospitalizationType().getHospitalization());
                        hospital_name_textView.setText(getHospitalizationDeatilsResponse.getData().getHospitalName());
                        admission_date_textView.setText(getHospitalizationDeatilsResponse.getData().getAdmissionDate());
                        discharge_date_textView.setText(getHospitalizationDeatilsResponse.getData().getDischargeDate());
                        hospital_phone_textView.setText(getHospitalizationDeatilsResponse.getData().getHospitalPhone());
                        reson_of_visit_textView.setText(getHospitalizationDeatilsResponse.getData().getReasonForHospitalization());
                        result_textView.setText(getHospitalizationDeatilsResponse.getData().getDiagnosisTreatmentProcedure());
                        if (getHospitalizationDeatilsResponse.getData().getDocuments().equals("")){
                            view_file_BT.setVisibility(View.GONE);
                        }else {
                            localUri = Uri.parse(getHospitalizationDeatilsResponse.getData().getDocuments());
                            view_file_BT.setVisibility(View.VISIBLE);
                        }
                    }
                }
                catch(Exception e) {
                    progressBar.setVisibility(View.GONE);
                    e.printStackTrace();
                }
                break;

            case "DeleteHospitalization":
                try {
                    Log.d("DeleteHospitalization",response.body().toString());
                    AddUpdateDeleteClinicVisitResponse addUpdateDeleteClinicVisitResponse = (AddUpdateDeleteClinicVisitResponse) response.body();
                    int responseCode = Integer.parseInt(addUpdateDeleteClinicVisitResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        Config.type = "Hospitalization";
                        onBackPressed();
                        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onError(Throwable t, String key) {

    }
}
