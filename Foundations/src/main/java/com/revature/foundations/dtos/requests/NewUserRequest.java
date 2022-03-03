package com.revature.foundations.dtos.requests;

import com.revature.foundations.models.ErsUser;
import com.revature.foundations.models.ErsUserRoles;

public class NewUserRequest {

    private String username;
    private String email;
    private String password;
    private String givenName;
    private String surname;
    private Boolean isActive;
    private ErsUserRoles roleId;

    public NewUserRequest() {super();
    }

    public NewUserRequest(String username, String email, String password, String givenName, String surname, Boolean isActive, ErsUserRoles roleId) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.givenName = givenName;
        this.surname = surname;
        this.isActive = isActive;
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public ErsUserRoles getRoleId() {
        return roleId;
    }

    public void setRoleId(ErsUserRoles roleId) {
        this.roleId = roleId;
    }

    public ErsUser extractUser(){
        return new ErsUser(username, email, password, givenName, surname, isActive, roleId);
    }

    @Override
    public String toString() {
        return "NewUserRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", givenName='" + givenName + '\'' +
                ", surname='" + surname + '\'' +
                ", isActive=" + isActive +
                ", roleId=" + roleId +
                '}';
    }
}

