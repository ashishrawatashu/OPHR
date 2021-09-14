
package com.cynoteck.petofyOPHR.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.allStaffRequest.AddStaffParams;
import com.cynoteck.petofyOPHR.params.allStaffRequest.AddStaffRequest;
import com.cynoteck.petofyOPHR.params.allStaffRequest.StaffDeatilsRequest;
import com.cynoteck.petofyOPHR.params.allStaffRequest.StaffDetailsParams;
import com.cynoteck.petofyOPHR.params.allStaffRequest.UpdateStaffParams;
import com.cynoteck.petofyOPHR.params.allStaffRequest.UpdateStaffRequest;
import com.cynoteck.petofyOPHR.response.getStaffResponse.GetStaffDetailsResponse;
import com.cynoteck.petofyOPHR.response.getStaffResponse.GetUpdateStaffResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;

import java.util.ArrayList;

import retrofit2.Response;

public class AddUpdateStaffActivity extends AppCompatActivity implements View.OnClickListener, ApiResponse {

    RelativeLayout back_arrow_RL;
    TextView add_staff_headline_TV, staff_password_TV,staff__confirm_password_TV,staff_permission_TV;
    EditText staff_first_name_ET, staff_last_name_ET, staff_email_ET, staff_password_ET, staff_confirm_password_ET, staff_phone_ET, staff_qualification_ET, staff_reg_number_ET;
    AppCompatSpinner staff_prefix_ACP;
    CheckBox show_name_prec_CB;
    Button add_staff_BT;
    ConstraintLayout for_dr_layout_CL, staff_password_CL;
    Methods methods;
    String strStaffPrefix ="", strStaffFirstName ="", strStaffLastName ="", strStaffEmail ="", strStaffPhone ="",
            strStaffPassword ="", strStaffConfirmPassword ="", strStaffQualifications ="", strStaffRegNumber ="",
            StrDisplayNameInprec="false";
    ArrayList<String> staffPrefixList;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String activityType="",staffId="",staffUserId="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadd_update_staff);
        methods = new  Methods(this);

        Intent intent = getIntent();
        activityType=intent.getStringExtra("activityType");
        staffId=intent.getStringExtra("staffId");

