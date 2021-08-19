package com.cynoteck.petofyOPHR.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.adapters.PetTypeListAdapter;
import com.cynoteck.petofyOPHR.adapters.ServiceTypeListAdpater;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.appointmentParams.AppointmentStatusParams;
import com.cynoteck.petofyOPHR.params.appointmentParams.AppointmentsStatusRequest;
import com.cynoteck.petofyOPHR.params.getPetListRequest.GetPetListParams;
import com.cynoteck.petofyOPHR.params.getPetListRequest.GetPetListRequest;
import com.cynoteck.petofyOPHR.params.uploadVetProfileImageParams.UploadProfileImageParams;
import com.cynoteck.petofyOPHR.params.uploadVetProfileImageParams.UploadVetProfileImageData;
import com.cynoteck.petofyOPHR.response.addPet.imageUpload.ImageResponse;
import com.cynoteck.petofyOPHR.response.getPetDetailsResponse.PetTypeList;
import com.cynoteck.petofyOPHR.response.onlineAppointmentOnOff.OnlineAppointmentResponse;
import com.cynoteck.petofyOPHR.response.updateProfileResponse.ServiceTypeList;
import com.cynoteck.petofyOPHR.response.updateProfileResponse.UserResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.JsonObject;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class ViewFullProfileVetActivity extends AppCompatActivity implements ApiResponse, View.OnClickListener {

    TextView vet_name_TV, vet_degree_TV, vet_phone_TV, vet_email_TV, vet_category_TV, vet_address_TV;
    RecyclerView vet_service_type_RV;
    ImageView edit_profile_image_IV, edit_profile_IV, back_arrow_IV, vet_image_TV,cover_image;
    MaterialCardView image_edit_CV;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor login_editor;
    Methods methods;
    UserResponse userResponse;
    PetTypeListAdapter petTypeListAdapter;
    ServiceTypeListAdpater serviceTypeListAdpater;
    Dialog dialog;
    private int GALLERY = 1, CAMERA = 2, USERUPDATION = 3;
    Bitmap bitmap, thumbnail;
    private static final String IMAGE_DIRECTORY = "/Picture";
    File catfile1 = null;
    ArrayList<ServiceTypeList> petService;
    ArrayList<PetTypeList> petType;
    ShimmerFrameLayout vet_profile_shimmer;
    ScrollView vet_full_details_SV;
    List<String> petServiceText;
    List<String> petServiceValue;


    List<String> petTypeText;
    List<String> petTypeValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_full_profile_vet);
        methods = new Methods(this);
        requestMultiplePermissions();
        inilization();
        Glide.with(this)
                .load(Config.user_Veterian_url)
                .placeholder(R.drawable.empty_vet_image)
                .into(vet_image_TV);
