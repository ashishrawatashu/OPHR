package com.cynoteck.petofyOPHR.fragments;

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
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cynoteck.petofyOPHR.R;
import com.cynoteck.petofyOPHR.activities.AddNewPetActivity;
import com.cynoteck.petofyOPHR.activities.AddPetRegister;
import com.cynoteck.petofyOPHR.activities.PetDetailsActivity;
import com.cynoteck.petofyOPHR.activities.PetIdCardActivity;
import com.cynoteck.petofyOPHR.activities.PetProfileActivity;
import com.cynoteck.petofyOPHR.adapters.RegisterPetAdapter;
import com.cynoteck.petofyOPHR.api.ApiClient;
import com.cynoteck.petofyOPHR.api.ApiResponse;
import com.cynoteck.petofyOPHR.api.ApiService;
import com.cynoteck.petofyOPHR.params.petReportsRequest.PetDataParams;
import com.cynoteck.petofyOPHR.params.petReportsRequest.PetDataRequest;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.getPetListResponse.GetPetListResponse;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.getPetListResponse.PetList;
import com.cynoteck.petofyOPHR.response.loginRegisterResponse.UserPermissionMasterList;
import com.cynoteck.petofyOPHR.response.staffPermissionListResponse.CheckStaffPermissionResponse;
import com.cynoteck.petofyOPHR.utils.Config;
import com.cynoteck.petofyOPHR.utils.Methods;
import com.cynoteck.petofyOPHR.utils.ViewDeatilsAndIdCardClick;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.StringTokenizer;

import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class PetRegisterFragment extends Fragment implements ApiResponse, ViewDeatilsAndIdCardClick, View.OnClickListener, TextWatcher {
    View view;
    Context context;
    Methods methods;
    CardView materialCardView;
    RecyclerView register_pet_RV;
    ImageView empty_IV, search_register_pet, back_arrow_IV;
    RegisterPetAdapter registerPetAdapter;
    private ShimmerFrameLayout mShimmerViewContainer;
    RelativeLayout search_boxRL, addNewEntry;
    AutoCompleteTextView search_box;
    TextView regiter_pet_headline_TV;
    NestedScrollView nestedScrollView;
    ProgressBar progressBar;
    int page = 1, pagelimit = 10;
    ArrayList<PetList> profileList;
    String permissionId = "";
    SharedPreferences sharedPreferences;
    String userTYpe = "";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public PetRegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_pet_register, container, false);
        sharedPreferences = getActivity().getSharedPreferences("userdetails", 0);

        init();

        if (methods.isInternetOn()) {
            getPetList(page, pagelimit);

        } else {

            methods.DialogInternet();
        }

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    page++;
                    progressBar.setVisibility(View.VISIBLE);
                    getFromScroll(page, pagelimit);
                }
            }
        });

        return view;
    }

    private void getFromScroll(int page, int pageLimit) {
        PetDataParams getPetDataParams = new PetDataParams();
        getPetDataParams.setPageNumber(page);//0
        getPetDataParams.setPageSize(pageLimit);//0
        getPetDataParams.setSearch_Data("");
        PetDataRequest getPetDataRequest = new PetDataRequest();
        getPetDataRequest.setData(getPetDataParams);

        ApiService<GetPetListResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getPetList(Config.token, getPetDataRequest), "GetFromScroll");
        Log.e("DATALOG", "check1=> " + getPetDataRequest);

    }
    private void init() {
        methods = new Methods(context);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);
        empty_IV = view.findViewById(R.id.empty_IV);
        materialCardView = view.findViewById(R.id.toolbar);
        register_pet_RV = view.findViewById(R.id.register_pet_RV);
        addNewEntry = view.findViewById(R.id.addNewEntry);
