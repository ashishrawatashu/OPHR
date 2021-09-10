package com.cynoteck.petofyOPHR.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.adapters.SearchAdapter;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.petReportsRequest.PetDataParams;
import com.cynoteck.petofyOPHR.params.petReportsRequest.PetDataRequest;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.getPetListResponse.GetPetListResponse;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.getPetListResponse.PetList;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.cynoteck.petofyOPHR.utils.SearchInterface;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements ApiResponse, SearchInterface {
    EditText searchpet;
    MaterialCardView back_arrow_CV,search_pet_MCV;
    ArrayList<PetList> profileList = new ArrayList<>();
    RecyclerView register_pet_RV;
    com.cynoteck.petofyOPHR.adapters.SearchAdapter SearchAdapter;
    Methods methods;
    ProgressBar search_PB;
    NestedScrollView nested_scroll_view;
    String intentFrom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();
        intentFrom = intent.getStringExtra("intentFrom");

        methods = new Methods(this);
        searchpet =  findViewById(R.id.search_pet);
        back_arrow_CV =  findViewById(R.id.back_arrow_CV);
        register_pet_RV = findViewById(R.id.register_pet_RV);
        search_pet_MCV = findViewById(R.id.search_pet_MCV);
        search_PB = findViewById(R.id.search_PB);
        nested_scroll_view = findViewById(R.id.nested_scroll_view);
        searchpet.requestFocus();

        back_arrow_CV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        search_pet_MCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (methods.isInternetOn()) {
                    register_pet_RV.setVisibility(View.GONE);
                    if (!searchpet.getText().toString().equals("")) {
                        search_PB.setVisibility(View.VISIBLE);
                        nested_scroll_view.setVisibility(View.GONE);
                        petSearchDependsOnPrefix(searchpet.getText().toString());
                    }else {
                        Toast.makeText(SearchActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    methods.DialogInternet();
                }
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
        service.get(SearchActivity.this, ApiClient.getApiInterface().getPetList(Config.token, getPetDataRequest), "GetPetListBySearch");
        Log.e("DATALOG", "check1=> " + getPetDataRequest);
    }

    @Override
    public void onResponse(Response arg0, String key) {
        switch (key) {
            case "GetPetListBySearch":
                try {
                    search_PB.setVisibility(View.GONE);
                    nested_scroll_view.setVisibility(View.VISIBLE);
                    GetPetListResponse getPetListResponse = (GetPetListResponse) arg0.body();
                    int responseCode = Integer.parseInt(getPetListResponse.getResponse().getResponseCode());
                    profileList.clear();
                    if (responseCode == 109) {
                        if (getPetListResponse.getData().getPetList().isEmpty()) {
                            Toast.makeText(this, "Pet Not Exist !", Toast.LENGTH_SHORT).show();
                        }
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this);
                        register_pet_RV.setLayoutManager(linearLayoutManager);
                        if (getPetListResponse.getData().getPetList().size() > 0) {

                            for (int i = 0; i < getPetListResponse.getData().getPetList().size(); i++) {
                                PetList petList = new PetList();
                                petList.setPetUniqueId(getPetListResponse.getData().getPetList().get(i).getPetUniqueId());
                                petList.setDateOfBirth(getPetListResponse.getData().getPetList().get(i).getDateOfBirth());
                                petList.setUserId(getPetListResponse.getData().getPetList().get(i).getUserId());
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
                            register_pet_RV.setVisibility(View.VISIBLE);
                            SearchAdapter = new SearchAdapter(SearchActivity.this, profileList, this);
                            register_pet_RV.setAdapter(SearchAdapter);
                            SearchAdapter.notifyDataSetChanged();

                        } else {

                            Log.e("No_DATA", "NO_DATA");
                        }


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
    public void onViewDetailsClick(int position) {
        if (intentFrom.equals("Appointment")){
            Intent intent = new Intent();
            intent.putExtra("petId",profileList.get(position).getId());
            intent.putExtra("userID",profileList.get(position).getUserId());
            intent.putExtra("petName",profileList.get(position).getPetName());
            intent.putExtra("PetUniqueId",profileList.get(position).getPetUniqueId());
            setResult(RESULT_OK,intent);
            finish();
        }else {
            Intent petDetailsIntent = new Intent(SearchActivity.this, PetDetailsActivity.class);
            Bundle data = new Bundle();
            data.putString("pet_image_url",profileList.get(position).getPetProfileImageUrl());
            data.putString("pet_id", profileList.get(position).getId());
            data.putString("pet_name", profileList.get(position).getPetName());
            data.putString("pet_parent", profileList.get(position).getPetParentName());
            data.putString("pet_sex", profileList.get(position).getPetSex());
            data.putString("pet_age", profileList.get(position).getPetAge());
            data.putString("pet_unique_id", profileList.get(position).getPetUniqueId());
            data.putString("pet_DOB", profileList.get(position).getDateOfBirth());
            data.putString("pet_encrypted_id", profileList.get(position).getEncryptedId());
            data.putString("pet_cat_id", profileList.get(position).getPetCategoryId());
            data.putString("lastVisitEncryptedId", profileList.get(position).getLastVisitEncryptedId());
            petDetailsIntent.putExtras(data);
            startActivity(petDetailsIntent);
        }

    }
}