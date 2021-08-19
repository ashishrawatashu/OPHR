package com.cynoteck.petofyOPHR.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.activities.AddNewPetActivity;
import com.cynoteck.petofyOPHR.activities.MedicalHistoryActivity;
import com.cynoteck.petofyOPHR.activities.PetDetailsActivity;
import com.cynoteck.petofyOPHR.activities.SearchActivity;
import com.cynoteck.petofyOPHR.activities.StaffListActivity;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.checkpetInVetRegister.InPetRegisterRequest;
import com.cynoteck.petofyOPHR.params.newPetEntryParams.NewPetParams;
import com.cynoteck.petofyOPHR.params.newPetEntryParams.NewPetRequest;
import com.cynoteck.petofyOPHR.params.otpRequest.SendOtpParameter;
import com.cynoteck.petofyOPHR.params.otpRequest.SendOtpRequest;
import com.cynoteck.petofyOPHR.response.InPetVeterian.InPetVeterianResponse;
import com.cynoteck.petofyOPHR.response.loginRegisterResponse.UserPermissionMasterList;
import com.cynoteck.petofyOPHR.response.newPetResponse.NewPetRegisterResponse;
import com.cynoteck.petofyOPHR.response.otpResponse.OtpResponse;
import com.cynoteck.petofyOPHR.response.staffPermissionListResponse.CheckStaffPermissionResponse;
import com.cynoteck.petofyOPHR.response.totalStaffPetsAppointment.GetDashboardCountsResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Response;

public class HomeFragment extends Fragment implements View.OnClickListener, ApiResponse, TextWatcher {

    RecyclerView pet_list_RV;
    Context context;
    View view;
    RelativeLayout search_boxRL;
    RelativeLayout addNewEntry;
    Methods methods;
    CardView reports_CV, all_staff_CV, allPets_CV, appoint_CV;
    ArrayList<String> petUniueId = null;
    HashMap<String, String> petExistingSearch;
    TextView vet_name_TV, staff_headline_TV, cancelOtpDialog;
    String petId = "", petParentContactNumber = "", strResponseOtp = "";
    Dialog otpDialog;
    TextInputLayout otp_TL;
    TextInputEditText pet_parent_otp;
    Button submit_parent_otp;
    SharedPreferences sharedPreferences;
    String userTYpe = "";
    String permissionId = "";
    TextView search_box_TV, total_my_pets_TV, total_appointment_TV, total_staff_TV;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        sharedPreferences = getActivity().getSharedPreferences("userdetails", 0);

