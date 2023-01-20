package com.cynoteck.petofyOPHR.params.assignAndRemovePermission;

public class AssignRemovePermissionModel {

    private String userId;
    private String permissionId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [userId = "+userId+", permissionId = "+permissionId+"]";
    }
}
