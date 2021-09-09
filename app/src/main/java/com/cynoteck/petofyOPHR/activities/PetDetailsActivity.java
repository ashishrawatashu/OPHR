
package com.cynoteck.petofyOPHR.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.cynoteck.petofyOPHR.adapters.ImmunizationHistoryAdopter;
import com.cynoteck.petofyOPHR.adapters.VaccineTypeAdapter;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.getVaccinationDetails.GetVaccinationModelParameter;
import com.cynoteck.petofyOPHR.params.getVaccinationDetails.GetVaccinationRequest;
import com.cynoteck.petofyOPHR.params.immunizationRequest.ImmunizationParams;
import com.cynoteck.petofyOPHR.params.immunizationRequest.ImmunizationRequest;
import com.cynoteck.petofyOPHR.params.petReportsRequest.PetClinicVisitDetailsRequest;
import com.cynoteck.petofyOPHR.params.petReportsRequest.PetClinicVistsDetailsParams;
import com.cynoteck.petofyOPHR.params.vaccinationSaveParams.VaccinationParameter;
import com.cynoteck.petofyOPHR.params.vaccinationSaveParams.VaccinationRequest;
import com.cynoteck.petofyOPHR.response.getImmunizationReport.PetImmunizationRecordResponse;
import com.cynoteck.petofyOPHR.response.getLastPrescriptionResponse.GetLastPrescriptionResponse;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.getClinicVisitDetails.GetClinicVisitsDetailsResponse;
import com.cynoteck.petofyOPHR.response.getVaccinationResponse.GetVaccineResponse;
import com.cynoteck.petofyOPHR.response.getVaccinationResponse.GetVaccineResponseModel;
import com.cynoteck.petofyOPHR.response.immuniztionHistory.ImmunizationHistoryResponse;
import com.cynoteck.petofyOPHR.response.immuniztionHistory.ImmunizationHistorymodel;
import com.cynoteck.petofyOPHR.response.loginRegisterResponse.UserPermissionMasterList;
import com.cynoteck.petofyOPHR.response.saveImmunizationData.SaveImmunizationResponse;
import com.cynoteck.petofyOPHR.response.staffPermissionListResponse.CheckStaffPermissionResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.ImmunizationOnclickListener;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Response;

public class PetDetailsActivity extends AppCompatActivity implements View.OnClickListener, ApiResponse, ImmunizationOnclickListener {
    String pet_position_in_list="",pet_image_url, pet_id, pet_name, patent_name, pet_bread, pet_unique_id = "", pet_sex = "", pet_age = "", pet_DOB = "", pet_encrypted_id = "", lastVisitEncryptedId = "", pet_cat_id = "";
    Methods methods;
    WebView webview;
    RelativeLayout back_arrow_RL;
    ArrayList<String> nextVisitList = new ArrayList<>();
    private ArrayList<ImmunizationHistorymodel> getImmunizationHistory;
    private ArrayList<GetVaccineResponseModel> getVaccineResponseModels;
    HashMap<String, String> nextVisitHas = new HashMap<>();

    private ArrayList<String> nextVisitDateList;
    private ArrayList<String> vaccineClassList;
    private ArrayList<String> vaccineList;
    private ArrayList<String> immunizationDateList;

    VaccineTypeAdapter vaccineTypeAdapter;
    ImmunizationHistoryAdopter immunizationHistoryAdopter;
    SharedPreferences sharedPreferences;
    String permissionId = "";
    Dialog vaccineDetailsDialog;

