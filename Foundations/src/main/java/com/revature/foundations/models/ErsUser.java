package com.revature.foundations.models;

import java.util.Objects;

public class ErsUser {

        // POJO = Plain Ol' Java Object
// Contains NO BUSINESS LOGIC
// Simple encapsulation of some domain object's states

        private String userId;
        private String username;
        private String email;
        private String password;
        private String givenName;
        private String surname;
        private Boolean isActive;
        private ErsUserRoles role;

    public ErsUser() { super();
    }


    public ErsUser(String username, String email, String password, String givenName, String surname, Boolean isActive, ErsUserRoles roleId) {

        this.username = username;
        this.email = email;
        this.password = password;
        this.givenName = givenName;
        this.surname = surname;
        this.isActive = isActive;
        this.role = roleId;
    }

    public ErsUser(String username, String email, String password, String givenName, String surname, Boolean isActive) {

        this.username = username;
        this.email = email;
        this.password = password;
        this.givenName = givenName;
        this.surname = surname;
        this.isActive = isActive;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public ErsUserRoles getRole() {
        return role;
    }

    public void setRole(ErsUserRoles role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErsUser ersUser = (ErsUser) o;
        return isActive == ersUser.isActive && Objects.equals(userId, ersUser.userId) && Objects.equals(username, ersUser.username) && Objects.equals(email, ersUser.email) && Objects.equals(password, ersUser.password) && Objects.equals(givenName, ersUser.givenName) && Objects.equals(surname, ersUser.surname) && Objects.equals(role, ersUser.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, email, password, givenName, surname, isActive, role);
    }

    @Override
    public String toString() {
        return "ErsUser{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", givenName='" + givenName + '\'' +
                ", surname='" + surname + '\'' +
                ", isActive=" + isActive +
                ", role=" + role +
                '}';
    }

}


