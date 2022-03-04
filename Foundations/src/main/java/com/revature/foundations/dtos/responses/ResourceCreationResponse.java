package com.revature.foundations.dtos.responses;

public class ResourceCreationResponse {

    private String userId;

    public ResourceCreationResponse(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