    ImageView petRegImage_IV;
    TextView pet_reg_name_TV, pet_reg_date_of_birth_TV, parent_name_TV, pet_reg__id_TV;
    ConstraintLayout add_record_CL, last_visit_CL, immunization_chart_CL, print_id_card_CL, view_history_CL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);
        methods = new Methods(this);
        Bundle extras = getIntent().getExtras();

        pet_id = extras.getString("pet_id");
        pet_image_url = extras.getString("pet_image_url");
        pet_name = extras.getString("pet_name");
        patent_name = extras.getString("pet_parent");
        pet_unique_id = extras.getString("pet_unique_id");
        pet_sex = extras.getString("pet_sex");
        pet_age = extras.getString("pet_age");
        pet_DOB = extras.getString("pet_DOB");
        pet_encrypted_id = extras.getString("pet_encrypted_id");
        pet_cat_id = extras.getString("pet_cat_id");
        lastVisitEncryptedId = extras.getString("lastVisitEncryptedId");
        Log.d("PET_DETAILS", "" + pet_DOB + " " + pet_id + " " + " " + pet_encrypted_id + " " + pet_cat_id + " " + lastVisitEncryptedId);

        back_arrow_RL = findViewById(R.id.back_arrow_RL);
        petRegImage_IV = findViewById(R.id.petRegImage_IV);
        pet_reg_name_TV = findViewById(R.id.pet_reg_name_TV);
        pet_reg_date_of_birth_TV = findViewById(R.id.pet_reg_date_of_birth_TV);
        parent_name_TV = findViewById(R.id.parent_name_TV);
        pet_reg__id_TV = findViewById(R.id.pet_reg__id_TV);
        add_record_CL = findViewById(R.id.add_record_CL);
        last_visit_CL = findViewById(R.id.last_visit_CL);
        immunization_chart_CL = findViewById(R.id.immunization_chart_CL);
        print_id_card_CL = findViewById(R.id.print_id_card_CL);
        view_history_CL = findViewById(R.id.view_history_CL);


        webview = findViewById(R.id.webview);

        back_arrow_RL.setOnClickListener(this);
        add_record_CL.setOnClickListener(this);
        last_visit_CL.setOnClickListener(this);
        immunization_chart_CL.setOnClickListener(this);
        print_id_card_CL.setOnClickListener(this);
        view_history_CL.setOnClickListener(this);

        sharedPreferences = getSharedPreferences("userdetails", 0);

        if (methods.isInternetOn()) {
//            methods.showCustomProgressBarDialog(this);
//            getImmunizationHistory();
//            getVaccinationDetails();
        } else {
            methods.DialogInternet();
        }

        pet_reg_name_TV.setText(pet_name.substring(0, 1).toUpperCase() + pet_name.substring(1) + " (" + pet_sex + ")");
        parent_name_TV.setText(patent_name);
        pet_reg__id_TV.setText(pet_unique_id);
        pet_reg_date_of_birth_TV.setText(pet_DOB);
        Glide.with(this)
                .load(pet_image_url)
                .placeholder(R.drawable.dummy_dog_image)
                .into(petRegImage_IV);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_history_CL:
                Intent petHistory = new Intent(this, SelectPetReportsActivity.class);
                Bundle dataHistry = new Bundle();
                dataHistry.putString("pet_id", pet_id.substring(0, pet_id.length() - 2));
                dataHistry.putString("pet_name", pet_name);
                dataHistry.putString("pet_unique_id", pet_unique_id);
                dataHistry.putString("pet_sex", pet_sex);
                dataHistry.putString("pet_owner_name", patent_name);
                dataHistry.putString("pet_owner_contact", "");
                dataHistry.putString("pet_DOB", pet_DOB);
                dataHistry.putString("pet_encrypted_id", pet_encrypted_id);
                dataHistry.putString("pet_image_url", pet_image_url);
                petHistory.putExtras(dataHistry);
                startActivity(petHistory);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

                break;
            case R.id.immunization_chart_CL:
                getVaccinationDetails();
