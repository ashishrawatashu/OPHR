package com.cynoteck.petofyOPHR.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.adapters.GetBanksAccountsAdapter;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.response.bankAccountResponse.GetBankAccoutsResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.google.android.material.card.MaterialCardView;

import retrofit2.Response;

public class GetAllBankAccountsActivity extends AppCompatActivity implements ApiResponse, View.OnClickListener {
    RecyclerView bank_accounts_RV;
    Methods methods;
    GetBanksAccountsAdapter getBanksAccountsAdapter;
    ProgressBar progressBar;
    Button add_account_BT;
    MaterialCardView back_arrow_CV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_bank_accounts);
        methods = new Methods(this);
        add_account_BT = findViewById(R.id.add_account_BT);
        back_arrow_CV=findViewById(R.id.back_arrow_CV);
        bank_accounts_RV = findViewById(R.id.bank_accounts_RV);
        progressBar = findViewById(R.id.progressBar);
        if (methods.isInternetOn()){
            getBankAccouts();
        }else {
            methods.DialogInternet();
        }
        add_account_BT.setOnClickListener(this);
        back_arrow_CV.setOnClickListener(this);

    }

    private void getBankAccouts() {
        ApiService<GetBankAccoutsResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getBankAccouts(Config.token), "GetAccounts");

    }

    @Override
    public void onResponse(Response arg0, String key) {
        switch (key){
            case "GetAccounts":
                try {
                    progressBar.setVisibility(View.GONE);
                    GetBankAccoutsResponse getBankAccoutsResponse  = (GetBankAccoutsResponse) arg0.body();
                    Log.d("GetAccounts", getBankAccoutsResponse.toString());
                    int responseCode = Integer.parseInt(getBankAccoutsResponse.getResponse().getResponseCode());
//                    for backbutton
                    SharedPreferences sharedPreferences = getSharedPreferences("responseCode", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("responseCode", responseCode);
                    editor.apply();



                    if (responseCode == 109) {
                        bank_accounts_RV.setVisibility(View.VISIBLE);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                        bank_accounts_RV.setLayoutManager(linearLayoutManager);
                        getBanksAccountsAdapter  = new GetBanksAccountsAdapter(this,getBankAccoutsResponse.getData());
                        bank_accounts_RV.setAdapter(getBanksAccountsAdapter);
                        getBanksAccountsAdapter.notifyDataSetChanged();
                    } else if (responseCode == 614) {
                        Toast.makeText(this, getBankAccoutsResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }else if (responseCode == 112) {
                        Intent intent = new Intent(this,BankAccountDetailsActivity.class);
                        startActivityForResult(intent,1);
                        Toast.makeText(this, getBankAccoutsResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        progressBar.setVisibility(View.VISIBLE);
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                getBankAccouts();
//                progressBar.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_account_BT:
                Intent intent = new Intent(this,BankAccountDetailsActivity.class);
                startActivityForResult(intent,1);
                break;

            case R.id.back_arrow_CV:
                onBackPressed();
                break;
        }
    }
}
