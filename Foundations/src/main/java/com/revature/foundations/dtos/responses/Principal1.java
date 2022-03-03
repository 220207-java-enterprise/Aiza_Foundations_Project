package com.revature.foundations.dtos.responses;

import com.revature.foundations.models.ErsUser;

public class Principal1 {

    private String userId;
    private String username;
    private String role;

    public Principal1() {
        super();
    }

    public Principal1(ErsUser user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.role = user.getRole().getRole();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "Principal{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}