//                vaccineDetailsDialog();
                break;

            case R.id.print_id_card_CL:
                Intent intent = new Intent(this, PetIdCardActivity.class);
                Bundle dataLabworkIdCard = new Bundle();
                dataLabworkIdCard.putString("id", pet_id);
                intent.putExtras(dataLabworkIdCard);
                startActivity(intent);
                break;

            case R.id.add_record_CL:
                String userTYpe = sharedPreferences.getString("user_type", "");
                if (userTYpe.equals("Vet Staff")) {
                    Gson gson = new Gson();
                    String json = sharedPreferences.getString("userPermission", null);
                    Type type = new TypeToken<ArrayList<UserPermissionMasterList>>() {
                    }.getType();
                    ArrayList<UserPermissionMasterList> arrayList = gson.fromJson(json, type);
                    Log.e("ArrayList", arrayList.toString());
                    Log.d("UserType", userTYpe);
                    permissionId = "6";
                    methods.showCustomProgressBarDialog(this);
                    String url = "user/CheckStaffPermission/" + permissionId;
                    Log.e("URL", url);
                    ApiService<CheckStaffPermissionResponse> service = new ApiService<>();
                    service.get(this, ApiClient.getApiInterface().getCheckStaffPermission(Config.token, url), "CheckPermission");
                } else if (userTYpe.equals("Veterinarian")) {
                    Intent petDetailsClinicVisits = new Intent(this, NewEntrysDetailsActivity.class);
                    Bundle dataClinicVisits = new Bundle();
                    dataClinicVisits.putString("pet_id", pet_id);
                    dataClinicVisits.putString("pet_name", pet_name);
                    dataClinicVisits.putString("pet_parent", patent_name);
                    dataClinicVisits.putString("pet_unique_id", pet_unique_id);
                    dataClinicVisits.putString("reports_id", "1.0");
                    dataClinicVisits.putString("pet_sex", pet_sex);
                    dataClinicVisits.putString("pet_age", pet_age);
                    dataClinicVisits.putString("add_button_text", "Clinic Visits");
                    dataClinicVisits.putString("pet_DOB", pet_DOB);
                    dataClinicVisits.putString("pet_encrypted_id", pet_encrypted_id);
                    dataClinicVisits.putString("pet_cat_id", pet_cat_id);
                    dataClinicVisits.putString("pet_DOB", pet_DOB);
                    dataClinicVisits.putString("pet_encrypted_id", pet_encrypted_id);
                    dataClinicVisits.putString("pet_cat_id", pet_cat_id);
                    dataClinicVisits.putString("pet_image_url", pet_image_url);
                    petDetailsClinicVisits.putExtras(dataClinicVisits);
                    startActivity(petDetailsClinicVisits);
                }

                break;

            case R.id.last_visit_CL:
                methods.showCustomProgressBarDialog(this);
                ImmunizationParams immunizationParams = new ImmunizationParams();
                immunizationParams.setEncryptedId(pet_encrypted_id);
                ImmunizationRequest immunizationRequest = new ImmunizationRequest();
                immunizationRequest.setData(immunizationParams);

                ApiService<PetImmunizationRecordResponse> service = new ApiService<>();
                service.get(this, ApiClient.getApiInterface().viewPetLastPrescriptionId(Config.token, immunizationRequest), "GetLastVisit");
                Log.d("GetImmunization", methods.getRequestJson(immunizationRequest));
                break;
            case R.id.back_arrow_RL:
                onBackPressed();
                Config.backCall = "hitUnique";
                break;
        }

    }

    private void getImmunizationHistory() {
        GetVaccinationModelParameter getVaccinationModelParameter = new GetVaccinationModelParameter();
        getVaccinationModelParameter.setEncryptedId(pet_encrypted_id);
        GetVaccinationRequest getVaccinationRequest = new GetVaccinationRequest();
        getVaccinationRequest.setData(getVaccinationModelParameter);
        ApiService<ImmunizationHistoryResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getPetImmunizationHistory(getVaccinationRequest), "GetPetImmunizationHistory");
        Gson gson = new Gson();
        String getImmunizationHistory = gson.toJson(getVaccinationRequest);
        Log.e("getImmunHistory=>", "" + getImmunizationHistory);
    }

    private void getVaccinationDetails() {
        methods.showCustomProgressBarDialog(this);
        GetVaccinationModelParameter getVaccinationModelParameter = new GetVaccinationModelParameter();
        getVaccinationModelParameter.setEncryptedId(pet_encrypted_id);
        GetVaccinationRequest getVaccinationRequest = new GetVaccinationRequest();
        getVaccinationRequest.setData(getVaccinationModelParameter);
        ApiService<GetVaccineResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getVaccinationScheduleChart(Config.token, getVaccinationRequest), "GetVaccinationScheduleChart");
        Log.e("GetVaccinSchedule==>", "" + getVaccinationRequest);
        Gson gson = new Gson();
        String getVaccinationDetails = gson.toJson(getVaccinationRequest);
        Log.e("getVaccinationDetails=>", "" + getVaccinationDetails);

    }

    private void getclinicVisitsReportDetails() {
        PetClinicVistsDetailsParams petClinicVistsDetailsParams = new PetClinicVistsDetailsParams();
        petClinicVistsDetailsParams.setId(pet_id.substring(0, pet_id.length() - 2));
        PetClinicVisitDetailsRequest petClinicVisitDetailsRequest = new PetClinicVisitDetailsRequest();
        petClinicVisitDetailsRequest.setData(petClinicVistsDetailsParams);
        Log.e("petClinicVisitDetail", methods.getRequestJson(petClinicVisitDetailsRequest));
        ApiService<JsonObject> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getLastPrescriptionUrl(Config.token, petClinicVisitDetailsRequest), "LastPrescriptionUrl");
    }

    private void saveVaccineData(VaccinationRequest vaccinationRequest) {
        ApiService<SaveImmunizationResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().saveImmunizationDetails(Config.token, vaccinationRequest), "SaveImmunizationDetails");
        Log.e("SaveVaccinSchedule==>", "" + vaccinationRequest);
    }

    @Override
    public void onResponse(Response arg0, String key) {
        Log.d("kkakakak", "" + key + " response: " + arg0);
        switch (key) {
            case "CheckPermission":
                try {
                    methods.customProgressDismiss();
                    CheckStaffPermissionResponse checkStaffPermissionResponse = (CheckStaffPermissionResponse) arg0.body();
                    Log.d("GetPetList", checkStaffPermissionResponse.toString());
                    int responseCode = Integer.parseInt(checkStaffPermissionResponse.getResponse().getResponseCode());

                    if (responseCode == 109) {
                        if (checkStaffPermissionResponse.getData().equals("true")) {
                            if (permissionId.equals("6")) {
                                Intent petDetailsClinicVisits = new Intent(this, NewEntrysDetailsActivity.class);
                                Bundle dataClinicVisits = new Bundle();
                                dataClinicVisits.putString("pet_id", pet_id);
                                dataClinicVisits.putString("pet_name", pet_name);
                                dataClinicVisits.putString("pet_parent", patent_name);
                                dataClinicVisits.putString("pet_unique_id", pet_unique_id);
                                dataClinicVisits.putString("reports_id", "1.0");
                                dataClinicVisits.putString("pet_sex", pet_sex);
                                dataClinicVisits.putString("pet_age", pet_age);
                                dataClinicVisits.putString("add_button_text", "Clinic Visits");
                                dataClinicVisits.putString("pet_DOB", pet_DOB);
                                dataClinicVisits.putString("pet_encrypted_id", pet_encrypted_id);
                                dataClinicVisits.putString("pet_cat_id", pet_cat_id);
                                dataClinicVisits.putString("lastVisitEncryptedId", lastVisitEncryptedId);
                                petDetailsClinicVisits.putExtras(dataClinicVisits);
                                startActivity(petDetailsClinicVisits);
                            } else if (permissionId.equals("7")) {
                                if (methods.isInternetOn()) {
                                    getclinicVisitsReportDetails();

                                } else {
                                    methods.DialogInternet();
                                }
                            }
                        } else {
                            Toast.makeText(this, "Permission not Granted!!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "Please Try Again!!", Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;


            case "GetPetClinicVisitDetails":
                try {
                    methods.customProgressDismiss();
                    Log.d("ResponseClinicVisit", arg0.body().toString());
                    GetLastPrescriptionResponse getClinicVisitsDetailsResponse = (GetLastPrescriptionResponse) arg0.body();
                    int responseCode = Integer.parseInt(getClinicVisitsDetailsResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        if (getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getNatureOfVisitId().equals("1.0")) {
                            String str = methods.pdfGenarator(getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getPetName(),
                                    getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getPetAge(),
                                    getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getPetSex(),
                                    getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getPetParentName(),
                                    getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getTemperature(),
                                    getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getHistory(),
                                    getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getDescription(),
                                    getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getDiagnosisProcedure(),
                                    getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getTreatmentRemarks(),
                                    getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getFollowUpDate(),
                                    getClinicVisitsDetailsResponse.getData().getVeterinarianDetails().getVetRegistrationNumber());
                            webview.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    methods.customProgressDismiss();
                                    Context context = PetDetailsActivity.this;
                                    PrintManager printManager = (PrintManager) getSystemService(context.PRINT_SERVICE);
                                    PrintDocumentAdapter adapter = null;
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                        adapter = webview.createPrintDocumentAdapter();
                                    }
                                    String JobName = getString(R.string.app_name) + "Document";
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                        PrintJob printJob = printManager.print(JobName, adapter, new PrintAttributes.Builder().build());
                                    }
                                }
                            }, 500);

                        } else if (getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getNatureOfVisitId().equals("5.0")) {
                            String str = methods.pdfGenaratorDeworming(getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getPetName(),
                                    getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getPetAge(),
                                    getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getPetSex(),
                                    getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getPetParentName(),
                                    getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getTemperature(),
                                    getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getDescription(),
                                    getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getHistory(),
                                    getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getDewormerName(),
                                    getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getDewormerDose(),
                                    getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getTreatmentRemarks(),
                                    getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getFollowUpDate(),
                                    getClinicVisitsDetailsResponse.getData().getVeterinarianDetails().getVetRegistrationNumber(),
                                    getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getWeightLbs()
                            );
                            webview.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    methods.customProgressDismiss();
                                    Context context = PetDetailsActivity.this;
                                    PrintManager printManager = (PrintManager) getSystemService(context.PRINT_SERVICE);
                                    PrintDocumentAdapter adapter = null;
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                        adapter = webview.createPrintDocumentAdapter();
                                    }
                                    String JobName = getString(R.string.app_name) + "Document";
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                        PrintJob printJob = printManager.print(JobName, adapter, new PrintAttributes.Builder().build());
                                    }
                                }
                            }, 500);
                        } else if (getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getNatureOfVisitId().equals("4.0")) {
                            ArrayList<String> immunizationDate = new ArrayList<>();
                            ArrayList<String> vaccineClass = new ArrayList<>();
                            ArrayList<String> nextDueDate = new ArrayList<>();
                            ArrayList<String> vaccineType = new ArrayList<>();

                            ArrayList<String> immunizationDatePending = new ArrayList<>();
                            ArrayList<String> vaccineClassPending = new ArrayList<>();
                            ArrayList<String> nextDueDatePending = new ArrayList<>();
                            ArrayList<String> vaccineTypePending = new ArrayList<>();

                            for (int i = 0; i < getClinicVisitsDetailsResponse.getData().getPendingVaccinations().size(); i++) {
                                if (getClinicVisitsDetailsResponse.getData().getPendingVaccinations().get(i).getIsVaccinated().equals("true")) {
                                    immunizationDate.add(getClinicVisitsDetailsResponse.getData().getPendingVaccinations().get(i).getVaccinationDate());
                                    vaccineClass.add(getClinicVisitsDetailsResponse.getData().getPendingVaccinations().get(i).getVaccineName());
                                    nextDueDate.add(getClinicVisitsDetailsResponse.getData().getPendingVaccinations().get(i).getNextVaccinationDate().substring(0, getClinicVisitsDetailsResponse.getData().getPendingVaccinations().get(i).getNextVaccinationDate().length() - 9));
                                    vaccineType.add(getClinicVisitsDetailsResponse.getData().getPendingVaccinations().get(i).getVaccineType());

                                } else {
                                    immunizationDatePending.add(getClinicVisitsDetailsResponse.getData().getPendingVaccinations().get(i).getVaccinationDate());
                                    vaccineClassPending.add(getClinicVisitsDetailsResponse.getData().getPendingVaccinations().get(i).getVaccineName());
                                    nextDueDatePending.add(getClinicVisitsDetailsResponse.getData().getPendingVaccinations().get(i).getNextVaccinationDate().substring(0, getClinicVisitsDetailsResponse.getData().getPendingVaccinations().get(i).getNextVaccinationDate().length() - 9));
                                    vaccineTypePending.add(getClinicVisitsDetailsResponse.getData().getPendingVaccinations().get(i).getVaccineType());

                                }
                            }
                            final JSONArray date = new JSONArray(immunizationDate);
                            final JSONArray vaccine = new JSONArray(vaccineClass);
                            final JSONArray nextDate = new JSONArray(nextDueDate);
                            final JSONArray vType = new JSONArray(vaccineType);

                            Log.d("jsjsjjsjs", "" + date.length());

                            final JSONArray datePending = new JSONArray(immunizationDatePending);
                            final JSONArray vaccinePending = new JSONArray(vaccineClassPending);
                            final JSONArray nextDatePending = new JSONArray(nextDueDatePending);
                            final JSONArray vTypePending = new JSONArray(vaccineTypePending);

                            Log.d("jsjsjjsjs", "" + datePending.length());

                            Log.e("aaaaaa", vaccineClass.toString());
                            Log.e("aaaaaa", vaccine.toString());
                            methods.customProgressDismiss();
                            String immunizationSet = methods.immunizationPdfGenarator(pet_name, pet_age, pet_sex, parent_name_TV.getText().toString(), Config.user_verterian_reg_no, vType, vaccine, nextDate, vTypePending, vaccinePending, nextDatePending);
                            WebSettings webSettings = webview.getSettings();
                            webSettings.setJavaScriptEnabled(true);
                            webview.loadDataWithBaseURL(null, immunizationSet, "text/html", "utf-8", null);
                            new Handler().postDelayed(new Runnable() {
                                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                                @Override
                                public void run() {
                                    Context context = PetDetailsActivity.this;
                                    PrintManager printManager = (PrintManager) PetDetailsActivity.this.getSystemService(context.PRINT_SERVICE);
                                    PrintDocumentAdapter adapter = null;
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                        adapter = webview.createPrintDocumentAdapter();
                                    }
                                    String JobName = getString(R.string.app_name) + "Document";
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                        PrintJob printJob = printManager.print(JobName, adapter, new PrintAttributes.Builder().build());
                                    }
                                }
                            }, 500);

                        }

                    } else if (responseCode == 404) {
                        Toast.makeText(this, getClinicVisitsDetailsResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please try again!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "GetPetImmunizationHistory":
                try {
                    Log.d("GetImmuniHistory", arg0.body().toString());
                    ImmunizationHistoryResponse immunizationHistoryResponse = (ImmunizationHistoryResponse) arg0.body();
                    int responseCode = Integer.parseInt(immunizationHistoryResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        getImmunizationHistory = immunizationHistoryResponse.getData();

                        nextVisitDateList = new ArrayList<>();
                        vaccineClassList = new ArrayList<>();
                        vaccineList = new ArrayList<>();
                        immunizationDateList = new ArrayList<>();

                        for (int outer = 0; outer < immunizationHistoryResponse.getData().size(); outer++) {
                            if (immunizationHistoryResponse.getData().get(outer).getPetVaccinationDetail().size() > 0) {
                                for (int inner = 0; inner < immunizationHistoryResponse.getData().get(outer).getPetVaccinationDetail().size(); inner++) {
                                    nextVisitDateList.add(immunizationHistoryResponse.getData().get(outer).getFollowUpDate());
                                    vaccineClassList.add(immunizationHistoryResponse.getData().get(outer).getPetVaccinationDetail().get(inner).getVaccineType());
                                    vaccineList.add(immunizationHistoryResponse.getData().get(outer).getPetVaccinationDetail().get(inner).getVaccine());
                                    immunizationDateList.add(immunizationHistoryResponse.getData().get(outer).getPetVaccinationDetail().get(inner).getImmunizationDate());
                                }

                            }
                        }
                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "GetVaccinationScheduleChart":
                try {
                    methods.customProgressDismiss();
                    Log.d("GetVaccinationSchedul", arg0.body().toString());
                    GetVaccineResponse getVaccineResponse = (GetVaccineResponse) arg0.body();
                    int responseCode = Integer.parseInt(getVaccineResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        Log.e("amamamammamamam", "Vaccine Detailsss" + getVaccineResponse.getData().size());
                        getVaccineResponseModels = getVaccineResponse.getData();
                        Log.e("VaccineResponseModels", methods.getRequestJson(arg0.body()));
                        vaccineDetailsDialog();
                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "SaveImmunizationDetails":
                try {
                    Log.d("SaveImmunizationDetails", arg0.body().toString());
                    SaveImmunizationResponse getVaccineResponse = (SaveImmunizationResponse) arg0.body();
                    int responseCode = Integer.parseInt(getVaccineResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        Toast.makeText(this, "Save Successfully", Toast.LENGTH_SHORT).show();
                        vaccineDetailsDialog.dismiss();
                    } else {
                        Toast.makeText(this, "Not Save", Toast.LENGTH_SHORT).show();
                        vaccineDetailsDialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "LastPrescriptionUrl":
                try {
                    Log.d("LastPrescriptionUrl", arg0.body().toString());
                    JsonObject lastPrescriptionUrl = (JsonObject) arg0.body();
                    JsonObject response = lastPrescriptionUrl.getAsJsonObject("response");
                    Log.d("hhshshhs", "" + response);

                    int responseCode = Integer.parseInt(String.valueOf(response.get("responseCode")));

                    if (responseCode == 109) {
                        String urlData = String.valueOf(lastPrescriptionUrl.get("data"));
                        Log.e("URL", urlData);
                        Intent intent = new Intent(this, PdfEditorActivity.class);
                        intent.putExtra("URL", urlData);
                        startActivity(intent);
                    } else {
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "GetLastVisit":
                try {
                    Log.d("GetLastVisit", arg0.body().toString());
                    PetImmunizationRecordResponse immunizationRecordResponse = (PetImmunizationRecordResponse) arg0.body();
                    methods.customProgressDismiss();
                    int responseCode = Integer.parseInt(immunizationRecordResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        if (immunizationRecordResponse.getData().equals("")) {
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

    private void vaccineDetailsDialog() {
        vaccineDetailsDialog = new Dialog(this);
        vaccineDetailsDialog.setContentView(R.layout.vaccine_deatils_dilog);
//        CardView age_group_CV = vaccineDetailsDialog.findViewById(R.id.age_group_CV);
//        CardView periodic_vaccine_CV = vaccineDetailsDialog.findViewById(R.id.periodic_vaccine_CV);
//        CardView history_CV = vaccineDetailsDialog.findViewById(R.id.history_CV);
//        final LinearLayout immunization_history_layout = vaccineDetailsDialog.findViewById(R.id.immunization_history_layout);
//        final TextView age_group_TV = vaccineDetailsDialog.findViewById(R.id.age_group_TV);
//        final TextView periodic_vaccine_TV = vaccineDetailsDialog.findViewById(R.id.periodic_vaccine_TV);
//        final TextView history_TV = vaccineDetailsDialog.findViewById(R.id.history_TV);

        MaterialCardView back_arrow_CV = vaccineDetailsDialog.findViewById(R.id.back_arrow_CV);
        final RecyclerView vaccine_type_name_list = vaccineDetailsDialog.findViewById(R.id.vaccine_type_name_list);
//        final RecyclerView pereodic_list = vaccineDetailsDialog.findViewById(R.id.pereodic_list);
//        final RecyclerView immunization_history_list = vaccineDetailsDialog.findViewById(R.id.immunization_history_list);

//        pereodic_list.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PetDetailsActivity.this);
        vaccine_type_name_list.setLayoutManager(linearLayoutManager);
        vaccine_type_name_list.setNestedScrollingEnabled(false);
        if ((getVaccineResponseModels != null)) {
            if (getVaccineResponseModels.size() > 0) {
                vaccineTypeAdapter = new VaccineTypeAdapter(PetDetailsActivity.this, getVaccineResponseModels, this);
                vaccine_type_name_list.setAdapter(vaccineTypeAdapter);
                vaccineTypeAdapter.notifyDataSetChanged();
            }
        }

        //////Set Immunization History List

        LinearLayoutManager linearLayoutManagerone = new LinearLayoutManager(PetDetailsActivity.this);
//        immunization_history_list.setLayoutManager(linearLayoutManagerone);
//        immunization_history_list.setNestedScrollingEnabled(false);

//        if (nextVisitDateList != null) {
//            if ((nextVisitDateList.size() > 0)) {
//                immunizationHistoryAdopter = new ImmunizationHistoryAdopter(PetDetailsActivity.this, nextVisitDateList, vaccineClassList, vaccineList, immunizationDateList);
//                immunization_history_list.setAdapter(immunizationHistoryAdopter);
//                immunizationHistoryAdopter.notifyDataSetChanged();
//            }
//        }

        back_arrow_CV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vaccineDetailsDialog.dismiss();
            }
        });

//        age_group_CV.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClick(View v) {
//                age_group_TV.setTextColor(R.color.black_color);
//                periodic_vaccine_TV.setTextColor(R.color.grayColorCode);
//                history_TV.setTextColor(R.color.grayColorCode);
//                vaccine_type_name_list.setVisibility(View.VISIBLE);
//                pereodic_list.setVisibility(View.GONE);
//                immunization_history_list.setVisibility(View.GONE);
//                immunization_history_layout.setVisibility(View.GONE);
//            }
//        });
//
//        periodic_vaccine_CV.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClick(View v) {
//                age_group_TV.setTextColor(R.color.grayColorCode);
//                periodic_vaccine_TV.setTextColor(R.color.black_color);
//                history_TV.setTextColor(R.color.grayColorCode);
//                vaccine_type_name_list.setVisibility(View.GONE);
//                pereodic_list.setVisibility(View.VISIBLE);
//                immunization_history_list.setVisibility(View.GONE);
//                immunization_history_layout.setVisibility(View.GONE);
//            }
//        });
//
//        history_CV.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceAsColor")
//            @Override
//            public void onClick(View v) {
//                age_group_TV.setTextColor(R.color.grayColorCode);
//                periodic_vaccine_TV.setTextColor(R.color.grayColorCode);
//                history_TV.setTextColor(R.color.black_color);
//                vaccine_type_name_list.setVisibility(View.GONE);
//                pereodic_list.setVisibility(View.GONE);
//                immunization_history_list.setVisibility(View.VISIBLE);
//                immunization_history_layout.setVisibility(View.VISIBLE);
//            }
//        });
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = vaccineDetailsDialog.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        vaccineDetailsDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        vaccineDetailsDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        vaccineDetailsDialog.show();

    }

    @Override
    public void onError(Throwable t, String key) {

    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onItemClickImmunizationPrimary(int position) {
        Log.e("vaccinlist_primery", "");
        VaccinationParameter vaccinationParameter = new VaccinationParameter();
        vaccinationParameter.setEncryptedId(pet_encrypted_id);
        vaccinationParameter.setVaccinationScheduleId(getVaccineResponseModels.get(position).getVaccinationSchedule().getId());
        vaccinationParameter.setVaccine(getVaccineResponseModels.get(position).getVaccinationSchedule().getPrimaryVaccine());
        vaccinationParameter.setVaccineType("Primary");
        VaccinationRequest vaccinationRequest = new VaccinationRequest();
        vaccinationRequest.setData(vaccinationParameter);
        saveVaccineData(vaccinationRequest);

    }

    @Override
    public void onItemClickImmunizationBoosterOne(int position) {
        Log.e("vaccinlist_booster_one", "");
        VaccinationParameter vaccinationParameter = new VaccinationParameter();
        vaccinationParameter.setEncryptedId(pet_encrypted_id);
        vaccinationParameter.setVaccinationScheduleId(getVaccineResponseModels.get(position).getVaccinationSchedule().getId());
        vaccinationParameter.setVaccine(getVaccineResponseModels.get(position).getVaccinationSchedule().getPrimaryVaccine());
        vaccinationParameter.setVaccineType("Booster One");
        VaccinationRequest vaccinationRequest = new VaccinationRequest();
        vaccinationRequest.setData(vaccinationParameter);
        saveVaccineData(vaccinationRequest);
    }


    @Override
    public void onItemClickImmunizationBoosterTwo(int position) {
        Log.e("vaccinlist_Booster_two", "");
        VaccinationParameter vaccinationParameter = new VaccinationParameter();
        vaccinationParameter.setEncryptedId(pet_encrypted_id);
        vaccinationParameter.setVaccinationScheduleId(getVaccineResponseModels.get(position).getVaccinationSchedule().getId());
        vaccinationParameter.setVaccine(getVaccineResponseModels.get(position).getVaccinationSchedule().getPrimaryVaccine());
        vaccinationParameter.setVaccineType("Booster Two");
        VaccinationRequest vaccinationRequest = new VaccinationRequest();
        vaccinationRequest.setData(vaccinationParameter);
        saveVaccineData(vaccinationRequest);
    }
}
