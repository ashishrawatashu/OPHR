package com.cynoteck.petofyOPHR.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.adapters.ImmunizationChartAdapter;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.immunizationRequest.ImmunizationParams;
import com.cynoteck.petofyOPHR.params.immunizationRequest.ImmunizationRequest;
import com.cynoteck.petofyOPHR.response.CheckTrueFalseStatus;
import com.cynoteck.petofyOPHR.response.immunizationListResponse.ImmunizationResponse;
import com.cynoteck.petofyOPHR.response.loginRegisterResponse.UserPermissionMasterList;
import com.cynoteck.petofyOPHR.response.staffPermissionListResponse.CheckStaffPermissionResponse;
import com.cynoteck.petofyOPHR.response.updateProfileResponse.PetTypeResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.ImmunizationOnclick;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Response;

public class ImmunizationChartActivity extends AppCompatActivity implements View.OnClickListener, ApiResponse, ImmunizationOnclick {
    LinearLayout create_new_immu;
    AppCompatSpinner pet_type_ACS;
    RecyclerView immue_list_RV;
    MaterialCardView back_arrow_CV;
    Methods methods;
    ImmunizationChartAdapter immunizationChartAdapter;
    ImmunizationResponse immunizationResponse;
    int deletePostion;
    ArrayList<String> petType = new ArrayList<>();
    HashMap<String,String> petTypeHashMap=new HashMap<>();
    String strSpnerItemPetNm="Dog",getStrSpnerItemPetNmId="",strChartSize="";
    String userTYpe="";
    String permissionId="";
    SharedPreferences sharedPreferences;
    int immunizationPostion;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immunization_chart);
        methods = new Methods(this);
        sharedPreferences = getSharedPreferences("userdetails", 0);

        initization();
        if (methods.isInternetOn()) {
            petType();
            getImmunizationList("1");
        } else {
            methods.DialogInternet();
        }


    }

    private void petType() {
        ApiService<PetTypeResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().petTypeApi(), "GetPetTypes");


    }

    private void getImmunizationList(String getStrSpnerItemPetNmId) {
        progressBar.setVisibility(View.VISIBLE);
        immue_list_RV.setVisibility(View.GONE);
        ImmunizationParams immunizationParams = new ImmunizationParams();
        immunizationParams.setEncryptedId(getStrSpnerItemPetNmId);
        ImmunizationRequest immunizationRequest = new ImmunizationRequest();
        immunizationRequest.setData(immunizationParams);
        ApiService<ImmunizationResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getImmunizationList(Config.token, immunizationRequest), "ImmunizationList");
        Log.d("Immuniztaion", "parameter" + immunizationRequest);
    }

    private void initization() {
        progressBar=findViewById(R.id.progressBar);
        create_new_immu = findViewById(R.id.create_new_immu);
        pet_type_ACS = findViewById(R.id.pet_type);
        immue_list_RV = findViewById(R.id.immue_list_RV);
        back_arrow_CV = findViewById(R.id.back_arrow_CV);

        create_new_immu.setOnClickListener(this);
        back_arrow_CV.setOnClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                strSpnerItemPetNm =data.getStringExtra("petCat");
                getImmunizationList(data.getStringExtra("petCatId"));
            }
        }
    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.create_new_immu:
                userTYpe = sharedPreferences.getString("user_type", "");
                if (userTYpe.equals("Vet Staff")){
                    Gson gson = new Gson();
                    String json = sharedPreferences.getString("userPermission", null);
                    Type type = new TypeToken<ArrayList<UserPermissionMasterList>>() {}.getType();
                    ArrayList<UserPermissionMasterList> arrayList = gson.fromJson(json, type);
                    Log.e("ArrayList",arrayList.toString());
                    Log.d("UserType",userTYpe);
                    permissionId = "19";
                    methods.showCustomProgressBarDialog(this);
                    String url  = "user/CheckStaffPermission/"+permissionId;
                    Log.e("URL",url);
                    ApiService<CheckStaffPermissionResponse> service = new ApiService<>();
                    service.get(this, ApiClient.getApiInterface().getCheckStaffPermission(Config.token,url), "CheckPermission");
                }else if (userTYpe.equals("Veterinarian")){
                    Intent intent = new Intent(this,AddEditImmunizationActivity.class);
                    intent.putExtra("type","add");
                    intent.putExtra("pet_encrpt_id","MQ==");
                    intent.putExtra("serialNumber",strChartSize);
                    startActivityForResult(intent,1);

                }

                break;

            case R.id.back_arrow_CV:
                onBackPressed();
                break;

        }

    }

    @Override
    public void onResponse(Response arg0, String key) {
        switch (key) {
            case "ImmunizationList":
                try {
                    immunizationResponse = (ImmunizationResponse) arg0.body();
                    Log.d("ImmunizationList", immunizationResponse.toString());
                    int responseCode = Integer.parseInt(immunizationResponse.getResponse().getResponseCode());

                    if (responseCode == 109) {
                        create_new_immu.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                        immue_list_RV.setVisibility(View.VISIBLE);
                        strChartSize = String.valueOf(immunizationResponse.getData().getImmunizationScheduleScheduleList().size()+1);
                        Log.e("SIZE",strChartSize);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                        immue_list_RV.setLayoutManager(linearLayoutManager);
                        immunizationChartAdapter = new ImmunizationChartAdapter(this, immunizationResponse.getData().getImmunizationScheduleScheduleList(), this);
                        immue_list_RV.setAdapter(immunizationChartAdapter);
                    } else if (responseCode == 614) {
                        Toast.makeText(this, immunizationResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "DeleteImmunization":
                try {
                    CheckTrueFalseStatus checkTrueFalseStatus = (CheckTrueFalseStatus) arg0.body();
                    Log.d("DeleteImmunization", checkTrueFalseStatus.toString());
                    int responseCode = Integer.parseInt(checkTrueFalseStatus.getResponse().getResponseCode());

                    if (responseCode == 109) {
                        if (checkTrueFalseStatus.getData().equals("true")){
                            immunizationResponse.getData().getImmunizationScheduleScheduleList().remove(deletePostion);
                            immunizationChartAdapter.notifyItemRemoved(deletePostion);
                            immunizationChartAdapter.notifyItemRangeChanged(deletePostion,immunizationResponse.getData().getImmunizationScheduleScheduleList().size());
                        }
                    } else if (responseCode == 614) {
                        Toast.makeText(this, immunizationResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "GetPetTypes":
                try {
                    Log.d("GetPetTypes",arg0.body().toString());
                    PetTypeResponse petTypeResponse = (PetTypeResponse) arg0.body();
                    int responseCode = Integer.parseInt(petTypeResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        Log.d("lalal",""+petTypeResponse.getData().size());
                        for(int i=0; i<petTypeResponse.getData().size(); i++){
                            Log.d("petttt",""+petTypeResponse.getData().get(i).getPetType1());
                            petType.add(petTypeResponse.getData().get(i).getPetType1());
                            petTypeHashMap.put(petTypeResponse.getData().get(i).getPetType1(),petTypeResponse.getData().get(i).getId());
                        }
                        Log.e("aaa",petTypeHashMap.toString());
                        setPetTypeSpinner();

                    }else if (responseCode==614){
                        Toast.makeText(this, petTypeResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }

                break;

            case "CheckPermission":
                try {
                    methods.customProgressDismiss();
                    CheckStaffPermissionResponse checkStaffPermissionResponse = (CheckStaffPermissionResponse) arg0.body();
                    Log.d("GetPetList", checkStaffPermissionResponse.toString());
                    int responseCode = Integer.parseInt(checkStaffPermissionResponse.getResponse().getResponseCode());

                    if (responseCode == 109) {
                        if (checkStaffPermissionResponse.getData().equals("true")){
                            if (permissionId.equals("19")) {
                                Intent intent = new Intent(this,AddEditImmunizationActivity.class);
                                intent.putExtra("type","add");
                                intent.putExtra("pet_encrpt_id","MQ==");
                                intent.putExtra("serialNumber",strChartSize);
                                startActivityForResult(intent,1);
                            }else if (permissionId.equals("20")){
                                addEditIntent(immunizationPostion);
                            }else if (permissionId.equals("21")){
                                Log.d("Add Anotheer Veterian","vet");
                                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                                alertDialog.setTitle("Are you sure?");
                                alertDialog.setMessage("Do You Want to Delete This Schedule ?");
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (methods.isInternetOn()) {
                                                    deleteImmunization(deletePostion);
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
                            }
                        }else {
                            Toast.makeText(this, "Permission not Granted!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(this, "Please Try Again!!", Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
        }
    }

    private void setPetTypeSpinner() {
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,petType);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        pet_type_ACS.setAdapter(aa);
        if (!strSpnerItemPetNm.equals("")) {
            int spinnerPosition = aa.getPosition(strSpnerItemPetNm);
            pet_type_ACS.setSelection(spinnerPosition);
        }
        pet_type_ACS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                strSpnerItemPetNm=item;
                Log.d("spnerType",""+strSpnerItemPetNm);
                getStrSpnerItemPetNmId=petTypeHashMap.get(strSpnerItemPetNm);
                getImmunizationList(getStrSpnerItemPetNmId);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }



    @Override
        public void onError (Throwable t, String key){
        Log.e("ERROR",t.getLocalizedMessage());
        methods.customProgressDismiss();
        }

    @Override
    public void onDeleteButton(final int position) {
        deletePostion = position;


        userTYpe = sharedPreferences.getString("user_type", "");
        if (userTYpe.equals("Vet Staff")){
            Gson gson = new Gson();
            String json = sharedPreferences.getString("userPermission", null);
            Type type = new TypeToken<ArrayList<UserPermissionMasterList>>() {}.getType();
            ArrayList<UserPermissionMasterList> arrayList = gson.fromJson(json, type);
            Log.e("ArrayList",arrayList.toString());
            Log.d("UserType",userTYpe);
            permissionId = "21";
            methods.showCustomProgressBarDialog(this);
            String url  = "user/CheckStaffPermission/"+permissionId;
            Log.e("URL",url);
            ApiService<CheckStaffPermissionResponse> service = new ApiService<>();
            service.get(this, ApiClient.getApiInterface().getCheckStaffPermission(Config.token,url), "CheckPermission");
        }else if (userTYpe.equals("Veterinarian")){
            Log.d("Add Anotheer Veterian","vet");
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Are you sure?");
            alertDialog.setMessage("Do You Want to Delete This Schedule ?");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if (methods.isInternetOn()) {
                                deleteImmunization(position);
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
        }



    }

    private void deleteImmunization(int position) {
        ImmunizationParams immunizationParams = new ImmunizationParams();
        immunizationParams.setEncryptedId(immunizationResponse.getData().getImmunizationScheduleScheduleList().get(position).getId());
        ImmunizationRequest immunizationRequest = new ImmunizationRequest();
        immunizationRequest.setData(immunizationParams);
        ApiService<CheckTrueFalseStatus> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().deleteImmunizationSchedule(Config.token, immunizationRequest), "DeleteImmunization");
        Log.d("DeleteImmuniztaion", "parameter" + immunizationRequest);

    }

    @Override
    public void onEditButton(int position) {
        immunizationPostion = position;
        userTYpe = sharedPreferences.getString("user_type", "");
        if (userTYpe.equals("Vet Staff")){
            Gson gson = new Gson();
            String json = sharedPreferences.getString("userPermission", null);
            Type type = new TypeToken<ArrayList<UserPermissionMasterList>>() {}.getType();
            ArrayList<UserPermissionMasterList> arrayList = gson.fromJson(json, type);
            Log.e("ArrayList",arrayList.toString());
            Log.d("UserType",userTYpe);
            permissionId = "20";
            methods.showCustomProgressBarDialog(this);
            String url  = "user/CheckStaffPermission/"+permissionId;
            Log.e("URL",url);
            ApiService<CheckStaffPermissionResponse> service = new ApiService<>();
            service.get(this, ApiClient.getApiInterface().getCheckStaffPermission(Config.token,url), "CheckPermission");
        }else if (userTYpe.equals("Veterinarian")){
            addEditIntent(position);

        }

    }

    private void addEditIntent(int position) {
        Intent intent = new Intent(this,AddEditImmunizationActivity.class);
        intent.putExtra("type","edit");
        intent.putExtra("id",immunizationResponse.getData().getImmunizationScheduleScheduleList().get(position).getEncryptedId());
        intent.putExtra("petType",immunizationResponse.getData().getImmunizationScheduleScheduleList().get(position).getPetCategoryName());
        intent.putExtra("sNo",immunizationResponse.getData().getImmunizationScheduleScheduleList().get(position).getSerialNumber());
        intent.putExtra("ageUnit","Day");
        intent.putExtra("minage",immunizationResponse.getData().getImmunizationScheduleScheduleList().get(position).getMinimunAge());
        intent.putExtra("maxAge",immunizationResponse.getData().getImmunizationScheduleScheduleList().get(position).getMaximunAge());
        intent.putExtra("vaccineName",immunizationResponse.getData().getImmunizationScheduleScheduleList().get(position).getRecommendedVaccinations());
        intent.putExtra("boosterOne",immunizationResponse.getData().getImmunizationScheduleScheduleList().get(position).getBoosterOne());
        intent.putExtra("gapBoosterOne",immunizationResponse.getData().getImmunizationScheduleScheduleList().get(position).getBoosterOneDaysGap());
        intent.putExtra("boosterTwo",immunizationResponse.getData().getImmunizationScheduleScheduleList().get(position).getBoosterTwo());
        intent.putExtra("gapBoosterTwo",immunizationResponse.getData().getImmunizationScheduleScheduleList().get(position).getBoosterTwoDaysGap());
        intent.putExtra("isPeriodicVaccine",immunizationResponse.getData().getImmunizationScheduleScheduleList().get(position).getIsPeriodicVaccine());
        intent.putExtra("vaccinePeriod",immunizationResponse.getData().getImmunizationScheduleScheduleList().get(position).getVaccinationPeriodText());
        startActivityForResult(intent,1);

    }
}

