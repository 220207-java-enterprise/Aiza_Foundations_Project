package com.revature.foundations.dtos.responses;

import com.revature.foundations.models.ErsUser;

public class AppUserResponse {

    private String userId;
    private String givenName;
    private String surname;
    private String username;
    private String role;

    public AppUserResponse() {
        super();
    }

    public AppUserResponse(ErsUser user) {
        this.userId = user.getUserId();
        this.givenName = user.getGivenName();
        this.surname = user.getSurname();
        this.username = user.getUsername();
        this.role = user.getRole();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