//        Glide.with(this)
//                .load(Config.coverimage)
//                .placeholder(R.drawable.empty_vet_image)
//                .into(cover_image);


        vet_name_TV.setText(Config.user_Veterian_name);
        if (methods.isInternetOn()) {
            getUserDetails();
        } else {
            methods.DialogInternet();
        }
        Log.d("TAG", "onCreate: ");

    }

    private void showPictureDialog() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        RelativeLayout select_camera = (RelativeLayout) dialog.findViewById(R.id.select_camera);
        RelativeLayout select_gallery = (RelativeLayout) dialog.findViewById(R.id.select_gallery);
        RelativeLayout cancel_dialog = (RelativeLayout) dialog.findViewById(R.id.cancel_dialog);

        select_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePhotoFromCamera();
            }
        });

        select_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePhotoFromGallary();
            }
        });

        cancel_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void choosePhotoFromGallary() {


        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {

        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);

    }

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                dialog.dismiss();
                Uri contentURI = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), contentURI);
                    vet_image_TV.setImageBitmap(bitmap);
                    saveImage(bitmap);


                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(ViewFullProfileVetActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            dialog.dismiss();
            if (data.getData() == null) {
                thumbnail = (Bitmap) data.getExtras().get("data");
                Log.e("jghl", "" + thumbnail);
                vet_image_TV.setImageBitmap(thumbnail);

                saveImage(thumbnail);
            } else {
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                    Glide.with(this)
                            .load(bitmap)
                            .into(vet_image_TV);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }
            Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show();
        } else if (requestCode == USERUPDATION) {
            if (resultCode == RESULT_OK) {
                setVetInfo();
                getUserDetails();
            }
        }
        return;
    }

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            catfile1 = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".png");
            catfile1.createNewFile();
            FileOutputStream fo = new FileOutputStream(catfile1);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{catfile1.getPath()},
                    new String[]{"image/png"}, null);
            fo.close();
            Log.d("TAG", "File Saved::---&gt;" + catfile1.getAbsolutePath());
            UploadImages(catfile1);
            return catfile1.getAbsolutePath();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    private void UploadImages(File absolutePath) {
        methods.showCustomProgressBarDialog(this);
        MultipartBody.Part userDpFilePart = null;
        if (absolutePath != null) {
            RequestBody userDpFile = RequestBody.create(MediaType.parse("image/*"), absolutePath);
            userDpFilePart = MultipartBody.Part.createFormData("file", absolutePath.getName(), userDpFile);
        }

        ApiService<ImageResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().uploadImages(Config.token, userDpFilePart), "UploadDocument");
        Log.e("DATALOG", "check1=> " + service);

    }

    private void requestMultiplePermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        android.Manifest.permission.CAMERA,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            //openSettingsDialog();
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
                        Toast.makeText(ViewFullProfileVetActivity.this, "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    private void setVetInfo() {
        Glide.with(this)
                .load(Config.user_Veterian_url)
                .placeholder(R.drawable.empty_vet_image)
                .into(vet_image_TV);
//        Glide.with(this)
//                .load(Config.coverimage)
//                .placeholder(R.drawable.empty_vet_image)
//                .into(cover_image);

        vet_name_TV.setText(Config.user_Veterian_name);
        vet_email_TV.setText(Config.user_Veterian_emial);
        vet_degree_TV.setText(Config.user_Veterian_study);
    }

    private void getUserDetails() {
        edit_profile_IV.setVisibility(View.INVISIBLE);
        image_edit_CV.setVisibility(View.INVISIBLE);
        vet_full_details_SV.setVisibility(View.GONE);
        vet_profile_shimmer.setVisibility(View.VISIBLE);
        vet_profile_shimmer.startShimmerAnimation();
        ApiService<UserResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getUserDetailsApi(Config.token), "GetUserDetails");
        Log.d("request", "getdetails");
    }

    private void inilization() {
        image_edit_CV = findViewById(R.id.image_edit_CV);
//        cover_image=findViewById(R.id.cover_image);
        vet_full_details_SV = findViewById(R.id.vet_full_details_SV);
        vet_profile_shimmer = findViewById(R.id.vet_profile_shimmer);
        vet_service_type_RV = findViewById(R.id.vet_service_type_RV);
        vet_image_TV = findViewById(R.id.vet_image_TV);
        vet_name_TV = findViewById(R.id.vet_name_TV);
        vet_degree_TV = findViewById(R.id.vet_degree_TV);
        vet_phone_TV = findViewById(R.id.vet_phone_TV);
        vet_email_TV = findViewById(R.id.vet_email_TV);
        vet_address_TV = findViewById(R.id.vet_address_TV);
        vet_category_TV = findViewById(R.id.vet_category_TV);
        edit_profile_IV = findViewById(R.id.edit_profile_IV);
        back_arrow_IV = findViewById(R.id.back_arrow_IV);
        edit_profile_image_IV = findViewById(R.id.edit_profile_image_IV);

        //on_click
        image_edit_CV.setOnClickListener(this);
        back_arrow_IV.setOnClickListener(this);
        edit_profile_IV.setOnClickListener(this);
        vet_image_TV.setOnClickListener(this);

    }

    @SuppressLint("NewApi")
    @Override
    public void onResponse(Response response, String key) {
        Log.d("ND", "onResponse: "+key);
        switch (key) {
            case "GetUserDetails":
                try {
                    vet_full_details_SV.setVisibility(View.VISIBLE);
                    edit_profile_IV.setVisibility(View.VISIBLE);
                    image_edit_CV.setVisibility(View.VISIBLE);
                    vet_profile_shimmer.setVisibility(View.GONE);
                    vet_profile_shimmer.stopShimmerAnimation();
                    userResponse = (UserResponse) response.body();
                    int responseCode = Integer.parseInt(userResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        Log.e("stydy",userResponse.getData().getVetQualifications());
                        petService = new ArrayList<>();
                        petServiceText = new ArrayList<>();
                        petServiceValue = new ArrayList<>();
                        petType = new ArrayList<>();
                        petTypeText = new ArrayList<>();
                        petTypeValue = new ArrayList<>();
                        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                        login_editor = sharedPreferences.edit();
                        login_editor.putString("first_name", userResponse.getData().getFirstName());
                        login_editor.putString("last_name", userResponse.getData().getLastName());
                        login_editor.putString("email", userResponse.getData().getEmail());
                        login_editor.putString("firstName", userResponse.getData().getFirstName());
                        login_editor.putString("lastName", userResponse.getData().getLastName());
                        login_editor.putString("address", userResponse.getData().getAddress());
                        login_editor.putString("profilePic", userResponse.getData().getProfileImageUrl());
                        login_editor.putString("coverimge",userResponse.getData().getCoverImageUrl());

                        login_editor.putString("vetid", userResponse.getData().getVetRegistrationNumber());
                        login_editor.putString("twoFactAuth", userResponse.getData().getEnableTwoStepVerification());
                        login_editor.putString("study", userResponse.getData().getVetQualifications());

                        login_editor.commit();
                        Config.coverimage=sharedPreferences.getString("coverimge","");
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
                        edit_profile_IV.setVisibility(View.VISIBLE);
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < userResponse.getData().getPetTypeList().size(); i++) {
                            PetTypeList petTypeList = new PetTypeList();
                            if (userResponse.getData().getPetTypeList().get(i).getStatus().equals("1")) {
                                petTypeList.setStatus(userResponse.getData().getPetTypeList().get(i).getStatus());
                                petTypeList.setText(userResponse.getData().getPetTypeList().get(i).getText());
                                petTypeList.setValue(userResponse.getData().getPetTypeList().get(i).getValue());
                                petType.add(petTypeList);

                            }
                        }
                        for (int i = 0; i < petType.size(); i++) {
                                petTypeText.add(petType.get(i).getText());
                                petTypeValue.add(petType.get(i).getValue());
                                stringBuilder.append(petTypeText.get(i)).append(", ");
                        }
                        String petCategoryStr = stringBuilder.toString();
                        if (petCategoryStr.length() > 0)
                            petCategoryStr = petCategoryStr.substring(0, petCategoryStr.length() - 2);
                        vet_category_TV.setText(petCategoryStr);
                        setVetInfo();
                        setServiceType();
                        petService = userResponse.getData().getServiceTypeList();
                        for (int i = 0; i < petService.size(); i++) {
                            if (petService.get(i).getStatus().equals("1")) {
                                petServiceText.add(petService.get(i).getText());
                                petServiceValue.add(petService.get(i).getValue());
                            }
                        }
                        setInfo();

//                        String my_url= "https://www.petofy.com/"+userResponse.getData().getCoverImageUrl();
//                        cover_image.setClipToOutline(true);
//                        Glide.with(this)
//                                .load(my_url)
//                                .into(cover_image);
//
                        if (!userResponse.getData().getProfileImageUrl().equals(null) ) {

                            Log.e("url", userResponse.getData().getProfileImageUrl());
                            Log.e("CoverImage", userResponse.getData().getCoverImageUrl());
                            Glide.with(this)
                                    .load(new URL(userResponse.getData().getProfileImageUrl()))
                                    .into(vet_image_TV);
                        }

                    } else if (responseCode == 614) {
                        Toast.makeText(this, userResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Try Again GetUserDetails !", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case "UploadDocument":
                try {
                    methods.customProgressDismiss();
                    Log.e("UploadDocument", response.body().toString());
                    ImageResponse imageResponse = (ImageResponse) response.body();
                    int responseCode = Integer.parseInt(imageResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        //Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                        Config.user_Veterian_url = imageResponse.getData().getDocumentUrl();
                        UploadProfileImageParams uploadProfileImageParams = new UploadProfileImageParams();
                        uploadProfileImageParams.setProfileImageUrl(imageResponse.getData().getDocumentUrl());
                        UploadVetProfileImageData uploadVetProfileImageData = new UploadVetProfileImageData();
                        uploadVetProfileImageData.setData(uploadProfileImageParams);
                        ApiService<JsonObject> service = new ApiService<>();
                        service.get(this, ApiClient.getApiInterface().updateProfileImage(Config.token, uploadVetProfileImageData), "UpdateProfileImage");
                        Log.d("UpdateProfileImage", uploadVetProfileImageData.toString());
                        Log.d("UpdateProfileImage ","upload document");

                        Toast.makeText(this, "Success in uploading", Toast.LENGTH_SHORT).show();

                    } else if (responseCode == 614) {
                        Toast.makeText(this, imageResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Try Again UploadDocument !", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

            case "UpdateProfileImage":
                try {
                    methods.customProgressDismiss();
//                    Log.d("UploadDocument", response.body().toString());
                    JsonObject jsonObject = new JsonObject();
                    jsonObject = (JsonObject) response.body();
                    int responseCode = Integer.parseInt(String.valueOf(jsonObject.getAsJsonObject("response").get("responseCode")));
                    if (responseCode == 109) {
//                        Log.d("UploadDocument ","Update prfile img");
//                        getUserDetails();

//                        Toast.makeText(this," Update profile image response code 109 "+ jsonObject.getAsJsonObject("response").get("responseMessage").toString(), Toast.LENGTH_SHORT).show();
                    } else if (responseCode == 614) {
                        Toast.makeText(this,"response code 614"+ jsonObject.getAsJsonObject("response").get("responseMessage").toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Try Again UpdateProfileImage!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
        }

    }

    private void setInfo() {
        vet_phone_TV.setText(userResponse.getData().getPhone());
        vet_address_TV.setText(userResponse.getData().getAddress());
    }

    private void setServiceType() {
        vet_service_type_RV.setLayoutFrozen(true);
        vet_service_type_RV.setNestedScrollingEnabled(false);
        ArrayList<ServiceTypeList> serviceTypeLists = new ArrayList<>();
        for (int i= 0; i < userResponse.getData().getServiceTypeList().size(); i++){
            ServiceTypeList serviceTypeList = new ServiceTypeList();
            if (userResponse.getData().getServiceTypeList().get(i).getStatus().equals("1")){
                serviceTypeList.setStatus(userResponse.getData().getServiceTypeList().get(i).getStatus());
                serviceTypeList.setText(userResponse.getData().getServiceTypeList().get(i).getText());
                serviceTypeList.setValue(userResponse.getData().getServiceTypeList().get(i).getValue());
                serviceTypeLists.add(serviceTypeList);
            }

        }
        Log.e("SERVICE",methods.getRequestJson(userResponse.getData().getServiceTypeList()));
        Log.e("SERVICE",methods.getRequestJson(serviceTypeLists));

        vet_service_type_RV.setLayoutManager(new GridLayoutManager(this, 2));
        serviceTypeListAdpater = new ServiceTypeListAdpater(this, serviceTypeLists);
        vet_service_type_RV.setAdapter(serviceTypeListAdpater);
        serviceTypeListAdpater.notifyDataSetChanged();
    }


    @Override
    public void onError(Throwable t, String key) {
//        methods.customProgressDismiss();
        Toast.makeText(this, "Some Error!", Toast.LENGTH_SHORT).show();
        Log.e("error", t.getLocalizedMessage());
    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.back_arrow_IV:
                onBackPressed();
                break;


            case R.id.image_edit_CV:
                showPictureDialog();

                break;


            case R.id.edit_profile_IV:

                Intent intent = new Intent(this, UpdateProfileActivity.class);
                intent.putExtra("activityName", "Edit");
                intent.putExtra("id", userResponse.getData().getId());
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
                intent.putExtra("description", userResponse.getData().getDescription());
                intent.putExtra("clinicName", userResponse.getData().getCompany());
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
                intent.putExtra("petService", petServiceText.stream().collect(Collectors.joining(",")));
                intent.putExtra("petServiceValue", petServiceValue.stream().collect(Collectors.joining(",")));
                intent.putExtra("petType", petTypeText.stream().collect(Collectors.joining(",")));
                intent.putExtra("petTypeValue", petTypeValue.stream().collect(Collectors.joining(",")));
                startActivityForResult(intent, USERUPDATION);
                break;

        }
    }


    @Override
    protected void onResume() {
        super.onResume();

    }
}
