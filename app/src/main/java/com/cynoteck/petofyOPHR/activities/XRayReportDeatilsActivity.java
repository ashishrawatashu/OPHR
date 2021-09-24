package com.cynoteck.petofyOPHR.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.petReportsRequest.PetClinicVisitDetailsRequest;
import com.cynoteck.petofyOPHR.params.petReportsRequest.PetClinicVistsDetailsParams;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.AddUpdateDeleteClinicVisitResponse;
import com.cynoteck.petofyOPHR.response.getXRayReports.getXRayReportDetailsResponse.GetXRayReportDeatilsResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.google.android.material.card.MaterialCardView;

import java.io.File;
import java.io.IOException;

import retrofit2.Response;

public class XRayReportDeatilsActivity extends AppCompatActivity implements ApiResponse, View.OnClickListener {

    TextView nature_of_visit_textView, test_date_textView, Result_textView, recommended_follow_up_textView, recommended_follow_up_date_textView;
    Button deleteReport_BT,view_file_BT;
    ImageView petRegImage_IV;
    MaterialCardView back_arrow_CV;
    TextView recommended_follow_up_date_TV,dot_recommended_follow_up_date_TV,dot_recommended_follow_up_TV,recommended_follow_up_TV, pet_reg_name_TV, pet_reg__id_TV, parent_name_TV, pet_reg_date_of_birth_TV;
    String pet_DOB, pet_image_url,nature = "", date_of_test = "", follow_up = "", result = "", pet_unique_id, pet_name, pet_sex, pet_owner_name, pet_owner_contact, pet_id, report_type_id, type, follow_up_date;
    Uri localUri;
    RelativeLayout card_view;
    ProgressBar progressBar;
    Methods methods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x_ray_report_deatils);
        recommended_follow_up_TV=findViewById(R.id.recommended_follow_up_TV);
        dot_recommended_follow_up_TV=findViewById(R.id.dot_recommended_follow_up_TV);
        dot_recommended_follow_up_date_TV=findViewById(R.id.dot_recommended_follow_up_date_TV);
        recommended_follow_up_date_TV=findViewById(R.id.recommended_follow_up_date_TV);
        methods = new Methods(this);
        getIntentData();
        init();
        setdataInFields();
        getXRayReportDeatilsDeatils();
        deleteReport_BT.setVisibility(View.GONE);
    }

    private void setdataInFields() {
        pet_reg_name_TV.setText(pet_name+" ("+pet_sex+")");
        pet_reg__id_TV.setText(pet_unique_id);
        parent_name_TV.setText(pet_owner_name);
        pet_reg_date_of_birth_TV.setText(pet_DOB);

        Glide.with(this)
                .load(pet_image_url)
                .placeholder(R.drawable.dummy_dog_image)
                .into(petRegImage_IV);
    }

    private void init() {
        card_view=findViewById(R.id.card_view);
        view_file_BT=findViewById(R.id.view_file_BT);
        nature_of_visit_textView = findViewById(R.id.nature_of_visit_textView);
        test_date_textView = findViewById(R.id.test_date_textView);
        Result_textView = findViewById(R.id.Result_textView);
        recommended_follow_up_textView = findViewById(R.id.recommended_follow_up_textView);
        recommended_follow_up_date_textView = findViewById(R.id.recommended_follow_up_date_textView);
        progressBar=findViewById(R.id.progressBar);
        pet_reg_name_TV = findViewById(R.id.pet_reg_name_TV);
        pet_reg__id_TV = findViewById(R.id.pet_reg__id_TV);
        parent_name_TV = findViewById(R.id.parent_name_TV);
        pet_reg_date_of_birth_TV = findViewById(R.id.pet_reg_date_of_birth_TV);
        deleteReport_BT = findViewById(R.id.deleteReport_BT);
        back_arrow_CV = findViewById(R.id.back_arrow_CV);
        petRegImage_IV=findViewById(R.id.petRegImage_IV);

        view_file_BT.setOnClickListener(this);
        back_arrow_CV.setOnClickListener(this);
        deleteReport_BT.setOnClickListener(this);
    }

    private void getIntentData() {
        Intent extras = getIntent();
        pet_id = extras.getExtras().getString("pet_id");
        pet_owner_contact = extras.getExtras().getString("pet_owner_contact");
        pet_owner_name = extras.getExtras().getString("pet_owner_name");
        pet_sex = extras.getExtras().getString("pet_sex");
        pet_name = extras.getExtras().getString("pet_name");
        pet_unique_id = extras.getExtras().getString("pet_unique_id");
        report_type_id = extras.getExtras().getString("id");
        follow_up_date = extras.getExtras().getString("follow_up_date");
        follow_up = extras.getExtras().getString("follow_up");
        nature = extras.getExtras().getString("nature");
        date_of_test = extras.getExtras().getString("date_of_test");
        result = extras.getExtras().getString("result");
        pet_image_url=extras.getExtras().getString("pet_image_url");
        pet_DOB = extras.getExtras().getString("pet_DOB");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.view_file_BT:

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                i.setDataAndType(localUri, getContentResolver().getType(localUri));
                startActivity(i);
                break;
            case R.id.back_arrow_CV:
                onBackPressed();
                break;

            case R.id.deleteReport_BT:
                Log.d("Add Anotheer Veterian", "vet");
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Are you sure?");
                alertDialog.setMessage("Do You Want to Delete This Report ?");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if (methods.isInternetOn()) {
                                    deleteXRay();
                                } else {
                                    methods.DialogInternet();
                                }
                                dialog.dismiss();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                alertDialog.show();

                break;
        }

    }

    private void getXRayReportDeatilsDeatils() {

        PetClinicVistsDetailsParams petClinicVistsDetailsParams = new PetClinicVistsDetailsParams();
        petClinicVistsDetailsParams.setId(report_type_id.substring(0, report_type_id.length() - 2));
        PetClinicVisitDetailsRequest petClinicVisitDetailsRequest = new PetClinicVisitDetailsRequest();
        petClinicVisitDetailsRequest.setData(petClinicVistsDetailsParams);
        Log.d("GetXRaykDetails", petClinicVisitDetailsRequest.toString());

        ApiService<GetXRayReportDeatilsResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getTestXRayDetails(Config.token, petClinicVisitDetailsRequest), "GetXRaykDetails");


    }


    private void deleteXRay() {
        PetClinicVistsDetailsParams petClinicVistsDetailsParams = new PetClinicVistsDetailsParams();
        petClinicVistsDetailsParams.setId(report_type_id.substring(0, report_type_id.length() - 2));
        PetClinicVisitDetailsRequest petClinicVisitDetailsRequest = new PetClinicVisitDetailsRequest();
        petClinicVisitDetailsRequest.setData(petClinicVistsDetailsParams);
        Log.d("DeleteTestXRay", petClinicVisitDetailsRequest.toString());
        ApiService<AddUpdateDeleteClinicVisitResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().deleteTestXRay(Config.token, petClinicVisitDetailsRequest), "DeleteTestXRay");

    }

    @Override
    public void onResponse(Response response, String key) {
        switch (key) {
            case "GetXRaykDetails":
                try {
                    progressBar.setVisibility(View.GONE);
                    Log.d("GetXRaykDetails", response.body().toString());
                    GetXRayReportDeatilsResponse getXRayReportDeatilsResponse = (GetXRayReportDeatilsResponse) response.body();
                    Log.e("GetXRaykDetails", methods.getRequestJson(getXRayReportDeatilsResponse));
                    int responseCode = Integer.parseInt(getXRayReportDeatilsResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        card_view.setVisibility(View.VISIBLE);
                        nature_of_visit_textView.setText(getXRayReportDeatilsResponse.getData().getTypeOfTest().getTestType());
                        test_date_textView.setText(getXRayReportDeatilsResponse.getData().getDateTested());
                        Result_textView.setText(getXRayReportDeatilsResponse.getData().getResults());
                        if (getXRayReportDeatilsResponse.getData().getDocuments().equals("")){
                            view_file_BT.setVisibility(View.GONE);
                        }else {
                            localUri = Uri.parse(getXRayReportDeatilsResponse.getData().getDocuments());
                            view_file_BT.setVisibility(View.VISIBLE);
                        }
//                        if (getXRayReportDeatilsResponse.getData().getFollowUpDate().equals("")) {
//                            recommended_follow_up_TV.setVisibility(View.GONE);
//                            recommended_follow_up_date_textView.setVisibility(View.GONE);
//                            dot_recommended_follow_up_TV.setVisibility(View.GONE);
//
//                        } else {
//                            recommended_follow_up_date_textView.setText(getXRayReportDeatilsResponse.getData().getFollowUpDate());
//                        }
//                        if (getXRayReportDeatilsResponse.getData().getFollowUp() == null) {
//                            dot_recommended_follow_up_date_TV.setVisibility(View.GONE);
//                            recommended_follow_up_textView.setVisibility(View.GONE);
//                            recommended_follow_up_date_TV.setVisibility(View.GONE);
//                        } else {
//                            recommended_follow_up_textView.setText(getXRayReportDeatilsResponse.getData().getFollowUpDate());
//                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "DeleteTestXRay":
                try {
                    Log.d("DeleteClinicVisit", response.body().toString());
                    AddUpdateDeleteClinicVisitResponse addUpdateDeleteClinicVisitResponse = (AddUpdateDeleteClinicVisitResponse) response.body();
                    int responseCode = Integer.parseInt(addUpdateDeleteClinicVisitResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        Config.type = "XRay";
                        onBackPressed();
                        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onError(Throwable t, String key) {
        Log.e("error", t.getLocalizedMessage());

    }

    public static void openFile(Context context, File url) throws IOException {
        // Create URI
        File file=url;
        Uri uri = Uri.fromFile(file);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        // Check what kind of file you are trying to open, by comparing the url with extensions.
        // When the if condition is matched, plugin sets the correct intent (mime) type,
        // so Android knew what application to use to open the file
        if (url.toString().contains(".doc") || url.toString().contains(".docx")) {
            // Word document
            intent.setDataAndType(uri, "application/msword");
        } else if(url.toString().contains(".pdf")) {
            // PDF file
            intent.setDataAndType(uri, "application/pdf");
        } else if(url.toString().contains(".ppt") || url.toString().contains(".pptx")) {
            // Powerpoint file
            intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        } else if(url.toString().contains(".xls") || url.toString().contains(".xlsx")) {
            // Excel file
            intent.setDataAndType(uri, "application/vnd.ms-excel");
        } else if(url.toString().contains(".zip") || url.toString().contains(".rar")) {
            // WAV audio file
            intent.setDataAndType(uri, "application/x-wav");
        } else if(url.toString().contains(".rtf")) {
            // RTF file
            intent.setDataAndType(uri, "application/rtf");
        } else if(url.toString().contains(".wav") || url.toString().contains(".mp3")) {
            // WAV audio file
            intent.setDataAndType(uri, "audio/x-wav");
        } else if(url.toString().contains(".gif")) {
            // GIF file
            intent.setDataAndType(uri, "image/gif");
        } else if(url.toString().contains(".jpg") || url.toString().contains(".jpeg") || url.toString().contains(".png")) {
            // JPG file
            intent.setDataAndType(uri, "image/jpeg");
        } else if(url.toString().contains(".txt")) {
            // Text file
            intent.setDataAndType(uri, "text/plain");
        } else if(url.toString().contains(".3gp") || url.toString().contains(".mpg") || url.toString().contains(".mpeg") || url.toString().contains(".mpe") || url.toString().contains(".mp4") || url.toString().contains(".avi")) {
            // Video files
            intent.setDataAndType(uri, "video/*");
        } else {
            //if you want you can also define the intent type for any other file

            //additionally use else clause below, to manage other unknown extensions
            //in this case, Android will show all applications installed on the device
            //so you can choose which application to use
            intent.setDataAndType(uri, "*/*");
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
