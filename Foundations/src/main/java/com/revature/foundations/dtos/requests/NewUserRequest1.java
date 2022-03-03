//package com.revature.foundations.dtos.requests;
//
//import com.revature.foundations.models.ErsUser;
//
//public class NewUserRequest1 {
//    private String userId;
//    private String username;
//    private String email;
//    private String password;
//    private String givenName;
//    private String surname;
//    private boolean isActive;
//    private String roleId;
//
//
//    public NewUserRequest1() {
//        super(); // not required, but it bugs me personally not to have it
//    }
//
//    public NewUserRequest1(String username, String email, String password,
//                   String givenName, String surname, boolean isActive) {
//
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.givenName = givenName;
//        this.surname = surname;
//        this.isActive = isActive;
//
//    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getGivenName() {
//        return givenName;
//    }
//
//    public void setGivenName(String givenName) {
//        this.givenName = givenName;
//    }
//
//    public String getSurname() {
//        return surname;
//    }
//
//    public void setSurname(String surname) {
//        this.surname = surname;
//    }
//
//    public boolean getIsActive() {
//        return isActive;
//    }
//
//    public void setIsActive(boolean isActive) {
//        this.isActive = isActive;
//    }
//
//    public String getRoleId() {
//        return roleId;
//    }
//
//    public void setRoleId(String roleId) {
//        this.roleId = roleId;
//    }
//    public ErsUser extractErsUser() {
//        return new ErsUser(userId, username, email, password, givenName, surname, isActive, roleId);
//    }
//
//        @Override
//        public String toString () {
//            return "NewUserRequest1{" +
//                    "userId='" + userId + '\'' +
//                    ", username='" + username + '\'' +
//                    ", email='" + email + '\'' +
//                    ", password='" + password + '\'' +
//                    ", givenName='" + givenName + '\'' +
//                    ", surname='" + surname + '\'' +
//                    ", isActive=" + isActive +
//                    ", roleId='" + roleId + '\'' +
//                    '}';
//        }
//
//    }
