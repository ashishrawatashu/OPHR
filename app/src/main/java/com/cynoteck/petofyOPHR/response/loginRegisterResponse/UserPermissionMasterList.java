package com.cynoteck.petofyOPHR.response.loginRegisterResponse;

public class UserPermissionMasterList
{
    private String role;

    private String roleId;

    private String isSelected;

    private String id;

    private String permissionCode;

    private String permissionName;

    public String getRole ()
{
    return role;
}

    public void setRole (String role)
    {
        this.role = role;
    }

    public String getRoleId ()
    {
        return roleId;
    }

    public void setRoleId (String roleId)
    {
        this.roleId = roleId;
    }

    public String getIsSelected ()
    {
        return isSelected;
    }

    public void setIsSelected (String isSelected)
    {
        this.isSelected = isSelected;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getPermissionCode ()
    {
        return permissionCode;
    }

    public void setPermissionCode (String permissionCode)
    {
        this.permissionCode = permissionCode;
    }

    public String getPermissionName ()
    {
        return permissionName;
    }

    public void setPermissionName (String permissionName)
    {
        this.permissionName = permissionName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [role = "+role+", roleId = "+roleId+", isSelected = "+isSelected+", id = "+id+", permissionCode = "+permissionCode+", permissionName = "+permissionName+"]";
    }
}