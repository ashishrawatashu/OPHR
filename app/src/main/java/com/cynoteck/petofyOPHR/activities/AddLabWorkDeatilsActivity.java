package com.cynoteck.petofyOPHR.activities;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.addLabRequest.AddLabParams;
import com.cynoteck.petofyOPHR.params.addLabRequest.AddLabRequest;
import com.cynoteck.petofyOPHR.params.updateLapTestParams.UpdateLabTestParams;
import com.cynoteck.petofyOPHR.params.updateLapTestParams.UpdateLabTestRequest;
import com.cynoteck.petofyOPHR.response.addLabWorkResponse.AddLabWorkResponse;
import com.cynoteck.petofyOPHR.response.addPet.imageUpload.ImageResponse;
import com.cynoteck.petofyOPHR.response.labTyperesponse.LabTypeResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
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

public class AddLabWorkDeatilsActivity extends AppCompatActivity implements View.OnClickListener, ApiResponse {

    EditText veterian_name_ET,veterian_phone_ET,lab_name_ET,lab_phone_ET,test_name_ET,reson_of_visit_ET,result_ET;
    AppCompatSpinner labType_spinner;
    TextView calenderTextViewVisitDt,lab_upload_documents,lab_peto_edit_reg_number_dialog,doctorPrescription_headline_TV;
    Button save_BT;
    ImageView lab_document,lab_back_arrow_IV;

    Methods methods;

    ArrayList<String>labTypeArrayList;

    HashMap<String,String>labTypeHash=new HashMap<>();

    DatePickerDialog picker;

    String report_id="",lab_type_string="",LabTypeId="",pet_id="",pet_name="",pet_owner_name="",pet_sex="",pet_unique_id="",strDocumentUrl="",test_name="",lab_phone="",lab_name="",lab_type="",visit_date="",result="",reason="",type="";

