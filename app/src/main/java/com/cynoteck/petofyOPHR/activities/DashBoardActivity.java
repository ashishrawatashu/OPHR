package com.cynoteck.petofyOPHR.activities;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.GoalRow;
import androidx.fragment.app.FragmentTransaction;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.fragments.VetAppointmentsFragment;
import com.cynoteck.petofyOPHR.fragments.HomeFragment;
import com.cynoteck.petofyOPHR.fragments.PetRegisterFragment;
import com.cynoteck.petofyOPHR.fragments.ProfileFragment;
import com.cynoteck.petofyOPHR.response.loginRegisterResponse.UserPermissionMasterList;
import com.cynoteck.petofyOPHR.response.staffPermissionListResponse.CheckStaffPermissionResponse;
import com.cynoteck.petofyOPHR.response.totalStaffPetsAppointment.GetDashboardCountsResponse;
import com.cynoteck.petofyOPHR.response.updateProfileResponse.UserResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.cynoteck.petofyOPHR.utils.VetDetailsSingleton;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class DashBoardActivity extends AppCompatActivity implements View.OnClickListener, ApiResponse {

    String currentVersion, latestVersion;
    Dialog dialog;
    private RelativeLayout homeRL, profileRL, petregisterRL, appointmentRL;
    public ImageView icHome, icProfile, icPetRegister, icAppointment;
    boolean doubleBackToExitPressedOnce = false;
    boolean exit = false;
    Methods methods;
    String IsVeterinarian = "";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor login_editor;
    private int USER_UPDATION_FIRST_TIME = 1;
    String userTYpe = "", permissionId = "";
    BroadcastReceiver broadcastReceiver;
    FragmentTransaction ft;
    static FrameLayout content_frame;
    static boolean checkNet=true;
    static LinearLayout something_wrong_LL;
    Button rtry;
    HomeFragment homeFragment = new HomeFragment();
    BottomSheetDialog updateDialog;
    Dialog  settingDialog,storageDialog;
    boolean  dialogStatus = false;
    Button open_setting_BT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        init();
        settingDialogInit();
        getDataFromSharedPreferences();
//        storagePermissionDialogInit();
//        requestMultiplePermissions();
        Log.d("CHECK","ONCREATE");
        methods = new Methods(this);
        broadcastReceiver =new checkIntetnetConnectivity();
        registerBroadcast();
        getCurrentVersion();
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        requestMultiplePermissions();


        if (Config.user_type.equals("Veterinarian")) {
            if (methods.isInternetOn()) {
                getUserDetails();
            } else {
                methods.DialogInternet();

            }
        }


        if (savedInstanceState == null) {
            ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.content_frame, homeFragment);
            ft.commit();

        }
    }

    private void getDataFromSharedPreferences() {
        Config.tabPosition = 1;
        sharedPreferences = getSharedPreferences("userdetails", 0);
        Config.user_Veterian_url = sharedPreferences.getString("profilePic", "");
        Config.token = sharedPreferences.getString("token", "");
        Config.user_id = sharedPreferences.getString("userId", "");
        Config.user_Veterian_phone = sharedPreferences.getString("phoneNumber", "");
        Config.user_Veterian_emial = sharedPreferences.getString("email", "");
        Config.user_Veterian_name = sharedPreferences.getString("firstName", "") + " " + sharedPreferences.getString("lastName", "");
        Config.user_Veterian_address = sharedPreferences.getString("address", "");
        Config.user_Veterian_online = sharedPreferences.getString("onlineAppoint", "");
        Config.user_Veterian_id = sharedPreferences.getString("vetid", "");
        Config.user_Veterian_study = sharedPreferences.getString("study", "");
        Config.two_fact_auth_status = sharedPreferences.getString("twoFactAuth", "");
        Config.user_type = sharedPreferences.getString("user_type", "");
        Config.user_verterian_reg_no = sharedPreferences.getString("vetid", "");
        Config.vet_first_name = sharedPreferences.getString("first_name", "");
        Config.vet_last_name = sharedPreferences.getString("last_name", "");
        Config.onlineConsultationCharges = sharedPreferences.getString("vet_charges", "");
        Log.d("TOKEN",Config.token);
    }

    private void settingDialogInit() {

    }

    private void getCurrentVersion() {
        PackageManager pm = this.getPackageManager();
        PackageInfo pInfo = null;

        try {
            pInfo = pm.getPackageInfo(this.getPackageName(), 0);

        } catch (PackageManager.NameNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        currentVersion = pInfo.versionName;
        //currentVersion="1.0.2";
        Log.d("currentVersion", currentVersion);
        new GetLatestVersion().execute();

    }

    protected void registerBroadcast() {
//        registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }


    public  static void isConnected(boolean value) {
        if (value) {
            checkNet=true;
//            Log.e("Connected", "Yes ");
        }
        else {
            checkNet=false;
            show();
        }
    }

    private static void show() {

        something_wrong_LL.setVisibility(View.VISIBLE);
        content_frame.setVisibility(View.GONE);

    }
    private void requestMultiplePermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        android.Manifest.permission.CAMERA,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissionos are granted
                        if (report.areAllPermissionsGranted()) {
                            Log.d("STORAGE_DIALOG","All permissions are granted by user!");
//                            try {
//                                settingDialog.dismiss();
//                            }catch (Exception e){
//                                e.printStackTrace();
//                            }
                        }else {
                            startActivity(new Intent(DashBoardActivity.this,PermissionCheckActivity.class));
//                            storagePermissionDialog();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            startActivity(new Intent(DashBoardActivity.this,PermissionCheckActivity.class));

//                            // show alert dialog navigating to Settings
//                            openSettingsDialog();
                            Log.d("STORAGE_DIALOG","openSettingsDialog");

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
                        Toast.makeText(DashBoardActivity.this, "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    private void openSettingsDialog() {
        dialogStatus  = true;
        settingDialog  = new Dialog(this);
        settingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        settingDialog.setCancelable(false);
        settingDialog.setContentView(R.layout.open_setting_dialog);
        settingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        open_setting_BT = settingDialog.findViewById(R.id.open_setting_BT);
        open_setting_BT.setOnClickListener(this);
        settingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        settingDialog.show();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = settingDialog.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
    }

    private void storagePermissionDialog() {
    }


    // -----------------------------------------------------------------------------------------------------------
    private class GetLatestVersion extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            try {
//It retrieves the latest version by scraping the content of current version from play store at runtime
                Document doc = Jsoup.connect("https://play.google.com/store/apps/details?id=com.cynoteck.petofyOPHR").get();
                latestVersion = doc.getElementsByClass("htlgb").get(6).text();

                //latestVersion = "1.0.1";
                Log.d("latestVersion", latestVersion);

            } catch (Exception e) {
                e.printStackTrace();

            }
            return new JSONObject();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            if (latestVersion != null) {
                if (!currentVersion.equalsIgnoreCase(latestVersion)) {
                    if (!isFinishing()) { //This would help to prevent Error : BinderProxy@45d459c0 is not valid; is your activity running? error
                        newUpdateDialog();
                    }
                }
            }
        }

    }

    private void newUpdateDialog() {
        updateDialog  = new BottomSheetDialog(this);
        updateDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        updateDialog.setCancelable(false);
        updateDialog.setContentView(R.layout.update_new_version_dialog);
        updateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Button download_BT = updateDialog.findViewById(R.id.download_BT);
        updateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        download_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent;
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse("market://details?id=" + "com.cynoteck.petofyOPHR"));
                    startActivity(intent);
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + "com.cynoteck.petofyOPHR")));
                }
            }
        });

        updateDialog.show();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = updateDialog.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
    }

    private void init() {
        homeRL = findViewById(R.id.homeRL);
        profileRL = findViewById(R.id.profileRL);
        petregisterRL = findViewById(R.id.petRegisterRL);
        appointmentRL = findViewById(R.id.appointmentRL);
        content_frame=findViewById(R.id.content_frame);
        icHome = findViewById(R.id.icHome);
        icProfile = findViewById(R.id.icProfile);
        icPetRegister = findViewById(R.id.icPetRegister);
        icAppointment = findViewById(R.id.icAppointment);
        something_wrong_LL=findViewById(R.id.something_wrong_LL);
        rtry=findViewById(R.id.retry_BT);

        homeRL.setOnClickListener(this);
        profileRL.setOnClickListener(this);
        petregisterRL.setOnClickListener(this);
        appointmentRL.setOnClickListener(this);
        rtry.setOnClickListener(this);
    }


    public void getUserDetails() {
        methods.showCustomProgressBarDialog(this);
        ApiService<UserResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getUserDetailsApi(Config.token), "GetUserDetails");

    }

    @Override
    public void onResponse(Response response, String key) {
        switch (key) {
            case "GetUserDetails":
                try {
                    methods.customProgressDismiss();
//                    ft.add(R.id.content_frame, homeFragment);
//                    ft.commit();
                    Log.d("GetUserDetails", response.body().toString());
                    UserResponse userResponse = (UserResponse) response.body();
                    int responseCode = Integer.parseInt(userResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        VetDetailsSingleton.getInstance().userResponse = (UserResponse) response.body();
                        icHome.setImageResource(R.drawable.home_active);
                        login_editor = sharedPreferences.edit();
                        login_editor.putString("profilePic", userResponse.getData().getProfileImageUrl());
                        login_editor.putString("vet_charges", userResponse.getData().getOnlineConsultationCharges());
                        login_editor.commit();
                        Config.user_Veterian_url = sharedPreferences.getString("profilePic", "");
                        Config.onlineConsultationCharges = sharedPreferences.getString("vet_charges", "");
                        //Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                        IsVeterinarian = userResponse.getData().getIsVeterinarian();
                        Log.d("IsVeterinarian", "" + userResponse.getData().getIsVeterinarian());
                        if (IsVeterinarian.equals("false")) {
                            Intent intent = new Intent(DashBoardActivity.this, UpdateProfileActivity.class);
                            intent.putExtra("activityName", "Update");
                            intent.putExtra("id", userResponse.getData().getId());
                            intent.putExtra("isVeterinarian", userResponse.getData().getIsVeterinarian());
                            intent.putExtra("isActive", userResponse.getData().getIsActive());
                            intent.putExtra("password", userResponse.getData().getPassword());
                            intent.putExtra("firstName", userResponse.getData().getFirstName());
                            intent.putExtra("lastName", userResponse.getData().getLastName());
                            intent.putExtra("email", userResponse.getData().getEmail());
                            intent.putExtra("phone", userResponse.getData().getPhone());
                            intent.putExtra("address", userResponse.getData().getAddress());
                            intent.putExtra("country", userResponse.getData().getCountryName());
                            intent.putExtra("state", userResponse.getData().getStateName());
                            intent.putExtra("city", userResponse.getData().getCityName());
                            intent.putExtra("pincode", userResponse.getData().getPostalCode());
                            intent.putExtra("onlineConsultationCharges", userResponse.getData().getOnlineConsultationCharges());
                            intent.putExtra("website", userResponse.getData().getWebsite());
                            intent.putExtra("clinicCode", userResponse.getData().getClinicCode());
                            intent.putExtra("socialMedia", userResponse.getData().getSocialMediaUrl());
                            intent.putExtra("vetRegNo", userResponse.getData().getVetRegistrationNumber());
                            intent.putExtra("vetStudy", userResponse.getData().getVetQualifications());
                            intent.putExtra("category", userResponse.getData().getCategories());
                            intent.putExtra("service", userResponse.getData().getServices());
                            intent.putExtra("serviceImage1", userResponse.getData().getFirstServiceImageUrl());
                            intent.putExtra("serviceImage2", userResponse.getData().getSecondServiceImageUrl());
                            intent.putExtra("serviceImage3", userResponse.getData().getThirdServiceImageUrl());
                            intent.putExtra("serviceImage4", userResponse.getData().getFourthServiceImageUrl());
                            intent.putExtra("serviceImage5", userResponse.getData().getFirstServiceImageUrl());
                            startActivityForResult(intent, USER_UPDATION_FIRST_TIME);
                            finish();
                        }
                    } else if (responseCode == 614) {
                        Toast.makeText(this, userResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
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
                                icHome.setImageResource(R.drawable.home_inactive);
                                icProfile.setImageResource(R.drawable.profile_inactive);
                                icPetRegister.setImageResource(R.drawable.pet_active);
                                icAppointment.setImageResource(R.drawable.appointment_inactive);
                                PetRegisterFragment petRegisterFragment = new PetRegisterFragment();
                                FragmentTransaction ftPetRegister = getSupportFragmentManager().beginTransaction();
                                ftPetRegister.replace(R.id.content_frame, petRegisterFragment);
                                ftPetRegister.commit();
                            } else if (permissionId.equals("16")) {
                                icHome.setImageResource(R.drawable.home_inactive);
                                icProfile.setImageResource(R.drawable.profile_inactive);
                                icPetRegister.setImageResource(R.drawable.pet_inactive);
                                icAppointment.setImageResource(R.drawable.appointment_active);
                                VetAppointmentsFragment VetAppointmentsFragment = new VetAppointmentsFragment();
                                FragmentTransaction ftAppointment = getSupportFragmentManager().beginTransaction();
                                ftAppointment.replace(R.id.content_frame, VetAppointmentsFragment);
                                ftAppointment.commit();
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == USER_UPDATION_FIRST_TIME) {
            if (resultCode == RESULT_OK) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("Your Profile is under review.");
                builder1.setMessage("You should hear back within 24 hours.\nThank You..");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "Log Out",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SharedPreferences preferences = getSharedPreferences("userdetails", 0);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.clear();
                                editor.apply();
                                startActivity(new Intent(DashBoardActivity.this, LoginActivity.class));
                                finish();
                            }
                        });


                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        }
    }

    @Override
    public void onError(Throwable t, String key) {
        Log.e("error", t.getMessage());
        Log.e("errrrr", t.getLocalizedMessage());
        methods.customProgressDismiss();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Config.tabPosition == 1) {
            icHome.setImageResource(R.drawable.home_active);
            icProfile.setImageResource(R.drawable.profile_inactive);
            icPetRegister.setImageResource(R.drawable.pet_inactive);
            icAppointment.setImageResource(R.drawable.appointment_inactive);
        } else if (Config.tabPosition == 2) {
            icHome.setImageResource(R.drawable.home_inactive);
            icProfile.setImageResource(R.drawable.profile_inactive);
            icPetRegister.setImageResource(R.drawable.pet_active);
            icAppointment.setImageResource(R.drawable.appointment_inactive);
        } else if (Config.tabPosition == 3) {
            icHome.setImageResource(R.drawable.home_inactive);
            icProfile.setImageResource(R.drawable.profile_inactive);
            icPetRegister.setImageResource(R.drawable.pet_inactive);
            icAppointment.setImageResource(R.drawable.appointment_active);
        } else if (Config.tabPosition == 4) {
            icHome.setImageResource(R.drawable.home_inactive);
            icProfile.setImageResource(R.drawable.profile_active);
            icPetRegister.setImageResource(R.drawable.pet_inactive);
            icAppointment.setImageResource(R.drawable.appointment_inactive);
        }
