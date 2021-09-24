package com.cynoteck.petofyOPHR.activities;

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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.fragments.NewEntrysListFragment;
import com.cynoteck.petofyOPHR.fragments.ReportListFragment;
import com.cynoteck.petofyOPHR.params.immunizationRequest.ImmunizationParams;
import com.cynoteck.petofyOPHR.params.immunizationRequest.ImmunizationRequest;
import com.cynoteck.petofyOPHR.response.getImmunizationReport.PetImmunizationRecordResponse;
import com.cynoteck.petofyOPHR.response.loginRegisterResponse.UserPermissionMasterList;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Response;

public class NewEntrysDetailsActivity extends AppCompatActivity implements View.OnClickListener, ApiResponse {
    ImageView petRegImage_IV;
    String pet_unique_id, pet_name,pet_sex,pet_age,pet_DOB,pet_owner_name,pet_owner_contact,pet_id ,
            report_type_id,button_text,button_type,pet_encrypted_id,pet_cat_id="",lastVisitEncryptedId="",pet_image_url;
    Bundle data = new Bundle();
    TextView pet_reg_name_TV,pet_sex_TV,parent_name_TV,pet_owner_phone_no_TV,
            reports_headline_TV,pet_reg__id_TV,pet_reg_date_of_birth_TV;
    SharedPreferences sharedPreferences;
    Methods methods;
    int ADD_CLINIC_VISIT = 1;
    RelativeLayout back_arrow_RL;

    ConstraintLayout add_clinic_visit_CL, test_x_ray_CL, hospitalization_CL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entrys_details);
        sharedPreferences = getSharedPreferences("userdetails", 0);
        methods = new Methods(this);

        Bundle extras = getIntent().getExtras();
        report_type_id = extras.getString("reports_id");
        pet_id = extras.getString("pet_id");
        pet_owner_contact = extras.getString("pet_owner_contact");
        pet_owner_name = extras.getString("pet_parent");
        pet_sex = extras.getString("pet_sex");
        pet_age = extras.getString(pet_age);
        pet_name = extras.getString("pet_name");
        button_type=extras.getString("button_type");
        pet_DOB=extras.getString("pet_DOB");
        pet_encrypted_id=extras.getString("pet_encrypted_id");
        pet_cat_id = extras.getString("pet_cat_id");
        pet_image_url = extras.getString("pet_image_url");
        pet_unique_id = extras.getString("pet_unique_id");
        button_text = extras.getString("add_button_text");
        Log.e("PET_DETAILS",""+pet_DOB+" "+pet_encrypted_id+" "+"cat_id=>"+pet_cat_id+" "+pet_name+" "+pet_owner_name+" "+pet_image_url+" "+pet_sex+" "+pet_age+" "+pet_unique_id);


        reports_headline_TV = findViewById(R.id.reports_headline_TV);
        back_arrow_RL =findViewById(R.id.back_arrow_RL);
        pet_reg_name_TV = findViewById(R.id.pet_reg_name_TV);
        pet_sex_TV = findViewById(R.id.pet_sex_TV);
        pet_reg__id_TV = findViewById(R.id.pet_reg__id_TV);
        parent_name_TV = findViewById(R.id.parent_name_TV);
        add_clinic_visit_CL = findViewById(R.id.add_clinic_visit_CL);
        test_x_ray_CL=findViewById(R.id.test_x_ray_CL);
        hospitalization_CL=findViewById(R.id.hospitalization_CL);
        pet_reg__id_TV=findViewById(R.id.pet_reg__id_TV);
        pet_reg_date_of_birth_TV=findViewById(R.id.pet_reg_date_of_birth_TV);
        petRegImage_IV=findViewById(R.id.petRegImage_IV);


        test_x_ray_CL.setOnClickListener(this);
        hospitalization_CL.setOnClickListener(this);
        add_clinic_visit_CL.setOnClickListener(this);
        back_arrow_RL.setOnClickListener(this);

        pet_reg_name_TV.setText(pet_name.substring(0, 1).toUpperCase() + pet_name.substring(1)+"("+pet_sex+")");
        parent_name_TV.setText(pet_owner_name);
        pet_reg__id_TV.setText(pet_unique_id);
        pet_reg_date_of_birth_TV.setText(pet_DOB);
        Glide.with(this)
                .load(pet_image_url)
                .placeholder(R.drawable.dummy_dog_image)
                .into(petRegImage_IV);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_clinic_visit_CL:
                    clinicDialog();
                break;

            case R.id.hospitalization_CL:
                HospitalizationDialog();
                break;

            case R.id.test_x_ray_CL:
                TestXrayDialog();

                break;

            case R.id.back_arrow_RL:
                onBackPressed();
                break;



        }
    }

    public void clinicDialog() {

        String userTYpe = sharedPreferences.getString("user_type", "");
        if (userTYpe.equals("Vet Staff")){
            Gson gson = new Gson();
            String json = sharedPreferences.getString("userPermission", null);
            Type type = new TypeToken<ArrayList<UserPermissionMasterList>>() {}.getType();
            ArrayList<UserPermissionMasterList> arrayList = gson.fromJson(json, type);
            Log.e("ArrayList",arrayList.toString());
            Log.d("UserType",userTYpe);
            if (arrayList.get(5).getIsSelected().equals("true")) {
                Intent petDetailsIntent = new Intent(this.getApplication(), AddClinicActivity.class);
                Bundle data = new Bundle();
                data.putString("pet_id",pet_id);
                data.putString("pet_name",pet_name);
                data.putString("pet_parent",pet_owner_name);
                data.putString("pet_sex",pet_sex);
                data.putString("pet_age",pet_age);
                data.putString("pet_unique_id",pet_unique_id);
                data.putString("pet_DOB",pet_DOB);
                data.putString("pet_encrypted_id",pet_encrypted_id);
                data.putString("nature_of_visit","");
                data.putString("visit_dt","");
                data.putString("visit_description","");
                data.putString("remarks","");
                data.putString("visit_weight","");
                data.putString("visit_temparature","");
                data.putString("dt_of_illness","");
                data.putString("pet_diognosis","");
                data.putString("next_dt","");
                data.putString("onlineAppointment", "no");
                data.putString("appoint_link", "");
                data.putString("toolbar_name","ADD CLINIC VISITS");
                data.putString("pet_cat_id",pet_cat_id);
                petDetailsIntent.putExtras(data);
                startActivity(petDetailsIntent);

            }else {
                Toast.makeText(this, "Permission not allowed!!", Toast.LENGTH_SHORT).show();
            }
        }else if (userTYpe.equals("Veterinarian")){
            Intent petDetailsIntent = new Intent(this.getApplication(), AddClinicActivity.class);
            Bundle data = new Bundle();
            data.putString("pet_id",pet_id);
            data.putString("pet_name",pet_name);
            data.putString("pet_parent",pet_owner_name);
            data.putString("pet_sex",pet_sex);
            data.putString("pet_age",pet_age);
            data.putString("pet_unique_id",pet_unique_id);
            data.putString("pet_DOB",pet_DOB);
            data.putString("pet_encrypted_id",pet_encrypted_id);
            data.putString("nature_of_visit","");
            data.putString("visit_dt","");
            data.putString("visit_description","");
            data.putString("remarks","");
            data.putString("visit_weight","");
            data.putString("visit_temparature","");
            data.putString("dt_of_illness","");
            data.putString("pet_diognosis","");
            data.putString("next_dt","");
            data.putString("onlineAppointment", "no");
            data.putString("appoint_link", "");
            data.putString("toolbar_name","ADD CLINIC VISITS");
            data.putString("pet_cat_id",pet_cat_id);
            petDetailsIntent.putExtras(data);
            startActivityForResult(petDetailsIntent,ADD_CLINIC_VISIT);

        }

    }

    public void TestXrayDialog() {
        Intent xRayIntent = new Intent(this,AddXRayDeatilsActivity.class);
        Bundle data = new Bundle();
        data.putString("type","add");
        data.putString("pet_id",pet_id);
        data.putString("pet_parent",pet_owner_name);
        data.putString("pet_sex",pet_sex);
        data.putString("pet_age",pet_age);
        data.putString("pet_unique_id",pet_unique_id);
        data.putString("pet_cat_id",pet_cat_id);
        data.putString("type","Add Test/X-rays");
        xRayIntent.putExtras(data);
        startActivity(xRayIntent);
    }

