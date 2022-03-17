//package com.revature.foundations.dtos.requests;
//
//
//import java.sql.Timestamp;
//
//public class NewReimbRequest {
//    private double amount;
//    private Timestamp submitted;
//    private String description;//TODO do I need description or Id?
//
//    public NewReimbRequest() { super();
//    }
//
//    public NewReimbRequest(double amount, Timestamp submitted, String description) {
//        this.amount = amount;
//        this.submitted = submitted;
//        this.description = description;
//    }
//
//    public double getAmount() {
//        return amount;
//    }
//
//    public void setAmount(double amount) {
//        this.amount = amount;
//    }
//
//    public Timestamp getSubmitted() {
//        return submitted;
//    }
//
//    public void setSubmitted(Timestamp submitted) {
//        this.submitted = submitted;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//      public ErsReimbursements extractUser(){
//        return new ErsReimbursements(amount, submitted, description);

//    @Override
//    public String toString() {
//        return "NewReimbRequest{" +
//                "amount=" + amount +
//                ", submitted=" + submitted +
//                ", description='" + description + '\'' +
//                '}';
//    }
//}
