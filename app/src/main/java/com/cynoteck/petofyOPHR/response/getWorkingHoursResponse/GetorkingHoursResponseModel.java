package com.cynoteck.petofyOPHR.response.getWorkingHoursResponse;

public class GetorkingHoursResponseModel {
    private String id;
    private String veterinarianId;
    private String dayId;
    private String startTime;
    private String endTime;
    private String isClosed;
    private String allDayOpen;
    private String day;
    private String veterinarian;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVeterinarianId() {
        return veterinarianId;
    }

    public void setVeterinarianId(String veterinarianId) {
        this.veterinarianId = veterinarianId;
    }

    public String getDayId() {
        return dayId;
    }

    public void setDayId(String dayId) {
        this.dayId = dayId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(String isClosed) {
        this.isClosed = isClosed;
    }

    public String getAllDayOpen() {
        return allDayOpen;
    }

    public void setAllDayOpen(String allDayOpen) {
        this.allDayOpen = allDayOpen;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(String veterinarian) {
        this.veterinarian = veterinarian;
    }

    @Override
    public String toString() {
        return "ClassPojo [" +
                "id=" + id +
                ", veterinarianId='" + veterinarianId +
                ", dayId=" + dayId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", isClosed=" + isClosed +
                ", allDayOpen=" + allDayOpen +
                ", day=" + day +
                ", veterinarian=" + veterinarian +
                "]";
    }
}
