package com.cynoteck.petofyOPHR.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.adapters.StaffPermissionListAdapter;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.assignAndRemovePermission.AssignRemovePermissionModel;
import com.cynoteck.petofyOPHR.params.assignAndRemovePermission.AssignRemovePermissionRequest;
import com.cynoteck.petofyOPHR.params.staffPermission.StaffPermissionModel;
import com.cynoteck.petofyOPHR.params.staffPermission.StaffPermissionRequest;
import com.cynoteck.petofyOPHR.response.staffPermissionListResponse.StaffPermissionResponse;
import com.cynoteck.petofyOPHR.response.staffPermissionListResponse.StaffPermissionResponseModel;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.cynoteck.petofyOPHR.utils.OperatingHoursClickListener;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class StaffPermissionActivity extends AppCompatActivity implements View.OnClickListener, ApiResponse, OperatingHoursClickListener {
    ShimmerFrameLayout shimmer_view_container;
    RecyclerView staff_permissionList;
    MaterialCardView back_arrow_CV;
    int pos;
    Methods methods;
    private String staffId = "";
    List<StaffPermissionResponseModel> staffPermissionResponseModels;
    StaffPermissionListAdapter staffPermissionListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_permission);

        initialize();
    }

    private void initialize() {
        Bundle extras = getIntent().getExtras();

        shimmer_view_container = findViewById(R.id.shimmer_view_container);
        staff_permissionList = findViewById(R.id.staff_permissionList);
        back_arrow_CV = findViewById(R.id.back_arrow_CV);
        back_arrow_CV.setOnClickListener(this);
        methods = new Methods(this);

        if (extras != null) {
            staffId = extras.getString("staffUserId");
        }

        StaffPermissionModel staffPermissionModel = new StaffPermissionModel();
        staffPermissionModel.setUserId(staffId);
        StaffPermissionRequest staffPermissionRequest = new StaffPermissionRequest();
        staffPermissionRequest.setData(staffPermissionModel);

        if (methods.isInternetOn())
            getStafPermission(staffPermissionRequest);
        else
            methods.DialogInternet();
    }

    private void getStafPermission(StaffPermissionRequest staffPermissionRequest) {
        shimmer_view_container.startShimmerAnimation();
        ApiService<StaffPermissionResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getStaffPermissionList(Config.token, staffPermissionRequest), "GetStaffPermissionList");
        Log.d("GetStaffPermissionList", "parameter" + staffPermissionRequest);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.back_arrow_CV:
                onBackPressed();
                break;
        }

    }

    private void assignPermission(AssignRemovePermissionRequest assignRemovePermissionRequest) {
        ApiService<StaffPermissionResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().assignPermission(Config.token, assignRemovePermissionRequest), "AssignPermission");
        Log.d("AssignPermission", "parameter" + assignRemovePermissionRequest);
    }

    private void removePermission(AssignRemovePermissionRequest assignRemovePermissionRequest) {
        ApiService<StaffPermissionResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().removePermission(Config.token, assignRemovePermissionRequest), "RemovePermission");
        Log.d("RemovePermission", "parameter" + assignRemovePermissionRequest);
    }


    @Override
    public void onResponse(Response arg0, String key) {
        switch (key) {
            case "GetStaffPermissionList":
                try {
                    StaffPermissionResponse staffPermissionResponse = (StaffPermissionResponse) arg0.body();
                    Log.d("GetStaffPermissionList", staffPermissionResponse.toString());
                    int responseCode = Integer.parseInt(staffPermissionResponse.getResponse().getResponseCode());

                    if (responseCode == 109) {
                        //Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                        staff_permissionList.setLayoutManager(linearLayoutManager);
                        staffPermissionListAdapter = new StaffPermissionListAdapter(this, staffPermissionResponse.getData(), this);
                        staff_permissionList.setAdapter(staffPermissionListAdapter);
                        staffPermissionResponseModels = staffPermissionResponse.getData();
                        staffPermissionListAdapter.notifyDataSetChanged();
                        shimmer_view_container.setVisibility(View.GONE);
                        shimmer_view_container.stopShimmerAnimation();
                    } else if (responseCode == 614) {
                        Toast.makeText(this, staffPermissionResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "AssignPermission":
                try {
                    StaffPermissionResponse staffPermissionResponse = (StaffPermissionResponse) arg0.body();
                    Log.d("AssignPermission", staffPermissionResponse.toString());
                    int responseCode = Integer.parseInt(staffPermissionResponse.getResponse().getResponseCode());

                    if (responseCode == 109) {
                        staffPermissionResponse.getData().get(pos).setIsSelected("true");
                        //Toast.makeText(this, "Assign Successfully", Toast.LENGTH_SHORT).show();
                    } else if (responseCode == 614) {
                        staffPermissionResponse.getData().get(pos).setIsSelected("false");
                        Toast.makeText(this, staffPermissionResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        staffPermissionResponse.getData().get(pos).setIsSelected("false");
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "RemovePermission":
                try {
                    StaffPermissionResponse staffPermissionResponse = (StaffPermissionResponse) arg0.body();
                    Log.d("RemovePermission", staffPermissionResponse.toString());
                    int responseCode = Integer.parseInt(staffPermissionResponse.getResponse().getResponseCode());

                    if (responseCode == 109) {
                        staffPermissionResponse.getData().get(pos).setIsSelected("false");
                        Toast.makeText(this, "Remove Successfully", Toast.LENGTH_SHORT).show();
                    } else if (responseCode == 614) {
                        staffPermissionResponse.getData().get(pos).setIsSelected("true");
                        Toast.makeText(this, staffPermissionResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        staffPermissionResponse.getData().get(pos).setIsSelected("true");

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

    }

    @Override
    public void onViewSetTime(int position, String id, String state) {
        pos = position;
        Log.d("annananan", "" + id + " " + state);
        if (state.equals("true")) {
            Log.d("call Asign", "");
            AssignRemovePermissionModel assignRemovePermissionModel = new AssignRemovePermissionModel();
            assignRemovePermissionModel.setUserId(staffId);
            assignRemovePermissionModel.setPermissionId(id.substring(0, id.length() - 2));
            AssignRemovePermissionRequest assignRemovePermissionRequest = new AssignRemovePermissionRequest();
            assignRemovePermissionRequest.setData(assignRemovePermissionModel);

            if (methods.isInternetOn())
                assignPermission(assignRemovePermissionRequest);
            else
                methods.isInternetOn();

        } else {
            Log.d("call remove", "");
            AssignRemovePermissionModel assignRemovePermissionModel = new AssignRemovePermissionModel();
            assignRemovePermissionModel.setUserId(staffId);
            assignRemovePermissionModel.setPermissionId(id.substring(0, id.length() - 2));
            AssignRemovePermissionRequest assignRemovePermissionRequest = new AssignRemovePermissionRequest();
            assignRemovePermissionRequest.setData(assignRemovePermissionModel);

            if (methods.isInternetOn())
                removePermission(assignRemovePermissionRequest);
            else
                methods.isInternetOn();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        StaffPermissionModel staffPermissionModel = new StaffPermissionModel();
        staffPermissionModel.setUserId(staffId);
        StaffPermissionRequest staffPermissionRequest = new StaffPermissionRequest();
        staffPermissionRequest.setData(staffPermissionModel);

        if (methods.isInternetOn())
            getStafPermission(staffPermissionRequest);
        else
            methods.DialogInternet();
    }
}