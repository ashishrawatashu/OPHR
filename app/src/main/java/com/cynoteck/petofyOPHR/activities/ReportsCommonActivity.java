package com.cynoteck.petofyOPHR.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.fragments.ReportListFragment;
import com.google.android.material.card.MaterialCardView;

public class ReportsCommonActivity extends AppCompatActivity {
    ImageView petRegImage_IV;
    MaterialCardView back_arrow_CV;
    String pet_image_url, pet_unique_id, pet_name, pet_sex, pet_owner_name, pet_owner_contact, pet_id, report_type_id, button_type, pet_DOB, pet_encrypted_id;
    Bundle data = new Bundle();
    TextView pet_reg_name_TV, pet_reg__id_TV, parent_name_TV, pet_reg_date_of_birth_TV, reports_headline_TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports_common);

        Bundle extras = getIntent().getExtras();
        report_type_id = extras.getString("reports_id");
        pet_id = extras.getString("pet_id");
        pet_image_url = extras.getString("pet_image_url");
        pet_owner_contact = extras.getString("pet_owner_contact");
        pet_owner_name = extras.getString("pet_owner_name");
        pet_sex = extras.getString("pet_sex");
        pet_name = extras.getString("pet_name");
        pet_unique_id = extras.getString("pet_unique_id");
        button_type = extras.getString("button_type");
        pet_DOB = extras.getString("pet_DOB");
        pet_encrypted_id = extras.getString("pet_encrypted_id");


        reports_headline_TV = findViewById(R.id.reports_headline_TV);
        back_arrow_CV = findViewById(R.id.back_arrow_CV);
        pet_reg_name_TV = findViewById(R.id.pet_reg_name_TV);
        pet_reg__id_TV = findViewById(R.id.pet_reg__id_TV);
        parent_name_TV = findViewById(R.id.parent_name_TV);
        petRegImage_IV = findViewById(R.id.petRegImage_IV);
        pet_reg_date_of_birth_TV = findViewById(R.id.pet_reg_date_of_birth_TV);

        pet_reg_name_TV.setText(pet_name.substring(0, 1).toUpperCase() + pet_name.substring(1) + " (" + pet_sex + ")");
        parent_name_TV.setText(pet_owner_name.substring(0, 1).toUpperCase() + pet_owner_name.substring(1));
        pet_reg__id_TV.setText(pet_unique_id);
        pet_reg_date_of_birth_TV.setText(pet_DOB);

        Glide.with(this)
                .load(pet_image_url)
                .placeholder(R.drawable.dummy_dog_image)
                .into(petRegImage_IV);

        data.putString("pet_id", pet_id);
        data.putString("pet_name", pet_name);
        data.putString("pet_unique_id", pet_unique_id);
        data.putString("pet_sex", pet_sex);
        data.putString("pet_owner_name", pet_owner_name);
        data.putString("pet_owner_contact", pet_owner_contact);
        switch (report_type_id) {

            case "1.0":
                if (button_type.equals("update")) {
                    data.putString("button_type", button_type);
                } else if (button_type.equals("view")) {
                    data.putString("button_type", "view");
                }
                reports_headline_TV.setText("ROUTINE REPORT");
                data.putString("reports_id", "1");
                data.putString("type", "list");
                data.putString("pet_DOB", pet_DOB);
                data.putString("pet_encrypted_id", pet_encrypted_id);
                data.putString("pet_image_url", pet_image_url);


                ReportListFragment fragment1 = new ReportListFragment();
                fragment1.setArguments(data);
                FragmentTransaction selectPetReportsFragmentFT = getSupportFragmentManager().beginTransaction();
                selectPetReportsFragmentFT.replace(R.id.report_type_frame, fragment1);
                selectPetReportsFragmentFT.commit();

                break;

            case "2.0":
                if (button_type.equals("update")) {
                    data.putString("button_type", button_type);
                } else if (button_type.equals("view")) {
                    data.putString("button_type", "view");
                }
                reports_headline_TV.setText("HEALTH PROBLEM");
                data.putString("reports_id", "2");
                data.putString("type", "list");
                data.putString("pet_DOB", pet_DOB);
                data.putString("pet_encrypted_id", pet_encrypted_id);
                data.putString("pet_image_url", pet_image_url);

                ReportListFragment fragment2 = new ReportListFragment();
                fragment2.setArguments(data);
                FragmentTransaction fragment2FT = getSupportFragmentManager().beginTransaction();
                fragment2FT.replace(R.id.report_type_frame, fragment2);
                fragment2FT.commit();

                break;


            case "4.0":
                if (button_type.equals("update")) {
                    data.putString("button_type", button_type);
                } else if (button_type.equals("view")) {
                    data.putString("button_type", "view");
                }
                reports_headline_TV.setText("IMMUNIZATION");
                data.putString("reports_id", "4");
                data.putString("type", "list");
                data.putString("pet_DOB", pet_DOB);
                data.putString("pet_encrypted_id", pet_encrypted_id);
                data.putString("pet_image_url", pet_image_url);

                ReportListFragment fragment3 = new ReportListFragment();
                fragment3.setArguments(data);
                FragmentTransaction fragment3FT = getSupportFragmentManager().beginTransaction();
                fragment3FT.replace(R.id.report_type_frame, fragment3);
                fragment3FT.commit();

                break;


            case "5.0":
                if (button_type.equals("update")) {
                    data.putString("button_type", button_type);
                } else if (button_type.equals("view")) {
                    data.putString("button_type", "view");
                }
                reports_headline_TV.setText("DEWORMING");
                data.putString("reports_id", "5");
                data.putString("type", "list");
                data.putString("pet_DOB", pet_DOB);
                data.putString("pet_encrypted_id", pet_encrypted_id);
                data.putString("pet_image_url", pet_image_url);

                ReportListFragment fragment4 = new ReportListFragment();
                fragment4.setArguments(data);
                FragmentTransaction fragment4FT = getSupportFragmentManager().beginTransaction();
                fragment4FT.replace(R.id.report_type_frame, fragment4);
                fragment4FT.commit();


                break;


            case "6.0":
                if (button_type.equals("update")) {
                    data.putString("button_type", button_type);
                } else if (button_type.equals("view")) {
                    data.putString("button_type", "view");
                }
                reports_headline_TV.setText("OTHER REPORT");
                data.putString("reports_id", "6");
                data.putString("type", "list");
                data.putString("pet_DOB", pet_DOB);
                data.putString("pet_encrypted_id", pet_encrypted_id);
                data.putString("pet_image_url", pet_image_url);

                ReportListFragment fragment5 = new ReportListFragment();
                fragment5.setArguments(data);
                FragmentTransaction fragment5FT = getSupportFragmentManager().beginTransaction();
                fragment5FT.replace(R.id.report_type_frame, fragment5);
                fragment5FT.commit();

                break;


            case "7.0":
                if (button_type.equals("update")) {
                    data.putString("button_type", button_type);
                } else if (button_type.equals("view")) {
                    data.putString("button_type", "view");
                }
                reports_headline_TV.setText("Test/X-Ray Report");
                data.putString("reports_id", "7");
                data.putString("type", "XRay");
                data.putString("pet_DOB", pet_DOB);
                data.putString("pet_encrypted_id", pet_encrypted_id);
                data.putString("pet_image_url", pet_image_url);

                ReportListFragment fragment6 = new ReportListFragment();
                fragment6.setArguments(data);
                FragmentTransaction fragment6FT = getSupportFragmentManager().beginTransaction();
                fragment6FT.replace(R.id.report_type_frame, fragment6);
                fragment6FT.commit();

                break;

            case "8.0":
                if (button_type.equals("update")) {
                    data.putString("button_type", button_type);
                } else if (button_type.equals("view")) {
                    data.putString("button_type", "view");
                }
                reports_headline_TV.setText("Lab Tests");
                data.putString("reports_id", "8");
                data.putString("type", "LabTest");
                data.putString("pet_DOB", pet_DOB);
                data.putString("pet_encrypted_id", pet_encrypted_id);
                data.putString("pet_image_url", pet_image_url);

                ReportListFragment fragment7 = new ReportListFragment();
                fragment7.setArguments(data);
                FragmentTransaction fragment7FT = getSupportFragmentManager().beginTransaction();
                fragment7FT.replace(R.id.report_type_frame, fragment7);
                fragment7FT.commit();

                break;

            case "9.0":
                if (button_type.equals("update")) {
                    data.putString("button_type", button_type);
                } else if (button_type.equals("view")) {
                    data.putString("button_type", "view");
                }
                reports_headline_TV.setText("Hospitalization & Surgeries");
                data.putString("reports_id", "9");
                data.putString("type", "Hospitalization");
                data.putString("pet_DOB", pet_DOB);
                data.putString("pet_encrypted_id", pet_encrypted_id);
                data.putString("pet_image_url", pet_image_url);

                ReportListFragment fragment8 = new ReportListFragment();
                fragment8.setArguments(data);
                FragmentTransaction fragment8FT = getSupportFragmentManager().beginTransaction();
                fragment8FT.replace(R.id.report_type_frame, fragment8);
                fragment8FT.commit();

                break;

            case "10.0":
                reports_headline_TV.setText("Clinic Visit Report");
                data.putString("reports_id", "10");
                data.putString("type", "ClinicVisitReport");
                data.putString("pet_DOB", pet_DOB);
                data.putString("pet_encrypted_id", pet_encrypted_id);
                data.putString("pet_image_url", pet_image_url);

                ReportListFragment fragment9 = new ReportListFragment();
                fragment9.setArguments(data);
                FragmentTransaction fragment9FT = getSupportFragmentManager().beginTransaction();
                fragment9FT.replace(R.id.report_type_frame, fragment9);
                fragment9FT.commit();

                break;


        }

        back_arrow_CV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
