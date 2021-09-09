package com.cynoteck.petofyOPHR.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.activities.AddClinicActivity;
import com.cynoteck.petofyOPHR.activities.AddHospitalizationDeatilsActivity;
import com.cynoteck.petofyOPHR.activities.AddLabWorkDeatilsActivity;
import com.cynoteck.petofyOPHR.activities.AddXRayDeatilsActivity;
import com.cynoteck.petofyOPHR.activities.HospitalizationDetailsActivity;
import com.cynoteck.petofyOPHR.activities.LabTestReportDeatilsActivity;
import com.cynoteck.petofyOPHR.activities.PdfEditorActivity;
import com.cynoteck.petofyOPHR.activities.ViewReportsDeatilsActivity;
import com.cynoteck.petofyOPHR.activities.XRayReportDeatilsActivity;
import com.cynoteck.petofyOPHR.adapters.HospitalizationReportsAdapter;
import com.cynoteck.petofyOPHR.adapters.LabTestReportsAdapter;
import com.cynoteck.petofyOPHR.adapters.ReportsTypeAdapter;
import com.cynoteck.petofyOPHR.adapters.TestAndXRayAdpater;
import com.cynoteck.petofyOPHR.adapters.UpdateClinicVisitAdapter;
import com.cynoteck.petofyOPHR.adapters.UpdateHospitalizationAdapter;
import com.cynoteck.petofyOPHR.adapters.UpdateLabTestAdpater;
import com.cynoteck.petofyOPHR.adapters.UpdateXRayAdpater;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.petReportsRequest.PetDataParams;
import com.cynoteck.petofyOPHR.params.petReportsRequest.VisitTypeData;
import com.cynoteck.petofyOPHR.params.petReportsRequest.VisitTypeRequest;
import com.cynoteck.petofyOPHR.response.getLabTestReportResponse.LabType;
import com.cynoteck.petofyOPHR.response.getLabTestReportResponse.getPetLabWorkListResponse.PetLabWorkList;
import com.cynoteck.petofyOPHR.response.getLabTestReportResponse.getPetLabWorkListResponse.PetLabWorkResponse;
import com.cynoteck.petofyOPHR.response.getPetHospitalizationResponse.HospitalizationType;
import com.cynoteck.petofyOPHR.response.getPetHospitalizationResponse.getHospitalizationListResponse.GetPetHospitalizationResponse;
import com.cynoteck.petofyOPHR.response.getPetHospitalizationResponse.getHospitalizationListResponse.PetHospitalizationsList;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.getPetClinicVisitsListsResponse.GetPetClinicVisitListResponse;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.getPetClinicVisitsListsResponse.PetClinicVisitList;
import com.cynoteck.petofyOPHR.response.getXRayReports.TypeOfTest;
import com.cynoteck.petofyOPHR.response.getXRayReports.getPetTestAndXRayResponse.GetPetTestAndXRayResponse;
import com.cynoteck.petofyOPHR.response.getXRayReports.getPetTestAndXRayResponse.PetTestsAndXrayList;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.cynoteck.petofyOPHR.utils.ViewAndUpdateClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */

public class ReportListFragment extends Fragment implements ApiResponse, ViewAndUpdateClickListener {

    String pet_DOB,pet_image_url,pet_unique_id, pet_name, pet_sex, pet_owner_name, pet_owner_contact, pet_id, report_type_id, type, button_type;

