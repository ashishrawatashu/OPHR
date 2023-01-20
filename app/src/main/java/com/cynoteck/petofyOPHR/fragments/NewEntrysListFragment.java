package com.cynoteck.petofyOPHR.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.activities.PdfEditorActivity;
import com.cynoteck.petofyOPHR.activities.ReportsCommonActivity;
import com.cynoteck.petofyOPHR.adapters.HospitalizationReportsAdapter;
import com.cynoteck.petofyOPHR.adapters.LabTestReportsAdapter;
import com.cynoteck.petofyOPHR.adapters.NewEntrysAdapter;
import com.cynoteck.petofyOPHR.adapters.ReportsTypeAdapter;
import com.cynoteck.petofyOPHR.adapters.TestAndXRayAdpater;
import com.cynoteck.petofyOPHR.adapters.VisitTypesAdapter;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.getPetListRequest.GetPetListParams;
import com.cynoteck.petofyOPHR.params.getPetListRequest.GetPetListRequest;
import com.cynoteck.petofyOPHR.params.immunizationRequest.ImmunizationParams;
import com.cynoteck.petofyOPHR.params.immunizationRequest.ImmunizationRequest;
import com.cynoteck.petofyOPHR.params.petBreedRequest.BreedParams;
import com.cynoteck.petofyOPHR.params.petBreedRequest.BreedRequest;
import com.cynoteck.petofyOPHR.params.petReportsRequest.PetDataParams;
import com.cynoteck.petofyOPHR.params.petReportsRequest.VisitTypeData;
import com.cynoteck.petofyOPHR.params.petReportsRequest.VisitTypeRequest;
import com.cynoteck.petofyOPHR.response.addPet.breedResponse.BreedCatRespose;
import com.cynoteck.petofyOPHR.response.addPet.petAgeResponse.PetAgeValueResponse;
import com.cynoteck.petofyOPHR.response.addPet.petColorResponse.PetColorValueResponse;
import com.cynoteck.petofyOPHR.response.addPet.petSizeResponse.PetSizeValueResponse;
import com.cynoteck.petofyOPHR.response.getImmunizationReport.PetImmunizationRecordResponse;
import com.cynoteck.petofyOPHR.response.getLabTestReportResponse.getPetLabWorkListResponse.PetLabWorkList;
import com.cynoteck.petofyOPHR.response.getLabTestReportResponse.getPetLabWorkListResponse.PetLabWorkResponse;
import com.cynoteck.petofyOPHR.response.getPetDetailsResponse.GetPetResponse;
import com.cynoteck.petofyOPHR.response.getPetHospitalizationResponse.getHospitalizationListResponse.GetPetHospitalizationResponse;
import com.cynoteck.petofyOPHR.response.getPetHospitalizationResponse.getHospitalizationListResponse.PetHospitalizationsList;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.GetReportsTypeData;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.GetReportsTypeResponse;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.getPetClinicVisitsListsResponse.GetPetClinicVisitListResponse;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.getPetClinicVisitsListsResponse.PetClinicVisitList;
import com.cynoteck.petofyOPHR.response.getXRayReports.getPetTestAndXRayResponse.GetPetTestAndXRayResponse;
import com.cynoteck.petofyOPHR.response.getXRayReports.getPetTestAndXRayResponse.PetTestsAndXrayList;
import com.cynoteck.petofyOPHR.response.recentEntrys.RecentEntrysResponse;
import com.cynoteck.petofyOPHR.response.updateProfileResponse.PetTypeResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.cynoteck.petofyOPHR.utils.NewEntryListClickListener;
import com.cynoteck.petofyOPHR.utils.RegisterRecyclerViewClickListener;
import com.cynoteck.petofyOPHR.utils.ViewAndUpdateClickListener;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import retrofit2.Response;

public class NewEntrysListFragment extends Fragment implements ApiResponse, RegisterRecyclerViewClickListener, ViewAndUpdateClickListener, NewEntryListClickListener, View.OnClickListener {
    String pet_unique_id, pet_name,pet_sex,pet_age, pet_owner_name,pet_owner_contact,pet_id ,report_type_id,type;

    RecyclerView routine_report_RV;
    View view;
    private ArrayList<PetClinicVisitList> petClinicVisitListArrayList;
    private ArrayList<PetTestsAndXrayList> petTestsAndXrayLists;
    private ArrayList<PetLabWorkList> petLabWorkLists;
    private ArrayList<PetHospitalizationsList> petHospitalizationsLists;
    private List<com.cynoteck.petofyOPHR.response.recentEntrys.PetClinicVisitList> petClinicVisitLists;
    ArrayList<GetReportsTypeData> getReportsTypeData;
    WebView webview;
    VisitTypesAdapter visitTypesAdapter;

    Dialog  prescription_dialog, editPetDilog;
    TextView parent_name,specilist,email,for_a,age,sex,date,prnt_nm,temparature,symptoms,diagnosis,
            remarks,nxt_visit,peto_reg_number_edit_dialog,calenderTextView_edit_dialog;

    Button crrete_pdf,cancel,save_changes_edit_dialog,
            cancel_edit_dialog;

