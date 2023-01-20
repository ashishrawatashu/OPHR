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
import android.widget.EditText;
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
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import retrofit2.Response;


public class RegisterActivity extends FragmentActivity implements ApiResponse, View.OnClickListener {
    Methods methods;
    private EditText vet_first_name_ET, vet_last_name_ET, vet_email_ET, vet_phone_ET, vet_password_ET, vet_confirm_password_ET;
    private Button vet_signUp_BT, login_bt_dialog;
    private String firstName = "", lastName = "", email = "", phoneNumber = "", password = "", confirmPassword = "", dctrAddresingStr = "";
    private TextView signIN_TV;
    MaterialCardView vet_back_arrow_CV;
    //    AppCompatSpinner parent_address;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ArrayList<String> parentAddresingList;
    Dialog email_verify_dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet_register);
        methods = new Methods(this);

        init();

    }

    private void init() {
        vet_first_name_ET = findViewById(R.id.vet_first_name_ET);
        vet_last_name_ET = findViewById(R.id.vet_last_name_ET);
        vet_email_ET = findViewById(R.id.vet_email_ET);
        vet_phone_ET = findViewById(R.id.vet_phone_ET);
        vet_password_ET = findViewById(R.id.vet_password_ET);
        vet_confirm_password_ET = findViewById(R.id.vet_confirm_password_ET);

        vet_back_arrow_CV = findViewById(R.id.vet_back_arrow_CV);

        vet_signUp_BT = findViewById(R.id.vet_signUp_BT);
        vet_back_arrow_CV.setOnClickListener(this);
        vet_signUp_BT.setOnClickListener(this);
        parentAddresingList = new ArrayList<>();
        parentAddresingList.add("Dr.");
        parentAddresingList.add("Mrs.");
        parentAddresingList.add("Mr.");

//        setSpinnerDrNameAdrressing();

    }

//    private void setSpinnerDrNameAdrressing() {
//        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,parentAddresingList);
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //Setting the ArrayAdapter data on the Spinner
//        parent_address.setAdapter(aa);
//        parent_address.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String item = parent.getItemAtPosition(position).toString();
//                // Showing selected spinner item
//                Log.d("spnerType","doctorAddress"+item);
//                dctrAddresingStr=item;
//            }
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });
//    }

    private void registerUser(Registerparams registerparams) {
        methods.showCustomProgressBarDialog(this);
        ApiService<LoginRegisterResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().registerApi(registerparams), "Register");
        Log.d("DATALOG", "check1=> " + registerparams);
    }

    @Override
    public void onResponse(Response response, String key) {
        methods.customProgressDismiss();
        switch (key) {
            case "Register":
                try {
                    Log.d("DATALOG", "" + response.body().toString());
                    LoginRegisterResponse registerResponse = (LoginRegisterResponse) response.body();
                    int responseCode = Integer.parseInt(registerResponse.getResponseLogin().getResponseCode());
                    if (responseCode == 109) {
//                        showEmailVerifyDialog();
                        setResult(RESULT_OK);
                        finish();
                        Toast.makeText(this, registerResponse.getResponseLogin().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else if (responseCode == 615) {
                        Toast.makeText(this, registerResponse.getResponseLogin().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("eeeeeee", e.getLocalizedMessage());
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

        switch (v.getId()) {

            case R.id.vet_signUp_BT:
                firstName = vet_first_name_ET.getText().toString().trim();
                lastName = vet_last_name_ET.getText().toString().trim();
                email = vet_email_ET.getText().toString().trim();
                password = vet_password_ET.getText().toString().trim();
                confirmPassword = vet_confirm_password_ET.getText().toString().trim();
                phoneNumber = vet_phone_ET.getText().toString().trim();

                if (firstName.isEmpty()) {
                    vet_first_name_ET.setError("Name is empty");
                    vet_last_name_ET.setError(null);
                    vet_email_ET.setError(null);
                    vet_phone_ET.setError(null);
                    vet_password_ET.setError(null);
                    vet_confirm_password_ET.setError(null);
                } else if (lastName.isEmpty()) {
                    vet_last_name_ET.setError("Last Name is empty");
                    vet_first_name_ET.setError(null);
                    vet_email_ET.setError(null);
                    vet_phone_ET.setError(null);
                    vet_password_ET.setError(null);
                    vet_confirm_password_ET.setError(null);
                } else if (email.isEmpty()) {
                    vet_email_ET.setError("Email is empty");
                    vet_first_name_ET.setError(null);
                    vet_last_name_ET.setError(null);
                    vet_phone_ET.setError(null);
                    vet_password_ET.setError(null);
                    vet_confirm_password_ET.setError(null);
                } else if (!email.matches(emailPattern)) {
                    vet_email_ET.setError("Invalid Email");
                    vet_first_name_ET.setError(null);
                    vet_last_name_ET.setError(null);
                    vet_phone_ET.setError(null);
                    vet_password_ET.setError(null);
                    vet_confirm_password_ET.setError(null);
                } else if (phoneNumber.isEmpty()) {
                    vet_phone_ET.setError("Phone Number is empty");
                    vet_first_name_ET.setError(null);
                    vet_last_name_ET.setError(null);
                    vet_email_ET.setError(null);
                    vet_password_ET.setError(null);
                    vet_confirm_password_ET.setError(null);
                } else if (password.isEmpty()) {
                    vet_password_ET.setError("Password is empty");
                    vet_first_name_ET.setError(null);
                    vet_last_name_ET.setError(null);
                    vet_email_ET.setError(null);
                    vet_phone_ET.setError(null);
                    vet_confirm_password_ET.setError(null);
                } else if (confirmPassword.isEmpty()) {
                    vet_confirm_password_ET.setError("Password is empty");
                    vet_first_name_ET.setError(null);
                    vet_last_name_ET.setError(null);
                    vet_email_ET.setError(null);
                    vet_phone_ET.setError(null);
                    vet_password_ET.setError(null);
                } else if (!vet_password_ET.getText().toString().equals(vet_confirm_password_ET.getText().toString())) {
                    vet_confirm_password_ET.setError("Password is not matched ");
                    vet_first_name_ET.setError(null);
                    vet_last_name_ET.setError(null);
                    vet_email_ET.setError(null);
                    vet_phone_ET.setError(null);
                    vet_password_ET.setError(null);
                } else {
                    vet_first_name_ET.setError(null);
                    vet_last_name_ET.setError(null);
                    vet_email_ET.setError(null);
                    vet_phone_ET.setError(null);
                    vet_password_ET.setError(null);
                    vet_confirm_password_ET.setError(null);
                    Registerparams registerparams = new Registerparams();
                    RegisterRequest data = new RegisterRequest();
                    data.setEmail(email);
                    data.setPassword(password);
                    data.setConfirmPassword(confirmPassword);
                    data.setFirstName(firstName);
                    data.setLastName(lastName);
                    data.setPhoneNumber(phoneNumber);
                    data.setRoleName("Veterinarian");
                    registerparams.setData(data);
                    if (methods.isInternetOn()) {
                        registerUser(registerparams);
                    } else {
                        methods.DialogInternet();
                    }


                }

                break;

            case R.id.vet_back_arrow_CV:

                onBackPressed();

                break;


        }

    }

}
