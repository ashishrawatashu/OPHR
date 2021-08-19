package com.cynoteck.petofyOPHR.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.getPetListRequest.GetPetListParams;
import com.cynoteck.petofyOPHR.params.getPetListRequest.GetPetListRequest;
import com.cynoteck.petofyOPHR.response.onlineAppointmentOnOff.OnlineAppointmentResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.google.android.material.card.MaterialCardView;

import java.io.IOException;
import java.lang.reflect.Method;

import retrofit2.Response;

public class SettingActivity extends AppCompatActivity implements ApiResponse, View.OnClickListener {
    MaterialCardView back_arrow_CV;
    EditText appointment_duration_ET,gap_duration_ET;
    TextView two_fact_auth_TV,immunization_chart_TV,bank_account_details_TV,share_prescription_TV;
    SwitchCompat two_fact_auth_SC,share_prescription_SC;
    String status="";
    Methods method;
    RelativeLayout rl;
//    private android.R.layout ShareWithoutLogin_CL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        back_arrow_CV = findViewById(R.id.back_arrow_CV);
        two_fact_auth_SC =findViewById(R.id.two_fact_auth_SC);
rl=(RelativeLayout)findViewById(R.id.ShareWithoutLogin_RL);
appointment_duration_ET=findViewById(R.id.appointment_duration_ET);
gap_duration_ET=findViewById(R.id.gap_duration_ET);

        two_fact_auth_TV=findViewById(R.id.two_fact_auth_TV);

        immunization_chart_TV = findViewById(R.id.immunization_chart_TV);
        bank_account_details_TV=findViewById(R.id.bank_account_details_TV);

        share_prescription_SC=findViewById(R.id.share_prescription_SC);
        share_prescription_TV=findViewById(R.id.share_prescription_TV);



        immunization_chart_TV.setOnClickListener(this);
        bank_account_details_TV.setOnClickListener(this);
        back_arrow_CV.setOnClickListener(this);
        rl.setVisibility(View.INVISIBLE);

        setPrescriptionSettings();
        prescriptionSwitchOnline();


