package com.cynoteck.petofyOPHR.activities;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import com.bumptech.glide.Glide;
import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.getPetListRequest.GetPetListParams;
import com.cynoteck.petofyOPHR.params.getPetListRequest.GetPetListRequest;
import com.cynoteck.petofyOPHR.params.petBreedRequest.BreedParams;
import com.cynoteck.petofyOPHR.params.petBreedRequest.BreedRequest;
import com.cynoteck.petofyOPHR.params.updateRequest.updateParamRequest.UpdatePetParam;
import com.cynoteck.petofyOPHR.params.updateRequest.updateParamRequest.UpdatePetRequest;
import com.cynoteck.petofyOPHR.response.addPet.addPetResponse.AddPetValueResponse;
import com.cynoteck.petofyOPHR.response.addPet.breedResponse.BreedCatRespose;
import com.cynoteck.petofyOPHR.response.addPet.imageUpload.ImageResponse;
import com.cynoteck.petofyOPHR.response.addPet.petAgeResponse.PetAgeValueResponse;
import com.cynoteck.petofyOPHR.response.addPet.petColorResponse.PetColorValueResponse;
import com.cynoteck.petofyOPHR.response.addPet.petSizeResponse.PetSizeValueResponse;
import com.cynoteck.petofyOPHR.response.addPet.uniqueIdResponse.UniqueResponse;
import com.cynoteck.petofyOPHR.response.getPetDetailsResponse.GetPetResponse;
import com.cynoteck.petofyOPHR.response.updateProfileResponse.PetTypeResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.MediaUtils;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.google.android.material.card.MaterialCardView;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class GetPetDetailsActivity extends AppCompatActivity implements View.OnClickListener, ApiResponse,MediaUtils.GetImg {
    Methods methods;
    TextView peto_details_reg_number;
    AppCompatSpinner add_details_pet_type,add_details_pet_age,add_details_pet_sex,add_details_pet_breed,add_detils_pet_color,
            add_details_pet_size;
    EditText pet_details_name,pet_details_parent_name,pet_deatils_contact_number,pet_deatils_description,
            pet_details_address;
    TextView calenderTextViewDetails;
    CircleImageView pet_Details_profile_image;
    ImageView service_details_cat_img_one,service_details_cat_img_two,service_details_cat_img_three,
            service_details_cat_img_four,service_detils_cat_img_five,id_card;
    MaterialCardView back_arrow_CV;
    Button pet_update;
    DatePickerDialog picker;

    ArrayList<String> petType;
    ArrayList<String> petBreed;
    ArrayList<String> petAge;
    ArrayList<String> petColor;
    ArrayList<String> petSize;
    ArrayList<String> petSex;

    HashMap<String,String> petTypeHashMap=new HashMap<>();
    HashMap<String,String> petBreedHashMap=new HashMap<>();
    HashMap<String,String> petAgeHashMap=new HashMap<>();
    HashMap<String,String> petColorHashMap=new HashMap<>();
    HashMap<String,String> petSizeHashMap=new HashMap<>();
    HashMap<String,String> petSexHashMap=new HashMap<>();

    private static final String IMAGE_DIRECTORY = "/Picture";
    private int GALLERY = 1, CAMERA = 2;
    File file = null;
    File fileImg1 = null;
    File fileImg2 = null;
    File fileImg3 = null;
    File fileImg4 = null;
    File fileImg5 = null;
    Bitmap bitmap, thumbnail;
    String capImage;
    MediaUtils mediaUtils;

    String pet_id = "",currentDateandTime="",strPetCategory="",strPetName="",strPetParentName="",image_url="",
            strPetContactNumber="",strPetDescription="",strPetAdress="",strPetBirthDay="",
            strSpnerItemPetNm="",getStrSpnerItemPetNmId="",strSpnrBreed="",strSpnrBreedId="",petUniqueId="",
            strSpnrAge="",strSpnrAgeId="",strSpnrColor="",strSpnrColorId="",strSpnrSize="",strSpneSizeId="",
            strSpnrSex="",strSpnrSexId="",selctProflImage="0",selctImgOne="0",selctImgtwo="0",
            slctImgThree="0",slctImgFour="0",slctImgFive="0",strProfileImgUrl="",strFirstImgUrl="",strSecondImgUrl="",
            strThirdImgUrl="",strFourthImUrl="",strFifthImgUrl="",strDateofBirthObject="";
    Dialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_pet_details);
        methods = new Methods(this);
        mediaUtils = new MediaUtils(this);
        currentDateAndTime();
        Bundle extras = getIntent().getExtras();
        init();
        requestMultiplePermissions();
        petSex=new ArrayList<>();
        petSex.add("Pet Sex");
        petSex.add("Male");
        petSex.add("Female");

        petSexHashMap.put("Pet Sex","0");
        petSexHashMap.put("Male","1");
        petSexHashMap.put("Female","2");



        if (extras != null) {
            pet_id = extras.getString("pet_id");
            strSpnerItemPetNm = extras.getString("pet_category");
            strPetName = extras.getString("pet_name");
            strSpnrSex = extras.getString("pet_sex");
            strDateofBirthObject = extras.getString("pet_DOB");
            strSpnrAge = extras.getString("pet_age");
            strSpnrSize = extras.getString("pet_size");
            strSpnrBreed = extras.getString("pet_breed");
            strSpnrColor = extras.getString("pet_color");
            strPetParentName = extras.getString("pet_parent");
            strPetContactNumber = extras.getString("pet_parent_contact");
            image_url = extras.getString("image_url");
            Log.d("jjsjsjjs",""+image_url);

            if(image_url!=null)
           {
               Glide.with(this)
                       .load(image_url)
                       .placeholder(R.drawable.empty_pet_image)
                       .into(pet_Details_profile_image);
           }


            pet_details_name.setText(strPetName);
            pet_details_parent_name.setText(strPetParentName);
            pet_deatils_contact_number.setText(strPetContactNumber);
        }
        GetPetListParams getPetListParams = new GetPetListParams();
        getPetListParams.setId(pet_id);
        GetPetListRequest getPetListRequest = new GetPetListRequest();
        getPetListRequest.setData(getPetListParams);
        if(methods.isInternetOn())
        {
            getPetlistData(getPetListRequest);
            petTypee();
            genaretePetUniqueKey();
            setSpinnerPetSex();
        }
        else
        {
            methods.DialogInternet();
        }
    }

    private void init() {

        peto_details_reg_number=findViewById(R.id.peto_details_reg_number);
        add_details_pet_type=findViewById(R.id.add_details_pet_type);
        add_details_pet_age=findViewById(R.id.add_details_pet_age);
        add_details_pet_sex=findViewById(R.id.add_details_pet_sex);
        add_details_pet_breed=findViewById(R.id.add_details_pet_breed);
        add_detils_pet_color=findViewById(R.id.add_detils_pet_color);
        add_details_pet_size=findViewById(R.id.add_details_pet_size);
        pet_details_name=findViewById(R.id.pet_details_name);
        pet_details_parent_name=findViewById(R.id.pet_details_parent_name);
        pet_deatils_contact_number=findViewById(R.id.pet_deatils_contact_number);
        pet_deatils_description=findViewById(R.id.pet_deatils_description);
        pet_details_address=findViewById(R.id.pet_details_address);
        calenderTextViewDetails=findViewById(R.id.calenderTextViewDetails);
        pet_Details_profile_image=findViewById(R.id.pet_Details_profile_image);
        service_details_cat_img_one=findViewById(R.id.service_details_cat_img_one);
        service_details_cat_img_two=findViewById(R.id.service_details_cat_img_two);
        service_details_cat_img_three=findViewById(R.id.service_details_cat_img_three);
        service_details_cat_img_four=findViewById(R.id.service_details_cat_img_four);
        service_detils_cat_img_five=findViewById(R.id.service_detils_cat_img_five);
        back_arrow_CV=findViewById(R.id.back_arrow_CV);
        pet_update=findViewById(R.id.pet_submit);
        //id_card=findViewById(R.id.id_card);

        back_arrow_CV.setOnClickListener(this);
        pet_update.setOnClickListener(this);
        calenderTextViewDetails.setOnClickListener(this);
        pet_Details_profile_image.setOnClickListener(this);
        pet_Details_profile_image.setOnClickListener(this);
        service_details_cat_img_one.setOnClickListener(this);
        service_details_cat_img_two.setOnClickListener(this);
        service_details_cat_img_three.setOnClickListener(this);
        service_details_cat_img_four.setOnClickListener(this);
        service_detils_cat_img_five.setOnClickListener(this);
        peto_details_reg_number.setOnClickListener(this);
       // id_card.setOnClickListener(this);

    }

    private void getPetlistData(GetPetListRequest getPetListRequest) {
        methods.showCustomProgressBarDialog(this);
        ApiService<GetPetResponse> service = new ApiService<>();
        service.get( this, ApiClient.getApiInterface().getPetDetails(Config.token,getPetListRequest), "GetPetDetail");
        Log.e("DATALOG","check1=> "+getPetListRequest);
    }


    private void currentDateAndTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, d MMM yyyy h:mm:ss a", Locale.getDefault());
        currentDateandTime = sdf.format(new Date());
        Log.d("currentDateandTime",""+currentDateandTime);
    }


    private void petTypee() {
        ApiService<PetTypeResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().petTypeApi(), "GetPetTypes");
    }

    private void genaretePetUniqueKey() {
        ApiService<UniqueResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getGeneratePetUniqueId(Config.token), "GeneratePetUniqueId");
    }

    private void getPetBreed() {
        BreedRequest breedRequest = new BreedRequest();
        breedRequest.setGetAll("false");
        if(!getStrSpnerItemPetNmId.equals("0"))
            breedRequest.setPetCategoryId(getStrSpnerItemPetNmId);
        else
            breedRequest.setPetCategoryId("1");
        BreedParams breedParams = new BreedParams();
        breedParams.setData(breedRequest);

        ApiService<BreedCatRespose> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getGetPetBreedApi(breedParams), "GetPetBreed");
        Log.d("Diolog_Breed","===>"+breedParams);
    }

    private void getPetAge() {
        BreedRequest breedRequest = new BreedRequest();
        breedRequest.setGetAll("false");
        if(!getStrSpnerItemPetNmId.equals("0"))
            breedRequest.setPetCategoryId(getStrSpnerItemPetNmId);
        else
            breedRequest.setPetCategoryId("1");
        BreedParams breedParams = new BreedParams();
        breedParams.setData(breedRequest);

        ApiService<PetAgeValueResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getGetPetAgeApi(breedParams), "GetPetAge");
    }

    private void getPetColor() {
        BreedRequest breedRequest = new BreedRequest();
        breedRequest.setGetAll("false");
        if(!getStrSpnerItemPetNmId.equals("0"))
            breedRequest.setPetCategoryId(getStrSpnerItemPetNmId);
        else
            breedRequest.setPetCategoryId("1");
        BreedParams breedParams = new BreedParams();
        breedParams.setData(breedRequest);

        ApiService<PetColorValueResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getGetPetColorApi(breedParams), "GetPetColor");
    }

    private void getPetSize() {
        BreedRequest breedRequest = new BreedRequest();
        breedRequest.setGetAll("false");
        if(!getStrSpnerItemPetNmId.equals("0"))
            breedRequest.setPetCategoryId(getStrSpnerItemPetNmId);
        else
            breedRequest.setPetCategoryId("1");
        BreedParams breedParams = new BreedParams();
        breedParams.setData(breedRequest);

        ApiService<PetSizeValueResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getGetPetSizeApi(breedParams), "GetPetSize");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.pet_submit:
                strPetName= pet_details_name.getText().toString().trim();
                strPetParentName = pet_details_parent_name.getText().toString().trim();
                strPetContactNumber = pet_deatils_contact_number.getText().toString().trim();
                strPetDescription = pet_deatils_description.getText().toString().trim();
                strPetAdress = pet_details_address.getText().toString().trim();
                strPetBirthDay = calenderTextViewDetails.getText().toString().trim();

                if(strPetName.isEmpty())
                {
                    pet_details_name.setError("Enter Pet Name");
                    pet_details_parent_name.setError(null);
                    pet_deatils_contact_number.setError(null);
                    pet_deatils_description.setError(null);
                    pet_details_address.setError(null);
                    calenderTextViewDetails.setError(null);
                }
                else if(strPetParentName.isEmpty())
                {
                    pet_details_name.setError(null);
                    pet_details_parent_name.setError("Enter Parent Name");
                    pet_deatils_contact_number.setError(null);
                    pet_deatils_description.setError(null);
                    pet_details_address.setError(null);
                    calenderTextViewDetails.setError(null);
                }
                else if(strPetContactNumber.isEmpty())
                {
                    pet_details_name.setError(null);
                    pet_details_parent_name.setError(null);
                    pet_deatils_contact_number.setError("Enter Contact Number");
                    pet_deatils_description.setError(null);
                    pet_details_address.setError(null);
                    calenderTextViewDetails.setError(null);
                }  else if(strPetContactNumber.length()<10)
                {
                    pet_details_name.setError(null);
                    pet_details_parent_name.setError(null);
                    pet_deatils_contact_number.setError("Invalid number !");
                    pet_deatils_description.setError(null);
                    pet_details_address.setError(null);
                    calenderTextViewDetails.setError(null);
                }
