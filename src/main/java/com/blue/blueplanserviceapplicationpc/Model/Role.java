package com.blue.blueplanserviceapplicationpc.Model;

public class Role {

    private Integer roleId;
    private String roleName;
    private String roleDescription;
    private String rolePrivilege;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getRolePrivilege() {
        return rolePrivilege;
    }

    public void setRolePrivilege(String rolePrivilege) {
        this.rolePrivilege = rolePrivilege;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleDescription='" + roleDescription + '\'' +
                ", rolePrivilege='" + rolePrivilege + '\'' +
                '}';
    }

}
