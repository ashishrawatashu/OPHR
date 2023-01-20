package com.cynoteck.petofyOPHR.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.loginRequest.LoginRequest;
import com.cynoteck.petofyOPHR.params.loginRequest.Loginparams;
import com.cynoteck.petofyOPHR.params.otpRequest.SendOtpParameter;
import com.cynoteck.petofyOPHR.params.otpRequest.SendOtpRequest;
import com.cynoteck.petofyOPHR.response.loginRegisterResponse.LoginRegisterResponse;
import com.cynoteck.petofyOPHR.response.loginRegisterResponse.UserPermissionMasterList;
import com.cynoteck.petofyOPHR.response.otpResponse.OtpResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Response;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.ACCESS_WIFI_STATE;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class LoginActivity extends FragmentActivity implements View.OnClickListener, ApiResponse {
    MaterialCardView login_back_arrow_CV;
    private static final int PERMISSION_REQUEST_CODE = 200;
    LoginRegisterResponse responseLogin;
    TextInputLayout mobile_numberTL, otp_TL;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor login_editor;
    Dialog otpDialog,email_verify_dialog;
    TextInputEditText pet_parent_otp;
    TextView cancelOtpDialog, otpHeading;
    boolean doubleBackToExitPressedOnce = false;
    private TextInputLayout email_TIL, password_TIL;
    private TextInputEditText email_TIET, password_TIET;
    private Button login_BT, submit_parent_otp,login_bt_dialog;
    private String emailString = "", passwordString = "";
    private TextView signUp_TV, forgetPass_TV;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Methods methods;
    ImageView logoVet;
    TelephonyManager telephonyManager;
    String imeiNumber = "", token = "";
    String deviceIp, strResponseOtp = "";
    public static final String channel_id="channel_id";
    private static final String channel_name="channel_name";
    private static final String channel_desc="channel_desc";
    private static final String TAG = "LoginActivity";
    ArrayList<UserPermissionMasterList> userPermissionMasterLists = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        methods = new Methods(this);
        init();
        if (checkPermission()) {
        } else {
            if (!checkPermission()) {
                requestPermission();
            } else {
                Toast.makeText(this, "Permission already granted.", Toast.LENGTH_SHORT).show();
            }
        }

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
//To do//
                            return;
                        }

