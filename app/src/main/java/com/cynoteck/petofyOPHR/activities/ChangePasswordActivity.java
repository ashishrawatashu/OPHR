package com.cynoteck.petofyOPHR.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.changePassRequest.ChangePassParams;
import com.cynoteck.petofyOPHR.params.changePassRequest.ChangePassRequest;
import com.cynoteck.petofyOPHR.response.forgetAndChangePassResponse.PasswordResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity implements ApiResponse , View.OnClickListener {
    TextInputEditText current_password_TIET ,new_password_TIET ,confirm_password_TIET;
    TextInputLayout current_password_TIL, new_password_TIL, confirm_password_TIL;
    Button change_password_BT;
    Methods methods;
    String current_password="", new_password="", confirm_password="";
    MaterialCardView login_back_arrow_CV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        methods = new Methods(this);
        login_back_arrow_CV=findViewById(R.id.login_back_arrow_CV);

        current_password_TIET = findViewById(R.id.current_password_TIET);
        new_password_TIET = findViewById(R.id.new_password_TIET);
        confirm_password_TIET = findViewById(R.id.confirm_password_TIET);

        current_password_TIL = findViewById(R.id.current_password_TIL);
        new_password_TIL = findViewById(R.id.new_password_TIL);
        confirm_password_TIL = findViewById(R.id.confirm_password_TIL);

        change_password_BT = findViewById(R.id.change_password_BT);
        change_password_BT.setOnClickListener(this);
        login_back_arrow_CV.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_password_BT:
                current_password = current_password_TIET.getText().toString().trim();
                new_password = new_password_TIET.getText().toString().trim();
                confirm_password = confirm_password_TIET.getText().toString().trim();

                if (current_password.isEmpty()){
                    current_password_TIL.setError("Empty");
                    new_password_TIL.setError(null);
                    confirm_password_TIL.setError(null);

                }else if (new_password.isEmpty()){
                    current_password_TIL.setError(null);
                    new_password_TIL.setError("Empty");
                    confirm_password_TIL.setError(null);

                }else if (new_password.length()<6){
                    current_password_TIL.setError(null);
                    new_password_TIL.setError("Password length to short!");
                    confirm_password_TIL.setError(null);

                }else if (confirm_password.isEmpty()){
                    current_password_TIL.setError(null);
                    new_password_TIL.setError(null);
                    confirm_password_TIL.setError("Empty");

                }else if (!new_password_TIET.getText().toString().equals(confirm_password_TIET.getText().toString())){
                    current_password_TIL.setError(null);
                    new_password_TIL.setError(null);
                    confirm_password_TIL.setError("Password do not match!");
                }else {
                    current_password_TIL.setError(null);
                    new_password_TIL.setError(null);
                    confirm_password_TIL.setError(null);

                    ChangePassParams changePassParams = new ChangePassParams();
                    changePassParams.setOldPassword(current_password);
                    changePassParams.setNewPassword(new_password);
                    changePassParams.setConfirmPassword(confirm_password);
                    ChangePassRequest changePassRequest = new ChangePassRequest();
                    changePassRequest.setData(changePassParams);
                    if(methods.isInternetOn())
                    {
                        chnagePassword(changePassRequest);
                    }
                    else
                    {
                        methods.DialogInternet();
                    }



                }

                break;

            case R.id.login_back_arrow_CV:
                onBackPressed();
                break;
        }

    }

    private void chnagePassword(ChangePassRequest changePassRequest) {
        methods.showCustomProgressBarDialog(this);
        ApiService<PasswordResponse> service = new ApiService<>();
        service.get( this, ApiClient.getApiInterface().getPasswordResponse(Config.token,changePassRequest), "ChangePassword");
        Log.e("DATALOG","check1=> "+changePassRequest);

    }

    @Override
    public void onResponse(Response response, String key) {
        methods.customProgressDismiss();
        switch (key)
        {
            case "ChangePassword":

                try {
                    Log.d("DATALOG",response.body().toString());
                    PasswordResponse passwordResponse = (PasswordResponse) response.body();
                    int responseCode = Integer.parseInt(passwordResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        current_password_TIET.getText().clear();
                        new_password_TIET.getText().clear();
                        confirm_password_TIET.getText().clear();

                        Intent intent = new Intent(this,LoginActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
                    }else if (responseCode==614){
                        Toast.makeText(this, passwordResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, "Your Current password is not valid !", Toast.LENGTH_SHORT).show();
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

    }
}
