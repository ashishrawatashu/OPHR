package com.cynoteck.petofyOPHR.params.assignAndRemovePermission;

public class AssignRemovePermissionRequest {

    private AssignRemovePermissionModel data;

    public AssignRemovePermissionModel getData() {
        return data;
    }

    public void setData(AssignRemovePermissionModel data) {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+"]";
    }
}
