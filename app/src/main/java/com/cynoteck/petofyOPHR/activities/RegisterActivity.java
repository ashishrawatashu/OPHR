package com.cynoteck.petofyOPHR.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.FragmentActivity;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.registerRequest.RegisterRequest;
import com.cynoteck.petofyOPHR.params.registerRequest.Registerparams;
import com.cynoteck.petofyOPHR.response.loginRegisterResponse.LoginRegisterResponse;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import retrofit2.Response;


public class RegisterActivity extends FragmentActivity implements ApiResponse, View.OnClickListener {
    Methods methods;
    private TextInputLayout firstname_TIL, lastName_TIL, email_TIL, phoneNumber_TIL, password_TIL, confirmPassword_TIL;
    private TextInputEditText firstname_TIET, lastName_TIET, email_TIET, phoneNumber_TIET, password_TIET, confirmPassword_TIET;
    private Button signUp_BT,login_bt_dialog;
    private String firstName="", lastName="", email="", phoneNumber="",password="",confirmPassword="", dctrAddresingStr="";
    private TextView signIN_TV;
    AppCompatSpinner parent_address;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ArrayList<String> parentAddresingList;
    Dialog email_verify_dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityregister);
        methods = new Methods(this);

        init();

    }

    private void init() {
        firstname_TIL = findViewById(R.id.firstName_TIL);
        lastName_TIL = findViewById(R.id.lastName_TIL);
        email_TIL = findViewById(R.id.email_TIL);
        phoneNumber_TIL = findViewById(R.id.number_TIL);
        password_TIL = findViewById(R.id.password_TIL);
        confirmPassword_TIL = findViewById(R.id.cPassword_TIL);
        parent_address = findViewById(R.id.parent_address);


        firstname_TIET = findViewById(R.id.firstName_TIET);
        lastName_TIET = findViewById(R.id.lastName_TIET);
        email_TIET = findViewById(R.id.email_TIET);
        phoneNumber_TIET = findViewById(R.id.number_TIET);
        password_TIET = findViewById(R.id.password_TIET);
        confirmPassword_TIET = findViewById(R.id.cPassword_TIET);

        signIN_TV = findViewById(R.id.cancel_TV);

        signUp_BT=findViewById(R.id.signUp_BT);

        signUp_BT.setOnClickListener(this);
        signIN_TV.setOnClickListener(this);

        parentAddresingList=new ArrayList<>();
        parentAddresingList.add("Dr.");
        parentAddresingList.add("Mrs.");
        parentAddresingList.add("Mr.");

        setSpinnerDrNameAdrressing();

    }

    private void setSpinnerDrNameAdrressing() {
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,parentAddresingList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        parent_address.setAdapter(aa);
        parent_address.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                Log.d("spnerType","doctorAddress"+item);
                dctrAddresingStr=item;
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void registerUser(Registerparams registerparams) {
        methods.showCustomProgressBarDialog(this);
        ApiService<LoginRegisterResponse> service = new ApiService<>();
        service.get( this, ApiClient.getApiInterface().registerApi(registerparams), "Register");
        Log.d("DATALOG","check1=> "+registerparams);
    }

    @Override
    public void onResponse(Response response, String key) {
        methods.customProgressDismiss();
        switch (key)
        {
            case "Register":
                try {
                    Log.d("DATALOG",""+response.body().toString());
                    LoginRegisterResponse registerResponse = (LoginRegisterResponse) response.body();
                    int responseCode = Integer.parseInt(registerResponse.getResponseLogin().getResponseCode());
                    if (responseCode==109){
//                        showEmailVerifyDialog();
                        setResult(RESULT_OK);
                        finish();
                        Toast.makeText(this, registerResponse.getResponseLogin().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }else if(responseCode==615) {
                        Toast.makeText(this, registerResponse.getResponseLogin().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }

                }
                catch(Exception e) {
                    e.printStackTrace();
                    Log.e("eeeeeee",e.getLocalizedMessage());
                }
                break;
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
        email_verify_dialog.setCanceledOnTouchOutside(false);
        email_verify_dialog.show();

    }

    @Override
    public void onError(Throwable t, String key) {
        methods.customProgressDismiss();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.signUp_BT:
                firstName = firstname_TIET.getText().toString().trim();
                lastName = lastName_TIET.getText().toString().trim();
                email = email_TIET.getText().toString().trim();
                password = password_TIET.getText().toString().trim();
                confirmPassword = confirmPassword_TIET.getText().toString().trim();
                phoneNumber = phoneNumber_TIET.getText().toString().trim();

                if (firstName.isEmpty()){
                    firstname_TIL.setError("Name is empty");
                    lastName_TIL.setError(null);
                    email_TIL.setError(null);
                    phoneNumber_TIL.setError(null);
                    password_TIL.setError(null);
                    confirmPassword_TIL.setError(null);
                }else if (lastName.isEmpty()){
                    lastName_TIL.setError("Last Name is empty");
                    firstname_TIL.setError(null);
                    email_TIL.setError(null);
                    phoneNumber_TIL.setError(null);
                    password_TIL.setError(null);
                    confirmPassword_TIL.setError(null);
                }else if (email.isEmpty()){
                    email_TIL.setError("Email is empty");
                    firstname_TIL.setError(null);
                    lastName_TIL.setError(null);
                    phoneNumber_TIL.setError(null);
                    password_TIL.setError(null);
                    confirmPassword_TIL.setError(null);
                }else if (!email.matches(emailPattern)) {
                    email_TIL.setError("Invalid Email");
                    firstname_TIL.setError(null);
                    lastName_TIL.setError(null);
                    phoneNumber_TIL.setError(null);
                    password_TIL.setError(null);
                    confirmPassword_TIL.setError(null);
                }else if (phoneNumber.isEmpty()){
                    phoneNumber_TIL.setError("Phone Number is empty");
                    firstname_TIL.setError(null);
                    lastName_TIL.setError(null);
                    email_TIL.setError(null);
                    password_TIL.setError(null);
                    confirmPassword_TIL.setError(null);
                }else if (password.isEmpty()){
                    password_TIL.setError("Password is empty");
                    firstname_TIL.setError(null);
                    lastName_TIL.setError(null);
                    email_TIL.setError(null);
                    phoneNumber_TIL.setError(null);
                    confirmPassword_TIL.setError(null);
                }else if (confirmPassword.isEmpty()){
                    confirmPassword_TIL.setError("Password is empty");
                    firstname_TIL.setError(null);
                    lastName_TIL.setError(null);
                    email_TIL.setError(null);
                    phoneNumber_TIL.setError(null);
                    password_TIL.setError(null);
                }else if (!password_TIET.getText().toString().equals(confirmPassword_TIET.getText().toString())){
                    confirmPassword_TIL.setError("Password is not matched ");
                    firstname_TIL.setError(null);
                    lastName_TIL.setError(null);
                    email_TIL.setError(null);
                    phoneNumber_TIL.setError(null);
                    password_TIL.setError(null);
                }else {
                    firstname_TIL.setError(null);
                    lastName_TIL.setError(null);
                    email_TIL.setError(null);
                    phoneNumber_TIL.setError(null);
                    password_TIL.setError(null);
                    confirmPassword_TIL.setError(null);
                    Registerparams registerparams = new Registerparams();
                    RegisterRequest data = new RegisterRequest();
                    data.setEmail(email);
                    data.setPassword(password);
                    data.setConfirmPassword(confirmPassword);
                    data.setFirstName(dctrAddresingStr+" "+firstName);
                    data.setLastName(lastName);
                    data.setPhoneNumber(phoneNumber);
                    data.setRoleName("Veterinarian");
                    registerparams.setData(data);
                    if(methods.isInternetOn())
                    {
                        registerUser(registerparams);
                    }
                    else
                    {
                        methods.DialogInternet();
                    }


                }

                break;

            case R.id.cancel_TV:

                onBackPressed();

                break;


            case R.id.login_button:
                onBackPressed();
                break;



        }

    }

}
