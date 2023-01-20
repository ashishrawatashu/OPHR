package com.cynoteck.petofyOPHR.activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.adapters.AllStaffAdapter;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.allStaffRequest.AddStaffParams;
import com.cynoteck.petofyOPHR.params.allStaffRequest.AddStaffRequest;
import com.cynoteck.petofyOPHR.params.allStaffRequest.ChangeStaffStatusParams;
import com.cynoteck.petofyOPHR.params.allStaffRequest.ChangeStaffStatusRequest;
import com.cynoteck.petofyOPHR.params.allStaffRequest.UpdateStaffParams;
import com.cynoteck.petofyOPHR.params.allStaffRequest.UpdateStaffRequest;
import com.cynoteck.petofyOPHR.response.getStaffResponse.GetAllStaffData;
import com.cynoteck.petofyOPHR.response.getStaffResponse.GetAllStaffResponse;
import com.cynoteck.petofyOPHR.response.getStaffResponse.GetStaffDetailsResponse;
import com.cynoteck.petofyOPHR.response.getStaffResponse.GetStaffStatusResponse;
import com.cynoteck.petofyOPHR.response.getStaffResponse.GetUpdateStaffResponse;
import com.cynoteck.petofyOPHR.response.loginRegisterResponse.UserPermissionMasterList;
import com.cynoteck.petofyOPHR.response.staffPermissionListResponse.CheckStaffPermissionResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.cynoteck.petofyOPHR.utils.StaffListClickListener;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Response;

public class StaffListActivity extends AppCompatActivity implements ApiResponse, StaffListClickListener, View.OnClickListener {
    RecyclerView all_staff_List_RV;
    Methods methods;
    AllStaffAdapter allStaffAdapter;
    private ShimmerFrameLayout mShimmerViewContainer;
    ArrayList<GetAllStaffData> getAllStaffData;
    String email_id, first_name, last_name, password, confirm_password, phone_number, encrypt_id;
    TextView total_no_of_staff_TV;
    String permissionId;
    private int ADD_STAFF_DEATILS = 1;
    SharedPreferences sharedPreferences;
    RelativeLayout add_satff_RL;
    MaterialCardView back_arrow_CV;
    EditText search_box_ET;
    int staffPostion;
    View coordinatorLayout;

