package com.cynoteck.petofyOPHR.params.staffPermission;

public class StaffPermissionModel {

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [userId= "+userId+"]";
    }
}
