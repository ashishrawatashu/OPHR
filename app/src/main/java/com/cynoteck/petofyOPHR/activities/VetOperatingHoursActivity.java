package com.cynoteck.petofyOPHR.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.workingHoursParameter.WorkingHoursParameter;
import com.cynoteck.petofyOPHR.response.getWorkingHoursResponse.GetorkingHoursResponseModel;
import com.cynoteck.petofyOPHR.response.getWorkingHoursResponse.WorkingHoursResponse;
import com.cynoteck.petofyOPHR.response.saveWorkingReponse.SaveWorkingHoursResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.CustomTimePickerDialog;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Response;

public class VetOperatingHoursActivity extends AppCompatActivity implements View.OnClickListener, ApiResponse {
    Methods methods;
    MaterialCardView back_arrow_CV;
    //mon_sat

    TextView start_time_mon_to_sat_TV, end_time_mon_to_sat_TV;
    SwitchCompat mon_sat_twenty_four_hr_SC, sunday_closed_hr_SC;

    RelativeLayout mon_RL, tue_RL, wed_RL, thu_RL, fri_RL, sat_RL, sun_RL;
    ConstraintLayout mon_CL, tue_CL, wed_CL, thu_CL, fri_CL, sat_CL, sun_CL;

    TextView start_time_mon_TV, end_time_mon_TV;
    SwitchCompat mon_twenty_four_hr_SC, mon_closed_SC;


    TextView start_time_tue_TV, end_time_tue_TV;
    SwitchCompat tue_twenty_four_hr_SC, tue_closed_SC;

    TextView start_time_wed_TV, end_time_wed_TV;
    SwitchCompat wed_twenty_four_hr_SC, wed_closed_SC;


    TextView start_time_thu_TV, end_time_thu_TV;
    SwitchCompat thu_twenty_four_hr_SC, thu_closed_SC;

    TextView start_time_fri_TV, end_time_fri_TV;
    SwitchCompat fri_twenty_four_hr_SC, fri_closed_SC;

    TextView start_time_sat_TV, end_time_sat_TV;
    SwitchCompat sat_twenty_four_hr_SC, sat_closed_SC;


    TextView start_time_sun_TV, end_time_sun_TV;
    SwitchCompat sun_twenty_four_hr_SC, sun_closed_SC;

    TextView sunday_TV, monday_TV, tuesday_TV, wednessday_TV, thursday_TV, friday_TV, saturday_TV;

    Button save_working_BT;


    private int intHour, intMinute;
    private String strMin = "";

