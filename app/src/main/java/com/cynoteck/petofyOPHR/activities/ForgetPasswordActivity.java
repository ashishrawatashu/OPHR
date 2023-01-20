package com.cynoteck.petofyOPHR.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.forgetPassRequest.ForgetPassDataParams;
import com.cynoteck.petofyOPHR.params.forgetPassRequest.ForgetPassRequest;
import com.cynoteck.petofyOPHR.response.forgetAndChangePassResponse.PasswordResponse;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Response;

public class ForgetPasswordActivity extends AppCompatActivity implements ApiResponse, View.OnClickListener {

    TextInputEditText email_TIET;
    TextInputLayout email_TIL;
    Button submit_BT;
    String mailString;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Methods methods;
    MaterialCardView back_arrow_CV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        methods = new Methods(this);
        email_TIET = findViewById(R.id.email_TIET);
        submit_BT= findViewById(R.id.submitMailBT);
        back_arrow_CV=findViewById(R.id.back_arrow_CV);
        email_TIL=findViewById(R.id.email_TIL);
        back_arrow_CV.setOnClickListener(this);
        submit_BT.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_arrow_CV:
                onBackPressed();
                break;

            case R.id.submitMailBT:
                mailString = email_TIET.getText().toString().trim();
                if ( mailString.isEmpty()){
                    email_TIL.setError("Email is empty");
                }else if (!mailString.matches(emailPattern)) {
                    email_TIL.setError("Invalid Email");
                }else{
                    email_TIET.setError(null);
                    ForgetPassDataParams forgetPassDataParams = new ForgetPassDataParams();
                    forgetPassDataParams.setEmail(mailString);
                    ForgetPassRequest forgetPassRequest = new ForgetPassRequest();
                    forgetPassRequest.setData(forgetPassDataParams);
                    if(methods.isInternetOn())
                    {
                        forgetPassword(forgetPassRequest);
                    }
                    else
                    {
                        methods.DialogInternet();
                    }

                }

                break;

        }

    }

    private void forgetPassword(ForgetPassRequest forgetPassRequest) {
        methods.showCustomProgressBarDialog(this);
        ApiService<PasswordResponse> service = new ApiService<>();
        service.get( this, ApiClient.getApiInterface().getPasswordResponse(forgetPassRequest), "ForgetPassword");
        Log.e("DATALOG","check1=> "+forgetPassRequest);

    }

    @Override
    public void onResponse(Response response, String key) {
        methods.customProgressDismiss();
        switch (key)
        {
            case "ForgetPassword":

                try {
                    Log.d("DATALOG",response.body().toString());
                    PasswordResponse passwordResponse = (PasswordResponse) response.body();
                    int responseCode = Integer.parseInt(passwordResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        email_TIET.getText().clear();

                        Intent intent = new Intent(this,LoginActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                    }else if (responseCode==614){
                        Toast.makeText(this, passwordResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }

                }
                catch(Exception e) {


                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onError(Throwable t, String key) {
        methods.customProgressDismiss();


    }
}
