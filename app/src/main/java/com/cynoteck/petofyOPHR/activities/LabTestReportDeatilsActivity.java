package com.cynoteck.petofyOPHR.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.petReportsRequest.PetClinicVisitDetailsRequest;
import com.cynoteck.petofyOPHR.params.petReportsRequest.PetClinicVistsDetailsParams;
import com.cynoteck.petofyOPHR.response.getLabTestReportResponse.getLabTestReportDetailsResponse.GetLabTestReportDeatilsResponse;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.AddUpdateDeleteClinicVisitResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;

import retrofit2.Response;

public class LabTestReportDeatilsActivity extends FragmentActivity implements ApiResponse, View.OnClickListener {

    TextView vet_name_textView,requesting_contact_textView,visit_date_textView,lab_type_textView,desce_textView,lab_phone_textView,test_name_textView,reson_of_visit_textView,result_textView;
    Button deleteReport_BT;
    ImageView back_arrow_IV;
    TextView pet_name_TV,pet_sex_TV,pet_id_TV,pet_owner_name_TV,pet_owner_phone_no_TV;
    String pet_unique_id, pet_name,pet_sex, pet_owner_name,pet_owner_contact,pet_id ,report_type_id,type;

    Methods methods;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_report_deatils);
        methods = new Methods(this);
        getIntentData();
        init();
        setdataInFields();
        getLabTestDeatils();

    }


    private void setdataInFields() {

        pet_name_TV.setText(pet_name);
        pet_sex_TV.setText(pet_sex);
        pet_id_TV.setText(pet_unique_id);
        pet_owner_name_TV.setText(pet_owner_name);
        pet_owner_phone_no_TV.setText(pet_owner_contact);

    }

    private void getIntentData() {

        Intent extras = getIntent();
        pet_id = extras.getExtras().getString("pet_id");
        pet_owner_contact = extras.getExtras().getString("pet_owner_contact");
        pet_owner_name = extras.getExtras().getString("pet_owner_name");
        pet_sex = extras.getExtras().getString("pet_sex");
        pet_name = extras.getExtras().getString("pet_name");
        pet_unique_id = extras.getExtras().getString("pet_unique_id");
        report_type_id=extras.getExtras().getString("id");


    }

    private void init() {
        vet_name_textView = findViewById(R.id.vet_name_textView);
        requesting_contact_textView = findViewById(R.id.requesting_contact_textView);
        visit_date_textView = findViewById(R.id.visit_date_textView);
        lab_type_textView = findViewById(R.id.lab_type_textView);
        desce_textView = findViewById(R.id.desce_textView);
        lab_phone_textView = findViewById(R.id.lab_phone_textView);
        test_name_textView = findViewById(R.id.test_name_textView);
        reson_of_visit_textView = findViewById(R.id.reson_of_visit_textView);
        result_textView = findViewById(R.id.result_textView);
        pet_name_TV = findViewById(R.id.pet_name_TV);
        pet_sex_TV = findViewById(R.id.pet_sex_TV);
        pet_id_TV = findViewById(R.id.pet_id_TV);
        pet_owner_name_TV = findViewById(R.id.pet_owner_name_TV);
        pet_owner_phone_no_TV = findViewById(R.id.pet_owner_phone_no_TV);
        deleteReport_BT = findViewById(R.id.deleteReport_BT);
        back_arrow_IV = findViewById(R.id.back_arrow_IV);

        back_arrow_IV.setOnClickListener(this);
        deleteReport_BT.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.back_arrow_IV:
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
                                    deleteLabWork();
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

    private void deleteLabWork() {
        PetClinicVistsDetailsParams petClinicVistsDetailsParams = new PetClinicVistsDetailsParams();
        petClinicVistsDetailsParams.setId(report_type_id.substring(0,report_type_id.length()-2));
        PetClinicVisitDetailsRequest petClinicVisitDetailsRequest = new PetClinicVisitDetailsRequest();
        petClinicVisitDetailsRequest.setData(petClinicVistsDetailsParams);
        Log.d("DeleteLabTestWork",petClinicVisitDetailsRequest.toString());
        ApiService<AddUpdateDeleteClinicVisitResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().deleteLabTestWork(Config.token,petClinicVisitDetailsRequest), "DeleteLabTestWork");

    }

    private void getLabTestDeatils() {

        PetClinicVistsDetailsParams petClinicVistsDetailsParams = new PetClinicVistsDetailsParams();
        petClinicVistsDetailsParams.setId(report_type_id.substring(0,report_type_id.length()-2));
        PetClinicVisitDetailsRequest petClinicVisitDetailsRequest = new PetClinicVisitDetailsRequest();
        petClinicVisitDetailsRequest.setData(petClinicVistsDetailsParams);
        Log.d("GetLabWorkDetails",petClinicVisitDetailsRequest.toString());

        ApiService<GetLabTestReportDeatilsResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getPetLabWorkDetails(Config.token,petClinicVisitDetailsRequest), "GetLabWorkDetails");


    }
    @Override
    public void onResponse(Response response, String key) {
        switch (key){
            case "GetLabWorkDetails":
                try {
                    Log.d("ResponseClinicVisit",response.body().toString());
                    GetLabTestReportDeatilsResponse getClinicVisitsDetailsResponse  = (GetLabTestReportDeatilsResponse) response.body();
                    int responseCode = Integer.parseInt(getClinicVisitsDetailsResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        vet_name_textView.setText(getClinicVisitsDetailsResponse.getData().getRequestingVeterinarian());
                        requesting_contact_textView.setText(getClinicVisitsDetailsResponse.getData().getVeterinarianPhone());
                        visit_date_textView.setText(getClinicVisitsDetailsResponse.getData().getVisitDate());
                        lab_type_textView.setText(getClinicVisitsDetailsResponse.getData().getLabType().getLab());
                        desce_textView.setText("");
                        lab_phone_textView.setText(getClinicVisitsDetailsResponse.getData().getLabPhone());
                        test_name_textView.setText(getClinicVisitsDetailsResponse.getData().getTestName());
                        reson_of_visit_textView.setText(getClinicVisitsDetailsResponse.getData().getReasonOfTest());
                        result_textView.setText(getClinicVisitsDetailsResponse.getData().getResults());

                        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();


                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;

            case "DeleteLabTestWork":
                try {
                    Log.d("DeleteLabTestWork",response.body().toString());
                    AddUpdateDeleteClinicVisitResponse addUpdateDeleteClinicVisitResponse = (AddUpdateDeleteClinicVisitResponse) response.body();
                    int responseCode = Integer.parseInt(addUpdateDeleteClinicVisitResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        Config.type = "Lab";
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
        Log.e("error",t.getLocalizedMessage());

    }
}
