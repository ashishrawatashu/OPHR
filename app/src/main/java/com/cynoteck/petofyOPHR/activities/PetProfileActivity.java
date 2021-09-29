package com.cynoteck.petofyOPHR.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.getPetListRequest.GetPetListParams;
import com.cynoteck.petofyOPHR.params.getPetListRequest.GetPetListRequest;
import com.cynoteck.petofyOPHR.response.getPetDetailsResponse.GetPetResponse;
import com.cynoteck.petofyOPHR.response.loginRegisterResponse.UserPermissionMasterList;
import com.cynoteck.petofyOPHR.response.staffPermissionListResponse.CheckStaffPermissionResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Response;

public class PetProfileActivity extends AppCompatActivity implements ApiResponse, View.OnClickListener {
    RelativeLayout edit_profile_RL;
    CardView parent_phone_CV, parent_location_CV;
    Methods methods;
    String lastVisitEncryptedId="",petId = "", imagerl = "";
    ImageView pet_profile_image_IV;
    GetPetResponse getPetResponse;
    boolean reloadData = false;
    SharedPreferences sharedPreferences;
    String permissionId = "";
    Button add_Clinic_BT;
    ShimmerFrameLayout pet_profile_shimmer;
    ConstraintLayout pet_profile_details_CL;
    MaterialCardView back_arrow_CV,image_edit_CV;
    TextView pet_name_TV, pet_age_TV, pet_reg__id_TV, pet_breed_TV, pet_gender_TV, pet_parent_name_TV, parent_phone_TV, parent_address_TV;
    ScrollView pet_full_details_SV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_profile);
        methods = new Methods(this);
        Bundle extras = getIntent().getExtras();
        petId = extras.getString("pet_id");
        lastVisitEncryptedId = extras.getString("lastVisitEncryptedId");

        init();

        GetPetListParams getPetListParams = new GetPetListParams();
        getPetListParams.setId(petId);
        GetPetListRequest getPetListRequest = new GetPetListRequest();
        getPetListRequest.setData(getPetListParams);
        sharedPreferences = getSharedPreferences("userdetails", 0);

        if (methods.isInternetOn()) {
            getPetlistData(getPetListRequest);
        } else {
            methods.DialogInternet();
        }

    }

    private void init() {
        pet_full_details_SV=findViewById(R.id.pet_full_details_SV);
        image_edit_CV=findViewById(R.id.image_edit_CV);
        parent_location_CV=findViewById(R.id.parent_location_RL);
        pet_profile_image_IV = findViewById(R.id.pet_profile_image_IV);
        pet_name_TV = findViewById(R.id.pet_name_TV);
        pet_age_TV = findViewById(R.id.pet_age_TV);
        pet_reg__id_TV = findViewById(R.id.pet_reg__id_TV);
        pet_breed_TV = findViewById(R.id.pet_breed_TV);
        pet_gender_TV = findViewById(R.id.pet_gender_TV);
        pet_parent_name_TV = findViewById(R.id.pet_parent_name_TV);
        parent_phone_TV = findViewById(R.id.parent_phone_TV);
        parent_address_TV = findViewById(R.id.parent_address_TV);
        add_Clinic_BT = findViewById(R.id.add_Clinic_BT);
        pet_profile_shimmer = findViewById(R.id.pet_profile_shimmer);


        back_arrow_CV = findViewById(R.id.back_arrow_CV);
        edit_profile_RL = findViewById(R.id.edit_profile_RL);
        image_edit_CV.setOnClickListener(this);
        edit_profile_RL.setOnClickListener(this);
        back_arrow_CV.setOnClickListener(this);
        add_Clinic_BT.setOnClickListener(this);
    }

    private void getPetlistData(GetPetListRequest getPetListRequest) {
        reloadData = true;
        add_Clinic_BT.setEnabled(false);
        pet_full_details_SV.setVisibility(View.GONE);
        edit_profile_RL.setVisibility(View.INVISIBLE);
        image_edit_CV.setVisibility(View.GONE);
        pet_profile_shimmer.setVisibility(View.VISIBLE);
        pet_profile_shimmer.startShimmerAnimation();
        ApiService<GetPetResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getPetDetails(Config.token, getPetListRequest), "GetPetDetail");
        Log.e("DATALOG", "check1=> " + methods.getRequestJson(getPetListRequest));

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_arrow_CV:
                setResult(RESULT_OK);
                finish();
                break;

            case R.id.edit_profile_RL :
                String userTYpe = sharedPreferences.getString("user_type", "");
                if (userTYpe.equals("Vet Staff")) {
                    Gson gson = new Gson();
                    String json = sharedPreferences.getString("userPermission", null);
                    Type type = new TypeToken<ArrayList<UserPermissionMasterList>>() {
                    }.getType();
                    ArrayList<UserPermissionMasterList> arrayList = gson.fromJson(json, type);
                    Log.e("ArrayList", arrayList.toString());
                    Log.d("UserType", userTYpe);
                    permissionId = arrayList.get(1).getPermissionCode();
                    methods.showCustomProgressBarDialog(this);
                    String url = "user/CheckStaffPermission/" + permissionId;
                    Log.e("URL", url);
                    ApiService<CheckStaffPermissionResponse> service = new ApiService<>();
                    service.get(this, ApiClient.getApiInterface().getCheckStaffPermission(Config.token, url), "CheckPermission");
                } else if (userTYpe.equals("Veterinarian")) {
                    Intent intent = new Intent(this, GetPetDetailsActivity.class);
                    intent.putExtra("pet_id", petId);
                    intent.putExtra("pet_category", getPetResponse.getData().getPetCategory());
                    intent.putExtra("pet_name", getPetResponse.getData().getPetName());
                    intent.putExtra("pet_sex", getPetResponse.getData().getPetSex());
                    intent.putExtra("pet_DOB", getPetResponse.getData().getDateOfBirth());
                    intent.putExtra("pet_age", getPetResponse.getData().getPetAge());
                    intent.putExtra("pet_size", getPetResponse.getData().getPetSize());
                    intent.putExtra("pet_breed", getPetResponse.getData().getPetBreed());
                    intent.putExtra("pet_color", getPetResponse.getData().getPetColor());
                    intent.putExtra("pet_parent", getPetResponse.getData().getPetParentName());
                    intent.putExtra("pet_parent_contact", getPetResponse.getData().getContactNumber());
                    intent.putExtra("image_url", getPetResponse.getData().getPetProfileImageUrl());
                    startActivityForResult(intent, 1);
                }

                break;
                
            case R.id.add_Clinic_BT:
                Intent petDetailsIntent = new Intent(this, PetDetailsActivity.class);
                Bundle data = new Bundle();
                data.putString("pet_id", getPetResponse.getData().getId());
                data.putString("pet_name", getPetResponse.getData().getPetName());
                data.putString("pet_parent", getPetResponse.getData().getPetParentName());
                data.putString("pet_sex", getPetResponse.getData().getPetSex());
                data.putString("pet_age", getPetResponse.getData().getPetAge());
                data.putString("pet_unique_id", getPetResponse.getData().getPetUniqueId());
                data.putString("pet_DOB", getPetResponse.getData().getDateOfBirth());
                data.putString("pet_encrypted_id", getPetResponse.getData().getEncryptedId());
                data.putString("pet_cat_id", getPetResponse.getData().getPetCategoryId());
                data.putString("lastVisitEncryptedId",lastVisitEncryptedId);
                data.putString("pet_image_url", getPetResponse.getData().getPetProfileImageUrl());

                petDetailsIntent.putExtras(data);
                startActivity(petDetailsIntent);
                
                
                break;

        }

    }

    @Override
    public void onResponse(Response arg0, String key) {
        reloadData = false;
        switch (key) {
            case "CheckPermission":
                try {
                    methods.customProgressDismiss();
                    CheckStaffPermissionResponse checkStaffPermissionResponse = (CheckStaffPermissionResponse) arg0.body();
                    int responseCode = Integer.parseInt(checkStaffPermissionResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        if (checkStaffPermissionResponse.getData().equals("true")) {
                            if (permissionId.equals("2")) {
                                Intent intent = new Intent(this, GetPetDetailsActivity.class);
                                intent.putExtra("pet_id", petId);
                                intent.putExtra("pet_category", getPetResponse.getData().getPetCategory());
                                intent.putExtra("pet_name", getPetResponse.getData().getPetName());
                                intent.putExtra("pet_sex", getPetResponse.getData().getPetSex());
                                intent.putExtra("pet_DOB", getPetResponse.getData().getDateOfBirth());
                                intent.putExtra("pet_age", getPetResponse.getData().getPetAge());
                                intent.putExtra("pet_size", getPetResponse.getData().getPetSize());
                                intent.putExtra("pet_breed", getPetResponse.getData().getPetBreed());
                                intent.putExtra("pet_color", getPetResponse.getData().getPetColor());
                                intent.putExtra("pet_parent", getPetResponse.getData().getPetParentName());
                                intent.putExtra("pet_parent_contact", getPetResponse.getData().getContactNumber());
                                intent.putExtra("image_url", getPetResponse.getData().getPetProfileImageUrl());
                                startActivityForResult(intent, 1);
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

            case "GetPetDetail":
                try {
                    add_Clinic_BT.setEnabled(true);
                    pet_profile_shimmer.setVisibility(View.GONE);
                    pet_full_details_SV.setVisibility(View.VISIBLE);
                    pet_profile_shimmer.stopShimmerAnimation();
                    edit_profile_RL.setVisibility(View.VISIBLE);
//                    image_edit_CV.setVisibility(View.VISIBLE);
                    getPetResponse = (GetPetResponse) arg0.body();
                    Log.e("GetPetDetail",methods.getRequestJson(getPetResponse));
                    int responseCode = Integer.parseInt(getPetResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        pet_name_TV.setText(getPetResponse.getData().getPetName());
                        pet_parent_name_TV.setText(getPetResponse.getData().getPetParentName());
                        parent_phone_TV.setText(getPetResponse.getData().getContactNumber());
                        pet_gender_TV.setText(getPetResponse.getData().getPetSex());
                        pet_breed_TV.setText(getPetResponse.getData().getPetBreed());
                        pet_reg__id_TV.setText(getPetResponse.getData().getPetUniqueId());
                        pet_age_TV.setText(getPetResponse.getData().getPetAge());
//                        Log.e("AGGRESSS",getPetResponse.getData().getAddress());
                        if (getPetResponse.getData().getAddress() == null) {
                            parent_location_CV.setVisibility(View.GONE);
                            parent_address_TV.setVisibility(View.GONE);
                        } else {
                            if (getPetResponse.getData().getAddress().equals("")){
                                parent_location_CV.setVisibility(View.GONE);
                                parent_address_TV.setVisibility(View.GONE);
                            }else {
                                parent_location_CV.setVisibility(View.VISIBLE);
                                parent_address_TV.setVisibility(View.VISIBLE);
                                parent_address_TV.setText(getPetResponse.getData().getAddress());
                            }

                        }
                        setImages();

                    } else if (responseCode == 614) {
                        Toast.makeText(this, getPetResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void setImages() {

        Glide.with(this)
                .load(getPetResponse.getData().getPetProfileImageUrl())
                .placeholder(R.drawable.empty_pet_image)
                .into(pet_profile_image_IV);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                GetPetListParams getPetListParams = new GetPetListParams();
                getPetListParams.setId(petId);
                GetPetListRequest getPetListRequest = new GetPetListRequest();
                getPetListRequest.setData(getPetListParams);
                if (methods.isInternetOn()) {
                    getPetlistData(getPetListRequest);
                } else {
                    methods.DialogInternet();
                }
            }
        }
    }

    @Override
    public void onError(Throwable t, String key) {

        Log.d("ERROR",t.getLocalizedMessage());
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (reloadData == false) {
//            GetPetListParams getPetListParams = new GetPetListParams();
//            getPetListParams.setId(petId);
//            GetPetListRequest getPetListRequest = new GetPetListRequest();
//            getPetListRequest.setData(getPetListParams);
//            if (methods.isInternetOn()) {
//                getPetlistData(getPetListRequest);
//            } else {
//                methods.DialogInternet();
//            }
//        }

    }
}
