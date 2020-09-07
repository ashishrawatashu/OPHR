package com.cynoteck.petofyvet.params.appointmentParams;

public class UpdateAppointmentParams {
    private String duration;

    private String description;

    private String eventStartTime;

    private String id;

    private String title;

    private String userId;

    private String eventStartDate;

    public String getDuration ()
    {
        return duration;
    }

    public void setDuration (String duration)
    {
        this.duration = duration;
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

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
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

    @Override
    public String toString()
    {
        return "ClassPojo [duration = "+duration+", description = "+description+", eventStartTime = "+eventStartTime+", id = "+id+", title = "+title+", userId = "+userId+", eventStartDate = "+eventStartDate+"]";
    }
}