        setTwoFactData();
        switchOnline();

    }


    private void prescriptionSwitchOnline(){
        share_prescription_SC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    status = "11";
                    PrescriptionEnableDiable(status);
                    rl.setVisibility(View.VISIBLE);
                }else {
                    status = "00";
                    PrescriptionEnableDiable(status);
                    rl.setVisibility(View.INVISIBLE);

                }
            }
        });

    }

    private void switchOnline() {
        two_fact_auth_SC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    status = "1";
                    enableDisable(status);
                }else {
                    status = "0";
                    enableDisable(status);

                }
            }
        });

    }

    private void enableDisable(String status) {
        GetPetListParams getPetListParams = new GetPetListParams();
        getPetListParams.setId(status);
        GetPetListRequest getPetListRequest = new GetPetListRequest();
        getPetListRequest.setData(getPetListParams);
        ApiService<OnlineAppointmentResponse> service = new ApiService<>();
        service.get( this, ApiClient.getApiInterface().enableDisableTowFactorAuth(Config.token,getPetListRequest), "EnableDisable");

    }

    private void PrescriptionEnableDiable(String status){
        GetPetListParams getPetListParams = new GetPetListParams();
        getPetListParams.setId(status);
        GetPetListRequest getPetListRequest = new GetPetListRequest();
        getPetListRequest.setData(getPetListParams);
        ApiService<OnlineAppointmentResponse> service = new ApiService<>();
        service.get( this, ApiClient.getApiInterface().enableDiasbleSharePrescription(Config.token,getPetListRequest),"PrescriptionEnableDisable");



    }



    private void setTwoFactData() {
        if (Config.two_fact_auth_status.equals("true")){
            two_fact_auth_SC.setChecked(true);
            two_fact_auth_TV.setText("Disable Two Step Authentication");
        }else {
            two_fact_auth_SC.setChecked(false);
            two_fact_auth_TV.setText("Enable Two Step Authentication");
        }
    }
    private void setPrescriptionSettings() {
        if (Config.prescription_Settings.equals("true")){
            share_prescription_SC.setChecked(true);
            share_prescription_TV.setText("Disable Prescription Settings");
        }else {
            share_prescription_SC.setChecked(false);
            share_prescription_TV.setText("Enable Prescription Settings");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTwoFactData();
        setPrescriptionSettings();
    }

    @Override
    public void onResponse(Response response, String key) {
//        Log.e("KeyValue", "onResponse: "+response.body().toString());
        Log.e("KeyValue", "onResponse: "+key);

        switch (key){
            case "EnableDisable":
                try {
                    Log.d("EnableDisable",response.body().toString());
                    OnlineAppointmentResponse onlineAppointmentResponse = (OnlineAppointmentResponse) response.body();
                    int responseCode = Integer.parseInt(onlineAppointmentResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        if (status.equals("1")){
                            Toast.makeText(this, "Two Step Authentication Enable", Toast.LENGTH_SHORT).show();
                            two_fact_auth_TV.setText("Disable Two Step Authentication");
                            SharedPreferences sharedPreferences = getSharedPreferences("userdetails", 0);
                            SharedPreferences.Editor login_editor;
                            login_editor = sharedPreferences.edit();
                            login_editor.putString("twoFactAuth", "true");
                            login_editor.commit();
                            Config.two_fact_auth_status ="true";
                        }else {
                            Toast.makeText(this, "Two Step Authentication Disable", Toast.LENGTH_SHORT).show();
                            two_fact_auth_TV.setText("Enable Two Step Authentication");
                            SharedPreferences sharedPreferences = getSharedPreferences("userdetails", 0);
                            SharedPreferences.Editor login_editor;
                            login_editor = sharedPreferences.edit();
                            login_editor.putString("twoFactAuth", "false");
                            login_editor.commit();
                            Config.two_fact_auth_status ="false";
                        }
                    }else if (responseCode==614){
                        Toast.makeText(this, onlineAppointmentResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                }

                catch(Exception e) {
                    e.printStackTrace();
                }

                break;


            case"PrescriptionEnableDisable":
                try {
                    OnlineAppointmentResponse onlineAppointmentResponse = (OnlineAppointmentResponse) response.body();
                    int responseCode = Integer.parseInt(onlineAppointmentResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
//                        method.showCustomProgressBarDialog(this);
                        if (status.equals("11")){
                            Toast.makeText(this, "Prescription Settings Enable", Toast.LENGTH_SHORT).show();
                            share_prescription_TV.setText("Disable Prescription Settings");
                            SharedPreferences sharedPreferences = getSharedPreferences("userdetails", 0);
                            SharedPreferences.Editor login_editor;
                            login_editor = sharedPreferences.edit();
                            login_editor.putString("PrescriptionSettings", "true");
                            login_editor.commit();
                            Config.prescription_Settings ="true";
                        }else {
                            Toast.makeText(this, "Prescription Settings Disable", Toast.LENGTH_SHORT).show();
                            share_prescription_TV.setText("Enable Prescription Settings");
                            SharedPreferences sharedPreferences = getSharedPreferences("userdetails", 0);
                            SharedPreferences.Editor login_editor;
                            login_editor = sharedPreferences.edit();
                            login_editor.putString("PrescriptionSettings", "false");
                            login_editor.commit();
                            Config.prescription_Settings ="false";
                        }
                    }else if (responseCode==614){
                        Toast.makeText(this, onlineAppointmentResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }






        }
    }

    @Override
    public void onError(Throwable t, String key) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_arrow_CV:
            onBackPressed();

            break;

            case R.id.immunization_chart_TV:
                Intent intent = new Intent(this,ImmunizationChartActivity.class);
                startActivity(intent);
                break;

            case R.id.bank_account_details_TV:
                Intent  intent1 = new Intent(this,GetAllBankAccountsActivity.class);
                startActivity(intent1);
        }
    }
}