//                else if(strPetDescription.isEmpty())
//                {
//                    pet_details_name.setError(null);
//                    pet_details_parent_name.setError(null);
//                    pet_deatils_contact_number.setError(null);
//                    pet_deatils_description.setError("Enter Description");
//                    pet_details_address.setError(null);
//                    calenderTextViewDetails.setError(null);
//                }
//                else if(strPetAdress.isEmpty())
//                {
//                    pet_details_name.setError(null);
//                    pet_details_parent_name.setError(null);
//                    pet_deatils_contact_number.setError(null);
//                    pet_deatils_description.setError(null);
//                    pet_details_address.setError("Enter Pet Address");
//                    calenderTextViewDetails.setError(null);
//                }
                else if(strPetBirthDay.isEmpty())
                {
                    pet_details_name.setError(null);
                    pet_details_parent_name.setError(null);
                    pet_deatils_contact_number.setError(null);
                    pet_deatils_description.setError(null);
                    pet_details_address.setError(null);
                    calenderTextViewDetails.setError("Pet YOB");
                }
                //pet size and color.
                else if(strSpnerItemPetNm.isEmpty()||(strSpnerItemPetNm.equals("Select Pet Type")))
                {
                    Toast.makeText(this, "Select Type!!", Toast.LENGTH_SHORT).show();
                }
                else if(strSpnrBreed.isEmpty()||(strSpnrBreed.equals("Pet Breed")))
                {
                    Toast.makeText(this, "Select Breed!!", Toast.LENGTH_SHORT).show();
                }
