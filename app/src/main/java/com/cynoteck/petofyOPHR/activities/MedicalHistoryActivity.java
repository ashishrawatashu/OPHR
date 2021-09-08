package com.cynoteck.petofyOPHR.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.adapters.ReportsAdapter;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.petReportsRequest.PetDataParams;
import com.cynoteck.petofyOPHR.params.petReportsRequest.PetDataRequest;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.getPetListResponse.GetPetListResponse;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.getPetListResponse.PetList;
import com.cynoteck.petofyOPHR.response.loginRegisterResponse.UserPermissionMasterList;
import com.cynoteck.petofyOPHR.response.staffPermissionListResponse.CheckStaffPermissionResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.cynoteck.petofyOPHR.utils.RegisterRecyclerViewClickListener;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Response;


public class MedicalHistoryActivity extends AppCompatActivity implements ApiResponse, RegisterRecyclerViewClickListener, View.OnClickListener, TextWatcher {

    View view;
    Methods methods;
    RecyclerView petList_RV;
    ReportsAdapter reportsAdapter;
    ArrayList<PetList> profileList;
    private static ShimmerFrameLayout mShimmerViewContainer;
    TextView reports_headline_TV;
    AutoCompleteTextView search_box;
    ImageView empty_IV, search_IV,cancel_IV;
    MaterialCardView back_arrow_CV;
    RelativeLayout search_boxRL;
    NestedScrollView nestedScrollView;
    ProgressBar progressBar;
    int page = 1, pagelimit = 10;
    SharedPreferences sharedPreferences;
    String userTYpe = "", permissionId = "";
    int pos;
    RelativeLayout visit_register_RL, upcoming_visits_RL;
    BroadcastReceiver receiverMedicalHistory;
    static boolean checkNet=true;
    static LinearLayout something_wrong_FR;
    static NestedScrollView nested_scroll_view;
    Button retryBTN;

    public MedicalHistoryActivity() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_reports);
        receiverMedicalHistory=new checkIntetnetConnectivity();
