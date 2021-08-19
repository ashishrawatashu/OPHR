package com.cynoteck.petofyOPHR.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.adapters.ClinicVisitOnlineAdapter;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.onlineClinicVisitsParams.OnlineClinicVisitisParameter;
import com.cynoteck.petofyOPHR.params.onlineClinicVisitsParams.OnlineClinicVisitsRequest;
import com.cynoteck.petofyOPHR.params.sendNotificationParams.NotificationParameter;
import com.cynoteck.petofyOPHR.params.sendNotificationParams.SendNotificationRequest;
import com.cynoteck.petofyOPHR.response.onlineClinicVisitResponse.OnlineClinicResponse;
import com.cynoteck.petofyOPHR.response.onlineClinicVisitResponse.VetAppointmentList;
import com.cynoteck.petofyOPHR.utils.AllVisitsDateWieseOnClick;
import com.cynoteck.petofyOPHR.utils.ClinicOnlineVisitNotification;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Response;


public class OnlineVisitFragment extends Fragment implements View.OnClickListener, ApiResponse, AllVisitsDateWieseOnClick, ClinicOnlineVisitNotification {

    TextView lastVisitDt, nextVisitDt;
    ImageView search_upcoming_IV;
    DatePickerDialog picker;
    String lastDate, nextDate;
    ProgressBar progressBar;
    RecyclerView upcomingVisitsOnline_RV;
    Methods methods;
    View view;
    OnlineClinicResponse onlineClinicResponse;
    ClinicVisitOnlineAdapter clinicVisitOnlineAdapter;
    List<VetAppointmentList> clinicVisitResponseData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view= inflater.inflate(R.layout.fragment_online_visit, container, false);
         init();
        return view;
    }

    public void init()
    {
        methods=new Methods(getActivity());

        progressBar =view.findViewById(R.id.progressBar);
        lastVisitDt =view.findViewById(R.id.lastVisitDt);
        nextVisitDt =view.findViewById(R.id.nextVisitDt);
        search_upcoming_IV =view.findViewById(R.id.search_upcoming_IV);
        upcomingVisitsOnline_RV =view.findViewById(R.id.upcomingVisitsOnline_RV);

        lastVisitDt.setOnClickListener(this);
        nextVisitDt.setOnClickListener(this);
        search_upcoming_IV.setOnClickListener(this);

//        if(methods.isInternetOn())
//            listOfOnlinClinicData();
//        else
//            methods.DialogInternet();
    }

    public void listOfOnlinClinicData()
    {
        OnlineClinicVisitisParameter onlineClinicVisitisParameter = new OnlineClinicVisitisParameter();
        onlineClinicVisitisParameter.setFromDate(lastDate);
        onlineClinicVisitisParameter.setToDate(nextDate);
        OnlineClinicVisitsRequest onlineClinicVisitsRequest = new OnlineClinicVisitsRequest();
        onlineClinicVisitsRequest.setData(onlineClinicVisitisParameter);

        ApiService<OnlineClinicResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getUpCommingClinicVisits(Config.token, onlineClinicVisitsRequest), "GetUpCommingClinicVisits");
        Log.e("UpcomingClinicVisits==>", "" + methods.getRequestJson(onlineClinicVisitsRequest));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lastVisitDt:
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String displayValue = dayOfMonth + "/" + (monthOfYear+1) + "/" + year;
                                lastVisitDt.setText(Config.changeDateFormat(displayValue));
                            }
                        }, year, month, day);
                picker.getDatePicker().setMinDate(cldr.getTimeInMillis());
                picker.show();
                break;
            case R.id.nextVisitDt:
                final Calendar cldrForNext = Calendar.getInstance();
                int dayForNext = cldrForNext.get(Calendar.DAY_OF_MONTH);
                int monthForNext = cldrForNext.get(Calendar.MONTH);
                int yearForNext = cldrForNext.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {


                                String displayValue = dayOfMonth + "/" + (monthOfYear+1) + "/" + year;
                                nextVisitDt.setText(Config.changeDateFormat(displayValue));
                            }
                        }, yearForNext, monthForNext, dayForNext);
                picker.getDatePicker().setMinDate(cldrForNext.getTimeInMillis());
                picker.show();
                break;

            case R.id.search_upcoming_IV:
                lastDate = lastVisitDt.getText().toString().trim();
                nextDate = nextVisitDt.getText().toString().trim();

                if (lastDate.isEmpty()){
                    Toast.makeText(getActivity(), "Select From Date", Toast.LENGTH_SHORT).show();
                }else if (nextDate.isEmpty()){
                    Toast.makeText(getActivity(), "Select To Date", Toast.LENGTH_SHORT).show();
                }else {
                    upcomingVisitsOnline_RV.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    if(methods.isInternetOn())
                        listOfOnlinClinicData();
                    else
                        methods.DialogInternet();
                }
                break;

        }
    }

    @Override
    public void onResponse(Response arg0, String key) {
        switch (key)
        {
            case"GetUpCommingClinicVisits":
                try {
                    onlineClinicResponse = (OnlineClinicResponse) arg0.body();
                    Log.d("GetUpCommingClinicVis", onlineClinicResponse.toString());
                    int responseCode = Integer.parseInt(onlineClinicResponse.getResponse().getResponseCode());
                    clinicVisitResponseData=new ArrayList<>();
                    if (responseCode == 109) {
                        Log.d("ksksksk",""+onlineClinicResponse.getData().getVetAppointmentList().get(0).getPetName());
                        progressBar.setVisibility(View.GONE);
                        upcomingVisitsOnline_RV.setVisibility(View.VISIBLE);
                        clinicVisitResponseData=onlineClinicResponse.getData().getVetAppointmentList();
                        if (clinicVisitResponseData.isEmpty()){
                            Toast.makeText(getContext(), "No Data Found !", Toast.LENGTH_SHORT).show();
                        }else {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                            upcomingVisitsOnline_RV.setLayoutManager(linearLayoutManager);
                            clinicVisitOnlineAdapter = new ClinicVisitOnlineAdapter(getActivity(), onlineClinicResponse.getData().getVetAppointmentList(), onlineClinicResponse.getData().getVetAppointmentList(), this);
                            upcomingVisitsOnline_RV.setAdapter(clinicVisitOnlineAdapter);
                        }
                    } else if (responseCode == 614) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), onlineClinicResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "No Data Found !", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                break;
            case "SendNotification":
                try {
                    Log.d("SendNotification",arg0.body().toString());
                    JsonObject notificationresponse = (JsonObject) arg0.body();

                    JsonObject response = notificationresponse.getAsJsonObject("response");
                    Log.d("hhshshhs",""+response);

                    int responseCode = Integer.parseInt(String.valueOf(response.get("responseCode")));

                    if (responseCode== 109){
                        methods.customProgressDismiss();
                        Toast.makeText(getActivity(), "Send Successfully..", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        methods.customProgressDismiss();
                        Toast.makeText(getActivity(), "Failed!!", Toast.LENGTH_SHORT).show();
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

    }

    @Override
    public void onPrecriptionButton(int position) {

    }

    @Override
    public void onImmunizationButton(int position) {

    }

    @Override
    public void ClickNotification(int position) {
        if(methods.isInternetOn())
        {
            methods.showCustomProgressBarDialog(getActivity());
            NotificationParameter notificationParameter = new NotificationParameter();
            notificationParameter.setId(clinicVisitResponseData.get(position).getId().substring(0,clinicVisitResponseData.get(position).getId().length()-2));
            SendNotificationRequest sendNotificationRequest = new SendNotificationRequest();
            sendNotificationRequest.setData(notificationParameter);

            ApiService<JsonObject> service = new ApiService<>();
            service.get(this, ApiClient.getApiInterface().sendNotification(Config.token, sendNotificationRequest), "SendNotification");
            Log.e("SendNotification==>", "" + sendNotificationRequest);
        }
        else
            methods.DialogInternet();
    }
}