//        search_register_pet = view.findViewById(R.id.search_register_pet);
        search_boxRL = view.findViewById(R.id.search_boxRL);
        search_box = view.findViewById(R.id.search_box_TV);
        regiter_pet_headline_TV = view.findViewById(R.id.regiter_pet_headline_TV);

        nestedScrollView = view.findViewById(R.id.nested_scroll_view);
        progressBar = view.findViewById(R.id.progressBar);

        mShimmerViewContainer.setVisibility(View.VISIBLE);
        mShimmerViewContainer.startShimmerAnimation();
        methods = new Methods(getContext());
        addNewEntry.setOnClickListener(this);
        search_box.addTextChangedListener(this);

        profileList = new ArrayList<>();

        getPet();
    }

    private void getPet() {
        search_box.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d("dataChange", "afterTextChanged" + new String(editable.toString()));
                String value = editable.toString();
                petSearchDependsOnPrefix(value);
            }
        });


        search_box.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value = search_box.getText().toString();
                String[] city_array = value.split("\\(");

                search_box.setText(city_array[0]);
            }
        });

    }

    private void petSearchDependsOnPrefix(String prefix) {
        PetDataParams getPetDataParams = new PetDataParams();
        getPetDataParams.setPageNumber(0);//0
        getPetDataParams.setPageSize(10);//0
        getPetDataParams.setSearch_Data(prefix);
        PetDataRequest getPetDataRequest = new PetDataRequest();
        getPetDataRequest.setData(getPetDataParams);

        ApiService<GetPetListResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getPetList(Config.token, getPetDataRequest), "GetPetListBySearch");
        Log.e("DATALOG", "check1=> " + getPetDataRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
                    addNewPetIntent.putExtra("from", "Register");
                    addNewPetIntent.putExtra("appointment", "");
                    startActivity(addNewPetIntent);
                }

                break;


        }

    }

    private void getPetList(int page, int pageLimit) {
        //       methods.showCustomProgressBarDialog(getContext());
        PetDataParams getPetDataParams = new PetDataParams();
        getPetDataParams.setPageNumber(page);//0
        getPetDataParams.setPageSize(pageLimit);//0
        getPetDataParams.setSearch_Data("");
        PetDataRequest getPetDataRequest = new PetDataRequest();
        getPetDataRequest.setData(getPetDataParams);

        ApiService<GetPetListResponse> service = new ApiService<>();
        service.get(this, ApiClient.getApiInterface().getPetList(Config.token, getPetDataRequest), "GetPetList");
        Log.e("DATALOG", "check1=> " + methods.getRequestJson(getPetDataRequest));


    }


    @Override
    public void onResponse(Response response, String key) {
//        methods.customProgressDismiss();
        switch (key) {
            case "CheckPermission":
                try {
                    methods.customProgressDismiss();
                    CheckStaffPermissionResponse checkStaffPermissionResponse = (CheckStaffPermissionResponse) response.body();
                    Log.d("GetPetList", checkStaffPermissionResponse.toString());
                    int responseCode = Integer.parseInt(checkStaffPermissionResponse.getResponse().getResponseCode());

                    if (responseCode == 109) {
                        if (checkStaffPermissionResponse.getData().equals("true")) {
                            if (permissionId.equals("1")) {
                                Intent addNewPetIntent = new Intent(getContext(), AddNewPetActivity.class);
                                addNewPetIntent.putExtra("from", "Register");
                                addNewPetIntent.putExtra("appointment", "");
                                startActivity(addNewPetIntent);
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
            case "GetPetList":
                try {
                    GetPetListResponse getPetListResponse = (GetPetListResponse) response.body();
                    int responseCode = Integer.parseInt(getPetListResponse.getResponse().getResponseCode());
                    mShimmerViewContainer.setVisibility(View.GONE);
                    mShimmerViewContainer.stopShimmerAnimation();
                    if (responseCode == 109) {
                        if (getPetListResponse.getData().getPetList().isEmpty()) {
                            empty_IV.setVisibility(View.VISIBLE);
                            search_register_pet.setVisibility(View.INVISIBLE);
                        } else {
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                            register_pet_RV.setLayoutManager(linearLayoutManager);
                            if (getPetListResponse.getData().getPetList().size() > 0) {
                                for (int i = 0; i < getPetListResponse.getData().getPetList().size(); i++) {
                                    PetList petList = new PetList();
                                    petList.setPetUniqueId(getPetListResponse.getData().getPetList().get(i).getPetUniqueId());
                                    petList.setDateOfBirth(getPetListResponse.getData().getPetList().get(i).getDateOfBirth());
                                    petList.setPetName(getPetListResponse.getData().getPetList().get(i).getPetName());
                                    petList.setPetSex(getPetListResponse.getData().getPetList().get(i).getPetSex());
                                    petList.setPetParentName(getPetListResponse.getData().getPetList().get(i).getPetParentName());
                                    petList.setPetProfileImageUrl(getPetListResponse.getData().getPetList().get(i).getPetProfileImageUrl());
                                    petList.setEncryptedId(getPetListResponse.getData().getPetList().get(i).getEncryptedId());
                                    petList.setId(getPetListResponse.getData().getPetList().get(i).getId());
                                    petList.setPetAge(getPetListResponse.getData().getPetList().get(i).getPetAge());
                                    petList.setPetCategoryId(getPetListResponse.getData().getPetList().get(i).getPetCategoryId());
                                    petList.setLastVisitEncryptedId(getPetListResponse.getData().getPetList().get(i).getLastVisitEncryptedId());
                                    Log.e("LAST_VISIT", getPetListResponse.getData().getPetList().get(i).getLastVisitEncryptedId());

                                    profileList.add(petList);
                                }
                                registerPetAdapter = new RegisterPetAdapter(getContext(), profileList, this);
                                register_pet_RV.setAdapter(registerPetAdapter);
                                registerPetAdapter.notifyDataSetChanged();
                                search_register_pet.setVisibility(View.VISIBLE);
                            }
                        }


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

            case "GetFromScroll":
                try {
                    progressBar.setVisibility(View.GONE);
                    GetPetListResponse getPetListResponse = (GetPetListResponse) response.body();
                    int responseCode = Integer.parseInt(getPetListResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        register_pet_RV.setLayoutManager(linearLayoutManager);
                        if (getPetListResponse.getData().getPetList().size() > 0) {
                            for (int i = 0; i < getPetListResponse.getData().getPetList().size(); i++) {
                                PetList petList = new PetList();
                                petList.setPetUniqueId(getPetListResponse.getData().getPetList().get(i).getPetUniqueId());
                                petList.setDateOfBirth(getPetListResponse.getData().getPetList().get(i).getDateOfBirth());
                                petList.setPetName(getPetListResponse.getData().getPetList().get(i).getPetName());
                                petList.setPetSex(getPetListResponse.getData().getPetList().get(i).getPetSex());
                                petList.setPetParentName(getPetListResponse.getData().getPetList().get(i).getPetParentName());
                                petList.setPetProfileImageUrl(getPetListResponse.getData().getPetList().get(i).getPetProfileImageUrl());
                                petList.setEncryptedId(getPetListResponse.getData().getPetList().get(i).getEncryptedId());
                                petList.setId(getPetListResponse.getData().getPetList().get(i).getId());
                                petList.setPetAge(getPetListResponse.getData().getPetList().get(i).getPetAge());
                                petList.setPetCategoryId(getPetListResponse.getData().getPetList().get(i).getPetCategoryId());
                                petList.setLastVisitEncryptedId(getPetListResponse.getData().getPetList().get(i).getLastVisitEncryptedId());
                                Log.e("LAST_VISIT", getPetListResponse.getData().getPetList().get(i).getLastVisitEncryptedId());

                                profileList.add(petList);
                            }
                            registerPetAdapter = new RegisterPetAdapter(getContext(), profileList, this);
                            register_pet_RV.setAdapter(registerPetAdapter);
                            registerPetAdapter.notifyDataSetChanged();
                            search_register_pet.setVisibility(View.VISIBLE);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case "GetPetListBySearch":
                try {
                    GetPetListResponse getPetListResponse = (GetPetListResponse) response.body();
                    int responseCode = Integer.parseInt(getPetListResponse.getResponse().getResponseCode());
                    if (responseCode == 109) {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        register_pet_RV.setLayoutManager(linearLayoutManager);
                        if (getPetListResponse.getData().getPetList().size() > 0) {
                            profileList.clear();
                            for (int i = 0; i < getPetListResponse.getData().getPetList().size(); i++) {
                                PetList petList = new PetList();
                                petList.setPetUniqueId(getPetListResponse.getData().getPetList().get(i).getPetUniqueId());
                                petList.setDateOfBirth(getPetListResponse.getData().getPetList().get(i).getDateOfBirth());
                                petList.setPetName(getPetListResponse.getData().getPetList().get(i).getPetName());
                                petList.setPetSex(getPetListResponse.getData().getPetList().get(i).getPetSex());
                                petList.setPetParentName(getPetListResponse.getData().getPetList().get(i).getPetParentName());
                                petList.setPetProfileImageUrl(getPetListResponse.getData().getPetList().get(i).getPetProfileImageUrl());
                                petList.setEncryptedId(getPetListResponse.getData().getPetList().get(i).getEncryptedId());
                                petList.setId(getPetListResponse.getData().getPetList().get(i).getId());
                                petList.setPetColor(getPetListResponse.getData().getPetList().get(i).getPetColor());
                                petList.setPetAge(getPetListResponse.getData().getPetList().get(i).getPetAge());
                                profileList.add(petList);
                            }
                            progressBar.setVisibility(View.GONE);
                            registerPetAdapter = new RegisterPetAdapter(getContext(), profileList, this);
                            register_pet_RV.setAdapter(registerPetAdapter);
                            registerPetAdapter.notifyDataSetChanged();
                            search_register_pet.setVisibility(View.GONE);
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
        //methods.customProgressDismiss();

    }


    @Override
    public void onResume() {
        super.onResume();
        if (Config.isUpdated==true) {
            mShimmerViewContainer.setVisibility(View.VISIBLE);
            mShimmerViewContainer.startShimmerAnimation();
            Config.backCall = "";
            profileList.clear();
            getPetList(1, 10);
            Config.isUpdated=false;
        }
    }

    @Override
    public void onPause() {
        /*mShimmerViewContainer.stopShimmerAnimation();*/
        super.onPause();
    }

    @Override
    public void onViewDetailsClick(int position) {
        Log.d("positionssss", "" + position);
        Log.d("pet_id", "" + profileList.get(position).getId());

        StringTokenizer tokens = new StringTokenizer(profileList.get(position).getId(), ".");
        String first = tokens.nextToken();
        Intent intent = new Intent(getActivity(), PetProfileActivity.class);
        intent.putExtra("pet_id", first);
        startActivityForResult(intent, 1);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                if (Config.isUpdated == true) {
                    profileList.clear();
                    mShimmerViewContainer.setVisibility(View.VISIBLE);
                    mShimmerViewContainer.startShimmerAnimation();
                    Snackbar snackbar = Snackbar.make(view, "Please wait....", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    getPetList(1, 10);
                    Config.isUpdated = false;
                }
            }
        }
        return;
    }

    @Override
    public void onIdCardClick(int position) {
        Log.d("positionssss", "" + position);
        Intent intent = new Intent(getContext(), PetIdCardActivity.class);
        Bundle idBundle = new Bundle();
        idBundle.putString("id", profileList.get(position).getId());
        intent.putExtras(idBundle);
        startActivity(intent);
    }

    @Override
    public void onIdAddClinicClick(int position) {
        Log.e("ID", profileList.get(position).getId());
        Log.e("E_ID", profileList.get(position).getEncryptedId());
        Log.e("PET_NAME", profileList.get(position).getPetName());
        Log.e("PET_PARENT", profileList.get(position).getPetParentName());
        Log.e("PET_CAT_ID", profileList.get(position).getPetCategoryId());

        Intent petDetailsIntent = new Intent(getActivity().getApplication(), PetDetailsActivity.class);
        Bundle data = new Bundle();
        data.putString("pet_id", profileList.get(position).getId());
        data.putString("pet_name", profileList.get(position).getPetName());
        data.putString("pet_parent", profileList.get(position).getPetParentName());
        data.putString("pet_sex", profileList.get(position).getPetSex());
        data.putString("pet_age", profileList.get(position).getPetAge());
        data.putString("pet_unique_id", profileList.get(position).getPetUniqueId());
        data.putString("pet_DOB", profileList.get(position).getDateOfBirth());
        data.putString("pet_encrypted_id", profileList.get(position).getEncryptedId());
        data.putString("pet_cat_id", profileList.get(position).getPetCategoryId());
        data.putString("lastVisitEncryptedId", profileList.get(position).getLastVisitEncryptedId());
        data.putString("pet_image_url", profileList.get(position).getPetProfileImageUrl());

        petDetailsIntent.putExtras(data);
        startActivity(petDetailsIntent);
    }

    private void clearSearch() {
        search_box.getText().clear();
        search_register_pet.setVisibility(View.VISIBLE);
        search_boxRL.setVisibility(View.GONE);
        back_arrow_IV.setVisibility(View.GONE);
        regiter_pet_headline_TV.setVisibility(View.VISIBLE);
        addNewEntry.setVisibility(View.VISIBLE);
        InputMethodManager imm1 = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm1.hideSoftInputFromWindow(search_box.getWindowToken(), 0);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        // registerPetAdapter.getFilter().filter(charSequence.toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }


}