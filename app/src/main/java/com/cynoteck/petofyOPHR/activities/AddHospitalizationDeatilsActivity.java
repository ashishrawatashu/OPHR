package com.cynoteck.petofyOPHR.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.addHospitalization.AddHospitalizationParam;
import com.cynoteck.petofyOPHR.params.addHospitalization.AddHospitalizationRequest;
import com.cynoteck.petofyOPHR.params.updateHospitalizationParams.UpdateHospitalizationParams;
import com.cynoteck.petofyOPHR.params.updateHospitalizationParams.UpdateHospitalizationRequest;
import com.cynoteck.petofyOPHR.response.addHospitalizationResponse.AddhospitalizationResposee;
import com.cynoteck.petofyOPHR.response.addPet.imageUpload.ImageResponse;
import com.cynoteck.petofyOPHR.response.hospitalTypeListResponse.HospitalAddmissionTypeResp;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.MediaUtils;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.google.android.material.card.MaterialCardView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import in.gauriinfotech.commons.Commons;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class AddHospitalizationDeatilsActivity extends AppCompatActivity implements View.OnClickListener, ApiResponse , MediaUtils.GetImg{
    EditText veterian_name_ET,veterian_phone_ET,hospital_name_ET,hospital_phone_ET,reson_of_hospitalization_ET,result_ET;
    Spinner hospital_type_spinner;
    TextView upload_doc_image_TV,  calenderTextView_admission_date,hospitalization_peto_edit_reg_number_dialog,
            calenderTextView_discharge_date_TV,doctorPrescription_headline_TV;
    Button save_BT;
    ImageView upload_doc_image_upload_IV,upload_doc_image_delete_IV;
    MaterialCardView back_arrow_CV;
    Methods methods;

    ArrayList<String> hospitalTypeArrayList;

    HashMap<String,String> hospitalTypeHashmap=new HashMap<>();

    DatePickerDialog picker;
    ProgressBar upload_doc_image_progress_bar;
    final Calendar cldr = Calendar.getInstance();

    String report_id="",hospitalizationStr="",hospitalizationId="",pet_id="",pet_name="",pet_owner_name="",pet_sex="",pet_unique_id="",strDocumentUrl="",hospital_type,hospital_name,hospital_phone,admission,discharge,result,reason,type;

    private static final String IMAGE_DIRECTORY = "/Picture";
    private int GALLERY = 1, CAMERA = 2;
    File file = null;
    Dialog dialog;
    Bitmap bitmap, thumbnail;
    String[] mimeTypes = {"image/*",
            "application/pdf",
            "application/msword",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            "text/plain"
    };
    private int                 DOC_UPLOAD=105;
    int front_status            = 0;
    MediaUtils mediaUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hospitalization_deatils);
        mediaUtils  = new MediaUtils(this);
        init();
    }

    private void init() {
        Bundle extras = getIntent().getExtras();
        hospitalization_peto_edit_reg_number_dialog=findViewById(R.id.hospitalization_peto_edit_reg_number_dialog);
        veterian_name_ET=findViewById(R.id.veterian_name_ET);
        veterian_phone_ET=findViewById(R.id.veterian_phone_ET);
        hospital_name_ET=findViewById(R.id.hospital_name_ET);
        hospital_phone_ET=findViewById(R.id.hospital_phone_ET);
        reson_of_hospitalization_ET=findViewById(R.id.reson_of_hospitalization_ET);
        result_ET=findViewById(R.id.result_ET);
        hospital_type_spinner=findViewById(R.id.hospital_type_spinner);
        calenderTextView_admission_date=findViewById(R.id.calenderTextView_admission_date);
        calenderTextView_discharge_date_TV=findViewById(R.id.calenderTextView_discharge_date_TV);
        save_BT=findViewById(R.id.save_BT);
        back_arrow_CV=findViewById(R.id.back_arrow_CV);
        upload_doc_image_progress_bar = findViewById(R.id.upload_doc_image_progress_bar);
        doctorPrescription_headline_TV=findViewById(R.id.doctorPrescription_headline_TV);
        upload_doc_image_upload_IV = findViewById(R.id.upload_doc_image_upload_IV);
        upload_doc_image_delete_IV = findViewById(R.id.upload_doc_image_delete_IV);
        upload_doc_image_TV = findViewById(R.id.upload_doc_image_TV);
        calenderTextView_admission_date.setOnClickListener(this);
        calenderTextView_discharge_date_TV.setOnClickListener(this);
        upload_doc_image_upload_IV.setOnClickListener(this);
        upload_doc_image_delete_IV.setOnClickListener(this);
        save_BT.setOnClickListener(this);
        back_arrow_CV.setOnClickListener(this);

        if (extras != null) {
            report_id = extras.getString("report_id");
            pet_id = extras.getString("pet_id");
            pet_name = extras.getString("pet_name");
            pet_owner_name = extras.getString("pet_owner_name");
            pet_sex = extras.getString("pet_sex");
            pet_unique_id = extras.getString("pet_unique_id");
            hospital_type = extras.getString("hospital_type");
            hospital_name = extras.getString("hospital_name");
            hospital_phone = extras.getString("hospital_phone");
            admission = extras.getString("admission");
            discharge = extras.getString("discharge");
            result = extras.getString("result");
            reason = extras.getString("reason");
            type = extras.getString("type");

            hospitalization_peto_edit_reg_number_dialog.setText(pet_unique_id);
            veterian_name_ET.setText(Config.user_Veterian_name);
            veterian_phone_ET.setText(Config.user_Veterian_phone);
        }

        methods=new Methods(this);
        if (type.equals("Update Hospitalization")){
            doctorPrescription_headline_TV.setText(type);
            calenderTextView_admission_date.setText(admission);
            calenderTextView_discharge_date_TV.setText(discharge);
            hospital_name_ET.setText(hospital_name);
            hospital_phone_ET.setText(hospital_phone);
            reson_of_hospitalization_ET.setText(reason);
            result_ET.setText(result);
            save_BT.setText("UPDATE");

        }else {
            save_BT.setText("Save");
        }

        if (methods.isInternetOn()){
            getHospitalTypeList();
        }else {
            methods.DialogInternet();
        }


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.calenderTextView_admission_date:
//                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                calenderTextView_admission_date.setText(Config.changeDateFormat(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year));
                            }
                        }, year, month, day);

                Config.day= day;
                Config.month=month;
                picker.getDatePicker().setMinDate(cldr.getTimeInMillis());
                picker.show();
                break;
            case R.id.calenderTextView_discharge_date_TV:
