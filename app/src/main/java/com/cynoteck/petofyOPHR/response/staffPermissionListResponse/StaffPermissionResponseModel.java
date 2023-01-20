package com.cynoteck.petofyOPHR.response.staffPermissionListResponse;

public class StaffPermissionResponseModel {
    
    private String id;
    private String permissionName;
    private String roleId;
    private String isSelected;
    private String role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", permissionName = "+permissionName+", roleId = "+roleId+", isSelected = "+isSelected+", role= "+role+"]";
    }

}
