
package com.cynoteck.petofyOPHR.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
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
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.adapters.ImmunazationVaccineAdopter;
import com.cynoteck.petofyOPHR.adapters.ImmunizationHistoryAdopter;
import com.cynoteck.petofyOPHR.adapters.VaccineTypeAdapter;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.VaccinationTypeByVaccineName.VaccinationTypeByVaccineNameParams;
import com.cynoteck.petofyOPHR.params.VaccinationTypeByVaccineName.VaccinationTypeByVaccineNameRequest;
import com.cynoteck.petofyOPHR.params.addImmunizationClinic.ImmunizationAddClinicModel;
import com.cynoteck.petofyOPHR.params.addImmunizationClinic.ImmunizationClinicData;
import com.cynoteck.petofyOPHR.params.addPetClinicParamRequest.AddPetClinicParam;
import com.cynoteck.petofyOPHR.params.addPetClinicParamRequest.AddPetClinicRequest;
import com.cynoteck.petofyOPHR.params.getFirstVaccine.GetFirstVaccineModel;
import com.cynoteck.petofyOPHR.params.getFirstVaccine.GetFirstVaccineRequest;
import com.cynoteck.petofyOPHR.params.getPetListRequest.GetPetListParams;
import com.cynoteck.petofyOPHR.params.getPetListRequest.GetPetListRequest;
import com.cynoteck.petofyOPHR.params.getVaccinationDetails.GetVaccinationModelParameter;
import com.cynoteck.petofyOPHR.params.getVaccinationDetails.GetVaccinationRequest;
import com.cynoteck.petofyOPHR.params.immunizationRequest.ImmunizationParameter;
import com.cynoteck.petofyOPHR.params.immunizationRequest.ImmunizationParams;
import com.cynoteck.petofyOPHR.params.immunizationRequest.ImmunizationRequest;
import com.cynoteck.petofyOPHR.params.immunizationRequest.ImmunizationRequestt;
import com.cynoteck.petofyOPHR.params.nextVaccineParameter.NextVaccineParam;
import com.cynoteck.petofyOPHR.params.nextVaccineParameter.NextVaccineRequest;
import com.cynoteck.petofyOPHR.params.petReportsRequest.PetClinicVisitDetailsRequest;
import com.cynoteck.petofyOPHR.params.petReportsRequest.PetClinicVistsDetailsParams;
import com.cynoteck.petofyOPHR.params.removeVccination.RemoveParams;
import com.cynoteck.petofyOPHR.params.removeVccination.RemoveRequest;
import com.cynoteck.petofyOPHR.params.saveVaccinationParameter.SaveRequest;
import com.cynoteck.petofyOPHR.params.saveVaccinationParameter.SaveVaccineModel;
import com.cynoteck.petofyOPHR.params.searcgDiagnosisRequest.SearchDiagnosisParameter;
import com.cynoteck.petofyOPHR.params.searcgDiagnosisRequest.SearchDiagnosisRequestData;
import com.cynoteck.petofyOPHR.params.searchRemarksParameter.SearchRemaksParametr;
import com.cynoteck.petofyOPHR.params.searchRemarksParameter.SearchRemaksRequest;
import com.cynoteck.petofyOPHR.params.updateClinicVisitsParams.UpdateClinicReportsParams;
import com.cynoteck.petofyOPHR.params.updateClinicVisitsParams.UpdateClinicReportsRequest;
import com.cynoteck.petofyOPHR.params.vaccinationSaveParams.VaccinationParameter;
import com.cynoteck.petofyOPHR.params.vaccinationSaveParams.VaccinationRequest;
import com.cynoteck.petofyOPHR.response.VaccinationTypeByVaccineNameResponse.VaccinationTypeByVaccineNameResponse;
import com.cynoteck.petofyOPHR.response.addPet.imageUpload.ImageResponse;
import com.cynoteck.petofyOPHR.response.addPetClinicresponse.AddpetClinicResponse;
import com.cynoteck.petofyOPHR.response.clinicVisist.ClinicVisitResponse;
import com.cynoteck.petofyOPHR.response.getFirstVaccineReponse.GetFirstVaccineResponseData;
import com.cynoteck.petofyOPHR.response.getImmunizationReport.PetImmunizationRecordResponse;
import com.cynoteck.petofyOPHR.response.getLastPrescriptionResponse.GetLastPrescriptionResponse;
import com.cynoteck.petofyOPHR.response.getPetDetailsResponse.GetPetResponse;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.GetReportsTypeResponse;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.getClinicVisitDetails.GetClinicVisitsDetailsResponse;
import com.cynoteck.petofyOPHR.response.getVaccinationResponse.GetVaccineResponse;
import com.cynoteck.petofyOPHR.response.getVaccinationResponse.GetVaccineResponseModel;
import com.cynoteck.petofyOPHR.response.immunizationVaccineType.ImmunizationVaccineResponse;
import com.cynoteck.petofyOPHR.response.immuniztionHistory.ImmunizationHistoryResponse;
import com.cynoteck.petofyOPHR.response.immuniztionHistory.ImmunizationHistorymodel;
import com.cynoteck.petofyOPHR.response.nextVaccineResponse.NextVaccineResponse;
import com.cynoteck.petofyOPHR.response.saveImmunizationData.SaveImmunizationResponse;
import com.cynoteck.petofyOPHR.response.saveResponse.SaveResponseData;
import com.cynoteck.petofyOPHR.response.searchDiagnosisResponse.SearchDiagnosisResponseData;
import com.cynoteck.petofyOPHR.response.searchDiagnosisResponse.SearchDiagnosisResponseResponse;
import com.cynoteck.petofyOPHR.response.searchRemaks.SearchRemaksResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.ImmunizationOnclickListener;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.json.JSONArray;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class AddClinicActivity extends AppCompatActivity implements View.OnClickListener, ApiResponse, ImmunizationOnclickListener, TextWatcher {
    boolean nextDewormerSelected = false;
    ConstraintLayout vaccine_layout;
    ProgressBar horizontal_progress_bar;
    RelativeLayout view_profile_RL;
    JsonArray myCustomArray;
                     Calendar cldr = Calendar.getInstance();

    private DatePicker datepicker;
    String report_id = "", visitIdString = "", pet_age = "", strNatureOfVist = "", appointment_ID = "0", pet_DOB = "", pet_encrypted_id = "", strDocumentUrl = "", visitId = "", natureOfVisit = "", pet_id = "",
            pet_name = "", pet_owner_name = "", pet_sex = "", pet_unique_id = "", veterian_name = "", descrisption = "", strPetAge = "", getStrVaccineType = "", getStrVaccineName = "",
            Remarks = "", visitDate = "", history = "", remarks = "", dtOfOnset = "", flowUpDt = "", weight = "", temparature = "", diagnosis = "", strNextVisitDate = "",
            strVacine = "", strDewormerName = "", strDewormerDose = "", strToolbarName = "", PetCategoryId = "", cocatVal = null, nextVaccineName = null, nextVaccineType = null,
            valueConcat = null, dewormerName = null, dewormerDose = null, strVaccineType = "", strVaccineName = "", strNextDewormer = "", pet_cat_id = "";
    Bundle data = new Bundle();
    TextView add_immunization_data, folow_up_dt_view, ilness_onset, next_visit, Dewormer_name_ET, Dewormer_name_TV, Dewormer_ET, Dewormer_TV, clinic_head_line, add_immunization_data_added,
            clinicCalenderTextViewVisitDt, clinicIlness_onset, date_of_illness_TV, follow_up_dt, nextImmunizationDate,
            clinic_peto_edit_reg_number_dialog, next_vaccine_TV, next_vaccine_type_TV, history_TV,
            next_dewormer_TV, pet_nameTV, pet_idTV, pet_genderTV, pet_ageTV, pet_breadTV, pet_typeTV;
    ImageView document_name, back_arrow_IV;
    LinearLayout add_immunization_data_LL, addPrescriptionButton, diagnosis_Layout, deworming_name_Layout, deworming_dose_Layout/*, imaunizatioHeader*/;
    EditText clinicVeterian_name_ET, clinicCescription_ET, remaks_ET, next_vaccine_ET, history_ET, diagnosis_result, remarks_ET,
            weight_ET, clinicTemparature_ET;

    MultiAutoCompleteTextView clinicTreatment_remarks_MT, clinicDiagnosis_ET, deworming_AC, deworming_dose_AC;
    AppCompatSpinner clinicNature_of_visit_spinner, clinicNext_visit_spinner, vaccine_type, vaccine_name,
            next_vaccine_type_spinner, next_dewormer_spinner;
    LinearLayout clinicDocument_layout, treatment_remarks_LL;
    Button clinicSave_clinic_data;
    WebView webview;

    Methods methods;
    RecyclerView immunization_data;
    ArrayList<String> nextVisitList;
    ArrayList<String> natureOfVisitList;
    ArrayList<String> vaccineTypeList;
    ArrayList<String> vaccineNameList;
    ArrayList<String> nextDeworming;
    ArrayList<String> nextVaccineTypeList;
    ArrayList<HashMap<String, String>> vaccinationModels;
    HashMap<String, String> vaccinationationModelHash;
    ImmunazationVaccineAdopter hospitalizationReportsAdapter;
    VaccineTypeAdapter vaccineTypeAdapter;
    ImmunizationHistoryAdopter immunizationHistoryAdopter;
    HashMap<String, String> nextVisitHas = new HashMap<>();
    HashMap<String, String> natureOfVisitHashMap = new HashMap<>();
    ArrayList<String> VaccineList = new ArrayList<String>();
    DatePickerDialog picker, dialogPicker;
    TextView description_TV, weight_TV, temparature_TV, diagnosis_TV, treatment_remaks_TV;

    private static final String IMAGE_DIRECTORY = "/Picture";
    private int GALLERY = 1, CAMERA = 2;
    File file = null;
    Dialog dialog, vaccineDialog, vaccineDetailsDialog,pet_profile_dialog, find_doses_dewormer_diagnosis_dialog;
    Bitmap bitmap, thumbnail;
    int backPressVal = 0;
    private ArrayList<GetVaccineResponseModel> getVaccineResponseModels;
    private ArrayList<ImmunizationHistorymodel> getImmunizationHistory;

    //Immunization History Array
    private ArrayList<String> nextVisitDateList;
    private ArrayList<String> vaccineClassList;
    private ArrayList<String> vaccineList;
    private ArrayList<String> immunizationDateList;
    ImageView dialog_cross_IV,dialog_pet_profile_image_IV;
    TextView dialog_pet_name_TV,dialog_pet_breed_TV ,dialog_pet_age_TV,dialog_pet_gender_TV,dialog_pet_id_TV,dialog_pet_parent_name_TV,dialog_parent_phone_TV,dialog_parent_address_TV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clinic);

        init();
        requestMultiplePermissions();

    }

