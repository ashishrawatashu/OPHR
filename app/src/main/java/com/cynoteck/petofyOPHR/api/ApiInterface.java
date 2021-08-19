package com.cynoteck.petofyOPHR.api;

import com.cynoteck.petofyOPHR.params.ClinicVisitsParameter.ClinicVisitRequest;
import com.cynoteck.petofyOPHR.params.VaccinationTypeByVaccineName.VaccinationTypeByVaccineNameRequest;
import com.cynoteck.petofyOPHR.params.addBankAccountParams.AddBankAccountRequest;
import com.cynoteck.petofyOPHR.params.addBankAccountParams.CheckAccountRequest;
import com.cynoteck.petofyOPHR.params.addBankAccountParams.ValidateIfscRequest;
import com.cynoteck.petofyOPHR.params.addEditImmunizationSchedule.AddEditImmunizationRequest;
import com.cynoteck.petofyOPHR.params.addHospitalization.AddHospitalizationRequest;
import com.cynoteck.petofyOPHR.params.addImmunizationClinic.ImmunizationClinicData;
import com.cynoteck.petofyOPHR.params.addLabRequest.AddLabRequest;
import com.cynoteck.petofyOPHR.params.addParamRequest.AddPetRequset;
import com.cynoteck.petofyOPHR.params.addPetClinicParamRequest.AddPetClinicRequest;
import com.cynoteck.petofyOPHR.params.addTestXRayParams.AddTestXRayRequest;
import com.cynoteck.petofyOPHR.params.allStaffRequest.AddStaffRequest;
import com.cynoteck.petofyOPHR.params.allStaffRequest.ChangeStaffStatusRequest;
import com.cynoteck.petofyOPHR.params.allStaffRequest.StaffDeatilsRequest;
import com.cynoteck.petofyOPHR.params.allStaffRequest.UpdateStaffRequest;
import com.cynoteck.petofyOPHR.params.appointmentParams.AppointmentsStatusRequest;
import com.cynoteck.petofyOPHR.params.appointmentParams.CreateAppointRequest;
import com.cynoteck.petofyOPHR.params.appointmentParams.GetAppointmentFromDateParams;
import com.cynoteck.petofyOPHR.params.appointmentParams.GetAppointmentFromDateRequest;
import com.cynoteck.petofyOPHR.params.appointmentParams.UpdateAppointmentRequest;
import com.cynoteck.petofyOPHR.params.assignAndRemovePermission.AssignRemovePermissionRequest;
import com.cynoteck.petofyOPHR.params.changePassRequest.ChangePassRequest;
import com.cynoteck.petofyOPHR.params.checkpetInVetRegister.InPetRegisterRequest;
import com.cynoteck.petofyOPHR.params.forgetPassRequest.ForgetPassRequest;
import com.cynoteck.petofyOPHR.params.getFirstVaccine.GetFirstVaccineRequest;
import com.cynoteck.petofyOPHR.params.getMyVisitPetRecordRequest.GetMyVisistPetRecordRequest;
import com.cynoteck.petofyOPHR.params.getPetListRequest.GetPetListRequest;
import com.cynoteck.petofyOPHR.params.getVaccinationDetails.GetVaccinationRequest;
import com.cynoteck.petofyOPHR.params.getpetAgeRequest.GetPetAgeRequestData;
import com.cynoteck.petofyOPHR.params.immunizationRequest.ImmunizationRequest;
import com.cynoteck.petofyOPHR.params.immunizationRequest.ImmunizationRequestt;
import com.cynoteck.petofyOPHR.params.loginRequest.Loginparams;
import com.cynoteck.petofyOPHR.params.newPetEntryParams.NewPetRequest;
import com.cynoteck.petofyOPHR.params.nextVaccineParameter.NextVaccineRequest;
import com.cynoteck.petofyOPHR.params.onlineClinicVisitsParams.OnlineClinicVisitsRequest;
import com.cynoteck.petofyOPHR.params.otpRequest.SendOtpRequest;
import com.cynoteck.petofyOPHR.params.petBreedRequest.BreedParams;
import com.cynoteck.petofyOPHR.params.petReportsRequest.PetClinicVisitDetailsRequest;
import com.cynoteck.petofyOPHR.params.petReportsRequest.PetDataRequest;
import com.cynoteck.petofyOPHR.params.petReportsRequest.VisitTypeRequest;
import com.cynoteck.petofyOPHR.params.registerRequest.Registerparams;
import com.cynoteck.petofyOPHR.params.removeVccination.RemoveRequest;
import com.cynoteck.petofyOPHR.params.saveVaccinationParameter.SaveRequest;
import com.cynoteck.petofyOPHR.params.searcgDiagnosisRequest.SearchDiagnosisRequestData;
import com.cynoteck.petofyOPHR.params.searchPetParentRequest.SearchPetParentRequestData;
import com.cynoteck.petofyOPHR.params.searchRemarksParameter.SearchRemaksRequest;
import com.cynoteck.petofyOPHR.params.sendNotificationParams.SendNotificationRequest;
import com.cynoteck.petofyOPHR.params.staffPermission.StaffPermissionRequest;
import com.cynoteck.petofyOPHR.params.upcommingVisitsRequest.UpcommingVisitsRequest;
import com.cynoteck.petofyOPHR.params.updateClinicVisitsParams.UpdateClinicReportsRequest;
import com.cynoteck.petofyOPHR.params.updateHospitalizationParams.UpdateHospitalizationRequest;
import com.cynoteck.petofyOPHR.params.updateLapTestParams.UpdateLabTestRequest;
import com.cynoteck.petofyOPHR.params.updateRequest.getValue.UpdateRequest;
import com.cynoteck.petofyOPHR.params.updateRequest.updateParamRequest.UpdatePetRequest;
import com.cynoteck.petofyOPHR.params.updateXRayParams.UpdateXrayRequest;
import com.cynoteck.petofyOPHR.params.uploadVetProfileImageParams.UploadVetProfileImageData;
import com.cynoteck.petofyOPHR.params.vaccinationSaveParams.VaccinationRequest;
import com.cynoteck.petofyOPHR.params.workingHoursParameter.WorkingHoursParameter;
import com.cynoteck.petofyOPHR.response.CheckTrueFalseStatus;
import com.cynoteck.petofyOPHR.response.ClinicVistResponse.ClinicVisitResponseData;
import com.cynoteck.petofyOPHR.response.InPetVeterian.InPetVeterianResponse;
import com.cynoteck.petofyOPHR.response.VaccinationTypeByVaccineNameResponse.VaccinationTypeByVaccineNameResponse;
import com.cynoteck.petofyOPHR.response.addEditImmunizationResponse.AddEditImmunizationResponse;
import com.cynoteck.petofyOPHR.response.addHospitalizationResponse.AddhospitalizationResposee;
import com.cynoteck.petofyOPHR.response.addLabWorkResponse.AddLabWorkResponse;
import com.cynoteck.petofyOPHR.response.addPet.addPetResponse.AddPetValueResponse;
import com.cynoteck.petofyOPHR.response.addPet.breedResponse.BreedCatRespose;
import com.cynoteck.petofyOPHR.response.addPet.imageUpload.ImageResponse;
import com.cynoteck.petofyOPHR.response.addPet.petAgeResponse.PetAgeValueResponse;
import com.cynoteck.petofyOPHR.response.addPet.petColorResponse.PetColorValueResponse;
import com.cynoteck.petofyOPHR.response.addPet.petSizeResponse.PetSizeValueResponse;
import com.cynoteck.petofyOPHR.response.addPet.uniqueIdResponse.UniqueResponse;
import com.cynoteck.petofyOPHR.response.addPetClinicresponse.AddpetClinicResponse;
import com.cynoteck.petofyOPHR.response.addTestAndXRayResponse.AddTestXRayResponse;
import com.cynoteck.petofyOPHR.response.appointmentResponse.AppointmentDetailsResponse;
import com.cynoteck.petofyOPHR.response.appointmentResponse.CreateAppointmentResponse;
import com.cynoteck.petofyOPHR.response.appointmentResponse.GetAppointmentResponse;
import com.cynoteck.petofyOPHR.response.appointmentResponse.requestPendingResponse.RequestPendingResponse;
import com.cynoteck.petofyOPHR.response.appointmentResponse.upComingAppointmentResponse.UpcomingAppointmentResponse;
import com.cynoteck.petofyOPHR.response.bankAccountResponse.AddBankAccountResponse;
import com.cynoteck.petofyOPHR.response.bankAccountResponse.GetBankAccoutsResponse;
import com.cynoteck.petofyOPHR.response.bankAccountResponse.ValidateIfscCodeResponse;
import com.cynoteck.petofyOPHR.response.clinicVisist.ClinicVisitResponse;
import com.cynoteck.petofyOPHR.response.dateOfBirthResponse.DateOfBirthResponse;
import com.cynoteck.petofyOPHR.response.forgetAndChangePassResponse.PasswordResponse;
import com.cynoteck.petofyOPHR.response.getAppointmentsStatusResponse.AppointmentStatusResponse;
import com.cynoteck.petofyOPHR.response.getFirstVaccineReponse.GetFirstVaccineResponseData;
import com.cynoteck.petofyOPHR.response.getImmunizationReport.PetImmunizationRecordResponse;
import com.cynoteck.petofyOPHR.response.getLabTestReportResponse.getLabTestReportDetailsResponse.GetLabTestReportDeatilsResponse;
import com.cynoteck.petofyOPHR.response.getLabTestReportResponse.getPetLabWorkListResponse.PetLabWorkResponse;
import com.cynoteck.petofyOPHR.response.getLastPrescriptionResponse.GetLastPrescriptionResponse;
import com.cynoteck.petofyOPHR.response.getMyVisitedPetRecordResponse.GetMyVisitPetRecordResponse;
import com.cynoteck.petofyOPHR.response.getPetAgeResponse.GetPetAgeresponseData;
import com.cynoteck.petofyOPHR.response.getPetDetailsResponse.GetPetResponse;
import com.cynoteck.petofyOPHR.response.getPetHospitalizationResponse.getHospitalizationDeatilsResponse.GetHospitalizationDeatilsResponse;
import com.cynoteck.petofyOPHR.response.getPetHospitalizationResponse.getHospitalizationListResponse.GetPetHospitalizationResponse;
import com.cynoteck.petofyOPHR.response.getPetIdCardResponse.PetIdCardResponse;
import com.cynoteck.petofyOPHR.response.getPetParentResponse.GetPetParentResponse;
import com.cynoteck.petofyOPHR.response.getPetParrentnameReponse.GetPetParentResponseData;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.AddUpdateDeleteClinicVisitResponse;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.GetReportsTypeResponse;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.getClinicVisitDetails.GetClinicVisitsDetailsResponse;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.getPetClinicVisitsListsResponse.GetPetClinicVisitListResponse;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.getPetListResponse.GetPetListResponse;
import com.cynoteck.petofyOPHR.response.getStaffResponse.GetAllStaffResponse;
import com.cynoteck.petofyOPHR.response.getStaffResponse.GetStaffDetailsResponse;
import com.cynoteck.petofyOPHR.response.getStaffResponse.GetStaffStatusResponse;
import com.cynoteck.petofyOPHR.response.getStaffResponse.GetUpdateStaffResponse;
import com.cynoteck.petofyOPHR.response.getVaccinationResponse.GetVaccineResponse;
import com.cynoteck.petofyOPHR.response.getWorkingHoursResponse.WorkingHoursResponse;
import com.cynoteck.petofyOPHR.response.getXRayReports.getPetTestAndXRayResponse.GetPetTestAndXRayResponse;
import com.cynoteck.petofyOPHR.response.getXRayReports.getXRayReportDetailsResponse.GetXRayReportDeatilsResponse;
import com.cynoteck.petofyOPHR.response.hospitalTypeListResponse.HospitalAddmissionTypeResp;
import com.cynoteck.petofyOPHR.response.immunizationListResponse.ImmunizationModelResponse;
import com.cynoteck.petofyOPHR.response.immunizationListResponse.ImmunizationResponse;
import com.cynoteck.petofyOPHR.response.immunizationVaccineType.ImmunizationVaccineResponse;
import com.cynoteck.petofyOPHR.response.immuniztionHistory.ImmunizationHistoryResponse;
import com.cynoteck.petofyOPHR.response.labTyperesponse.LabTypeResponse;
import com.cynoteck.petofyOPHR.response.loginRegisterResponse.LoginRegisterResponse;
import com.cynoteck.petofyOPHR.response.newPetResponse.NewPetRegisterResponse;
import com.cynoteck.petofyOPHR.response.nextVaccineResponse.NextVaccineResponse;
import com.cynoteck.petofyOPHR.response.onlineAppointmentOnOff.OnlineAppointmentResponse;
import com.cynoteck.petofyOPHR.response.onlineClinicVisitResponse.OnlineClinicResponse;
import com.cynoteck.petofyOPHR.response.otpResponse.OtpResponse;
import com.cynoteck.petofyOPHR.response.petAgeUnitResponse.PetAgeUnitResponseData;
import com.cynoteck.petofyOPHR.response.profileImageresponse.ProfileImageResponse;
import com.cynoteck.petofyOPHR.response.recentEntrys.RecentEntrysResponse;
import com.cynoteck.petofyOPHR.response.saveImmunizationData.SaveImmunizationResponse;
import com.cynoteck.petofyOPHR.response.saveResponse.SaveResponseData;
import com.cynoteck.petofyOPHR.response.saveWorkingReponse.SaveWorkingHoursResponse;
import com.cynoteck.petofyOPHR.response.searchDiagnosisResponse.SearchDiagnosisResponseData;
import com.cynoteck.petofyOPHR.response.searchDiagnosisResponse.SearchDiagnosisResponseResponse;
import com.cynoteck.petofyOPHR.response.searchRemaks.SearchRemaksResponse;
import com.cynoteck.petofyOPHR.response.staffPermissionListResponse.CheckStaffPermissionResponse;
import com.cynoteck.petofyOPHR.response.staffPermissionListResponse.StaffPermissionResponse;
import com.cynoteck.petofyOPHR.response.testResponse.XrayTestResponse;
import com.cynoteck.petofyOPHR.response.totalStaffPetsAppointment.GetDashboardCountsResponse;
import com.cynoteck.petofyOPHR.response.upcommingVisitsResponse.UpcommingVisitsResponse;
import com.cynoteck.petofyOPHR.response.updateProfileResponse.CityResponse;
import com.cynoteck.petofyOPHR.response.updateProfileResponse.CountryResponse;
import com.cynoteck.petofyOPHR.response.updateProfileResponse.PetServiceResponse;
import com.cynoteck.petofyOPHR.response.updateProfileResponse.PetTypeResponse;
import com.cynoteck.petofyOPHR.response.updateProfileResponse.StateResponse;
import com.cynoteck.petofyOPHR.response.updateProfileResponse.UserResponse;
import com.cynoteck.petofyOPHR.response.updateVetDetailsresponse.UpdateVetResponse;
import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface ApiInterface {

    @POST("User/Login")
    Call<LoginRegisterResponse> loginApi(@Body Loginparams loginparams);
    @POST("User/Registration")
    Call<LoginRegisterResponse> registerApi(@Body Registerparams registerparams);
    @GET("common/GetState")
    Call<StateResponse> getStateApi();
    @GET("common/GetCountry")
    Call<CountryResponse>getCountryApi();

    @GET
    Call<CityResponse> getCityApi(@Url String url);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("common/GetPetTypes")
    Call<PetTypeResponse> petTypeApi();

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("common/GetServiceTypes")
    Call<PetServiceResponse> petServiceTypeApi(@Header("Authorization") String auth);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("report/GetPetList")
    Call<GetPetListResponse>getPetList(@Header("Authorization") String auth, @Body PetDataRequest getPetDataRequest);


    @POST("user/forgotpassword")
    Call<PasswordResponse> getPasswordResponse(@Body ForgetPassRequest forgetPassRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("user/ChangePassword")
    Call<PasswordResponse> getPasswordResponse(@Header("Authorization") String auth, @Body ChangePassRequest changePassRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("user/UpdateVeterinarian")
    Call<UpdateVetResponse> updateUser(@Header("Authorization") String auth, @Body UpdateRequest updateRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("User/GetUserDetails")
    Call<UserResponse> getUserDetailsApi(@Header("Authorization") String auth);


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("pet/GeneratePetUniqueId")
    Call<UniqueResponse> getGeneratePetUniqueId(@Header("Authorization") String auth);


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pet/GetPetBreed")
    Call<BreedCatRespose> getGetPetBreedApi(@Body BreedParams breedParams);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pet/GetPetAge")
    Call<PetAgeValueResponse> getGetPetAgeApi(@Body BreedParams breedParams);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pet/GetPetColor")
    Call<PetColorValueResponse> getGetPetColorApi(@Body BreedParams breedParams);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pet/GetPetSize")
    Call<PetSizeValueResponse> getGetPetSizeApi(@Body BreedParams breedParams);



    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pet/AddPet")
    Call<AddPetValueResponse> addNewPet(@Header("Authorization") String auth,
                                        @Body AddPetRequset addPetRequset);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pet/UpdatePetDetails")
    Call<AddPetValueResponse> updatePetDetails(@Header("Authorization") String auth,
                                               @Body UpdatePetRequest addPetRequset);


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pet/GetPetDetail")
    Call<GetPetResponse> getPetDetails(@Header("Authorization") String auth,
                                       @Body GetPetListRequest addPetRequset);


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pet/GetPetIdentityCard")
    Call<GetPetResponse> getPetIdentyCard(@Header("Authorization") String auth,
                                          @Body GetPetListRequest addPetRequset);

    @Multipart
    @POST("document/UploadDocument")
    Call<ImageResponse> uploadImages(@Header("Authorization") String auth,
                                     @Part MultipartBody.Part file);

    @Multipart
    @POST("user/ChangeProfileImage")
    Call<ProfileImageResponse> uploadProfile(@Header("Authorization") String auth,
                                             @Part MultipartBody.Part file);

    //GET FIRST RECOMMENDED VACCINE..........................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/GetInitialVaccineDetails")
    Call<GetFirstVaccineResponseData> getInitialVaccineDetails(@Header("Authorization") String auth, @Body GetFirstVaccineRequest getFirstVaccineRequest);

    //GET FIRST NEXT VACCINE..................................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/GetNextVaccinationDateAndName")
    Call<NextVaccineResponse> getNextVaccinationDateAndName(@Header("Authorization") String auth, @Body NextVaccineRequest nextVaccineRequest);


    //reports section.........................................


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("report/GetVisitTypes")
    Call<GetReportsTypeResponse> getReportsType(@Header("Authorization") String auth);

    //GetRecomender Vaccine
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("immunization/GetRecommendedVaccine")
    Call<ImmunizationVaccineResponse> getRecommendedVaccine(@Header("Authorization") String auth, @Body ImmunizationRequestt immunizationRequestt);


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("report/GetPetClinicVisits")
    Call<GetPetClinicVisitListResponse> getPetClinicVisits(@Header("Authorization") String auth, @Body VisitTypeRequest visitTypeRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/GetClinicVisit")
    Call<GetClinicVisitsDetailsResponse> getClinicVisitDetails(@Header("Authorization") String auth, @Body PetClinicVisitDetailsRequest petClinicVisitDetailsRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("report/GetPetTestAndXRay")
    Call<GetPetTestAndXRayResponse> getPetTestAndXRay(@Header("Authorization") String auth, @Body VisitTypeRequest visitTypeRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("report/GetPetLabWork")
    Call<PetLabWorkResponse> getPetLabWorkResponse(@Header("Authorization") String auth, @Body VisitTypeRequest visitTypeRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("report/GetPetHospitalization")
    Call<GetPetHospitalizationResponse> getPetHospitalization(@Header("Authorization") String auth, @Body VisitTypeRequest visitTypeRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/GetTestXRay")
    Call<GetXRayReportDeatilsResponse> getTestXRayDetails(@Header("Authorization") String auth, @Body PetClinicVisitDetailsRequest petClinicVisitDetailsRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/GetPetLabWork")
    Call<GetLabTestReportDeatilsResponse> getPetLabWorkDetails(@Header("Authorization") String auth, @Body PetClinicVisitDetailsRequest petClinicVisitDetailsRequest);


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/GetPetHospitalization")
    Call<GetHospitalizationDeatilsResponse> getPetHospitalizationDetails(@Header("Authorization") String auth, @Body PetClinicVisitDetailsRequest petClinicVisitDetailsRequest);


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/DeleteClinicVisit")
    Call<AddUpdateDeleteClinicVisitResponse> deleteClinicVisit(@Header("Authorization") String auth, @Body PetClinicVisitDetailsRequest petClinicVisitDetailsRequest);


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/DeleteTestXRay")
    Call<AddUpdateDeleteClinicVisitResponse> deleteTestXRay(@Header("Authorization") String auth, @Body PetClinicVisitDetailsRequest petClinicVisitDetailsRequest);


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/DeletePetHospitalization")
    Call<AddUpdateDeleteClinicVisitResponse> deletePetHospitalization(@Header("Authorization") String auth, @Body PetClinicVisitDetailsRequest petClinicVisitDetailsRequest);


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/DeletePetLabWork")
    Call<AddUpdateDeleteClinicVisitResponse> deleteLabTestWork(@Header("Authorization") String auth, @Body PetClinicVisitDetailsRequest petClinicVisitDetailsRequest);


    //All Staff Section .....................................................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("staff/AddStaff")
    Call<GetStaffDetailsResponse> addNewStaff(@Header("Authorization") String auth, @Body AddStaffRequest addStaffRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("staff/UpdateStaff")
    Call<GetUpdateStaffResponse> updateStaff(@Header("Authorization") String auth, @Body UpdateStaffRequest updateStaffRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("staff/GetStaffList")
    Call<GetAllStaffResponse> getAllStaff(@Header("Authorization") String auth);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("staff/GetStaff")
    Call<GetStaffDetailsResponse> getStaffDetails(@Header("Authorization") String auth, @Body StaffDeatilsRequest staffDeatilsRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("staff/ChangeStatus")
    Call<GetStaffStatusResponse> getStaffStatus(@Header("Authorization") String auth, @Body ChangeStaffStatusRequest changeStaffStatusRequest);


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/GetRecentClinicVisits")
    Call<RecentEntrysResponse> getRecentClientcVisits(@Header("Authorization") String auth);

    //CHECK IF A PET EXIST PET IN VET REGISTER........................................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/CheckPetInVetRegister")
    Call<InPetVeterianResponse> checkPetInVetRegister(@Header("Authorization") String auth, @Body InPetRegisterRequest inPetRegisterRequest);

    //SEND OTP TO USER..................................................................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("common/SendOtp")
    Call<OtpResponse> senOtp(@Header("Authorization") String auth, @Body SendOtpRequest inPetRegisterRequest);

    //ADD A PET TO VET REGISTER..........................................................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/AddPetToRegister")
    Call<NewPetRegisterResponse> addPetToRegister(@Header("Authorization") String auth, @Body NewPetRequest newPetRequest);

    //GET ROUTINE FOLLOW-UP TYPE LIST (CLINIC VISIT)......................................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("pethealthrecord/GetClinicVisitRoutineFollowupTypes")
    Call<ClinicVisitResponse> getClinicVisit(@Header("Authorization") String auth);

    //SEARCH TREATMENT & REMARKS..........................................................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/SearchTreatmentRemarks")
    Call<SearchRemaksResponse> getSearchTreatmentRemarks(@Header("Authorization") String auth, @Body SearchRemaksRequest searchRemaksRequest);

    //ADD CLINIC VISIT.....................................................................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/AddClinicVisit")
    Call<AddpetClinicResponse> addClinicVisit(@Header("Authorization") String auth, @Body AddPetClinicRequest addPetClinicRequest);

    //ADD IMMUNAZATION DATA................................................................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/AddPetVaccination")
    Call<AddpetClinicResponse> addPetVaccination(@Header("Authorization") String auth, @Body ImmunizationClinicData immunizationClinicData);

    //Update CLINIC VISIT...................................................................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/UpdateClinicVisit")
    Call<AddpetClinicResponse> updateClinicVisit(@Header("Authorization") String auth, @Body UpdateClinicReportsRequest updateClinicReportsRequest);

    //HOSPITAL TYPE.........................................................................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("pethealthrecord/GetHospitalTypeList")
    Call<HospitalAddmissionTypeResp> getHospitalTypeList(@Header("Authorization") String auth);

    //GET LAB TYPE..........................................................................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("pethealthrecord/GetLabTypeList")
    Call<LabTypeResponse> getLabTypeList(@Header("Authorization") String auth);

    //GET TYPE OF TEST LIST (TEST X-RAY)....................................................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("pethealthrecord/GetTestTypeList")
    Call<XrayTestResponse> getTestTypeList(@Header("Authorization") String auth);

    //Get Last Prescrition...................................................................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/GetLastPrescription")
    Call<GetLastPrescriptionResponse> getLastPrescription(@Header("Authorization") String auth, @Body PetClinicVisitDetailsRequest petClinicVisitDetailsRequest);

    //ADD PET LAB WORK.......................................................................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/AddPetLabWork")
    Call<AddLabWorkResponse> addPetLabWork(@Header("Authorization") String auth, @Body AddLabRequest addLabRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/UpdateLabWork")
    Call<AddLabWorkResponse> updatePetLabWork(@Header("Authorization") String auth, @Body UpdateLabTestRequest updateLabTestRequest);

    //Add Pet Test And Xray

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/AddTestXRay")
    Call<AddTestXRayResponse> addTestXRay(@Header("Authorization") String auth, @Body AddTestXRayRequest addTestXRayRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/UpdateTestXRay")
    Call<AddTestXRayResponse> updateTestXRay(@Header("Authorization") String auth, @Body UpdateXrayRequest updateXrayRequest);

    //ADD PET HOSPITALIZATION


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/AddPetHospitalization")
    Call<AddhospitalizationResposee> addPetHospitalization(@Header("Authorization") String auth, @Body AddHospitalizationRequest addHospitalizationRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/UpdateHospitalization")
    Call<AddhospitalizationResposee> updatePetHospitalization(@Header("Authorization") String auth, @Body UpdateHospitalizationRequest updateHospitalizationRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST(" pet/GetPetIdentityCard")
    Call<PetIdCardResponse> getPetIdCard(@Header("Authorization") String auth, @Body PetClinicVisitDetailsRequest idCardRequest);


    //APPOINTMENTS......................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("appointment/getappointment")
    Call<GetAppointmentResponse> getAppointment(@Header("Authorization") String auth);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("appointment/ApproveRejectAppointment")
    Call<JsonObject> appointmentApproveReject(@Header("Authorization") String auth, @Body AppointmentsStatusRequest appointmentsStatusRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("appointment/CreateAppointment")
    Call<CreateAppointmentResponse> createAppointment(@Header("Authorization") String auth, @Body CreateAppointRequest createAppointRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("appointment/UpdateAppointment")
    Call<CreateAppointmentResponse> updateAppointment(@Header("Authorization") String auth, @Body UpdateAppointmentRequest updateAppointmentRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("user/GetPetParentList")
    Call<GetPetParentResponse> getPetParentList(@Header("Authorization") String auth);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("appointment/GetAppointmentById")
    Call<AppointmentDetailsResponse> getAppointmentsDetails(@Header("Authorization") String auth,
                                                            @Body GetPetListRequest addPetRequset);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("user/EnableDisableOnlineAppointments")
    Call<OnlineAppointmentResponse> onlineAppointmentOnOff(@Header("Authorization") String auth,
                                                           @Body AppointmentsStatusRequest addPetRequset);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("user/EnableTwoStepAuthentication")
    Call<OnlineAppointmentResponse> enableDisableTowFactorAuth(@Header("Authorization") String auth,
                                                               @Body GetPetListRequest addPetRequset);


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("user/EnableTwoStepAuthentication")
    Call<OnlineAppointmentResponse> enableDiasbleSharePrescription(@Header("Authorization") String auth,
                                                               @Body GetPetListRequest addPetRequset);

    //TODO=================GET OPERATING HOURS===========================

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("user/GetOperatingHours")
    Call<WorkingHoursResponse> getOperatingHours(@Header("Authorization") String auth);

    //TODO==================SAVE OPERATING HOURS===========================

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("user/SaveOperatingHours")
    Call<SaveWorkingHoursResponse> saveOperatingHours(@Header("Authorization") String auth, @Body WorkingHoursParameter workingHoursParameter);

    //TODO===================GetStaffPermissionList====================

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("Staff/GetStaffPermissionList")
    Call<StaffPermissionResponse> getStaffPermissionList(@Header("Authorization") String auth, @Body StaffPermissionRequest staffPermissionRequest);

    //TODO===================AssignPermission==========================

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("Staff/AssignPermission")
    Call<StaffPermissionResponse> assignPermission(@Header("Authorization") String auth, @Body AssignRemovePermissionRequest assignRemovePermissionRequest);

    //TODO====================RemovePermission==========================

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("Staff/RemovePermission")
    Call<StaffPermissionResponse> removePermission(@Header("Authorization") String auth, @Body AssignRemovePermissionRequest assignRemovePermissionRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("immunization/GetVaccinationSchedule")
    Call<ImmunizationResponse> getImmunizationList(@Header("Authorization") String auth, @Body ImmunizationRequest immunizationRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("immunization/GetImmunizationModel")
    Call<ImmunizationModelResponse> getImmunizationModel(@Header("Authorization") String auth, @Body ImmunizationRequest immunizationRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("immunization/AddVaccinationSchedule")
    Call<AddEditImmunizationResponse> addImmunizationSchedule(@Header("Authorization") String auth, @Body AddEditImmunizationRequest addEditImmunizationRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("immunization/EditVaccinationSchedule")
    Call<AddEditImmunizationResponse> editImmunizationSchedule(@Header("Authorization") String auth, @Body AddEditImmunizationRequest addEditImmunizationRequest);


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("immunization/DeleteVaccinationSchedule")
    Call<CheckTrueFalseStatus> deleteImmunizationSchedule(@Header("Authorization") String auth, @Body ImmunizationRequest immunizationRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/ViewPetVaccination")
    Call<PetImmunizationRecordResponse> viewPetVaccination(@Header("Authorization") String auth, @Body ImmunizationRequest immunizationRequest);
   // Call<JsonObject> viewPetVaccination(@Header("Authorization") String auth, @Body ImmunizationRequest immunizationRequest);

    //Bank Account .........................................


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("paymentgateway/getaccounts")
    Call<GetBankAccoutsResponse> getBankAccouts(@Header("Authorization") String auth);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("paymentgateway/addbank")
    Call<AddBankAccountResponse> addBankAccount(@Header("Authorization") String auth, @Body AddBankAccountRequest addBankAccountRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("paymentgateway/validateifsccode")
    Call<ValidateIfscCodeResponse> validateIfscCode(@Header("Authorization") String auth, @Body ValidateIfscRequest validateIfscRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("paymentgateway/checkaccountnumber")
    Call<CheckTrueFalseStatus> checkAccountNumber(@Header("Authorization") String auth, @Body CheckAccountRequest checkAccountRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/UpComingVisitsData")
    Call<UpcommingVisitsResponse> getUpcomingVisits(@Header("Authorization") String auth, @Body UpcommingVisitsRequest upcommingVisitsRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/GetMyVisitedPetRecord")
    Call<GetMyVisitPetRecordResponse> getMyVisitedPetRecord(@Header("Authorization") String auth, @Body GetMyVisistPetRecordRequest getMyVisistPetRecordRequest);

    //GetVaccinationScheduleChart

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("immunization/GetVaccinationScheduleChart")
    Call<GetVaccineResponse> getVaccinationScheduleChart(@Header("Authorization") String auth, @Body GetVaccinationRequest getVaccinationRequest);


    //Save Immunization.....................................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("immunization/SaveImmunizationDetails")
    Call<SaveImmunizationResponse> saveImmunizationDetails(@Header("Authorization") String auth, @Body VaccinationRequest vaccinationRequest);

    @POST("pethealthrecord/GetPetImmunizationHistory")
    Call<ImmunizationHistoryResponse> getPetImmunizationHistory(@Body GetVaccinationRequest immunizationHistoryRequest);

    //GET PET AGE UNIT LIST..................................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pet/GetPetAgeUnit")
    Call<PetAgeUnitResponseData> getPetAgeUnit(@Header("Authorization") String auth);

    //GET PET AGE STRING......................................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pet/GetPetAgeString")
    Call<GetPetAgeresponseData> getPetAgeString(@Body GetPetAgeRequestData getPetAgeRequestData);

    //GET DATE OF BIRTH........................................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("pet/GetPetDateOfBirth/{data}")
    Call<DateOfBirthResponse> GetPetDateOfBirth( @Path("data") String data);

    //TEMPORARY SAVE VACCINATION.........................................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("immunization/SaveTemporaryVaccinationDetails")
    Call<SaveResponseData> SaveVaccination(@Header("Authorization") String auth, @Body SaveRequest saveRequest);

    //DELETE VACCINATION.................................................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("immunization/RemoveVaccineDetails")
    Call<JsonObject> removeVaccineDetails(@Header("Authorization") String auth, @Body RemoveRequest removeRequest);

    //SEARCH PET PARENT.......................................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pet/SearchPetParent")
    Call<GetPetParentResponseData> searchPetParent(@Header("Authorization") String auth, @Body SearchPetParentRequestData getPetAgeRequestData);

    //DELETE TEMPORARY VACCINATION............................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("pethealthrecord/DeleteTemporaryVaccination/{PETID}")
    Call<JsonObject> DeleteTemporaryVaccination(@Header("Authorization") String auth, @Path("PETID") String data);

    //SAVE PREVIOUS VACCINATION..............................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("immunization/SavePreviousVaccinationDetails")
    Call<SaveResponseData> savePreviousVaccinationDetails(@Header("Authorization") String auth, @Body SaveRequest saveRequest);

    //SearchClinicVisitFieldData
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("pethealthrecord/SearchClinicVisitFieldData")
    Call<SearchDiagnosisResponseResponse> searchClinicVisitFieldData(@Header("Authorization") String auth);

    //Search Dignisis..........................................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/SearchDiagnosis")
    Call<SearchDiagnosisResponseData> searchDiagnosis(@Header("Authorization") String auth, @Body SearchDiagnosisRequestData searchDiagnosisRequestData);

    //Search Dewormer Name....................................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/SearchDewormerName")
    Call<SearchDiagnosisResponseData> searchDewormerName(@Header("Authorization") String auth, @Body SearchDiagnosisRequestData searchDiagnosisRequestData);

    //Search Dewormer Dose....................................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/SearchDewormerDose")
    Call<SearchDiagnosisResponseData> searchDewormerDose(@Header("Authorization") String auth, @Body SearchDiagnosisRequestData searchDiagnosisRequestData);

    //GET UPCOMMING CLINIC VISITS.............................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("report/GetUpCommingClinicVisits")
    Call<ClinicVisitResponseData> getUpCommingClinicVisits(@Header("Authorization") String auth, @Body ClinicVisitRequest clinicVisitRequest);

    //GET UPCOMMING ONLINE APPOINTMENTS.......................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("report/GetUpCommingOnlineAppointments")
    Call<OnlineClinicResponse> getUpCommingClinicVisits(@Header("Authorization") String auth, @Body OnlineClinicVisitsRequest onlineClinicVisitsRequest);


    //NOTIFY PET PARENT.......................................

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("report/SendNotification")
    Call<JsonObject> sendNotification(@Header("Authorization") String auth, @Body SendNotificationRequest sendNotificationRequest);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("user/UpdateProfileImage")
    Call<JsonObject> updateProfileImage(@Header("Authorization") String auth, @Body UploadVetProfileImageData uploadVetProfileImageData);


    //check Staff Permission
    @GET
    Call<CheckStaffPermissionResponse> getCheckStaffPermission(@Header("Authorization") String auth, @Url String url);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("pethealthrecord/GetLastPrescriptionUrl")
    Call<JsonObject> getLastPrescriptionUrl(@Header("Authorization") String auth, @Body PetClinicVisitDetailsRequest petClinicVisitDetailsRequest );


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("immunization/GetVaccinationTypeByVaccineName")
    Call<VaccinationTypeByVaccineNameResponse> getVaccinationTypeByVaccineName(@Header("Authorization") String auth, @Body VaccinationTypeByVaccineNameRequest vaccinationTypeByVaccineNameRequest );


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("appointment/GetPendingAppointments")
    Call<RequestPendingResponse> getPendingAppointments(@Header("Authorization") String auth, @Body GetAppointmentFromDateRequest getAppointmentFromDateRequest );

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("appointment/GetAppointmentByDate")
    Call<UpcomingAppointmentResponse> getAppointmentByDate(@Header("Authorization") String auth, @Body GetAppointmentFromDateRequest getAppointmentFromDateRequest );


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("user/GetDashboardCounts")
    Call<GetDashboardCountsResponse> getDashboardCounts(@Header("Authorization") String auth);

}


