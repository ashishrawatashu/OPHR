package com.cynoteck.petofyOPHR.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.activities.AddClinicActivity;
import com.cynoteck.petofyOPHR.activities.AddUpdateAppointmentActivity;
import com.cynoteck.petofyOPHR.adapters.DateListAdapter;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.appointmentParams.AppointmentStatusParams;
import com.cynoteck.petofyOPHR.params.appointmentParams.AppointmentsStatusRequest;
import com.cynoteck.petofyOPHR.response.appointmentResponse.AppointmentList;
import com.cynoteck.petofyOPHR.response.appointmentResponse.GetAppointmentDates;
import com.cynoteck.petofyOPHR.response.appointmentResponse.GetAppointmentResponse;
import com.cynoteck.petofyOPHR.response.getAppointmentsStatusResponse.AppointmentStatusResponse;
import com.cynoteck.petofyOPHR.response.loginRegisterResponse.UserPermissionMasterList;
import com.cynoteck.petofyOPHR.utils.AppointmentsClickListner;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppointementFragment extends Fragment implements ApiResponse ,View.OnClickListener
{
    FloatingActionButton create_appointment_FBT;
    GetAppointmentResponse  getAppointmentResponse;
    View view;
    RecyclerView date_day_RV;
    DateListAdapter dateListAdapter;
    ArrayList<GetAppointmentDates> getAppointmentDates;
    Methods methods;
    ArrayList<AppointmentList> appointmentList;
    String mettingId="", status="",pet_id="",pet_owner_name="",pet_sex="",pet_age="",pet_unique_id="";
    SharedPreferences sharedPreferences;

    private ShimmerFrameLayout mShimmerViewContainer;
    AppointmentsClickListner appointmentsClickListner;


    public AppointementFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_appointement, container, false);

        methods = new Methods(getContext());
        date_day_RV = view.findViewById(R.id.date_day_RV);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);
        create_appointment_FBT=view.findViewById(R.id.create_appointment_FBT);
        create_appointment_FBT.setOnClickListener(this);
        sharedPreferences = getActivity().getSharedPreferences("userdetails", 0);

        if (methods.isInternetOn()){
            getAppointment();

        }else {
            methods.DialogInternet();
        }


        return view;

    }

    public void getAppointment() {
        ApiService<GetAppointmentResponse> service = new ApiService<>();
        service.get( this, ApiClient.getApiInterface().getAppointment(Config.token), "GetAppointment");

    }
    private void approveAndReject() {
        AppointmentStatusParams appointmentStatusParams = new AppointmentStatusParams();
        appointmentStatusParams.setId(mettingId);
        appointmentStatusParams.setStatus(status);
        AppointmentsStatusRequest appointmentsStatusRequest = new AppointmentsStatusRequest();
        appointmentsStatusRequest.setData(appointmentStatusParams);
        Log.d("Statusrequest",appointmentsStatusRequest.toString());

        ApiService<AppointmentStatusResponse> service = new ApiService<>();
        service.get( this, ApiClient.getApiInterface().appointmentApproveReject(Config.token,appointmentsStatusRequest), "Status");

    }
    @Override
    public void onResponse(Response arg0, String key) {
        Log.d("Response", arg0.toString());
        switch (key){
            case  "GetAppointment":
                try {
                    getAppointmentResponse  = (GetAppointmentResponse) arg0.body();
                    Log.d("GetAppointment", getAppointmentResponse.toString());
                    int responseCode = Integer.parseInt(getAppointmentResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
//                        approveAndReject("19","false");

                        mShimmerViewContainer.setVisibility(View.GONE);
                        create_appointment_FBT.setVisibility(View.VISIBLE);
                        mShimmerViewContainer.stopShimmerAnimation();
                        dateListAdapter= new DateListAdapter(getAppointmentResponse.getData(), getContext(), new AppointmentsClickListner() {
                            @Override
                            public void onItemClick(int position, ArrayList<AppointmentList> appointmentLists) {

                                String userTYpe = sharedPreferences.getString("user_type", "");
                                Log.e("USERTYPE",userTYpe);
                                if (userTYpe.equals("Vet Staff")){
                                    Gson gson = new Gson();
                                    String json = sharedPreferences.getString("userPermission", null);
                                    Type type = new TypeToken<ArrayList<UserPermissionMasterList>>() {}.getType();
                                    ArrayList<UserPermissionMasterList> arrayList = gson.fromJson(json, type);
                                    Log.e("ArrayList",arrayList.toString());
                                    Log.d("UserType",userTYpe);
                                    if (arrayList.get(11).getIsSelected().equals("true")) {
                                        Intent intent = new Intent(getContext(),AddUpdateAppointmentActivity.class);
                                        intent.putExtra("type","update");
                                        intent.putExtra("id",appointmentLists.get(position).getId());
                                        intent.putExtra("pet_id",appointmentLists.get(position).getPetId());
                                        intent.putExtra("petParent",appointmentLists.get(position).getPetUniqueId());
                                        if(appointmentLists.get(position).getPaymentStatus().equals("true"))
                                            startActivity(intent);
                                    }else {
                                        Toast.makeText(getContext(), "Permission not allowed!!", Toast.LENGTH_SHORT).show();
                                    }
                                }else if (userTYpe.equals("Veterinarian")){
                                    Intent intent = new Intent(getContext(),AddUpdateAppointmentActivity.class);
                                    intent.putExtra("type","update");
                                    intent.putExtra("id",appointmentLists.get(position).getId());
                                    intent.putExtra("pet_id",appointmentLists.get(position).getPetId());
                                    intent.putExtra("petParent",appointmentLists.get(position).getPetUniqueId());
                                    if(appointmentLists.get(position).getPaymentStatus().equals("true")) {
                                        startActivity(intent);
                                    }
                                }
                            }

                            @Override
                            public void onJoinClick(int position, ArrayList<AppointmentList> appointmentLists, Button button) {
                                Uri uri = Uri.parse(appointmentLists.get(position).getMeetingUrl()); // missing 'http://' will cause crashed
//                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                                startActivity(intent);
                                String userTYpe = sharedPreferences.getString("user_type", "");
                                if (userTYpe.equals("Vet Staff")){
                                    Gson gson = new Gson();
                                    String json = sharedPreferences.getString("userPermission", null);
                                    Type type = new TypeToken<ArrayList<UserPermissionMasterList>>() {}.getType();
                                    ArrayList<UserPermissionMasterList> arrayList = gson.fromJson(json, type);
                                    Log.e("ArrayList",arrayList.toString());
                                    Log.d("UserType",userTYpe);
                                    if (arrayList.get(12).getIsSelected().equals("true")) {
                                        Intent petDetailsIntent = new Intent(getContext(), AddClinicActivity.class);
                                        Bundle data = new Bundle();
                                        data.putString("pet_id",appointmentLists.get(position).getPetId()+".0");
                                        data.putString("pet_parent",appointmentLists.get(position).getPetParentName());
                                        data.putString("pet_sex",appointmentLists.get(position).getPetSex());
                                        data.putString("pet_age",appointmentLists.get(position).getPetAge());
                                        data.putString("pet_unique_id",appointmentLists.get(position).getPetUniqueId());
                                        data.putString("appointment_ID",appointmentLists.get(position).getId());
                                        data.putString("pet_DOB",appointmentLists.get(position).getPetDOB());
                                        data.putString("pet_encrypted_id",appointmentLists.get(position).getEncrptedId());
                                        data.putString("nature_of_visit","");
                                        data.putString("visit_dt","");
                                        data.putString("visit_description","");
                                        data.putString("remarks","");
                                        data.putString("visit_weight","");
                                        data.putString("visit_temparature","");
                                        data.putString("dt_of_illness","");
                                        data.putString("pet_diognosis","");
                                        data.putString("next_dt","");
                                        data.putString("appointment","join");
                                        data.putString("appoint_link", String.valueOf((Uri.parse(appointmentLists.get(position).getMeetingUrl()))));
                                        data.putString("toolbar_name","ADD CLINIC");
                                        petDetailsIntent.putExtras(data);
                                        startActivity(petDetailsIntent);
                                    }
                                    else {
                                        Toast.makeText(getContext(), "Permission not allowed!!", Toast.LENGTH_SHORT).show();
                                    }
                                }else if (userTYpe.equals("Veterinarian")){
                                    Intent petDetailsIntent = new Intent(getContext(), AddClinicActivity.class);
                                    Bundle data = new Bundle();
                                    data.putString("pet_id",appointmentLists.get(position).getPetId()+".0");
                                    data.putString("pet_parent",appointmentLists.get(position).getPetParentName());
                                    data.putString("pet_sex",appointmentLists.get(position).getPetSex());
                                    data.putString("pet_age",appointmentLists.get(position).getPetAge());
                                    data.putString("pet_unique_id",appointmentLists.get(position).getPetUniqueId());
                                    data.putString("appointment_ID",appointmentLists.get(position).getId());
                                    data.putString("pet_DOB",appointmentLists.get(position).getPetDOB());
                                    data.putString("pet_encrypted_id",appointmentLists.get(position).getEncrptedId());
                                    data.putString("nature_of_visit","");
                                    data.putString("visit_dt","");
                                    data.putString("visit_description","");
                                    data.putString("remarks","");
                                    data.putString("visit_weight","");
                                    data.putString("visit_temparature","");
                                    data.putString("dt_of_illness","");
                                    data.putString("pet_diognosis","");
                                    data.putString("next_dt","");
                                    data.putString("appointment","join");
                                    data.putString("appoint_link", String.valueOf((Uri.parse(appointmentLists.get(position).getMeetingUrl()))));
                                    data.putString("toolbar_name","ADD CLINIC");
                                    petDetailsIntent.putExtras(data);
                                    startActivity(petDetailsIntent);
                                }
                            }

                            @Override
                            public void onApproveClick(final int position, final ArrayList<AppointmentList> appointmentLists, final Button button) {
                                mettingId = appointmentLists.get(position).getId();
                                Log.d("Add Anotheer Veterian","vet");
                                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                                alertDialog.setTitle("");
                                alertDialog.setMessage("Do you want to approve this appointment?");
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Approve",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                status = "true";
                                                button.setVisibility(View.GONE);
                                                approveAndReject();
                                                dialog.dismiss();

                                            }
                                        });

                                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Reject",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                status = "false";
                                                approveAndReject();
                                                dialogInterface.dismiss();

                                            }

                                        });
                                alertDialog.show();

                            }


                        });
                        date_day_RV.setLayoutManager(new LinearLayoutManager(getContext()));
                        date_day_RV.setAdapter(dateListAdapter);
                        dateListAdapter.notifyDataSetChanged();


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "Status":
                try {
                    Log.d("appointmentstaus",arg0.body().toString());
                    AppointmentStatusResponse appointmentStatusResponse = (AppointmentStatusResponse) arg0.body();
                    Log.d("appointmentstaus",appointmentStatusResponse.toString());
                    int responseCode = Integer.parseInt(appointmentStatusResponse.getResponse().getResponseCode());
                    if (responseCode==109) {
                        Toast.makeText(getContext(), "Status Changes Successfully", Toast.LENGTH_SHORT).show();
                        getAppointment();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                break;

        }
    }



    @Override
    public void onError(Throwable t, String key) {

    }


    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
        if (Config.backCall.equals("hit")) {
            Config.backCall ="";
            getAppointment();
        }
    }

    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.create_appointment_FBT:

                String userTYpe = sharedPreferences.getString("user_type", "");
                if (userTYpe.equals("Vet Staff")){
                    Gson gson = new Gson();
                    String json = sharedPreferences.getString("userPermission", null);
                    Type type = new TypeToken<ArrayList<UserPermissionMasterList>>() {}.getType();
                    ArrayList<UserPermissionMasterList> arrayList = gson.fromJson(json, type);
                    Log.e("ArrayList",arrayList.toString());
                    Log.d("UserType",userTYpe);
                    if (arrayList.get(10).getIsSelected().equals("true")) {
                        Intent intent = new Intent(getContext(), AddUpdateAppointmentActivity.class);
                        intent.putExtra("type","add");
                        intent.putExtra("id","");
                        intent.putExtra("pet_id","");
                        startActivity(intent);
                    }else {
                        Toast.makeText(getContext(), "Permission not allowed!!", Toast.LENGTH_SHORT).show();
                    }
                }else if (userTYpe.equals("Veterinarian")){
                    Intent intent = new Intent(getContext(), AddUpdateAppointmentActivity.class);
                    intent.putExtra("type","add");
                    intent.putExtra("id","");
                    intent.putExtra("pet_id","");
                    startActivity(intent);
                }
                break;
        }
    }
}
