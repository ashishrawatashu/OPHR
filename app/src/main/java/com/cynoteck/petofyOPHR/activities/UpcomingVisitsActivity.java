package com.cynoteck.petofyOPHR.activities;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.adapters.MyAdapter;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.tabs.TabLayout;

import retrofit2.Response;

public class UpcomingVisitsActivity extends AppCompatActivity implements ApiResponse, View.OnClickListener {


//    checkIntetnetConnectivity checkIntetnetConnectivity=new checkIntetnetConnectivity();


    Methods methods;
    TabLayout tabLayout;
    ViewPager viewPager;
    MaterialCardView back_arrow_CV;
    private int[] tabIcons = {
            R.drawable.update_prof_logo,
            R.drawable.calendar,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_visits);
        methods = new Methods(this);
        initization();

    }
//    public  void start(){
//        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
//        registerReceiver(checkIntetnetConnectivity,filter);
//
////        finish();
//    }


    private void initization() {
       /* progressBar = findViewById(R.id.progressBar);
        upcomingVisits_RV = findViewById(R.id.upcomingVisits_RV);
        lastVisitDt = findViewById(R.id.lastVisitDt);
        nextVisitDt = findViewById(R.id.nextVisitDt);
        back_arrow_CV = findViewById(R.id.back_arrow_CV);
        search_upcoming_IV = findViewById(R.id.search_upcoming_IV);
        
        back_arrow_CV.setOnClickListener(this);
        lastVisitDt.setOnClickListener(this);
        nextVisitDt.setOnClickListener(this);
        search_upcoming_IV.setOnClickListener(this);*/

        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        back_arrow_CV = findViewById(R.id.back_arrow_CV);

        back_arrow_CV.setOnClickListener(this);

        tabLayout.addTab(tabLayout.newTab().setText("Clinic Visit"));
        tabLayout.addTab(tabLayout.newTab().setText("Online Appointment"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);

        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));
        tabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));

//        setupTabIcons();
        
        final MyAdapter adapter = new MyAdapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
}

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_arrow_CV:
                onBackPressed();
                break;

                /*case R.id.lastVisitDt:
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String displayValue = dayOfMonth + "/" + (monthOfYear+1) + "/" + year;
                                lastVisitDt.setText(Config.changeDateFormat(displayValue));
                            }
                        }, year, month, day);
                picker.show();
                break;
            case R.id.nextVisitDt:
                final Calendar cldrForNext = Calendar.getInstance();
                int dayForNext = cldrForNext.get(Calendar.DAY_OF_MONTH);
                int monthForNext = cldrForNext.get(Calendar.MONTH);
                int yearForNext = cldrForNext.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {


                                String displayValue = dayOfMonth + "/" + (monthOfYear+1) + "/" + year;
                                nextVisitDt.setText(Config.changeDateFormat(displayValue));
                            }
                        }, yearForNext, monthForNext, dayForNext);
                picker.show();
                break;

            case R.id.search_upcoming_IV:
                lastDate = lastVisitDt.getText().toString().trim();
                nextDate = nextVisitDt.getText().toString().trim();

                if (lastDate.isEmpty()){
                    Toast.makeText(this, "Select From Date", Toast.LENGTH_SHORT).show();
                }else if (nextDate.isEmpty()){
                    Toast.makeText(this, "Select To Date", Toast.LENGTH_SHORT).show();
                }else {
                    upcomingVisits_RV.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    UpcommingVisitsParams upcommingVisitsParams = new UpcommingVisitsParams();
                    upcommingVisitsParams.setFromDate(lastDate);
                    upcommingVisitsParams.setTodate(nextDate);
                    UpcommingVisitsRequest upcommingVisitsRequest = new UpcommingVisitsRequest();
                    upcommingVisitsRequest.setData(upcommingVisitsParams);

                    ApiService<UpcommingVisitsResponse> service = new ApiService<>();
                    service.get(this, ApiClient.getApiInterface().getUpcomingVisits(Config.token, upcommingVisitsRequest), "UpcomingVisits");
                    Log.d("UpcomingVisits==>", "" + upcommingVisitsRequest);
                    
                }
                break;*/

        }
    }

    @Override
    public void onResponse(Response arg0, String key) {
       /* switch (key){
            case "UpcomingVisits":
                try {
                    upcommingVisitsResponse = (UpcommingVisitsResponse) arg0.body();
                    Log.d("UpcomingVisits", upcommingVisitsResponse.toString());
                    int responseCode = Integer.parseInt(upcommingVisitsResponse.getResponse().getResponseCode());

                    if (responseCode == 109) {
                        progressBar.setVisibility(View.GONE);
                        upcomingVisits_RV.setVisibility(View.VISIBLE);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                        upcomingVisits_RV.setLayoutManager(linearLayoutManager);
                        upcomingVisitsAdapter = new UpcomingVisitsAdapter(this, upcommingVisitsResponse.getData());
                        upcomingVisits_RV.setAdapter(upcomingVisitsAdapter);
                    } else if (responseCode == 614) {
                        Toast.makeText(this, upcommingVisitsResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;


        }*/

    }

    @Override
    public void onError(Throwable t, String key) {

    }
}