//                else if(strSpnrAge.isEmpty()||(strSpnrAge.equals("Select Pet Age")))
//                {
//                    Toast.makeText(this, "Select Pet Age!!", Toast.LENGTH_SHORT).show();
//                }
                else if(strSpnrSex.isEmpty()||(strSpnrSex.equals("Pet Sex")))
                {
                    Toast.makeText(this, "Select Pet Sex!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    pet_details_name.setError(null);
                    pet_details_parent_name.setError(null);
                    pet_deatils_contact_number.setError(null);
                    pet_deatils_description.setError(null);
                    pet_details_address.setError(null);
                    calenderTextViewDetails.setError(null);
                    Log.d("hahahah",""+getStrSpnerItemPetNmId+" "+strSpnrSexId+" "+strSpnrAgeId+" "+strSpneSizeId+
                            " "+strSpnrColorId+" "+strSpnrBreedId+" "+strPetName+" "+strPetBirthDay+" "+strPetDescription+" "+currentDateandTime);
                    UpdatePetRequest updatePetRequest = new UpdatePetRequest();
                    UpdatePetParam data = new UpdatePetParam();
                    data.setId(pet_id);
                    data.setPetCategoryId(getStrSpnerItemPetNmId);
                    data.setPetSexId(strSpnrSexId);
                    data.setPetAgeId("1.0");
                    data.setPetSizeId("0");
                    data.setPetColorId(strSpnrColorId);
                    data.setPetBreedId(strSpnrBreedId);
                    data.setPetName(strPetName);
                    data.setPetParentName(strPetParentName);
                    data.setContactNumber(strPetContactNumber);
                    if (strPetAdress.equals("")){
                        data.setAddress(null);
                    }else {
                        data.setAddress(strPetAdress);
                    }
                    data.setDescription("Description");
                    data.setCreateDate(currentDateandTime);
                    data.setDateOfBirth(strPetBirthDay);
                    data.setPetProfileImageUrl(strProfileImgUrl);
                    data.setFirstServiceImageUrl(strFirstImgUrl);
                    data.setSecondServiceImageUrl(strSecondImgUrl);
                    data.setThirdServiceImageUrl(strThirdImgUrl);
                    data.setFourthServiceImageUrl(strFourthImUrl);
                    data.setFifthServiceImageUrl(strFifthImgUrl);
                    updatePetRequest.setAddPetParams(data);
                    if(methods.isInternetOn())
                    {
                        updatePetDetails(updatePetRequest);
                    }
                    else
                    {
                        methods.DialogInternet();
                    }
                }
                break;

            case R.id.calenderTextViewDetails:
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(GetPetDetailsActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                calenderTextViewDetails.setText(Config.changeDateFormat(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year));
                            }
                        }, year, month, day);
                picker.getDatePicker().setMaxDate(cldr.getTimeInMillis());
                picker.show();
                break;
            case R.id.pet_Details_profile_image:
                selctProflImage = "1";
                showPictureDialog();
                break;
            case R.id.service_details_cat_img_one:
                selctImgOne="1";
                showPictureDialog();
                break;
            case R.id.service_details_cat_img_two:
                selctImgtwo="1";
                showPictureDialog();
                break;
            case R.id.service_details_cat_img_three:
                slctImgThree="1";
                showPictureDialog();
                break;
            case R.id.service_details_cat_img_four:
                slctImgFour="1";
                showPictureDialog();
                break;
            case R.id.service_detils_cat_img_five:
                slctImgFive="1";
                showPictureDialog();
                break;

            case R.id.back_arrow_CV:
                onBackPressed();
                break;