    List<GetorkingHoursResponseModel> getorkingHoursResponseModels;
    HashMap<String, HashMap<String, String>> outerHashmap = new HashMap<String, HashMap<String, String>>();
    HashMap<String, String> innerHashMap;
    String mon_id, mon_day_id, tues_day_id, tues_id,wed_day_id, wed_id,thurs_day_id, thurs_id,fri_day_id,fri_id,satr_day_id,satr_id,sun_day_id,sun_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet_operating_hours);
        methods = new Methods(this);
        init();
        if(methods.isInternetOn())
        { getWorkingHours();
        }
        else{
            methods.DialogInternet();
        }
    }

    private void init() {

        back_arrow_CV = findViewById(R.id.back_arrow_CV);
        save_working_BT=findViewById(R.id.save_working_BT);

        start_time_mon_to_sat_TV = findViewById(R.id.start_time_mon_to_sat_TV);
        end_time_mon_to_sat_TV = findViewById(R.id.end_time_mon_to_sat_TV);
        sunday_closed_hr_SC = findViewById(R.id.sunday_closed_hr_SC);
        mon_sat_twenty_four_hr_SC = findViewById(R.id.mon_sat_twenty_four_hr_SC);


        sun_CL = findViewById(R.id.sun_CL);

        start_time_mon_TV = findViewById(R.id.start_time_mon_TV);
        end_time_mon_TV = findViewById(R.id.end_time_mon_TV);
        mon_twenty_four_hr_SC = findViewById(R.id.mon_twenty_four_hr_SC);
        mon_closed_SC = findViewById(R.id.mon_closed_SC);

        start_time_tue_TV = findViewById(R.id.start_time_tue_TV);
        end_time_tue_TV = findViewById(R.id.end_time_tue_TV);
        tue_twenty_four_hr_SC = findViewById(R.id.tue_twenty_four_hr_SC);
        tue_closed_SC = findViewById(R.id.tue_closed_SC);

        start_time_wed_TV = findViewById(R.id.start_time_wed_TV);
        end_time_wed_TV = findViewById(R.id.end_time_wed_TV);
        wed_twenty_four_hr_SC = findViewById(R.id.wed_twenty_four_hr_SC);
        wed_closed_SC = findViewById(R.id.wed_closed_SC);

        start_time_thu_TV = findViewById(R.id.start_time_thu_TV);
        end_time_thu_TV = findViewById(R.id.end_time_thu_TV);
        thu_twenty_four_hr_SC = findViewById(R.id.thu_twenty_four_hr_SC);
        thu_closed_SC = findViewById(R.id.thu_closed_SC);

        start_time_fri_TV = findViewById(R.id.start_time_fri_TV);
        end_time_fri_TV = findViewById(R.id.end_time_fri_TV);
        fri_twenty_four_hr_SC = findViewById(R.id.fri_twenty_four_hr_SC);
        fri_closed_SC = findViewById(R.id.fri_closed_SC);

        start_time_sat_TV = findViewById(R.id.start_time_sat_TV);
        end_time_sat_TV = findViewById(R.id.end_time_sat_TV);
        sat_twenty_four_hr_SC = findViewById(R.id.sat_twenty_four_hr_SC);
        sat_closed_SC = findViewById(R.id.sat_closed_SC);

        start_time_sun_TV = findViewById(R.id.start_time_sun_TV);
        end_time_sun_TV = findViewById(R.id.end_time_sun_TV);
        sun_twenty_four_hr_SC = findViewById(R.id.sun_twenty_four_hr_SC);
        sun_closed_SC = findViewById(R.id.sun_closed_SC);

        mon_RL = findViewById(R.id.mon_RL);
        tue_RL = findViewById(R.id.tue_RL);
        wed_RL = findViewById(R.id.wed_RL);
        thu_RL = findViewById(R.id.thu_RL);
        fri_RL = findViewById(R.id.fri_RL);
        sat_RL = findViewById(R.id.sat_RL);
        sun_RL = findViewById(R.id.sun_RL);

        monday_TV = findViewById(R.id.monday_TV);
        tuesday_TV = findViewById(R.id.tuesday_TV);
        wednessday_TV = findViewById(R.id.wednessday_TV);
        thursday_TV = findViewById(R.id.thursday_TV);
        friday_TV = findViewById(R.id.friday_TV);
        saturday_TV = findViewById(R.id.saturday_TV);
        sunday_TV = findViewById(R.id.sunday_TV);

        mon_RL.setOnClickListener(this);
        tue_RL.setOnClickListener(this);
        wed_RL.setOnClickListener(this);
        thu_RL.setOnClickListener(this);
        fri_RL.setOnClickListener(this);
        sat_RL.setOnClickListener(this);
        sun_RL.setOnClickListener(this);

        mon_CL = findViewById(R.id.mon_CL);
        tue_CL = findViewById(R.id.tue_CL);
        wed_CL = findViewById(R.id.wed_CL);
        thu_CL = findViewById(R.id.thu_CL);
        fri_CL = findViewById(R.id.fri_CL);
        sat_CL = findViewById(R.id.sat_CL);
        sun_CL = findViewById(R.id.sun_CL);

        back_arrow_CV.setOnClickListener(this);
        save_working_BT.setOnClickListener(this);
        start_time_mon_to_sat_TV.setOnClickListener(this);
        end_time_mon_to_sat_TV.setOnClickListener(this);

        start_time_mon_TV.setOnClickListener(this);
        end_time_mon_TV.setOnClickListener(this);

        start_time_tue_TV.setOnClickListener(this);
        end_time_tue_TV.setOnClickListener(this);

        start_time_wed_TV.setOnClickListener(this);
        end_time_wed_TV.setOnClickListener(this);

        start_time_thu_TV.setOnClickListener(this);
        end_time_thu_TV.setOnClickListener(this);

        start_time_sat_TV.setOnClickListener(this);
        end_time_sat_TV.setOnClickListener(this);

        start_time_sun_TV.setOnClickListener(this);
        end_time_sun_TV.setOnClickListener(this);

        //switch
        mon_closed_SC.setOnClickListener(this);
        mon_twenty_four_hr_SC.setOnClickListener(this);

        tue_closed_SC.setOnClickListener(this);
        tue_twenty_four_hr_SC.setOnClickListener(this);

        wed_closed_SC.setOnClickListener(this);
        wed_twenty_four_hr_SC.setOnClickListener(this);

        thu_closed_SC.setOnClickListener(this);
        thu_twenty_four_hr_SC.setOnClickListener(this);

        fri_closed_SC.setOnClickListener(this);
        fri_twenty_four_hr_SC.setOnClickListener(this);

        sat_closed_SC.setOnClickListener(this);
        sat_twenty_four_hr_SC.setOnClickListener(this);

        sun_closed_SC.setOnClickListener(this);
        sun_twenty_four_hr_SC.setOnClickListener(this);

        sunday_closed_hr_SC.setOnClickListener(this);
        mon_sat_twenty_four_hr_SC.setOnClickListener(this);


    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_working_BT:
                gettingValues();
                break;

            case R.id.mon_sat_twenty_four_hr_SC:
                if (mon_sat_twenty_four_hr_SC.isChecked()){
                    start_time_mon_to_sat_TV.setText("12:00 AM");
                    end_time_mon_to_sat_TV.setText("12:00 PM");

                    start_time_mon_TV.setText("12:00 AM");
                    end_time_mon_TV.setText("12:00 PM");
                    mon_closed_SC.setChecked(false);
                    mon_twenty_four_hr_SC.setChecked(true);

                    start_time_tue_TV.setText("12:00 AM");
                    end_time_tue_TV.setText("12:00 PM");
                    tue_closed_SC.setChecked(false);
                    tue_twenty_four_hr_SC.setChecked(true);

                    start_time_wed_TV.setText("12:00 AM");
                    end_time_wed_TV.setText("12:00 PM");
                    wed_closed_SC.setChecked(false);
                    wed_twenty_four_hr_SC.setChecked(true);

                    start_time_thu_TV.setText("12:00 AM");
                    end_time_thu_TV.setText("12:00 PM");
                    thu_closed_SC.setChecked(false);
                    thu_twenty_four_hr_SC.setChecked(true);

                    start_time_fri_TV.setText("12:00 AM");
                    end_time_fri_TV.setText("12:00 PM");
                    fri_closed_SC.setChecked(false);
                    fri_twenty_four_hr_SC.setChecked(true);

                    start_time_sat_TV.setText("12:00 AM");
                    end_time_sat_TV.setText("12:00 PM");
                    sat_closed_SC.setChecked(false);
                    sat_twenty_four_hr_SC.setChecked(true);


                }else {
                    start_time_mon_to_sat_TV.setText("9:30 AM");
                    end_time_mon_to_sat_TV.setText("9:30 PM");

                    start_time_mon_TV.setText("9:30 AM");
                    end_time_mon_TV.setText("6:30 PM");
                    mon_twenty_four_hr_SC.setChecked(false);

                    start_time_tue_TV.setText("9:30 AM");
                    end_time_tue_TV.setText("6:30 PM");
                    tue_twenty_four_hr_SC.setChecked(false);

                    start_time_wed_TV.setText("9:30 AM");
                    end_time_wed_TV.setText("6:30 PM");
                    wed_twenty_four_hr_SC.setChecked(false);

                    start_time_thu_TV.setText("9:30 AM");
                    end_time_thu_TV.setText("6:30 PM");
                    thu_twenty_four_hr_SC.setChecked(false);

                    start_time_fri_TV.setText("9:30 AM");
                    end_time_fri_TV.setText("6:30 PM");
                    fri_twenty_four_hr_SC.setChecked(false);

                    start_time_sat_TV.setText("9:30 AM");
                    end_time_sat_TV.setText("6:30 PM");
                    sat_twenty_four_hr_SC.setChecked(false);


                }
                break;

            case R.id.sunday_closed_hr_SC:
                if (sunday_closed_hr_SC.isChecked()){
                    sun_twenty_four_hr_SC.setChecked(false);
                    sun_closed_SC.setChecked(true);
                    start_time_sun_TV.setText("0:00 AM");
                    end_time_sun_TV.setText("0:00 PM");
                }else {
                    sun_twenty_four_hr_SC.setChecked(false);
                    sun_closed_SC.setChecked(false);
                    start_time_sun_TV.setText("9:30 AM");
                    end_time_sun_TV.setText("6:30 PM");
                }
                break;

            case R.id.mon_closed_SC:
                if (mon_closed_SC.isChecked()){
                    mon_twenty_four_hr_SC.setChecked(false);
                    start_time_mon_TV.setText("0:00 AM");
                    end_time_mon_TV.setText("0:00 PM");
                }else {
                    start_time_mon_TV.setHint("9:30 AM");
                    end_time_mon_TV.setHint("6:30 PM");
                }
                break;

            case R.id.mon_twenty_four_hr_SC:
                if (mon_twenty_four_hr_SC.isChecked()){
                    start_time_mon_TV.setText("12:00 AM");
                    end_time_mon_TV.setText("12:00 PM");
                    mon_closed_SC.setChecked(false);
                }else {
                    start_time_mon_TV.setHint("9:30 AM");
                    end_time_mon_TV.setHint("6:30 PM");
                }
                break;

            case R.id.tue_twenty_four_hr_SC:
                if (tue_twenty_four_hr_SC.isChecked()){
                    start_time_tue_TV.setText("12:00 AM");
                    end_time_tue_TV.setText("12:00 PM");
                    tue_closed_SC.setChecked(false);
                }else {
                    start_time_tue_TV.setText("9:30 AM");
                    end_time_tue_TV.setText("6:30 PM");
                }
                break;

            case R.id.tue_closed_SC:
                if (tue_closed_SC.isChecked()){
                    tue_twenty_four_hr_SC.setChecked(false);
                    start_time_tue_TV.setText("0:00 AM");
                    end_time_tue_TV.setText("0:00 PM");
                }else {
                    start_time_tue_TV.setHint("9:30 AM");
                    end_time_tue_TV.setHint("6:30 PM");
                }
                break;

            case R.id.wed_closed_SC:
                if (wed_closed_SC.isChecked()){
                    wed_twenty_four_hr_SC.setChecked(false);
                    start_time_wed_TV.setText("0:00 AM");
                    end_time_wed_TV.setText("0:00 PM");
                }else {
                    start_time_wed_TV.setHint("9:30 AM");
                    end_time_wed_TV.setHint("6:30 PM");
                }
                break;

            case R.id.wed_twenty_four_hr_SC:
                if (wed_twenty_four_hr_SC.isChecked()){
                    start_time_wed_TV.setText("12:00 AM");
                    end_time_wed_TV.setText("12:00 PM");
                    wed_closed_SC.setChecked(false);
                }else {
                    start_time_wed_TV.setText("9:30 AM");
                    end_time_wed_TV.setText("6:30 PM");
                }
                break;

            case R.id.thu_twenty_four_hr_SC:
                if (thu_twenty_four_hr_SC.isChecked()){
                    start_time_thu_TV.setText("12:00 AM");
                    end_time_thu_TV.setText("12:00 PM");
                    thu_closed_SC.setChecked(false);
                }else {
                    start_time_thu_TV.setText("9:30 AM");
                    end_time_thu_TV.setText("6:30 PM");
                }
                break;

            case R.id.thu_closed_SC:
                if (thu_closed_SC.isChecked()){
                    thu_twenty_four_hr_SC.setChecked(false);
                    start_time_thu_TV.setText("0:00 AM");
                    end_time_thu_TV.setText("0:00 PM");
                }else {
                    start_time_thu_TV.setHint("9:30 AM");
                    end_time_thu_TV.setHint("6:30 PM");
                }
                break;

            case R.id.fri_twenty_four_hr_SC:
                if (fri_twenty_four_hr_SC.isChecked()){
                    start_time_fri_TV.setText("12:00 AM");
                    end_time_fri_TV.setText("12:00 PM");
                    fri_closed_SC.setChecked(false);
                }else {
                    start_time_fri_TV.setText("9:30 AM");
                    end_time_fri_TV.setText("6:30 PM");
                }
                break;

            case R.id.fri_closed_SC:
                if (fri_closed_SC.isChecked()){
                    thu_twenty_four_hr_SC.setChecked(false);
                    start_time_thu_TV.setText("0:00 AM");
                    end_time_thu_TV.setText("0:00 PM");
                }else {
                    start_time_thu_TV.setHint("9:30 AM");
                    end_time_thu_TV.setHint("6:30 PM");
                }
                break;

            case R.id.sat_twenty_four_hr_SC:
                if (sat_twenty_four_hr_SC.isChecked()){
                    start_time_sat_TV.setText("12:00 AM");
                    end_time_sat_TV.setText("12:00 PM");
                    sat_closed_SC.setChecked(false);
                }else {
                    start_time_sat_TV.setText("9:30 AM");
                    end_time_sat_TV.setText("6:30 PM");
                }
                break;

            case R.id.sat_closed_SC:
                if (sat_closed_SC.isChecked()){
                    sat_twenty_four_hr_SC.setChecked(false);
                    start_time_sat_TV.setText("0:00 AM");
                    end_time_sat_TV.setText("0:00 PM");
                }else {
                    start_time_sat_TV.setHint("9:30 AM");
                    end_time_sat_TV.setHint("6:30 PM");
                }
                break;

            case R.id.sun_closed_SC:
                if (sun_closed_SC.isChecked()){
                    sun_twenty_four_hr_SC.setChecked(false);
                    sunday_closed_hr_SC.setChecked(false);
                    start_time_sun_TV.setText("0:00 AM");
                    end_time_sun_TV.setText("0:00 PM");
                }else {
                    sunday_closed_hr_SC.setChecked(true);
                    start_time_sun_TV.setHint("9:30 AM");
                    end_time_sun_TV.setHint("6:30 PM");
                }
                break;

            case R.id.sun_twenty_four_hr_SC:
                if (sun_twenty_four_hr_SC.isChecked()){
                    start_time_sun_TV.setText("12:00 AM");
                    end_time_sun_TV.setText("12:00 PM");
                    sun_closed_SC.setChecked(false);
                }else {
                    start_time_sun_TV.setText("9:30 AM");
                    end_time_sun_TV.setText("6:30 PM");
                }
                break;

            case R.id.back_arrow_CV:
                onBackPressed();
                break;
//            case R.id.start_time_mon_to_sat_TV:
//                selectTimeDialog(start_time_mon_to_sat_TV.getText().toString(), start_time_mon_to_sat_TV.getText().toString(), "MondayStart");
//                break;
//            case R.id.end_time_mon_to_sat_TV:
//                selectTimeDialog(end_time_mon_to_sat_TV.getText().toString(), end_time_mon_TV.getText().toString(), "MondayStart");
//                break;

            case R.id.start_time_mon_TV:
                selectTimeDialog(mon_day_id, mon_id, "MondayStart");
//                gettingValues();
                break;
            case R.id.end_time_mon_TV:
                selectTimeDialog(mon_day_id, mon_id, "MondayClose");
//                gettingValues();
                break;

            case R.id.start_time_tue_TV:
                selectTimeDialog(tues_day_id, tues_id, "TuesdayStart");
//                gettingValues();
                break;
            case R.id.end_time_tue_TV:
                selectTimeDialog(tues_day_id, tues_id, "TuesdayClose");
//                gettingValues();
                break;

            case R.id.start_time_wed_TV:
                selectTimeDialog(wed_day_id, wed_id, "WednesdayStart");
//                gettingValues();
                break;
            case R.id.end_time_wed_TV:
                selectTimeDialog(wed_day_id, wed_id, "WednesdayClose");
//                gettingValues();
                break;

            case R.id.start_time_thu_TV:
                selectTimeDialog(thurs_day_id, thurs_id, "ThursdayStart");
//                gettingValues();
                break;
            case R.id.end_time_thu_TV:
                selectTimeDialog(thurs_day_id, thurs_id, "ThursdayClose");
//                gettingValues();
                break;

            case R.id.start_time_fri_TV:
                selectTimeDialog(fri_day_id, fri_id, "FridayStart");
//                gettingValues();
                break;
            case R.id.end_time_fri_TV:
                selectTimeDialog(fri_day_id, fri_id, "FridayClose");
//                gettingValues();
                break;

            case R.id.start_time_sat_TV:
                selectTimeDialog(satr_day_id, satr_id, "SaturdayStart");
//                gettingValues();
                break;
            case R.id.end_time_sat_TV:
                selectTimeDialog(satr_day_id, satr_id, "SaturdayClose");
//                gettingValues();
                break;

            case R.id.start_time_sun_TV:
                selectTimeDialog(sun_day_id, sun_id, "SundayStart");
//                gettingValues();
                break;
            case R.id.end_time_sun_TV:
                selectTimeDialog(sun_day_id, sun_id, "SundayClose");
//                gettingValues();
                break;

            case R.id.mon_RL:
                mon_CL.setVisibility(View.VISIBLE);
                tue_CL.setVisibility(View.GONE);
                wed_CL.setVisibility(View.GONE);
                thu_CL.setVisibility(View.GONE);
                fri_CL.setVisibility(View.GONE);
                sat_CL.setVisibility(View.GONE);
                sun_CL.setVisibility(View.GONE);

                mon_RL.setBackgroundResource(R.drawable.days_blue_bg);
                tue_RL.setBackgroundResource(R.drawable.days_white_bg);
                wed_RL.setBackgroundResource(R.drawable.days_white_bg);
                thu_RL.setBackgroundResource(R.drawable.days_white_bg);
                fri_RL.setBackgroundResource(R.drawable.days_white_bg);
                sat_RL.setBackgroundResource(R.drawable.days_white_bg);
                sun_RL.setBackgroundResource(R.drawable.days_white_bg);

                monday_TV.setTextColor(this.getResources().getColor(R.color.whiteColor));
                tuesday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                wednessday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                thursday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                friday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                saturday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                sunday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));

                break;


            case R.id.tue_RL:
                mon_CL.setVisibility(View.GONE);
                tue_CL.setVisibility(View.VISIBLE);
                wed_CL.setVisibility(View.GONE);
                thu_CL.setVisibility(View.GONE);
                fri_CL.setVisibility(View.GONE);
                sat_CL.setVisibility(View.GONE);
                sun_CL.setVisibility(View.GONE);

                mon_RL.setBackgroundResource(R.drawable.days_white_bg);
                tue_RL.setBackgroundResource(R.drawable.days_blue_bg);
                wed_RL.setBackgroundResource(R.drawable.days_white_bg);
                thu_RL.setBackgroundResource(R.drawable.days_white_bg);
                fri_RL.setBackgroundResource(R.drawable.days_white_bg);
                sat_RL.setBackgroundResource(R.drawable.days_white_bg);
                sun_RL.setBackgroundResource(R.drawable.days_white_bg);

                monday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                tuesday_TV.setTextColor(this.getResources().getColor(R.color.whiteColor));
                wednessday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                thursday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                friday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                saturday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                sunday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                break;

            case R.id.wed_RL:
                mon_CL.setVisibility(View.GONE);
                tue_CL.setVisibility(View.GONE);
                wed_CL.setVisibility(View.VISIBLE);
                thu_CL.setVisibility(View.GONE);
                fri_CL.setVisibility(View.GONE);
                sat_CL.setVisibility(View.GONE);
                sun_CL.setVisibility(View.GONE);

                mon_RL.setBackgroundResource(R.drawable.days_white_bg);
                tue_RL.setBackgroundResource(R.drawable.days_white_bg);
                wed_RL.setBackgroundResource(R.drawable.days_blue_bg);
                thu_RL.setBackgroundResource(R.drawable.days_white_bg);
                fri_RL.setBackgroundResource(R.drawable.days_white_bg);
                sat_RL.setBackgroundResource(R.drawable.days_white_bg);
                sun_RL.setBackgroundResource(R.drawable.days_white_bg);

                monday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                tuesday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                wednessday_TV.setTextColor(this.getResources().getColor(R.color.whiteColor));
                thursday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                friday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                saturday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                sunday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));

                break;

            case R.id.thu_RL:
                mon_CL.setVisibility(View.GONE);
                tue_CL.setVisibility(View.GONE);
                wed_CL.setVisibility(View.GONE);
                thu_CL.setVisibility(View.VISIBLE);
                fri_CL.setVisibility(View.GONE);
                sat_CL.setVisibility(View.GONE);
                sun_CL.setVisibility(View.GONE);

                mon_RL.setBackgroundResource(R.drawable.days_white_bg);
                tue_RL.setBackgroundResource(R.drawable.days_white_bg);
                wed_RL.setBackgroundResource(R.drawable.days_white_bg);
                thu_RL.setBackgroundResource(R.drawable.days_blue_bg);
                fri_RL.setBackgroundResource(R.drawable.days_white_bg);
                sat_RL.setBackgroundResource(R.drawable.days_white_bg);
                sun_RL.setBackgroundResource(R.drawable.days_white_bg);

                monday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                tuesday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                wednessday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                thursday_TV.setTextColor(this.getResources().getColor(R.color.whiteColor));
                friday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                saturday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                sunday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                break;

            case R.id.fri_RL:
                mon_CL.setVisibility(View.GONE);
                tue_CL.setVisibility(View.GONE);
                wed_CL.setVisibility(View.GONE);
                thu_CL.setVisibility(View.GONE);
                fri_CL.setVisibility(View.VISIBLE);
                sat_CL.setVisibility(View.GONE);
                sun_CL.setVisibility(View.GONE);

                mon_RL.setBackgroundResource(R.drawable.days_white_bg);
                tue_RL.setBackgroundResource(R.drawable.days_white_bg);
                wed_RL.setBackgroundResource(R.drawable.days_white_bg);
                thu_RL.setBackgroundResource(R.drawable.days_white_bg);
                fri_RL.setBackgroundResource(R.drawable.days_blue_bg);
                sat_RL.setBackgroundResource(R.drawable.days_white_bg);
                sun_RL.setBackgroundResource(R.drawable.days_white_bg);

                monday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                tuesday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                wednessday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                thursday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                friday_TV.setTextColor(this.getResources().getColor(R.color.whiteColor));
                saturday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                sunday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));

                break;

            case R.id.sat_RL:
                mon_CL.setVisibility(View.GONE);
                tue_CL.setVisibility(View.GONE);
                wed_CL.setVisibility(View.GONE);
                thu_CL.setVisibility(View.GONE);
                fri_CL.setVisibility(View.GONE);
                sat_CL.setVisibility(View.VISIBLE);
                sun_CL.setVisibility(View.GONE);

                mon_RL.setBackgroundResource(R.drawable.days_white_bg);
                tue_RL.setBackgroundResource(R.drawable.days_white_bg);
                wed_RL.setBackgroundResource(R.drawable.days_white_bg);
                thu_RL.setBackgroundResource(R.drawable.days_white_bg);
                fri_RL.setBackgroundResource(R.drawable.days_white_bg);
                sat_RL.setBackgroundResource(R.drawable.days_blue_bg);
                sun_RL.setBackgroundResource(R.drawable.days_white_bg);

                monday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                tuesday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                wednessday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                thursday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                friday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                saturday_TV.setTextColor(this.getResources().getColor(R.color.whiteColor));
                sunday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                break;

            case R.id.sun_RL:
                mon_CL.setVisibility(View.GONE);
                tue_CL.setVisibility(View.GONE);
                wed_CL.setVisibility(View.GONE);
                thu_CL.setVisibility(View.GONE);
                fri_CL.setVisibility(View.GONE);
                sat_CL.setVisibility(View.GONE);
                sun_CL.setVisibility(View.VISIBLE);

                mon_RL.setBackgroundResource(R.drawable.days_white_bg);
                tue_RL.setBackgroundResource(R.drawable.days_white_bg);
                wed_RL.setBackgroundResource(R.drawable.days_white_bg);
                thu_RL.setBackgroundResource(R.drawable.days_white_bg);
                fri_RL.setBackgroundResource(R.drawable.days_white_bg);
                sat_RL.setBackgroundResource(R.drawable.days_white_bg);
                sun_RL.setBackgroundResource(R.drawable.days_blue_bg);

                monday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                tuesday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                wednessday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                thursday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                friday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                saturday_TV.setTextColor(this.getResources().getColor(R.color.gray_1));
                sunday_TV.setTextColor(this.getResources().getColor(R.color.whiteColor));
                break;


        }
    }

    private void gettingValuesForAllDays() {
        ArrayList<HashMap<String, String>> actualList = new ArrayList<>();
        innerHashMap = new HashMap<>();
        innerHashMap.put("id", mon_id);
        innerHashMap.put("dayId", mon_day_id);
        innerHashMap.put("startTime", start_time_mon_to_sat_TV.getText().toString());
        innerHashMap.put("endTime", end_time_mon_to_sat_TV.getText().toString());

        if (mon_sat_twenty_four_hr_SC.isChecked()) {
            innerHashMap.put("allDayOpen", "true");
            innerHashMap.put("isClosed", "false");
        } else {
            innerHashMap.put("allDayOpen", "false");
            innerHashMap.put("isClosed", "true");
        }

        actualList.add(innerHashMap);

        innerHashMap = new HashMap<>();
        innerHashMap.put("id", tues_id);
        innerHashMap.put("dayId", tues_day_id);
        innerHashMap.put("startTime", start_time_mon_to_sat_TV.getText().toString());
        innerHashMap.put("endTime", end_time_mon_to_sat_TV.getText().toString());

        if (mon_sat_twenty_four_hr_SC.isChecked()) {
            innerHashMap.put("allDayOpen", "true");
            innerHashMap.put("isClosed", "false");
        } else {
            innerHashMap.put("allDayOpen", "false");
            innerHashMap.put("isClosed", "true");
        }

        actualList.add(innerHashMap);

        innerHashMap = new HashMap<>();
        innerHashMap.put("id", wed_id);
        innerHashMap.put("dayId", wed_day_id);
        innerHashMap.put("startTime", start_time_mon_to_sat_TV.getText().toString());
        innerHashMap.put("endTime", end_time_mon_to_sat_TV.getText().toString());

        if (mon_sat_twenty_four_hr_SC.isChecked()) {
            innerHashMap.put("allDayOpen", "true");
            innerHashMap.put("isClosed", "false");
        } else {
            innerHashMap.put("allDayOpen", "false");
            innerHashMap.put("isClosed", "true");
        }

        actualList.add(innerHashMap);

        innerHashMap = new HashMap<>();
        innerHashMap.put("id", thurs_id);
        innerHashMap.put("dayId", thurs_day_id);
        innerHashMap.put("startTime", start_time_mon_to_sat_TV.getText().toString());
        innerHashMap.put("endTime", end_time_mon_to_sat_TV.getText().toString());

        if (mon_sat_twenty_four_hr_SC.isChecked()) {
            innerHashMap.put("allDayOpen", "true");
            innerHashMap.put("isClosed", "false");
        } else {
            innerHashMap.put("allDayOpen", "false");
            innerHashMap.put("isClosed", "true");
        }


        actualList.add(innerHashMap);

        innerHashMap = new HashMap<>();
        innerHashMap.put("id", fri_id);
        innerHashMap.put("dayId", fri_day_id);
        innerHashMap.put("startTime", start_time_mon_to_sat_TV.getText().toString());
        innerHashMap.put("endTime", end_time_mon_to_sat_TV.getText().toString());

        if (mon_sat_twenty_four_hr_SC.isChecked()) {
            innerHashMap.put("allDayOpen", "true");
            innerHashMap.put("isClosed", "false");
        } else {
            innerHashMap.put("allDayOpen", "false");
            innerHashMap.put("isClosed", "true");
        }
        actualList.add(innerHashMap);

        innerHashMap = new HashMap<>();
        innerHashMap.put("id", satr_id);
        innerHashMap.put("dayId", satr_day_id);
        innerHashMap.put("startTime", start_time_mon_to_sat_TV.getText().toString());
        innerHashMap.put("endTime", end_time_mon_to_sat_TV.getText().toString());

        if (mon_sat_twenty_four_hr_SC.isChecked()) {
            innerHashMap.put("allDayOpen", "true");
            innerHashMap.put("isClosed", "false");
        } else {
            innerHashMap.put("allDayOpen", "false");
            innerHashMap.put("isClosed", "true");
        }

        actualList.add(innerHashMap);

        innerHashMap = new HashMap<>();
        innerHashMap.put("id", sun_id);
        innerHashMap.put("dayId", sun_day_id);
        innerHashMap.put("startTime", start_time_mon_to_sat_TV.getText().toString());
        innerHashMap.put("endTime", end_time_mon_to_sat_TV.getText().toString());

        if (sunday_closed_hr_SC.isChecked()) {
            innerHashMap.put("allDayOpen", "false");
            innerHashMap.put("isClosed", "true");
        }else{
            innerHashMap.put("isClosed", "false");
            if (sat_twenty_four_hr_SC.isChecked()) {
                innerHashMap.put("allDayOpen", "true");
            }else {
                innerHashMap.put("allDayOpen", "false");
            }
        }

        actualList.add(innerHashMap);

        Log.d("actual_ArrayList", "" + actualList.toString());

        Gson gson = new GsonBuilder().create();
        JsonArray myCustomArray = gson.toJsonTree(actualList).getAsJsonArray();

        Log.d("actual_ArrayList", "after_json" + myCustomArray);
        WorkingHoursParameter workingHoursParameter = new WorkingHoursParameter();
        workingHoursParameter.setData(myCustomArray);

        if (methods.isInternetOn()) {
            saveWorkingHours(workingHoursParameter);
        } else {
            methods.DialogInternet();
        }
    }


    private void gettingValues() {
        methods.showCustomProgressBarDialog(this);
        ArrayList<HashMap<String, String>> actualList = new ArrayList<>();
        innerHashMap = new HashMap<>();
        innerHashMap.put("id", mon_id);
        innerHashMap.put("dayId", mon_day_id);
        innerHashMap.put("startTime", start_time_mon_TV.getText().toString());
        innerHashMap.put("endTime", end_time_mon_TV.getText().toString());

        if (mon_twenty_four_hr_SC.isChecked())
            innerHashMap.put("allDayOpen", "true");
        else
            innerHashMap.put("allDayOpen", "false");

        if (mon_closed_SC.isChecked())
            innerHashMap.put("isClosed", "true");
        else
            innerHashMap.put("isClosed", "false");

        actualList.add(innerHashMap);

        innerHashMap = new HashMap<>();
        innerHashMap.put("id", tues_id);
        innerHashMap.put("dayId", tues_day_id);
        innerHashMap.put("startTime", start_time_tue_TV.getText().toString());
        innerHashMap.put("endTime", end_time_tue_TV.getText().toString());
        if (tue_twenty_four_hr_SC.isChecked())
            innerHashMap.put("allDayOpen", "true");
        else
            innerHashMap.put("allDayOpen", "false");
        if (tue_closed_SC.isChecked())
            innerHashMap.put("isClosed", "true");
        else
            innerHashMap.put("isClosed", "false");

        actualList.add(innerHashMap);

        innerHashMap = new HashMap<>();
        innerHashMap.put("id", wed_id);
        innerHashMap.put("dayId", wed_day_id);
        innerHashMap.put("startTime", start_time_wed_TV.getText().toString());
        innerHashMap.put("endTime", end_time_wed_TV.getText().toString());
        if (wed_twenty_four_hr_SC.isChecked())
            innerHashMap.put("allDayOpen", "true");
        else
            innerHashMap.put("allDayOpen", "false");

        if (wed_closed_SC.isChecked())
            innerHashMap.put("isClosed", "true");
        else
            innerHashMap.put("isClosed", "false");

        actualList.add(innerHashMap);

        innerHashMap = new HashMap<>();
        innerHashMap.put("id", thurs_id);
        innerHashMap.put("dayId", thurs_day_id);
        innerHashMap.put("startTime", start_time_thu_TV.getText().toString());
        innerHashMap.put("endTime", end_time_thu_TV.getText().toString());
        if (thu_twenty_four_hr_SC.isChecked())
            innerHashMap.put("allDayOpen", "true");
        else
            innerHashMap.put("allDayOpen", "false");
        if (thu_closed_SC.isChecked())
            innerHashMap.put("isClosed", "true");
        else
            innerHashMap.put("isClosed", "false");

        actualList.add(innerHashMap);

        innerHashMap = new HashMap<>();
        innerHashMap.put("id", fri_id);
        innerHashMap.put("dayId", fri_day_id);
        innerHashMap.put("startTime", start_time_fri_TV.getText().toString());
        innerHashMap.put("endTime", end_time_fri_TV.getText().toString());
        if (fri_twenty_four_hr_SC.isChecked())
            innerHashMap.put("allDayOpen", "true");
        else
            innerHashMap.put("allDayOpen", "false");

        if (fri_closed_SC.isChecked())
            innerHashMap.put("isClosed", "true");
        else
            innerHashMap.put("isClosed", "false");
        actualList.add(innerHashMap);

        innerHashMap = new HashMap<>();
        innerHashMap.put("id", satr_id);
        innerHashMap.put("dayId", satr_day_id);
        innerHashMap.put("startTime", start_time_sat_TV.getText().toString());
        innerHashMap.put("endTime", end_time_sat_TV.getText().toString());
        if (sat_twenty_four_hr_SC.isChecked())
            innerHashMap.put("allDayOpen", "true");
        else
            innerHashMap.put("allDayOpen", "false");

        if (sat_closed_SC.isChecked())
            innerHashMap.put("isClosed", "true");
        else
            innerHashMap.put("isClosed", "false");
        actualList.add(innerHashMap);

        innerHashMap = new HashMap<>();
        innerHashMap.put("id", sun_id);
        innerHashMap.put("dayId", sun_day_id);
        innerHashMap.put("startTime", start_time_sun_TV.getText().toString());
        innerHashMap.put("endTime", start_time_sun_TV.getText().toString());
        innerHashMap.put("isClosed", "false");
        innerHashMap.put("allDayOpen", "false");
        if (sun_twenty_four_hr_SC.isChecked())
            innerHashMap.put("allDayOpen", "true");
        else
            innerHashMap.put("allDayOpen", "false");

        if (sun_closed_SC.isChecked())
            innerHashMap.put("isClosed", "true");
        else
            innerHashMap.put("isClosed", "false");
        actualList.add(innerHashMap);

        Log.d("actual_ArrayList", "" + actualList.toString());

        Gson gson = new GsonBuilder().create();
        JsonArray myCustomArray = gson.toJsonTree(actualList).getAsJsonArray();

        Log.d("actual_ArrayList", "after_json" + myCustomArray);
        WorkingHoursParameter workingHoursParameter = new WorkingHoursParameter();
        workingHoursParameter.setData(myCustomArray);

        if (methods.isInternetOn()) {
            saveWorkingHours(workingHoursParameter);
        } else {
            methods.DialogInternet();
        }
    }

    private void saveWorkingHours(WorkingHoursParameter workingHoursParameter) {
//        methods.showCustomProgressBarDialog(this);
        ApiService<SaveWorkingHoursResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().saveOperatingHours(Config.token, workingHoursParameter), "SaveOperatingHours");
        Log.e("DIALOG_Save_DATA", "==>" + methods.getRequestJson(workingHoursParameter));
    }


    public void selectTimeDialog(final String DayId, final String id, final String type) {
        Log.d("DayId", "" + DayId);

        CustomTimePickerDialog timePickerDialog = new CustomTimePickerDialog(VetOperatingHoursActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @SuppressLint("NewApi")
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Log.e("Log", "selected time----" + hourOfDay + ":" + minute);
                String strTime = hourOfDay + ":" + minute + ":00";

                updateTime(hourOfDay, minute, DayId, id, type);
            }
        }, intHour, intMinute, false);
        timePickerDialog.show();
    }

    private void updateTime(int hours, int mins, String DayId, String id, String type) {
        String timeSet = "";
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12)
            timeSet = "PM";
        else
            timeSet = "AM";

        if (mins < 10)
            strMin = "0" + mins;
        else
            strMin = String.valueOf(mins);
        // Append in a StringBuilder
        String aTime = new StringBuilder().append(pad(hours)).append(':')
                .append(pad(Integer.parseInt(strMin))).append(" ").append(timeSet).toString();
        Log.e("Time",aTime.substring(1));
        if (hours<10){
            aTime = aTime.substring(1);
            if ((type.equals("MondayStart")) && (DayId.equals("1.0"))) {
                start_time_mon_TV.setText(aTime);
            } else if ((type.equals("MondayClose")) && (DayId.equals("1.0"))) {
                end_time_mon_TV.setText(aTime);
            } else if ((type.equals("TuesdayStart")) && (DayId.equals("2.0"))) {
                start_time_tue_TV.setText(aTime);
            } else if ((type.equals("TuesdayClose")) && (DayId.equals("2.0"))) {
                end_time_tue_TV.setText(aTime);
            } else if ((type.equals("WednesdayStart")) && (DayId.equals("3.0"))) {
                start_time_wed_TV.setText(aTime);
            } else if ((type.equals("WednesdayClose")) && (DayId.equals("3.0"))) {
                end_time_wed_TV.setText(aTime);
            } else if ((type.equals("ThursdayStart")) && (DayId.equals("4.0"))) {
                start_time_thu_TV.setText(aTime);
            } else if ((type.equals("ThursdayClose")) && (DayId.equals("4.0"))) {
                end_time_thu_TV.setText(aTime);
            } else if ((type.equals("FridayStart")) && (DayId.equals("5.0"))) {
                start_time_fri_TV.setText(aTime);
            } else if ((type.equals("FridayClose")) && (DayId.equals("5.0"))) {
                end_time_fri_TV.setText(aTime);
            } else if ((type.equals("SaturdayStart")) && (DayId.equals("6.0"))) {
                start_time_sat_TV.setText(aTime);
            } else if ((type.equals("SaturdayClose")) && (DayId.equals("6.0"))) {
                end_time_sat_TV.setText(aTime);
            } else if ((type.equals("SundayStart")) && (DayId.equals("7.0"))) {
                start_time_sun_TV.setText(aTime);
            } else if ((type.equals("SundayClose")) && (DayId.equals("7.0"))) {
                end_time_sun_TV.setText(aTime);
            }
        }else {
            if ((type.equals("MondayStart")) && (DayId.equals("1.0"))) {
                start_time_mon_TV.setText(aTime);
            } else if ((type.equals("MondayClose")) && (DayId.equals("1.0"))) {
                end_time_mon_TV.setText(aTime);
            } else if ((type.equals("TuesdayStart")) && (DayId.equals("2.0"))) {
                start_time_tue_TV.setText(aTime);
            } else if ((type.equals("TuesdayClose")) && (DayId.equals("2.0"))) {
                end_time_tue_TV.setText(aTime);
            } else if ((type.equals("WednesdayStart")) && (DayId.equals("3.0"))) {
                start_time_wed_TV.setText(aTime);
            } else if ((type.equals("WednesdayClose")) && (DayId.equals("3.0"))) {
                end_time_wed_TV.setText(aTime);
            } else if ((type.equals("ThursdayStart")) && (DayId.equals("4.0"))) {
                start_time_thu_TV.setText(aTime);
            } else if ((type.equals("ThursdayClose")) && (DayId.equals("4.0"))) {
                end_time_thu_TV.setText(aTime);
            } else if ((type.equals("FridayStart")) && (DayId.equals("5.0"))) {
                start_time_fri_TV.setText(aTime);
            } else if ((type.equals("FridayClose")) && (DayId.equals("5.0"))) {
                end_time_fri_TV.setText(aTime);
            } else if ((type.equals("SaturdayStart")) && (DayId.equals("6.0"))) {
                start_time_sat_TV.setText(aTime);
            } else if ((type.equals("SaturdayClose")) && (DayId.equals("6.0"))) {
                end_time_sat_TV.setText(aTime);
            } else if ((type.equals("SundayStart")) && (DayId.equals("7.0"))) {
                start_time_sun_TV.setText(aTime);
            } else if ((type.equals("SundayClose")) && (DayId.equals("7.0"))) {
                end_time_sun_TV.setText(aTime);
            }
        }

        Log.e("aTime checking ==>", "" + aTime);
        Log.e("zhsbdbsvdbsv", "" + DayId);
        Log.e("kakkakak", "" + type);




    }

    private String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    @Override
    public void onResponse(Response arg0, String key) {

        switch (key) {
            case "GetOperatingHours":
                try {
                    methods.customProgressDismiss();
                    WorkingHoursResponse workingHoursResponse = (WorkingHoursResponse) arg0.body();
                    Log.e("GetOperatingHours", workingHoursResponse.toString());
                    int responseCode = Integer.parseInt(workingHoursResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        Log.d("amkaknan", "" + workingHoursResponse.getData().get(1).getId());
                        getorkingHoursResponseModels = workingHoursResponse.getData();
                       /* LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                        operarting_hrs_list.setLayoutManager(linearLayoutManager);
                        operatingHoursAdaptear  = new OperatingHoursAdaptear(this,workingHoursResponse.getData(),this);
                        operarting_hrs_list.setAdapter(operatingHoursAdaptear);
                        operatingHoursAdaptear.notifyDataSetChanged();*/
                        for (int i = 0; i < workingHoursResponse.getData().size(); i++) {
                            innerHashMap = new HashMap<String, String>();
                            if (workingHoursResponse.getData().get(i).getDayId().equals("1.0")) {
                                mon_day_id = (workingHoursResponse.getData().get(i).getDayId());
                                mon_id = (workingHoursResponse.getData().get(i).getId());
                                start_time_mon_TV.setText(workingHoursResponse.getData().get(i).getStartTime());
                                end_time_mon_TV.setText(workingHoursResponse.getData().get(i).getEndTime());
                                if (workingHoursResponse.getData().get(i).getIsClosed().equals("true")) {
                                    mon_closed_SC.setChecked(true);
                                } else {
                                    mon_closed_SC.setChecked(false);

                                }
                                if (workingHoursResponse.getData().get(i).getAllDayOpen().equals("true")) {
                                    mon_twenty_four_hr_SC.setChecked(true);
                                } else {
                                    mon_twenty_four_hr_SC.setChecked(false);

                                }
                            }

                            if (workingHoursResponse.getData().get(i).getDayId().equals("2.0")) {
                                tues_day_id=(workingHoursResponse.getData().get(i).getDayId());
                                tues_id=(workingHoursResponse.getData().get(i).getId());
                                start_time_tue_TV.setText(workingHoursResponse.getData().get(i).getStartTime());
                                end_time_tue_TV.setText(workingHoursResponse.getData().get(i).getEndTime());
                                if (workingHoursResponse.getData().get(i).getIsClosed().equals("true")) {
                                    tue_closed_SC.setChecked(true);
                                } else {
                                    tue_closed_SC.setChecked(false);
                                }
                                if (workingHoursResponse.getData().get(i).getAllDayOpen().equals("true")) {
                                    tue_twenty_four_hr_SC.setChecked(true);
                                } else {
                                    tue_twenty_four_hr_SC.setChecked(false);
                                }
                            }

                            if (workingHoursResponse.getData().get(i).getDayId().equals("3.0")) {
                                wed_day_id=(workingHoursResponse.getData().get(i).getDayId());
                                wed_id=(workingHoursResponse.getData().get(i).getId());
                                start_time_wed_TV.setText(workingHoursResponse.getData().get(i).getStartTime());
                                end_time_wed_TV.setText(workingHoursResponse.getData().get(i).getEndTime());
                                if (workingHoursResponse.getData().get(i).getIsClosed().equals("true")){
                                    wed_closed_SC.setChecked(true);
                                }else {
                                    wed_closed_SC.setChecked(false);
                                }
                                if (workingHoursResponse.getData().get(i).getAllDayOpen().equals("true")){
                                    wed_twenty_four_hr_SC.setChecked(true);
                                }else {
                                    wed_twenty_four_hr_SC.setChecked(false);
                                }
                            }

                            if (workingHoursResponse.getData().get(i).getDayId().equals("4.0")) {
                                thurs_day_id=(workingHoursResponse.getData().get(i).getDayId());
                                thurs_id=(workingHoursResponse.getData().get(i).getId());
                                start_time_thu_TV.setText(workingHoursResponse.getData().get(i).getStartTime());
                                end_time_thu_TV.setText(workingHoursResponse.getData().get(i).getEndTime());
                                if (workingHoursResponse.getData().get(i).getIsClosed().equals("true")) {
                                    thu_closed_SC.setChecked(true);
                                }else {
                                    thu_closed_SC.setChecked(false);
                                }
                                if (workingHoursResponse.getData().get(i).getAllDayOpen().equals("true")) {
                                    thu_twenty_four_hr_SC.setChecked(true);
                                }else {
                                    thu_twenty_four_hr_SC.setChecked(false);
                                }
                            }

                            if (workingHoursResponse.getData().get(i).getDayId().equals("5.0")) {
                                fri_day_id=(workingHoursResponse.getData().get(i).getDayId());
                                fri_id=(workingHoursResponse.getData().get(i).getId());
                                start_time_fri_TV.setText(workingHoursResponse.getData().get(i).getStartTime());
                                end_time_fri_TV.setText(workingHoursResponse.getData().get(i).getEndTime());
                                if (workingHoursResponse.getData().get(i).getIsClosed().equals("true")) {
                                    fri_closed_SC.setChecked(true);
                                }else {
                                    fri_closed_SC.setChecked(false);
                                }
                                if (workingHoursResponse.getData().get(i).getAllDayOpen().equals("true")) {
                                    fri_twenty_four_hr_SC.setChecked(true);
                                }else {
                                    fri_twenty_four_hr_SC.setChecked(false);
                                }
                            }

                            if (workingHoursResponse.getData().get(i).getDayId().equals("6.0")) {
                                satr_day_id=(workingHoursResponse.getData().get(i).getDayId());
                                satr_id=(workingHoursResponse.getData().get(i).getId());
                                start_time_sat_TV.setText(workingHoursResponse.getData().get(i).getStartTime());
                                end_time_sat_TV.setText(workingHoursResponse.getData().get(i).getEndTime());
                                if (workingHoursResponse.getData().get(i).getIsClosed().equals("true")) {
                                    sat_closed_SC.setChecked(true);
                                }else {
                                    sat_closed_SC.setChecked(false);
                                }
                                if (workingHoursResponse.getData().get(i).getAllDayOpen().equals("true")) {
                                    sat_twenty_four_hr_SC.setChecked(true);
                                }else {
                                    sat_twenty_four_hr_SC.setChecked(false);
                                }
                            }

                            if (workingHoursResponse.getData().get(i).getDayId().equals("7.0")) {
                                sun_day_id=(workingHoursResponse.getData().get(i).getDayId());
                                sun_id=(workingHoursResponse.getData().get(i).getId());
                                start_time_sun_TV.setText(workingHoursResponse.getData().get(i).getStartTime());
                                end_time_sun_TV.setText(workingHoursResponse.getData().get(i).getEndTime());
                                if (workingHoursResponse.getData().get(i).getIsClosed().equals("true")) {
                                    sun_closed_SC.setChecked(true);
                                    sunday_closed_hr_SC.setChecked(true);
                                }else {
                                    sun_closed_SC.setChecked(false);
                                    sunday_closed_hr_SC.setChecked(false);
                                }
                                if (workingHoursResponse.getData().get(i).getAllDayOpen().equals("true")) {
                                    sun_twenty_four_hr_SC.setChecked(true);
                                }else {
                                    sun_twenty_four_hr_SC.setChecked(false);
                                }
                            }

                            innerHashMap.put("id", workingHoursResponse.getData().get(i).getId());
                            innerHashMap.put("dayId", workingHoursResponse.getData().get(i).getDayId());
                            innerHashMap.put("startTime", workingHoursResponse.getData().get(i).getStartTime());
                            innerHashMap.put("endTime", workingHoursResponse.getData().get(i).getEndTime());
                            innerHashMap.put("isClosed", workingHoursResponse.getData().get(i).getIsClosed());
                            innerHashMap.put("allDayOpen", workingHoursResponse.getData().get(i).getAllDayOpen());
                            outerHashmap.put(workingHoursResponse.getData().get(i).getDayId(), innerHashMap);
                        }
//                        shimmer_view_container.stopShimmerAnimation();
//                        shimmer_view_container.setVisibility(View.GONE);
//                        main_view.setVisibility(View.VISIBLE);
//                        Log.e("outerHashMap", "" + outerHashmap);
                    }


//                    if (methods.isInternetOn()) {
////                        methods.customProgressDismiss();
//                        getWorkingHours();
////                            methods.customProgressDismiss();
//                    } else {
//                        methods.DialogInternet();
//                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "SaveOperatingHours":
                try {
                    methods.customProgressDismiss();
//                    methods.showCustomProgressBarDialog(this);
                    SaveWorkingHoursResponse saveWorkingHoursResponse = (SaveWorkingHoursResponse) arg0.body();
                    Log.e("SaveOperatingHours", saveWorkingHoursResponse.toString());
                    int responseCode = Integer.parseInt(saveWorkingHoursResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        Toast.makeText(this, "Successfully Updated  ", Toast.LENGTH_SHORT).show();
                    }

//                    if (methods.isInternetOn()) {
////                        methods.customProgressDismiss();
//                        getWorkingHours();
////                        methods.customProgressDismiss();
//                    } else {
//                        methods.DialogInternet();
//                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }


    }

    private void getWorkingHours() {
        methods.showCustomProgressBarDialog(this);
        ApiService<WorkingHoursResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getOperatingHours(Config.token), "GetOperatingHours");
    }

    @Override
    public void onError(Throwable t, String key) {

    }
}