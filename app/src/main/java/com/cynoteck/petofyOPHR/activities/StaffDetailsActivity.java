package com.cynoteck.petofyOPHR.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cynoteck.petofyOPHR.R;
import com.google.android.material.card.MaterialCardView;

public class StaffDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    TextView staff_name_TV,staff_email_TV,staff_phone_TV,staff_degree_TV,staff_reg_no_TV;
    RelativeLayout edit_permission_RL,edit_profile_RL;
    MaterialCardView back_arrow_CV,image_edit_CV,staff_degree_RL,staff_reg_no_RL;
    String staffId="",staff_name="",staff_email="",staff_phone="",staff_degree="",staff_reg_no="",staff_image_url="",staffUserId="";
    ImageView staff_image_TV;
    private int ADD_STAFF_DEATILS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_details);

        Intent intent = getIntent();
        staffId=intent.getStringExtra("staffId");
        staffUserId= intent.getStringExtra("staffUserId");
        staff_name=intent.getStringExtra("staff_name");
        staff_email=intent.getStringExtra("staff_email");
        staff_phone=intent.getStringExtra("staff_phone");
        staff_degree=intent.getStringExtra("staff_degree");
        staff_reg_no=intent.getStringExtra("staff_reg_no");
        staff_image_url=intent.getStringExtra("staff_image_url");

        staff_image_TV=findViewById(R.id.staff_image_TV);
        staff_name_TV=findViewById(R.id.staff_name_TV);
        staff_email_TV=findViewById(R.id.staff_email_TV);
        staff_phone_TV=findViewById(R.id.staff_phone_TV);
        staff_degree_TV=findViewById(R.id.staff_degree_TV);
        staff_reg_no_TV=findViewById(R.id.staff_reg_no_TV);
        edit_permission_RL=findViewById(R.id.edit_permission_RL);
        back_arrow_CV=findViewById(R.id.back_arrow_CV);
        image_edit_CV=findViewById(R.id.image_edit_CV);
        edit_profile_RL=findViewById(R.id.edit_profile_RL);
        staff_reg_no_RL=findViewById(R.id.staff_reg_no_RL);
        staff_degree_RL=findViewById(R.id.staff_degree_RL);

        back_arrow_CV.setOnClickListener(this);
        edit_permission_RL.setOnClickListener(this);
        image_edit_CV.setOnClickListener(this);
        edit_profile_RL.setOnClickListener(this);

        setStaffInfo();



    }

    private void setStaffInfo() {
        staff_name_TV.setText(staff_name);
        staff_email_TV.setText(staff_email);
        staff_phone_TV.setText(staff_phone);
        if (staff_degree.equals("")){
            staff_degree_RL.setVisibility(View.INVISIBLE);
        }
        if (staff_reg_no.equals("")){
            staff_reg_no_RL.setVisibility(View.INVISIBLE);
        }
        staff_degree_TV.setText(staff_degree);
        staff_reg_no_TV.setText(staff_reg_no);

        Glide.with(this)
                .load(staff_image_url)
                .placeholder(R.drawable.staff_groups)
                .into(staff_image_TV);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.edit_profile_RL:
                Intent updateStaffIntent = new Intent(this, AddUpdateStaffActivity.class);
                updateStaffIntent.putExtra("activityType", "Update");
                updateStaffIntent.putExtra("staffId", staffId);
                startActivityForResult(updateStaffIntent, ADD_STAFF_DEATILS);

                break;

            case R.id.image_edit_CV:

                break;

            case R.id.edit_permission_RL:
                Intent intent=new Intent(this, StaffPermissionActivity.class);
                intent.putExtra("staffUserId",staffUserId);
                startActivity(intent);

                break;


            case R.id.back_arrow_CV:
                onBackPressed();
                break;

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_STAFF_DEATILS) {
            if (resultCode == RESULT_OK) {
                staffId=data.getStringExtra("staffId");
                staffUserId= data.getStringExtra("staffUserId");
                staff_name=data.getStringExtra("staff_name");
                staff_email=data.getStringExtra("staff_email");
                staff_phone=data.getStringExtra("staff_phone");
                staff_degree=data.getStringExtra("staff_degree");
                staff_reg_no=data.getStringExtra("staff_reg_no");
                staff_image_url=data.getStringExtra("staff_image_url");
                setStaffInfo();
            }
        }
        return;
    }
}