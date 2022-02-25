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
        private boolean isActive;
        private String roleId;


        public ErsUser() {
            super(); // not required, but it bugs me personally not to have it
        }

        public ErsUser(String userId, String username, String email, String password,
                       String givenName, String surname, boolean isActive, String roleId) {
            this.userId = userId;
            this.username = username;
            this.email = email;
            this.password = password;
            this.givenName = givenName;
            this.surname = surname;
            this.isActive = isActive;
            this.roleId = roleId;

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

        public boolean getIsActive() {
            return isActive;
        }

        public void setIsActive(boolean isActive) {
            this.isActive = isActive;
        }

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }



        public String toFileString() {
            return new StringBuilder(userId).append(":")
                    .append(username).append(":")
                    .append(email).append(":")
                    .append(password).append(":")
                    .append(givenName).append(":")
                    .append(surname).append(":")
                    .append(isActive).append(":")
                    .append(roleId).toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            com.revature.foundations.models.ErsUser ersUser = (com.revature.foundations.models.ErsUser) o;
            return Objects.equals(userId, ersUser.userId) && Objects.equals(username, ersUser.username)
                    && Objects.equals(email, ersUser.email) && Objects.equals(password, ersUser.password)
                    && Objects.equals(givenName, ersUser.givenName) && Objects.equals(surname, ersUser.surname)
                    && Objects.equals(isActive, ersUser.isActive) && Objects.equals(roleId, ersUser.roleId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId, username, email, password, givenName, surname,isActive, roleId);
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
                    ", isActive=" + isActive + '\'' +
                    ", roleId=" + roleId + '\'' +
                    '}';
        }

    }