    RecyclerView routine_report_RV;
    View view;
    Context context;
    ImageView empty_IV;
    public ArrayList<PetClinicVisitList> petClinicVisitListArrayList;
    private ArrayList<PetTestsAndXrayList> petTestsAndXrayLists;
    private ArrayList<PetLabWorkList> petLabWorkLists;
    private ArrayList<PetHospitalizationsList> petHospitalizationsLists;
    ProgressBar progressBar;
    ReportsTypeAdapter reportsTypeAdapter;
    TestAndXRayAdpater testAndXRayAdpater;
    LabTestReportsAdapter labTestReportsAdapter;
    HospitalizationReportsAdapter hospitalizationReportsAdapter;
    UpdateClinicVisitAdapter updateClinicVisitAdapter;
    UpdateXRayAdpater updateXRayAdpater;
    UpdateLabTestAdpater updateLabTestAdpater;
    UpdateHospitalizationAdapter updateHospitalizationAdapter;
    Methods methods;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public ReportListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_report_list, container, false);
        methods = new Methods(getActivity());
        Bundle extras = this.getArguments();
        report_type_id = extras.getString("reports_id");
        pet_id = extras.getString("pet_id");
        pet_owner_contact = extras.getString("pet_owner_contact");
        pet_owner_name = extras.getString("pet_owner_name");
        pet_sex = extras.getString("pet_sex");
        pet_name = extras.getString("pet_name");
        pet_unique_id = extras.getString("pet_unique_id");
        type = extras.getString("type");
        button_type = extras.getString("button_type");
        pet_image_url = extras.getString("pet_image_url");
        pet_DOB = extras.getString("pet_DOB");
        routine_report_RV = view.findViewById(R.id.routine_report_RV);
        empty_IV = view.findViewById(R.id.empty_IV);
        progressBar = view.findViewById(R.id.progressBar);
        Log.e("petid",pet_id);


        switch (type) {

            case "list":
                getPetClinicVisit();
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
        }
        return view;
    }

    private void getHospitalizationReport() {
        VisitTypeRequest visitTypeRequest = new VisitTypeRequest();
        PetDataParams petDataParams = new PetDataParams();
        petDataParams.setPageNumber(1);
        petDataParams.setPageSize(10);
        petDataParams.setSearch_Data("");
        VisitTypeData visitTypeData = new VisitTypeData();
        visitTypeData.setVisitType(report_type_id);
        visitTypeData.setPetId(pet_id);
        visitTypeRequest.setHeader(petDataParams);
        visitTypeRequest.setData(visitTypeData);
        Log.e("Hospital",methods.getRequestJson(visitTypeRequest));


        ApiService<GetPetHospitalizationResponse> service = new ApiService<GetPetHospitalizationResponse>();
        service.get(this, ApiClient.getApiInterface().getPetHospitalization(Config.token, visitTypeRequest), "GetHospitalization");


    }

    private void getLabTestReport() {

        VisitTypeRequest visitTypeRequest = new VisitTypeRequest();
        PetDataParams petDataParams = new PetDataParams();
        petDataParams.setPageNumber(1);
        petDataParams.setPageSize(10);
        petDataParams.setSearch_Data("");
        VisitTypeData visitTypeData = new VisitTypeData();
        visitTypeData.setVisitType(report_type_id);
        visitTypeData.setPetId(pet_id);
        visitTypeRequest.setHeader(petDataParams);
        visitTypeRequest.setData(visitTypeData);
        Log.d("LabTestRequest", visitTypeRequest.toString());


        ApiService<PetLabWorkResponse> service = new ApiService<PetLabWorkResponse>();
        service.get(this, ApiClient.getApiInterface().getPetLabWorkResponse(Config.token, visitTypeRequest), "GetLabTest");


    }

    private void getPetClinicVisit() {

        VisitTypeRequest visitTypeRequest = new VisitTypeRequest();
        PetDataParams petDataParams = new PetDataParams();
        petDataParams.setPageNumber(1);
        petDataParams.setPageSize(10);
        petDataParams.setSearch_Data("");
        VisitTypeData visitTypeData = new VisitTypeData();
        visitTypeData.setVisitType(report_type_id);
        visitTypeData.setPetId(pet_id);
        visitTypeRequest.setHeader(petDataParams);
        visitTypeRequest.setData(visitTypeData);
        Log.d("visitTypeRequest",methods.getRequestJson(visitTypeRequest));


        ApiService<GetPetClinicVisitListResponse> service = new ApiService<GetPetClinicVisitListResponse>();
        service.get(this, ApiClient.getApiInterface().getPetClinicVisits(Config.token, visitTypeRequest), "GetPetClinicVisit");


    }

    private void getXrayReport() {

        PetDataParams getPetDataParams = new PetDataParams();
        getPetDataParams.setPageNumber(1);
        getPetDataParams.setPageSize(10);
        getPetDataParams.setSearch_Data("");
        VisitTypeData visitTypeData = new VisitTypeData();
        visitTypeData.setPetId(pet_id);
        VisitTypeRequest visitTypeRequest = new VisitTypeRequest();
        visitTypeRequest.setHeader(getPetDataParams);
        visitTypeRequest.setData(visitTypeData);

        ApiService<GetPetTestAndXRayResponse> service = new ApiService<GetPetTestAndXRayResponse>();
        service.get(this, ApiClient.getApiInterface().getPetTestAndXRay(Config.token, visitTypeRequest), "GetPetTestAndXRay");
        Log.e("GetPetTestAndXRay",methods.getRequestJson(visitTypeRequest));


    }

    @Override
    public void onResponse(Response response, String key) {
        progressBar.setVisibility(View.GONE);
        switch (key) {
            case "GetPetClinicVisit":
                try {
                    Log.d("ResponseClinicVisit", response.body().toString());
                    GetPetClinicVisitListResponse petServiceResponse = (GetPetClinicVisitListResponse) response.body();
                    int responseCode = Integer.parseInt(petServiceResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        if (petServiceResponse.getData().getPetClinicVisitList().isEmpty()) {
//                            Toast.makeText(getContext(), "Empty", Toast.LENGTH_SHORT).show();
                            empty_IV.setVisibility(View.VISIBLE);
                            routine_report_RV.setVisibility(View.GONE);
                        }
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        petClinicVisitListArrayList = petServiceResponse.getData().getPetClinicVisitList();
                        routine_report_RV.setLayoutManager(linearLayoutManager);
                        routine_report_RV.setNestedScrollingEnabled(false);

                        if (button_type.equals("view")) {
                            routine_report_RV.setVisibility(View.VISIBLE);
                            reportsTypeAdapter = new ReportsTypeAdapter(getContext(), petServiceResponse.getData().getPetClinicVisitList(), this);
                            routine_report_RV.setAdapter(reportsTypeAdapter);
                            reportsTypeAdapter.notifyDataSetChanged();
                        } else if (button_type.equals("update")) {
                            routine_report_RV.setVisibility(View.VISIBLE);
                            updateClinicVisitAdapter = new UpdateClinicVisitAdapter(getContext(), petServiceResponse.getData().getPetClinicVisitList(), this);
                            routine_report_RV.setAdapter(updateClinicVisitAdapter);
                            updateClinicVisitAdapter.notifyDataSetChanged();
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "GetPetTestAndXRay":
                try {
                    Log.d("GetPetTestAndXRay", "GetPetTestAndXRay=> " + (response.body()));
                    GetPetTestAndXRayResponse getPetTestAndXRayResponse = (GetPetTestAndXRayResponse) response.body();
                    int responseCode = Integer.parseInt(getPetTestAndXRayResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
//                        Toast.makeText(getContext(), "GetPetTestAndXRay", Toast.LENGTH_SHORT).show();
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        petTestsAndXrayLists = getPetTestAndXRayResponse.getData().getPetTestsAndXrayList();
                        routine_report_RV.setLayoutManager(linearLayoutManager);
                        routine_report_RV.setNestedScrollingEnabled(false);
                        if (getPetTestAndXRayResponse.getData().getPetTestsAndXrayList().isEmpty()) {
                            empty_IV.setVisibility(View.VISIBLE);
                            routine_report_RV.setVisibility(View.GONE);
                        } else if (button_type.equals("view")) {
                            routine_report_RV.setVisibility(View.VISIBLE);
                            testAndXRayAdpater = new TestAndXRayAdpater(getContext(), getPetTestAndXRayResponse.getData().getPetTestsAndXrayList(), this);
                            routine_report_RV.setAdapter(testAndXRayAdpater);
                            testAndXRayAdpater.notifyDataSetChanged();
                        } else if (button_type.equals("update")) {
                            routine_report_RV.setVisibility(View.VISIBLE);
                            Toast.makeText(getContext(), "Updated", Toast.LENGTH_SHORT).show();
                            updateXRayAdpater = new UpdateXRayAdpater(getContext(), getPetTestAndXRayResponse.getData().getPetTestsAndXrayList(), this);
                            routine_report_RV.setAdapter(updateXRayAdpater);
                            updateXRayAdpater.notifyDataSetChanged();
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "GetLabTest":
                try {
                    Log.d("GetLabTest", "GetLabTest=> " + (response.body()));
                    PetLabWorkResponse petLabWorkResponse = (PetLabWorkResponse) response.body();
                    int responseCode = Integer.parseInt(petLabWorkResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
//                        Toast.makeText(getContext(), "GetLabTest", Toast.LENGTH_SHORT).show();
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        petLabWorkLists = petLabWorkResponse.getData().getPetLabWorkList();
                        routine_report_RV.setLayoutManager(linearLayoutManager);
                        routine_report_RV.setNestedScrollingEnabled(false);
                        if (petLabWorkResponse.getData().getPetLabWorkList().isEmpty()) {
                            empty_IV.setVisibility(View.VISIBLE);
                            routine_report_RV.setVisibility(View.GONE);
                        } else if (button_type.equals("view")) {
                            empty_IV.setVisibility(View.GONE);
                            routine_report_RV.setVisibility(View.VISIBLE);
                            labTestReportsAdapter = new LabTestReportsAdapter(getContext(), petLabWorkResponse.getData().getPetLabWorkList(), this);
                            routine_report_RV.setAdapter(labTestReportsAdapter);
                            labTestReportsAdapter.notifyDataSetChanged();
                        } else if (button_type.equals("update")) {
                            empty_IV.setVisibility(View.GONE);
                            routine_report_RV.setVisibility(View.VISIBLE);
                            Toast.makeText(getContext(), "Update", Toast.LENGTH_SHORT).show();
                            updateLabTestAdpater = new UpdateLabTestAdpater(getContext(), petLabWorkResponse.getData().getPetLabWorkList(), this);
                            routine_report_RV.setAdapter(updateLabTestAdpater);
                            updateLabTestAdpater.notifyDataSetChanged();
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;


            case "GetHospitalization":
                try {
                    Log.d("GetHospitalization", "GetHospitalization=> " + (response.body()));
                    GetPetHospitalizationResponse getPetHospitalizationResponse = (GetPetHospitalizationResponse) response.body();
                    int responseCode = Integer.parseInt(getPetHospitalizationResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
//                        Toast.makeText(getContext(), "GetHospitalization", Toast.LENGTH_SHORT).show();
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        petHospitalizationsLists = getPetHospitalizationResponse.getData().getPetHospitalizationsList();
                        routine_report_RV.setLayoutManager(linearLayoutManager);
                        routine_report_RV.setNestedScrollingEnabled(false);
                        if (getPetHospitalizationResponse.getData().getPetHospitalizationsList().isEmpty()) {
                            empty_IV.setVisibility(View.VISIBLE);
                            routine_report_RV.setVisibility(View.GONE);
                        } else if (button_type.equals("view")) {
                            empty_IV.setVisibility(View.GONE);
                            routine_report_RV.setVisibility(View.VISIBLE);
                            hospitalizationReportsAdapter = new HospitalizationReportsAdapter(getContext(), getPetHospitalizationResponse.getData().getPetHospitalizationsList(), this);
                            routine_report_RV.setAdapter(hospitalizationReportsAdapter);
                            hospitalizationReportsAdapter.notifyDataSetChanged();

                        } else if (button_type.equals("update")) {
                            empty_IV.setVisibility(View.GONE);
                            routine_report_RV.setVisibility(View.VISIBLE);
                            Toast.makeText(getContext(), "Update", Toast.LENGTH_SHORT).show();
                            updateHospitalizationAdapter = new UpdateHospitalizationAdapter(getContext(), getPetHospitalizationResponse.getData().getPetHospitalizationsList(), this);
                            routine_report_RV.setAdapter(updateHospitalizationAdapter);
                            updateHospitalizationAdapter.notifyDataSetChanged();

                        }

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
    public void onViewXrayClick(int position) {
        Intent labIntent = new Intent(getContext(), XRayReportDeatilsActivity.class);
        labIntent.putExtra("pet_id", pet_id);
        labIntent.putExtra("pet_name", pet_name);
        labIntent.putExtra("pet_unique_id", pet_unique_id);
        labIntent.putExtra("pet_sex", pet_sex);
        labIntent.putExtra("pet_owner_name", pet_owner_name);
        labIntent.putExtra("pet_owner_contact", pet_owner_contact);
        labIntent.putExtra("nature", petTestsAndXrayLists.get(position).getTypeOfTest().getTestType());
        labIntent.putExtra("date_of_test", petTestsAndXrayLists.get(position).getDateTested());
        labIntent.putExtra("result", petTestsAndXrayLists.get(position).getResults());
        labIntent.putExtra("follow_up", petTestsAndXrayLists.get(position).getFollowUp().getFollowUpTitle());
        labIntent.putExtra("follow_up_date", petTestsAndXrayLists.get(position).getFollowUpDate());
        labIntent.putExtra("id", petTestsAndXrayLists.get(position).getId());
        labIntent.putExtra("pet_image_url", pet_image_url);
        labIntent.putExtra("pet_DOB", pet_DOB);


        labIntent.putExtras(labIntent);
        startActivity(labIntent);
        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @Override
    public void onUpdateXrayClick(int position) {
       /* Toast.makeText(getContext(), "Upadte", Toast.LENGTH_SHORT).show();
        Intent addXrayActivityIntent = new Intent(getActivity().getApplication(), AddXRayDeatilsActivity.class);
        addXrayActivityIntent.putExtra("report_id",petTestsAndXrayLists.get(position).getId());
        addXrayActivityIntent.putExtra("pet_id",pet_id);
        addXrayActivityIntent.putExtra("pet_name",pet_name);
        addXrayActivityIntent.putExtra("pet_unique_id",pet_unique_id);
        addXrayActivityIntent.putExtra("pet_sex",pet_sex);
        addXrayActivityIntent.putExtra("next_visit",petTestsAndXrayLists.get(position).getFollowUp().getFollowUpTitle());
        addXrayActivityIntent.putExtra("date_of_test",petTestsAndXrayLists.get(position).getDateTested());
        addXrayActivityIntent.putExtra("follow_up_date",petTestsAndXrayLists.get(position).getFollowUpDate());
        addXrayActivityIntent.putExtra("nature_of_visit",petTestsAndXrayLists.get(position).getTypeOfTest().getTestType());
        addXrayActivityIntent.putExtra("test_date",petTestsAndXrayLists.get(position).getDateTested());
        addXrayActivityIntent.putExtra("result",petTestsAndXrayLists.get(position).getResults());
        addXrayActivityIntent.putExtra("type","Update Test/X-rays");
        addXrayActivityIntent.putExtras(addXrayActivityIntent);
        startActivity(addXrayActivityIntent);
        getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);*/

    }

    @Override
    public void onViewLabTestReportsClick(int position) {
        /*Toast.makeText(getContext(), ""+petLabWorkLists.get(position).getId(), Toast.LENGTH_SHORT).show();

        Intent labIntent = new Intent(getContext(), LabTestReportDeatilsActivity.class);
        labIntent.putExtra("pet_id",pet_id);
        labIntent.putExtra("pet_name",pet_name);
        labIntent.putExtra("pet_unique_id",pet_unique_id);
        labIntent.putExtra("pet_sex",pet_sex);
        labIntent.putExtra("pet_owner_name",pet_owner_name);
        labIntent.putExtra("pet_owner_contact",pet_owner_contact);
        labIntent.putExtra("id",petLabWorkLists.get(position).getId());

        labIntent.putExtras(labIntent);
        startActivity(labIntent);
        getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);*/

    }

    @Override
    public void onViewHospitalizationClick(int position) {
        Intent labIntent = new Intent(getContext(), HospitalizationDetailsActivity.class);
        labIntent.putExtra("pet_id",pet_id);
        labIntent.putExtra("pet_name",pet_name);
        labIntent.putExtra("pet_unique_id",pet_unique_id);
        labIntent.putExtra("pet_sex",pet_sex);
        labIntent.putExtra("pet_owner_name",pet_owner_name);
        labIntent.putExtra("pet_owner_contact",pet_owner_contact);
        labIntent.putExtra("report_id",petHospitalizationsLists.get(position).getId());
        labIntent.putExtra("pet_image_url", pet_image_url);
        labIntent.putExtra("pet_DOB", pet_DOB);
        labIntent.putExtras(labIntent);
        startActivity(labIntent);
        getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
    }


    @Override
    public void onUpdateLabTestReportsClick(int position) {
       /* Intent addLabActivityIntent = new Intent(getActivity().getApplication(), AddLabWorkDeatilsActivity.class);
        addLabActivityIntent.putExtra("report_id",petLabWorkLists.get(position).getId());
        addLabActivityIntent.putExtra("pet_id",pet_id);
        addLabActivityIntent.putExtra("pet_name",pet_name);
        addLabActivityIntent.putExtra("pet_unique_id",pet_unique_id);
        addLabActivityIntent.putExtra("pet_sex",pet_sex);
        addLabActivityIntent.putExtra("test_name",petLabWorkLists.get(position).getTestName());
        addLabActivityIntent.putExtra("lab_phone",petLabWorkLists.get(position).getLabPhone());
        addLabActivityIntent.putExtra("lab_name",petLabWorkLists.get(position).getNameOfLab());
        addLabActivityIntent.putExtra("lab_type",petLabWorkLists.get(position).getLabType().getLab());
        addLabActivityIntent.putExtra("visit_date",petLabWorkLists.get(position).getVisitDate());
        addLabActivityIntent.putExtra("result",petLabWorkLists.get(position).getResults());
        addLabActivityIntent.putExtra("reason",petLabWorkLists.get(position).getReasonOfTest());
        addLabActivityIntent.putExtra("type","Update Lab Work");
        addLabActivityIntent.putExtras(addLabActivityIntent);
        startActivity(addLabActivityIntent);
        getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);*/
    }

    @Override
    public void onUpdateHospitalizationClick(int position) {
        /*Intent addHozpitalActivityIntent = new Intent(getActivity().getApplication(), AddHospitalizationDeatilsActivity.class);
        addHozpitalActivityIntent.putExtra("pet_id",pet_id);
        addHozpitalActivityIntent.putExtra("report_id",petHospitalizationsLists.get(position).getId());
        addHozpitalActivityIntent.putExtra("pet_name",pet_name);
        addHozpitalActivityIntent.putExtra("pet_unique_id",pet_unique_id);
        addHozpitalActivityIntent.putExtra("pet_sex",pet_sex);
        addHozpitalActivityIntent.putExtra("hospital_type",petHospitalizationsLists.get(position).getHospitalizationType().getHospitalization());
        addHozpitalActivityIntent.putExtra("hospital_name",petHospitalizationsLists.get(position).getHospitalName());
        addHozpitalActivityIntent.putExtra("hospital_phone",petHospitalizationsLists.get(position).getHospitalPhone());
        addHozpitalActivityIntent.putExtra("admission",petHospitalizationsLists.get(position).getAdmissionDate());
        addHozpitalActivityIntent.putExtra("discharge",petHospitalizationsLists.get(position).getDischargeDate());
        addHozpitalActivityIntent.putExtra("result",petHospitalizationsLists.get(position).getDiagnosisTreatmentProcedure());
        addHozpitalActivityIntent.putExtra("reason",petHospitalizationsLists.get(position).getReasonForHospitalization());
        addHozpitalActivityIntent.putExtra("type","Update Hospitalization");
        addHozpitalActivityIntent.putExtras(addHozpitalActivityIntent);
        startActivity(addHozpitalActivityIntent);
        getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);*/
    }


    @Override
    public void onResume() {
        super.onResume();
        if (Config.type.equals("list")) {
            Config.type = "";
            getPetClinicVisit();
        } else if (Config.type.equals("XRay")) {
            Config.type = "";
            getXrayReport();
        } else if (Config.type.equals("Lab")) {
            Config.type = "";
            getLabTestReport();
        } else if (Config.type.equals("Hospitalization")) {
            Config.type = "";
            getHospitalizationReport();
        }
    }

    @Override
    public void onViewClick(int position) {
        Intent lastPrescriptionIntent = new Intent(getContext(), PdfEditorActivity.class);
        lastPrescriptionIntent.putExtra("encryptId",petClinicVisitListArrayList.get(position).getEncryptedId());
        startActivity(lastPrescriptionIntent);
//        Intent viewReportsDeatilsActivityIntent = new Intent(getActivity().getApplication(), ViewReportsDeatilsActivity.class);
//        viewReportsDeatilsActivityIntent.putExtra("clinic_id", petClinicVisitListArrayList.get(position).getId());
//        viewReportsDeatilsActivityIntent.putExtra("pet_id", pet_id);
//        viewReportsDeatilsActivityIntent.putExtra("pet_name", pet_name);
//        viewReportsDeatilsActivityIntent.putExtra("pet_unique_id", pet_unique_id);
//        viewReportsDeatilsActivityIntent.putExtra("pet_sex", pet_sex);
//        viewReportsDeatilsActivityIntent.putExtra("pet_owner_name", pet_owner_name);
//        viewReportsDeatilsActivityIntent.putExtra("pet_owner_contact", pet_owner_contact);
//        viewReportsDeatilsActivityIntent.putExtra("pet_image_url", pet_image_url);
//        viewReportsDeatilsActivityIntent.putExtra("pet_DOB", pet_DOB);
//        Log.d("getEncryptedId",petClinicVisitListArrayList.get(position).getEncryptedId());
//        viewReportsDeatilsActivityIntent.putExtra("id", petClinicVisitListArrayList.get(position).getNatureOfVisitId());
//        viewReportsDeatilsActivityIntent.putExtras(viewReportsDeatilsActivityIntent);
//        startActivity(viewReportsDeatilsActivityIntent);
//        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @Override
    public void onUpdateClick(int position) {
        /*Toast.makeText(getContext(), "Upadte", Toast.LENGTH_SHORT).show();
        Intent viewReportsDeatilsActivityIntent = new Intent(getActivity().getApplication(), AddClinicActivity.class);
        viewReportsDeatilsActivityIntent.putExtra("pet_id",pet_id);
        viewReportsDeatilsActivityIntent.putExtra("pet_name",pet_name);
        viewReportsDeatilsActivityIntent.putExtra("pet_unique_id",pet_unique_id);
        viewReportsDeatilsActivityIntent.putExtra("pet_sex",pet_sex);
        viewReportsDeatilsActivityIntent.putExtra("pet_owner_name",pet_owner_name);
        viewReportsDeatilsActivityIntent.putExtra("nature_of_visit",petClinicVisitListArrayList.get(position).getNatureOfVisit().getNature());
        viewReportsDeatilsActivityIntent.putExtra("visit_dt",petClinicVisitListArrayList.get(position).getVisitDate());
        viewReportsDeatilsActivityIntent.putExtra("visit_description",petClinicVisitListArrayList.get(position).getDescription());
        viewReportsDeatilsActivityIntent.putExtra("visit_weight",petClinicVisitListArrayList.get(position).getWeightOz());
        viewReportsDeatilsActivityIntent.putExtra("visit_temparature",petClinicVisitListArrayList.get(position).getTemperature());
        viewReportsDeatilsActivityIntent.putExtra("dt_of_illness",petClinicVisitListArrayList.get(position).getVisitDate());
        viewReportsDeatilsActivityIntent.putExtra("pet_diognosis",petClinicVisitListArrayList.get(position).getDescription());
        viewReportsDeatilsActivityIntent.putExtra("next_dt",petClinicVisitListArrayList.get(position).getFollowUpDate());
        viewReportsDeatilsActivityIntent.putExtra("report_id",petClinicVisitListArrayList.get(position).getId());

        viewReportsDeatilsActivityIntent.putExtra("toolbar_name","Update Clinic");
        viewReportsDeatilsActivityIntent.putExtras(viewReportsDeatilsActivityIntent);
        startActivity(viewReportsDeatilsActivityIntent);
        getActivity().overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);*/
    }


}
