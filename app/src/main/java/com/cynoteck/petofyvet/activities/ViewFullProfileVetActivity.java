package com.cynoteck.petofyvet.activities;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cynoteck.petofyvet.R;
import com.cynoteck.petofyvet.adapters.PetTypeListAdapter;
import com.cynoteck.petofyvet.adapters.ServiceTypeListAdpater;
import com.cynoteck.petofyvet.api.ApiClient;
import com.cynoteck.petofyvet.api.ApiResponse;
import com.cynoteck.petofyvet.api.ApiService;
import com.cynoteck.petofyvet.params.getPetListRequest.GetPetListParams;
import com.cynoteck.petofyvet.params.getPetListRequest.GetPetListRequest;
import com.cynoteck.petofyvet.response.addPet.imageUpload.ImageResponse;
import com.cynoteck.petofyvet.response.onlineAppointmentOnOff.OnlineAppointmentResponse;
import com.cynoteck.petofyvet.response.updateProfileResponse.UserResponse;
import com.cynoteck.petofyvet.utils.Config;
import com.cynoteck.petofyvet.utils.Methods;
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
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class ViewFullProfileVetActivity extends AppCompatActivity implements ApiResponse , View.OnClickListener {

    ImageView vet_image_CIV;
    TextView vet_name_TV, vet_office_TV,vet_study_TV, vet_id_TV,vet_details_TV,phone_one,phone_two,vet_email_id_TV,address_line_one_TV,address_line_two_TV,link_one_TV,link_two_TV;
    ImageView image_one, image_two, image_three, image_four,image_five,edit_image;
    SwitchCompat online_switch;
    Methods methods;
    UserResponse userResponse;
    RecyclerView pet_type_RV,service_type_RV;
    PetTypeListAdapter petTypeListAdapter;
    ServiceTypeListAdpater serviceTypeListAdpater;
    Dialog dialog;
    private int GALLERY = 1, CAMERA = 2;
    Bitmap bitmap, thumbnail;
    private static final String IMAGE_DIRECTORY = "/Picture";
    File catfile1 = null;
    String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_full_profile_vet);
        methods = new Methods(this);
        requestMultiplePermissions();
        inilization();
        setVetInfo();
        switchOnline();
        if (methods.isInternetOn()){
            getUserDetails();
        }else {
            methods.DialogInternet();
        }

    }
    private void showPictureDialog() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_layout);

        TextView select_camera = (TextView) dialog.findViewById(R.id.select_camera);
        TextView select_gallery = (TextView) dialog.findViewById(R.id.select_gallery);
        TextView cancel_dialog = (TextView) dialog.findViewById(R.id.cancel_dialog);

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
        dialog.dismiss();
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {

                Uri contentURI = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), contentURI);
                        vet_image_CIV.setImageBitmap(bitmap);
                        saveImage(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        }
        else if (requestCode == CAMERA) {

            if (data.getData() == null)
            {
                thumbnail = (Bitmap) data.getExtras().get("data");
                Log.e("jghl",""+thumbnail);
                    vet_image_CIV.setImageBitmap(thumbnail);
                    saveImage(thumbnail);
            }

            else{
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());

                        vet_image_CIV.setImageBitmap(bitmap);
                        saveImage(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }
            Toast.makeText(this, "Image Saved!", Toast.LENGTH_SHORT).show();
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
                        .getTimeInMillis() + ".jpg");
                catfile1.createNewFile();
                FileOutputStream fo = new FileOutputStream(catfile1);
                fo.write(bytes.toByteArray());
                MediaScannerConnection.scanFile(this,
                        new String[]{catfile1.getPath()},
                        new String[]{"image/jpeg"}, null);
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
        MultipartBody.Part userDpFilePart = null;
        if (absolutePath != null) {
            RequestBody userDpFile = RequestBody.create(MediaType.parse("image/*"), absolutePath);
            userDpFilePart = MultipartBody.Part.createFormData("file", absolutePath.getName(), userDpFile);
        }

        ApiService<ImageResponse> service = new ApiService<>();
        service.get( this, ApiClient.getApiInterface().uploadImages(Config.token,userDpFilePart), "UploadProfile");
        Log.e("DATALOG","check1=> "+service);

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
    private void switchOnline() {
        online_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    status= "1";
                    onlineAppoint(status);
                }else {
                    status ="0";
                    onlineAppoint(status);

                }
            }
        });
    }

    private void onlineAppoint(String value) {
        GetPetListParams getPetListParams = new GetPetListParams();
        getPetListParams.setId(value);
        GetPetListRequest getPetListRequest = new GetPetListRequest();
        getPetListRequest.setData(getPetListParams);
        ApiService<OnlineAppointmentResponse> service = new ApiService<>();
        service.get( this, ApiClient.getApiInterface().onlineAppointmentOnOff(Config.token,getPetListRequest), "OnlineAppoint");

    }

    private void setVetInfo() {
        Glide.with(this)
                .load(Config.user_Veterian_url)
                .into(vet_image_CIV);
        vet_name_TV.setText(Config.user_Veterian_name);
        vet_email_id_TV.setText(Config.user_Veterian_emial);
        vet_study_TV.setText(Config.user_Veterian_study);
        vet_id_TV.setText(Config.user_Veterian_id);
        if (Config.user_Veterian_online.equals("true")){
            online_switch.setChecked(true);
        }else {
            online_switch.setChecked(false);
        }
    }

    private void getUserDetails() {
        methods.showCustomProgressBarDialog(this);
        ApiService<UserResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getUserDetailsApi(Config.token), "GetUserDetails");
        Log.d("request","getDeatisl");
    }

    private void inilization() {
        service_type_RV=findViewById(R.id.service_type_RV);
        pet_type_RV=findViewById(R.id.pet_type_RV);
        vet_image_CIV = findViewById(R.id.vet_image_CIV);
        vet_name_TV = findViewById(R.id.vet_name_TV);
        vet_office_TV = findViewById(R.id.vet_office_TV);
        vet_study_TV = findViewById(R.id.vet_study_TV);
        vet_details_TV = findViewById(R.id.vet_details_TV);
        vet_id_TV = findViewById(R.id.vet_id_TV);
        phone_one = findViewById(R.id.phone_one);
        phone_two = findViewById(R.id.phone_two);
        vet_email_id_TV = findViewById(R.id.vet_email_id_TV);
        address_line_one_TV = findViewById(R.id.address_line_one_TV);
        address_line_two_TV = findViewById(R.id.address_line_two_TV);
        link_one_TV = findViewById(R.id.link_one_TV);
        link_two_TV = findViewById(R.id.link_two_TV);
        image_one = findViewById(R.id.image_one);
        image_two = findViewById(R.id.image_two);
        image_two = findViewById(R.id.image_two);
        image_three = findViewById(R.id.image_three);
        image_four = findViewById(R.id.image_four);
        image_five = findViewById(R.id.image_five);
        online_switch = findViewById(R.id.online_switch);
        edit_image=findViewById(R.id.edit_image);

        edit_image.setOnClickListener(this);
        vet_image_CIV.setOnClickListener(this);
        image_one.setOnClickListener(this);
        image_two.setOnClickListener(this);
        image_three.setOnClickListener(this);
        image_four.setOnClickListener(this);
        image_five.setOnClickListener(this);


    }

    @Override
    public void onResponse(Response response, String key) {
        switch (key){

            case "GetUserDetails":
                try {
                    methods.customProgressDismiss();
                    Log.d("GetUserDetails",response.body().toString());
                    userResponse = (UserResponse) response.body();
                    int responseCode = Integer.parseInt(userResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        edit_image.setVisibility(View.VISIBLE);
                        setPetType(); setServiceType(); setImages(); setInfo();
                    }else if (responseCode==614){
                        Toast.makeText(this, userResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }

                break;
            case "UploadProfile":
                try {
                    Log.d("UploadDocument",response.body().toString());
                    ImageResponse imageResponse = (ImageResponse) response.body();
                    int responseCode = Integer.parseInt(imageResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                            Config.user_Veterian_url=imageResponse.getData().getDocumentUrl();
                    }else if (responseCode==614){
                        Toast.makeText(this, imageResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }

                break;

            case "OnlineAppoint":
                try {
                    Log.d("UploadDocument",response.body().toString());
                    OnlineAppointmentResponse onlineAppointmentResponse = (OnlineAppointmentResponse) response.body();
                    int responseCode = Integer.parseInt(onlineAppointmentResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        if (status.equals("1")){
                            SharedPreferences sharedPreferences = getSharedPreferences("userdetails", 0);
                            SharedPreferences.Editor login_editor;
                            login_editor = sharedPreferences.edit();
                            login_editor.putString("onlineAppoint", "true");
                            login_editor.commit();
                            Config.user_Veterian_online ="true";

                        }else {
                            SharedPreferences sharedPreferences = getSharedPreferences("userdetails", 0);
                            SharedPreferences.Editor login_editor;
                            login_editor = sharedPreferences.edit();
                            login_editor.putString("onlineAppoint", "false");
                            login_editor.commit();
                            Config.user_Veterian_online ="false";

                        }
                    }else if (responseCode==614){
                        Toast.makeText(this, onlineAppointmentResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
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

    private void setInfo() {
        vet_office_TV.setText("Office :"+ userResponse.getData().getCompany());
        vet_details_TV.setText(userResponse.getData().getDescription());
        phone_one.setText(userResponse.getData().getPhone());
        if (userResponse.getData().getPhone2().isEmpty()){
            phone_two.setVisibility(View.GONE);
        }else {
            phone_two.setText(userResponse.getData().getPhone2());
        }
        link_one_TV.setText(userResponse.getData().getSocialMediaUrl());
        address_line_one_TV.setText(userResponse.getData().getAddress());
        address_line_two_TV.setText(userResponse.getData().getAddress2()+", "+userResponse.getData().getPostalCode());

    }

    private void setServiceType() {
        service_type_RV.setLayoutManager(new GridLayoutManager(this, 2));
        serviceTypeListAdpater  = new ServiceTypeListAdpater(this,userResponse.getData().getServiceTypeList());
        service_type_RV.setAdapter(serviceTypeListAdpater);
        serviceTypeListAdpater.notifyDataSetChanged();
    }

    private void setPetType() {
        pet_type_RV.setLayoutManager(new GridLayoutManager(this, 2));
        petTypeListAdapter  = new PetTypeListAdapter(this,userResponse.getData().getPetTypeList());
        pet_type_RV.setAdapter(petTypeListAdapter);
        petTypeListAdapter.notifyDataSetChanged();
    }

    private void setImages() {
        if (userResponse.getData().getFirstServiceImageUrl().isEmpty()){
            image_one.setVisibility(View.INVISIBLE);
        }else {
            Glide.with(this)
                    .load(userResponse.getData().getFirstServiceImageUrl())
                    .into(image_one);
        }
        if (userResponse.getData().getSecondServiceImageUrl().isEmpty()){
            image_two.setVisibility(View.INVISIBLE);

        }else {
            Glide.with(this)
                    .load(userResponse.getData().getSecondServiceImageUrl())
                    .into(image_two);
        }if (userResponse.getData().getThirdServiceImageUrl().isEmpty()){
            image_three.setVisibility(View.INVISIBLE);

        }else {
            Glide.with(this)
                    .load(userResponse.getData().getThirdServiceImageUrl())
                    .into(image_three);
        }if (userResponse.getData().getFourthServiceImageUrl().isEmpty()){
            image_four.setVisibility(View.INVISIBLE);

        }else {
            Glide.with(this)
                    .load(userResponse.getData().getFourthServiceImageUrl())
                    .into(image_four);
        }if (userResponse.getData().getFifthServiceImageUrl().isEmpty()){
            image_five.setVisibility(View.INVISIBLE);
        }else {
            Glide.with(this)
                    .load(userResponse.getData().getFifthServiceImageUrl())
                    .into(image_five);
        }
    }

    @Override
    public void onError(Throwable t, String key) {
        methods.customProgressDismiss();
        Toast.makeText(this, "Some Error!", Toast.LENGTH_SHORT).show();
        Log.e("error",t.getLocalizedMessage());
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.vet_image_CIV:
                showPictureDialog();

                break;

            case R.id.image_one:


                break;

            case R.id.image_two:


                break;

            case R.id.image_three:


                break;

            case R.id.image_four:


                break;

            case R.id.image_five:


                break;

            case R.id.edit_image:

                Intent intent = new Intent(this,UpdateProfileActivity.class);
                intent.putExtra("id",userResponse.getData().getId());
                intent.putExtra("password",userResponse.getData().getPassword());
                intent.putExtra("firstName",userResponse.getData().getFirstName());
                intent.putExtra("lastName",userResponse.getData().getLastName());
                intent.putExtra("email",userResponse.getData().getEmail());
                intent.putExtra("phone",userResponse.getData().getPhone());
                intent.putExtra("address",userResponse.getData().getAddress());
                intent.putExtra("country",userResponse.getData().getCountryName());
                intent.putExtra("state",userResponse.getData().getStateName());
                intent.putExtra("city",userResponse.getData().getCityName());
                intent.putExtra("pincode",userResponse.getData().getPostalCode());
                intent.putExtra("website",userResponse.getData().getWebsite());
                intent.putExtra("socialMedia",userResponse.getData().getSocialMediaUrl());
                intent.putExtra("vetRegNo",userResponse.getData().getVetRegistrationNumber());
                intent.putExtra("vetStudy",userResponse.getData().getVetQualifications());
                intent.putExtra("category",userResponse.getData().getCategories());
                intent.putExtra("service",userResponse.getData().getServices());
                intent.putExtra("serviceImage1",userResponse.getData().getFirstServiceImageUrl());
                intent.putExtra("serviceImage2",userResponse.getData().getSecondServiceImageUrl());
                intent.putExtra("serviceImage3",userResponse.getData().getThirdServiceImageUrl());
                intent.putExtra("serviceImage4",userResponse.getData().getFourthServiceImageUrl());
                intent.putExtra("serviceImage5",userResponse.getData().getFirstServiceImageUrl());



                startActivity(intent);
                break;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Config.backCall.equals("hit")) {
            setVetInfo();
            Config.backCall ="";
            getUserDetails();
        }

    }
}
