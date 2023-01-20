package com.cynoteck.petofyOPHR.params.sendNotificationParams;

public class SendNotificationRequest {
    private NotificationParameter data;

    public NotificationParameter getData() {
        return data;
    }

    public void setData(NotificationParameter data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ClassPojo[" +
                "data=" + data +
                "]";
    }
}