    private static final String IMAGE_DIRECTORY = "/Picture";
    private int GALLERY = 1, CAMERA = 2;
    File file = null;
    Dialog dialog;
    Bitmap bitmap, thumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lab_work_deatils);
        init();
        requestMultiplePermissions();
    }

    private void init() {
        methods=new Methods(this);
        Bundle extras = getIntent().getExtras();

        lab_peto_edit_reg_number_dialog=findViewById(R.id.lab_peto_edit_reg_number_dialog);
        veterian_name_ET=findViewById(R.id.veterian_name_ET);
        veterian_phone_ET=findViewById(R.id.veterian_phone_ET);
        lab_name_ET=findViewById(R.id.lab_name_ET);
        lab_phone_ET=findViewById(R.id.lab_phone_ET);
        test_name_ET=findViewById(R.id.test_name_ET);
        reson_of_visit_ET=findViewById(R.id.reson_of_visit_ET);
        lab_upload_documents=findViewById(R.id.lab_upload_documents);
        lab_document=findViewById(R.id.lab_document);
        result_ET=findViewById(R.id.result_ET);
        labType_spinner=findViewById(R.id.labType_spinner);
        calenderTextViewVisitDt=findViewById(R.id.calenderTextViewVisitDt);
        save_BT=findViewById(R.id.save_BT);
        lab_back_arrow_IV=findViewById(R.id.lab_back_arrow_IV);
        doctorPrescription_headline_TV=findViewById(R.id.doctorPrescription_headline_TV);
        calenderTextViewVisitDt.setOnClickListener(this);
        save_BT.setOnClickListener(this);
        lab_upload_documents.setOnClickListener(this);
        lab_back_arrow_IV.setOnClickListener(this);

        if (extras != null) {
            report_id = extras.getString("report_id");
            pet_id = extras.getString("pet_id");
            pet_name = extras.getString("pet_name");
            pet_owner_name = extras.getString("pet_owner_name");
            pet_sex = extras.getString("pet_sex");
            pet_unique_id = extras.getString("pet_unique_id");
            test_name = extras.getString("test_name");
            lab_phone = extras.getString("lab_phone");
            lab_name = extras.getString("lab_name");
            lab_type = extras.getString("lab_type");
            visit_date = extras.getString("visit_date");
            result = extras.getString("result");
            reason = extras.getString("reason");
            type = extras.getString("type");

            Log.d("labType",lab_type);

            lab_peto_edit_reg_number_dialog.setText(pet_unique_id);
            veterian_name_ET.setText(Config.user_Veterian_name);
            calenderTextViewVisitDt.setText(Config.currentDate());
            veterian_phone_ET.setText(Config.user_Veterian_phone);
        }

        if (type.equals("Update Lab Work")){
            save_BT.setText("UPDATE");
            doctorPrescription_headline_TV.setText("Update Lab Work");
            calenderTextViewVisitDt.setText(visit_date);
            lab_name_ET.setText(lab_name);
            lab_phone_ET.setText(lab_phone);
            test_name_ET.setText(test_name);
            result_ET.setText(result);
            reson_of_visit_ET.setText(reason);

        }else{
            save_BT.setText("SUBMIT");
            calenderTextViewVisitDt.setText(Config.currentDate());

        }

        if (methods.isInternetOn()){
            getLabTypeList();
        }else {
            methods.DialogInternet();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.calenderTextViewVisitDt:
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                calenderTextViewVisitDt.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
                break;
            case R.id.lab_upload_documents:
                showPictureDialog();
                break;
            case R.id.save_BT:
                String strRequstVeterian=veterian_name_ET.getText().toString();
                String strPhoneNumber=veterian_phone_ET.getText().toString();
                String strLabName=lab_name_ET.getText().toString();
                String strLabphoneNumber=lab_phone_ET.getText().toString();
                String strNameofTest=test_name_ET.getText().toString();
                String strReasonOfVisit=reson_of_visit_ET.getText().toString();
                String strRsult=result_ET.getText().toString();
                String strLabVisitDt=calenderTextViewVisitDt.getText().toString();

                if(strRequstVeterian.isEmpty()){
                    veterian_name_ET.setError("Enter Veterinarian Name");
                    veterian_phone_ET.setError(null);
                    lab_name_ET.setError(null);
                    test_name_ET.setError(null);
                    reson_of_visit_ET.setError(null);
                    result_ET.setError(null);
                }
                else if(strPhoneNumber.isEmpty())
                {
                    veterian_name_ET.setError(null);
                    veterian_phone_ET.setError("Enter Contact Number");
                    lab_name_ET.setError(null);
                    test_name_ET.setError(null);
                    reson_of_visit_ET.setError(null);
                    result_ET.setError(null);
                }
                else if(strLabName.isEmpty())
                {
                    veterian_name_ET.setError(null);
                    veterian_phone_ET.setError(null);
                    lab_name_ET.setError("Enter Lab Name");
                    test_name_ET.setError(null);
                    reson_of_visit_ET.setError(null);
                    result_ET.setError(null);
                }
                else if(strNameofTest.isEmpty())
                {
                    veterian_name_ET.setError(null);
                    veterian_phone_ET.setError(null);
                    lab_name_ET.setError(null);
                    test_name_ET.setError("Name of Test");
                    reson_of_visit_ET.setError(null);
                    result_ET.setError(null);
                }
                else if(strReasonOfVisit.isEmpty())
                {
                    veterian_name_ET.setError(null);
                    veterian_phone_ET.setError(null);
                    lab_name_ET.setError(null);
                    test_name_ET.setError(null);
                    reson_of_visit_ET.setError("Reason of Test");
                    result_ET.setError(null);
                }
                else if(strRsult.isEmpty())
                {
                    veterian_name_ET.setError(null);
                    veterian_phone_ET.setError(null);
                    lab_name_ET.setError(null);
                    test_name_ET.setError(null);
                    reson_of_visit_ET.setError(null);
                    result_ET.setError("Result");
                }
                else if(strLabVisitDt.isEmpty())
                {
                    veterian_name_ET.setError(null);
                    veterian_phone_ET.setError(null);
                    lab_name_ET.setError(null);
                    test_name_ET.setError(null);
                    reson_of_visit_ET.setError(null);
                    result_ET.setError(null);
                    Toast.makeText(this, "Select Visit Date ", Toast.LENGTH_SHORT).show();
                }else if (lab_type_string.isEmpty()||lab_type_string.equals("Select Lab Type")){

                    veterian_name_ET.setError(null);
                    veterian_phone_ET.setError(null);
                    lab_name_ET.setError(null);
                    test_name_ET.setError(null);
                    reson_of_visit_ET.setError(null);
                    result_ET.setError(null);
                    Toast.makeText(this, "Select Lab type ", Toast.LENGTH_SHORT).show();
                }
                else{
                    methods.showCustomProgressBarDialog(this);
                    veterian_name_ET.setError(null);
                    veterian_phone_ET.setError(null);
                    lab_name_ET.setError(null);
                    test_name_ET.setError(null);
                    reson_of_visit_ET.setError(null);
                    result_ET.setError(null);


                    if (type.equals("Update Lab Work")){
                        UpdateLabTestParams updateLabTestParams=new UpdateLabTestParams();
                        updateLabTestParams.setId(report_id);
                        updateLabTestParams.setPetId(pet_id);
                        updateLabTestParams.setRequestingVeterinarian(strRequstVeterian);
                        updateLabTestParams.setVeterinarianPhone(strPhoneNumber);
                        updateLabTestParams.setVisitDate(strLabVisitDt);
                        updateLabTestParams.setLabTypeId(LabTypeId);
                        updateLabTestParams.setNameOfLab(strLabName);
                        updateLabTestParams.setLabPhone(strLabphoneNumber);
                        updateLabTestParams.setAddress1("");
                        updateLabTestParams.setAddress2("");
                        updateLabTestParams.setCountryId("");
                        updateLabTestParams.setStateId("");
                        updateLabTestParams.setCityId("");
                        updateLabTestParams.setZipCode("");
                        updateLabTestParams.setTestName(strNameofTest);
                        updateLabTestParams.setReasonOfTest(strReasonOfVisit);
                        updateLabTestParams.setResults(strRsult);
                        updateLabTestParams.setDocuments(strDocumentUrl);
                        UpdateLabTestRequest updateLabTestRequest =new UpdateLabTestRequest();
                        updateLabTestRequest.setAddPetParams(updateLabTestParams);
                        if(methods.isInternetOn())
                        {
                            updateLabWork(updateLabTestRequest);
                        }
                        else
                        {
                            methods.DialogInternet();
                        }
                    }else {
                        AddLabParams addLabParams=new AddLabParams();
                        addLabParams.setPetId(pet_id);
                        addLabParams.setRequestingVeterinarian(strRequstVeterian);
                        addLabParams.setVeterinarianPhone(strPhoneNumber);
                        addLabParams.setVisitDate(strLabVisitDt);
                        addLabParams.setLabTypeId(LabTypeId);
                        addLabParams.setNameOfLab(strLabName);
                        addLabParams.setLabPhone(strLabphoneNumber);
                        addLabParams.setAddress1("");
                        addLabParams.setAddress2("");
                        addLabParams.setCountryId("");
                        addLabParams.setStateId("");
                        addLabParams.setCityId("");
                        addLabParams.setZipCode("");
                        addLabParams.setTestName(strNameofTest);
                        addLabParams.setReasonOfTest(strReasonOfVisit);
                        addLabParams.setResults(strRsult);
                        addLabParams.setDocuments(strDocumentUrl);
                        AddLabRequest addLabRequest=new AddLabRequest();
                        addLabRequest.setAddPetParams(addLabParams);
                        if(methods.isInternetOn())
                        {
                            addPetLabTestReport(addLabRequest);
                        }
                        else
                        {
                            methods.DialogInternet();
                        }
                    }


                }
                break;

            case R.id.lab_back_arrow_IV:
                onBackPressed();
                break;
        }

    }

    private void updateLabWork(UpdateLabTestRequest updateLabTestRequest) {
        ApiService<AddLabWorkResponse> service = new ApiService<>();
        service.get( this, ApiClient.getApiInterface().updatePetLabWork(Config.token,updateLabTestRequest), "UpdatePetLabWork");
        Log.d("updatePetLabParams",""+updateLabTestRequest);

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
                    lab_document.setImageBitmap(bitmap);
                    saveImage(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(AddLabWorkDeatilsActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        }
        else if (requestCode == CAMERA) {

            if (data.getData() == null)
            {
                thumbnail = (Bitmap) data.getExtras().get("data");
                Log.e("jghl",""+thumbnail);
                lab_document.setImageBitmap(thumbnail);
                saveImage(thumbnail);
                Toast.makeText(AddLabWorkDeatilsActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
            }

            else{
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(AddLabWorkDeatilsActivity.this.getContentResolver(), data.getData());
                    lab_document.setImageBitmap(bitmap);
                    saveImage(bitmap);
                    Toast.makeText(AddLabWorkDeatilsActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
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
        service.get( this, ApiClient.getApiInterface().uploadImages(Config.token,userDpFilePart), "UploadDocument");
        Log.e("DATALOG","check1=> "+service);

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
                            Toast.makeText(AddLabWorkDeatilsActivity.this, "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(AddLabWorkDeatilsActivity.this, "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    private void getLabTypeList() {
        ApiService<LabTypeResponse> service = new ApiService<>();
        service.get( this, ApiClient.getApiInterface().getLabTypeList(Config.token), "GetLabTypeList");
    }

    private void addPetLabTestReport(AddLabRequest addLabRequest) {
        ApiService<AddLabWorkResponse> service = new ApiService<>();
        service.get( this, ApiClient.getApiInterface().addPetLabWork(Config.token,addLabRequest), "AddPetLabWork");
        Log.d("addPetLabParams",""+addLabRequest);
    }

    @Override
    public void onResponse(Response arg0, String key) {

        switch (key) {
                case "GetLabTypeList":
                    try {
                        LabTypeResponse labTypeResponse = (LabTypeResponse) arg0.body();
                        Log.d("GetLabTypeList", labTypeResponse.toString());
                        int responseCode = Integer.parseInt(labTypeResponse.getResponse().getResponseCode());

                        if (responseCode == 109) {
                            //Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                            labTypeArrayList = new ArrayList<>();
                            labTypeArrayList.add("Select Lab Type");
                            for (int i = 0; i < labTypeResponse.getData().size(); i++) {
                                labTypeArrayList.add(labTypeResponse.getData().get(i).getLab());
                                labTypeHash.put(labTypeResponse.getData().get(i).getLab(), labTypeResponse.getData().get(i).getId());
                            }
                            setSpinneerLabType();
                        } else if (responseCode == 614) {
                            Toast.makeText(this, labTypeResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            case "UploadDocument":
                try {
                    Log.d("UploadDocument",arg0.body().toString());
                    ImageResponse imageResponse = (ImageResponse) arg0.body();
                    int responseCode = Integer.parseInt(imageResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        //Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
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
            case "AddPetLabWork":
                try {
                    methods.customProgressDismiss();
                    AddLabWorkResponse addLabWorkResponse = (AddLabWorkResponse) arg0.body();
                    Log.d("AddPetLabWork", addLabWorkResponse.toString());
                    int responseCode = Integer.parseInt(addLabWorkResponse.getResponse().getResponseCode());

                    if (responseCode == 109) {
                        Config.type="Lab";
                        onBackPressed();
                        Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    } else if (responseCode == 614) {
                        Toast.makeText(this, addLabWorkResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "UpdatePetLabWork":
                try {
                    methods.customProgressDismiss();
                    AddLabWorkResponse addLabWorkResponse = (AddLabWorkResponse) arg0.body();
                    Log.d("UpdatePetLabWork", addLabWorkResponse.toString());
                    int responseCode = Integer.parseInt(addLabWorkResponse.getResponse().getResponseCode());

                    if (responseCode == 109) {
                        Config.type="Lab";
                        onBackPressed();
                        Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    } else if (responseCode == 614) {
                        Toast.makeText(this, addLabWorkResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    private void setSpinneerLabType() {
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,labTypeArrayList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        labType_spinner.setAdapter(aa);
        if (!lab_type.equals("")) {
            int spinnerPosition = aa.getPosition(lab_type);
            labType_spinner.setSelection(spinnerPosition);
        }
        labType_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                lab_type_string =item;
                LabTypeId=labTypeHash.get(lab_type_string);
                Log.d("TYPE",""+item);

            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void onError(Throwable t, String key) {

    }
}