    public StaffListActivity() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_list_activity);

        init();
        sharedPreferences = getSharedPreferences("userdetails", 0);
    }

    private void init() {
        methods = new Methods(this);
        coordinatorLayout = findViewById(R.id.content_view);
        all_staff_List_RV = findViewById(R.id.all_staff_List_RV);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        add_satff_RL = findViewById(R.id.add_satff_RL);
        back_arrow_CV = findViewById(R.id.back_arrow_CV);
        total_no_of_staff_TV = findViewById(R.id.total_no_of_staff_TV);
        search_box_ET = findViewById(R.id.search_box_ET);
        search_box_ET.setEnabled(false);

        back_arrow_CV.setOnClickListener(this);
        add_satff_RL.setOnClickListener(this);

        searchStaffList();

        mShimmerViewContainer.startShimmerAnimation();
        if (methods.isInternetOn()) {
            getAllStaff();
        } else {
            methods.DialogInternet();
        }
    }

    private void searchStaffList() {
        search_box_ET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                allStaffAdapter.getFilter().filter(s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void onViewDetailsClick(int position) {
        staffPostion = position;
        Log.d("staff_details", getAllStaffData.toString());
        encrypt_id = getAllStaffData.get(position).getEncryptedId();

        String userTYpe = sharedPreferences.getString("user_type", "");
        if (userTYpe.equals("Vet Staff")) {
            Gson gson = new Gson();
            String json = sharedPreferences.getString("userPermission", null);
            Type type = new TypeToken<ArrayList<UserPermissionMasterList>>() {
            }.getType();
            ArrayList<UserPermissionMasterList> arrayList = gson.fromJson(json, type);
            Log.e("ArrayList", arrayList.toString());
            Log.d("UserType", userTYpe);
            permissionId = "5";
            methods.showCustomProgressBarDialog(this);
            String url = "user/CheckStaffPermission/" + permissionId;
            Log.e("URL", url);
            ApiService<CheckStaffPermissionResponse> service = new ApiService<>();
            service.get(this, ApiClient.getApiInterface().getCheckStaffPermission(Config.token, url), "CheckPermission");
        } else if (userTYpe.equals("Veterinarian")) {
            Intent updateStaffIntent = new Intent(this, StaffDetailsActivity.class);
//            updateStaffIntent.putExtra("activityType", "Update");
            updateStaffIntent.putExtra("staffId", encrypt_id);
            updateStaffIntent.putExtra("staffUserId", getAllStaffData.get(position).getUserId());
            updateStaffIntent.putExtra("staff_name", getAllStaffData.get(position).getFirstName() + " " + getAllStaffData.get(position).getLastName());
            updateStaffIntent.putExtra("staff_email", getAllStaffData.get(position).getEmail());
            updateStaffIntent.putExtra("staff_phone", getAllStaffData.get(position).getPhoneNumber());
            updateStaffIntent.putExtra("staff_degree", getAllStaffData.get(position).getVetQualification());
            updateStaffIntent.putExtra("staff_reg_no", getAllStaffData.get(position).getVetRegistrationNumber());
            updateStaffIntent.putExtra("staff_image_url", getAllStaffData.get(position).getProfileImageUrl());
            startActivityForResult(updateStaffIntent, ADD_STAFF_DEATILS);
        }


    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onStausClick(int position, TextView textView, ImageView staff_staus_IV) {
        String status = getAllStaffData.get(position).getIsActive();
        String post_status = "";
        String encrtpty_id = getAllStaffData.get(position).getEncryptedId();
        if (status.equals("true")) {
            getAllStaffData.get(position).setIsActive("false");
            textView.setText("Deactivate");
            textView.setTextColor(this.getResources().getColor(R.color.deactivate_red));
            staff_staus_IV.setColorFilter(this.getResources().getColor(R.color.deactivate_red));
            post_status = "false";
        } else {
            getAllStaffData.get(position).setIsActive("true");
            textView.setText("Active");
            textView.setTextColor(this.getResources().getColor(R.color.dark_green));
            staff_staus_IV.setColorFilter(this.getResources().getColor(R.color.dark_green));
            post_status = "true";
        }

        ChangeStaffStatusParams changeStaffStatusParams = new ChangeStaffStatusParams();
        changeStaffStatusParams.setEncryptedId(encrtpty_id);
        changeStaffStatusParams.setStatus(post_status);
        ChangeStaffStatusRequest changeStaffStatusRequest = new ChangeStaffStatusRequest();
        changeStaffStatusRequest.setData(changeStaffStatusParams);
        ApiService<GetStaffStatusResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getStaffStatus(Config.token, changeStaffStatusRequest), "GetStaffStatus");
        Log.e("DATALOG", "=> " + changeStaffStatusRequest);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrow_CV:
                onBackPressed();
                break;


            case R.id.add_satff_RL:

//                showAddStaffDilaog();
                String userTYpe = sharedPreferences.getString("user_type", "");
                if (userTYpe.equals("Vet Staff")) {
                    Gson gson = new Gson();
                    String json = sharedPreferences.getString("userPermission", null);
                    Type type = new TypeToken<ArrayList<UserPermissionMasterList>>() {
                    }.getType();
                    ArrayList<UserPermissionMasterList> arrayList = gson.fromJson(json, type);
                    Log.e("ArrayList", arrayList.toString());
                    Log.d("UserType", userTYpe);
                    permissionId = "4";
                    methods.showCustomProgressBarDialog(this);
                    String url = "user/CheckStaffPermission/" + permissionId;
                    Log.e("URL", url);
                    ApiService<CheckStaffPermissionResponse> service = new ApiService<>();
                    service.get(this, ApiClient.getApiInterface().getCheckStaffPermission(Config.token, url), "CheckPermission");
                } else if (userTYpe.equals("Veterinarian")) {
                    Intent updateStaffIntent = new Intent(this, AddUpdateStaffActivity.class);
                    updateStaffIntent.putExtra("activityType", "Add");
                    updateStaffIntent.putExtra("staffId", encrypt_id);
//                    updateStaffIntent.putExtra("staffUserId", getAllStaffData.get(staffPostion).getUserId());
//                    updateStaffIntent.putExtra("staff_name", getAllStaffData.get(staffPostion).getFirstName() + " " + getAllStaffData.get(staffPostion).getLastName());
//                    updateStaffIntent.putExtra("staff_email", getAllStaffData.get(staffPostion).getEmail());
//                    updateStaffIntent.putExtra("staff_phone", getAllStaffData.get(staffPostion).getPhoneNumber());
//                    updateStaffIntent.putExtra("staff_degree", "");
//                    updateStaffIntent.putExtra("staff_reg_no", "");
//                    updateStaffIntent.putExtra("staff_image_url", getAllStaffData.get(staffPostion).getProfileImageUrl());
                    startActivityForResult(updateStaffIntent, ADD_STAFF_DEATILS);

                }

                break;


        }

    }

    private void getAllStaff() {
        ApiService<GetAllStaffResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getAllStaff(Config.token), "GetAllStaff");

    }

    private void updateStaff() {
        UpdateStaffParams updateStaffParams = new UpdateStaffParams();
        updateStaffParams.setEncryptedId(encrypt_id);
        updateStaffParams.setFirstName(first_name);
        updateStaffParams.setLastName(last_name);
        updateStaffParams.setEmail(email_id);
        updateStaffParams.setPhoneNumber(phone_number);
        UpdateStaffRequest updateStaffRequest = new UpdateStaffRequest();
        updateStaffRequest.setData(updateStaffParams);
        ApiService<GetUpdateStaffResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().updateStaff(Config.token, updateStaffRequest), "UpdateStaff");
        Log.d("updateStaff", updateStaffRequest.toString());

    }

    private void addStaff() {
        AddStaffParams addStaffParams = new AddStaffParams();
        addStaffParams.setFirstName(first_name);
        addStaffParams.setLastName(last_name);
        addStaffParams.setEmail(email_id);
        addStaffParams.setPhoneNumber(phone_number);
        addStaffParams.setPassword(password);
        addStaffParams.setConfirmPassword(confirm_password);
        AddStaffRequest addStaffRequest = new AddStaffRequest();
        addStaffRequest.setData(addStaffParams);
        ApiService<GetStaffDetailsResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().addNewStaff(Config.token, addStaffRequest), "AddStaff");
        Log.e("addstaff", addStaffRequest.toString());
    }

    @Override
    public void onResponse(Response response, String key) {
        Log.e("DATALOG", "=> " + response.body());

        switch (key) {
            case "CheckPermission":
                try {
                    methods.customProgressDismiss();
                    CheckStaffPermissionResponse checkStaffPermissionResponse = (CheckStaffPermissionResponse) response.body();
                    Log.d("GetPetList", checkStaffPermissionResponse.toString());
                    int responseCode = Integer.parseInt(checkStaffPermissionResponse.getResponse().getResponseCode());

                    if (responseCode == 109) {
                        if (checkStaffPermissionResponse.getData().equals("true")) {
                            if (permissionId.equals("4")) {
                                Intent addUpdateStaff = new Intent(this, AddUpdateStaffActivity.class);
                                addUpdateStaff.putExtra("activityType", "Add");
                                startActivityForResult(addUpdateStaff, ADD_STAFF_DEATILS);

                            } else if (permissionId.equals("5")) {
                                Intent updateStaffIntent = new Intent(this, AddUpdateStaffActivity.class);
                                updateStaffIntent.putExtra("activityType", "Update");
                                updateStaffIntent.putExtra("staffId", encrypt_id);
                                startActivityForResult(updateStaffIntent, ADD_STAFF_DEATILS);
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

            case "GetAllStaff":
                try {
                    Log.d("DATALOG", methods.getRequestJson(response.body().toString()));
                    GetAllStaffResponse getAllStaffResponse = (GetAllStaffResponse) response.body();
                    int responseCode = Integer.parseInt(getAllStaffResponse.getResponse().getResponseCode());

                    if (responseCode == 109) {
                        search_box_ET.setEnabled(true);
                        total_no_of_staff_TV.setText("You have total " + getAllStaffResponse.getData().size() + " staff registered");
                        total_no_of_staff_TV.setVisibility(View.VISIBLE);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                        all_staff_List_RV.setLayoutManager(linearLayoutManager);
                        allStaffAdapter = new AllStaffAdapter(this, getAllStaffResponse.getData(), this);
                        all_staff_List_RV.setAdapter(allStaffAdapter);
                        all_staff_List_RV.setVisibility(View.VISIBLE);
                        getAllStaffData = getAllStaffResponse.getData();
                        allStaffAdapter.notifyDataSetChanged();
                        mShimmerViewContainer.setVisibility(View.GONE);
                        mShimmerViewContainer.stopShimmerAnimation();
                    }

                } catch (Exception e) {


                    e.printStackTrace();
                }

                break;

            case "GetStaffStatus":
                try {
                    GetStaffStatusResponse getStaffStatusResponse = (GetStaffStatusResponse) response.body();
                    Log.d("DATALOG-1", getStaffStatusResponse.toString());
                    int responseCode = Integer.parseInt(getStaffStatusResponse.getResponse().getResponseCode());

                    if (responseCode == 109) {
                        Toast.makeText(this, "Status Changed Succesfully", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {


                    e.printStackTrace();
                }

                break;


            case "AddStaff":
                try {
                    GetStaffDetailsResponse getStaffDetailsResponse = (GetStaffDetailsResponse) response.body();
                    Log.d("DATALOG-1", getStaffDetailsResponse.toString());
                    int responseCode = Integer.parseInt(getStaffDetailsResponse.getResponse().getResponseCode());

                    if (responseCode == 109) {
                        getAllStaff();
                        mShimmerViewContainer.setVisibility(View.GONE);
                        all_staff_List_RV.setVisibility(View.VISIBLE);
                        Toast.makeText(this, "Staff Added Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        getAllStaff();
                        mShimmerViewContainer.setVisibility(View.GONE);
                        all_staff_List_RV.setVisibility(View.VISIBLE);
                        Toast.makeText(this, "Something Went Wrong !", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case "UpdateStaff":
                try {
                    GetUpdateStaffResponse getUpdateStaffResponse = (GetUpdateStaffResponse) response.body();
                    Log.d("DATALOG-2", getUpdateStaffResponse.toString());
                    int responseCode = Integer.parseInt(getUpdateStaffResponse.getResponse().getResponseCode());


                    if (responseCode == 109) {
                        getAllStaff();
                        mShimmerViewContainer.setVisibility(View.GONE);
                        all_staff_List_RV.setVisibility(View.VISIBLE);
                        Toast.makeText(this, "Staff Updated Successfully", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_STAFF_DEATILS) {
            if (resultCode == RESULT_OK) {
                getAllStaff();
            }
        }
        return;
    }


    @Override
    public void onError(Throwable t, String key) {
        Log.d("error", t.getLocalizedMessage());

    }

    @Override
    public void onResume() {
        super.onResume();
        if (Config.isUpdated=true){
            Snackbar snackbar = Snackbar.make(coordinatorLayout, "Loading......", Snackbar.LENGTH_LONG);
            snackbar.show();
            getAllStaff();
            Config.isUpdated = false;
        }
    }

    @Override
    public void onPause() {
//        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }
}
