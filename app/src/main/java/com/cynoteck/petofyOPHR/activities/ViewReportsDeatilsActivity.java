
package com.cynoteck.petofyOPHR.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.petReportsRequest.PetClinicVisitDetailsRequest;
import com.cynoteck.petofyOPHR.params.petReportsRequest.PetClinicVistsDetailsParams;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.AddUpdateDeleteClinicVisitResponse;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.getClinicVisitDetails.GetClinicVisitsDetailsResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.google.android.material.card.MaterialCardView;

import retrofit2.Response;

public class ViewReportsDeatilsActivity extends AppCompatActivity implements ApiResponse, View.OnClickListener {

    String pet_DOB,pet_image_url,clinic_id, type;
    TextView vet_name_textView,visit_date_textView,nature_ofVist_textView,weight_textView
            ,temperature_textView,vaccine_textView,symptoms_textView,diagnosis_textView,
            remarks_textView,pet_weight_TV,dot_pet_weight,dot_pet_temp,pet_temp_TV,pet_remarks_TV,dot_pet_remarks;
    MaterialCardView back_arrow_CV;
    ImageView petRegImage_IV;
    Button deleteReport_BT;
    String report_type, pet_unique_id, pet_name,pet_sex, pet_owner_name,pet_owner_contact,pet_id ,report_type_id;
    TextView pet_reg_name_TV,pet_sex_TV,pet_reg__id_TV,parent_name_TV,pet_reg_date_of_birth_TV,dewarmername_textView,dose_textView,next_dewarmer_textView;
    Methods methods;
    ProgressBar progressBar;
    LinearLayout linearLayout,layout_next_dewarmer,layout_dose,layout_dewarming_name;
    ConstraintLayout deworming_CL, report_detail_CL,other_report_CL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_reports_details);
        methods = new Methods(this);
        init();
        setdataInfields();
        getclinicVisitsReportDetails();

    }

    private void setdataInfields() {

        pet_reg_name_TV.setText(pet_name+" ("+pet_sex+")");
        pet_reg__id_TV.setText(pet_unique_id);
        parent_name_TV.setText(pet_owner_name);
        pet_reg_date_of_birth_TV.setText(pet_DOB);
        
        Glide.with(this)
                .load(pet_image_url)
                .placeholder(R.drawable.dummy_dog_image)
                .into(petRegImage_IV);

    }

    private void getclinicVisitsReportDetails() {
        PetClinicVistsDetailsParams petClinicVistsDetailsParams = new PetClinicVistsDetailsParams();
        petClinicVistsDetailsParams.setId(clinic_id.substring(0,clinic_id.length()-2));
        PetClinicVisitDetailsRequest petClinicVisitDetailsRequest = new PetClinicVisitDetailsRequest();
        petClinicVisitDetailsRequest.setData(petClinicVistsDetailsParams);
        Log.e("petClinicVisitDetail",methods.getRequestJson(petClinicVisitDetailsRequest).toString());
        ApiService<GetClinicVisitsDetailsResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getClinicVisitDetails(Config.token,petClinicVisitDetailsRequest), "GetPetClinicVisitDetails");

    }

    private void init() {
        Intent extras = getIntent();
        clinic_id = extras.getExtras().getString("clinic_id");
        pet_owner_contact = extras.getExtras().getString("pet_owner_contact");
        pet_owner_name = extras.getExtras().getString("pet_owner_name");
        pet_sex = extras.getExtras().getString("pet_sex");
        pet_name = extras.getExtras().getString("pet_name");
        pet_unique_id = extras.getExtras().getString("pet_unique_id");
        report_type_id=extras.getExtras().getString("id");
        pet_image_url=extras.getExtras().getString("pet_image_url");
        pet_DOB = extras.getExtras().getString("pet_DOB");
        linearLayout=findViewById(R.id.data);
        report_detail_CL = findViewById(R.id.report_detail_CL);
        progressBar = findViewById(R.id.progressBar);
        deleteReport_BT=findViewById(R.id.deleteReport_BT);
        vet_name_textView=findViewById(R.id.vet_name_textView);
        visit_date_textView=findViewById(R.id.visit_date_textView);
        nature_ofVist_textView=findViewById(R.id.nature_ofVist_textView);
        weight_textView=findViewById(R.id.weight_textView);
        temperature_textView=findViewById(R.id.temperature_textView);
        vaccine_textView=findViewById(R.id.vaccine_textView);
        symptoms_textView=findViewById(R.id.symptoms_textView);
        diagnosis_textView=findViewById(R.id.diagnosis_textView);
        remarks_textView=findViewById(R.id.remarks_textView);
        back_arrow_CV=findViewById(R.id.back_arrow_CV);
        petRegImage_IV=findViewById(R.id.petRegImage_IV);
        dewarmername_textView=findViewById(R.id.dewarmername_textView);
        dose_textView=findViewById(R.id.dose_textView);
        next_dewarmer_textView=findViewById(R.id.next_dewarmer_textView);
        deworming_CL=findViewById(R.id.deworming_CL);
        other_report_CL=findViewById(R.id.other_report_CL);

        layout_next_dewarmer=findViewById(R.id.layout_next_dewarmer);
        layout_dose=findViewById(R.id.layout_dose);
        layout_dewarming_name=findViewById(R.id.layout_dewarming_name);
        dot_pet_weight=findViewById(R.id.dot_pet_weight);
        pet_weight_TV=findViewById(R.id.pet_weight_TV);
        pet_reg_name_TV = findViewById(R.id.pet_reg_name_TV);
        pet_sex_TV = findViewById(R.id.pet_sex_TV);
        pet_reg__id_TV = findViewById(R.id.pet_reg__id_TV);
        parent_name_TV = findViewById(R.id.parent_name_TV);
        pet_reg_date_of_birth_TV = findViewById(R.id.pet_reg_date_of_birth_TV);
        dot_pet_temp=findViewById(R.id.dot_pet_temp);
        pet_temp_TV=findViewById(R.id.pet_temp_TV);
        dot_pet_temp=findViewById(R.id.dot_pet_temp);
        pet_remarks_TV=findViewById(R.id.pet_remarks_TV);
        dot_pet_remarks=findViewById(R.id.dot_pet_remarks);
        back_arrow_CV.setOnClickListener(this);
        deleteReport_BT.setOnClickListener(this);

    }

    @Override
    public void onResponse(Response response, String key) {
        switch (key){
            case "GetPetClinicVisitDetails":
                try {
                    Log.d("ResponseClinicVisit",response.body().toString());
                    GetClinicVisitsDetailsResponse getClinicVisitsDetailsResponse = (GetClinicVisitsDetailsResponse) response.body();
                    int responseCode = Integer.parseInt(getClinicVisitsDetailsResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
//                        deleteReport_BT.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        report_detail_CL.setVisibility(View.VISIBLE);
                        if (report_type_id.equals("5.0")){
                            deworming_CL.setVisibility(View.VISIBLE);
                            other_report_CL.setVisibility(View.GONE);
                            next_dewarmer_textView.setText(getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getFollowUpDate());
                            dewarmername_textView.setText(getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getDewormerName());
                            dose_textView.setText(getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getDewormerDose());
                        }else {
                            deworming_CL.setVisibility(View.GONE);
                            other_report_CL.setVisibility(View.VISIBLE);
                        }
                        vet_name_textView.setText(getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getVeterinarian());
                        visit_date_textView.setText(getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getVisitDate());
                        nature_ofVist_textView.setText(getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getNatureOfVisit().getNature());
                        symptoms_textView.setText(getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getDescription());
                        diagnosis_textView.setText(getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getDiagnosisProcedure());
                        if(getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getWeightLbs().equals("")){
                            weight_textView.setVisibility(View.GONE);
                            pet_weight_TV.setVisibility(View.GONE);
                            dot_pet_weight.setVisibility(View.GONE);
                        }
                        else{
                            weight_textView.setText(getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getWeightLbs());
                        }
                        if(getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getTemperature().equals("")){
                            temperature_textView.setVisibility(View.GONE);
                            pet_temp_TV.setVisibility(View.GONE);
                            dot_pet_temp.setVisibility(View.GONE);
                        }
                        else{
                            temperature_textView.setText(getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getTemperature());
                        }
                        if(getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getTreatmentRemarks().equals("")){
                            remarks_textView.setVisibility(View.GONE);
                            pet_remarks_TV.setVisibility(View.GONE);
                            dot_pet_remarks.setVisibility(View.GONE);
                        }
                        else {
                            remarks_textView.setText(getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getTreatmentRemarks());
                        }
                    }


                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;

            case "DeletePetClinicVisitDetails":
                try {
                    Log.d("DeleteClinicVisit",response.body().toString());
                    AddUpdateDeleteClinicVisitResponse addUpdateDeleteClinicVisitResponse = (AddUpdateDeleteClinicVisitResponse) response.body();
                    int responseCode = Integer.parseInt(addUpdateDeleteClinicVisitResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        Config.type = "list";
                        onBackPressed();
                        Toast.makeText(this, "Report deleted successfully!", Toast.LENGTH_SHORT).show();
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
        Log.e("error",t.getLocalizedMessage());
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

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
                                    deletClinicVisitDetails();
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

    private void deletClinicVisitDetails() {

        PetClinicVistsDetailsParams petClinicVistsDetailsParams = new PetClinicVistsDetailsParams();
        petClinicVistsDetailsParams.setId(clinic_id.substring(0,clinic_id.length()-2));
        PetClinicVisitDetailsRequest petClinicVisitDetailsRequest = new PetClinicVisitDetailsRequest();
        petClinicVisitDetailsRequest.setData(petClinicVistsDetailsParams);
        Log.d("DeleteClinicVisit",petClinicVisitDetailsRequest.toString());
        ApiService<AddUpdateDeleteClinicVisitResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().deleteClinicVisit(Config.token,petClinicVisitDetailsRequest), "DeletePetClinicVisitDetails");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