    String getStrSpnerItemPetNmId="",strSpnrBreedId="",strSpnrAgeId="",strSpnrColorId="",
            strSpneSizeId="",strSpnrSexId="",strSpnerItemPetType="",strSpnrBreed="",strSpnrAge="",strSpnrSex="",
            currentDateandTime="",petIdGetForUpdate="",strResponseOtp="",petId="",petParentContactNumber="",
            pet_DOB="",pet_encrypted_id="";

    AppCompatSpinner add_pet_type_edit_dialog,
            add_pet_age_edit_dialog,add_pet_sex_edit_dialog,add_pet_breed_edit_dialog,add_pet_color_edit_dialog,add_pet_size_edit_dialog;

    EditText pet_name_edit_dialog,pet_parent_name_edit_dialog,pet_contact_number_edit_dialog;

    ReportsTypeAdapter reportsTypeAdapter;
    TestAndXRayAdpater testAndXRayAdpater;
    LabTestReportsAdapter labTestReportsAdapter;
    HospitalizationReportsAdapter hospitalizationReportsAdapter;
    NewEntrysAdapter newEntrysAdapter;
    Methods methods;

    DatePickerDialog picker;
    ArrayList<String> petType;
    ArrayList<String> petBreed;
    ArrayList<String> petAge;
    ArrayList<String> petColor;
    ArrayList<String> petSize;
    ArrayList<String> petSex;