        initization();
        if (activityType.equals("Update")){
            add_staff_headline_TV.setText("UPDATE STAFF");
            add_staff_BT.setText("Update");
            staff_password_CL.setVisibility(View.GONE);
            staff_permission_TV.setVisibility(View.GONE);

            getStaffDetails();
        }
        staffPrefixList = new ArrayList<>();
        staffPrefixList.add("Dr.");
        staffPrefixList.add("Mrs.");
        staffPrefixList.add("Mr.");
        staffPrefixList.add("Miss.");
        setSpinnerPrefix(strStaffPrefix);


    }



    private void setSpinnerPrefix(String StaffPrefix) {
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,staffPrefixList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        staff_prefix_ACP.setAdapter(aa);
        if (!StaffPrefix.equals("")) {
            int spinnerPosition = aa.getPosition(strStaffPrefix);
            staff_prefix_ACP.setSelection(spinnerPosition);
        }
        staff_prefix_ACP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                Log.d("staff_prefix_ACP","staff_prefix_ACP"+item);
                strStaffPrefix=item;
                if (strStaffPrefix.equals("Dr.")){
                    for_dr_layout_CL.setVisibility(View.VISIBLE);
                }else {
                    for_dr_layout_CL.setVisibility(View.GONE);
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initization() {
        back_arrow_RL =findViewById(R.id.back_arrow_RL);
        add_staff_headline_TV =findViewById(R.id.add_staff_headline_TV);
        staff_first_name_ET =findViewById(R.id.staff_first_name_ET);
        staff_last_name_ET =findViewById(R.id.staff_last_name_ET);
        staff_password_TV=findViewById(R.id.staff_password_TV);
        staff__confirm_password_TV=findViewById(R.id.staff__confirm_password_TV);
        staff_password_ET =findViewById(R.id.staff_password_ET);
        staff_confirm_password_ET =findViewById(R.id.staff_confirm_password_ET);
        staff_phone_ET =findViewById(R.id.staff_phone_ET);
        staff_qualification_ET =findViewById(R.id.staff_qualification_ET);
        staff_reg_number_ET =findViewById(R.id.staff_reg_number_ET);
        staff_prefix_ACP =findViewById(R.id.staff_prefix_ACP);
        show_name_prec_CB =findViewById(R.id.show_name_prec_CB);
        add_staff_BT =findViewById(R.id.add_staff_BT);
        staff_email_ET =findViewById(R.id.staff_email_ET);
        for_dr_layout_CL =findViewById(R.id.for_dr_layout_CL);
        staff_password_CL=findViewById(R.id.staff_password_CL);
        staff_permission_TV=findViewById(R.id.staff_permission_TV);

        add_staff_BT.setOnClickListener(this);
        show_name_prec_CB.setOnClickListener(this);
        back_arrow_RL.setOnClickListener(this);
        staff_permission_TV.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.back_arrow_RL:
                onBackPressed();
                break;

            case R.id.staff_permission_TV:
                Intent intent=new Intent(this, StaffPermissionActivity.class);
                intent.putExtra("staffUserId",staffUserId);
                startActivity(intent);
                break;
            case R.id.add_staff_BT:

                strStaffFirstName = staff_first_name_ET.getText().toString().trim();
                strStaffLastName = staff_last_name_ET.getText().toString().trim();
                strStaffEmail = staff_email_ET.getText().toString().trim();
                strStaffPassword = staff_password_ET.getText().toString().trim();
                strStaffConfirmPassword = staff_confirm_password_ET.getText().toString().trim();
                strStaffPhone = staff_phone_ET.getText().toString().trim();
                strStaffQualifications = staff_qualification_ET.getText().toString().trim();
                strStaffRegNumber = staff_reg_number_ET.getText().toString().trim();

                if (strStaffFirstName.isEmpty()){
                    staff_first_name_ET.setError("First name is empty!");
                    staff_last_name_ET.setError(null);
                    staff_email_ET.setError(null);
                    staff_password_ET.setError(null);
                    staff_confirm_password_ET.setError(null);
                    staff_phone_ET.setError(null);
                    staff_qualification_ET.setError(null);
                    staff_reg_number_ET.setError(null);

                }else if (strStaffLastName.isEmpty()){
                    staff_first_name_ET.setError(null);
                    staff_last_name_ET.setError("First name is empty!");
                    staff_email_ET.setError(null);
                    staff_password_ET.setError(null);
                    staff_confirm_password_ET.setError(null);
                    staff_phone_ET.setError(null);
                    staff_qualification_ET.setError(null);
                    staff_reg_number_ET.setError(null);

                }else if (strStaffEmail.isEmpty()){
                    staff_first_name_ET.setError(null);
                    staff_last_name_ET.setError(null);
                    staff_email_ET.setError("Email is empty!");
                    staff_password_ET.setError(null);
                    staff_confirm_password_ET.setError(null);
                    staff_phone_ET.setError(null);
                    staff_qualification_ET.setError(null);
                    staff_reg_number_ET.setError(null);

                }else if (!strStaffEmail.matches(emailPattern)) {
                    staff_first_name_ET.setError(null);
                    staff_last_name_ET.setError(null);
                    staff_email_ET.setError("Invalid email.!!");
                    staff_password_ET.setError(null);
                    staff_confirm_password_ET.setError(null);
                    staff_phone_ET.setError(null);
                    staff_qualification_ET.setError(null);
                    staff_reg_number_ET.setError(null);

                } else if (strStaffPhone.isEmpty()) {
                    staff_first_name_ET.setError(null);
                    staff_last_name_ET.setError(null);
                    staff_email_ET.setError(null);
                    staff_password_ET.setError(null);
                    staff_confirm_password_ET.setError(null);
                    staff_phone_ET.setError("Phone no. is empty!");
                    staff_qualification_ET.setError(null);
                    staff_reg_number_ET.setError(null);

                }else if (strStaffPhone.length()!=10) {
                    staff_first_name_ET.setError(null);
                    staff_last_name_ET.setError(null);
                    staff_email_ET.setError(null);
                    staff_password_ET.setError(null);
                    staff_confirm_password_ET.setError(null);
                    staff_phone_ET.setError("Invalid Phone no !");
                    staff_qualification_ET.setError(null);
                    staff_reg_number_ET.setError(null);

                } else if (strStaffPassword.isEmpty()&&activityType.equals("Add")) {
                        staff_first_name_ET.setError(null);
                        staff_last_name_ET.setError(null);
                        staff_email_ET.setError(null);
                        staff_password_ET.setError("Password is empty !");
                        staff_confirm_password_ET.setError(null);
                        staff_phone_ET.setError(null);
                        staff_qualification_ET.setError(null);
                        staff_reg_number_ET.setError(null);
                }else if (strStaffPassword.length()<6&&activityType.equals("Add")) {
                        staff_first_name_ET.setError(null);
                        staff_last_name_ET.setError(null);
                        staff_email_ET.setError(null);
                        staff_password_ET.setError("Password is to short !");
                        staff_confirm_password_ET.setError(null);
                        staff_phone_ET.setError(null);
                        staff_qualification_ET.setError(null);
                        staff_reg_number_ET.setError(null);

                }else if (strStaffConfirmPassword.isEmpty()&&activityType.equals("Add")) {
                        staff_first_name_ET.setError(null);
                        staff_last_name_ET.setError(null);
                        staff_email_ET.setError(null);
                        staff_password_ET.setError(null);
                        staff_confirm_password_ET.setError("Password is empty !");
                        staff_phone_ET.setError(null);
                        staff_qualification_ET.setError(null);
                        staff_reg_number_ET.setError(null);

                    }else if (!staff_password_ET.getText().toString().equals(staff_confirm_password_ET.getText().toString())&&activityType.equals("Add")){
                        staff_first_name_ET.setError(null);
                        staff_last_name_ET.setError(null);
                        staff_email_ET.setError(null);
                        staff_password_ET.setError(null);
                        staff_confirm_password_ET.setError("Password is not matched  ");
                        staff_phone_ET.setError(null);
                        staff_qualification_ET.setError(null);
                        staff_reg_number_ET.setError(null);


                } else if (strStaffPhone.length()<10) {
                    staff_first_name_ET.setError(null);
                    staff_last_name_ET.setError(null);
                    staff_email_ET.setError(null);
                    staff_password_ET.setError(null);
                    staff_confirm_password_ET.setError(null);
                    staff_phone_ET.setError("Invalid phone no.!!");
                    staff_qualification_ET.setError(null);
                    staff_reg_number_ET.setError(null);

                }else if (strStaffPhone.length()>10) {
                    staff_first_name_ET.setError(null);
                    staff_last_name_ET.setError(null);
                    staff_email_ET.setError(null);
                    staff_password_ET.setError(null);
                    staff_confirm_password_ET.setError(null);
                    staff_phone_ET.setError("Invalid phone no.!!");
                    staff_qualification_ET.setError(null);
                    staff_reg_number_ET.setError(null);

                }else if (strStaffQualifications.isEmpty()&&strStaffPrefix.equals("Dr.")){
                    staff_first_name_ET.setError(null);
                    staff_last_name_ET.setError(null);
                    staff_email_ET.setError(null);
                    staff_password_ET.setError(null);
                    staff_confirm_password_ET.setError(null);
                    staff_phone_ET.setError(null);
                    staff_qualification_ET.setError("Enter your qualification.");
                    staff_reg_number_ET.setError(null);
                }else if (strStaffRegNumber.isEmpty()&&strStaffPrefix.equals("Dr.")){
                    staff_first_name_ET.setError(null);
                    staff_last_name_ET.setError(null);
                    staff_email_ET.setError(null);
                    staff_password_ET.setError(null);
                    staff_confirm_password_ET.setError(null);
                    staff_phone_ET.setError(null);
                    staff_qualification_ET.setError(null);
                    staff_reg_number_ET.setError("Enter your registration no.");
                }else {
                    if (activityType.equals("Add")){
                        AddStaffParams addStaffParams = new AddStaffParams();
                        addStaffParams.setInitials(strStaffPrefix);
                        addStaffParams.setFirstName(strStaffFirstName);
                        addStaffParams.setLastName(strStaffLastName);
                        addStaffParams.setEmail(strStaffEmail);
                        addStaffParams.setPassword(strStaffPassword);
                        addStaffParams.setConfirmPassword(strStaffConfirmPassword);
                        addStaffParams.setPhoneNumber(strStaffPhone);
                        addStaffParams.setVetQualification(strStaffQualifications);
                        addStaffParams.setVetRegistrationNumber(strStaffRegNumber);
                        addStaffParams.setDisplayInPrescription(StrDisplayNameInprec);

                        AddStaffRequest addStaffRequest = new AddStaffRequest();
                        addStaffRequest.setData(addStaffParams);
                        Log.e("Add_staff",methods.getRequestJson(addStaffRequest));
                        if (methods.isInternetOn()){
                            addStaffDetails(addStaffRequest);
                        }else {
                            methods.DialogInternet();
                        }
                    }
                    else {

                        UpdateStaffParams updateStaffParams = new UpdateStaffParams();
                        updateStaffParams.setEncryptedId(staffId);
                        updateStaffParams.setInitials(strStaffPrefix);
                        updateStaffParams.setFirstName(strStaffFirstName);
                        updateStaffParams.setLastName(strStaffLastName);
                        updateStaffParams.setEmail(strStaffEmail);
                        updateStaffParams.setPhoneNumber(strStaffPhone);
                        updateStaffParams.setVetQualification(strStaffQualifications);
                        updateStaffParams.setVetRegistrationNumber(strStaffRegNumber);
                        updateStaffParams.setDisplayInPrescription(StrDisplayNameInprec);




                        UpdateStaffRequest updateStaffRequest = new UpdateStaffRequest();
                        updateStaffRequest.setData(updateStaffParams);
                        updateStaffDetails(updateStaffRequest);
                        Log.e("Update_staff",methods.getRequestJson(updateStaffRequest));
                    }

                }

                break;
            case R.id.show_name_prec_CB:
                if(((show_name_prec_CB)).isChecked()){
                    StrDisplayNameInprec= "true";
                } else {
                    StrDisplayNameInprec= "false";
                }
                break;

        }
    }

    private void updateStaffDetails(UpdateStaffRequest updateStaffRequest) {
        methods.showCustomProgressBarDialog(this);
        ApiService<GetUpdateStaffResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().updateStaff(Config.token, updateStaffRequest), "UpdateStaff");
        Log.e("addstaff",updateStaffRequest.toString());
    }

    private void addStaffDetails(AddStaffRequest addStaffRequest) {
        methods.showCustomProgressBarDialog(this);
        ApiService<GetStaffDetailsResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().addNewStaff(Config.token, addStaffRequest), "AddStaff");
        Log.e("addstaff",addStaffRequest.toString());
    }

    private void getStaffDetails() {
        methods.showCustomProgressBarDialog(this);
        StaffDetailsParams staffDetailsParams = new StaffDetailsParams();
        staffDetailsParams.setEncryptedId(staffId);
        StaffDeatilsRequest staffDeatilsRequest = new StaffDeatilsRequest();
        staffDeatilsRequest.setData(staffDetailsParams);
        ApiService<GetStaffDetailsResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getStaffDetails(Config.token, staffDeatilsRequest), "GetStaffDetails");
        Log.e("staffDetailsRequest",staffDeatilsRequest.toString());

    }

    @Override
    public void onResponse(Response response, String key) {
        methods.customProgressDismiss();
        switch (key){
            case "AddStaff":
                try {
                    GetStaffDetailsResponse getStaffDetailsResponse = (GetStaffDetailsResponse) response.body();
                    Log.d("DATALOG-ADDSTAFF", getStaffDetailsResponse.toString());
                    int responseCode = Integer.parseInt(getStaffDetailsResponse.getResponse().getResponseCode());
                    Log.e("code",getStaffDetailsResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        Toast.makeText(this, "Staff Added Successfully", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                        finish();
                    }else if (responseCode==615){
                        Toast.makeText(this, getStaffDetailsResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }else if (responseCode==625){
                        Toast.makeText(this, getStaffDetailsResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(this, "Something Went Wrong !", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }

                break;

            case "GetStaffDetails":
                try {
                    GetStaffDetailsResponse getStaffDetails = (GetStaffDetailsResponse) response.body();
                    Log.d("DATALOG-STAFF_DETAILS", getStaffDetails.toString());
                    int responseCode = Integer.parseInt(getStaffDetails.getResponse().getResponseCode());
                    if (responseCode== 109){
                        strStaffPrefix = getStaffDetails.getData().getInitials();
                        staff_first_name_ET.setText(getStaffDetails.getData().getFirstName());
                        staff_last_name_ET.setText(getStaffDetails.getData().getLastName());
                        staff_phone_ET.setText(getStaffDetails.getData().getPhoneNumber());
                        staff_email_ET.setText(getStaffDetails.getData().getEmail());
                        staff_email_ET.setEnabled(false);
                        staffUserId=getStaffDetails.getData().getUserId();
//                        staff_password_ET.setText(getStaffDetails.getData().getPassword());
//                        staff_confirm_password_ET.setText(getStaffDetails.getData().getConfirmPassword());
                        staff_qualification_ET.setText(getStaffDetails.getData().getQualification());
                        staff_reg_number_ET.setText(getStaffDetails.getData().getVetRegistrationNumber());
                        StrDisplayNameInprec = getStaffDetails.getData().getDisplayInPrescription();
                        if (StrDisplayNameInprec.equals("true")){
                            show_name_prec_CB.setChecked(true);
                        }else if (StrDisplayNameInprec.equals("false")){
                            show_name_prec_CB.setChecked(false);
                        }
                        Log.e("StaffPrefix",strStaffPrefix);
                        if (strStaffPrefix.equals("Dr.")){
                            for_dr_layout_CL.setVisibility(View.VISIBLE);
                            setSpinnerPrefix(strStaffPrefix);
                        }else {
                            for_dr_layout_CL.setVisibility(View.VISIBLE);
                            setSpinnerPrefix(strStaffPrefix);
                        }
                    }else{

                        Toast.makeText(this, "Something Went Wrong !", Toast.LENGTH_SHORT).show();

                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }

            case "UpdateStaff":
                try {
                    Config.isUpdated = true;
                    GetUpdateStaffResponse getUpdateStaffResponse = (GetUpdateStaffResponse) response.body();
                    Log.d("DATALOG-UPDATE", getUpdateStaffResponse.toString());
                    Log.e("code",getUpdateStaffResponse.getResponse().getResponseCode());
                    int responseCode = Integer.parseInt(getUpdateStaffResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        Toast.makeText(this, "Staff Updated Successfully", Toast.LENGTH_SHORT).show();
                        Intent updateStaffIntent = new Intent();
                        updateStaffIntent.putExtra("staffId", getUpdateStaffResponse.getData().getEncryptedId());
                        updateStaffIntent.putExtra("staffUserId", getUpdateStaffResponse.getData().getUserId());
                        updateStaffIntent.putExtra("staff_name", getUpdateStaffResponse.getData().getFirstName() + " " + getUpdateStaffResponse.getData().getLastName());
                        updateStaffIntent.putExtra("staff_email", getUpdateStaffResponse.getData().getEmail());
                        updateStaffIntent.putExtra("staff_phone", getUpdateStaffResponse.getData().getPhoneNumber());
                        updateStaffIntent.putExtra("staff_degree", getUpdateStaffResponse.getData().getVetQualification());
                        updateStaffIntent.putExtra("staff_reg_no", getUpdateStaffResponse.getData().getVetRegistrationNumber());
                        updateStaffIntent.putExtra("staff_image_url", getUpdateStaffResponse.getData().getProfileImageUrl());
                        setResult(RESULT_OK,updateStaffIntent);
                        finish();
                    }else if (responseCode==615){
                        Toast.makeText(this, getUpdateStaffResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }else if (responseCode==625){
                        Toast.makeText(this, getUpdateStaffResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, "Something Went Wrong !", Toast.LENGTH_SHORT).show();
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

        methods.customProgressDismiss();
        Log.e("ADDUPDATEStaff",t.getLocalizedMessage());
    }
}