//            case R.id.id_card:
//                break;

        }

    }

    @Override
    public void onResponse(Response arg0, String key) {
        switch (key) {
            case "GetPetDetail":
                try {
                    methods.customProgressDismiss();
                    Log.d("GetPetDetail", arg0.body().toString());
                    GetPetResponse getPetResponse = (GetPetResponse) arg0.body();
                    int responseCode = Integer.parseInt(getPetResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        //Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                        strProfileImgUrl = getPetResponse.getData().getPetProfileImageUrl();
                        pet_details_name.setText(getPetResponse.getData().getPetName());
                        pet_details_parent_name.setText(getPetResponse.getData().getPetParentName());
                        pet_deatils_contact_number.setText(getPetResponse.getData().getContactNumber());
                        pet_details_address.setText(getPetResponse.getData().getAddress());
                        calenderTextViewDetails.setText(getPetResponse.getData().getDateOfBirth());
                        peto_details_reg_number.setText(getPetResponse.getData().getPetUniqueId());
                        pet_deatils_description.setText(getPetResponse.getData().getDescription());
                        pet_details_address.setText(getPetResponse.getData().getAddress());

                    } else if (responseCode == 614) {
                        Toast.makeText(this, getPetResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case "GetPetTypes":
                try {
                    Log.d("GetPetTypes",arg0.body().toString());
                    PetTypeResponse petTypeResponse = (PetTypeResponse) arg0.body();
                    int responseCode = Integer.parseInt(petTypeResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        petType=new ArrayList<>();
                        petType.add("Select Pet Type");
                        petTypeHashMap.put("Select Pet Type","0");
                        Log.d("lalal",""+petTypeResponse.getData().size());
                        for(int i=0; i<petTypeResponse.getData().size(); i++){
                            Log.d("petttt",""+petTypeResponse.getData().get(i).getPetType1());
                            petType.add(petTypeResponse.getData().get(i).getPetType1());
                            petTypeHashMap.put(petTypeResponse.getData().get(i).getPetType1(),petTypeResponse.getData().get(i).getId());
                        }
                        setPetTypeSpinner();

                    }else if (responseCode==614){
                        Toast.makeText(this, petTypeResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            case "GetPetBreed":
                try {
                    Log.d("GetPetBreed",arg0.body().toString());
                    BreedCatRespose breedCatRespose = (BreedCatRespose) arg0.body();
                    int responseCode = Integer.parseInt(breedCatRespose.getResponse().getResponseCode());
                    if (responseCode== 109){
                        petBreed=new ArrayList<>();
                        petBreed.add("Pet Breed");
                        Log.d("lalal",""+breedCatRespose.getData().size());
                        for(int i=0; i<breedCatRespose.getData().size(); i++){
                            Log.d("petttt",""+breedCatRespose.getData().get(i).getBreed());
                            petBreed.add(breedCatRespose.getData().get(i).getBreed());
                            petBreedHashMap.put(breedCatRespose.getData().get(i).getBreed(),breedCatRespose.getData().get(i).getId());
                        }
                        setPetBreeSpinner();
                    }else if (responseCode==614){
                        Toast.makeText(this, breedCatRespose.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case "GetPetAge":
                try {
                    Log.d("GetPetAge",arg0.body().toString());
                    PetAgeValueResponse petAgeValueResponse = (PetAgeValueResponse) arg0.body();
                    int responseCode = Integer.parseInt(petAgeValueResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        petAge=new ArrayList<>();
                        petAge.add("Select Pet Age");
                        Log.d("lalal",""+petAgeValueResponse.getData().size());
                        for(int i=0; i<petAgeValueResponse.getData().size(); i++){
                            Log.d("petttt",""+petAgeValueResponse.getData().get(i).getAge());
                            petAge.add(petAgeValueResponse.getData().get(i).getAge());
                            petAgeHashMap.put(petAgeValueResponse.getData().get(i).getAge(),petAgeValueResponse.getData().get(i).getId());
                        }
                        setPetAgeSpinner();
                    }else if (responseCode==614){
                        Toast.makeText(this, petAgeValueResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;

            case "GetPetColor":
                try {
                    Log.d("GetPetColor",arg0.body().toString());
                    PetColorValueResponse petColorValueResponse = (PetColorValueResponse) arg0.body();
                    int responseCode = Integer.parseInt(petColorValueResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        petColor=new ArrayList<>();
                        petColor.add("Pet Color");
                        Log.d("lalal",""+petColorValueResponse.getData().size());
                        for(int i=0; i<petColorValueResponse.getData().size(); i++){
                            Log.d("petttt",""+petColorValueResponse.getData().get(i).getColor());
                            petColor.add(petColorValueResponse.getData().get(i).getColor());
                            petColorHashMap.put(petColorValueResponse.getData().get(i).getColor(),petColorValueResponse.getData().get(i).getId());
                        }
                        setPetColorSpinner();
                    }else if (responseCode==614){
                        Toast.makeText(this, petColorValueResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case "GetPetSize":
                try {
                    Log.d("GetPetSize",arg0.body().toString());
                    PetSizeValueResponse petSizeValueResponse = (PetSizeValueResponse) arg0.body();
                    int responseCode = Integer.parseInt(petSizeValueResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        petSize=new ArrayList<>();
                        petSize.add("Pet Size");
                        Log.d("lalal",""+petSizeValueResponse.getData().size());
                        for(int i=0; i<petSizeValueResponse.getData().size(); i++){
                            Log.d("petttt",""+petSizeValueResponse.getData().get(i).getSize());
                            petSize.add(petSizeValueResponse.getData().get(i).getSize());
                            petSizeHashMap.put(petSizeValueResponse.getData().get(i).getSize(),petSizeValueResponse.getData().get(i).getId());
                        }
                        setPetSizeSpinner();
                    }else if (responseCode==614){
                        Toast.makeText(this, petSizeValueResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;

            case "UpdatePetDetails":
                try {
                    methods.customProgressDismiss();
                    Config.isUpdated = true;
                    Config.new_pet_add=true;
                    Log.d("UpdatePetDetails",arg0.body().toString());
                    AddPetValueResponse addPetValueResponse = (AddPetValueResponse) arg0.body();
                    int responseCode = Integer.parseInt(addPetValueResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        setResult(RESULT_OK);
                        Toast.makeText(this, "Update Succesfully ", Toast.LENGTH_SHORT).show();
                        finish();
                    }else if (responseCode==614){
                        Toast.makeText(this, addPetValueResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case "UploadDocument":
                try {
                    methods.customProgressDismiss();
                    Log.d("UploadDocument",arg0.body().toString());
                    ImageResponse imageResponse = (ImageResponse) arg0.body();
                    int responseCode = Integer.parseInt(imageResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        //Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
                        Glide.with(this)
                                .load(imageResponse.getData().getDocumentUrl())
                                .placeholder(R.drawable.empty_pet_image)
                                .into(pet_Details_profile_image);
                        if(selctProflImage.equals("1")){
                            strProfileImgUrl=imageResponse.getData().getDocumentUrl();
                            selctProflImage="0";
                        }
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
        }


    }

    @Override
    public void onError(Throwable t, String key) {
        methods.customProgressDismiss();
        Toast.makeText(this, "Please try again!", Toast.LENGTH_SHORT).show();

    }

    private void setPetTypeSpinner() {
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,petType);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        add_details_pet_type.setAdapter(aa);
        if (!strSpnerItemPetNm.equals("")) {
            int spinnerPosition = aa.getPosition(strSpnerItemPetNm);
            add_details_pet_type.setSelection(spinnerPosition);
        }
        add_details_pet_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                strSpnerItemPetNm=item;
                Log.d("spnerType",""+strSpnerItemPetNm);
                getStrSpnerItemPetNmId=petTypeHashMap.get(strSpnerItemPetNm);
                if(!getStrSpnerItemPetNmId.equals("0"))
                {
                    getPetBreed();
                    getPetAge();
                    getPetColor();
                    getPetSize();
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setPetBreeSpinner() {
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,petBreed);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        add_details_pet_breed.setAdapter(aa);
        if (!strSpnrBreed.equals("")) {
            int spinnerPosition = aa.getPosition(strSpnrBreed);
            add_details_pet_breed.setSelection(spinnerPosition);
        }
        add_details_pet_breed.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                strSpnrBreed=item;
                Log.d("spnerType",""+strSpnrBreed);
                strSpnrBreedId=petBreedHashMap.get(strSpnrBreed);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setPetAgeSpinner() {
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,petAge);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        add_details_pet_age.setAdapter(aa);
        if (!strSpnrAge.equals("")) {
            int spinnerPosition = aa.getPosition(strSpnrAge);
            add_details_pet_age.setSelection(spinnerPosition);
        }
        add_details_pet_age.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                strSpnrAge=item;
                Log.d("spnerType",""+strSpnrAge);
                strSpnrAgeId=petAgeHashMap.get(strSpnrAge);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    private void setPetColorSpinner() {
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,petColor);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        add_detils_pet_color.setAdapter(aa);
        if (!strSpnrColor.equals("")) {
            int spinnerPosition = aa.getPosition(strSpnrColor);
            add_detils_pet_color.setSelection(spinnerPosition);
        }
        add_detils_pet_color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                strSpnrColor=item;
                Log.d("spnerType",""+strSpnrColor);
                strSpnrColorId=petColorHashMap.get(strSpnrColor);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setPetSizeSpinner() {
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,petSize);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        add_details_pet_size.setAdapter(aa);
        if (!strSpnrSize.equals("")) {
            int spinnerPosition = aa.getPosition(strSpnrSize);
            add_details_pet_size.setSelection(spinnerPosition);
        }
        add_details_pet_size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                //strSpnrSize=item;
                strSpnrSize=item;
                Log.d("spnerType",""+strSpnrSize);
                strSpneSizeId=petSizeHashMap.get(strSpnrSize);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setSpinnerPetSex() {
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,petSex);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        add_details_pet_sex.setAdapter(aa);
        if (!strSpnrSex.equals("")) {
            int spinnerPosition = aa.getPosition(strSpnrSex);
            add_details_pet_sex.setSelection(spinnerPosition);
        }
        add_details_pet_sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                //strSpnrSex=item;
                strSpnrSex=item;
                Log.d("spnerType",""+strSpnrSex);
                strSpnrSexId=petSexHashMap.get(strSpnrSex);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void showPictureDialog() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_layout);

        RelativeLayout select_camera = (RelativeLayout) dialog.findViewById(R.id.select_camera);
        RelativeLayout select_gallery = (RelativeLayout) dialog.findViewById(R.id.select_gallery);
        RelativeLayout cancel_dialog = (RelativeLayout) dialog.findViewById(R.id.cancel_dialog);

        select_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaUtils.openCamera();
                dialog.dismiss();
            }
        });

        select_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaUtils.openGallery();
                dialog.dismiss();
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
        mediaUtils.onActivityResult(requestCode, resultCode, data);
    }

//    @RequiresApi(api = Build.VERSION_CODES.FROYO)
//    public String saveImage(Bitmap myBitmap) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
//        File wallpaperDirectory = new File(
//                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
//        // have the object build the directory structure, if needed.
//        if (!wallpaperDirectory.exists()) {
//            wallpaperDirectory.mkdirs();
//        }
//
//        try {
//            if(selctProflImage.equals("1")){
//                file = new File(wallpaperDirectory, Calendar.getInstance()
//                        .getTimeInMillis() + ".png");
//                file.createNewFile();
//                FileOutputStream fo = new FileOutputStream(file);
//                fo.write(bytes.toByteArray());
//                MediaScannerConnection.scanFile(this,
//                        new String[]{file.getPath()},
//                        new String[]{"image/png"}, null);
//                fo.close();
//                Log.d("TAG", "File Saved::---&gt;" + file.getAbsolutePath());
//                UploadImages(file);
//                return file.getAbsolutePath();
//            }
//            if(selctImgOne.equals("1")){
//                fileImg1 = new File(wallpaperDirectory, Calendar.getInstance()
//                        .getTimeInMillis() + ".jpg");
//                fileImg1.createNewFile();
//                FileOutputStream fo = new FileOutputStream(fileImg1);
//                fo.write(bytes.toByteArray());
//                MediaScannerConnection.scanFile(this,
//                        new String[]{fileImg1.getPath()},
//                        new String[]{"image/jpeg"}, null);
//                fo.close();
//                Log.d("TAG", "File Saved::---&gt;" + fileImg1.getAbsolutePath());
//                UploadImages(fileImg1);
//                return fileImg1.getAbsolutePath();
//
//            }
//            if(selctImgtwo.equals("1")){
//                fileImg2 = new File(wallpaperDirectory, Calendar.getInstance()
//                        .getTimeInMillis() + ".jpg");
//                fileImg2.createNewFile();
//                FileOutputStream fo = new FileOutputStream(fileImg2);
//                fo.write(bytes.toByteArray());
//                MediaScannerConnection.scanFile(this,
//                        new String[]{fileImg2.getPath()},
//                        new String[]{"image/jpeg"}, null);
//                fo.close();
//                Log.d("TAG", "File Saved::---&gt;" + fileImg2.getAbsolutePath());
//                UploadImages(fileImg2);
//                return fileImg2.getAbsolutePath();
//
//            }
//            if(slctImgThree.equals("1")){
//                fileImg3 = new File(wallpaperDirectory, Calendar.getInstance()
//                        .getTimeInMillis() + ".jpg");
//                fileImg3.createNewFile();
//                FileOutputStream fo = new FileOutputStream(fileImg3);
//                fo.write(bytes.toByteArray());
//                MediaScannerConnection.scanFile(this,
//                        new String[]{fileImg3.getPath()},
//                        new String[]{"image/jpeg"}, null);
//                fo.close();
//                Log.d("TAG", "File Saved::---&gt;" + fileImg3.getAbsolutePath());
//                UploadImages(fileImg3);
//                return fileImg2.getAbsolutePath();
//            }
//            if(slctImgFour.equals("1")){
//                fileImg4 = new File(wallpaperDirectory, Calendar.getInstance()
//                        .getTimeInMillis() + ".jpg");
//                fileImg4.createNewFile();
//                FileOutputStream fo = new FileOutputStream(fileImg4);
//                fo.write(bytes.toByteArray());
//                MediaScannerConnection.scanFile(this,
//                        new String[]{fileImg4.getPath()},
//                        new String[]{"image/jpeg"}, null);
//                fo.close();
//                Log.d("TAG", "File Saved::---&gt;" + fileImg4.getAbsolutePath());
//                UploadImages(fileImg4);
//                return fileImg4.getAbsolutePath();
//            }
//            if(slctImgFive.equals("1")){
//                fileImg5 = new File(wallpaperDirectory, Calendar.getInstance()
//                        .getTimeInMillis() + ".jpg");
//                fileImg5.createNewFile();
//                FileOutputStream fo = new FileOutputStream(fileImg5);
//                fo.write(bytes.toByteArray());
//                MediaScannerConnection.scanFile(this,
//                        new String[]{fileImg5.getPath()},
//                        new String[]{"image/jpeg"}, null);
//                fo.close();
//                Log.d("TAG", "File Saved::---&gt;" + fileImg5.getAbsolutePath());
//                UploadImages(fileImg5);
//                return fileImg5.getAbsolutePath();
//
//            }
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
//        return "";
//    }

    private void UploadImages(File absolutePath) {
        methods.showCustomProgressBarDialog(this);
        MultipartBody.Part userDpFilePart = null;
        if (absolutePath != null) {
            RequestBody userDpFile = RequestBody.create(MediaType.parse("image/*"), absolutePath);
            userDpFilePart = MultipartBody.Part.createFormData("file", absolutePath.getName(), userDpFile);
        }

        ApiService<ImageResponse> service = new ApiService<>();
        service.get( this, ApiClient.getApiInterface().uploadImages(Config.token,userDpFilePart), "UploadDocument");
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
                            Log.d("Debuging","All Permission Granted");
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
                        Log.d("Debuging","Some Error");
                    }
                })
                .onSameThread()
                .check();
    }

    private void updatePetDetails(UpdatePetRequest addPetRequset) {
        methods.showCustomProgressBarDialog(this);
        ApiService<AddPetValueResponse> service = new ApiService<>();
        service.get( this, ApiClient.getApiInterface().updatePetDetails(Config.token,addPetRequset), "UpdatePetDetails");
        Log.e("DATALOG","check1=> "+methods.getRequestJson(addPetRequset));

    }

    @Override
    public void imgdata(String imgPath) {
        Log.d ("imgdata123" , imgPath.toString());
        Uri selectedImageURI = null;
        File imgFile = new File(imgPath);
        Log.d ("imgdata: " , imgFile.toString());
        UploadImages(imgFile);
    }
}