//        if(Config.report==true)
//        {
//        }
            registerBroadcast();


        sharedPreferences = getSharedPreferences("userdetails", 0);
        something_wrong_FR=findViewById(R.id.something_wrong_FR);
        nested_scroll_view=(NestedScrollView) findViewById(R.id.nested_scroll_view);
        retryBTN=findViewById(R.id.retryBTN);

        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        upcoming_visits_RL = findViewById(R.id.upcoming_visits_RL);
        visit_register_RL = findViewById(R.id.visit_register_RL);
        petList_RV = findViewById(R.id.petList_RV);
        reports_headline_TV = findViewById(R.id.reports_headline_TV);
        search_box = findViewById(R.id.search_box);
        search_IV = findViewById(R.id.search_IV);
        cancel_IV=findViewById(R.id.cancel_IV);
        back_arrow_CV = findViewById(R.id.back_arrow_CV);
        search_boxRL = findViewById(R.id.search_boxRL);
        nestedScrollView = findViewById(R.id.nested_scroll_view);
        progressBar = findViewById(R.id.progressBar);
        empty_IV=findViewById(R.id.empty_IV);
        search_IV.setOnClickListener(this);
        back_arrow_CV.setOnClickListener(this);
        upcoming_visits_RL.setOnClickListener(this);
        visit_register_RL.setOnClickListener(this);
        search_box.addTextChangedListener(this);
        cancel_IV.setOnClickListener(this);
        retryBTN.setOnClickListener(this);


        methods = new Methods(this);
        search_IV.setEnabled(false);
        profileList = new ArrayList<>();
        getPet();
        if (methods.isInternetOn()) {
            getPetList(page, pagelimit);

        } else {

            methods.DialogInternet();
        }

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    page++;
                    progressBar.setVisibility(View.VISIBLE);
                    getPetList(page, pagelimit);
                }
            }
        });

    }

    private void getPetList(int page, int pageLimit) {
        PetDataParams getPetDataParams = new PetDataParams();
        getPetDataParams.setPageNumber(page);
        getPetDataParams.setPageSize(pageLimit);
        getPetDataParams.setSearch_Data("");
        PetDataRequest getPetDataRequest = new PetDataRequest();
        getPetDataRequest.setData(getPetDataParams);

        ApiService<GetPetListResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getPetList(Config.token, getPetDataRequest), "GetPetList");
        Log.e("DATALOG", "check1=> " + methods.getRequestJson(getPetDataRequest));


    }

    private void getPet() {
        search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d("dataChange", "afterTextChanged" + new String(editable.toString()));
                String value = editable.toString();
                petSearchDependsOnPrefix(value);
            }
        });


        search_box.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = search_box.getText().toString();
                String[] city_array = value.split("\\(");

                search_box.setText(city_array[0]);
            }
        });

    }

    private void petSearchDependsOnPrefix(String prefix) {
        PetDataParams getPetDataParams = new PetDataParams();
        getPetDataParams.setPageNumber(0);//0
        getPetDataParams.setPageSize(10);//0
        getPetDataParams.setSearch_Data(prefix);
        PetDataRequest getPetDataRequest = new PetDataRequest();
        getPetDataRequest.setData(getPetDataParams);

        ApiService<GetPetListResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getPetList(Config.token, getPetDataRequest), "GetPetListBySearch");
        Log.e("DATALOG", "check1=> " + getPetDataRequest);
    }

    @Override
    public void onResponse(Response response, String key) {
        Log.e("DATALOG", "=> " + response.body());

        switch (key) {
            case "GetPetList":
                try {
                    mShimmerViewContainer.setVisibility(View.GONE);
                    mShimmerViewContainer.stopShimmerAnimation();
                    GetPetListResponse getPetListResponse = (GetPetListResponse) response.body();
                    Log.d("DATALOG", getPetListResponse.toString());
                    int responseCode = Integer.parseInt(getPetListResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        if (getPetListResponse.getData().getPetList().isEmpty()){
                            empty_IV.setVisibility(View.VISIBLE);
                        }else {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                            petList_RV.setLayoutManager(linearLayoutManager);
                            if (getPetListResponse.getData().getPetList().size() > 0) {
                                for (int i = 0; i < getPetListResponse.getData().getPetList().size(); i++) {
                                    PetList petList = new PetList();
                                    petList.setPetUniqueId(getPetListResponse.getData().getPetList().get(i).getPetUniqueId());
                                    petList.setDateOfBirth(getPetListResponse.getData().getPetList().get(i).getDateOfBirth());
                                    petList.setPetName(getPetListResponse.getData().getPetList().get(i).getPetName());
                                    petList.setPetSex(getPetListResponse.getData().getPetList().get(i).getPetSex());
                                    petList.setPetParentName(getPetListResponse.getData().getPetList().get(i).getPetParentName());
                                    petList.setPetProfileImageUrl(getPetListResponse.getData().getPetList().get(i).getPetProfileImageUrl());
                                    petList.setEncryptedId(getPetListResponse.getData().getPetList().get(i).getEncryptedId());
                                    petList.setId(getPetListResponse.getData().getPetList().get(i).getId());
                                    petList.setPetAge(getPetListResponse.getData().getPetList().get(i).getPetAge());
                                    petList.setPetCategoryId(getPetListResponse.getData().getPetList().get(i).getPetCategoryId());
                                    petList.setLastVisitEncryptedId(getPetListResponse.getData().getPetList().get(i).getLastVisitEncryptedId());
                                    profileList.add(petList);
                                }
                                progressBar.setVisibility(View.GONE);
                                reportsAdapter = new ReportsAdapter(this, profileList, this);
                                petList_RV.setAdapter(reportsAdapter);
                                reportsAdapter.notifyDataSetChanged();
                                search_IV.setEnabled(true);
                            }
                        }


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

            case "GetPetListBySearch":
                try {
                    GetPetListResponse getPetListResponse = (GetPetListResponse) response.body();
                    Log.d("GetPetListBySearch", getPetListResponse.toString());
                    int responseCode = Integer.parseInt(getPetListResponse.getResponse().getResponseCode());

                    if (responseCode == 109) {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                        petList_RV.setLayoutManager(linearLayoutManager);
                        if (getPetListResponse.getData().getPetList().size() > 0) {
                            profileList.clear();
                            for (int i = 0; i < getPetListResponse.getData().getPetList().size(); i++) {
                                PetList petList = new PetList();
                                petList.setPetUniqueId(getPetListResponse.getData().getPetList().get(i).getPetUniqueId());
                                petList.setDateOfBirth(getPetListResponse.getData().getPetList().get(i).getDateOfBirth());
                                petList.setPetName(getPetListResponse.getData().getPetList().get(i).getPetName());
                                petList.setPetSex(getPetListResponse.getData().getPetList().get(i).getPetSex());
                                petList.setPetParentName(getPetListResponse.getData().getPetList().get(i).getPetParentName());
                                petList.setPetProfileImageUrl(getPetListResponse.getData().getPetList().get(i).getPetProfileImageUrl());
                                petList.setEncryptedId(getPetListResponse.getData().getPetList().get(i).getEncryptedId());
                                petList.setId(getPetListResponse.getData().getPetList().get(i).getId());
                                petList.setPetAge(getPetListResponse.getData().getPetList().get(i).getPetAge());
                                petList.setPetCategoryId(getPetListResponse.getData().getPetList().get(i).getPetCategoryId());
                                petList.setLastVisitEncryptedId(getPetListResponse.getData().getPetList().get(i).getLastVisitEncryptedId());
                                Log.e("LAST_VISIT", getPetListResponse.getData().getPetList().get(i).getLastVisitEncryptedId());

                                profileList.add(petList);
                            }
                            progressBar.setVisibility(View.GONE);
                            reportsAdapter = new ReportsAdapter(this, profileList, this);
                            petList_RV.setAdapter(reportsAdapter);
                            reportsAdapter.notifyDataSetChanged();
                            mShimmerViewContainer.setVisibility(View.GONE);
                            mShimmerViewContainer.stopShimmerAnimation();
                        } else {
                            progressBar.setVisibility(View.GONE);
//                            Toast.makeText(this, "Data Not found", Toast.LENGTH_SHORT).show();
                        }


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "CheckPermission":
                try {
                    methods.customProgressDismiss();
                    CheckStaffPermissionResponse checkStaffPermissionResponse = (CheckStaffPermissionResponse) response.body();
                    Log.d("GetPetList", checkStaffPermissionResponse.toString());
                    int responseCode = Integer.parseInt(checkStaffPermissionResponse.getResponse().getResponseCode());

                    if (responseCode == 109) {
                        if (checkStaffPermissionResponse.getData().equals("true")) {
                            if (permissionId.equals("9")) {
                                profileList.get(pos).getPetUniqueId();
                                Intent selectReportsIntent = new Intent(this.getApplication(), SelectPetReportsActivity.class);
                                Bundle data = new Bundle();
                                data.putString("pet_id", profileList.get(pos).getId());
                                data.putString("pet_name", profileList.get(pos).getPetName());
                                data.putString("pet_unique_id", profileList.get(pos).getPetUniqueId());
                                data.putString("pet_sex", profileList.get(pos).getPetSex());
                                data.putString("pet_owner_name", profileList.get(pos).getPetParentName());
                                data.putString("pet_owner_contact", profileList.get(pos).getContactNumber());
                                data.putString("pet_DOB", profileList.get(pos).getDateOfBirth());
                                data.putString("pet_encrypted_id", profileList.get(pos).getEncryptedId());
                                data.putString("pet_age", profileList.get(pos).getPetAge());
                                data.putString("pet_image_url", profileList.get(pos).getPetProfileImageUrl());
                                selectReportsIntent.putExtras(data);
                                startActivity(selectReportsIntent);
                                this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                                clearSearch();
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
        }
    }

    @Override
    public void onError(Throwable t, String key) {

        Log.d("error", t.getLocalizedMessage());

    }

    @Override
    public void onProductClick(int position) {
        pos = position;
        userTYpe = sharedPreferences.getString("user_type", "");
        if (userTYpe.equals("Vet Staff")) {
            Gson gson = new Gson();
            String json = sharedPreferences.getString("userPermission", null);
            Type type = new TypeToken<ArrayList<UserPermissionMasterList>>() {
            }.getType();
            ArrayList<UserPermissionMasterList> arrayList = gson.fromJson(json, type);
            Log.e("ArrayList", arrayList.toString());
            Log.d("UserType", userTYpe);
            permissionId = "9";
            methods.showCustomProgressBarDialog(this);
            String url = "user/CheckStaffPermission/" + permissionId;
            Log.e("URL", url);
            ApiService<CheckStaffPermissionResponse> service = new ApiService<>();
            service.get(this, ApiClient.getApiInterface().getCheckStaffPermission(Config.token, url), "CheckPermission");
        } else if (userTYpe.equals("Veterinarian")) {
            profileList.get(position).getPetUniqueId();
            Intent selectReportsIntent = new Intent(this.getApplication(), SelectPetReportsActivity.class);
            Bundle data = new Bundle();
//        Toast.makeText(this, ""+profileList.get(position).getId(), Toast.LENGTH_SHORT).show();
            data.putString("pet_id", profileList.get(position).getId().substring(0, profileList.get(position).getId().length() - 2));
            data.putString("pet_name", profileList.get(position).getPetName());
            data.putString("pet_unique_id", profileList.get(position).getPetUniqueId());
            data.putString("pet_sex", profileList.get(position).getPetSex());
            data.putString("pet_owner_name", profileList.get(position).getPetParentName());
            data.putString("pet_owner_contact", profileList.get(position).getContactNumber());
            data.putString("pet_DOB", profileList.get(position).getDateOfBirth());
            data.putString("pet_encrypted_id", profileList.get(position).getEncryptedId());
            data.putString("pet_image_url", profileList.get(position).getPetProfileImageUrl());

            selectReportsIntent.putExtras(data);
            startActivity(selectReportsIntent);
            this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            clearSearch();
        }


    }

    private void clearSearch() {
        search_box.getText().clear();
        search_boxRL.setVisibility(View.GONE);
        reports_headline_TV.setVisibility(View.VISIBLE);
        InputMethodManager imm1 = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm1.hideSoftInputFromWindow(search_box.getWindowToken(), 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();

    }

    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_box:

                break;

            case R.id.upcoming_visits_RL:
                startActivity(new Intent(this, UpcomingVisitsActivity.class));
                break;

            case R.id.visit_register_RL:
                startActivity(new Intent(this, AllVisitsActivity.class));
                break;

            case R.id.back_arrow_CV:
                onBackPressed();
                break;

            case R.id.search_IV:
                search_IV.setVisibility(View.GONE);
                search_boxRL.setVisibility(View.VISIBLE);
                cancel_IV.setVisibility(View.VISIBLE);
                search_box.requestFocus();
                InputMethodManager imm1 = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm1.showSoftInput(search_box, InputMethodManager.SHOW_FORCED);
                reports_headline_TV.setVisibility(View.GONE);
                break;

            case R.id.cancel_IV:
                search_box.getText().clear();
                search_boxRL.setVisibility(View.GONE);
                cancel_IV.setVisibility(View.GONE);
                reports_headline_TV.setVisibility(View.VISIBLE);
                search_IV.setVisibility(View.VISIBLE);
                search_box.clearFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0); // hide
                break;


            case R.id.retryBTN:
                if(checkNet)
                {
                    something_wrong_FR.setVisibility(View.GONE);
                    nested_scroll_view.setVisibility(View.VISIBLE);
                    mShimmerViewContainer.setVisibility(View.VISIBLE);
                    mShimmerViewContainer.startShimmerAnimation();
                     getPetList(page, pagelimit);

                }
                else{
                    show();
                }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        // reportsAdapter.getFilter().filter(s.toString());

    }

    @Override
    public void afterTextChanged(Editable s) {

    }




    protected void registerBroadcast() {
//        registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(receiverMedicalHistory, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(receiverMedicalHistory, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(receiverMedicalHistory);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        Config.report=false;
    }


    public  static void isConn(boolean value) {
        if (value) {
            checkNet=true;
            Log.e("Connected", "Yes ");
        }
        else {
            checkNet=false;
            show();
        }
    }

    private static void show() {
        something_wrong_FR.setVisibility(View.VISIBLE);
        nested_scroll_view.setVisibility(View.GONE);
        mShimmerViewContainer.setVisibility(View.GONE);
        mShimmerViewContainer.stopShimmerAnimation();

    }

}
