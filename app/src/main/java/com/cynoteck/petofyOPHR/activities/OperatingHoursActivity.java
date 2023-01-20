//package com.cynoteck.petofyOPHR.activities;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import retrofit2.Response;
//
//import android.annotation.SuppressLint;
//import android.app.TimePickerDialog;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.ImageView;
//import android.widget.ScrollView;
//import android.widget.TextView;
//import android.widget.TimePicker;
//import android.widget.Toast;
//
//import com.cynoteck.petofyOPHR.R;
//import com.cynoteck.petofyOPHR.api.ApiClient;
//import com.cynoteck.petofyOPHR.api.ApiResponse;
//import com.cynoteck.petofyOPHR.api.ApiService;
//import com.cynoteck.petofyOPHR.params.workingHoursParameter.WorkingHoursParameter;
//import com.cynoteck.petofyOPHR.response.getWorkingHoursResponse.GetorkingHoursResponseModel;
//import com.cynoteck.petofyOPHR.response.getWorkingHoursResponse.WorkingHoursResponse;
//import com.cynoteck.petofyOPHR.response.saveWorkingReponse.SaveWorkingHoursResponse;
//import com.cynoteck.petofyOPHR.utils.Config;
//import com.cynoteck.petofyOPHR.utils.CustomTimePickerDialog;
//import com.cynoteck.petofyOPHR.utils.Methods;
//import com.facebook.shimmer.ShimmerFrameLayout;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonArray;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class OperatingHoursActivity extends AppCompatActivity implements View.OnClickListener, ApiResponse {
//
//    TextView moday_start_time, moday_close_time, tuesday_start_time, tuesday_close_time, wednessday_start_time, wednessday_close_time, thusday_start_time,
//            thusday_close_time, friday_start_time, friday_close_time, saturday_start_time, saturday_close_time,
//            sunday_start_time, sunday_close_time, mon_day_id, mon_id, tues_day_id, tues_id, wed_day_id, wed_id, thurs_day_id, thurs_id,
//            fri_day_id, fri_id, satr_day_id, satr_id, sun_day_id, sun_id;
//
//    CheckBox monday_tfhrs_chkbox, monday_close_chkbox, tuesday_tfhrs_chkbox, tuesday_close_chkbox, wednessday_tfhrs_chkbox,
//            wednessday_close_chkbox, thusday_tfhrs_chkbox, thusday_close_chkbox, friday_tfhrs_chkbox, friday_close_chkbox, saturday_tfhrs_chkbox,
//            saturday_close_chkbox, sunday_tfhrs_chkbox, sunday_close_chkbox;
//
//    Button save_working_hours;
//    ImageView back;
//    ScrollView main_view;
//    ShimmerFrameLayout shimmer_view_container;
//
//
//    /*RecyclerView operarting_hrs_list;*/
//    /*OperatingHoursAdaptear operatingHoursAdaptear;*/
//
//    Methods method;
//    private TimePickerDialog timePickerDialog;
//    List<GetorkingHoursResponseModel> getorkingHoursResponseModels;
//    HashMap<String, HashMap<String, String>> outerHashmap = new HashMap<String, HashMap<String, String>>();
//    HashMap<String, String> innerHashMap;
//    private int intHour, intMinute;
//    private String strMin = "";
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_operating_hours);
//
//        initialize();
//
//
//    }
//
//    private void initialize() {
//        /* operarting_hrs_list=findViewById(R.id.operarting_hrs_list);*/
//
//        back = findViewById(R.id.back);
//        main_view = findViewById(R.id.main_view);
//        shimmer_view_container = findViewById(R.id.shimmer_view_container);
//        moday_start_time = findViewById(R.id.moday_start_time);
//        moday_close_time = findViewById(R.id.moday_close_time);
//        tuesday_start_time = findViewById(R.id.tuesday_start_time);
//        tuesday_close_time = findViewById(R.id.tuesday_close_time);
//        wednessday_start_time = findViewById(R.id.wednessday_start_time);
//        wednessday_close_time = findViewById(R.id.wednessday_close_time);
//        thusday_start_time = findViewById(R.id.thusday_start_time);
//        thusday_close_time = findViewById(R.id.thusday_close_time);
//        friday_start_time = findViewById(R.id.friday_start_time);
//        friday_close_time = findViewById(R.id.friday_close_time);
//        saturday_start_time = findViewById(R.id.saturday_start_time);
//        saturday_close_time = findViewById(R.id.saturday_close_time);
//        sunday_start_time = findViewById(R.id.sunday_start_time);
//        sunday_close_time = findViewById(R.id.sunday_close_time);
//        save_working_hours = findViewById(R.id.save_working_hours);
//
//        mon_day_id = findViewById(R.id.mon_day_id);
//        mon_id = findViewById(R.id.mon_id);
//        tues_day_id = findViewById(R.id.tues_day_id);
//        tues_id = findViewById(R.id.tues_id);
//        wed_day_id = findViewById(R.id.wed_day_id);
//        wed_id = findViewById(R.id.wed_id);
//        thurs_day_id = findViewById(R.id.thurs_day_id);
//        thurs_id = findViewById(R.id.thurs_id);
//        fri_day_id = findViewById(R.id.fri_day_id);
//        fri_id = findViewById(R.id.fri_id);
//        satr_day_id = findViewById(R.id.satr_day_id);
//        satr_id = findViewById(R.id.satr_id);
//        sun_day_id = findViewById(R.id.sun_day_id);
//        sun_id = findViewById(R.id.sun_id);
//
//        moday_start_time.setOnClickListener(this);
//        moday_close_time.setOnClickListener(this);
//        tuesday_start_time.setOnClickListener(this);
//        tuesday_close_time.setOnClickListener(this);
//        wednessday_start_time.setOnClickListener(this);
//        wednessday_close_time.setOnClickListener(this);
//        thusday_start_time.setOnClickListener(this);
//        thusday_close_time.setOnClickListener(this);
//        friday_start_time.setOnClickListener(this);
//        friday_close_time.setOnClickListener(this);
//        saturday_start_time.setOnClickListener(this);
//        saturday_close_time.setOnClickListener(this);
//        sunday_start_time.setOnClickListener(this);
//        sunday_close_time.setOnClickListener(this);
//        save_working_hours.setOnClickListener(this);
//        back.setOnClickListener(this);
//
//        monday_tfhrs_chkbox = findViewById(R.id.monday_tfhrs_chkbox);
//        monday_close_chkbox = findViewById(R.id.monday_close_chkbox);
//        tuesday_tfhrs_chkbox = findViewById(R.id.tuesday_tfhrs_chkbox);
//        tuesday_close_chkbox = findViewById(R.id.tuesday_close_chkbox);
//        wednessday_tfhrs_chkbox = findViewById(R.id.wednessday_tfhrs_chkbox);
//        wednessday_close_chkbox = findViewById(R.id.wednessday_close_chkbox);
//        thusday_tfhrs_chkbox = findViewById(R.id.thusday_tfhrs_chkbox);
//        thusday_close_chkbox = findViewById(R.id.thusday_close_chkbox);
//        friday_tfhrs_chkbox = findViewById(R.id.friday_tfhrs_chkbox);
//        friday_close_chkbox = findViewById(R.id.friday_close_chkbox);
//        saturday_tfhrs_chkbox = findViewById(R.id.saturday_tfhrs_chkbox);
//        saturday_close_chkbox = findViewById(R.id.saturday_close_chkbox);
//        sunday_tfhrs_chkbox = findViewById(R.id.sunday_tfhrs_chkbox);
//        sunday_close_chkbox = findViewById(R.id.sunday_close_chkbox);
//
//        monday_tfhrs_chkbox.setOnClickListener(this);
//        monday_close_chkbox.setOnClickListener(this);
//        tuesday_tfhrs_chkbox.setOnClickListener(this);
//        tuesday_close_chkbox.setOnClickListener(this);
//        wednessday_tfhrs_chkbox.setOnClickListener(this);
//        wednessday_close_chkbox.setOnClickListener(this);
//        thusday_tfhrs_chkbox.setOnClickListener(this);
//        thusday_close_chkbox.setOnClickListener(this);
//        friday_tfhrs_chkbox.setOnClickListener(this);
//        friday_close_chkbox.setOnClickListener(this);
//        saturday_tfhrs_chkbox.setOnClickListener(this);
//        saturday_close_chkbox.setOnClickListener(this);
//        sunday_tfhrs_chkbox.setOnClickListener(this);
//        sunday_close_chkbox.setOnClickListener(this);
//
//        method = new Methods(this);
//        if (method.isInternetOn()) {
//            getWorkingHours();
//        } else {
//            method.DialogInternet();
//        }
//    }
//
//    private void getWorkingHours() {
//        shimmer_view_container.startShimmerAnimation();
//        ApiService<WorkingHoursResponse> service = new ApiService<>();
//        service.get(this, ApiClient.getApiInterface().getOperatingHours(Config.token), "GetOperatingHours");
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.moday_start_time:
//                selectTimeDialog(mon_day_id.getText().toString(), mon_id.getText().toString(), "MondayStart");
//                break;
//
//            case R.id.moday_close_time:
//                selectTimeDialog(mon_day_id.getText().toString(), mon_id.getText().toString(), "MondayClose");
//                break;
//
//            case R.id.tuesday_start_time:
//                selectTimeDialog(tues_day_id.getText().toString(), tues_id.getText().toString(), "TuesdayStart");
//                break;
//
//            case R.id.tuesday_close_time:
//                selectTimeDialog(tues_day_id.getText().toString(), tues_id.getText().toString(), "TuesdayClose");
//                break;
//
//            case R.id.wednessday_start_time:
//                selectTimeDialog(wed_day_id.getText().toString(), wed_id.getText().toString(), "WednesdayStart");
//                break;
//
//            case R.id.wednessday_close_time:
//                selectTimeDialog(wed_day_id.getText().toString(), wed_id.getText().toString(), "WednesdayClose");
//                break;
//
//            case R.id.thusday_start_time:
//                selectTimeDialog(thurs_day_id.getText().toString(), thurs_id.getText().toString(), "ThursdayStart");
//                break;
//
//            case R.id.thusday_close_time:
//                selectTimeDialog(thurs_day_id.getText().toString(), thurs_id.getText().toString(), "ThursdayClose");
//                break;
//
//            case R.id.friday_start_time:
//                selectTimeDialog(fri_day_id.getText().toString(), fri_id.getText().toString(), "FridayStart");
//                break;
//
//            case R.id.friday_close_time:
//                selectTimeDialog(fri_day_id.getText().toString(), fri_id.getText().toString(), "FridayClose");
//                break;
//
//            case R.id.saturday_start_time:
//                selectTimeDialog(satr_day_id.getText().toString(), satr_id.getText().toString(), "SaturdayStart");
//                break;
//
//            case R.id.saturday_close_time:
//                selectTimeDialog(satr_day_id.getText().toString(), satr_id.getText().toString(), "SaturdayClose");
//                break;
//
//            case R.id.sunday_start_time:
//                selectTimeDialog(sun_day_id.getText().toString(), sun_id.getText().toString(), "SundayStart");
//                break;
//
//            case R.id.sunday_close_time:
//                selectTimeDialog(sun_day_id.getText().toString(), sun_id.getText().toString(), "SundayClose");
//                break;
//
//            case R.id.monday_tfhrs_chkbox:
//                if (((CompoundButton) view).isChecked()) {
//                    moday_start_time.setText("12:00 AM");
//                    moday_close_time.setText("12:00 PM");
//                    if (monday_close_chkbox.isChecked())
//                        monday_close_chkbox.setChecked(false);
//                } else {
//                    moday_start_time.setHint("09:30 AM");
//                    moday_close_time.setHint("06:30 PM");
//                }
//                break;
//
//            case R.id.monday_close_chkbox:
//                if (((CompoundButton) view).isChecked()) {
//                    moday_start_time.setText("00:00 AM");
//                    moday_close_time.setText("00:00 PM");
//                    if (monday_tfhrs_chkbox.isChecked())
//                        monday_tfhrs_chkbox.setChecked(false);
//                } else {
//                    moday_start_time.setHint("09:30 AM");
//                    moday_close_time.setHint("06:30 PM");
//                }
//                break;
//
//            case R.id.tuesday_tfhrs_chkbox:
//                if (((CompoundButton) view).isChecked()) {
//                    tuesday_start_time.setText("12:00 AM");
//                    tuesday_close_time.setText("12:00 PM");
//                    if (tuesday_close_chkbox.isChecked())
//                        tuesday_close_chkbox.setChecked(false);
//                } else {
//                    tuesday_start_time.setHint("09:30 AM");
//                    tuesday_close_time.setHint("06:30 PM");
//                }
//                break;
//            case R.id.tuesday_close_chkbox:
//                if (((CompoundButton) view).isChecked()) {
//                    tuesday_start_time.setText("00:00 AM");
//                    tuesday_close_time.setText("00:00 PM");
//                    if (tuesday_tfhrs_chkbox.isChecked())
//                        tuesday_tfhrs_chkbox.setChecked(false);
//                } else {
//                    tuesday_start_time.setHint("09:30 AM");
//                    tuesday_close_time.setHint("06:30 PM");
//                }
//                break;
//            case R.id.wednessday_tfhrs_chkbox:
//                if (((CompoundButton) view).isChecked()) {
//                    wednessday_start_time.setText("12:00 AM");
//                    wednessday_close_time.setText("12:00 PM");
//                    if (wednessday_close_chkbox.isChecked())
//                        wednessday_close_chkbox.setChecked(false);
//                } else {
//                    wednessday_start_time.setHint("09:30 AM");
//                    wednessday_close_time.setHint("06:30 PM");
//                }
//                break;
//            case R.id.wednessday_close_chkbox:
//                if (((CompoundButton) view).isChecked()) {
//                    wednessday_start_time.setText("00:00 AM");
//                    wednessday_close_time.setText("00:00 PM");
//                    if (wednessday_tfhrs_chkbox.isChecked())
//                        wednessday_tfhrs_chkbox.setChecked(false);
//                } else {
//                    wednessday_start_time.setHint("09:30 AM");
//                    wednessday_close_time.setHint("06:30 PM");
//                }
//                break;
//            case R.id.thusday_tfhrs_chkbox:
//                if (((CompoundButton) view).isChecked()) {
//                    thusday_start_time.setText("12:00 AM");
//                    thusday_close_time.setText("12:00 PM");
//                    if (thusday_close_chkbox.isChecked())
//                        thusday_close_chkbox.setChecked(false);
//                } else {
//                    thusday_start_time.setHint("09:30 AM");
//                    thusday_close_time.setHint("06:30 PM");
//                }
//                break;
//            case R.id.thusday_close_chkbox:
//                if (((CompoundButton) view).isChecked()) {
//                    thusday_start_time.setText("00:00 AM");
//                    thusday_close_time.setText("00:00 PM");
//                    if (thusday_tfhrs_chkbox.isChecked())
//                        thusday_tfhrs_chkbox.setChecked(false);
//                } else {
//                    thusday_start_time.setHint("09:30 AM");
//                    thusday_close_time.setHint("06:30 PM");
//                }
//                break;
//            case R.id.friday_tfhrs_chkbox:
//                if (((CompoundButton) view).isChecked()) {
//                    friday_start_time.setText("12:00 AM");
//                    friday_close_time.setText("12:00 PM");
//                    if (friday_close_chkbox.isChecked())
//                        friday_close_chkbox.setChecked(false);
//                } else {
//                    friday_start_time.setHint("09:30 AM");
//                    friday_close_time.setHint("06:30 PM");
//                }
//                break;
//            case R.id.friday_close_chkbox:
//                if (((CompoundButton) view).isChecked()) {
//                    friday_start_time.setText("00:00 AM");
//                    friday_close_time.setText("00:00 PM");
//                    if (friday_tfhrs_chkbox.isChecked())
//                        friday_tfhrs_chkbox.setChecked(false);
//                } else {
//                    friday_start_time.setHint("09:30 AM");
//                    friday_close_time.setHint("06:30 PM");
//                }
//                break;
//            case R.id.saturday_tfhrs_chkbox:
//                if (((CompoundButton) view).isChecked()) {
//                    saturday_start_time.setText("12:00 AM");
//                    saturday_close_time.setText("12:00 PM");
//                    if (saturday_close_chkbox.isChecked())
//                        saturday_close_chkbox.setChecked(false);
//                } else {
//                    saturday_start_time.setHint("09:30 AM");
//                    saturday_close_time.setHint("06:30 PM");
//                }
//                break;
//            case R.id.saturday_close_chkbox:
//                if (((CompoundButton) view).isChecked()) {
//                    saturday_start_time.setText("00:00 AM");
//                    saturday_close_time.setText("00:00 PM");
//                    if (saturday_tfhrs_chkbox.isChecked())
//                        saturday_tfhrs_chkbox.setChecked(false);
//                } else {
//                    saturday_start_time.setHint("09:30 AM");
//                    saturday_close_time.setHint("06:30 PM");
//                }
//                break;
//            case R.id.sunday_tfhrs_chkbox:
//                if (((CompoundButton) view).isChecked()) {
//                    sunday_start_time.setText("12:00 AM");
//                    sunday_close_time.setText("12:00 PM");
//                    if (sunday_close_chkbox.isChecked())
//                        sunday_close_chkbox.setChecked(false);
//                } else {
//                    sunday_start_time.setHint("09:30 AM");
//                    sunday_close_time.setHint("06:30 PM");
//                }
//                break;
//            case R.id.sunday_close_chkbox:
//                if (((CompoundButton) view).isChecked()) {
//                    sunday_start_time.setText("00:00 AM");
//                    sunday_close_time.setText("00:00 PM");
//                    if (sunday_tfhrs_chkbox.isChecked())
//                        sunday_tfhrs_chkbox.setChecked(false);
//                } else {
//                    sunday_start_time.setHint("09:30 AM");
//                    sunday_close_time.setHint("06:30 PM");
//                }
//                break;
//
//            case R.id.back:
//                onBackPressed();
//                break;
//
//            case R.id.save_working_hours:
//                main_view.setVisibility(View.GONE);
//                shimmer_view_container.setVisibility(View.VISIBLE);
//
//                ArrayList<HashMap<String, String>> actualList = new ArrayList<>();
//                innerHashMap = new HashMap<>();
//                innerHashMap.put("id", mon_id.getText().toString());
//                innerHashMap.put("dayId", mon_day_id.getText().toString());
//                innerHashMap.put("startTime", moday_start_time.getText().toString());
//                innerHashMap.put("endTime", moday_close_time.getText().toString());
//
//                if (monday_tfhrs_chkbox.isChecked())
//                    innerHashMap.put("allDayOpen", "true");
//                else
//                    innerHashMap.put("allDayOpen", "false");
//
//                if (monday_close_chkbox.isChecked())
//                    innerHashMap.put("isClosed", "true");
//                else
//                    innerHashMap.put("isClosed", "false");
//
//                actualList.add(innerHashMap);
//
//                innerHashMap = new HashMap<>();
//                innerHashMap.put("id", tues_id.getText().toString());
//                innerHashMap.put("dayId", tues_day_id.getText().toString());
//                innerHashMap.put("startTime", tuesday_start_time.getText().toString());
//                innerHashMap.put("endTime", tuesday_close_time.getText().toString());
//                if (tuesday_tfhrs_chkbox.isChecked())
//                    innerHashMap.put("allDayOpen", "true");
//                else
//                    innerHashMap.put("allDayOpen", "false");
//                if (tuesday_close_chkbox.isChecked())
//                    innerHashMap.put("isClosed", "true");
//                else
//                    innerHashMap.put("isClosed", "false");
//
//                actualList.add(innerHashMap);
//
//                innerHashMap = new HashMap<>();
//                innerHashMap.put("id", wed_id.getText().toString());
//                innerHashMap.put("dayId", wed_day_id.getText().toString());
//                innerHashMap.put("startTime", wednessday_start_time.getText().toString());
//                innerHashMap.put("endTime", wednessday_close_time.getText().toString());
//                if (wednessday_tfhrs_chkbox.isChecked())
//                    innerHashMap.put("allDayOpen", "true");
//                else
//                    innerHashMap.put("allDayOpen", "false");
//
//                if (wednessday_close_chkbox.isChecked())
//                    innerHashMap.put("isClosed", "true");
//                else
//                    innerHashMap.put("isClosed", "false");
//
//                actualList.add(innerHashMap);
//
//                innerHashMap = new HashMap<>();
//                innerHashMap.put("id", thurs_id.getText().toString());
//                innerHashMap.put("dayId", thurs_day_id.getText().toString());
//                innerHashMap.put("startTime", thusday_start_time.getText().toString());
//                innerHashMap.put("endTime", thusday_close_time.getText().toString());
//                if (thusday_tfhrs_chkbox.isChecked())
//                    innerHashMap.put("allDayOpen", "true");
//                else
//                    innerHashMap.put("allDayOpen", "false");
//                if (thusday_close_chkbox.isChecked())
//                    innerHashMap.put("isClosed", "true");
//                else
//                    innerHashMap.put("isClosed", "false");
//
//                actualList.add(innerHashMap);
//
//                innerHashMap = new HashMap<>();
//                innerHashMap.put("id", fri_id.getText().toString());
//                innerHashMap.put("dayId", fri_day_id.getText().toString());
//                innerHashMap.put("startTime", friday_start_time.getText().toString());
//                innerHashMap.put("endTime", friday_close_time.getText().toString());
//                if (friday_tfhrs_chkbox.isChecked())
//                    innerHashMap.put("allDayOpen", "true");
//                else
//                    innerHashMap.put("allDayOpen", "false");
//
//                if (friday_close_chkbox.isChecked())
//                    innerHashMap.put("isClosed", "true");
//                else
//                    innerHashMap.put("isClosed", "false");
//                actualList.add(innerHashMap);
//
//                innerHashMap = new HashMap<>();
//                innerHashMap.put("id", satr_id.getText().toString());
//                innerHashMap.put("dayId", satr_day_id.getText().toString());
//                innerHashMap.put("startTime", saturday_start_time.getText().toString());
//                innerHashMap.put("endTime", saturday_close_time.getText().toString());
//                if (saturday_tfhrs_chkbox.isChecked())
//                    innerHashMap.put("allDayOpen", "true");
//                else
//                    innerHashMap.put("allDayOpen", "false");
//
//                if (saturday_close_chkbox.isChecked())
//                    innerHashMap.put("isClosed", "true");
//                else
//                    innerHashMap.put("isClosed", "false");
//                actualList.add(innerHashMap);
//
//                innerHashMap = new HashMap<>();
//                innerHashMap.put("id", sun_id.getText().toString());
//                innerHashMap.put("dayId", sun_day_id.getText().toString());
//                innerHashMap.put("startTime", sunday_start_time.getText().toString());
//                innerHashMap.put("endTime", sunday_close_time.getText().toString());
//                innerHashMap.put("isClosed", "false");
//                innerHashMap.put("allDayOpen", "false");
//                actualList.add(innerHashMap);
//
//                innerHashMap = new HashMap<>();
//                innerHashMap.put("id", sun_id.getText().toString());
//                innerHashMap.put("dayId", sun_day_id.getText().toString());
//                innerHashMap.put("startTime", sunday_start_time.getText().toString());
//                innerHashMap.put("endTime", sunday_close_time.getText().toString());
//                if (sunday_tfhrs_chkbox.isChecked())
//                    innerHashMap.put("allDayOpen", "true");
//                else
//                    innerHashMap.put("allDayOpen", "false");
//
//                if (sunday_close_chkbox.isChecked())
//                    innerHashMap.put("isClosed", "true");
//                else
//                    innerHashMap.put("isClosed", "false");
//                actualList.add(innerHashMap);
//
//                Log.d("actual_ArrayList", "" + actualList.toString());
//
//                Gson gson = new GsonBuilder().create();
//                JsonArray myCustomArray = gson.toJsonTree(actualList).getAsJsonArray();
//
//                Log.d("actual_ArrayList", "after_json" + myCustomArray);
//                WorkingHoursParameter workingHoursParameter = new WorkingHoursParameter();
//                workingHoursParameter.setData(myCustomArray);
//
//                if (method.isInternetOn()) {
//                    saveWorkingHours(workingHoursParameter);
//                } else {
//                    method.DialogInternet();
//                }
//                break;
//        }
//    }
//
//    private void saveWorkingHours(WorkingHoursParameter workingHoursParameter) {
//        ApiService<SaveWorkingHoursResponse> service = new ApiService<>();
//        service.get(this, ApiClient.getApiInterface().saveOperatingHours(Config.token, workingHoursParameter), "SaveOperatingHours");
//        Log.e("DIALOG_Save_DATA", "==>" + workingHoursParameter);
//    }
//
//    @Override
//    public void onResponse(Response arg0, String key) {
//
//        switch (key) {
//            case "GetOperatingHours":
//                try {
//                    WorkingHoursResponse workingHoursResponse = (WorkingHoursResponse) arg0.body();
//                    Log.e("GetOperatingHours", workingHoursResponse.toString());
//                    int responseCode = Integer.parseInt(workingHoursResponse.getResponse().getResponseCode());
//                    if (responseCode == 109) {
//                        Log.d("amkaknan", "" + workingHoursResponse.getData().get(1).getId());
//                        getorkingHoursResponseModels = workingHoursResponse.getData();
//                       /* LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//                        operarting_hrs_list.setLayoutManager(linearLayoutManager);
//                        operatingHoursAdaptear  = new OperatingHoursAdaptear(this,workingHoursResponse.getData(),this);
//                        operarting_hrs_list.setAdapter(operatingHoursAdaptear);
//                        operatingHoursAdaptear.notifyDataSetChanged();*/
//                        for (int i = 0; i < workingHoursResponse.getData().size(); i++) {
//                            innerHashMap = new HashMap<String, String>();
//                            if (workingHoursResponse.getData().get(i).getDayId().equals("1.0")) {
//                                mon_day_id.setText(workingHoursResponse.getData().get(i).getDayId());
//                                mon_id.setText(workingHoursResponse.getData().get(i).getId());
//                                moday_start_time.setText(workingHoursResponse.getData().get(i).getStartTime());
//                                moday_close_time.setText(workingHoursResponse.getData().get(i).getEndTime());
//                                if (workingHoursResponse.getData().get(i).getIsClosed().equals("true"))
//                                    monday_close_chkbox.setChecked(true);
//                                if (workingHoursResponse.getData().get(i).getAllDayOpen().equals("true"))
//                                    monday_tfhrs_chkbox.setChecked(true);
//                            }
//
//                            if (workingHoursResponse.getData().get(i).getDayId().equals("2.0")) {
//                                tues_day_id.setText(workingHoursResponse.getData().get(i).getDayId());
//                                tues_id.setText(workingHoursResponse.getData().get(i).getId());
//                                tuesday_start_time.setText(workingHoursResponse.getData().get(i).getStartTime());
//                                tuesday_close_time.setText(workingHoursResponse.getData().get(i).getEndTime());
//                                if (workingHoursResponse.getData().get(i).getIsClosed().equals("true"))
//                                    tuesday_close_chkbox.setChecked(true);
//                                if (workingHoursResponse.getData().get(i).getAllDayOpen().equals("true"))
//                                    tuesday_tfhrs_chkbox.setChecked(true);
//                            }
//
//                            if (workingHoursResponse.getData().get(i).getDayId().equals("3.0")) {
//                                wed_day_id.setText(workingHoursResponse.getData().get(i).getDayId());
//                                wed_id.setText(workingHoursResponse.getData().get(i).getId());
//                                wednessday_start_time.setText(workingHoursResponse.getData().get(i).getStartTime());
//                                wednessday_close_time.setText(workingHoursResponse.getData().get(i).getEndTime());
//                                if (workingHoursResponse.getData().get(i).getIsClosed().equals("true"))
//                                    wednessday_close_chkbox.setChecked(true);
//                                if (workingHoursResponse.getData().get(i).getAllDayOpen().equals("true"))
//                                    wednessday_tfhrs_chkbox.setChecked(true);
//                            }
//
//                            if (workingHoursResponse.getData().get(i).getDayId().equals("4.0")) {
//                                thurs_day_id.setText(workingHoursResponse.getData().get(i).getDayId());
//                                thurs_id.setText(workingHoursResponse.getData().get(i).getId());
//                                thusday_start_time.setText(workingHoursResponse.getData().get(i).getStartTime());
//                                thusday_close_time.setText(workingHoursResponse.getData().get(i).getEndTime());
//                                if (workingHoursResponse.getData().get(i).getIsClosed().equals("true"))
//                                    thusday_close_chkbox.setChecked(true);
//                                if (workingHoursResponse.getData().get(i).getAllDayOpen().equals("true"))
//                                    thusday_tfhrs_chkbox.setChecked(true);
//                            }
//
//                            if (workingHoursResponse.getData().get(i).getDayId().equals("5.0")) {
//                                fri_day_id.setText(workingHoursResponse.getData().get(i).getDayId());
//                                fri_id.setText(workingHoursResponse.getData().get(i).getId());
//                                friday_start_time.setText(workingHoursResponse.getData().get(i).getStartTime());
//                                friday_close_time.setText(workingHoursResponse.getData().get(i).getEndTime());
//                                if (workingHoursResponse.getData().get(i).getIsClosed().equals("true"))
//                                    friday_close_chkbox.setChecked(true);
//                                if (workingHoursResponse.getData().get(i).getAllDayOpen().equals("true"))
//                                    friday_tfhrs_chkbox.setChecked(true);
//                            }
//
//                            if (workingHoursResponse.getData().get(i).getDayId().equals("6.0")) {
//                                satr_day_id.setText(workingHoursResponse.getData().get(i).getDayId());
//                                satr_id.setText(workingHoursResponse.getData().get(i).getId());
//                                saturday_start_time.setText(workingHoursResponse.getData().get(i).getStartTime());
//                                saturday_close_time.setText(workingHoursResponse.getData().get(i).getEndTime());
//                                if (workingHoursResponse.getData().get(i).getIsClosed().equals("true"))
//                                    saturday_close_chkbox.setChecked(true);
//                                if (workingHoursResponse.getData().get(i).getAllDayOpen().equals("true"))
//                                    saturday_tfhrs_chkbox.setChecked(true);
//                            }
//
//                            if (workingHoursResponse.getData().get(i).getDayId().equals("7.0")) {
//                                sun_day_id.setText(workingHoursResponse.getData().get(i).getDayId());
//                                sun_id.setText(workingHoursResponse.getData().get(i).getId());
//                                sunday_start_time.setText(workingHoursResponse.getData().get(i).getStartTime());
//                                sunday_close_time.setText(workingHoursResponse.getData().get(i).getEndTime());
//                                if (workingHoursResponse.getData().get(i).getIsClosed().equals("true"))
//                                    sunday_close_chkbox.setChecked(true);
//                                if (workingHoursResponse.getData().get(i).getAllDayOpen().equals("true"))
//                                    sunday_tfhrs_chkbox.setChecked(true);
//                            }
//
//                            innerHashMap.put("id", workingHoursResponse.getData().get(i).getId());
//                            innerHashMap.put("dayId", workingHoursResponse.getData().get(i).getDayId());
//                            innerHashMap.put("startTime", workingHoursResponse.getData().get(i).getStartTime());
//                            innerHashMap.put("endTime", workingHoursResponse.getData().get(i).getEndTime());
//                            innerHashMap.put("isClosed", workingHoursResponse.getData().get(i).getIsClosed());
//                            innerHashMap.put("allDayOpen", workingHoursResponse.getData().get(i).getAllDayOpen());
//                            outerHashmap.put(workingHoursResponse.getData().get(i).getDayId(), innerHashMap);
//                        }
//                        shimmer_view_container.stopShimmerAnimation();
//                        shimmer_view_container.setVisibility(View.GONE);
//                        main_view.setVisibility(View.VISIBLE);
//                        Log.e("outerHashMap", "" + outerHashmap);
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                break;
//
//            case "SaveOperatingHours":
//                try {
//                    SaveWorkingHoursResponse saveWorkingHoursResponse = (SaveWorkingHoursResponse) arg0.body();
//                    Log.e("SaveOperatingHours", saveWorkingHoursResponse.toString());
//                    int responseCode = Integer.parseInt(saveWorkingHoursResponse.getResponse().getResponseCode());
//                    if (responseCode == 109) {
//                        Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show();
//                        if (method.isInternetOn()) {
//                            getWorkingHours();
//                        } else {
//                            method.DialogInternet();
//                        }
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                break;
//        }
//
//
//    }
//
//    @Override
//    public void onError(Throwable t, String key) {
//
//    }
//
//
//    public void selectTimeDialog(final String DayId, final String id, final String type) {
//        Log.d("DayId", "" + DayId);
//
//        CustomTimePickerDialog timePickerDialog = new CustomTimePickerDialog(OperatingHoursActivity.this, new TimePickerDialog.OnTimeSetListener() {
//            @SuppressLint("NewApi")
//            @Override
//            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                Log.e("Log", "selected time----" + hourOfDay + ":" + minute);
//                String strTime = hourOfDay + ":" + minute + ":00";
//
//                updateTime(hourOfDay, minute, DayId, id, type);
//            }
//        }, intHour, intMinute, false);
//        timePickerDialog.show();
//    }
//
//    private void updateTime(int hours, int mins, String DayId, String id, String type) {
//        String timeSet = "";
//        if (hours > 12) {
//            hours -= 12;
//            timeSet = "PM";
//        } else if (hours == 0) {
//            hours += 12;
//            timeSet = "AM";
//        } else if (hours == 12)
//            timeSet = "PM";
//        else
//            timeSet = "AM";
//
//        if (mins < 10)
//            strMin = "0" + mins;
//        else
//            strMin = String.valueOf(mins);
//        // Append in a StringBuilder
//        String aTime = new StringBuilder().append(pad(hours)).append(':')
//                .append(pad(Integer.parseInt(strMin))).append(" ").append(timeSet).toString();
//
//        Log.e("aTime checking ==>", "" + aTime);
//        Log.e("zhsbdbsvdbsv", "" + DayId);
//        Log.e("kakkakak", "" + type);
//
//        if ((type.equals("MondayStart")) && (DayId.equals("1.0"))) {
//            moday_start_time.setText(aTime);
//        } else if ((type.equals("MondayClose")) && (DayId.equals("1.0"))) {
//            moday_close_time.setText(aTime);
//        } else if ((type.equals("TuesdayStart")) && (DayId.equals("2.0"))) {
//            tuesday_start_time.setText(aTime);
//        } else if ((type.equals("TuesdayClose")) && (DayId.equals("2.0"))) {
//            tuesday_close_time.setText(aTime);
//        } else if ((type.equals("WednesdayStart")) && (DayId.equals("3.0"))) {
//            wednessday_start_time.setText(aTime);
//        } else if ((type.equals("WednesdayClose")) && (DayId.equals("3.0"))) {
//            wednessday_close_time.setText(aTime);
//        } else if ((type.equals("ThursdayStart")) && (DayId.equals("4.0"))) {
//            thusday_start_time.setText(aTime);
//        } else if ((type.equals("ThursdayClose")) && (DayId.equals("4.0"))) {
//            thusday_close_time.setText(aTime);
//        } else if ((type.equals("FridayStart")) && (DayId.equals("5.0"))) {
//            friday_start_time.setText(aTime);
//        } else if ((type.equals("FridayClose")) && (DayId.equals("5.0"))) {
//            friday_close_time.setText(aTime);
//        } else if ((type.equals("SaturdayStart")) && (DayId.equals("6.0"))) {
//            saturday_start_time.setText(aTime);
//        } else if ((type.equals("SaturdayClose")) && (DayId.equals("6.0"))) {
//            saturday_close_time.setText(aTime);
//        } else if ((type.equals("SundayStart")) && (DayId.equals("7.0"))) {
//            sunday_start_time.setText(aTime);
//        } else if ((type.equals("SundayClose")) && (DayId.equals("7.0"))) {
//            sunday_close_time.setText(aTime);
//        }
//
//
//    }
//
//    private String pad(int c) {
//        if (c >= 10)
//            return String.valueOf(c);
//        else
//            return "0" + String.valueOf(c);
//    }
//
//
//}