package com.cynoteck.petofyOPHR.params.staffPermission;

public class StaffPermissionRequest {

    private StaffPermissionModel data;

    public StaffPermissionModel getData() {
        return data;
    }

    public void setData(StaffPermissionModel data) {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+ data +"]";
    }
}