        init();
        ApiService<GetDashboardCountsResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getDashboardCounts(Config.token), "GetDashboardCounts");
        setTotalDashboardNumber();
        return view;
    }


    private void init() {
        methods = new Methods(context);
        vet_name_TV = view.findViewById(R.id.vet_name_TV);
        search_box_TV = view.findViewById(R.id.search_box_TV);
        staff_headline_TV = view.findViewById(R.id.staff_headline_TV);
        addNewEntry = view.findViewById(R.id.addNewEntry);
        reports_CV = view.findViewById(R.id.reports_CV);
        all_staff_CV = view.findViewById(R.id.staff_CV);
        pet_list_RV = view.findViewById(R.id.pet_id_TV);
        allPets_CV = view.findViewById(R.id.allPets_CV);
        appoint_CV = view.findViewById(R.id.appoint_CV);
        total_staff_TV = view.findViewById(R.id.total_staff_TV);
        total_my_pets_TV = view.findViewById(R.id.total_my_pets_TV);
        total_appointment_TV = view.findViewById(R.id.total_appointment_TV);

        vet_name_TV.setText("Hello, Dr " + Config.vet_first_name);

        addNewEntry.setOnClickListener(this);
        allPets_CV.setOnClickListener(this);
        reports_CV.setOnClickListener(this);
        all_staff_CV.setOnClickListener(this);
        appoint_CV.setOnClickListener(this);
        search_box_TV.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_box_TV:
                Intent searchPetActivity = new Intent(getContext(), SearchActivity.class);
                startActivity(searchPetActivity);
                break;
            case R.id.submit_parent_otp:
                String otp = pet_parent_otp.getText().toString();
                if (otp.isEmpty()) {
                    pet_parent_otp.setError("Enter Correct OTP");
                } else if (!otp.equals(strResponseOtp)) {
                    pet_parent_otp.setError("Enter Wrong OTP");
                } else {
                    pet_parent_otp.setError(null);
                    NewPetRequest newPetRequest = new NewPetRequest();
                    NewPetParams data = new NewPetParams();
                    data.setId(petId);
                    newPetRequest.setData(data);
                    if (methods.isInternetOn()) {
                        addNewRegisterPet(newPetRequest);
                    } else {
                        methods.DialogInternet();
                    }
                }
                break;

            case R.id.addNewEntry:
                userTYpe = sharedPreferences.getString("user_type", "");
                if (userTYpe.equals("Vet Staff")) {
                    Gson gson = new Gson();
                    String json = sharedPreferences.getString("userPermission", null);
                    Type type = new TypeToken<ArrayList<UserPermissionMasterList>>() {
                    }.getType();
                    ArrayList<UserPermissionMasterList> arrayList = gson.fromJson(json, type);
                    Log.e("ArrayList", arrayList.toString());
                    Log.d("UserType", userTYpe);
                    permissionId = "1";
                    methods.showCustomProgressBarDialog(getContext());
                    String url = "user/CheckStaffPermission/" + permissionId;
                    Log.e("URL", url);
                    ApiService<CheckStaffPermissionResponse> service = new ApiService<>();
                    service.get(this, ApiClient.getApiInterface().getCheckStaffPermission(Config.token, url), "CheckPermission");
                } else if (userTYpe.equals("Veterinarian")) {
                    Intent addNewPetIntent = new Intent(getContext(), AddNewPetActivity.class);
                    addNewPetIntent.putExtra("from", "Home");
                    addNewPetIntent.putExtra("appointment", "");
                    startActivity(addNewPetIntent);
                }

                break;

            case R.id.cancelOtpDialog:
                otpDialog.dismiss();
                break;

            case R.id.reports_CV:
                Intent medicalHisroryIntent = new Intent(getContext(), MedicalHistoryActivity.class);
                startActivity(medicalHisroryIntent);
                break;

            case R.id.staff_CV:
                userTYpe = sharedPreferences.getString("user_type", "");
                if (userTYpe.equals("Vet Staff")) {
                    Gson gson = new Gson();
                    String json = sharedPreferences.getString("userPermission", null);
                    Type type = new TypeToken<ArrayList<UserPermissionMasterList>>() {
                    }.getType();
                    ArrayList<UserPermissionMasterList> arrayList = gson.fromJson(json, type);
                    Log.e("ArrayList", arrayList.toString());
                    Log.d("UserType", userTYpe);
                    permissionId = "15";
                    methods.showCustomProgressBarDialog(getContext());
                    String url = "user/CheckStaffPermission/" + permissionId;
                    Log.e("URL", url);
                    ApiService<CheckStaffPermissionResponse> service = new ApiService<>();
                    service.get(this, ApiClient.getApiInterface().getCheckStaffPermission(Config.token, url), "CheckPermission");
                } else if (userTYpe.equals("Veterinarian")) {
                    Intent staffIntent = new Intent(getContext(), StaffListActivity.class);
                    startActivity(staffIntent);

                }

                break;

            case R.id.allPets_CV:
                PetRegisterFragment petRegisterFragment = new PetRegisterFragment();
                replaceFragment(petRegisterFragment);

                break;

            case R.id.appoint_CV:
                userTYpe = sharedPreferences.getString("user_type", "");
                if (userTYpe.equals("Vet Staff")) {
                    Gson gson = new Gson();
                    String json = sharedPreferences.getString("userPermission", null);
                    Type type = new TypeToken<ArrayList<UserPermissionMasterList>>() {
                    }.getType();
                    ArrayList<UserPermissionMasterList> arrayList = gson.fromJson(json, type);
                    Log.e("ArrayList", arrayList.toString());
                    Log.d("UserType", userTYpe);
                    permissionId = "16";
                    methods.showCustomProgressBarDialog(getContext());
                    String url = "user/CheckStaffPermission/" + permissionId;
                    Log.e("URL", url);
                    ApiService<CheckStaffPermissionResponse> service = new ApiService<>();
                    service.get(this, ApiClient.getApiInterface().getCheckStaffPermission(Config.token, url), "CheckPermission");
                } else if (userTYpe.equals("Veterinarian")) {
                    VetAppointmentsFragment VetAppointmentsFragment = new VetAppointmentsFragment();
                    replaceFragment(VetAppointmentsFragment);

                }

                break;


        }

    }

    private void replaceFragment(Fragment fragment) {
        Config.count = 0;
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    private void chkVetInregister(InPetRegisterRequest inPetRegisterRequest) {
        ApiService<InPetVeterianResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().checkPetInVetRegister(Config.token, inPetRegisterRequest), "CheckPetInVetRegister");
        Log.e("DATALOG", "check1=> " + inPetRegisterRequest);
    }

    private void sendotpUsingMobileNumber(SendOtpRequest sendOtpRequest) {
        ApiService<OtpResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().senOtp(Config.token, sendOtpRequest), "SendOtp");
        Log.e("DATALOG", "check1=> " + sendOtpRequest);
    }

    private void addNewRegisterPet(NewPetRequest newPetRequest) {
        ApiService<NewPetRegisterResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().addPetToRegister(Config.token, newPetRequest), "AddPetToRegister");
        Log.e("DATALOG", "check1=> " + newPetRequest);
    }

    @Override
    public void onResponse(Response arg0, String key) {

        switch (key) {
            case "CheckPermission":
                try {
                    methods.customProgressDismiss();
                    CheckStaffPermissionResponse checkStaffPermissionResponse = (CheckStaffPermissionResponse) arg0.body();
                    Log.d("GetPetList", checkStaffPermissionResponse.toString());
                    int responseCode = Integer.parseInt(checkStaffPermissionResponse.getResponse().getResponseCode());

                    if (responseCode == 109) {
                        if (checkStaffPermissionResponse.getData().equals("true")) {
                            if (permissionId.equals("16")) {
                                VetAppointmentsFragment VetAppointmentsFragment = new VetAppointmentsFragment();
                                replaceFragment(VetAppointmentsFragment);
                            } else if (permissionId.equals("1")) {
                                Intent addNewPetIntent = new Intent(getContext(), AddNewPetActivity.class);
                                addNewPetIntent.putExtra("from", "Home");
                                addNewPetIntent.putExtra("appointment", "");
                                startActivity(addNewPetIntent);
                            } else if (permissionId.equals("15")) {
                                Intent staffIntent = new Intent(getContext(), StaffListActivity.class);
                                startActivity(staffIntent);
                            }
                        } else {
                            Toast.makeText(context, "Permission not Granted!!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "Please Try Again!!", Toast.LENGTH_SHORT).show();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

            case "CheckPetInVetRegister":
                try {
                    Log.d("CheckPetInVetRegister", arg0.body().toString());
                    InPetVeterianResponse inPetVeterianResponse = (InPetVeterianResponse) arg0.body();
                    int responseCode = Integer.parseInt(inPetVeterianResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        if (!inPetVeterianResponse.getData().getPetId().equals("0")) {
                            petId = inPetVeterianResponse.getData().getPetId();
                            petParentContactNumber = inPetVeterianResponse.getData().getPetParentContactNumber();
                            String actualNumber = petParentContactNumber.replaceAll("-", "");
                            SendOtpRequest sendOtpRequest = new SendOtpRequest();
                            SendOtpParameter data = new SendOtpParameter();
                            data.setPhoneNumber(actualNumber);
                            data.setEmailId("");
                            sendOtpRequest.setData(data);
                            if (methods.isInternetOn()) {
                                sendotpUsingMobileNumber(sendOtpRequest);
                            } else {
                                methods.DialogInternet();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Invalid pet unique Id", Toast.LENGTH_SHORT).show();
                        }

                    } else if (responseCode == 614) {
                        Toast.makeText(getActivity(), inPetVeterianResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "SendOtp":
                try {
                    Log.d("SendOtp", arg0.body().toString());
                    OtpResponse otpResponse = (OtpResponse) arg0.body();
                    int responseCode = Integer.parseInt(otpResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        if (otpResponse.getData().getSuccess().equals("true")) {
                            strResponseOtp = otpResponse.getData().getOtp();
                            otpDialog();
                        } else {
                            Toast.makeText(getActivity(), "Invalid Mobile Number", Toast.LENGTH_SHORT).show();
                        }
                    } else if (responseCode == 614) {
                        Toast.makeText(getActivity(), otpResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "AddPetToRegister":
                try {
                    Log.d("AddPetToRegister", arg0.body().toString());
                    NewPetRegisterResponse newPetRegisterResponse = (NewPetRegisterResponse) arg0.body();
                    int responseCode = Integer.parseInt(newPetRegisterResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        otpDialog.dismiss();
                        Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
                        String sexName = "";
                        if (newPetRegisterResponse.getData().getSexId().equals("2.0"))
                            sexName = "Female";
                        else
                            sexName = "Male";
                        Intent petDetailsIntent = new Intent(getActivity().getApplication(), PetDetailsActivity.class);
                        Bundle data = new Bundle();
                        data.putString("pet_id", petId);
                        data.putString("pet_name", newPetRegisterResponse.getData().getPetName());
                        data.putString("pet_sex", sexName);
                        data.putString("pet_parent", newPetRegisterResponse.getData().getPetParentName());
                        data.putString("pet_age", "puppy");
                        data.putString("pet_unique_id", newPetRegisterResponse.getData().getPetUniqueId());
                        data.putString("pet_DOB", newPetRegisterResponse.getData().getDateOfBirth());
                        data.putString("pet_encrypted_id", "");
                        data.putString("pet_cat_id", newPetRegisterResponse.getData().getPetCategoryId());
                        data.putString("lastVisitEncryptedId", "");
                        petDetailsIntent.putExtras(data);
                        startActivity(petDetailsIntent);
                    } else if (responseCode == 614) {
                        Toast.makeText(getActivity(), newPetRegisterResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "GetDashboardCounts":
                try {
                    Log.d("SendOtp", arg0.body().toString());
                    GetDashboardCountsResponse getDashboardCountsResponse = (GetDashboardCountsResponse) arg0.body();
                    int responseCode = Integer.parseInt(getDashboardCountsResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        Config.total_appointment = getDashboardCountsResponse.getData().getNumberOfAppointments();
                        Config.total_pets = getDashboardCountsResponse.getData().getNumberOfPets();
                        Config.total_staff = getDashboardCountsResponse.getData().getNumberOfStaffs();
                        setTotalDashboardNumber();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


                break;
        }

    }

    private void setTotalDashboardNumber() {
        total_appointment_TV.setText(Config.total_appointment);
        total_staff_TV.setText(Config.total_staff);
        total_my_pets_TV.setText(Config.total_pets);
    }

    @Override
    public void onError(Throwable t, String key) {

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    public void otpDialog() {
        otpDialog = new Dialog(getContext());
        otpDialog.setContentView(R.layout.otp_layout);

        otp_TL = otpDialog.findViewById(R.id.otp_TL);
        pet_parent_otp = otpDialog.findViewById(R.id.pet_parent_otp);
        submit_parent_otp = otpDialog.findViewById(R.id.submit_parent_otp);
        cancelOtpDialog = otpDialog.findViewById(R.id.cancelOtpDialog);

        submit_parent_otp.setOnClickListener(this);
        cancelOtpDialog.setOnClickListener(this);


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = otpDialog.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        otpDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        otpDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        otpDialog.show();

    }


    @Override
    public void onResume() {
        super.onResume();

    }
}