//    @Override
//    protected void onRestart() {
//        init();
//        requestMultiplePermissions();
//        super.onRestart();
//    }

    private void init() {
        methods = new Methods(this);
        Bundle extras = getIntent().getExtras();
        clinic_peto_edit_reg_number_dialog = findViewById(R.id.clinic_peto_edit_reg_number_dialog);
        pet_nameTV = findViewById(R.id.pet_nameTV);
        horizontal_progress_bar = findViewById(R.id.horizontal_progress_bar);
        pet_idTV = findViewById(R.id.pet_idTV);
        pet_genderTV = findViewById(R.id.pet_genderTV);
        pet_ageTV = findViewById(R.id.pet_ageTV);
        pet_breadTV = findViewById(R.id.pet_breadTV);
        pet_typeTV = findViewById(R.id.pet_typeTV);
        clinic_head_line = findViewById(R.id.clinic_head_line);
        clinicVeterian_name_ET = findViewById(R.id.veterian_name_ET);
        clinicNature_of_visit_spinner = findViewById(R.id.nature_of_visit_spinner);
        clinicCalenderTextViewVisitDt = findViewById(R.id.calenderTextViewVisitDt);
        clinicCescription_ET = findViewById(R.id.description_ET);
        date_of_illness_TV = findViewById(R.id.date_of_illness_TV);
        document_name = findViewById(R.id.document_name);
        remaks_ET = findViewById(R.id.remaks_TV);
        follow_up_dt = findViewById(R.id.follow_up_dt);
        weight_TV = findViewById(R.id.weight_TV);
        weight_ET = findViewById(R.id.weight_ET);
        folow_up_dt_view = findViewById(R.id.folow_up_dt_view);
        add_immunization_data=findViewById(R.id.add_immunization_data);
        Dewormer_TV = findViewById(R.id.Dewormer_TV);
        Dewormer_ET = findViewById(R.id.Dewormer_ET);
        diagnosis_TV = findViewById(R.id.diagnosis_TV);
        treatment_remaks_TV = findViewById(R.id.treatment_remaks_TV);
        remarks_ET = findViewById(R.id.remarks_ET);
        Dewormer_name_TV = findViewById(R.id.Dewormer_name_TV);
        Dewormer_name_ET = findViewById(R.id.Dewormer_name_ET);
        clinicTemparature_ET = findViewById(R.id.temparature_ET);
        next_visit = findViewById(R.id.next_visit);
        clinicIlness_onset = findViewById(R.id.ilness_onset);
        clinicDiagnosis_ET = findViewById(R.id.diagnosis_ET);
        deworming_AC = findViewById(R.id.deworming_AC);
        deworming_dose_AC = findViewById(R.id.deworming_dose_AC);
        clinicTreatment_remarks_MT = findViewById(R.id.clinicTreatment_remarks_MT);
        clinicNext_visit_spinner = findViewById(R.id.next_visit_spinner);
        clinicDocument_layout = findViewById(R.id.document_layout);
        treatment_remarks_LL = findViewById(R.id.treatment_remarks_LL);
        clinicSave_clinic_data = findViewById(R.id.save_clinic_data);
        back_arrow_IV = findViewById(R.id.back_arrow_IV);
        vaccine_layout = findViewById(R.id.vaccine_layout);
        diagnosis_Layout = findViewById(R.id.diagnosis_Layout);
        deworming_name_Layout = findViewById(R.id.deworming_name_Layout);
        deworming_dose_Layout = findViewById(R.id.deworming_dose_Layout);
        add_immunization_data_LL = findViewById(R.id.add_immunization_data_LL);
        immunization_data = findViewById(R.id.immunization_data);
        description_TV = findViewById(R.id.description_TV);
        temparature_TV = findViewById(R.id.temparature_TV);
        next_vaccine_TV = findViewById(R.id.next_vaccine_TV);
        next_vaccine_type_TV = findViewById(R.id.next_vaccine_type_TV);
        next_vaccine_ET = findViewById(R.id.next_vaccine_ET);
        history_TV = findViewById(R.id.history_TV);
        next_dewormer_TV = findViewById(R.id.next_dewormer_TV);
        next_dewormer_spinner = findViewById(R.id.next_dewormer_spinner);
        history_ET = findViewById(R.id.history_ET);
        diagnosis_result = findViewById(R.id.diagnosis_result);
        next_vaccine_type_spinner = findViewById(R.id.next_vaccine_type_spinner);
        webview = findViewById(R.id.webview);
        add_immunization_data_added = findViewById(R.id.add_immunization_data_added);
//        imaunizatioHeader = findViewById(R.id.imaunizatioHeader);
//        immunizationView = findViewById(R.id.immunizationView);
        view_profile_RL=findViewById(R.id.view_profile_RL);


        clinicCalenderTextViewVisitDt.setOnClickListener(this);
        clinicIlness_onset.setOnClickListener(this);
        folow_up_dt_view.setOnClickListener(this);
        clinicSave_clinic_data.setOnClickListener(this);
        back_arrow_IV.setOnClickListener(this);
        add_immunization_data_LL.setOnClickListener(this);

        clinicCescription_ET.addTextChangedListener(this);
        clinicDiagnosis_ET.addTextChangedListener(this);
        clinicTreatment_remarks_MT.addTextChangedListener(this);
        deworming_AC.addTextChangedListener(this);
        deworming_dose_AC.addTextChangedListener(this);

        textWatcher();
        petDeatilsDialogInit();


        clinicTreatment_remarks_MT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("kkakakkakak", "beforeTextChanged" + new String(charSequence.toString()));
                Log.d("kkakakkakak", "beforeTextChangedLength" + charSequence.length());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("kkakakkakak", "onTextChanged" + new String(charSequence.toString()));
                Log.d("kkakakkakak", "onTextChangedLength" + charSequence.length());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d("kkakakkakak", "afterTextChanged" + new String(editable.toString()));
                String value = editable.toString();
                SearchRemaksParametr searchRemaksParametr = new SearchRemaksParametr();
                searchRemaksParametr.setPrefix(value);
                SearchRemaksRequest searchRemaksRequest = new SearchRemaksRequest();
                searchRemaksRequest.setAddPetParams(searchRemaksParametr);
                if (methods.isInternetOn()) {
                    getTreatmentRemaks(searchRemaksRequest);
                } else {
                    methods.DialogInternet();
                }

            }
        });

        //8583896504(Suresh Das).

        if (extras != null) {
            report_id = extras.getString("report_id");
            pet_id = extras.getString("pet_id");
            pet_name = extras.getString("pet_name");
            pet_owner_name = extras.getString("pet_owner_name");
            pet_sex = extras.getString("pet_sex");
            pet_age = extras.getString("pet_age");
            pet_unique_id = extras.getString("pet_unique_id");
            strNatureOfVist = extras.getString("nature_of_visit");
            visitDate = extras.getString("visit_dt");
            descrisption = extras.getString("visit_description");
            Remarks = extras.getString("remarks");
            weight = extras.getString("visit_weight");
            temparature = extras.getString("visit_temparature");
            dtOfOnset = extras.getString("dt_of_illness");
            diagnosis = extras.getString("pet_diognosis");
            flowUpDt = extras.getString("next_dt");
            pet_DOB = extras.getString("pet_DOB");
            appointment_ID = extras.getString("appointment_ID");
            pet_encrypted_id = extras.getString("pet_encrypted_id");
            strToolbarName = extras.getString("toolbar_name");
            pet_cat_id = extras.getString("pet_cat_id");

            Log.d("PET_DETAILS", " " + pet_DOB + " " + pet_encrypted_id + " " + descrisption + "" + Remarks + " " + pet_id + "" + PetCategoryId);

            if (strToolbarName.equals("Update Clinic"))
                clinicSave_clinic_data.setText("UPDATE");
            else
                clinicSave_clinic_data.setText("Continue");

//            clinic_head_line.setText(strToolbarName);
            // clinic_peto_edit_reg_number_dialog.setText(pet_unique_id);
            clinicVeterian_name_ET.setText(Config.user_Veterian_name);

            if (visitDate != null) {
                if (visitDate.equals("Update Clinic"))
                    clinicCalenderTextViewVisitDt.setText(visitDate);
                else
                    clinicCalenderTextViewVisitDt.setText(Config.currentDate());
            }

            if (descrisption != null) {
                if (!descrisption.equals(""))
                    clinicCescription_ET.setText(descrisption);
            }

            if (weight != null) {
                if (!weight.equals(""))
                    weight_ET.setText(weight);
            }

            if (temparature != null) {
                if (!temparature.equals(""))
                    clinicTemparature_ET.setText(temparature);
            }

            if (dtOfOnset != null) {
                if (!dtOfOnset.equals(""))
                    clinicIlness_onset.setText(dtOfOnset);
            }

            if (diagnosis != null) {
                if (!diagnosis.equals(""))
                    diagnosis_result.setText(diagnosis);
            }

            if (flowUpDt != null) {
                if (!flowUpDt.equals(""))
                    folow_up_dt_view.setText(flowUpDt);
            }

            if (Remarks != null) {
                if (!Remarks.equals("")) {
                    remaks_ET.setText(Remarks);
                }
            }


        }

        nextDeworming = new ArrayList<>();
        nextDeworming.add("Select Number of Days");
        nextDeworming.add("7 Days");
        nextDeworming.add("10 Days");
        nextDeworming.add("15 Days");
        nextDeworming.add("21 Days");
        nextDeworming.add("30 Days");
        nextDeworming.add("60 Days");


        if (methods.isInternetOn()) {
//            getImmunizationData();
            getClientVisit();
            getImmunizationHistory();
            getVaccinationDetails();
            getVisitTypes();
            petDetails(pet_id);
            searchClinicVisitFieldData();
//            searchDiagnosisData();
//            searchDeormerName();
//            searchDeormerDose();
            setNextDewormerDoseSpinner();
            getNextFirstVaccine();
            DeleteTemporaryVaccination();
        } else {

            methods.DialogInternet();
        }

    }

    private void petDeatilsDialogInit() {
        pet_profile_dialog = new Dialog(this);
        pet_profile_dialog.setContentView(R.layout.pet_deatils_dialog);

        dialog_pet_profile_image_IV =  pet_profile_dialog.findViewById(R.id.dialog_pet_profile_image_IV);
        dialog_pet_name_TV =  pet_profile_dialog.findViewById(R.id.dialog_pet_name_TV);
        dialog_cross_IV =  pet_profile_dialog.findViewById(R.id.dialog_cross_IV);
        dialog_pet_id_TV =  pet_profile_dialog.findViewById(R.id.dialog_pet_reg__id_TV);
        dialog_pet_age_TV =  pet_profile_dialog.findViewById(R.id.dialog_pet_age_TV);
        dialog_pet_gender_TV =  pet_profile_dialog.findViewById(R.id.dialog_pet_gender_TV);
        dialog_pet_parent_name_TV =  pet_profile_dialog.findViewById(R.id.dialog_pet_parent_name_TV);
        dialog_parent_phone_TV =  pet_profile_dialog.findViewById(R.id.dialog_parent_phone_TV);
        dialog_parent_address_TV =  pet_profile_dialog.findViewById(R.id.dialog_parent_address_TV);
        dialog_pet_breed_TV =  pet_profile_dialog.findViewById(R.id.dialog_pet_breed_TV);

        dialog_cross_IV.setOnClickListener(this);
    }

    private void textWatcher() {
        clinicCescription_ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("count", "" + count);
                if (natureOfVisit.equals("Routine/Health Problem")||natureOfVisit.equals("Other")) {
                    if (start == 0 && count == 1 && before == 0) {
                        int progress = horizontal_progress_bar.getProgress();
                        progress = progress + 19;
                        setProgressStatus(progress);
                    } else if (count == 0 && start == 0) {
                        int progress = horizontal_progress_bar.getProgress();
                        progress = progress - 19;
                        setProgressStatus(progress);
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        clinicDiagnosis_ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("count", "" + count);
                if (natureOfVisit.equals("Routine/Health Problem")||natureOfVisit.equals("Other")) {
                    if (start == 0 && count == 1 && before == 0) {
                        int progress = horizontal_progress_bar.getProgress();
                        progress = progress + 19;
                        setProgressStatus(progress);
                    } else if (count == 0 && start == 0) {
                        int progress = horizontal_progress_bar.getProgress();
                        progress = progress - 19;
                        setProgressStatus(progress);
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        clinicTreatment_remarks_MT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("count", "" + count);
                if (natureOfVisit.equals("Routine/Health Problem")||natureOfVisit.equals("Other")) {
                    if (start == 0 && count == 1 && before == 0) {
                        int progress = horizontal_progress_bar.getProgress();
                        progress = progress + 19;
                        setProgressStatus(progress);
                    } else if (count == 0 && start == 0) {
                        int progress = horizontal_progress_bar.getProgress();
                        progress = progress - 19;
                        setProgressStatus(progress);
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        deworming_AC.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("count", "" + count);
                if (natureOfVisit.equals("Deworming")) {
                    if (start == 0 && count == 1 && before == 0) {
                        int progress = horizontal_progress_bar.getProgress();
                        progress = progress + 19;
                        setProgressStatus(progress);
                    } else if (count == 0 && start == 0) {
                        int progress = horizontal_progress_bar.getProgress();
                        progress = progress - 19;
                        setProgressStatus(progress);
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        deworming_dose_AC.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("count", "" + count);
                if (natureOfVisit.equals("Deworming")) {
                    if (start == 0 && count == 1 && before == 0) {
                        int progress = horizontal_progress_bar.getProgress();
                        progress = progress + 19;
                        setProgressStatus(progress);
                    } else if (count == 0 && start == 0) {
                        int progress = horizontal_progress_bar.getProgress();
                        progress = progress - 19;
                        setProgressStatus(progress);
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void searchClinicVisitFieldData() {
        ApiService<SearchDiagnosisResponseResponse> service = new ApiService<>();
        service.get(AddClinicActivity.this, ApiClient.getApiInterface().searchClinicVisitFieldData(Config.token), "SearchClinicVisitFieldData");
    }

    private void petDetails(String pet_id) {
        GetPetListParams getPetListParams = new GetPetListParams();
        getPetListParams.setId(pet_id.substring(0, pet_id.length() - 2));
        GetPetListRequest getPetListRequest = new GetPetListRequest();
        getPetListRequest.setData(getPetListParams);
        ApiService<GetPetResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getPetDetails(Config.token, getPetListRequest), "GetPetDetail");
        Log.e("DATALOG", "GetPetDetailParam=>" + methods.getRequestJson(getPetListRequest));

    }

    private void getTreatmentRemaks(SearchRemaksRequest searchRemaksRequest) {
        ApiService<SearchRemaksResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getSearchTreatmentRemarks(Config.token, searchRemaksRequest), "SearchTreatmentRemarks");
        Log.d("SearchTreatmentRemarks", "parameter" + searchRemaksRequest);
    }

    private void getClientVisit() {
        ApiService<ClinicVisitResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getClinicVisit(Config.token), "GetClinicVisitRoutineFollowupTypes");
    }

    private void getFirstVaccine() {
        GetFirstVaccineModel getFirstVaccineModel = new GetFirstVaccineModel();
        getFirstVaccineModel.setCategoryId(pet_cat_id);
        getFirstVaccineModel.setPetId(pet_id.substring(0, pet_id.length() - 2));
        GetFirstVaccineRequest getFirstVaccineRequest = new GetFirstVaccineRequest();
        getFirstVaccineRequest.setData(getFirstVaccineModel);
        ApiService<GetFirstVaccineResponseData> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getInitialVaccineDetails(Config.token, getFirstVaccineRequest), "GetInitialVaccineDetails");
        Log.e("GetInitialVaccineD", "" + getFirstVaccineRequest);
    }

    private void getNextFirstVaccine() {
        NextVaccineParam nextVaccineParam = new NextVaccineParam();
        nextVaccineParam.setNextVaccinationDate(strNextVisitDate);
        nextVaccineParam.setVaccineName(strVaccineName);
        nextVaccineParam.setVaccineType(strVaccineType);
        nextVaccineParam.setCategoryId(pet_cat_id);
        nextVaccineParam.setPetId(pet_id.substring(0, pet_id.length() - 2));
        NextVaccineRequest nextVaccineRequest = new NextVaccineRequest();
        nextVaccineRequest.setData(nextVaccineParam);
        ApiService<NextVaccineResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getNextVaccinationDateAndName(Config.token, nextVaccineRequest), "GetNextVaccinationDateAndName");
        Log.e("NextFirstVaccine", "" + methods.getRequestJson(nextVaccineRequest));
    }

    private void getVaccinationTypeByVaccineName(String strVaccineName) {
        VaccinationTypeByVaccineNameParams vaccinationTypeByVaccineNameParams = new VaccinationTypeByVaccineNameParams();
        vaccinationTypeByVaccineNameParams.setVaccineName(strVaccineName);
        vaccinationTypeByVaccineNameParams.setPetId(pet_cat_id);
        VaccinationTypeByVaccineNameRequest vaccinationTypeByVaccineNameRequest = new VaccinationTypeByVaccineNameRequest();
        vaccinationTypeByVaccineNameRequest.setData(vaccinationTypeByVaccineNameParams);

        ApiService<VaccinationTypeByVaccineNameResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getVaccinationTypeByVaccineName(Config.token, vaccinationTypeByVaccineNameRequest), "VaccinationTypeByVaccineName");
        Log.e("VaccinationType", "" + methods.getRequestJson(vaccinationTypeByVaccineNameRequest));


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.view_profile_RL:
                showPetProfileDialog();

                break;

            case R.id.dialog_cross_IV:
                pet_profile_dialog.dismiss();
                break;

            case R.id.calenderTextViewVisitDt:
//                 Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                clinicCalenderTextViewVisitDt.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, year, month, day);
//-------------------------------------------------------------------------------------------------------------------

//             picker.onDateChanged(datepicker,year,month,day);

//                final DatePickerDialog datePickerDialog = new DatePickerDialog(this,
//                        new DatePickerDialog.OnDateSetListener() {
//
//                            @Override
//                            public void onDateSet(DatePicker view, int year,
//                                                  int monthOfYear, int dayOfMonth) {
//
//                                datepicker = new DatePicker(getApplicationContext());
//
//                                datepicker.init(year, monthOfYear + 1, dayOfMonth, null);
//
//
//                            }
//                        }, year, month, day);
//                cldr.set(year,month,day);
//                Calendar date=cldr;
//                int Smplemonth= picker.getDatePicker().getDayOfMonth();
//                long checoutdate=cldr.
                Log.d("DAYOFMONTH", "onClick: " + Config.day);


//--------------------------------------------------------------------------------------------------------------
                picker.getDatePicker().setMinDate(cldr.getTimeInMillis());
                picker.show();

//                datePickerDialog.getDatePicker().setMinDate(cldr.getTimeInMillis());
//                datePickerDialog.show();

                break;
            case R.id.ilness_onset:
                final Calendar cldrIll = Calendar.getInstance();
                int dayIll = cldrIll.get(Calendar.DAY_OF_MONTH);
                int monthIll = cldrIll.get(Calendar.MONTH);
                int yearIll = cldrIll.get(Calendar.YEAR);


                // date picker dialog
                picker = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                clinicIlness_onset.setText(Config.changeDateFormat(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year));
                            }
                        }, yearIll, monthIll, dayIll);
                picker.show();
                break;


            case R.id.folow_up_dt_view:
                Log.d("PreviousDate", "onClick: "+cldr.get(6));
//                String prevDate = String.valueOf(clinicCalenderTextViewVisitDt.getText().toString().trim());
//                String mydate=prevDate.substring(0,2);
//                String myMonth=prevDate.substring(3,4);
//                Log.d("TAGDAY", "onClick: "+mydate);
//                Log.d("TAGDAY", "onClick: "+myMonth);
//                Log.d("YEAR", "onClick: "+mydate);
//                int a=Integer.parseInt(mydate);
//                int b=Integer.parseInt(myMonth);
//                final Calendar cldrNext = Calendar.getInstance();
                Calendar minDate=Calendar.getInstance();

                int dayNext = cldr.get(Calendar.DAY_OF_MONTH);
                int monthNext = cldr.get(Calendar.MONTH);
                int yearNext = cldr.get(Calendar.YEAR);

//                int dayNext=cldrNext.get(a);
//                int monthNext=cldrNext.get(b);
//                int yearNext=cldrNext.get(Calendar.YEAR);


//-------------------------------------------------------------------------------------------
//
//                picker = new DatePickerDialog(this,
//                        new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                                folow_up_dt_view.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
//
//                            }
//                        }, yearNext, monthNext, dayNext);
//                --------------------------------------------------------------------------------
                dialogPicker=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                folow_up_dt_view.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, yearNext, monthNext, dayNext);




                dialogPicker.getDatePicker().setMinDate(minDate.get(1));



//                picker.getDatePicker().setMinDate(cldr.getTimeInMillis());
//                  picker.getDatePicker().setMinDate(nd);

//                picker.updateDate(yearNext,b-1,a);
//                cldrNext.set(yearNext,b-1,a);


                picker.show();
                break;
            case R.id.save_clinic_data:
                veterian_name = clinicVeterian_name_ET.getText().toString();
                descrisption = clinicCescription_ET.getText().toString();
                visitDate = clinicCalenderTextViewVisitDt.getText().toString();
                dtOfOnset = clinicIlness_onset.getText().toString();
                flowUpDt = folow_up_dt_view.getText().toString();
                weight = weight_ET.getText().toString();
                temparature = clinicTemparature_ET.getText().toString();
                Remarks = clinicTreatment_remarks_MT.getText().toString();
                diagnosis = clinicDiagnosis_ET.getText().toString();
                strDewormerName = deworming_AC.getText().toString();
                strDewormerDose = deworming_dose_AC.getText().toString();
                history = history_ET.getText().toString();
                nextVaccineName = next_vaccine_ET.getText().toString();
                if (natureOfVisit.equals("Immunization")) {
                    if ((natureOfVisit.equals("Immunization")) && (VaccineList.size() < 1)) {
                        clinicVeterian_name_ET.setError(null);
                        clinicCescription_ET.setError(null);
                        remaks_ET.setError(null);
                        diagnosis_result.setError(null);
                        Dewormer_name_ET.setError(null);
                        Dewormer_ET.setError(null);
                        Toast.makeText(this, "Add Vaccine Type and Name", Toast.LENGTH_LONG).show();
                    } else {
                        vaccinationModels = new ArrayList<HashMap<String, String>>();
                        for (int i = 0; i < VaccineList.size(); i++) {
                            vaccinationationModelHash = new HashMap<>();
                            StringTokenizer st = new StringTokenizer(VaccineList.get(i), ",");
                            String brandType = st.nextToken();
                            String vaccine_name = st.nextToken();
                            String vaccine_date = st.nextToken();
                            vaccinationationModelHash.put("id", String.valueOf(i));
                            vaccinationationModelHash.put("vaccineType", brandType);
                            vaccinationationModelHash.put("vaccine", vaccine_name);
                            vaccinationationModelHash.put("brandName", "null");
                            vaccinationationModelHash.put("vaccineDose", "null");
                            vaccinationationModelHash.put("immunizationDate", vaccine_date.trim());
                            vaccinationModels.add(vaccinationationModelHash);
                        }
                        Gson gson = new GsonBuilder().create();
                        myCustomArray = gson.toJsonTree(vaccinationModels).getAsJsonArray();
                        Log.d("nannananna", "" + myCustomArray);
                        if (methods.isInternetOn()) {
                            addClinicVisit();
                        } else {
                            methods.DialogInternet();
                        }
                    }

                } else if (natureOfVisit.isEmpty() || (natureOfVisit.equals("Select Visit"))) {
                    Toast.makeText(this, "Please Select Nature of Visit", Toast.LENGTH_SHORT).show();
                    return;
                } else if (natureOfVisit.isEmpty() || (natureOfVisit.equals("Routine/Health Problem") || (natureOfVisit.equals("Other")))) {
                    if (diagnosis.isEmpty()) {
                        clinicCescription_ET.setError(null);
                        history_ET.setError(null);
                        Toast.makeText(this, "Enter Diagnosis", Toast.LENGTH_SHORT).show();
                    } else if (Remarks.isEmpty()) {
                        clinicCescription_ET.setError(null);
                        history_ET.setError(null);
                        Toast.makeText(this, "Enter treatment", Toast.LENGTH_SHORT).show();
                    } else {
                        clinicCescription_ET.setError(null);
                        history_ET.setError(null);
                        addClinicVisit();

                    }

                } else if (natureOfVisit.isEmpty() || (natureOfVisit.equals("Deworming"))) {
                    if (strDewormerName.isEmpty()) {
                        clinicCescription_ET.setError(null);
                        history_ET.setError(null);
                        diagnosis_result.setError(null);
                        Toast.makeText(this, "Enter Dewormer Name", Toast.LENGTH_SHORT).show();
                    } else if (strDewormerDose.isEmpty()) {
                        clinicCescription_ET.setError(null);
                        history_ET.setError(null);
                        diagnosis_result.setError(null);
                        Toast.makeText(this, "Enter Dewormer Dose", Toast.LENGTH_SHORT).show();
                    } else if (strNextDewormer.equals("Select Number of Days")) {
                        clinicCescription_ET.setError(null);
                        history_ET.setError(null);
                        diagnosis_result.setError(null);
                        Toast.makeText(this, "Select Number of Days", Toast.LENGTH_SHORT).show();
                    } else {
                        addClinicVisit();
                    }
                } else if (natureOfVisit.isEmpty() || (natureOfVisit.equals("Other"))) {
                    Log.e("errrr", "asdasd");
                    if (diagnosis.isEmpty()) {
                        clinicCescription_ET.setError(null);
                        history_ET.setError(null);
                        diagnosis_result.setError("Enter Diagnosis");
                        remaks_ET.setError(null);
                        return;
                    } else if (remarks.isEmpty()) {
                        clinicCescription_ET.setError(null);
                        history_ET.setError(null);
                        diagnosis_result.setError(null);
                        remaks_ET.setError("Enter treatment");
                        return;
                    } else {
                        clinicCescription_ET.setError(null);
                        history_ET.setError(null);
                        diagnosis_result.setError(null);
                        remaks_ET.setError(null);
                        addClinicVisit();

                    }

                }
                break;
            case R.id.upload_documents:
                showPictureDialog();
                break;
            case R.id.back_arrow_IV:
                onBackPressed();
                break;
            case R.id.add_immunization_data_LL:
                showVaccineDialog();
                break;

        }
    }

    private void addClinicVisit() {
        AddPetClinicParam addPetClinicParam = new AddPetClinicParam();
        addPetClinicParam.setPetId(pet_id);
        addPetClinicParam.setVeterinarian(veterian_name);
        addPetClinicParam.setVisitDate(visitDate);
        addPetClinicParam.setNatureOfVisitId(strNatureOfVist);
        addPetClinicParam.setVaccine(strVacine);
        addPetClinicParam.setDescription(descrisption);
        addPetClinicParam.setWeightLbs(weight);
        addPetClinicParam.setWeightOz(weight);
        addPetClinicParam.setTemperature(temparature);
        addPetClinicParam.setDateOfOnset(dtOfOnset);
        addPetClinicParam.setDewormerName(strDewormerName);
        addPetClinicParam.setTreatmentRemarks(Remarks);
        addPetClinicParam.setDiagnosisProcedure(diagnosis);
        addPetClinicParam.setFollowUpId("");
        addPetClinicParam.setRemarks(remarks);
        addPetClinicParam.setHistory(history);
        if (natureOfVisit.isEmpty() || (natureOfVisit.equals("Deworming"))) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Calendar c = Calendar.getInstance();
            try {
                //Setting the date to the given date
                c.setTime(sdf.parse(folow_up_dt_view.getText().toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //Number of Days to add
            c.add(Calendar.DAY_OF_MONTH, Integer.parseInt(strNextDewormer.substring(0, strNextDewormer.length() - 5)));
            String nextDewormerDate = sdf.format(c.getTime());
            Log.e("DATE", nextDewormerDate);
            addPetClinicParam.setFollowUpDate(nextDewormerDate);
            addPetClinicParam.setDewormerDose(strDewormerDose);

        } else {
            addPetClinicParam.setFollowUpDate(flowUpDt);
        }
        addPetClinicParam.setDocuments(strDocumentUrl);
        addPetClinicParam.setAppointmentId(appointment_ID);
        if (natureOfVisit.isEmpty() || (natureOfVisit.equals("Immunization"))) {
            addPetClinicParam.setVaccinationModels(myCustomArray);
            addPetClinicParam.setNextVaccineName(nextVaccineName);
            addPetClinicParam.setNextVaccinetype(nextVaccineType);
            addPetClinicParam.setFollowUpDate(folow_up_dt_view.getText().toString());
        }
        AddPetClinicRequest addPetClinicRequest = new AddPetClinicRequest();
        addPetClinicRequest.setAddPetParams(addPetClinicParam);
        if (methods.isInternetOn()) {
            methods.showCustomProgressBarDialog(this);
            addPetClinicData(addPetClinicRequest);
        } else {
            methods.DialogInternet();
        }

    }
    private  void showPetProfileDialog(){

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = pet_profile_dialog.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        pet_profile_dialog.setCancelable(true);
        pet_profile_dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        pet_profile_dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        pet_profile_dialog.show();
    }

    private void showVaccineDialog() {

        vaccineDialog = new Dialog(this);
        vaccineDialog.setContentView(R.layout.vaccination_add_layout);

        vaccine_type = (AppCompatSpinner) vaccineDialog.findViewById(R.id.vaccine_type);
        vaccine_name = (AppCompatSpinner) vaccineDialog.findViewById(R.id.vaccine_name);
        nextImmunizationDate = (TextView) vaccineDialog.findViewById(R.id.nextImmunizationDate);
        Button add_vaccine_data = (Button) vaccineDialog.findViewById(R.id.add_vaccine_data);
        Button add_vaccine_cancel = (Button) vaccineDialog.findViewById(R.id.add_vaccine_cancel);

        setVaccineTypeSpinner(getStrVaccineType);
        setVaccineNameSpinner(getStrVaccineName);

        nextImmunizationDate.setText(Config.currentDate());
        nextImmunizationDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                dialogPicker = new DatePickerDialog(AddClinicActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                nextImmunizationDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                dialogPicker.getDatePicker().setMaxDate(System.currentTimeMillis());
                dialogPicker.show();
            }
        });

        add_vaccine_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("vaccinlist_before", "" + VaccineList.size());
                if (strVaccineType.equals("Select Vaccine Type") || strVaccineName.equals("Select Vaccine Name")) {
                    Toast.makeText(AddClinicActivity.this, "Add Vaccine Data", Toast.LENGTH_SHORT).show();
                } else {
                    VaccineList.add(strVaccineType + ", " + strVaccineName + ", " + nextImmunizationDate.getText().toString());
                    Log.e("vaccinlist_after", "" + VaccineList.size());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AddClinicActivity.this);
                    immunization_data.setLayoutManager(linearLayoutManager);
                    immunization_data.setNestedScrollingEnabled(false);
                    if (VaccineList.size() > 0) {
                        if (VaccineList.size()>0){
                                int progress = horizontal_progress_bar.getProgress();
                                progress = progress + 50;
                                setProgressStatus(progress);
                            } else{
                                int progress = horizontal_progress_bar.getProgress();
                                progress = progress - 50;
                                setProgressStatus(progress);
                            }
                        hospitalizationReportsAdapter = new ImmunazationVaccineAdopter(AddClinicActivity.this, AddClinicActivity.this, VaccineList);
                        immunization_data.setAdapter(hospitalizationReportsAdapter);
                        hospitalizationReportsAdapter.notifyDataSetChanged();
                        saveVaccineAfterAdd();
                        vaccineDialog.dismiss();
                    } else {
                        vaccineDialog.dismiss();
                    }
                }


            }
        });

        add_vaccine_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vaccineDialog.dismiss();
            }
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = vaccineDialog.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        vaccineDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        vaccineDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        vaccineDialog.show();
    }

    private void vaccineDetailsDialog() {
        vaccineDetailsDialog = new Dialog(this);
        vaccineDetailsDialog.setContentView(R.layout.vaccine_deatils_dilog);
        CardView age_group_CV = vaccineDetailsDialog.findViewById(R.id.age_group_CV);
        CardView periodic_vaccine_CV = vaccineDetailsDialog.findViewById(R.id.periodic_vaccine_CV);
        CardView history_CV = vaccineDetailsDialog.findViewById(R.id.history_CV);
        final LinearLayout immunization_history_layout = vaccineDetailsDialog.findViewById(R.id.immunization_history_layout);
        final TextView age_group_TV = vaccineDetailsDialog.findViewById(R.id.age_group_TV);
        final TextView periodic_vaccine_TV = vaccineDetailsDialog.findViewById(R.id.periodic_vaccine_TV);
        final TextView history_TV = vaccineDetailsDialog.findViewById(R.id.history_TV);

        ImageView clinic_back_arrow_IV = vaccineDetailsDialog.findViewById(R.id.clinic_back_arrow_IV);
        final RecyclerView vaccine_type_name_list = vaccineDetailsDialog.findViewById(R.id.vaccine_type_name_list);
        final RecyclerView pereodic_list = vaccineDetailsDialog.findViewById(R.id.pereodic_list);
        final RecyclerView immunization_history_list = vaccineDetailsDialog.findViewById(R.id.immunization_history_list);

        pereodic_list.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AddClinicActivity.this);
        vaccine_type_name_list.setLayoutManager(linearLayoutManager);
        vaccine_type_name_list.setNestedScrollingEnabled(false);
        if ((getVaccineResponseModels != null)) {
            if (getVaccineResponseModels.size() > 0) {
                vaccineTypeAdapter = new VaccineTypeAdapter(AddClinicActivity.this, getVaccineResponseModels, AddClinicActivity.this);
                vaccine_type_name_list.setAdapter(vaccineTypeAdapter);
                vaccineTypeAdapter.notifyDataSetChanged();
            }
        }

        //////Set Immunization History List

        LinearLayoutManager linearLayoutManagerone = new LinearLayoutManager(AddClinicActivity.this);
        immunization_history_list.setLayoutManager(linearLayoutManagerone);
        immunization_history_list.setNestedScrollingEnabled(false);

        if (nextVisitDateList != null) {
            if ((nextVisitDateList.size() > 0)) {
                immunizationHistoryAdopter = new ImmunizationHistoryAdopter(AddClinicActivity.this, nextVisitDateList, vaccineClassList, vaccineList, immunizationDateList);
                immunization_history_list.setAdapter(immunizationHistoryAdopter);
                immunizationHistoryAdopter.notifyDataSetChanged();
            }
        }

        clinic_back_arrow_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vaccineDetailsDialog.dismiss();
            }
        });

        age_group_CV.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                age_group_TV.setTextColor(R.color.black_color);
                periodic_vaccine_TV.setTextColor(R.color.grayColorCode);
                history_TV.setTextColor(R.color.grayColorCode);
                vaccine_type_name_list.setVisibility(View.VISIBLE);
                pereodic_list.setVisibility(View.GONE);
                immunization_history_list.setVisibility(View.GONE);
                immunization_history_layout.setVisibility(View.GONE);
            }
        });

        periodic_vaccine_CV.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                age_group_TV.setTextColor(R.color.grayColorCode);
                periodic_vaccine_TV.setTextColor(R.color.black_color);
                history_TV.setTextColor(R.color.grayColorCode);
                vaccine_type_name_list.setVisibility(View.GONE);
                pereodic_list.setVisibility(View.VISIBLE);
                immunization_history_list.setVisibility(View.GONE);
                immunization_history_layout.setVisibility(View.GONE);
            }
        });

        history_CV.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                age_group_TV.setTextColor(R.color.grayColorCode);
                periodic_vaccine_TV.setTextColor(R.color.grayColorCode);
                history_TV.setTextColor(R.color.black_color);
                vaccine_type_name_list.setVisibility(View.GONE);
                pereodic_list.setVisibility(View.GONE);
                immunization_history_list.setVisibility(View.VISIBLE);
                immunization_history_layout.setVisibility(View.VISIBLE);
            }
        });
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

    private void setVaccineTypeSpinner(String type) {
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, vaccineTypeList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        vaccine_type.setAdapter(aa);

        if (type != null) {
            if (!type.equals("null")) {
                if (type != null) {
                    int spinnerPosition = aa.getPosition(type);
                    vaccine_type.setSelection(spinnerPosition);
                }
            }
        }


        vaccine_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                Log.d("vaccineType", "" + item);
                strVaccineType = item;
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setVaccineNextTypeSpinner(String nextType) {
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, nextVaccineTypeList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        next_vaccine_type_spinner.setAdapter(aa);
        if (nextType != null) {
            int spinnerPosition = aa.getPosition(nextType);
            next_vaccine_type_spinner.setSelection(spinnerPosition);
        }
        next_vaccine_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                Log.d("vaccineType", "" + item);
                nextVaccineType = item;
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setVaccineNameSpinner(String name) {
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, vaccineNameList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        vaccine_name.setAdapter(aa);

        if (name != null) {
            if (!name.equals("null")) {
                if (name != null) {
                    int spinnerPosition = aa.getPosition(name);
                    vaccine_name.setSelection(spinnerPosition);
                }
            }
        }

        vaccine_name.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                Log.d("vaccineName", "" + item);
                strVaccineName = item;
                getVaccinationTypeByVaccineName(strVaccineName);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }


    private void showPictureDialog() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_layout);

        RelativeLayout select_camera = (RelativeLayout) dialog.findViewById(R.id.select_camera);
        RelativeLayout select_gallery = (RelativeLayout) dialog.findViewById(R.id.select_gallery);
        RelativeLayout cancel_dialog = (RelativeLayout) dialog.findViewById(R.id.cancel_dialog);
        select_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhotoFromCamera();
            }
        });

        select_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePhotoFromGallary();
            }
        });

        cancel_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void choosePhotoFromGallary() {


        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {

        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);

    }

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        dialog.dismiss();
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {

                Uri contentURI = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), contentURI);
                    document_name.setImageBitmap(bitmap);
                    saveImage(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(AddClinicActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {

            if (data.getData() == null) {
                thumbnail = (Bitmap) data.getExtras().get("data");
                Log.e("jghl", "" + thumbnail);
                document_name.setImageBitmap(thumbnail);
                saveImage(thumbnail);
                Toast.makeText(AddClinicActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(AddClinicActivity.this.getContentResolver(), data.getData());
                    document_name.setImageBitmap(bitmap);
                    saveImage(bitmap);
                    Toast.makeText(AddClinicActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return;
    }

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            file = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".png");
            file.createNewFile();
            FileOutputStream fo = new FileOutputStream(file);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{file.getPath()},
                    new String[]{"image/png"}, null);
            fo.close();
            Log.d("TAG", "File Saved::---&gt;" + file.getAbsolutePath());
            UploadImages(file);
            return file.getAbsolutePath();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    private void UploadImages(File absolutePath) {
        MultipartBody.Part userDpFilePart = null;
        if (absolutePath != null) {
            RequestBody userDpFile = RequestBody.create(MediaType.parse("image/*"), absolutePath);
            userDpFilePart = MultipartBody.Part.createFormData("file", absolutePath.getName(), userDpFile);
        }

        ApiService<ImageResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().uploadImages(Config.token, userDpFilePart), "UploadDocument");
        Log.e("DATALOG", "check1=> " + service);
        methods.showCustomProgressBarDialog(this);
    }

    private void requestMultiplePermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        android.Manifest.permission.CAMERA,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Log.d("Permision", "All permissions are granted by user!");
//                            Toast.makeText(AddClinicActivity.this, "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            //openSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }


                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(AddClinicActivity.this, "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    private void getImmunizationData() {
        methods.showCustomProgressBarDialog(this);
        ImmunizationParameter immunizationParameter = new ImmunizationParameter();
        immunizationParameter.setCategoryId(pet_cat_id);
        immunizationParameter.setPetDOB(pet_DOB);
        immunizationParameter.setEncryptedId(pet_encrypted_id);
        ImmunizationRequestt immunizationRequestt = new ImmunizationRequestt();
        immunizationRequestt.setImmunizationData(immunizationParameter);
        ApiService<ImmunizationVaccineResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getRecommendedVaccine(Config.token, immunizationRequestt), "GetRecommendedVaccine");
        Log.e("IMMU_DATALOG==>", methods.getRequestJson(immunizationRequestt));
    }

    private void getVisitTypes() {
        ApiService<GetReportsTypeResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getReportsType(Config.token), "GetReportsType");
    }

    private void addImmuzationData(ImmunizationClinicData immunizationClinicData) {
        methods.showCustomProgressBarDialog(this);
        ApiService<AddpetClinicResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().addPetVaccination(Config.token, immunizationClinicData), "AddClinicVisit");
        Log.e("DATALOG_AddPetVaccin", methods.getRequestJson(immunizationClinicData));
    }


    private void addPetClinicData(AddPetClinicRequest addPetClinicRequest) {
        ApiService<AddpetClinicResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().addClinicVisit(Config.token, addPetClinicRequest), "AddClinicVisit");
        Log.e("AddClinicData==>", methods.getRequestJson(addPetClinicRequest));
    }

    private void updateClinic(UpdateClinicReportsRequest updateClinicReportsRequest) {
        ApiService<AddpetClinicResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().updateClinicVisit(Config.token, updateClinicReportsRequest), "UpdateClinicVisit");
        Log.d("UpdateClinicData==>", "" + updateClinicReportsRequest);
    }

    private void getclinicVisitsReportDetails() {
        PetClinicVistsDetailsParams petClinicVistsDetailsParams = new PetClinicVistsDetailsParams();
        petClinicVistsDetailsParams.setId(pet_id.substring(0, pet_id.length() - 2));
        PetClinicVisitDetailsRequest petClinicVisitDetailsRequest = new PetClinicVisitDetailsRequest();
        petClinicVisitDetailsRequest.setData(petClinicVistsDetailsParams);
        Log.d("petClinicVisitDetail", methods.getRequestJson(petClinicVisitDetailsRequest));
        ApiService<GetLastPrescriptionResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getLastPrescription(Config.token, petClinicVisitDetailsRequest), "GetPetClinicVisitDetails");
        methods.showCustomProgressBarDialog(this);
    }

    private void getVaccinationDetails() {
        GetVaccinationModelParameter getVaccinationModelParameter = new GetVaccinationModelParameter();
        getVaccinationModelParameter.setEncryptedId(pet_encrypted_id);
        GetVaccinationRequest getVaccinationRequest = new GetVaccinationRequest();
        getVaccinationRequest.setData(getVaccinationModelParameter);
        ApiService<GetVaccineResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getVaccinationScheduleChart(Config.token, getVaccinationRequest), "GetVaccinationScheduleChart");
        Log.e("GetVaccinSchedule==>", "" + methods.getRequestJson(getVaccinationRequest));
    }

    private void getImmunizationHistory() {
        GetVaccinationModelParameter getVaccinationModelParameter = new GetVaccinationModelParameter();
        getVaccinationModelParameter.setEncryptedId(pet_encrypted_id);
        GetVaccinationRequest getVaccinationRequest = new GetVaccinationRequest();
        getVaccinationRequest.setData(getVaccinationModelParameter);
        ApiService<ImmunizationHistoryResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getPetImmunizationHistory(getVaccinationRequest), "GetPetImmunizationHistory");
        Log.e("GetVaccinHistory==>", "" + getVaccinationRequest);
    }


    private void saveVaccineData(VaccinationRequest vaccinationRequest) {
        ApiService<SaveImmunizationResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().saveImmunizationDetails(Config.token, vaccinationRequest), "SaveImmunizationDetails");
        Log.e("SaveVaccinSchedule==>", "" + vaccinationRequest);
    }

    private void saveVaccineAfterAdd() {
        methods.showCustomProgressBarDialog(this);
        SaveVaccineModel saveVaccineModel = new SaveVaccineModel();
        saveVaccineModel.setVaccineName(strVaccineName);
        saveVaccineModel.setVaccineType(strVaccineType);
        saveVaccineModel.setVaccinationDate(Config.currentDate());
        saveVaccineModel.setPetId(pet_id.substring(0, pet_id.length() - 2));
        saveVaccineModel.setPetClinicVisitId("0");
        SaveRequest saveRequest = new SaveRequest();
        saveRequest.setData(saveVaccineModel);
        ApiService<SaveResponseData> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().SaveVaccination(Config.token, saveRequest), "SaveVaccination");
        Log.e("SaveVaccination", methods.getRequestJson(saveRequest));
    }

    private void savePreviousVaccinationDetails() {
        methods.showCustomProgressBarDialog(this);
        SaveVaccineModel saveVaccineModel = new SaveVaccineModel();
        saveVaccineModel.setVaccineName(strVaccineName);
        saveVaccineModel.setVaccineType(strVaccineType);
        saveVaccineModel.setVaccinationDate(Config.currentDate());
        saveVaccineModel.setPetId(pet_id.substring(0, pet_id.length() - 2));
        saveVaccineModel.setPetClinicVisitId("0");
        SaveRequest saveRequest = new SaveRequest();
        saveRequest.setData(saveVaccineModel);
        ApiService<SaveResponseData> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().savePreviousVaccinationDetails(Config.token, saveRequest), "SaveVaccination");
        Log.e("SavePreviousVaccination", "" + methods.getRequestJson(saveRequest));
    }

    private void removeTemporaryVaccine() {
        RemoveParams removeParams = new RemoveParams();
        removeParams.setId(pet_id.substring(0, pet_id.length() - 2));
        RemoveRequest removeRequest = new RemoveRequest();
        removeRequest.setData(removeParams);
        ApiService<JsonObject> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().removeVaccineDetails(Config.token, removeRequest), "RemoveVaccineDetails");
        Log.e("RemoveVaccineDetails", "" + removeRequest);

    }

    private void getImmunization() {
        methods.showCustomProgressBarDialog(this);
        ImmunizationParams immunizationParams = new ImmunizationParams();
        immunizationParams.setEncryptedId(pet_encrypted_id);
        ImmunizationRequest immunizationRequest = new ImmunizationRequest();
        immunizationRequest.setData(immunizationParams);

        ApiService<PetImmunizationRecordResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().viewPetVaccination(Config.token, immunizationRequest), "GetImmunization");
        Log.d("GetImmunization", immunizationRequest.toString());
    }

    private void DeleteTemporaryVaccination() {
        ApiService<JsonObject> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().DeleteTemporaryVaccination(Config.token, pet_id.substring(0, pet_id.length() - 2)), "DeleteTemporaryVaccination");
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onResponse(Response arg0, String key) {
        Log.d("amammammama", "" + key);
        switch (key) {
            case "GetRecommendedVaccine":
                try {
                    methods.customProgressDismiss();
                    Log.d("GetRecommendedVaccine", arg0.body().toString());
                    ImmunizationVaccineResponse immunizationVaccineResponse = (ImmunizationVaccineResponse) arg0.body();
                    int responseCode = Integer.parseInt(immunizationVaccineResponse.getResponse().getResponseCode());
                    vaccineTypeList = new ArrayList<>();
                    vaccineTypeList.add("Select Vaccine Type");
                    nextVaccineTypeList = new ArrayList<>();
                    vaccineNameList = new ArrayList<>();
                    vaccineNameList.add("Select Vaccine Name");
                    if (responseCode == 109) {
                        strNextVisitDate = immunizationVaccineResponse.getData().getNextVisitDate();
                        strPetAge = immunizationVaccineResponse.getData().getAge();
                        if (immunizationVaccineResponse.getData().getVaccineTypeList().size() > 0) {
                            for (int i = 0; i < immunizationVaccineResponse.getData().getVaccineTypeList().size(); i++) {
                                vaccineTypeList.add(immunizationVaccineResponse.getData().getVaccineTypeList().get(i).getValue());
                                nextVaccineTypeList.add(immunizationVaccineResponse.getData().getVaccineTypeList().get(i).getValue());
                            }
                        }


                        if (!immunizationVaccineResponse.getData().getNextVaccination().getNextVaccinationDate().equals("null")) {
                            next_vaccine_ET.setText(immunizationVaccineResponse.getData().getNextVaccination().getVaccineName());
                        } else if (!immunizationVaccineResponse.getData().getNextVaccination().getVaccineName().equals("null")) {
                            next_vaccine_ET.setText(immunizationVaccineResponse.getData().getNextVaccination().getVaccineName());
                        }


                        if (immunizationVaccineResponse.getData().getPrimaryVaccine().size() > 0) {
                            for (int j = 0; j < immunizationVaccineResponse.getData().getPrimaryVaccine().size(); j++) {
                                vaccineNameList.add(immunizationVaccineResponse.getData().getPrimaryVaccine().get(j));

                            }

                        }

                    }
                    showVaccineDialog();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "VaccinationTypeByVaccineName":
                try {
                    VaccinationTypeByVaccineNameResponse vaccinationTypeByVaccineNameResponse = (VaccinationTypeByVaccineNameResponse) arg0.body();
                    vaccineTypeList = new ArrayList<>();
                    vaccine_type.setVisibility(View.VISIBLE);
                    vaccineTypeList.add("Select Vaccine Type");
                    getStrVaccineType = "Select Vaccine Type";
                    if (vaccinationTypeByVaccineNameResponse.getData().size() > 0) {
                        for (int i = 0; i < vaccinationTypeByVaccineNameResponse.getData().size(); i++) {
                            vaccineTypeList.add(vaccinationTypeByVaccineNameResponse.getData().get(i).getVaccineType());
//                            nextVaccineTypeList.add(vaccinationTypeByVaccineNameResponse.getData().get(i).getVaccineCode());
                        }
                    }
                    setVaccineTypeSpinner(getStrVaccineType);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

            case "SearchClinicVisitFieldData":
                try {
                    Log.d("SearchDiagnosis", arg0.body().toString());
                    SearchDiagnosisResponseResponse searchDiagnosisResponseData = (SearchDiagnosisResponseResponse) arg0.body();
                    int responseCode = Integer.parseInt(searchDiagnosisResponseData.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        ArrayList<String> getDewormerArray = new ArrayList<>();
                        ArrayList<String> getDewormerDoseArray = new ArrayList<>();
                        ArrayList<String> getDiagnosisArray = new ArrayList<>();
                        ArrayList<String> getTreammentRemarksArray = new ArrayList<>();

                        for (int i = 0; i < searchDiagnosisResponseData.getData().getDewormer().size(); i++) {
                            getDewormerArray.add(String.valueOf(searchDiagnosisResponseData.getData().getDewormer().get(i)));
                        }
                        for (int i = 0; i < searchDiagnosisResponseData.getData().getDewormerDose().size(); i++) {
                            getDewormerDoseArray.add(String.valueOf(searchDiagnosisResponseData.getData().getDewormerDose().get(i)));
                        }
                        for (int i = 0; i < searchDiagnosisResponseData.getData().getDiagnosis().size(); i++) {
                            getDiagnosisArray.add(String.valueOf(searchDiagnosisResponseData.getData().getDiagnosis().get(i)));
                        }
                        for (int i = 0; i < searchDiagnosisResponseData.getData().getTreammentRemarks().size(); i++) {
                            getTreammentRemarksArray.add(String.valueOf(searchDiagnosisResponseData.getData().getTreammentRemarks().get(i)));
                        }

                        //getDewormer
                        ArrayAdapter<String> getDewormerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getDewormerArray);
                        deworming_AC.setAdapter(getDewormerArrayAdapter);
                        deworming_AC.setThreshold(1);
                        deworming_AC.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
                        getDewormerArrayAdapter.notifyDataSetChanged();

                        //getDewormerDose
                        ArrayAdapter<String> getDewormerDoseArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getDewormerDoseArray);
                        deworming_dose_AC.setAdapter(getDewormerDoseArrayAdapter);
                        deworming_dose_AC.setThreshold(1);
                        deworming_dose_AC.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
                        getDewormerDoseArrayAdapter.notifyDataSetChanged();

                        //getDiagnosis
                        ArrayAdapter<String> getDiagnosisArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getDiagnosisArray);
                        clinicDiagnosis_ET.setAdapter(getDiagnosisArrayAdapter);
                        clinicDiagnosis_ET.setThreshold(1);
                        clinicDiagnosis_ET.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
                        getDiagnosisArrayAdapter.notifyDataSetChanged();

                        //getTreammentRemarks
                        ArrayAdapter<String> getTreammentRemarksArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getTreammentRemarksArray);
                        clinicTreatment_remarks_MT.setAdapter(getTreammentRemarksArrayAdapter);
                        clinicTreatment_remarks_MT.setThreshold(1);
                        clinicTreatment_remarks_MT.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
                        getTreammentRemarksArrayAdapter.notifyDataSetChanged();


                    } else if (responseCode == 614) {
                        Toast.makeText(this, searchDiagnosisResponseData.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
//                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                        Log.e("ErrorSerach", "Plese Try Again !!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "GetPetDetail":
                try {
                    Log.d("GetPetDetail", arg0.body().toString());
                    GetPetResponse getPetResponse = (GetPetResponse) arg0.body();
                    int responseCode = Integer.parseInt(getPetResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        view_profile_RL.setOnClickListener(this);
                        pet_name = getPetResponse.getData().getPetName();
                        dialog_pet_id_TV.setText(getPetResponse.getData().getPetUniqueId());
                        dialog_pet_gender_TV.setText(getPetResponse.getData().getPetSex());
                        dialog_pet_age_TV.setText(getPetResponse.getData().getPetAge().substring(6));
                        dialog_pet_breed_TV.setText(getPetResponse.getData().getPetBreed());
//                        pet_typeTV.setText(getPetResponse.getData().getPetCategory());
                        dialog_pet_parent_name_TV.setText(getPetResponse.getData().getPetParentName());
                        dialog_parent_phone_TV.setText(getPetResponse.getData().getContactNumber());
                        dialog_parent_address_TV.setText(getPetResponse.getData().getAddress());

                        Glide.with(this)
                                .load(getPetResponse.getData().getPetProfileImageUrl())
                                .placeholder(R.drawable.dummy_dog_image)
                                .into(dialog_pet_profile_image_IV);

                    } else if (responseCode == 614) {
                        Toast.makeText(this, getPetResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "GetClinicVisitRoutineFollowupTypes":
                try {
                    ClinicVisitResponse clinicVisitResponse = (ClinicVisitResponse) arg0.body();
                    Log.d("GetClinicVisit", clinicVisitResponse.toString());
                    int responseCode = Integer.parseInt(clinicVisitResponse.getResponse().getResponseCode());

                    if (responseCode == 109) {
                        //Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                        nextVisitList = new ArrayList<>();
                        nextVisitList.add("Select Visit");
                        for (int i = 0; i < clinicVisitResponse.getData().size(); i++) {
                            nextVisitList.add(clinicVisitResponse.getData().get(i).getFollowUpTitle());
                            nextVisitHas.put(clinicVisitResponse.getData().get(i).getFollowUpTitle(), clinicVisitResponse.getData().get(i).getId());
                        }
                        setSpinnerNextClinicVisit();
                    } else if (responseCode == 614) {
                        Toast.makeText(this, clinicVisitResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "UploadDocument":
                try {
                    Log.d("UploadDocument", arg0.body().toString());
                    ImageResponse imageResponse = (ImageResponse) arg0.body();
                    int responseCode = Integer.parseInt(imageResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        //Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                        strDocumentUrl = imageResponse.getData().getDocumentUrl();
                        Toast.makeText(this, "Upload Successfully", Toast.LENGTH_SHORT).show();
                        methods.customProgressDismiss();
                    } else if (responseCode == 614) {
                        Toast.makeText(this, imageResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                        methods.customProgressDismiss();
                    } else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                        methods.customProgressDismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "AddClinicVisit":
                try {
                    methods.customProgressDismiss();
                    AddpetClinicResponse addpetClinicResponse = (AddpetClinicResponse) arg0.body();
                    Log.d("AddClinicVisit", addpetClinicResponse.toString());
                    int responseCode = Integer.parseInt(addpetClinicResponse.getResponse().getResponseCode());

                    if (responseCode == 109) {
                        Intent intent = new Intent();
                        intent.putExtra("encryptId", addpetClinicResponse.getData().getEncryptedId());
                        setResult(RESULT_OK, intent);
                        finish();
                        Toast.makeText(this, "Report Added Successfully", Toast.LENGTH_SHORT).show();
                    } else if (responseCode == 614) {
                        Toast.makeText(this, addpetClinicResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else if (responseCode == 652) {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "AddPetVaccination":
                try {
                    methods.customProgressDismiss();

                    JsonObject immunizationAddResponse = (JsonObject) arg0.body();
                    Log.d("AddPetVaccination", immunizationAddResponse.toString());

                    JsonObject response = immunizationAddResponse.getAsJsonObject("response");
                    JsonObject data = immunizationAddResponse.getAsJsonObject("data");

                    Log.d("hhshshhs", "" + response);

                    int responseCode = Integer.parseInt(String.valueOf(response.get("responseCode")));

                    if (responseCode == 109) {
                        Intent intent = new Intent();
                        intent.putExtra("encryptId", String.valueOf(data.get("encryptedId")));
                        setResult(RESULT_OK, intent);
                        finish();
                        Toast.makeText(this, "Report Added Successfully", Toast.LENGTH_SHORT).show();
                    } else if (responseCode == 614) {
                        Toast.makeText(this, String.valueOf(response.getAsJsonObject("responseMessage")), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "GetImmunization":
                try {
                    Log.d("GetImmunization", arg0.body().toString());
                    PetImmunizationRecordResponse immunizationRecordResponse = (PetImmunizationRecordResponse) arg0.body();
//                    methods.customProgressDismiss();
                    int responseCode = Integer.parseInt(immunizationRecordResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        if (immunizationRecordResponse.getData().getPetImmunizationDetailModels().isEmpty()) {
                            // methods.customProgressDismiss();
                            Toast.makeText(this, "No Record Found !", Toast.LENGTH_SHORT).show();
                        } else {
                            ArrayList<String> immunizationDate = new ArrayList<>();
                            ArrayList<String> vaccineClass = new ArrayList<>();
                            ArrayList<String> nextDueDate = new ArrayList<>();
                            ArrayList<String> vaccineType = new ArrayList<>();

                            ArrayList<String> immunizationDatePending = new ArrayList<>();
                            ArrayList<String> vaccineClassPending = new ArrayList<>();
                            ArrayList<String> nextDueDatePending = new ArrayList<>();
                            ArrayList<String> vaccineTypePending = new ArrayList<>();

                            for (int i = 0; i < immunizationRecordResponse.getData().getPetPendingVaccinations().size(); i++) {
                                if (immunizationRecordResponse.getData().getPetPendingVaccinations().get(i).getIsVaccinated().equals("true")) {
                                    immunizationDate.add(immunizationRecordResponse.getData().getPetPendingVaccinations().get(i).getVaccinationDate());
                                    vaccineClass.add(immunizationRecordResponse.getData().getPetPendingVaccinations().get(i).getVaccineName());
                                    nextDueDate.add(immunizationRecordResponse.getData().getPetPendingVaccinations().get(i).getNextVaccinationDate().substring(0, immunizationRecordResponse.getData().getPetPendingVaccinations().get(i).getNextVaccinationDate().length() - 9));
                                    vaccineType.add(immunizationRecordResponse.getData().getPetPendingVaccinations().get(i).getVaccineType());

                                } else {
                                    immunizationDatePending.add(immunizationRecordResponse.getData().getPetPendingVaccinations().get(i).getVaccinationDate());
                                    vaccineClassPending.add(immunizationRecordResponse.getData().getPetPendingVaccinations().get(i).getVaccineName());
                                    nextDueDatePending.add(immunizationRecordResponse.getData().getPetPendingVaccinations().get(i).getNextVaccinationDate().substring(0, immunizationRecordResponse.getData().getPetPendingVaccinations().get(i).getNextVaccinationDate().length() - 9));
                                    vaccineTypePending.add(immunizationRecordResponse.getData().getPetPendingVaccinations().get(i).getVaccineType());

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
                            String immunizationSet = methods.immunizationPdfGenarator(pet_name, pet_age, pet_sex, pet_owner_name, Config.user_verterian_reg_no, vType, vaccine, nextDate, vTypePending, vaccinePending, nextDatePending);
                            WebSettings webSettings = webview.getSettings();
                            webSettings.setJavaScriptEnabled(true);
                            webview.loadDataWithBaseURL(null, immunizationSet, "text/html", "utf-8", null);
                            new Handler().postDelayed(new Runnable() {
                                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                                @Override
                                public void run() {
                                    Context context = AddClinicActivity.this;
                                    PrintManager printManager = (PrintManager) AddClinicActivity.this.getSystemService(context.PRINT_SERVICE);
                                    PrintDocumentAdapter adapter = null;
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                        adapter = webview.createPrintDocumentAdapter();
                                    }
                                    String JobName = getString(R.string.app_name) + "Document";
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                        PrintJob printJob = printManager.print(JobName, adapter, new PrintAttributes.Builder().build());
                                    }
                                }
                            }, 3000);

                        }

                    } else if (responseCode == 614) {
                        Toast.makeText(this, immunizationRecordResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "GetReportsType":
                try {
                    Log.d("GetPetServiceTypes", arg0.body().toString());
                    GetReportsTypeResponse petServiceResponse = (GetReportsTypeResponse) arg0.body();
                    int responseCode = Integer.parseInt(petServiceResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        natureOfVisitList = new ArrayList<>();
                        natureOfVisitList.add("Select Visit");
                        for (int i = 0; i < petServiceResponse.getData().size(); i++) {
                            natureOfVisitList.add(petServiceResponse.getData().get(i).getNature());
                            natureOfVisitHashMap.put(petServiceResponse.getData().get(i).getNature(), petServiceResponse.getData().get(i).getId());
                        }
                        setSpinnerNatureofVisit();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "UpdateClinicVisit":
                try {
                    methods.customProgressDismiss();
                    AddpetClinicResponse addpetClinicResponse = (AddpetClinicResponse) arg0.body();
                    Log.d("UpdateClinicVisit", addpetClinicResponse.toString());
                    int responseCode = Integer.parseInt(addpetClinicResponse.getResponse().getResponseCode());

                    if (responseCode == 109) {
                        Toast.makeText(this, "Update Data Successfully", Toast.LENGTH_SHORT).show();
                        Config.type = "list";
                        getclinicVisitsReportDetails();
                    } else if (responseCode == 614) {
                        Toast.makeText(this, addpetClinicResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "GetPetClinicVisitDetails":
                try {
                    Log.d("ResponseClinicVisit", arg0.body().toString());
                    GetLastPrescriptionResponse getClinicVisitsDetailsResponse = (GetLastPrescriptionResponse) arg0.body();
                    int responseCode = Integer.parseInt(getClinicVisitsDetailsResponse.getResponse().getResponseCode());
                    Log.d("ajajjaja", "" + responseCode);
                    Config.type = "list";
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
                            backPressVal = 1;
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    methods.customProgressDismiss();
                                    Context context = AddClinicActivity.this;
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
                            backPressVal = 1;
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    methods.customProgressDismiss();
                                    Context context = AddClinicActivity.this;
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
                            String immunizationSet = methods.immunizationPdfGenarator(getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getPetName(), getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getPetAge(), getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getPetSex(), getClinicVisitsDetailsResponse.getData().getPetClinicVisitDetails().getPetParentName(), Config.user_verterian_reg_no, vType, vaccine, nextDate, vTypePending, vaccinePending, nextDatePending);
                            WebSettings webSettings = webview.getSettings();
                            webSettings.setJavaScriptEnabled(true);
                            webview.loadDataWithBaseURL(null, immunizationSet, "text/html", "utf-8", null);
                            new Handler().postDelayed(new Runnable() {
                                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                                @Override
                                public void run() {
                                    Context context = AddClinicActivity.this;
                                    PrintManager printManager = (PrintManager) AddClinicActivity.this.getSystemService(context.PRINT_SERVICE);
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

                    } else {
                        methods.customProgressDismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;


            case "GetVaccinationScheduleChart":
                try {
                    Log.d("GetVaccinationSchedul", arg0.body().toString());
                    GetVaccineResponse getVaccineResponse = (GetVaccineResponse) arg0.body();
                    int responseCode = Integer.parseInt(getVaccineResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        Log.e("amamamammamamam", "Vaccine Detailsss" + getVaccineResponse.getData().size());
                        getVaccineResponseModels = getVaccineResponse.getData();
                    } else {

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
            case "GetInitialVaccineDetails":
                try {
                    Log.e("GetInitialVaccineD", arg0.body().toString());
                    GetFirstVaccineResponseData getFirstVaccineResponseData = (GetFirstVaccineResponseData) arg0.body();
                    int responseCode = Integer.parseInt(getFirstVaccineResponseData.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        getStrVaccineType = getFirstVaccineResponseData.getData().getVaccineType();
                        getStrVaccineName = getFirstVaccineResponseData.getData().getVaccineName();
                        setVaccineNextTypeSpinner(getStrVaccineType);
                        Log.e("kakkakka", "getStr" + getStrVaccineType + " " + getStrVaccineName);

                        if ((getStrVaccineName == null) || (getStrVaccineType == null)) {
                            add_immunization_data_added.setVisibility(View.VISIBLE);
                            add_immunization_data.setVisibility(View.INVISIBLE);
                            add_immunization_data_LL.setVisibility(View.INVISIBLE);
//                            imaunizatioHeader.setVisibility(View.GONE);
//                            immunizationView.setVisibility(View.GONE);
                            immunization_data.setVisibility(View.GONE);
                        } else {
                            add_immunization_data_added.setVisibility(View.GONE);
                            add_immunization_data.setVisibility(View.VISIBLE);
                            add_immunization_data_LL.setVisibility(View.VISIBLE);
//                            imaunizatioHeader.setVisibility(View.VISIBLE);
//                            immunizationView.setVisibility(View.VISIBLE);
                            immunization_data.setVisibility(View.VISIBLE);
                            next_vaccine_ET.setText(getStrVaccineName);
                        }
                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "GetNextVaccinationDateAndName":
                try {
                    Log.d("GetNextVaccination", arg0.body().toString());
                    NextVaccineResponse nextVaccineResponse = (NextVaccineResponse) arg0.body();
                    int responseCode = Integer.parseInt(nextVaccineResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        next_vaccine_ET.setEnabled(false);
//                        Log.e("folloupdate", nextVaccineResponse.getData().getNextDate());
                        folow_up_dt_view.setText(nextVaccineResponse.getData().getNextDate());
                        next_vaccine_ET.setText(nextVaccineResponse.getData().getVaccineName());
                        setVaccineNextTypeSpinner(nextVaccineResponse.getData().getVaccineType());
                        Log.d("vaccine type", nextVaccineResponse.getData().getVaccineType());

                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "SaveVaccination":
                try {
                    methods.customProgressDismiss();
                    SaveResponseData saveResponseData = (SaveResponseData) arg0.body();
                    Log.e("SaveResponseData", methods.getRequestJson(saveResponseData));
                    int responseCode = Integer.parseInt(saveResponseData.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        next_vaccine_ET.setEnabled(false);
                        next_dewormer_spinner.setEnabled(false);
                        next_dewormer_spinner.setClickable(false);
                        Log.e("folloupdate", saveResponseData.getData().getNextVaccineDate());
                        folow_up_dt_view.setText(saveResponseData.getData().getNextVaccineDate());
                        next_vaccine_ET.setText(saveResponseData.getData().getNextVaccineName());
                        setVaccineNextTypeSpinner(saveResponseData.getData().getVaccineType());
//                        getNextFirstVaccine();
//                        getFirstVaccine();
                    } else if (responseCode == 115) {
                        alertDialogForVaccineAdd(saveResponseData.getData().getErrorMessage());
                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "RemoveVaccineDetails":
                try {
                    Log.d("RemoveVaccineRespo", arg0.body().toString());
                    JsonObject removeResponse = (JsonObject) arg0.body();
                    JsonObject response = removeResponse.getAsJsonObject("response");
                    Log.d("hhshshhs", "" + response);
                    int responseCode = Integer.parseInt(String.valueOf(response.get("responseCode")));
                    if (responseCode == 109) {
                        Toast.makeText(this, "Removed!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "DeleteTemporaryVaccination":
                try {
                    Log.d("DeleteTemporary", arg0.body().toString());
                    JsonObject deleteData = (JsonObject) arg0.body();
                    JsonObject response = deleteData.getAsJsonObject("response");
                    Log.d("hhshshhs", "" + response);

                    int responseCode = Integer.parseInt(String.valueOf(response.get("responseCode")));

                    if (responseCode == 109) {

                    } else if (responseCode == 614) {
                        methods.customProgressDismiss();
                        Toast.makeText(this, String.valueOf(response.getAsJsonObject("responseMessage")), Toast.LENGTH_SHORT).show();
                    } else {
                        methods.customProgressDismiss();
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
        methods.customProgressDismiss();
        Log.e("ERROR", t.getLocalizedMessage());

    }

    private void alertDialogForVaccineAdd(String errorMsg) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Do you want to continue?");
        alertDialog.setMessage(errorMsg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        savePreviousVaccinationDetails();
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
    }

    private void setNextDewormerDoseSpinner() {
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, nextDeworming);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        next_dewormer_spinner.setAdapter(aa);
        next_dewormer_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                Log.d("dewormerDose", "" + item);
                strNextDewormer = item;
                if (strNextDewormer.equals("Select Number of Days")) {
                    if (nextDewormerSelected==true){
                        int progress = horizontal_progress_bar.getProgress();
                        progress = progress - 19;
                        setProgressStatus(progress);
                    }
                    nextDewormerSelected = false;
                } else {
                    if (nextDewormerSelected==false) {
                        int progress = horizontal_progress_bar.getProgress();
                        progress = progress + 19;
                        setProgressStatus(progress);
                        nextDewormerSelected = true;
                    }
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setSpinnerNextClinicVisit() {
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, nextVisitList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        clinicNext_visit_spinner.setAdapter(aa);
        clinicNext_visit_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                Log.d("spnerType", "" + item);
                visitIdString = item;
                visitId = nextVisitHas.get(item);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setSpinnerNatureofVisit() {
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, natureOfVisitList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        clinicNature_of_visit_spinner.setAdapter(aa);
        if (!strNatureOfVist.equals("")) {
            int spinnerPosition = aa.getPosition(strNatureOfVist);
            clinicNature_of_visit_spinner.setSelection(spinnerPosition);
        }
        clinicNature_of_visit_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                natureOfVisit = item;
                strNatureOfVist = natureOfVisitHashMap.get(natureOfVisit);

                if (natureOfVisit.equals("Select Visit")) {
                    setProgressStatus(2);
                    clinicCescription_ET.getText().clear();
                    history_ET.getText().clear();
                    weight_ET.getText().clear();
                    clinicTemparature_ET.getText().clear();
                    clinicDiagnosis_ET.getText().clear();
                    clinicTreatment_remarks_MT.getText().clear();
                    deworming_AC.getText().clear();
                    deworming_dose_AC.getText().clear();
                } else {
                    setProgressStatus(48);
                }

                if (natureOfVisit.equals("Immunization")) {
                    getImmunizationData();
//                    showVaccineDialog();
                    getFirstVaccine();
                    folow_up_dt_view.setEnabled(false);
                    next_vaccine_type_spinner.setEnabled(false);
                    next_vaccine_type_TV.setEnabled(false);
                    date_of_illness_TV.setVisibility(View.GONE);
                    clinicIlness_onset.setVisibility(View.GONE);
                    history_TV.setVisibility(View.GONE);
                    history_ET.setVisibility(View.GONE);
                    description_TV.setVisibility(View.GONE);
                    clinicCescription_ET.setVisibility(View.GONE);
                    diagnosis_TV.setVisibility(View.GONE);
                    diagnosis_Layout.setVisibility(View.GONE);
                    treatment_remaks_TV.setVisibility(View.GONE);
                    treatment_remarks_LL.setVisibility(View.GONE);
                    clinicNext_visit_spinner.setVisibility(View.GONE);
                    deworming_name_Layout.setVisibility(View.GONE);
                    deworming_dose_Layout.setVisibility(View.GONE);
                    next_visit.setVisibility(View.GONE);
                    Dewormer_TV.setVisibility(View.GONE);
                    Dewormer_name_TV.setVisibility(View.GONE);
                    next_dewormer_TV.setVisibility(View.GONE);
                    next_dewormer_spinner.setVisibility(View.GONE);
                    weight_TV.setVisibility(View.VISIBLE);
                    weight_ET.setVisibility(View.VISIBLE);
                    temparature_TV.setVisibility(View.VISIBLE);
                    clinicTemparature_ET.setVisibility(View.VISIBLE);
//                    pet_age_TV.setVisibility(View.VISIBLE);
                    vaccine_layout.setVisibility(View.VISIBLE);
                    next_vaccine_TV.setVisibility(View.GONE);
                    next_vaccine_type_TV.setVisibility(View.GONE);
                    next_vaccine_ET.setVisibility(View.GONE);
                    next_vaccine_type_spinner.setVisibility(View.GONE);
                    follow_up_dt.setVisibility(View.GONE);
                    folow_up_dt_view.setVisibility(View.GONE);
                    if (strToolbarName.equals("Update Clinic")) {
                        methods.showCustomProgressBarDialog(AddClinicActivity.this);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                methods.customProgressDismiss();
//                                pet_age_TV.setText("AGE:- " + strPetAge + " DAYS");
//                                vaccineDetailsDialog();
                            }
                        }, 3000);
                    } else {
//                        pet_age_TV.setText("AGE:- " + strPetAge + " DAYS");
//                        vaccineDetailsDialog();
                    }


                } else if (natureOfVisit.equals("Deworming")) {
                    description_TV.setText("Symptoms");
                    diagnosis_TV.setVisibility(View.GONE);
                    diagnosis_Layout.setVisibility(View.GONE);
                    treatment_remaks_TV.setVisibility(View.GONE);
                    treatment_remarks_LL.setVisibility(View.GONE);
                    date_of_illness_TV.setVisibility(View.GONE);
                    clinicIlness_onset.setVisibility(View.GONE);
//                    pet_age_TV.setVisibility(View.GONE);
                    next_vaccine_TV.setVisibility(View.GONE);
                    next_vaccine_type_TV.setVisibility(View.GONE);
                    next_vaccine_ET.setVisibility(View.GONE);
                    next_vaccine_type_spinner.setVisibility(View.GONE);
                    vaccine_layout.setVisibility(View.GONE);
                    treatment_remaks_TV.setVisibility(View.GONE);
                    treatment_remarks_LL.setVisibility(View.GONE);
                    folow_up_dt_view.setVisibility(View.GONE);
                    follow_up_dt.setVisibility(View.GONE);
                    history_TV.setVisibility(View.VISIBLE);
                    history_ET.setVisibility(View.VISIBLE);
                    Dewormer_TV.setVisibility(View.VISIBLE);
                    deworming_name_Layout.setVisibility(View.VISIBLE);
                    Dewormer_name_TV.setVisibility(View.VISIBLE);
                    deworming_dose_Layout.setVisibility(View.VISIBLE);
                    next_dewormer_TV.setVisibility(View.VISIBLE);
                    next_dewormer_spinner.setVisibility(View.VISIBLE);

                } else {
                    description_TV.setText("Symptoms*");
                    folow_up_dt_view.setEnabled(true);
                    treatment_remaks_TV.setVisibility(View.VISIBLE);
                    treatment_remarks_LL.setVisibility(View.VISIBLE);
                    date_of_illness_TV.setVisibility(View.VISIBLE);
                    clinicIlness_onset.setVisibility(View.VISIBLE);
                    date_of_illness_TV.setVisibility(View.VISIBLE);
                    clinicIlness_onset.setVisibility(View.VISIBLE);
                    folow_up_dt_view.setVisibility(View.VISIBLE);
                    description_TV.setVisibility(View.VISIBLE);
                    clinicCescription_ET.setVisibility(View.VISIBLE);
                    weight_TV.setVisibility(View.VISIBLE);
                    weight_ET.setVisibility(View.VISIBLE);
                    temparature_TV.setVisibility(View.VISIBLE);
                    clinicTemparature_ET.setVisibility(View.VISIBLE);
                    diagnosis_TV.setVisibility(View.VISIBLE);
                    diagnosis_Layout.setVisibility(View.VISIBLE);
                    treatment_remaks_TV.setVisibility(View.VISIBLE);
                    treatment_remarks_LL.setVisibility(View.VISIBLE);
                    follow_up_dt.setVisibility(View.VISIBLE);
                    history_TV.setVisibility(View.VISIBLE);
                    history_ET.setVisibility(View.VISIBLE);
                    vaccine_layout.setVisibility(View.GONE);
//                    pet_age_TV.setVisibility(View.GONE);
                    Dewormer_TV.setVisibility(View.GONE);
                    deworming_name_Layout.setVisibility(View.GONE);
                    Dewormer_name_TV.setVisibility(View.GONE);
                    deworming_dose_Layout.setVisibility(View.GONE);
                    next_vaccine_TV.setVisibility(View.GONE);
                    next_vaccine_type_TV.setVisibility(View.GONE);
                    next_vaccine_ET.setVisibility(View.GONE);
                    next_vaccine_type_spinner.setVisibility(View.GONE);
                    next_dewormer_TV.setVisibility(View.GONE);
                    next_dewormer_spinner.setVisibility(View.GONE);
                }
                Log.d("Spinner", "" + item);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setProgressStatus(int status) {

        horizontal_progress_bar.setProgress(status);
    }

    private AdapterView.OnItemClickListener onItemClickListener =
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(AddClinicActivity.this,
                            "Clicked item from auto completion list "
                                    + adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
                    if (clinicTreatment_remarks_MT.getText().toString().isEmpty()) {

                    } else {
                        String val = String.valueOf(adapterView.getItemAtPosition(i));

                        if (cocatVal == null)
                            cocatVal = val;
                        else
                            cocatVal = cocatVal + "," + val;
                        remaks_ET.setText(cocatVal);
                        clearSearch();
                    }
                }
            };

    private void clearSearch() {
        clinicTreatment_remarks_MT.getText().clear();
        InputMethodManager imm1 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm1.hideSoftInputFromWindow(clinicTreatment_remarks_MT.getWindowToken(), 0);
    }

    private void clearSearchDiognosis() {
        clinicDiagnosis_ET.getText().clear();
        InputMethodManager imm1 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm1.hideSoftInputFromWindow(clinicDiagnosis_ET.getWindowToken(), 0);
    }

    private void clearDewormerName() {
        deworming_AC.getText().clear();
        InputMethodManager imm1 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm1.hideSoftInputFromWindow(deworming_AC.getWindowToken(), 0);
    }

    private void clearDewormerDose() {
        deworming_dose_AC.getText().clear();
        InputMethodManager imm1 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm1.hideSoftInputFromWindow(deworming_dose_AC.getWindowToken(), 0);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (backPressVal == 1)
            onBackPressed();
    }

    @Override
    public void onItemClick(int position) {
        VaccineList.remove(position);
        Log.e("vaccinlist_after", "" + VaccineList.size());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AddClinicActivity.this);
        immunization_data.setLayoutManager(linearLayoutManager);
        immunization_data.setNestedScrollingEnabled(false);
        hospitalizationReportsAdapter = new ImmunazationVaccineAdopter(AddClinicActivity.this, AddClinicActivity.this, VaccineList);
        immunization_data.setAdapter(hospitalizationReportsAdapter);
        hospitalizationReportsAdapter.notifyDataSetChanged();
        removeTemporaryVaccine();
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}