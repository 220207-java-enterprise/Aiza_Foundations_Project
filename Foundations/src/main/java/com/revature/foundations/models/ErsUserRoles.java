package com.revature.foundations.models;

import java.util.Objects;

public class ErsUserRoles {

    private String roleId;
    private String role;

    public ErsUserRoles() {
        super(); // not required, but it bugs me personally not to have it
    }

    public ErsUserRoles(String roleId, String role) {
        this.roleId = roleId;
        this.role = role;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErsUserRoles that = (ErsUserRoles) o;
        return Objects.equals(roleId, that.roleId) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, role);
    }

    @Override
    public String toString() {
        return "ErsUserRoles{" +
                "roleId='" + roleId + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