    HashMap<String,String> petTypeHashMap=new HashMap<>();
    HashMap<String,String> petBreedHashMap=new HashMap<>();
    HashMap<String,String> petAgeHashMap=new HashMap<>();
    HashMap<String,String> petColorHashMap=new HashMap<>();
    HashMap<String,String> petSizeHashMap=new HashMap<>();
    HashMap<String,String> petSexHashMap=new HashMap<>();
    HashMap<String,String> petExistingSearch=new HashMap<>();
    public NewEntrysListFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_new_entrys_list, container, false);
        webview = view.findViewById(R.id.webview);
        methods=new Methods(getActivity());
        Bundle extras = this.getArguments();
        report_type_id = extras.getString("reports_id");
        pet_id = extras.getString("pet_id");
        Log.d("kakkaak",""+pet_id);
        pet_owner_contact = extras.getString("pet_owner_contact");
        pet_owner_name = extras.getString("pet_owner_name");
        pet_sex = extras.getString("pet_sex");
        pet_age = extras.getString("pet_age");
        pet_name = extras.getString("pet_name");
        pet_unique_id = extras.getString("pet_unique_id");
        type=extras.getString("type");
        pet_DOB=extras.getString("pet_DOB");
        pet_encrypted_id=extras.getString("pet_encrypted_id");



        routine_report_RV = view.findViewById(R.id.routine_report_RV);

        petSex=new ArrayList<>();
        petSex.add("Pet Sex");
        petSex.add("Male");
        petSex.add("Female");

        petSexHashMap.put("Pet Sex","0");
        petSexHashMap.put("Male","1");
        petSexHashMap.put("Female","2");

        if (methods.isInternetOn()){
            petType();
            getPetBreed();
            getPetAge();
            getPetColor();
            getPetSize();
        }else {

            methods.DialogInternet();
        }

        switch (type){

            case "list":
                getVisitTypes();
                break;

            case "XRay":
                getXrayReport();
                break;

            case "LabTest":
                getLabTestReport();
                break;

            case "Hospitalization":
                getHospitalizationReport();
                break;

            case "RecentVisit":
                getPetNewList();
                break;

            case "petHistory":
                getPetClinicVisit();
                break;
        }

        return view;
    }

    private void getVisitTypes() {
        ApiService<GetReportsTypeResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getReportsType(Config.token), "GetReportsType");

    }

    private void petType() {
        ApiService<PetTypeResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().petTypeApi(), "GetPetTypes");
    }

    private void getPetBreed() {
        BreedRequest breedRequest = new BreedRequest();
        breedRequest.setGetAll("true");
        breedRequest.setPetCategoryId("1");
        BreedParams breedParams = new BreedParams();
        breedParams.setData(breedRequest);

        ApiService<BreedCatRespose> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getGetPetBreedApi(breedParams), "GetPetBreed");
    }

    private void getPetAge() {
        BreedRequest breedRequest = new BreedRequest();
        breedRequest.setGetAll("true");
        breedRequest.setPetCategoryId("1");
        BreedParams breedParams = new BreedParams();
        breedParams.setData(breedRequest);

        ApiService<PetAgeValueResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getGetPetAgeApi(breedParams), "GetPetAge");
    }

    private void getPetColor() {
        BreedRequest breedRequest = new BreedRequest();
        breedRequest.setGetAll("true");
        breedRequest.setPetCategoryId("1");
        BreedParams breedParams = new BreedParams();
        breedParams.setData(breedRequest);

        ApiService<PetColorValueResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getGetPetColorApi(breedParams), "GetPetColor");
    }

    private void getPetSize() {
        BreedRequest breedRequest = new BreedRequest();
        breedRequest.setGetAll("true");
        breedRequest.setPetCategoryId("1");
        BreedParams breedParams = new BreedParams();
        breedParams.setData(breedRequest);

        ApiService<PetSizeValueResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getGetPetSizeApi(breedParams), "GetPetSize");
    }

    private void getHospitalizationReport() {
        VisitTypeRequest visitTypeRequest = new VisitTypeRequest();
        PetDataParams petDataParams = new PetDataParams();
        petDataParams.setPageNumber(1);
        petDataParams.setPageSize(10000);
        petDataParams.setSearch_Data("");
        VisitTypeData visitTypeData = new VisitTypeData();
        visitTypeData.setVisitType(report_type_id);
        visitTypeData.setPetId(pet_id);
        visitTypeRequest.setHeader(petDataParams);
        visitTypeRequest.setData(visitTypeData);
        Log.d("HospitalizationRequest",visitTypeRequest.toString());


        ApiService<GetPetHospitalizationResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getPetHospitalization(Config.token,visitTypeRequest), "GetHospitalization");


    }

    private void getLabTestReport() {

        VisitTypeRequest visitTypeRequest = new VisitTypeRequest();
        PetDataParams petDataParams = new PetDataParams();
        petDataParams.setPageNumber(1);
        petDataParams.setPageSize(10000);
        petDataParams.setSearch_Data("");
        VisitTypeData visitTypeData = new VisitTypeData();
        visitTypeData.setVisitType(report_type_id);
        visitTypeData.setPetId(pet_id);
        visitTypeRequest.setHeader(petDataParams);
        visitTypeRequest.setData(visitTypeData);
        Log.d("LabTestRequest",visitTypeRequest.toString());


        ApiService<PetLabWorkResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getPetLabWorkResponse(Config.token,visitTypeRequest), "GetLabTest");


    }

    private void getPetClinicVisit() {

        VisitTypeRequest visitTypeRequest = new VisitTypeRequest();
        PetDataParams petDataParams = new PetDataParams();
        petDataParams.setPageNumber(1);
        petDataParams.setPageSize(10000);
        petDataParams.setSearch_Data("");
        VisitTypeData visitTypeData = new VisitTypeData();
        visitTypeData.setVisitType(report_type_id);
        visitTypeData.setPetId(pet_id);
        visitTypeRequest.setHeader(petDataParams);
        visitTypeRequest.setData(visitTypeData);
        Log.d("visitTypeRequest",visitTypeRequest.toString());


        ApiService<GetPetClinicVisitListResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getPetClinicVisits(Config.token,visitTypeRequest), "GetPetClinicVisit");
    }

    private void getPetNewList() {
        String input= Config.token.trim();
        Log.d("lalalall",""+input);
        ApiService<RecentEntrysResponse> service = new ApiService<>();
        service.get( this, ApiClient.getApiInterface().getRecentClientcVisits(input), "GetRecentClinicVisits");
    }


    private void getXrayReport() {


        PetDataParams getPetDataParams = new PetDataParams();
        getPetDataParams.setPageNumber(1);
        getPetDataParams.setPageSize(10000);
        getPetDataParams.setSearch_Data("");
        VisitTypeData visitTypeData = new VisitTypeData();
        visitTypeData.setPetId(pet_id);
        VisitTypeRequest visitTypeRequest = new VisitTypeRequest();
        visitTypeRequest.setHeader(getPetDataParams);
        visitTypeRequest.setData(visitTypeData);


        ApiService<GetPetTestAndXRayResponse> service = new ApiService<>();
        service.get( this, ApiClient.getApiInterface().getPetTestAndXRay(Config.token,visitTypeRequest), "GetPetTestAndXRay");
        Log.e("DATALOG","GetPetTestAndXRay_Request=> "+visitTypeRequest);


    }
    @Override
    public void onResponse(Response response, String key) {

        switch (key){

            case "GetImmunization":
                try {
                    Log.d("GetImmunization", response.body().toString());
                    PetImmunizationRecordResponse immunizationRecordResponse = (PetImmunizationRecordResponse) response.body();
                    methods.customProgressDismiss();
                    int responseCode = Integer.parseInt(immunizationRecordResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        if (immunizationRecordResponse.getData().equals("")) {
                            // methods.customProgressDismiss();
                            Toast.makeText(getContext(), "No Record Found !", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.d("encryptId",immunizationRecordResponse.getData());
                            Intent lastPrescriptionIntent = new Intent(getContext(), PdfEditorActivity.class);
                            lastPrescriptionIntent.putExtra("encryptId",immunizationRecordResponse.getData());
                            startActivity(lastPrescriptionIntent);
                        }

                    }  else {
                        Toast.makeText(getContext(), "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "GetReportsType":
                try {
                    Log.d("GetPetServiceTypes",response.body().toString());
                    GetReportsTypeResponse petServiceResponse = (GetReportsTypeResponse) response.body();
                    int responseCode = Integer.parseInt(petServiceResponse.getResponse().getResponseCode());
                    if (responseCode== 109){

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        routine_report_RV.setLayoutManager(linearLayoutManager);
                        routine_report_RV.setNestedScrollingEnabled(false);
                        visitTypesAdapter  = new VisitTypesAdapter(getContext(),petServiceResponse.getData(),this);
                        getReportsTypeData = petServiceResponse.getData();
                        routine_report_RV.setAdapter(visitTypesAdapter);
                        visitTypesAdapter.notifyDataSetChanged();
                        routine_report_RV.setVisibility(View.VISIBLE);
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case "GetPetClinicVisit":
                try {
                    Log.d("ResponseClinicVisit",response.body().toString());
                    GetPetClinicVisitListResponse petServiceResponse = (GetPetClinicVisitListResponse) response.body();
                    int responseCode = Integer.parseInt(petServiceResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        petClinicVisitListArrayList = petServiceResponse.getData().getPetClinicVisitList();
                        routine_report_RV.setLayoutManager(linearLayoutManager);
                        routine_report_RV.setNestedScrollingEnabled(false);
                        reportsTypeAdapter  = new ReportsTypeAdapter(getContext(),petServiceResponse.getData().getPetClinicVisitList(),this);
                        routine_report_RV.setAdapter(reportsTypeAdapter);
                        reportsTypeAdapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;

            case "GetPetTestAndXRay":
                try {
                    Log.d("GetPetTestAndXRay","GetPetTestAndXRay=> "+(response.body()));
                    GetPetTestAndXRayResponse getPetTestAndXRayResponse = (GetPetTestAndXRayResponse) response.body();
                    int responseCode = Integer.parseInt(getPetTestAndXRayResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        petTestsAndXrayLists = getPetTestAndXRayResponse.getData().getPetTestsAndXrayList();

                        routine_report_RV.setLayoutManager(linearLayoutManager);
                        routine_report_RV.setNestedScrollingEnabled(false);
                        testAndXRayAdpater   = new TestAndXRayAdpater(getContext(),getPetTestAndXRayResponse.getData().getPetTestsAndXrayList(),this);
                        routine_report_RV.setAdapter(testAndXRayAdpater);
                        testAndXRayAdpater.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Sucess", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;

            case "GetLabTest":
                try {
                    Log.d("GetLabTest","GetLabTest=> "+(response.body()));
                    PetLabWorkResponse petLabWorkResponse = (PetLabWorkResponse) response.body();
                    int responseCode = Integer.parseInt(petLabWorkResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        petLabWorkLists = petLabWorkResponse.getData().getPetLabWorkList();

                        routine_report_RV.setLayoutManager(linearLayoutManager);
                        routine_report_RV.setNestedScrollingEnabled(false);
                        labTestReportsAdapter   = new LabTestReportsAdapter(getContext(),petLabWorkResponse.getData().getPetLabWorkList(),this);
                        routine_report_RV.setAdapter(labTestReportsAdapter);
                        labTestReportsAdapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Sucess", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;


            case "GetHospitalization":
                try {
                    Log.d("GetHospitalization","GetHospitalization=> "+(response.body()));
                    GetPetHospitalizationResponse getPetHospitalizationResponse = (GetPetHospitalizationResponse) response.body();
                    int responseCode = Integer.parseInt(getPetHospitalizationResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        petHospitalizationsLists = getPetHospitalizationResponse.getData().getPetHospitalizationsList();

                        routine_report_RV.setLayoutManager(linearLayoutManager);
                        routine_report_RV.setNestedScrollingEnabled(false);
                        hospitalizationReportsAdapter   = new HospitalizationReportsAdapter(getContext(),getPetHospitalizationResponse.getData().getPetHospitalizationsList(),this);
                        routine_report_RV.setAdapter(hospitalizationReportsAdapter);
                        hospitalizationReportsAdapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Sucess", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;

            case "GetRecentClinicVisits":
                try {
                    RecentEntrysResponse getPetListResponse = (RecentEntrysResponse) response.body();
                    Log.d("GetRecentClinicVisits", getPetListResponse.toString());
                    int responseCode = Integer.parseInt(getPetListResponse.getResponse().getResponseCode());

                    if (responseCode== 109){
                        Log.e("lallalal",""+getPetListResponse.getData().getPetClinicVisitList().size());
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                        routine_report_RV.setLayoutManager(linearLayoutManager);
                        newEntrysAdapter  = new NewEntrysAdapter(getActivity(),getPetListResponse.getData().getPetClinicVisitList(),this);
                        routine_report_RV.setAdapter(newEntrysAdapter);
                        petClinicVisitLists = getPetListResponse.getData().getPetClinicVisitList();
                        newEntrysAdapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();

                    }

                }
                catch(Exception e) {
                    e.printStackTrace();
                }

            case "GetPetTypes":
                try {
                    Log.d("GetPetTypes",response.body().toString());
                    PetTypeResponse petTypeResponse = (PetTypeResponse) response.body();
                    int responseCode = Integer.parseInt(petTypeResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        petType=new ArrayList<>();
                        petType.add("Select Pet Type");
                        Log.d("lalal",""+petTypeResponse.getData().size());
                        for(int i=0; i<petTypeResponse.getData().size(); i++){
                            Log.d("petttt",""+petTypeResponse.getData().get(i).getPetType1());
                            petType.add(petTypeResponse.getData().get(i).getPetType1());
                            petTypeHashMap.put(petTypeResponse.getData().get(i).getPetType1(),petTypeResponse.getData().get(i).getId());
                        }


                    }else if (responseCode==614){
                        Toast.makeText(getActivity(), petTypeResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(), "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }

            case "GetPetBreed":
                try {
                    Log.d("GetPetBreed",response.body().toString());
                    BreedCatRespose breedCatRespose = (BreedCatRespose) response.body();
                    int responseCode = Integer.parseInt(breedCatRespose.getResponse().getResponseCode());
                    if (responseCode== 109){
                        petBreed=new ArrayList<>();
                        petBreed.add("Pet Breed");
                        Log.d("lalal",""+breedCatRespose.getData().size());
                        for(int i=0; i<breedCatRespose.getData().size(); i++){
                            Log.d("petttt",""+breedCatRespose.getData().get(i).getBreed());
                            petBreed.add(breedCatRespose.getData().get(i).getBreed());
                            petBreedHashMap.put(breedCatRespose.getData().get(i).getBreed(),breedCatRespose.getData().get(i).getId());
                        }

                    }else if (responseCode==614){
                        Toast.makeText(getActivity(), breedCatRespose.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(), "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case "GetPetAge":
                try {
                    Log.d("GetPetAge",response.body().toString());
                    PetAgeValueResponse petAgeValueResponse = (PetAgeValueResponse) response.body();
                    int responseCode = Integer.parseInt(petAgeValueResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        petAge=new ArrayList<>();
                        petAge.add("Select Pet Age");
                        Log.d("lalal",""+petAgeValueResponse.getData().size());
                        for(int i=0; i<petAgeValueResponse.getData().size(); i++){
                            Log.d("petttt",""+petAgeValueResponse.getData().get(i).getAge());
                            petAge.add(petAgeValueResponse.getData().get(i).getAge());
                            petAgeHashMap.put(petAgeValueResponse.getData().get(i).getAge(),petAgeValueResponse.getData().get(i).getId());
                        }

                    }else if (responseCode==614){
                        Toast.makeText(getActivity(), petAgeValueResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(), "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;

            case "GetPetColor":
                try {
                    Log.d("GetPetColor",response.body().toString());
                    PetColorValueResponse petColorValueResponse = (PetColorValueResponse) response.body();
                    int responseCode = Integer.parseInt(petColorValueResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        petColor=new ArrayList<>();
                        petColor.add("Pet Color");
                        Log.d("lalal",""+petColorValueResponse.getData().size());
                        for(int i=0; i<petColorValueResponse.getData().size(); i++){
                            Log.d("petttt",""+petColorValueResponse.getData().get(i).getColor());
                            petColor.add(petColorValueResponse.getData().get(i).getColor());
                            petColorHashMap.put(petColorValueResponse.getData().get(i).getColor(),petColorValueResponse.getData().get(i).getId());
                        }
                    }else if (responseCode==614){
                        Toast.makeText(getActivity(), petColorValueResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(), "Please Try Again !", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                break;
            case "GetPetSize":
                try {
                    Log.d("GetPetSize",response.body().toString());
                    PetSizeValueResponse petSizeValueResponse = (PetSizeValueResponse) response.body();
                    int responseCode = Integer.parseInt(petSizeValueResponse.getResponse().getResponseCode());
                    if (responseCode== 109){
                        petSize=new ArrayList<>();
                        petSize.add("Pet Size");
                        Log.d("lalal",""+petSizeValueResponse.getData().size());
                        for(int i=0; i<petSizeValueResponse.getData().size(); i++){
                            Log.d("petttt",""+petSizeValueResponse.getData().get(i).getSize());
                            petSize.add(petSizeValueResponse.getData().get(i).getSize());
                            petSizeHashMap.put(petSizeValueResponse.getData().get(i).getSize(),petSizeValueResponse.getData().get(i).getId());
                        }

                    }else if (responseCode==614){
                        Toast.makeText(getActivity(), petSizeValueResponse.getResponse().getResponseMessage(), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(), "Please Try Again !", Toast.LENGTH_SHORT).show();
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
    public void onProductClick(int position) {
        getReportsTypeData.get(position).getId();
//        Toast.makeText(getContext(), ""+getReportsTypeData.get(position).getId(), Toast.LENGTH_SHORT).show();
        if (getReportsTypeData.get(position).getId().equals("4.0")){
            methods.showCustomProgressBarDialog(getActivity());
            ImmunizationParams immunizationParams = new ImmunizationParams();
            immunizationParams.setEncryptedId(pet_encrypted_id);
            ImmunizationRequest immunizationRequest = new ImmunizationRequest();
            immunizationRequest.setData(immunizationParams);

            ApiService<PetImmunizationRecordResponse> service = new ApiService<>();
            service.get(this, ApiClient.getApiInterface().viewPetVaccination(Config.token,immunizationRequest), "GetImmunization");
            Log.d("GetImmunization",immunizationRequest.toString());

        }else {
            Intent selectReportsIntent = new Intent(getContext(), ReportsCommonActivity.class);
            Bundle data = new Bundle();
            data.putString("pet_id", pet_id);
            data.putString("pet_name", pet_name);
            data.putString("pet_unique_id", pet_unique_id);
            data.putString("pet_sex", pet_sex);
            data.putString("pet_owner_name", pet_owner_name);
            data.putString("pet_owner_contact", pet_owner_contact);
            data.putString("reports_id", getReportsTypeData.get(position).getId());
            data.putString("pet_DOB", pet_DOB);
            data.putString("pet_encrypted_id", pet_encrypted_id);
            data.putString("button_type", "update");
            selectReportsIntent.putExtras(data);
            Log.e("valueeeee", data.toString());
            startActivity(selectReportsIntent);
            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        }
    }

    @Override
    public void onProductPrescriptionClick(int position) {
        showEditStaffDialog(petClinicVisitLists.get(position).getVeterinarian(),petClinicVisitLists.get(position).getCreatedByUser().getEmail(),petClinicVisitLists.get(position).getPetName(),petClinicVisitLists.get(position).getPetAge(),petClinicVisitLists.get(position).getPetSex(),petClinicVisitLists.get(position).getDateOfOnset(),petClinicVisitLists.get(position).getPetParentName(),petClinicVisitLists.get(position).getTemperature(),petClinicVisitLists.get(position).getDiagnosisProcedure(),petClinicVisitLists.get(position).getTreatmentRemarks(),petClinicVisitLists.get(position).getFollowUpDate());
    }

    @Override
    public void onProductDownloadClick(int position) {
        createPdf(petClinicVisitLists.get(position).getVeterinarian(),petClinicVisitLists.get(position).getCreatedByUser().getEmail(),petClinicVisitLists.get(position).getPetName(),petClinicVisitLists.get(position).getPetAge(),petClinicVisitLists.get(position).getPetSex(),petClinicVisitLists.get(position).getDateOfOnset(),petClinicVisitLists.get(position).getPetParentName(),petClinicVisitLists.get(position).getTemperature(),petClinicVisitLists.get(position).getDiagnosisProcedure(),petClinicVisitLists.get(position).getTreatmentRemarks(),petClinicVisitLists.get(position).getFollowUpDate());
    }

    public void createPdf(String veterian, String strEmail, String strForA, String strAge,String strSex, String strDate,String strParntNm, String strTemparature, String strDiagnosis,String strRemark, String strNxtVisit)
    {
        String care="Aviral Care";
        String pet_parent="Pramod Rana";
        String Symptons="Problems";
        String address="Dehradun";
        String registration_number="VET-00987";
        String str="<!DOCTYPE html>\n"+
                "<html>\n" +
                "<head>\n" +
                "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n" +
                "<script src=\"//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js\"></script>\n" +
                "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n" +
                "    \n" +
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css\">\n" +
                "\n" +
                "    <title>Invioce</title>\n" +
                "</head>\n" +
                "<style type=\"text/css\">\n" +
                "    .invoice-title h2, .invoice-title h3 {\n" +
                "        display: inline-block;\n" +
                "    }\n" +
                "\n" +
                "    .table > tbody > tr > .no-line {\n" +
                "        border-top: none;\n" +
                "    }\n" +
                "\n" +
                "    .table > thead > tr > .no-line {\n" +
                "        border-bottom: none;\n" +
                "    }\n" +
                "\n" +
                "    .table > tbody > tr > .thick-line {\n" +
                "        border-top: 2px solid;\n" +
                "    }\n" +
                "    @page {\n" +
                "      size: A4;\n" +
                "      margin: 15px;\n" +
                "    }\n" +
                "</style>\n" +
                "\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <p><?=date('d/m/Y')?></p> \n" +
                "    <div class=\"row\">\n" +
                "        <div class=\"col-xs-12\">\n" +
                "            <div class=\"invoice-title \">\n" +
                "                <div class=\"row\">\n" +
                "                    <div class=\"col-lg-12 col-md-12 col-xs-12\" style=\"font-size: 25px;font-family: cizel;\">\n" +
                "                       <b>"+Config.user_Veterian_name+"</b> \n" +
                "                    </div>\n" +
                "                    <div class=\"col-lg-12 col-md-12 col-xs-12\" style=\"font-size: 20px; margin-bottom: 20px;\">\n" +
                "                        MBBS,MVS \n" +
                "                    </div>\n" +
                "                    <div class=\"col-lg-6 col-md-6 col-xs-6\" style=\"font-size: 20px; \" >\n" +care+
                "                       \n" +
                "                    </div>\n" +
                "                    <div class=\"col-lg-6 col-md-6 col-xs-6 text-right\" style=\"font-size: 20px;\">\n" +
                "                       <b> Mobile :"+Config.user_Veterian_phone+" </b>\n" +
                "                    </div>\n" +
                "                    \n" +
                "                    <div class=\"col-lg-6 col-md-6 col-xs-6\" style=\"font-size: 17px;\">\n" +
                "                       <b> Email: "+Config.user_Veterian_emial+" </b>\n" +
                "                    </div>\n" +
                "                    <div class=\"col-lg-6 col-md-6 col-xs-6 text-right\" style=\"font-size: 20px;\">\n" +
                "                       <b> "+Config.user_Veterian_phone+"</b>\n" +
                "                    </div>\n" +
                "                 \n" +
                "                    \n" +
                "                </div>\n" +
                "               \n" +
                "                \n" +
                "            </div>\n" +
                "            <div class=\"row\">\n" +
                "                <div class=\"col-md-12\" style=\"border: 1px solid black;\"></div>\n" +
                "            </div>\n" +
                "            <div class=\"row\">\n" +
                "                <div class=\"col-xs-3\" style=\"font-size: 20px;\">\n" +
                "                    <b>For a: "+strForA+"</b>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-3\" style=\"font-size: 20px;\">\n" +
                "                    <b>Age: "+strAge+"</b>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-3\" style=\"font-size: 20px;\">\n" +
                "                    <b>Sex: "+strSex+"</b>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-3\" style=\"font-size: 20px; margin-bottom: 25px;\">\n" +
                "                    <b>Date: <?=date('d/m/Y')?></b>\n" +
                "                </div>\n" +
                "\n" +
                "\n" +
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 25px;\">\n" +
                "                    <b>Pet Parant Name:"+pet_parent+"</b>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 10px;\">\n" +
                "                    <b>Temparature(F): "+strTemparature+"</b>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 25px;\">\n" +
                "                    <b>Symptons:</b>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-12\" style=\"margin-bottom: 10px;\">\n" +
                "                    <p>"+Symptons+"</p>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 25px;\">\n" +
                "                    <b>Diagnosis:</b>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 10px;\">\n" +
                "                    <p>"+strDiagnosis+"</p>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 25px;\">\n" +
                "                    <b>Treatment Remarks:</b>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 10px;\">\n" +
                "                    <p>"+strRemark+"</p>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 25px;\">\n" +
                "                    <b>Next Visit:</b>\n" +
                "                </div>\n" +
                "                <div class=\"col-xs-12\" style=\"font-size: 20px; margin-bottom: 10px;\">\n" +
                "                    <p>"+strNxtVisit+"</p>\n" +
                "                </div>\n" +
                "\n" +
                "            </div><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>\n" +
                "            <div class=\"col-md-12\" style=\"border: 1px solid black;\"></div>\n" +
                "            <div class=\"col-md-12\" style=\"font-size: 25px; text-align: center;\">Address: "+address+", Registration Number: "+registration_number+"</div>\n" +
                "            \n" +
                "        </div>\n" +
                "\n" +
                "\n" +
                "    </div>\n" +
                "\n" +
                "</div>\n" +
                "<script type=\"text/javascript\">\n" +
                "    $(function(){\n" +
                "        window.print();\n" +
                "        window.close();\n" +
                "    });\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>";
        webview.loadDataWithBaseURL(null,str,"text/html","utf-8",null);
        Context context=getActivity();
        PrintManager printManager=(PrintManager)getActivity().getSystemService(context.PRINT_SERVICE);
        PrintDocumentAdapter adapter=null;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            adapter=webview.createPrintDocumentAdapter();
        }
        String JobName=getString(R.string.app_name) +"Document";
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            PrintJob printJob=printManager.print(JobName,adapter,new PrintAttributes.Builder().build());
        }
    }
    @Override
    public void onProductEditClick(int position) {
        editPrescriptionDialog(petClinicVisitLists.get(position).getId());
    }


    @Override
    public void onViewClick(int position) {

    }

    @Override
    public void onUpdateClick(int position) {

    }

    @Override
    public void onViewXrayClick(int position) {

    }

    @Override
    public void onViewLabTestReportsClick(int position) { }

    @Override
    public void onViewHospitalizationClick(int position) { }

    @Override
    public void onUpdateXrayClick(int position) {

    }

    @Override
    public void onUpdateLabTestReportsClick(int position) {

    }

    @Override
    public void onUpdateHospitalizationClick(int position) {

    }

    private void getPetlistData(GetPetListRequest getPetListRequest) {
        ApiService<GetPetResponse> service = new ApiService<>();
        service.get( this, ApiClient.getApiInterface().getPetDetails(Config.token,getPetListRequest), "GetPetDetail");
        Log.e("DATALOG","check1=> "+getPetListRequest);
    }

    private void showEditStaffDialog(final String veterian, final String strEmail, final String strForA, final String strAge, final String strSex, final String strDate, final String strParntNm, final String strTemparature, final String strDiagnosis, final String strRemark, final String strNxtVisit) {

        prescription_dialog = new Dialog(getContext());
        prescription_dialog.setContentView(R.layout.precription_layout);

        parent_name=prescription_dialog.findViewById(R.id.parent_name);
        specilist=prescription_dialog.findViewById(R.id.specilist);
        email=prescription_dialog.findViewById(R.id.email);
        for_a=prescription_dialog.findViewById(R.id.for_a);
        age=prescription_dialog.findViewById(R.id.age);
        sex=prescription_dialog.findViewById(R.id.sex);
        date=prescription_dialog.findViewById(R.id.date);
        prnt_nm=prescription_dialog.findViewById(R.id.prnt_nm);
        temparature=prescription_dialog.findViewById(R.id.temparature);
        symptoms=prescription_dialog.findViewById(R.id.symptoms);
        diagnosis=prescription_dialog.findViewById(R.id.diagnosis);
        remarks=prescription_dialog.findViewById(R.id.remarks);
        nxt_visit=prescription_dialog.findViewById(R.id.nxt_visit);

        crrete_pdf=prescription_dialog.findViewById(R.id.crrete_pdf);
        cancel=prescription_dialog.findViewById(R.id.cancel);


        cancel.setOnClickListener(this);

        parent_name.setText(veterian);
        //specilist.setText(strSpecialist);
        email.setText(strEmail);
        for_a.setText(strForA);
        age.setText(strAge);
        sex.setText(strSex);
        date.setText(strDate);
        prnt_nm.setText(strParntNm);
        temparature.setText(strTemparature);
        //symptoms.setText(strSymptoms);
        diagnosis.setText(strDiagnosis);
        remarks.setText(strRemark);
        nxt_visit.setText(strNxtVisit);


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = prescription_dialog.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        prescription_dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        prescription_dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        prescription_dialog.show();

    }
    public void editPrescriptionDialog(String petId)
    {
        editPetDilog = new Dialog(getContext());
        editPetDilog.setContentView(R.layout.edit_pet_layout);
        petIdGetForUpdate=petId;

        add_pet_type_edit_dialog=editPetDilog.findViewById(R.id.add_edit_pet_type);
        add_pet_age_edit_dialog=editPetDilog.findViewById(R.id.add_edit_pet_age_dialog);
        add_pet_sex_edit_dialog=editPetDilog.findViewById(R.id.add_edit_pet_sex_dialog);
        add_pet_breed_edit_dialog=editPetDilog.findViewById(R.id.add_edit_pet_breed_dialog);
        add_pet_color_edit_dialog=editPetDilog.findViewById(R.id.add_edit_pet_color_dialog);
        add_pet_size_edit_dialog=editPetDilog.findViewById(R.id.add_edit_pet_size_dialog);
        pet_name_edit_dialog=editPetDilog.findViewById(R.id.pet_edit_name_ET);
        pet_parent_name_edit_dialog=editPetDilog.findViewById(R.id.pet_edit_parent_name_ET);
        pet_contact_number_edit_dialog=editPetDilog.findViewById(R.id.pet_edit_contact_number);
        peto_reg_number_edit_dialog=editPetDilog.findViewById(R.id.peto_edit_reg_number_dialog);
        calenderTextView_edit_dialog=editPetDilog.findViewById(R.id.calenderTextView_edit_dialog);
        save_changes_edit_dialog=editPetDilog.findViewById(R.id.save_edit_changes_dialog);
        cancel_edit_dialog=editPetDilog.findViewById(R.id.cancel_edit_dialog);

        calenderTextView_edit_dialog.setOnClickListener(this);
        save_changes_edit_dialog.setOnClickListener(this);
        cancel_edit_dialog.setOnClickListener(this);
        StringTokenizer tokens = new StringTokenizer(petId, ".");
        String first = tokens.nextToken();
        GetPetListParams getPetListParams = new GetPetListParams();
        getPetListParams.setId(first);
        GetPetListRequest getPetListRequest = new GetPetListRequest();
        getPetListRequest.setData(getPetListParams);
        if(methods.isInternetOn())
        {
            getPetlistData(getPetListRequest);
        }
        else
        {
            methods.DialogInternet();
        }

        setSpinnerEditPetSex();
        setPetTypeEditSpinner();
        setPetBreeEditSpinner();
        setPetAgeEditSpinner();
        setPetColorEditSpinner();
        setPetSizeEditSpinner();



        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = editPetDilog.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        editPetDilog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        editPetDilog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        editPetDilog.show();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cancel:
                prescription_dialog.dismiss();
                break;
            case R.id.cancel_edit_dialog:
                editPetDilog.dismiss();
        }

    }

    private void setPetTypeEditSpinner() {
        ArrayAdapter aa = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,petType);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        add_pet_type_edit_dialog.setAdapter(aa);
        add_pet_type_edit_dialog.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                Log.d("spnerType",""+item);
                strSpnerItemPetType=item;
                getStrSpnerItemPetNmId=petTypeHashMap.get(item);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setPetBreeEditSpinner() {
        ArrayAdapter aa = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,petBreed);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        add_pet_breed_edit_dialog.setAdapter(aa);
        add_pet_breed_edit_dialog.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                Log.d("spnerType",""+item);
                strSpnrBreed=item;
                strSpnrBreedId=petBreedHashMap.get(item);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setPetAgeEditSpinner() {
        ArrayAdapter aa = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,petAge);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        add_pet_age_edit_dialog.setAdapter(aa);
        add_pet_age_edit_dialog.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                Log.d("spnerType",""+item);
                strSpnrAge=item;
                strSpnrAgeId=petAgeHashMap.get(item);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setPetColorEditSpinner() {
        ArrayAdapter aa = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,petColor);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        add_pet_color_edit_dialog.setAdapter(aa);
        add_pet_color_edit_dialog.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                Log.d("spnerType",""+item);
                strSpnrColorId=petColorHashMap.get(item);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setPetSizeEditSpinner() {
        ArrayAdapter aa = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,petSize);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        add_pet_size_edit_dialog.setAdapter(aa);
        add_pet_size_edit_dialog.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                Log.d("spnerType",""+item);
                strSpneSizeId=petSizeHashMap.get(item);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setSpinnerEditPetSex() {
        ArrayAdapter aa = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,petSex);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        add_pet_sex_edit_dialog.setAdapter(aa);
        add_pet_sex_edit_dialog.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                Log.d("spnerType",""+item);
                strSpnrSex=item;
                strSpnrSexId=petSexHashMap.get(item);
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}