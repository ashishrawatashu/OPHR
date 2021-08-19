package com.cynoteck.petofyOPHR.activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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
import com.cynoteck.petofyOPHR.response.updateProfileResponse.UserResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.lang.reflect.Type;
import java.util.ArrayList;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        init();
        methods = new Methods(this);
        getCurrentVersion();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Config.tabPosition = 1;
        sharedPreferences = getSharedPreferences("userdetails", 0);
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


        if (Config.user_type.equals("Veterinarian")) {
            if (methods.isInternetOn()) {
                getUserDetails();
            } else {
                methods.DialogInternet();
            }
        }

        if (savedInstanceState == null) {
            HomeFragment homeFragment = new HomeFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.content_frame, homeFragment);
            ft.commit();
            icHome.setImageResource(R.drawable.home_active);
        }

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
                        showUpdateDialog();
                    }
                }
            } else {
                //background.start();
                // super.onPostExecute(jsonObject);
            }
        }

        private void showUpdateDialog() {
            final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(DashBoardActivity.this);
            builder.setTitle("A New Update is Available");
            builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        Intent intent;
                        intent = new Intent(Intent.ACTION_VIEW);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setData(Uri.parse("market://details?id=" + "com.cynoteck.petofyOPHR"));
                        startActivity(intent);
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + "com.cynoteck.petofyOPHR")));
                    }
                    dialog.dismiss();
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //background.start();
                }
            });

            builder.setCancelable(false);
            dialog = builder.show();
        }

    }


    private void init() {
        homeRL = findViewById(R.id.homeRL);
        profileRL = findViewById(R.id.profileRL);
        petregisterRL = findViewById(R.id.petRegisterRL);
        appointmentRL = findViewById(R.id.appointmentRL);

        icHome = findViewById(R.id.icHome);
        icProfile = findViewById(R.id.icProfile);
        icPetRegister = findViewById(R.id.icPetRegister);
        icAppointment = findViewById(R.id.icAppointment);

        homeRL.setOnClickListener(this);
        profileRL.setOnClickListener(this);
        petregisterRL.setOnClickListener(this);
        appointmentRL.setOnClickListener(this);


    }

    private void getUserDetails() {
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
                    Log.d("GetUserDetails", response.body().toString());
                    UserResponse userResponse = (UserResponse) response.body();
                    int responseCode = Integer.parseInt(userResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
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

        }

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
