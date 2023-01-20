package com.cynoteck.petofyOPHR.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
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
import com.cynoteck.petofyOPHR.response.getPetIdCardResponse.PetIdCardResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.cynoteck.petofyOPHR.utils.ScreenshotUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

import retrofit2.Response;

public class PetIdCardActivity extends AppCompatActivity implements ApiResponse {

    TextView printID_TV,pet_name_TV,pet_sex_TV,dob_TV,breed_TV,parent_TV,contact_TV,pet_id_TV;
    ImageView imageView,bar_code_IV,pet_image;
    CardView card_view;
    String id;
    Methods methods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.identity_card);
        methods=new Methods(this);
        Bundle extras = getIntent().getExtras();
        id = extras.getString("id");
        getIdCardResposne(id);
        printID_TV= findViewById(R.id.printID_TV);
        imageView = findViewById(R.id.image_view);
        card_view=findViewById(R.id.card_view);
        pet_name_TV=findViewById(R.id.pet_name_TV);
        pet_sex_TV=findViewById(R.id.pet_sex_TV);
        dob_TV=findViewById(R.id.dob_TV);
        breed_TV=findViewById(R.id.breed_TV);
        parent_TV=findViewById(R.id.parent_TV);
        contact_TV=findViewById(R.id.contact_TV);
        pet_id_TV=findViewById(R.id.pet_id_TV);
        bar_code_IV=findViewById(R.id.bar_code_IV);
        pet_image=findViewById(R.id.pet_image);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        printID_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printID_TV.setVisibility(View.INVISIBLE);
                takeScreenshot();

            }
        });

    }

    private void getIdCardResposne(String id) {
        PetClinicVisitDetailsRequest petClinicVisitDetailsRequest = new PetClinicVisitDetailsRequest();
        PetClinicVistsDetailsParams petClinicVistsDetailsParams = new PetClinicVistsDetailsParams();
        petClinicVistsDetailsParams.setId(id.substring(0,id.length()-2));
        petClinicVisitDetailsRequest.setData(petClinicVistsDetailsParams);

        ApiService<PetIdCardResponse> service = new ApiService<>();
        service.get( this, ApiClient.getApiInterface().getPetIdCard(Config.token,petClinicVisitDetailsRequest), "GetIdCard");
        Log.e("IDCARD_REQUEST","===>"+petClinicVisitDetailsRequest);
        methods.showCustomProgressBarDialog(this);

    }

    private void ScreenShot() {
        View view1 =  getWindow().getDecorView().getRootView();
        view1.setDrawingCacheEnabled(true);


        Bitmap bitmap = Bitmap.createBitmap(view1.getDrawingCache());
        view1.setDrawingCacheEnabled(false);

        String filepath = Environment.getExternalStorageDirectory()+"/Download/"+ Calendar.getInstance().getTime().toString()+".jpg";
        File dileScreenshot = new File(filepath);
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(dileScreenshot);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        Uri uri = Uri.fromFile(dileScreenshot);//Convert file path into Uri for sharing
        intent.setDataAndType(uri,"image/jpeg");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);

    }

    private void takeScreenshot() {
        Bitmap b = null;


        printID_TV.setVisibility(View.VISIBLE);
        b = ScreenshotUtils.getScreenShot(card_view);
        printID_TV.setVisibility(View.INVISIBLE);

        if (b != null) {
            card_view.setVisibility(View.GONE);
            showScreenShotImage(b);
            View view1 =  getWindow().getDecorView().getRootView();
            view1.setDrawingCacheEnabled(true);


            Bitmap  bitmap = Bitmap.createBitmap(view1.getDrawingCache());
            view1.setDrawingCacheEnabled(false);

            String filepath = Environment.getExternalStorageDirectory()+"/Download/"+ Calendar.getInstance().getTime().toString()+".jpg";
            File dileScreenshot = new File(filepath);
            FileOutputStream fileOutputStream = null;
            MediaScannerConnection.scanFile(PetIdCardActivity.this,
                    new String[] { dileScreenshot.getPath() }, null,
                    new MediaScannerConnection.OnScanCompletedListener() {

                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("TAG", "Finished scanning " + path);
                        }
                    });
            try {
                fileOutputStream = new FileOutputStream(dileScreenshot);
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            shareScreenshot(dileScreenshot);

        } else
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();

    }

    private void showScreenShotImage(Bitmap b) {
        imageView.setImageBitmap(b);
    }

    private void shareScreenshot(File file) {
        Toast.makeText(this, "Pet ID Card Save Successfully ", Toast.LENGTH_SHORT).show();
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        intent.putExtra(Intent.EXTRA_STREAM, uri);//pass uri here
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    @Override
    public void onResponse(Response arg0, String key) {
        Log.e("IDCARD_REQUEST","===>"+arg0.body());
        methods.customProgressDismiss();
        switch (key){
            case "GetIdCard":

                PetIdCardResponse petIdCardResponse = (PetIdCardResponse) arg0.body();
                int responseCode = Integer.parseInt(petIdCardResponse.getResponse().getResponseCode());
                if (responseCode==109){

                    pet_name_TV.setText(petIdCardResponse.getData().getPetName());
                    pet_sex_TV.setText(" , "+petIdCardResponse.getData().getPetSex());
                    dob_TV.setText(petIdCardResponse.getData().getDateOfBirth());
                    breed_TV.setText(petIdCardResponse.getData().getPetBreed());
                    parent_TV.setText(petIdCardResponse.getData().getPetParentName());
                    contact_TV.setText(petIdCardResponse.getData().getContactNumber());
                    pet_id_TV.setText(petIdCardResponse.getData().getPetUniqueId());
                    Glide.with(this)
                            .load(petIdCardResponse.getData().getPetProfileImageUrl())
                            .placeholder(R.drawable.pet_image)
                            .into(pet_image);
                    Glide.with(this).load(petIdCardResponse.getData().getBarcodeUrl()).into(bar_code_IV);
                }
        }

    }

    @Override
    public void onError(Throwable t, String key) {
        Log.e("IDCARD_Error","===>"+t.getLocalizedMessage());

    }
}