//    public void LabWorkDialog() {
//        Intent labWorkIntent = new Intent(this,AddLabWorkDeatilsActivity.class);
//        Bundle data = new Bundle();
//        data.putString("pet_id",pet_id);
//        data.putString("pet_parent",pet_owner_name);
//        data.putString("pet_sex",pet_sex);
//        data.putString("pet_age",pet_age);
//        data.putString("pet_unique_id",pet_unique_id);
//        data.putString("pet_cat_id",pet_cat_id);
//        data.putString("lab_type","");
//        data.putString("type","Add");
//
//        labWorkIntent.putExtras(data);
//        startActivity(labWorkIntent);
//    }

    public void HospitalizationDialog() {
        Intent hospitalIntent = new Intent(this,AddHospitalizationDeatilsActivity.class);
        Bundle data = new Bundle();
        data.putString("pet_id",pet_id);
        data.putString("pet_parent",pet_owner_name);
        data.putString("pet_sex",pet_sex);
        data.putString("pet_age",pet_age);
        data.putString("pet_unique_id",pet_unique_id);
        data.putString("pet_cat_id",pet_cat_id);
        data.putString("type","Add");
        hospitalIntent.putExtras(data);
        startActivity(hospitalIntent);
    }

    @Override
    public void onResponse(Response arg0, String key) {
        switch (key){
//            case "GetImmunization":
//                try {
//                    Log.d("GetImmunization",arg0.body().toString());
//                    PetImmunizationRecordResponse immunizationRecordResponse = (PetImmunizationRecordResponse) arg0.body();
////                    methods.customProgressDismiss();
//                    int responseCode = Integer.parseInt(immunizationRecordResponse.getResponse().getResponseCode());
//                    if (responseCode== 109){
//                        if (immunizationRecordResponse.getData().getPetImmunizationDetailModels().isEmpty()){
//                            // methods.customProgressDismiss();
//                            Toast.makeText(this, "No Record Found !", Toast.LENGTH_SHORT).show();
//                        }else {
//                            ArrayList<String> immunizationDate = new ArrayList<>();
//                            ArrayList<String> vaccineClass = new ArrayList<>();
//                            ArrayList<String> nextDueDate = new ArrayList<>();
//                            ArrayList<String> vaccineType = new ArrayList<>();
//
//                            ArrayList<String> immunizationDatePending = new ArrayList<>();
//                            ArrayList<String> vaccineClassPending = new ArrayList<>();
//                            ArrayList<String> nextDueDatePending = new ArrayList<>();
//                            ArrayList<String> vaccineTypePending = new ArrayList<>();
//
//                            for (int i = 0; i < immunizationRecordResponse.getData().getPetPendingVaccinations().size(); i++) {
//                                if(immunizationRecordResponse.getData().getPetPendingVaccinations().get(i).getIsVaccinated().equals("true"))
//                                {
//                                    immunizationDate.add(immunizationRecordResponse.getData().getPetPendingVaccinations().get(i).getVaccinationDate());
//                                    vaccineClass.add(immunizationRecordResponse.getData().getPetPendingVaccinations().get(i).getVaccineName());
//                                    nextDueDate.add(immunizationRecordResponse.getData().getPetPendingVaccinations().get(i).getNextVaccinationDate().substring(0, immunizationRecordResponse.getData().getPetPendingVaccinations().get(i).getNextVaccinationDate().length() - 9));
//                                    vaccineType.add(immunizationRecordResponse.getData().getPetPendingVaccinations().get(i).getVaccineType());
//
//                                }
//                                else
//                                {
//                                    immunizationDatePending.add(immunizationRecordResponse.getData().getPetPendingVaccinations().get(i).getVaccinationDate());
//                                    vaccineClassPending.add(immunizationRecordResponse.getData().getPetPendingVaccinations().get(i).getVaccineName());
//                                    nextDueDatePending.add(immunizationRecordResponse.getData().getPetPendingVaccinations().get(i).getNextVaccinationDate().substring(0, immunizationRecordResponse.getData().getPetPendingVaccinations().get(i).getNextVaccinationDate().length() - 9));
//                                    vaccineTypePending.add(immunizationRecordResponse.getData().getPetPendingVaccinations().get(i).getVaccineType());
//
//                                }
//                            }
//                            final JSONArray date = new JSONArray(immunizationDate);
//                            final JSONArray vaccine = new JSONArray(vaccineClass);
//                            final JSONArray nextDate = new JSONArray(nextDueDate);
//                            final JSONArray vType = new JSONArray(vaccineType);
//
//                            Log.d("jsjsjjsjs",""+date.length());
//
//                            final JSONArray datePending = new JSONArray(immunizationDatePending);
//                            final JSONArray vaccinePending = new JSONArray(vaccineClassPending);
//                            final JSONArray nextDatePending = new JSONArray(nextDueDatePending);
//                            final JSONArray vTypePending = new JSONArray(vaccineTypePending);
//
//                            Log.d("jsjsjjsjs",""+datePending.length());
//
//                            Log.e("aaaaaa", vaccineClass.toString());
//                            Log.e("aaaaaa", vaccine.toString());
//                            methods.customProgressDismiss();
//                            String immunizationSet = methods.immunizationPdfGenarator(pet_name, pet_age, pet_sex, pet_owner_name, Config.user_verterian_reg_no, vType, vaccine, nextDate, vTypePending, vaccinePending, nextDatePending);
//                            WebSettings webSettings = webview.getSettings();
//                            webSettings.setJavaScriptEnabled(true);
//                            webview.loadDataWithBaseURL(null, immunizationSet, "text/html", "utf-8", null);
//                            new Handler().postDelayed(new Runnable() {
//                                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//                                @Override
//                                public void run() {
//                                    Context context = NewEntrysDetailsActivity.this;
//                                    PrintManager printManager = (PrintManager)NewEntrysDetailsActivity.this.getSystemService(context.PRINT_SERVICE);
//                                    PrintDocumentAdapter adapter = null;
//                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                                        adapter = webview.createPrintDocumentAdapter();
//                                    }
//                                    String JobName = getString(R.string.app_name) + "Document";
//                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                                        PrintJob printJob = printManager.print(JobName, adapter, new PrintAttributes.Builder().build());
//                                    }
//                                }
//                            }, 3000);
//
//                        }
//
//                    }else if (responseCode==614){
//                        Toast.makeText(this, immunizationRecordResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CLINIC_VISIT) {
            if(resultCode == RESULT_OK) {
                Intent lastPrescriptionIntent = new Intent(this,PdfEditorActivity.class);
                lastPrescriptionIntent.putExtra("encryptId",data.getStringExtra("encryptId"));
                startActivity(lastPrescriptionIntent);

            }
        }
    }
}