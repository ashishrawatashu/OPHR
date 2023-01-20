package com.cynoteck.petofyOPHR.params.appointmentParams;

public class CreateAppointParams {
    private String duration;

    private String petid;

    private String isVideoCall;

    private String description;

    private String eventStartTime;

    private String title;

    private String userId;

    private String eventStartDate;

    private String vetId;

    public String getDuration ()
    {
        return duration;
    }

    public void setDuration (String duration)
    {
        this.duration = duration;
    }

    public String getPetid ()
    {
        return petid;
    }

    public void setPetid (String petid)
    {
        this.petid = petid;
    }

    public String getIsVideoCall ()
    {
        return isVideoCall;
    }

    public void setIsVideoCall (String isVideoCall)
    {
        this.isVideoCall = isVideoCall;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getEventStartTime ()
    {
        return eventStartTime;
    }

    public void setEventStartTime (String eventStartTime)
    {
        this.eventStartTime = eventStartTime;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getUserId ()
    {
        return userId;
    }

    public void setUserId (String userId)
    {
        this.userId = userId;
    }

    public String getEventStartDate ()
    {
        return eventStartDate;
    }

    public void setEventStartDate (String eventStartDate)
    {
        this.eventStartDate = eventStartDate;
    }

    public String getVetId ()
    {
        return vetId;
    }

    public void setVetId (String vetId)
    {
        this.vetId = vetId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [duration = "+duration+", petid = "+petid+", isVideoCall = "+isVideoCall+", description = "+description+", eventStartTime = "+eventStartTime+", title = "+title+", userId = "+userId+", eventStartDate = "+eventStartDate+", vetId = "+vetId+"]";
    }
}


