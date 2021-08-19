package com.cynoteck.petofyOPHR.fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.activities.ChangePasswordActivity;
import com.cynoteck.petofyOPHR.activities.GetAllBankAccountsActivity;
import com.cynoteck.petofyOPHR.activities.ImmunizationChartActivity;
import com.cynoteck.petofyOPHR.activities.LoginActivity;
import com.cynoteck.petofyOPHR.activities.SettingActivity;
import com.cynoteck.petofyOPHR.activities.VetOperatingHoursActivity;
import com.cynoteck.petofyOPHR.activities.ViewFullProfileVetActivity;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.appointmentParams.AppointmentStatusParams;
import com.cynoteck.petofyOPHR.params.appointmentParams.AppointmentsStatusRequest;
import com.cynoteck.petofyOPHR.response.loginRegisterResponse.UserPermissionMasterList;
import com.cynoteck.petofyOPHR.response.onlineAppointmentOnOff.OnlineAppointmentResponse;
import com.cynoteck.petofyOPHR.response.staffPermissionListResponse.CheckStaffPermissionResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener, ApiResponse {

    TextView vet_name_TV,vet_study_TV;
    ImageView vet_profile_pic;
    SwitchCompat online_switch;
    View view;
    String status;
    SharedPreferences sharedPreferences;
    String userTYpe="";
    String permissionId="";
    Methods methods;
    Context context;
    ConstraintLayout general_details_CL,operating_hours_CL,change_password_CL,immunization_master_CL,bank_account_CL,privacy_CL,logout_CL;
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.vet_profile_fragment, container, false);
        sharedPreferences = getActivity().getSharedPreferences("userdetails", 0);
        methods = new Methods(context);



        initialize();
        getVetInfo();
        switchOnline();

        return view;
    }

    private void getVetInfo() {
        try {
            Glide.with(this)
                    .load(new URL(Config.user_Veterian_url))
                    .placeholder(R.drawable.doctor_dummy_image)
                    .into(vet_profile_pic);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        vet_name_TV.setText(Config.user_Veterian_name);
        vet_study_TV.setText(Config.user_Veterian_study);


    }

    private void switchOnline() {
        online_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    status = "1";
                    onlineAppoint(status);
                }else {
                    status = "0";
                    onlineAppoint(status);

                }
            }
        });
    }

    private void onlineAppoint(String value) {
        AppointmentStatusParams getPetListParams = new AppointmentStatusParams();
        getPetListParams.setId(value);
        AppointmentsStatusRequest getPetListRequest = new AppointmentsStatusRequest();
        getPetListRequest.setData(getPetListParams);
        ApiService<OnlineAppointmentResponse> service = new ApiService<>();
        service.get( this, ApiClient.getApiInterface().onlineAppointmentOnOff(Config.token,getPetListRequest), "OnlineAppoint");
        Log.e("onlineAppointment==>",""+methods.getRequestJson(getPetListRequest));

    }

    public  void initialize()
    {


        vet_name_TV = view.findViewById(R.id.vet_name_TV);
        vet_study_TV = view.findViewById(R.id.vet_study_TV);
        vet_profile_pic = view.findViewById(R.id.vet_profile_pic);
        online_switch = view.findViewById(R.id.online_switch);
        general_details_CL=view.findViewById(R.id.general_details_CL);
        operating_hours_CL=view.findViewById(R.id.operating_hours_CL);
        immunization_master_CL=view.findViewById(R.id.immunization_master_CL);
        bank_account_CL=view.findViewById(R.id.bank_account_CL);
        privacy_CL=view.findViewById(R.id.privacy_CL);
        logout_CL=view.findViewById(R.id.logout_CL);
        change_password_CL=view.findViewById(R.id.change_password_CL);


        general_details_CL.setOnClickListener(this);
        operating_hours_CL.setOnClickListener(this);
        immunization_master_CL.setOnClickListener(this);
        bank_account_CL.setOnClickListener(this);
        logout_CL.setOnClickListener(this);
        privacy_CL.setOnClickListener(this);
        change_password_CL.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.bank_account_CL:
                Intent  bank_accounts_intent = new Intent(getContext(), GetAllBankAccountsActivity.class);
                startActivity(bank_accounts_intent);
                break;
            case R.id.immunization_master_CL:
                userTYpe = sharedPreferences.getString("user_type", "");
                if (userTYpe.equals("Vet Staff")){
                    Gson gson = new Gson();
                    String json = sharedPreferences.getString("userPermission", null);
                    Type type = new TypeToken<ArrayList<UserPermissionMasterList>>() {}.getType();
                    ArrayList<UserPermissionMasterList> arrayList = gson.fromJson(json, type);
                    Log.e("ArrayList",arrayList.toString());
                    Log.d("UserType",userTYpe);
                    permissionId = "18";
                    methods.showCustomProgressBarDialog(getContext());
                    String url  = "user/CheckStaffPermission/"+permissionId;
                    Log.e("URL",url);
                    ApiService<CheckStaffPermissionResponse> service = new ApiService<>();
                    service.get(this, ApiClient.getApiInterface().getCheckStaffPermission(Config.token,url), "CheckPermission");
                }else if (userTYpe.equals("Veterinarian")){
                    Intent immunization_master_intent = new Intent(getContext(), ImmunizationChartActivity.class);
                    startActivity(immunization_master_intent);

                }

                break;
            case R.id.change_password_CL:

                Intent changePass_intent = new Intent(getContext(), ChangePasswordActivity.class);
                startActivity(changePass_intent);
                break;
            case R.id.general_details_CL:
                Intent veterian_full_profile_intent = new Intent(getContext(), ViewFullProfileVetActivity.class);
                startActivity(veterian_full_profile_intent);
                break;

            case R.id.operating_hours_CL:
                startActivity(new Intent(getActivity(), VetOperatingHoursActivity.class));
                break;

            case R.id.privacy_CL:
                Intent setting = new Intent(getContext(), SettingActivity.class);
                startActivity(setting);

                break;

            case R.id.logout_CL:
                Mydialog();
                break;





        }
    }

 public void Mydialog()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setMessage("Do you really want to Logout");
        builder.setTitle("Alert Box");
        builder.setCancelable(false);


        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();

            }
        });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SharedPreferences preferences =getActivity().getSharedPreferences("userdetails",0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                Config.logoutFromAccount = true;
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();


            }
        });


        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }




    @Override
    public void onResume() {
        super.onResume();
        if (Config.user_Veterian_online.equals("true")){
            online_switch.setChecked(true);
        }else {
            online_switch.setChecked(false);
        }    }

    @Override
    public void onResponse(Response response, String key) {
        switch (key){
            case "OnlineAppoint":
                try {
                    OnlineAppointmentResponse onlineAppointmentResponse = (OnlineAppointmentResponse) response.body();
                    int responseCode = Integer.parseInt(onlineAppointmentResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        if (status.equals("1")){
                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userdetails", 0);
                            SharedPreferences.Editor login_editor;
                            login_editor = sharedPreferences.edit();
                            login_editor.putString("onlineAppoint", "true");
                            login_editor.commit();
                            Config.user_Veterian_online ="true";

                        }else {
                            Toast.makeText(getContext(), "Disable Online Appointment", Toast.LENGTH_SHORT).show();
                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userdetails", 0);
                            SharedPreferences.Editor login_editor;
                            login_editor = sharedPreferences.edit();
                            login_editor.putString("onlineAppoint", "false");
                            login_editor.commit();
                            Config.user_Veterian_online ="false";

                        }
                    }else if (responseCode==614){
                        Toast.makeText(getContext(), onlineAppointmentResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }

                break;

            case "CheckPermission":
                try {
                    methods.customProgressDismiss();
                    CheckStaffPermissionResponse checkStaffPermissionResponse = (CheckStaffPermissionResponse) response.body();
                    int responseCode = Integer.parseInt(checkStaffPermissionResponse.getResponse().getResponseCode());

                    if (responseCode == 109) {
                        if (checkStaffPermissionResponse.getData().equals("true")){
                            if (permissionId.equals("18")) {
                                Intent immunization_master_intent = new Intent(getContext(), ImmunizationChartActivity.class);
                                startActivity(immunization_master_intent);
                            }
                        }else {
                            Toast.makeText(getContext(), "Permission not Granted!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(context, "Please Try Again!!", Toast.LENGTH_SHORT).show();
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
}
