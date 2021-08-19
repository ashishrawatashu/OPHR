package com.cynoteck.petofyOPHR.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cynoteck.petofyOPHR.CalenderLib.DayDateMonthYearModel;
import com.cynoteck.petofyOPHR.CalenderLib.HorizontalCalendarListener;
import com.cynoteck.petofyOPHR.CalenderLib.HorizontalCalendarView;
import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.activities.AddClinicActivity;
import com.cynoteck.petofyOPHR.activities.AddUpdateAppointmentActivity;
import com.cynoteck.petofyOPHR.adapters.RequestPendingAdapter;
import com.cynoteck.petofyOPHR.adapters.UpcomingAppointmentsAdapter;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.appointmentParams.AppointmentStatusParams;
import com.cynoteck.petofyOPHR.params.appointmentParams.AppointmentsStatusRequest;
import com.cynoteck.petofyOPHR.params.appointmentParams.GetAppointmentFromDateParams;
import com.cynoteck.petofyOPHR.params.appointmentParams.GetAppointmentFromDateRequest;
import com.cynoteck.petofyOPHR.response.appointmentResponse.requestPendingResponse.RequestPendingData;
import com.cynoteck.petofyOPHR.response.appointmentResponse.requestPendingResponse.RequestPendingResponse;
import com.cynoteck.petofyOPHR.response.appointmentResponse.upComingAppointmentResponse.UpcomingAppointmentData;
import com.cynoteck.petofyOPHR.response.appointmentResponse.upComingAppointmentResponse.UpcomingAppointmentResponse;
import com.cynoteck.petofyOPHR.response.loginRegisterResponse.UserPermissionMasterList;
import com.cynoteck.petofyOPHR.response.staffPermissionListResponse.CheckStaffPermissionResponse;
import com.cynoteck.petofyOPHR.utils.AppointmentsClickListener;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class VetAppointmentsFragment extends Fragment implements HorizontalCalendarListener, View.OnClickListener, ApiResponse, AppointmentsClickListener {

    TextView cal_date_TV, month_TV, appointment_charge_TV, total_appointment_headline_TV, pending_visit_empty_TV, upcoming_visit_empty_TV;
    ShimmerFrameLayout pending_visit_SFL, upcoming_visit_SFL;
    HorizontalCalendarView horizontalCalendarView;
    View view;
    RecyclerView upcoming_visit_RV, pending_request_RV;
    RelativeLayout edit_appointment_charge_RL, add_appointment_RL;
    Methods methods;
    UpcomingAppointmentsAdapter upcomingAppointmentsAdapter;
    RequestPendingAdapter requestPendingAdapter;
    String permissionId = "";
    SharedPreferences sharedPreferences;
    int joinPostion;
    ArrayList<UpcomingAppointmentData> appointmentListsJoin;
    String mettingId = "", approve_reject = "";

    public VetAppointmentsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_vet_appointments, container, false);
        initialization();
        pendingAppointment();
        return view;

    }

    private void pendingAppointment() {
        pending_visit_empty_TV.setVisibility(View.GONE);
        pending_request_RV.setVisibility(View.GONE);
        pending_visit_SFL.setVisibility(View.VISIBLE);
        pending_visit_SFL.startShimmerAnimation();
        try {
            Date c = Calendar.getInstance().getTime();
            System.out.println("Current time => " + c);
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String formattedDate = df.format(c);
            Log.e("current Date",formattedDate);
            Log.e("date", methods.addMonths(formattedDate, 1));
            getRequestPendingAppointments(formattedDate, methods.addMonths(formattedDate, 1));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void initialization() {
        methods = new Methods(getActivity());
        sharedPreferences = getActivity().getSharedPreferences("userdetails", 0);
        appointment_charge_TV = view.findViewById(R.id.appointment_charge_TV);
        total_appointment_headline_TV = view.findViewById(R.id.total_appointment_headline_TV);
        cal_date_TV = view.findViewById(R.id.cal_date_TV);
        upcoming_visit_RV = view.findViewById(R.id.upcoming_visit_RV);
        pending_request_RV = view.findViewById(R.id.pending_request_RV);
        month_TV = view.findViewById(R.id.month_TV);
        upcoming_visit_empty_TV = view.findViewById(R.id.upcoming_visit_empty_TV);
        pending_visit_empty_TV = view.findViewById(R.id.pending_visit_empty_TV);
        upcoming_visit_SFL = view.findViewById(R.id.upcoming_visit_SFL);
        pending_visit_SFL = view.findViewById(R.id.pending_visit_SFL);
        horizontalCalendarView = view.findViewById(R.id.horizontalCalendarView);
        edit_appointment_charge_RL = view.findViewById(R.id.edit_appointment_charge_RL);
        add_appointment_RL = view.findViewById(R.id.add_appointment_RL);
        horizontalCalendarView.setContext(this);
        add_appointment_RL.setOnClickListener(this);
        edit_appointment_charge_RL.setOnClickListener(this);
        appointment_charge_TV.setText("₹ " + Config.onlineConsultationCharges);


    }

    @Override
    public void updateMonthOnScroll(DayDateMonthYearModel selectedDate) {
        String month = "" + selectedDate.month + " " + selectedDate.year;
        month_TV.setText("<" + month + ">");
    }

    @Override
    public void newDateSelected(DayDateMonthYearModel selectedDate) {
        String month = "" + (selectedDate.day) + ". " + selectedDate.date + " " + selectedDate.month + " " + selectedDate.year;
        cal_date_TV.setText(month);
        String dateString = selectedDate.date + "/" + selectedDate.monthNumeric + "/" + selectedDate.year;
        upcoming_visit_empty_TV.setVisibility(View.GONE);
        upcoming_visit_RV.setVisibility(View.INVISIBLE);
        upcoming_visit_SFL.setVisibility(View.VISIBLE);
        upcoming_visit_SFL.startShimmerAnimation();
        getUpcomingAppointments(dateString);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_appointment_RL:
//                Toast.makeText(getContext(), "ADD", Toast.LENGTH_SHORT).show();
                String userTYpe = sharedPreferences.getString("user_type", "");
                if (userTYpe.equals("Vet Staff")) {
                    Gson gson = new Gson();
                    String json = sharedPreferences.getString("userPermission", null);
                    Type type = new TypeToken<ArrayList<UserPermissionMasterList>>() {
                    }.getType();
                    ArrayList<UserPermissionMasterList> arrayList = gson.fromJson(json, type);
                    Log.e("ArrayList", arrayList.toString());
                    Log.d("UserType", userTYpe);
                    permissionId = "11";
                    methods.showCustomProgressBarDialog(getContext());
                    String url = "user/CheckStaffPermission/" + permissionId;
                    Log.e("URL", url);
                    ApiService<CheckStaffPermissionResponse> service = new ApiService<>();
                    service.get(this, ApiClient.getApiInterface().getCheckStaffPermission(Config.token, url), "CheckPermission");
                } else if (userTYpe.equals("Veterinarian")) {
                    Intent intent = new Intent(getContext(), AddUpdateAppointmentActivity.class);
                    intent.putExtra("type", "add");
                    intent.putExtra("id", "");
                    intent.putExtra("purpose", "");
                    intent.putExtra("pet_id", "");
                    startActivityForResult(intent, 100);
                }
                break;

            case R.id.edit_appointment_charge_RL:

                break;

        }
    }

    private void getRequestPendingAppointments(String dateString, String toDate) {
        GetAppointmentFromDateParams getAppointmentFromDateParams = new GetAppointmentFromDateParams();
        getAppointmentFromDateParams.setFromDate(dateString);
        getAppointmentFromDateParams.setToDate(toDate);
        GetAppointmentFromDateRequest getAppointmentFromDateRequest = new GetAppointmentFromDateRequest();
        getAppointmentFromDateRequest.setData(getAppointmentFromDateParams);

        ApiService<RequestPendingResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getPendingAppointments(Config.token, getAppointmentFromDateRequest), "GetPendingAppointments");
        Log.e("DATALOG", "check1=> " + methods.getRequestJson(getAppointmentFromDateRequest));
    }

    private void getUpcomingAppointments(String dateString) {

        GetAppointmentFromDateParams getAppointmentFromDateParams = new GetAppointmentFromDateParams();
        getAppointmentFromDateParams.setFromDate(dateString);
        GetAppointmentFromDateRequest getAppointmentFromDateRequest = new GetAppointmentFromDateRequest();
        getAppointmentFromDateRequest.setData(getAppointmentFromDateParams);
        ApiService<UpcomingAppointmentResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getAppointmentByDate(Config.token, getAppointmentFromDateRequest), "GetAppointmentByDate");
        Log.e("DATALOG", "check1=> " + methods.getRequestJson(getAppointmentFromDateRequest));
    }

    private void checkPermission(String url) {
        ApiService<CheckStaffPermissionResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getCheckStaffPermission(Config.token, url), "CheckPermission");
    }

    @Override
    public void onResponse(Response response, String key) {

        switch (key) {

            case "GetAppointmentByDate":
                try {
                    UpcomingAppointmentResponse upcomingAppointmentResponse = (UpcomingAppointmentResponse) response.body();
                    Log.e("UpcomingResponse", methods.getRequestJson(upcomingAppointmentResponse));
                    int responseCode = Integer.parseInt(upcomingAppointmentResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        if (upcomingAppointmentResponse.getData().isEmpty()) {
                            upcoming_visit_empty_TV.setVisibility(View.VISIBLE);
                            upcoming_visit_SFL.setVisibility(View.INVISIBLE);
                            upcoming_visit_SFL.stopShimmerAnimation();
                            upcoming_visit_RV.setVisibility(View.INVISIBLE);
                        } else {
                            String totalAppointments = String.valueOf(upcomingAppointmentResponse.getData().size());
                            total_appointment_headline_TV.setText("You have " + totalAppointments + " appointments");
                            upcomingAppointmentsAdapter = new UpcomingAppointmentsAdapter(upcomingAppointmentResponse.getData(), getContext(), this);
                            LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                            upcoming_visit_RV.setLayoutManager(horizontalLayoutManagaer);
                            upcoming_visit_RV.setAdapter(upcomingAppointmentsAdapter);

                            upcoming_visit_empty_TV.setVisibility(View.GONE);
                            upcoming_visit_SFL.setVisibility(View.INVISIBLE);
                            upcoming_visit_SFL.stopShimmerAnimation();
                            upcoming_visit_RV.setVisibility(View.VISIBLE);
                        }

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

            case "GetPendingAppointments":
                try {
                    RequestPendingResponse requestPendingResponse = (RequestPendingResponse) response.body();
                    Log.e("PendingAppointments", methods.getRequestJson(requestPendingResponse));
                    int responseCode = Integer.parseInt(requestPendingResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        if (requestPendingResponse.getData().isEmpty()) {
                            pending_visit_empty_TV.setVisibility(View.VISIBLE);
                            pending_visit_SFL.setVisibility(View.INVISIBLE);
                            pending_request_RV.setVisibility(View.GONE);
                            pending_visit_SFL.stopShimmerAnimation();
                        } else {
                            requestPendingAdapter = new RequestPendingAdapter(requestPendingResponse.getData(), getContext(), this);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                            pending_request_RV.setLayoutManager(linearLayoutManager);
                            pending_request_RV.setAdapter(requestPendingAdapter);
                            pending_request_RV.setNestedScrollingEnabled(false);
                            pending_request_RV.setLayoutFrozen(true);
                            pending_visit_empty_TV.setVisibility(View.GONE);
                            pending_visit_SFL.setVisibility(View.INVISIBLE);
                            pending_request_RV.setVisibility(View.VISIBLE);
                            pending_visit_SFL.stopShimmerAnimation();
                        }


                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "CheckPermission":
                try {
                    methods.customProgressDismiss();
                    CheckStaffPermissionResponse checkStaffPermissionResponse = (CheckStaffPermissionResponse) response.body();
                    Log.d("GetPetList", checkStaffPermissionResponse.toString());
                    int responseCode = Integer.parseInt(checkStaffPermissionResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        if (checkStaffPermissionResponse.getData().equals("true")) {
                            if (permissionId.equals("11")) {
                                Intent intent = new Intent(getContext(), AddUpdateAppointmentActivity.class);
                                intent.putExtra("type", "add");
                                intent.putExtra("purpose", "");
                                intent.putExtra("id", "");
                                intent.putExtra("pet_id", "");
                                startActivity(intent);
                            } else if (permissionId.equals("13")) {
                                Intent petDetailsIntent = new Intent(getContext(), AddClinicActivity.class);
                                Bundle data = new Bundle();
                                data.putString("pet_id", appointmentListsJoin.get(joinPostion).getPetId() + ".0");
                                data.putString("pet_parent", appointmentListsJoin.get(joinPostion).getPetParentName());
                                data.putString("pet_sex", appointmentListsJoin.get(joinPostion).getPetSex());
                                data.putString("pet_age", appointmentListsJoin.get(joinPostion).getPetAge());
                                data.putString("pet_unique_id", appointmentListsJoin.get(joinPostion).getPetUniqueId());
                                data.putString("appointment_ID", appointmentListsJoin.get(joinPostion).getId());
                                data.putString("pet_DOB", appointmentListsJoin.get(joinPostion).getPetDOB());
                                data.putString("pet_encrypted_id", appointmentListsJoin.get(joinPostion).getEncrptedId());
                                data.putString("nature_of_visit", "");
                                data.putString("visit_dt", "");
                                data.putString("visit_description", "");
                                data.putString("remarks", "");
                                data.putString("visit_weight", "");
                                data.putString("visit_temparature", "");
                                data.putString("dt_of_illness", "");
                                data.putString("pet_diognosis", "");
                                data.putString("next_dt", "");
                                data.putString("appointment", "join");
                                data.putString("appoint_link", String.valueOf((Uri.parse(appointmentListsJoin.get(joinPostion).getMeetingUrl()))));
                                data.putString("toolbar_name", "ADD CLINIC");
                                petDetailsIntent.putExtras(data);
                                startActivity(petDetailsIntent);
                            } else if (permissionId.equals("12")) {
                                Intent intent = new Intent(getContext(), AddUpdateAppointmentActivity.class);
                                intent.putExtra("type", "update");
                                intent.putExtra("purpose", appointmentListsJoin.get(joinPostion).getSubject());
                                intent.putExtra("id", appointmentListsJoin.get(joinPostion).getId());
                                intent.putExtra("pet_id", appointmentListsJoin.get(joinPostion).getPetId());
                                intent.putExtra("petParent", appointmentListsJoin.get(joinPostion).getPetUniqueId());
                                if (appointmentListsJoin.get(joinPostion).getPaymentStatus().equals("false")) {
                                    startActivityForResult(intent, 100);
                                }
                            }
                        } else {
                            Toast.makeText(getContext(), "Permission not Granted!!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Please Try Again!!", Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case "Status":
                try {

                    Log.d("appointmentstaus", response.body().toString());
                    JsonObject appointmentstaus = (JsonObject) response.body();
                    Log.d("appointmentstaus", appointmentstaus.toString());
//                    approveAndReject("true");
                    JsonObject responseStaus = appointmentstaus.getAsJsonObject("response");
                    Log.d("hhshshhs", "" + response);
                    int responseCode = Integer.parseInt(String.valueOf(responseStaus.get("responseCode")));
                    if (responseCode == 109) {

                        pendingAppointment();
                        Toast.makeText(getContext(), "Status Changes Successfully Status", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onJoinClick(int position, ArrayList<UpcomingAppointmentData> appointmentLists) {
        joinPostion = position;
        appointmentListsJoin = appointmentLists;
        String userTYpe = sharedPreferences.getString("user_type", "");
        if (userTYpe.equals("Vet Staff")) {
            Gson gson = new Gson();
            String json = sharedPreferences.getString("userPermission", null);
            Type type = new TypeToken<ArrayList<UserPermissionMasterList>>() {
            }.getType();
            ArrayList<UserPermissionMasterList> arrayList = gson.fromJson(json, type);
            Log.e("ArrayList", arrayList.toString());
            Log.d("UserType", userTYpe);
            permissionId = "13";
            methods.showCustomProgressBarDialog(getContext());
            String url = "user/CheckStaffPermission/" + permissionId;
            Log.e("URL", url);
            checkPermission(url);
        } else if (userTYpe.equals("Veterinarian")) {
            Intent petDetailsIntent = new Intent(getContext(), AddClinicActivity.class);
            Bundle data = new Bundle();
            data.putString("pet_id", appointmentLists.get(position).getPetId() + ".0");
            data.putString("pet_parent", appointmentLists.get(position).getPetParentName());
            data.putString("pet_sex", appointmentLists.get(position).getPetSex());
            data.putString("pet_age", appointmentLists.get(position).getPetAge());
            data.putString("pet_unique_id", appointmentLists.get(position).getPetUniqueId());
            data.putString("appointment_ID", appointmentLists.get(position).getId());
            data.putString("pet_DOB", appointmentLists.get(position).getPetDOB());
            data.putString("pet_encrypted_id", appointmentLists.get(position).getEncrptedId());
            data.putString("nature_of_visit", "");
            data.putString("visit_dt", "");
            data.putString("visit_description", "");
            data.putString("remarks", "");
            data.putString("visit_weight", "");
            data.putString("visit_temparature", "");
            data.putString("dt_of_illness", "");
            data.putString("pet_diognosis", "");
            data.putString("next_dt", "");
            data.putString("appointment", "join");
            data.putString("appoint_link", String.valueOf((Uri.parse(appointmentLists.get(position).getMeetingUrl()))));
            data.putString("toolbar_name", "ADD CLINIC");
            petDetailsIntent.putExtras(data);
            startActivity(petDetailsIntent);
        }
    }

    @Override
    public void onReScheduleClickFromUpcoming(int position, ArrayList<UpcomingAppointmentData> appointmentLists) {
        String userTYpe = sharedPreferences.getString("user_type", "");
        Log.e("USERTYPE", userTYpe);
        if (userTYpe.equals("Vet Staff")) {
            Gson gson = new Gson();
            String json = sharedPreferences.getString("userPermission", null);
            Type type = new TypeToken<ArrayList<UserPermissionMasterList>>() {
            }.getType();
            ArrayList<UserPermissionMasterList> arrayList = gson.fromJson(json, type);
            Log.e("ArrayList", arrayList.toString());
            Log.d("UserType", userTYpe);
            permissionId = "12";
            methods.showCustomProgressBarDialog(getContext());
            String url = "user/CheckStaffPermission/" + permissionId;
            Log.e("URL", url);
            checkPermission(url);

        } else if (userTYpe.equals("Veterinarian")) {
            Intent intent = new Intent(getContext(), AddUpdateAppointmentActivity.class);
            intent.putExtra("type", "update");
            intent.putExtra("purpose", appointmentLists.get(position).getSubject());
            intent.putExtra("id", appointmentLists.get(position).getId());
            intent.putExtra("pet_id", appointmentLists.get(position).getPetId());
            intent.putExtra("petParent", appointmentLists.get(position).getPetUniqueId());
            startActivityForResult(intent, 100);

        }
    }

    @Override
    public void onConfirmClick(final int position, final ArrayList<RequestPendingData> requestPendingData) {
        Log.e("request", requestPendingData.toString());
        mettingId = requestPendingData.get(position).getId();
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setMessage("Do you want to approve this appointment?");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                            requestPendingData.get(position).setIsApproved("true");
                            requestPendingAdapter.notifyItemChanged(position);
                            requestPendingAdapter.notifyDataSetChanged();
//                        requestPendingData.get(position).
                            approve_reject = "approve";
                            approveAndReject("true");
                            dialog.dismiss();





                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }

                });

        alertDialog.show();

    }

    private void approveAndReject(String status) {
        AppointmentStatusParams appointmentStatusParams = new AppointmentStatusParams();
        appointmentStatusParams.setId(mettingId);
        appointmentStatusParams.setStatus(status);
        AppointmentsStatusRequest appointmentsStatusRequest = new AppointmentsStatusRequest();
        appointmentsStatusRequest.setData(appointmentStatusParams);
        Log.d("Statusrequest", methods.getRequestJson(appointmentsStatusRequest));
        ApiService<JsonObject> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().appointmentApproveReject(Config.token, appointmentsStatusRequest), "Status");

    }

    @Override
    public void onCancelClick(final int position, final ArrayList<RequestPendingData> requestPendingData) {
        mettingId = requestPendingData.get(position).getId();
        Log.e("MEETINGid", "onCancelClick: "+mettingId);
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setMessage("Do you want to reject this appointment?");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        requestPendingData.remove(position);
                        requestPendingAdapter.notifyItemChanged(position);
                        requestPendingAdapter.notifyDataSetChanged();
                        approve_reject = "reject";
                        approveAndReject("false");
                        dialog.dismiss();
//                        AddUpdateAppointmentActivity addUpdateAppointmentActivity = new AddUpdateAppointmentActivity();
//                        addUpdateAppointmentActivity.re_schedule_BT;
//                        pendingAppointment();

                    }

                });
//        approveAndReject("false");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }

                });
        alertDialog.show();

    }

    @Override
    public void onReScheduleClickFromPending(int position, ArrayList<RequestPendingData> appointmentLists) {
        String userTYpe = sharedPreferences.getString("user_type", "");
        if (userTYpe.equals("Vet Staff")) {
            Gson gson = new Gson();
            String json = sharedPreferences.getString("userPermission", null);
            Type type = new TypeToken<ArrayList<UserPermissionMasterList>>() {
            }.getType();
            ArrayList<UserPermissionMasterList> arrayList = gson.fromJson(json, type);
            Log.e("ArrayList", arrayList.toString());
            Log.d("UserType", userTYpe);
            permissionId = "12";
            methods.showCustomProgressBarDialog(getContext());
            String url = "user/CheckStaffPermission/" + permissionId;
            Log.e("URL", url);
            checkPermission(url);
        } else if (userTYpe.equals("Veterinarian")) {
            Intent intent = new Intent(getContext(), AddUpdateAppointmentActivity.class);
            intent.putExtra("type", "update");
               intent.putExtra("purpose", appointmentLists.get(position).getSubject());
               intent.putExtra("id", appointmentLists.get(position).getId());
               intent.putExtra("pet_id", appointmentLists.get(position).getPetId());
               intent.putExtra("petParent", appointmentLists.get(position).getPetUniqueId());
               startActivityForResult(intent, 100);


        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                pendingAppointment();
            }

        }

    }
}