// Get the Instance ID token//
                        String token = task.getResult().getToken();
                        String msg = getString(R.string.fcm_token, token);
                        Log.d(TAG, msg);

                    }
                });
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel=new NotificationChannel(channel_id, channel_name, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription(channel_desc);
            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);

        }


    }

    private boolean checkPermission() {

        int result = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
        int result2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_PHONE_STATE);
        int result3 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        int result4 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result5 = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_WIFI_STATE);
        int result6 = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_NETWORK_STATE);
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED &&  result2 == PackageManager.PERMISSION_GRANTED  && result3 == PackageManager.PERMISSION_GRANTED &&  result4 == PackageManager.PERMISSION_GRANTED && result5 == PackageManager.PERMISSION_GRANTED &&  result6 == PackageManager.PERMISSION_GRANTED;

    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION,CAMERA,READ_PHONE_STATE,READ_EXTERNAL_STORAGE,WRITE_EXTERNAL_STORAGE,ACCESS_WIFI_STATE,ACCESS_NETWORK_STATE}, PERMISSION_REQUEST_CODE);

    }

    private void init() {
        login_back_arrow_CV = findViewById(R.id.login_back_arrow_CV);
        logoVet = findViewById(R.id.logoVet);
        email_TIET = findViewById(R.id.email_TIET);
        password_TIET = findViewById(R.id.password_TIET);
        email_TIL = findViewById(R.id.email_TIL);
        password_TIL = findViewById(R.id.password_TIL);
        login_BT = findViewById(R.id.login_BT);
        signUp_TV = findViewById(R.id.signUp_TV);
        forgetPass_TV = findViewById(R.id.forgetPass_TV);

        signUp_TV.setOnClickListener(this);
        forgetPass_TV.setOnClickListener(this);
        login_BT.setOnClickListener(this);
        login_back_arrow_CV.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.login_back_arrow_CV:
                if (Config.logoutFromAccount==false){
                onBackPressed();
                }else {
                    finishAffinity();
//                    System.exit(0);
                }
                break;

            case R.id.login_BT:
                getDeviceId();
                emailString = email_TIET.getText().toString().trim();
                passwordString = password_TIET.getText().toString().trim();
//                Toast.makeText(this, ""+imeiNumber, Toast.LENGTH_LONG).show();
                if (emailString.isEmpty()) {
                    email_TIL.setError("Email is empty");
                    password_TIL.setError(null);
                } else if (!emailString.matches(emailPattern)) {
                    email_TIL.setError("Invalid Email");
                    password_TIL.setError(null);
                } else if (passwordString.isEmpty()) {
                    email_TIL.setError(null);
                    password_TIL.setError("Password is empty");
                }
                /*else if (imeiNumber.isEmpty()) {
                    if (checkPermission()) {
                        getDeviceId();
                    } else {
                        if (!checkPermission()) {
                            requestPermission();
                        } else {
                            Toast.makeText(this, "Permission already granted.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }*/
                else {
                    email_TIL.setError(null);
                    password_TIL.setError(null);
                    Loginparams loginparams = new Loginparams();
                    LoginRequest data = new LoginRequest();
                    data.setEmail(emailString);
                    data.setDeviceId(imeiNumber);
                    data.setDeviceIp(Config.IpAddress);
                    data.setPassword(passwordString);
                    loginparams.setLoginData(data);
                    if (methods.isInternetOn()) {
                        loginUser(loginparams);
                    } else {
                        methods.DialogInternet();
                    }

                }
                break;

            case R.id.signUp_TV:
                Intent signUP_intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivityForResult(signUP_intent, 1);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                break;

            case R.id.forgetPass_TV:
                Intent forgetPass_intent = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                startActivity(forgetPass_intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

                break;
            case R.id.submit_parent_otp:
                String otp = pet_parent_otp.getText().toString();
                if (otp.isEmpty()) {
                    pet_parent_otp.setError("Enter Correct OTP");
                } else if (!otp.equals(strResponseOtp)) {
                    pet_parent_otp.setError("Enter Wrong OTP");
                } else {
                    Log.e("UserRole",responseLogin.getData().getUserRole());
                    if(responseLogin.getData().getUserRole().equals("Veterinarian"))
                        loginSucess();
                    else if (responseLogin.getData().getUserRole().equals("Vet Staff")){
                        loginSucess();

                    }else {
                        ifParentIdDialog();
                    }
                }
                break;

            case R.id.cancelOtpDialog:
                otpDialog.dismiss();
                break;

            case R.id.login_button:
                email_verify_dialog.dismiss();
                break;
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setTitle("Verify your Email");
                builder1.setMessage("Please click on the link that has just been sent to your registered email id to verify your account and continue the registration process.");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });


                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        }
    }
    private void ifParentIdDialog()
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("ALERT!!");
        builder1.setMessage("This is Pet Parent account, Kindly use this account for  Petofy Parent App\nThank You..");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });


        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    private void loginUser(Loginparams loginparams) {
        methods.showCustomProgressBarDialog(this);
        ApiService<LoginRegisterResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().loginApi(loginparams), "Login");
        Log.e("DATALOG", "check1=> " + loginparams);

    }

    @Override
    public void onResponse(Response response, String key) {
        methods.customProgressDismiss();
        switch (key) {
            case "Login":
                try {
                    Log.d("DATALOG", response.body().toString());
                    responseLogin = (LoginRegisterResponse) response.body();
                    int responseCode = Integer.parseInt(responseLogin.getResponseLogin().getResponseCode());
                    if (responseCode == 109) {
                        token = responseLogin.getResponseLogin().getToken();
                        if (responseLogin.getData().getIsEmailVerified().equals("false")){
                            showEmailVerifyDialog();
                        }else if (responseLogin.getData().getIsActive().equals("false")){
                                isactiveDialog();
                        }else {
                            if (responseLogin.getData().getEnableTwoStepVerification().equals("true")) {
                                String actualNumber = responseLogin.getData().getPhoneNumber().replaceAll("-", "");
                                SendOtpRequest sendOtpRequest = new SendOtpRequest();
                                SendOtpParameter data = new SendOtpParameter();
                                data.setPhoneNumber(actualNumber);
                                data.setEmailId(responseLogin.getData().getEmail().trim());
                                sendOtpRequest.setData(data);
                                if (methods.isInternetOn()) {
                                    sendotpUsingMobileNumber(sendOtpRequest);
                                } else {
                                    methods.DialogInternet();
                                }


                            }
                            else {
                                if(responseLogin.getData().getUserRole().equals("Veterinarian"))
                                    loginSucess();
                                else if (responseLogin.getData().getUserRole().equals("Vet Staff")){
                                    userPermissionMasterLists = responseLogin.getData().getUserPermissionMasterList();
                                    Log.e("userPermission",responseLogin.getData().getUserPermissionMasterList().toString());
                                    Log.e("userPermission",userPermissionMasterLists.toString());
                                    loginSucess();
                                }else {
                                    ifParentIdDialog();
                                }
                            }
                        }



                    } else if (responseCode == 614) {
                        Toast.makeText(LoginActivity.this, responseLogin.getResponseLogin().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;


            case "SendOtp":
                try {
                    Log.d("SendOtp", response.body().toString());
                    OtpResponse otpResponse = (OtpResponse) response.body();
                    int responseCode = Integer.parseInt(otpResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        if (otpResponse.getData().getSuccess().equals("true")) {
                            strResponseOtp = otpResponse.getData().getOtp();
                            otpDialog();
                        } else {
                            Toast.makeText(LoginActivity.this, "Invalid Mobile Number", Toast.LENGTH_SHORT).show();
                        }
                    } else if (responseCode == 614) {
                        Toast.makeText(LoginActivity.this, otpResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void isactiveDialog() {
        {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setTitle("Your Profile is under review.");
            builder1.setMessage("You should hear back within 24 hours.\nThank You..");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });


            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }

    private void showEmailVerifyDialog() {
        email_verify_dialog = new Dialog(this);
        email_verify_dialog.setContentView(R.layout.email_verify_dialog);

        login_bt_dialog = email_verify_dialog.findViewById(R.id.login_button);
        login_bt_dialog.setOnClickListener(this);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = email_verify_dialog.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        email_verify_dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        email_verify_dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
//        email_verify_dialog.setCanceledOnTouchOutside(false);
        email_verify_dialog.show();

    }

    private void loginSucess() {
        email_TIET.getText().clear();
        password_TIET.getText().clear();
        sharedPreferences = LoginActivity.this.getSharedPreferences("userdetails", 0);
        login_editor = sharedPreferences.edit();
        login_editor.putString("first_name", responseLogin.getData().getFirstName());
        login_editor.putString("last_name", responseLogin.getData().getLastName());
        login_editor.putString("email", responseLogin.getData().getEmail());
        login_editor.putString("user_type", responseLogin.getData().getUserRole());
        login_editor.putString("userId", responseLogin.getData().getUserId());
        login_editor.putString("firstName", responseLogin.getData().getFirstName());
        login_editor.putString("lastName", responseLogin.getData().getLastName());
        login_editor.putString("phoneNumber", responseLogin.getData().getPhoneNumber());
        login_editor.putString("address", responseLogin.getData().getAddress());
        login_editor.putString("token", responseLogin.getResponseLogin().getToken());
        login_editor.putString("profilePic", responseLogin.getData().getProfileImageUrl());
        login_editor.putString("study", responseLogin.getData().getVetQualification());
        login_editor.putString("vetid", responseLogin.getData().getVetRegistrationNumber());
        login_editor.putString("onlineAppoint", responseLogin.getData().getOnlineAppointmentStatus());
        login_editor.putString("twoFactAuth", responseLogin.getData().getEnableTwoStepVerification());
        if (responseLogin.getData().getUserRole().equals("Vet Staff")) {
            Gson gson = new Gson();
            String json = gson.toJson(userPermissionMasterLists);
            login_editor.putString("userPermission", json);
            Log.e("userPermission",userPermissionMasterLists.toString());
//            login_editor.putInt(userPermissionMasterLists +"_size", userPermissionMasterLists.size());
//            for(int i=0;i<userPermissionMasterLists.size();i++)
//                login_editor.putString(userPermissionMasterLists + "_" + i, String.valueOf(userPermissionMasterLists.get(i)));

        }
        Config.token = responseLogin.getResponseLogin().getToken();
        login_editor.putString("loggedIn", "loggedIn");
        login_editor.commit();

        Intent intent = new Intent(this, DashBoardActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        Toast.makeText(LoginActivity.this, "Welcome Dr. "+Config.vet_first_name, Toast.LENGTH_SHORT).show();
    }

    private void otpDialog() {
        otpDialog = new Dialog(this);
        otpDialog.setContentView(R.layout.otp_layout);

        otp_TL = otpDialog.findViewById(R.id.otp_TL);
        pet_parent_otp = otpDialog.findViewById(R.id.pet_parent_otp);
        submit_parent_otp = otpDialog.findViewById(R.id.submit_parent_otp);
        cancelOtpDialog = otpDialog.findViewById(R.id.cancelOtpDialog);
        otpHeading = otpDialog.findViewById(R.id.otpHeading);
        submit_parent_otp.setOnClickListener(this);
        cancelOtpDialog.setOnClickListener(this);
        otpHeading.setText("Enter O.T.P");


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = otpDialog.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        otpDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        otpDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        otpDialog.show();

    }

    private void sendotpUsingMobileNumber(SendOtpRequest sendOtpRequest) {
        ApiService<OtpResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().senOtp(token, sendOtpRequest), "SendOtp");
        Log.e("DATALOG", "check1=> " + sendOtpRequest);

    }

    @Override
    public void onError(Throwable t, String key) {
        methods.customProgressDismiss();
        Log.e("error", t.getMessage());
        Log.e("errrrr", t.getLocalizedMessage());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @SuppressLint("MissingPermission")
    private void getDeviceId() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                if (telephonyManager != null) {
                    try {
                        imeiNumber = Settings.Secure.getString(
                                this.getContentResolver(),
                                Settings.Secure.ANDROID_ID);
                    } catch (Exception e) {
                        e.printStackTrace();
                        imeiNumber = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
                    }
                }
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1010);
            }
        } else {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                if (telephonyManager != null) {
                    imeiNumber = telephonyManager.getDeviceId();
                }
                else {
                    imeiNumber = Settings.Secure.getString(
                            this.getContentResolver(),
                            Settings.Secure.ANDROID_ID);
                }
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1010);
            }
        }
    }

}