//        Log.e("OnResume", "onResume function is called : ");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeRL:
                Config.count = 1;
                Config.tabPosition = 1;
                icHome.setImageResource(R.drawable.home_active);
                icProfile.setImageResource(R.drawable.profile_inactive);
                icPetRegister.setImageResource(R.drawable.pet_inactive);
                icAppointment.setImageResource(R.drawable.appointment_inactive);
                HomeFragment homeFragment = new HomeFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, homeFragment);
                ft.commit();
                break;

            case R.id.profileRL:
                Config.count = 0;
                Config.tabPosition = 4;
                icHome.setImageResource(R.drawable.home_inactive);
                icProfile.setImageResource(R.drawable.profile_active);
                icPetRegister.setImageResource(R.drawable.pet_inactive);
                icAppointment.setImageResource(R.drawable.appointment_inactive);
                ProfileFragment profileFragment = new ProfileFragment();
                FragmentTransaction ftProfile = getSupportFragmentManager().beginTransaction();
                ftProfile.replace(R.id.content_frame, profileFragment);
                ftProfile.commit();
                break;

            case R.id.petRegisterRL:
                Config.count = 0;
                Config.tabPosition = 2;
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
                    icHome.setImageResource(R.drawable.home_inactive);
                    icProfile.setImageResource(R.drawable.profile_inactive);
                    icPetRegister.setImageResource(R.drawable.pet_active);
                    icAppointment.setImageResource(R.drawable.appointment_inactive);
                    PetRegisterFragment petRegisterFragment = new PetRegisterFragment();
                    FragmentTransaction ftPetRegister = getSupportFragmentManager().beginTransaction();
                    ftPetRegister.replace(R.id.content_frame, petRegisterFragment);
                    ftPetRegister.commit();
                }
                break;

            case R.id.appointmentRL:
                Config.count = 0;
                Config.tabPosition = 3;
                userTYpe = sharedPreferences.getString("user_type", "");
                if (userTYpe.equals("Vet Staff")) {
                    Gson gson = new Gson();
                    String json = sharedPreferences.getString("userPermission", null);
                    Type type = new TypeToken<ArrayList<UserPermissionMasterList>>() {
                    }.getType();
                    ArrayList<UserPermissionMasterList> arrayList = gson.fromJson(json, type);
                    Log.e("ArrayList", arrayList.toString());
                    Log.d("UserType", userTYpe);
                    permissionId = "16";
                    methods.showCustomProgressBarDialog(this);
                    String url = "user/CheckStaffPermission/" + permissionId;
                    Log.e("URL", url);
                    ApiService<CheckStaffPermissionResponse> service = new ApiService<>();
                    service.get(this, ApiClient.getApiInterface().getCheckStaffPermission(Config.token, url), "CheckPermission");
                } else if (userTYpe.equals("Veterinarian")) {
                    icHome.setImageResource(R.drawable.home_inactive);
                    icProfile.setImageResource(R.drawable.profile_inactive);
                    icPetRegister.setImageResource(R.drawable.pet_inactive);
                    icAppointment.setImageResource(R.drawable.appointment_active);
                    VetAppointmentsFragment VetAppointmentsFragment = new VetAppointmentsFragment();
                    FragmentTransaction ftAppointment = getSupportFragmentManager().beginTransaction();
                    ftAppointment.replace(R.id.content_frame, VetAppointmentsFragment);
                    ftAppointment.commit();
                }

                break;

            case R.id.retry_BT:
                if(checkNet)
                {
                    something_wrong_LL.setVisibility(View.GONE);
                    content_frame.setVisibility(View.VISIBLE);
//                    getUserDetails();
//                    Toast.makeText(getApplicationContext(), ""+Config.tabPosition, Toast.LENGTH_SHORT).show();
                        if(Config.tabPosition==3)
                             {
                                userTYpe = sharedPreferences.getString("user_type", "");
                                if (userTYpe.equals("Vet Staff"))
                                {
                                    Gson gson = new Gson();
                                    String json = sharedPreferences.getString("userPermission", null);
                                    Type type = new TypeToken<ArrayList<UserPermissionMasterList>>() {
                                    }.getType();
                                    ArrayList<UserPermissionMasterList> arrayList = gson.fromJson(json, type);
                                    Log.e("ArrayList", arrayList.toString());
                                    Log.d("UserType", userTYpe);
                                    permissionId = "16";
                                    methods.showCustomProgressBarDialog(this);
                                    String url = "user/CheckStaffPermission/" + permissionId;
                                    Log.e("URL", url);
                                    ApiService<CheckStaffPermissionResponse> service = new ApiService<>();
                                    service.get(this, ApiClient.getApiInterface().getCheckStaffPermission(Config.token, url), "CheckPermission");
                                }else if (userTYpe.equals("Veterinarian")) {
                                    icHome.setImageResource(R.drawable.home_inactive);
                                    icProfile.setImageResource(R.drawable.profile_inactive);
                                    icPetRegister.setImageResource(R.drawable.pet_inactive);
                                    icAppointment.setImageResource(R.drawable.appointment_active);
                                    VetAppointmentsFragment VetAppointmentsFragment = new VetAppointmentsFragment();
                                    FragmentTransaction ftAppointment = getSupportFragmentManager().beginTransaction();
                                    ftAppointment.replace(R.id.content_frame, VetAppointmentsFragment);
                                    ftAppointment.commit();
                                }
                             }

                  else if(Config.tabPosition==2)
                    {
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
                            icHome.setImageResource(R.drawable.home_inactive);
                            icProfile.setImageResource(R.drawable.profile_inactive);
                            icPetRegister.setImageResource(R.drawable.pet_active);
                            icAppointment.setImageResource(R.drawable.appointment_inactive);
                            PetRegisterFragment petRegisterFragment = new PetRegisterFragment();
                            FragmentTransaction ftPetRegister = getSupportFragmentManager().beginTransaction();
                            ftPetRegister.replace(R.id.content_frame, petRegisterFragment);
                            ftPetRegister.commit();
                        }

                    }

                }
                else
                {
                    methods.customProgressDismiss();
                    show();
                }
                break;

            case R.id.open_setting_BT:
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);

                break;

        }

    }

    @Override
    protected void onPause() {
        super.onPause();
//        if (Config.user_type.equals("Veterinarian")) {
//            if (methods.isInternetOn()) {
//                getUserDetails();
//            } else {
//                methods.DialogInternet();
//
//            }
//        }
//
//
//        if (savedInstanceState == null) {
//            ft = getSupportFragmentManager().beginTransaction();
//
//        }
//        Toast.makeText(this,"this is onpausecalled",Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        Log.e("count", String.valueOf(Config.count));
        Log.e("exit", String.valueOf(exit));
        if (Config.count == 1) {
            if (exit) {
                super.onBackPressed();
                finishAffinity();
//                System.exit(0);
                return;
            } else {
                Toast.makeText(this, "Press Back again to Exit.", Toast.LENGTH_SHORT).show();
                exit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exit = false;
                    }
                }, 2000);
            }
        } else {
            Config.count = 1;
            Config.tabPosition = 1;
            HomeFragment homeFragment = new HomeFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, homeFragment);
            ft.commit();
            getSupportFragmentManager().popBackStack();
            icHome.setImageResource(R.drawable.home_active);
            icProfile.setImageResource(R.drawable.profile_inactive);
            icPetRegister.setImageResource(R.drawable.pet_inactive);
            icAppointment.setImageResource(R.drawable.appointment_inactive);
        }
    }

}
