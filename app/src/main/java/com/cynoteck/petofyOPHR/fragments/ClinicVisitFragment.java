package com.cynoteck.petofyOPHR.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.adapters.AllClinicVisitAdopter;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.ClinicVisitsParameter.ClinicVisitParameterModel;
import com.cynoteck.petofyOPHR.params.ClinicVisitsParameter.ClinicVisitRequest;
import com.cynoteck.petofyOPHR.params.sendNotificationParams.NotificationParameter;
import com.cynoteck.petofyOPHR.params.sendNotificationParams.SendNotificationRequest;
import com.cynoteck.petofyOPHR.response.ClinicVistResponse.ClinicVisitResponseData;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.GetReportsTypeResponse;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.getPetClinicVisitsListsResponse.PetClinicVisitList;
import com.cynoteck.petofyOPHR.utils.ClinicOnlineVisitNotification;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import retrofit2.Response;

public class ClinicVisitFragment extends Fragment implements ApiResponse, View.OnClickListener, ClinicOnlineVisitNotification {

    View view;
    Methods methods;
    TextView lastVisitDt, nextVisitDt;
    AppCompatSpinner nature_of_visit_spinner;
    DatePickerDialog picker;
    ArrayList<String> natureOfVisitList;
    HashMap<String, String> natureOfVisitHashMap = new HashMap<>();
    String strNatureOfVist = "2.0", natureOfVisit = "", lastDate = "", nextDate = "";
    LinearLayout search_visits;
    RecyclerView all_clinic_visits_RV;
    AllClinicVisitAdopter allClinicVisitAdopter;
    List<PetClinicVisitList> clinicVisitResponseDataList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_clinic_visit, container, false);
        init();
        return view;
    }

    public void init() {
        methods = new Methods(getActivity());
        lastVisitDt = view.findViewById(R.id.lastVisitDt);
        nextVisitDt = view.findViewById(R.id.nextVisitDt);
        nature_of_visit_spinner = view.findViewById(R.id.nature_of_visit_spinner);
        search_visits = view.findViewById(R.id.search_visits);
        all_clinic_visits_RV = view.findViewById(R.id.all_clinic_visits_RV);

        lastVisitDt.setOnClickListener(this);
        nextVisitDt.setOnClickListener(this);
        search_visits.setOnClickListener(this);

        if (methods.isInternetOn()) {
//            clinicVisitdata();
            getVisitTypes();
        } else {
            methods.DialogInternet();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lastVisitDt:
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog

//                picker.setMinDate(System.currentTimeMillis() - 1000);
                picker = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String displayValue = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
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
                                String displayValue = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                nextVisitDt.setText(Config.changeDateFormat(displayValue));
                            }
                        }, yearForNext, monthForNext, dayForNext);
                picker.getDatePicker().setMinDate(cldrForNext.getTimeInMillis());
                picker.show();
                break;
            case R.id.search_visits:
                lastDate = lastVisitDt.getText().toString().trim();
                nextDate = nextVisitDt.getText().toString().trim();
                if (natureOfVisit.equals("Select Visit")) {
                    Toast.makeText(getActivity(), "Select Visit Type", Toast.LENGTH_SHORT).show();
                } else if (lastDate.isEmpty()) {
                    Toast.makeText(getActivity(), "Select From Date", Toast.LENGTH_SHORT).show();
                } else if (nextDate.isEmpty()) {
                    Toast.makeText(getActivity(), "Select To Date", Toast.LENGTH_SHORT).show();
                } else {

                    if (methods.isInternetOn()) {
                        clinicVisitdata();
                    } else {
                        methods.DialogInternet();
                    }

                }
                break;
        }
    }

    private void getVisitTypes() {
        ApiService<GetReportsTypeResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getReportsType(Config.token), "GetReportsType");

    }

    public void clinicVisitdata() {
        methods.showCustomProgressBarDialog(getActivity());
        ClinicVisitParameterModel clinicVisitParameterModel = new ClinicVisitParameterModel();
        clinicVisitParameterModel.setFromDate(lastDate);
        clinicVisitParameterModel.setToDate(nextDate);
        clinicVisitParameterModel.setNatureOfVisiteId(strNatureOfVist.substring(0, strNatureOfVist.length() - 2));
        ClinicVisitRequest clinicVisitRequest = new ClinicVisitRequest();
        clinicVisitRequest.setData(clinicVisitParameterModel);
        ApiService<ClinicVisitResponseData> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getUpCommingClinicVisits(Config.token, clinicVisitRequest), "GetUpCommingClinicVisits");
        Log.e("UpcomingClinicVisits==>", "" + methods.getRequestJson(clinicVisitRequest));
    }

    @Override
    public void onResponse(Response arg0, String key) {
        switch (key) {
            case "GetUpCommingClinicVisits":
                try {
                    ClinicVisitResponseData clinicVisitResponseData = (ClinicVisitResponseData) arg0.body();
                    Log.d("CommingClinicVisits", clinicVisitResponseData.toString());
                    Log.d("CommingClinicVisits", "" + clinicVisitResponseData.getData().getPetClinicVisitList().size());
                    int responseCode = Integer.parseInt(clinicVisitResponseData.getResponse().getResponseCode());
                    clinicVisitResponseDataList = new ArrayList<>();
                    if (responseCode == 109) {
                        methods.customProgressDismiss();
//                        Log.d("aaanana", "" + clinicVisitResponseData.getData().getPetClinicVisitList().get(0).getVisitDate());
                        all_clinic_visits_RV.setVisibility(View.VISIBLE);
                        clinicVisitResponseDataList = clinicVisitResponseData.getData().getPetClinicVisitList();
                        if (clinicVisitResponseDataList.isEmpty()) {
                            Toast.makeText(getContext(), "No Data Found !", Toast.LENGTH_SHORT).show();
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                            all_clinic_visits_RV.setLayoutManager(linearLayoutManager);
                            all_clinic_visits_RV.setAdapter(null);
                        } else {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                            all_clinic_visits_RV.setLayoutManager(linearLayoutManager);
                            allClinicVisitAdopter = new AllClinicVisitAdopter(getActivity(), clinicVisitResponseData.getData().getPetClinicVisitList(), this);
                            all_clinic_visits_RV.setAdapter(allClinicVisitAdopter);
                        }
                    } else if (responseCode == 614) {
                        methods.customProgressDismiss();
                        Toast.makeText(getActivity(), clinicVisitResponseData.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        methods.customProgressDismiss();
                        Toast.makeText(getActivity(), "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    methods.customProgressDismiss();
//                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//                    all_clinic_visits_RV.setLayoutManager(linearLayoutManager);
//                    all_clinic_visits_RV.setAdapter(null);
                    Toast.makeText(getContext(), "No Data Found  exception!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                break;

            case "GetReportsType":
                try {
                    Log.d("GetPetServiceTypes", arg0.body().toString());
                    GetReportsTypeResponse petServiceResponse = (GetReportsTypeResponse) arg0.body();
                    int responseCode = Integer.parseInt(petServiceResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        natureOfVisitList = new ArrayList<>();
                        natureOfVisitList.add("Select Visit");
                        for (int i = 0; i < petServiceResponse.getData().size(); i++) {
                            natureOfVisitList.add(petServiceResponse.getData().get(i).getNature());
                            natureOfVisitHashMap.put(petServiceResponse.getData().get(i).getNature(), petServiceResponse.getData().get(i).getId());
                        }
                        setSpinnerNatureofVisit();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "SendNotification":
                try {
                    Log.d("SendNotification", arg0.body().toString());
                    JsonObject notificationresponse = (JsonObject) arg0.body();

                    JsonObject response = notificationresponse.getAsJsonObject("response");
                    Log.d("hhshshhs", "" + response);

                    int responseCode = Integer.parseInt(String.valueOf(response.get("responseCode")));

                    if (responseCode == 109) {
                        methods.customProgressDismiss();
                        Toast.makeText(getActivity(), "Send Successfully..", Toast.LENGTH_SHORT).show();
                    } else {
                        methods.customProgressDismiss();
                        Toast.makeText(getActivity(), "Failed!!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

        }
    }

    @Override
    public void onError(Throwable t, String key) {

    }

    private void setSpinnerNatureofVisit() {
        ArrayAdapter aa = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, natureOfVisitList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        nature_of_visit_spinner.setAdapter(aa);
        if (!strNatureOfVist.equals("")) {
            int spinnerPosition = aa.getPosition(strNatureOfVist);
            nature_of_visit_spinner.setSelection(spinnerPosition);
        }
        nature_of_visit_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                natureOfVisit = item;
                strNatureOfVist = natureOfVisitHashMap.get(natureOfVisit);
                Log.d("Spinner", "" + item);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void ClickNotification(int position) {
        if (methods.isInternetOn()) {
            methods.showCustomProgressBarDialog(getActivity());
            NotificationParameter notificationParameter = new NotificationParameter();
            notificationParameter.setId(clinicVisitResponseDataList.get(position).getId().substring(0, clinicVisitResponseDataList.get(position).getId().length() - 2));
            SendNotificationRequest sendNotificationRequest = new SendNotificationRequest();
            sendNotificationRequest.setData(notificationParameter);
            ApiService<JsonObject> service = new ApiService<>();
            service.get(this, ApiClient.getApiInterface().sendNotification(Config.token, sendNotificationRequest), "SendNotification");
            Log.e("SendNotification==>", "" + sendNotificationRequest);
        } else
            methods.DialogInternet();

    }
}