//                final Calendar cldr = Calendar.getInstance();
                int dayDis = cldr.get(Calendar.DAY_OF_MONTH);
                int monthDis = cldr.get(Calendar.MONTH);
                int yearDis = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                calenderTextView_discharge_date_TV.setText(Config.changeDateFormat(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year));
                            }
                        }, yearDis, monthDis, dayDis);
//                picker.updateDate(Config.day,Config.month,yearDis);
//                cldr.updateDate(yearDis,Config.month,Config.day);

                picker.show();
                break;

            case R.id.upload_doc_image_delete_IV:
                strDocumentUrl = "";
                upload_doc_image_progress_bar.setProgress(0);
                upload_doc_image_upload_IV.setVisibility(View.VISIBLE);
                upload_doc_image_delete_IV.setVisibility(View.GONE);

                break;

            case R.id.upload_doc_image_upload_IV:
//                Intent intent1 = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//                intent1.addCategory(Intent.CATEGORY_OPENABLE);
//                intent1.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
//                intent1.setType("*application/pdf||*application/doc");
//                startActivityForResult(Intent.createChooser(intent1, "Select a file"), DOC_UPLOAD);
//                mediaUtils.openGallery();
                showPictureDialog();

                break;
            case R.id.save_BT:
                String strRequstVeterian=veterian_name_ET.getText().toString();
                String strPhoneNumber=veterian_phone_ET.getText().toString();
                String strHospitalName=hospital_name_ET.getText().toString();
                String strHospitalphoneNumber=hospital_phone_ET.getText().toString();
                String strResonsOfHospitalization=reson_of_hospitalization_ET.getText().toString();
                String strResult=result_ET.getText().toString();
                String strHospitalAdmissionDt=calenderTextView_admission_date.getText().toString();
                String strHospitalDischargeDt=calenderTextView_discharge_date_TV.getText().toString();

                if(strRequstVeterian.isEmpty()){
                    veterian_name_ET.setError("Enter Veterinarian Name");
                    hospital_name_ET.setError(null);
                    hospital_phone_ET.setError(null);
                    reson_of_hospitalization_ET.setError(null);
                    calenderTextView_admission_date.setError(null);
                    veterian_phone_ET.setError(null);
                    calenderTextView_discharge_date_TV.setError(null);
                }else if (strPhoneNumber.length() != 10){
                    veterian_name_ET.setError(null);
                    hospital_name_ET.setError(null);
                    reson_of_hospitalization_ET.setError(null);
                    calenderTextView_admission_date.setError(null);
                    calenderTextView_discharge_date_TV.setError(null);
                    veterian_phone_ET.setError("Invalid phone no !");
                }else if(hospitalizationStr.equals("Select Hospital Type")) {
                    veterian_name_ET.setError(null);
                    hospital_name_ET.setError(null);
                    veterian_phone_ET.setError(null);
                    reson_of_hospitalization_ET.setError(null);
                    calenderTextView_admission_date.setError(null);
                    calenderTextView_discharge_date_TV.setError(null);
                    hospital_phone_ET.setError(null);
                    Toast.makeText(this, "Select Hospitalization type ", Toast.LENGTH_SHORT).show();
                }else if(strHospitalAdmissionDt.isEmpty()) {
                    veterian_name_ET.setError(null);
                    hospital_name_ET.setError(null);
                    reson_of_hospitalization_ET.setError(null);
                    veterian_phone_ET.setError(null);
                    hospital_phone_ET.setError(null);
                    calenderTextView_admission_date.setError("Enter Admission Date");
                    calenderTextView_discharge_date_TV.setError(null);
                }
                else if(strHospitalDischargeDt.isEmpty()) {
                    veterian_name_ET.setError(null);
                    hospital_name_ET.setError(null);
                    reson_of_hospitalization_ET.setError(null);
                    calenderTextView_admission_date.setError(null);
                    veterian_phone_ET.setError(null);
                    hospital_phone_ET.setError(null);
                    calenderTextView_discharge_date_TV.setError("Enter discharge Date");
                }
                else if(strHospitalName.isEmpty())
                {
                    veterian_name_ET.setError(null);
                    hospital_phone_ET.setError(null);
                    hospital_name_ET.setError("Enter Hospital Name");
                    reson_of_hospitalization_ET.setError(null);
                    veterian_phone_ET.setError(null);
                    calenderTextView_admission_date.setError(null);
                    calenderTextView_discharge_date_TV.setError(null);
                }
                else if(strResonsOfHospitalization.isEmpty())
                {
                    veterian_name_ET.setError(null);
                    hospital_name_ET.setError(null);
                    veterian_phone_ET.setError(null);
                    hospital_phone_ET.setError(null);
                    reson_of_hospitalization_ET.setError("Reason of Hospitalization");
                    calenderTextView_admission_date.setError(null);
                    calenderTextView_discharge_date_TV.setError(null);
                } else if (!strHospitalphoneNumber.equals("")&&strHospitalphoneNumber.length()!=10){

                    veterian_name_ET.setError(null);
                    hospital_name_ET.setError(null);
                    veterian_phone_ET.setError(null);
                    reson_of_hospitalization_ET.setError(null);
                    calenderTextView_admission_date.setError(null);
                    calenderTextView_discharge_date_TV.setError(null);
                    hospital_phone_ET.setError("Invalid phone no!");
                } else {
                    methods.showCustomProgressBarDialog(this);
                    if (type.equals("Update Hospitalization")) {
                        UpdateHospitalizationParams updateHospitalizationParams = new UpdateHospitalizationParams();
                        updateHospitalizationParams.setId(report_id);
                        updateHospitalizationParams.setPetId(pet_id);
                        updateHospitalizationParams.setRequestingVeterinarian(strRequstVeterian);
                        updateHospitalizationParams.setVeterinarianPhone(strPhoneNumber);
                        updateHospitalizationParams.setHospitalizationTypeId(hospitalizationId);
                        updateHospitalizationParams.setAdmissionDate(strHospitalAdmissionDt);
                        updateHospitalizationParams.setDischargeDate(strHospitalDischargeDt);
                        updateHospitalizationParams.setHospitalName(strHospitalName);
                        updateHospitalizationParams.setHospitalPhone(strHospitalphoneNumber);
                        updateHospitalizationParams.setAddress("");
                        updateHospitalizationParams.setCountryId("");
                        updateHospitalizationParams.setStateId("");
                        updateHospitalizationParams.setCityId("");
                        updateHospitalizationParams.setZipCode("");
                        updateHospitalizationParams.setReasonForHospitalization(strResonsOfHospitalization);
                        updateHospitalizationParams.setDiagnosisTreatmentProcedure(strResult);
                        updateHospitalizationParams.setDocuments(strDocumentUrl);
                        UpdateHospitalizationRequest updateHospitalizationRequest  = new UpdateHospitalizationRequest();
                        updateHospitalizationRequest.setData(updateHospitalizationParams);
                        if (methods.isInternetOn()) {
                            updateHospitalization(updateHospitalizationRequest);
                        } else {
                            methods.DialogInternet();
                        }
                    } else {
                        AddHospitalizationParam addHospitalizationParam = new AddHospitalizationParam();
                        addHospitalizationParam.setPetId(pet_id);
                        addHospitalizationParam.setRequestingVeterinarian(strRequstVeterian);
                        addHospitalizationParam.setVeterinarianPhone(strPhoneNumber);
                        addHospitalizationParam.setHospitalizationTypeId(hospitalizationId);
                        addHospitalizationParam.setAdmissionDate(strHospitalAdmissionDt);
                        addHospitalizationParam.setDischargeDate(strHospitalDischargeDt);
                        addHospitalizationParam.setHospitalName(strHospitalName);
                        addHospitalizationParam.setHospitalPhone(strHospitalphoneNumber);
                        addHospitalizationParam.setAddress("");
                        addHospitalizationParam.setCountryId("");
                        addHospitalizationParam.setStateId("");
                        addHospitalizationParam.setCityId("");
                        addHospitalizationParam.setZipCode("");
                        addHospitalizationParam.setReasonForHospitalization(strResonsOfHospitalization);
                        addHospitalizationParam.setDiagnosisTreatmentProcedure(strResult);
                        addHospitalizationParam.setDocuments(strDocumentUrl);
                        AddHospitalizationRequest addHospitalizationRequest = new AddHospitalizationRequest();
                        addHospitalizationRequest.setData(addHospitalizationParam);
                        if (methods.isInternetOn()) {
                            addHospitalization(addHospitalizationRequest);
                        } else {
                            methods.DialogInternet();
                        }
                    }

                }
                break;
            case R.id.back_arrow_CV:
                onBackPressed();
                break;

        }

    }

    private void updateHospitalization(UpdateHospitalizationRequest updateHospitalizationRequest) {
        ApiService<AddhospitalizationResposee> service = new ApiService<>();
        service.get( this, ApiClient.getApiInterface().updatePetHospitalization(Config.token,updateHospitalizationRequest), "UpdatePetHospitalization");
        Log.e("AddPetHospitalParam","===>"+updateHospitalizationRequest);

    }
    private void requestMultiplePermissions() {
        Dexter.withActivity(this)
                .withPermissions(
//                        android.Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Log.d("STORAGE_DIALOG","All permissions are granted by user!");
                        }else {
                            Log.d("STORAGE_DIALOG","storagePermissionDialog");
//                            storagePermissionDialog();
                            startActivity(new Intent(AddHospitalizationDeatilsActivity.this,PermissionCheckActivity.class));
                            Toast.makeText(AddHospitalizationDeatilsActivity.this, "Please allow storage permission !", Toast.LENGTH_SHORT).show();
                        }


                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            Toast.makeText(AddHospitalizationDeatilsActivity.this, "Please allow storage permission !", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddHospitalizationDeatilsActivity.this,PermissionCheckActivity.class));
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
                        Toast.makeText(AddHospitalizationDeatilsActivity.this, "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestMultiplePermissions();
    }

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mediaUtils.onActivityResult(requestCode, resultCode, data);
//        if (requestCode==DOC_UPLOAD){
//            Uri uri = data.getData();
//            String fullPath = Commons.getPath(uri, this);
//            File file = new File(fullPath);
//            UploadImages(file);
//        }
    }

    private void UploadImages(File absolutePath) {
        methods.showCustomProgressBarDialog(this);
        MultipartBody.Part userDpFilePart = null;
        if (absolutePath != null) {
            RequestBody userDpFile = RequestBody.create(MediaType.parse("image/*"), absolutePath);
            userDpFilePart = MultipartBody.Part.createFormData("file", absolutePath.getName(), userDpFile);
        }
        front_status = 0;
        Handler front_handler = new Handler();
        upload_doc_image_progress_bar.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (front_status < 80) {

                    front_status += 1;

                    try {
                        Thread.sleep(15);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    front_handler.post(new Runnable() {
                        @Override
                        public void run() {
                            upload_doc_image_progress_bar.setProgress(front_status);
                        }
                    });
                }
            }
        }).start();
        ApiService<ImageResponse> service = new ApiService<>();
        service.get( this, ApiClient.getApiInterface().uploadImages(Config.token,userDpFilePart), "UploadDocument");
        Log.e("DATALOG","check1=> "+service);

    }

    private void addHospitalization(AddHospitalizationRequest addHospitalizationRequest) {
        ApiService<AddhospitalizationResposee> service = new ApiService<>();
        service.get( this, ApiClient.getApiInterface().addPetHospitalization(Config.token,addHospitalizationRequest), "AddPetHospitalization");
        Log.e("AddPetHospitalParam","===>"+addHospitalizationRequest);

    }

    private void getHospitalTypeList() {
        ApiService<HospitalAddmissionTypeResp> service = new ApiService<>();
        service.get( this, ApiClient.getApiInterface().getHospitalTypeList(Config.token), "getHospitalTypeList");
    }


    @Override
    public void onResponse(Response arg0, String key) {
        switch (key) {
            case "getHospitalTypeList":
                try {
                    HospitalAddmissionTypeResp hospitalAddmissionTypeResponse = (HospitalAddmissionTypeResp) arg0.body();
                    Log.d("getHospitalTypeList", hospitalAddmissionTypeResponse.toString());
                    int responseCode = Integer.parseInt(hospitalAddmissionTypeResponse.getResponse().getResponseCode());

                    if (responseCode == 109) {
                        hospitalTypeArrayList = new ArrayList<>();
                        hospitalTypeArrayList.add("Select Hospital Type");
                        for (int i = 0; i < hospitalAddmissionTypeResponse.getData().size(); i++) {
                            hospitalTypeArrayList.add(hospitalAddmissionTypeResponse.getData().get(i).getHospitalization());
                            hospitalTypeHashmap.put(hospitalAddmissionTypeResponse.getData().get(i).getHospitalization(), hospitalAddmissionTypeResponse.getData().get(i).getId());
                        }
                        setSpinnerHospitalizationType();
                    } else if (responseCode == 614) {
                        Toast.makeText(this, hospitalAddmissionTypeResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "UploadDocument":
                try {
                    methods.customProgressDismiss();
                    ImageResponse imageResponse = (ImageResponse) arg0.body();
                    int responseCode = Integer.parseInt(imageResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        upload_doc_image_TV.setText("Document Uploaded");
                        upload_doc_image_progress_bar.setProgress(100);
                        upload_doc_image_upload_IV.setVisibility(View.GONE);
                        upload_doc_image_delete_IV.setVisibility(View.VISIBLE);
                        strDocumentUrl=imageResponse.getData().getDocumentUrl();
                    }else if (responseCode==614){
                        Toast.makeText(this, imageResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case "AddPetHospitalization":
                try {
                    methods.customProgressDismiss();
                    AddhospitalizationResposee addhospitalizationResposee = (AddhospitalizationResposee) arg0.body();
                    Log.d("AddPetHospitalization", addhospitalizationResposee.toString());
                    int responseCode = Integer.parseInt(addhospitalizationResposee.getResponse().getResponseCode());

                    if (responseCode == 109) {
                        Config.type ="Hospitalization";
                        onBackPressed();
                        Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    } else if (responseCode == 614) {
                        Toast.makeText(this, addhospitalizationResposee.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "UpdatePetHospitalization":
                try {
                    methods.customProgressDismiss();
                    AddhospitalizationResposee addhospitalizationResposee = (AddhospitalizationResposee) arg0.body();
                    Log.d("UpdateHospitalization", addhospitalizationResposee.toString());
                    int responseCode = Integer.parseInt(addhospitalizationResposee.getResponse().getResponseCode());

                    if (responseCode == 109) {
                        Config.type ="Hospitalization";
                        onBackPressed();
                        Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    } else if (responseCode == 614) {
                        Toast.makeText(this, addhospitalizationResposee.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

        }

    }

    private void setSpinnerHospitalizationType() {
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,hospitalTypeArrayList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        hospital_type_spinner.setAdapter(aa);
        if (type.equals("Update Hospitalization")) {
            if (!hospital_type.equals("")) {
                int spinnerPosition = aa.getPosition(hospital_type);
                hospital_type_spinner.setSelection(spinnerPosition);
            }
        }
        hospital_type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                Log.d("spnerType",""+item);
                hospitalizationStr=item;
                hospitalizationId=hospitalTypeHashmap.get(item);
                Log.d("Hospitalization_Id",""+hospitalizationId);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    @Override
    public void onError(Throwable t, String key) {
        Log.e("Error",t.getLocalizedMessage());
    }

    @Override
    public void imgdata(String imgPath) {
        Log.d ("imgdata123" , imgPath.toString());
        Uri selectedImageURI = null;
        File imgFile = new File(imgPath);
        Log.d ("imgdata: " , imgFile.toString());
        UploadImages(imgFile);
    }

    private void showPictureDialog() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        RelativeLayout select_camera = (RelativeLayout) dialog.findViewById(R.id.select_camera);
        RelativeLayout select_gallery = (RelativeLayout) dialog.findViewById(R.id.select_gallery);
        RelativeLayout cancel_dialog = (RelativeLayout) dialog.findViewById(R.id.cancel_dialog);

        select_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                mediaUtils.openCamera();
            }
        });

        select_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaUtils.openGallery();
                dialog.dismiss();
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







}