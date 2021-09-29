package com.cynoteck.petofyOPHR.response.getPetDetailsResponse;

import com.cynoteck.petofyOPHR.response.getPetReportsResponse.getPetListResponse.PetDetailImageList;
import com.cynoteck.petofyOPHR.response.getPetReportsResponse.getPetListResponse.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPetData {
    private String id;
    private String userId;
    private String petCategoryId;
    private String petSexId;
    private String petAgeId;
    private String petSizeId;
    private String petColorId;
    private String petBreedId;
    private String petName;
    private String petParentName;
    private String contactNumber;
    private String address;
    private String dateOfBirth;
    private String description;
    private String createDate;
    private String petProfileImageUrl;
    private String firstServiceImageUrl;
    private String secondServiceImageUrl;
    private String thirdServiceImageUrl;
    private String fourthServiceImageUrl;
    private String fifthServiceImageUrl;
    private String encryptedId;
    private User user;
    @SerializedName("petTypeList")
    @Expose
    private List<PetTypeList> petTypeList = null;
    @SerializedName("petSexList")
    @Expose
    private List<PetSexList> petSexList = null;
    @SerializedName("petAgeList")
    @Expose
    private List<PetAgeList> petAgeList = null;
    @SerializedName("petSizeList")
    @Expose
    private List<PetSizeList> petSizeList = null;
    @SerializedName("petColorList")
    @Expose
    private List<PetColorList> petColorList = null;
    @SerializedName("petBreedList")
    @Expose
    private List<PetBreedList> petBreedList = null;
    private String cityList;
    private String stateList;
    private String petType;
    private String petSex;
    private String petAge;
    private String petSize;
    private String petColor;
    private String petCategory;
    private String petBreed;
    private String role;
    private String otherBreedName;
    private String otherColorName;
    private String otherSizeName;
    private String otherAgeName;
    private String numberOfRecords;
    private String pageNumber;
    private String petUniqueId;
    private String isAddedToRegister;
    private String barcodeUrl;
    @SerializedName("petDetailImageList")
    @Expose
    private List<PetDetailImageList> petDetailImageList = null;
    @SerializedName("petTestsAndXrey")
    @Expose
    private List<String> petTestsAndXrey = null;
    @SerializedName("petClinicVisit")
    @Expose
    private List<String> petClinicVisit = null;
    @SerializedName("petHospitalizationsSurgeries")
    @Expose
    private List<String> petHospitalizationsSurgeries = null;
    @SerializedName("petLabwork")
    @Expose
    private List<String> petLabwork = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPetCategoryId() {
        return petCategoryId;
    }

    public void setPetCategoryId(String petCategoryId) {
        this.petCategoryId = petCategoryId;
    }

    public String getPetSexId() {
        return petSexId;
    }

    public void setPetSexId(String petSexId) {
        this.petSexId = petSexId;
    }

    public String getPetAgeId() {
        return petAgeId;
    }

    public void setPetAgeId(String petAgeId) {
        this.petAgeId = petAgeId;
    }

    public String getPetSizeId() {
        return petSizeId;
    }

    public void setPetSizeId(String petSizeId) {
        this.petSizeId = petSizeId;
    }

    public String getPetColorId() {
        return petColorId;
    }

    public void setPetColorId(String petColorId) {
        this.petColorId = petColorId;
    }

    public String getPetBreedId() {
        return petBreedId;
    }

    public void setPetBreedId(String petBreedId) {
        this.petBreedId = petBreedId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetParentName() {
        return petParentName;
    }

    public void setPetParentName(String petParentName) {
        this.petParentName = petParentName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getPetProfileImageUrl() {
        return petProfileImageUrl;
    }

    public void setPetProfileImageUrl(String petProfileImageUrl) {
        this.petProfileImageUrl = petProfileImageUrl;
    }

    public String getFirstServiceImageUrl() {
        return firstServiceImageUrl;
    }

    public void setFirstServiceImageUrl(String firstServiceImageUrl) {
        this.firstServiceImageUrl = firstServiceImageUrl;
    }

    public String getSecondServiceImageUrl() {
        return secondServiceImageUrl;
    }

    public void setSecondServiceImageUrl(String secondServiceImageUrl) {
        this.secondServiceImageUrl = secondServiceImageUrl;
    }

    public String getThirdServiceImageUrl() {
        return thirdServiceImageUrl;
    }

    public void setThirdServiceImageUrl(String thirdServiceImageUrl) {
        this.thirdServiceImageUrl = thirdServiceImageUrl;
    }

    public String getFourthServiceImageUrl() {
        return fourthServiceImageUrl;
    }

    public void setFourthServiceImageUrl(String fourthServiceImageUrl) {
        this.fourthServiceImageUrl = fourthServiceImageUrl;
    }

    public String getFifthServiceImageUrl() {
        return fifthServiceImageUrl;
    }

    public void setFifthServiceImageUrl(String fifthServiceImageUrl) {
        this.fifthServiceImageUrl = fifthServiceImageUrl;
    }

    public String getEncryptedId() {
        return encryptedId;
    }

    public void setEncryptedId(String encryptedId) {
        this.encryptedId = encryptedId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PetTypeList> getPetTypeList() {
        return petTypeList;
    }

    public void setPetTypeList(List<PetTypeList> petTypeList) {
        this.petTypeList = petTypeList;
    }

    public List<PetSexList> getPetSexList() {
        return petSexList;
    }

    public void setPetSexList(List<PetSexList> petSexList) {
        this.petSexList = petSexList;
    }

    public List<PetAgeList> getPetAgeList() {
        return petAgeList;
    }

    public void setPetAgeList(List<PetAgeList> petAgeList) {
        this.petAgeList = petAgeList;
    }

    public List<PetSizeList> getPetSizeList() {
        return petSizeList;
    }

    public void setPetSizeList(List<PetSizeList> petSizeList) {
        this.petSizeList = petSizeList;
    }

    public List<PetColorList> getPetColorList() {
        return petColorList;
    }

    public void setPetColorList(List<PetColorList> petColorList) {
        this.petColorList = petColorList;
    }

    public List<PetBreedList> getPetBreedList() {
        return petBreedList;
    }

    public void setPetBreedList(List<PetBreedList> petBreedList) {
        this.petBreedList = petBreedList;
    }

    public String getCityList() {
        return cityList;
    }

    public void setCityList(String cityList) {
        this.cityList = cityList;
    }

    public String getStateList() {
        return stateList;
    }

    public void setStateList(String stateList) {
        this.stateList = stateList;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getPetSex() {
        return petSex;
    }

    public void setPetSex(String petSex) {
        this.petSex = petSex;
    }

    public String getPetAge() {
        return petAge;
    }

    public void setPetAge(String petAge) {
        this.petAge = petAge;
    }

    public String getPetSize() {
        return petSize;
    }

    public void setPetSize(String petSize) {
        this.petSize = petSize;
    }

    public String getPetColor() {
        return petColor;
    }

    public void setPetColor(String petColor) {
        this.petColor = petColor;
    }

    public String getPetCategory() {
        return petCategory;
    }

    public void setPetCategory(String petCategory) {
        this.petCategory = petCategory;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getOtherBreedName() {
        return otherBreedName;
    }

    public void setOtherBreedName(String otherBreedName) {
        this.otherBreedName = otherBreedName;
    }

    public String getOtherColorName() {
        return otherColorName;
    }

    public void setOtherColorName(String otherColorName) {
        this.otherColorName = otherColorName;
    }

    public String getOtherSizeName() {
        return otherSizeName;
    }

    public void setOtherSizeName(String otherSizeName) {
        this.otherSizeName = otherSizeName;
    }

    public String getOtherAgeName() {
        return otherAgeName;
    }

    public void setOtherAgeName(String otherAgeName) {
        this.otherAgeName = otherAgeName;
    }

    public String getNumberOfRecords() {
        return numberOfRecords;
    }

    public void setNumberOfRecords(String numberOfRecords) {
        this.numberOfRecords = numberOfRecords;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPetUniqueId() {
        return petUniqueId;
    }

    public void setPetUniqueId(String petUniqueId) {
        this.petUniqueId = petUniqueId;
    }

    public String getIsAddedToRegister() {
        return isAddedToRegister;
    }

    public void setIsAddedToRegister(String isAddedToRegister) {
        this.isAddedToRegister = isAddedToRegister;
    }

    public String getBarcodeUrl() {
        return barcodeUrl;
    }

    public void setBarcodeUrl(String barcodeUrl) {
        this.barcodeUrl = barcodeUrl;
    }

    public List<PetDetailImageList> getPetDetailImageList() {
        return petDetailImageList;
    }

    public void setPetDetailImageList(List<PetDetailImageList> petDetailImageList) {
        this.petDetailImageList = petDetailImageList;
    }

    public List<String> getPetTestsAndXrey() {
        return petTestsAndXrey;
    }

    public void setPetTestsAndXrey(List<String> petTestsAndXrey) {
        this.petTestsAndXrey = petTestsAndXrey;
    }

    public List<String> getPetClinicVisit() {
        return petClinicVisit;
    }

    public void setPetClinicVisit(List<String> petClinicVisit) {
        this.petClinicVisit = petClinicVisit;
    }

    public List<String> getPetHospitalizationsSurgeries() {
        return petHospitalizationsSurgeries;
    }

    public void setPetHospitalizationsSurgeries(List<String> petHospitalizationsSurgeries) {
        this.petHospitalizationsSurgeries = petHospitalizationsSurgeries;
    }

    public List<String> getPetLabwork() {
        return petLabwork;
    }

    public void setPetLabwork(List<String> petLabwork) {
        this.petLabwork = petLabwork;
    }
    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", userId = "+userId+", petCategoryId= "+petCategoryId+", petSexId= "+petSexId+", petAgeId= "+petAgeId+", petSizeId= "+petSizeId+"," +
                "petColorId = "+petColorId+", petBreedId = "+petBreedId+", petName= "+petName+", petParentName= "+petParentName+", contactNumber= "+contactNumber+", address= "+address+"," +
                "dateOfBirth = "+dateOfBirth+", description = "+description+", createDate= "+createDate+", petProfileImageUrl= "+petProfileImageUrl+", firstServiceImageUrl= "+firstServiceImageUrl+", secondServiceImageUrl= "+secondServiceImageUrl+"," +
                "thirdServiceImageUrl = "+thirdServiceImageUrl+", fourthServiceImageUrl = "+fourthServiceImageUrl+", fifthServiceImageUrl= "+fifthServiceImageUrl+", encryptedId= "+encryptedId+", user= "+user+", petTypeList= "+petTypeList+"," +
                "petSexList = "+petSexList+", petAgeList = "+petAgeList+", petSizeList= "+petSizeList+", petColorList= "+petColorList+", petBreedList= "+petBreedList+", cityList= "+cityList+"," +
                "stateList = "+stateList+", petType = "+petType+", petSex= "+petSex+", petAge= "+petAge+", petSize= "+petSize+", petColor= "+petColor+"," +
                "petCategory = "+petCategory+", petBreed = "+petBreed+", role= "+role+", otherBreedName= "+otherBreedName+", otherColorName= "+otherColorName+", otherSizeName= "+otherSizeName+"," +
                " otherAgeName = "+otherAgeName+", numberOfRecords = "+numberOfRecords+", pageNumber= "+pageNumber+", petUniqueId= "+petUniqueId+", isAddedToRegister= "+isAddedToRegister+", barcodeUrl= "+barcodeUrl+"," +
                " petDetailImageList = "+petDetailImageList+", petTestsAndXrey = "+petTestsAndXrey+", petClinicVisit= "+petClinicVisit+", petHospitalizationsSurgeries= "+petHospitalizationsSurgeries+", petLabwork= "+petLabwork+"+]";
    }
}
