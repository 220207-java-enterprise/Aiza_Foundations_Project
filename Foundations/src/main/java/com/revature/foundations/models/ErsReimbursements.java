package com.revature.foundations.models;

import java.util.Objects;

public class ErsReimbursements{

    private String reimbId;
    private int amount;
    private int submitted;
    private int resolved;
    private String description;
    private byte receipt;
    private String paymentId;
    private String authorId;
    private String resolverId;
    private String statusId;
    private String typeId;


    public ErsReimbursements() {
        super();
    }

    public ErsReimbursements(String reimbId, int amount, int submitted, int resolved, String description, byte receipt, String paymentId,
            String authorId, String resolverId, String statusId,String typeId){
        this.reimbId = reimbId;
        this.amount = amount;
        this.submitted = submitted;
        this.resolved = resolved;
        this.description = description;
        this.receipt = receipt;
        this.paymentId = paymentId;
        this.authorId = authorId;
        this.resolverId = resolverId;
        this.statusId = statusId;
        this.typeId = typeId;

    }

    public String getReimbId() {
        return reimbId;
    }

    public void setReimbId(String id) {
        this.reimbId = reimbId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getSubmitted() {
        return submitted;
    }

    public void setSubmitted(String submitted) { this.submitted = submitted; }

    public String getResolved() { return resolved; }

    public void setResolved(String resolved) {
        this.resolved = resolved;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReceipt() { return receipt; }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getPaymentId() { return paymentId; }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getAuthorId() { return authorId; }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getResolverId() { return resolverId; }

    public void setResolverId(String resolverId) {
        this.resolverId = resolverId;
    }

    public String getStatusId() { return statusId; }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getTypeId() { return typeId; }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErsReimbursements ersReimbursements = (ErsReimbursements) o;
        return Objects.equals(reimbId, ersReimbursements.reimbId) && Objects.equals(amount, ersReimbursements.amount)
                && Objects.equals(submitted, ersReimbursements.submitted) && Objects.equals(resolved, ersReimbursements.resolved)
                && Objects.equals(description, ersReimbursements.description) && Objects.equals(receipt, ersReimbursements.receipt)
                && Objects.equals(paymentId, ersReimbursements.paymentId) && Objects.equals(authorId, ersReimbursements.authorId)
                && Objects.equals(resolverId, ersReimbursements.resolverId) && Objects.equals(statusId, ersReimbursements.statusId)
                && Objects.equals(typeId, ersReimbursements.typeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionText, answerText, creatorId);
    }

    @Override
    public String toString() {
        return "ErsReimbursements{" +
                "reimbId='" + reimbId + '\'' +
                ", amount='" + amount + '\'' +
                ", submitted='" + submitted + '\'' +
                ", resolved='" + resolved + '\'' +
                ", description=" + description + '\'' +
                ", receipt=" + receipt + '\'' +
                ", paymentId=" + paymentId + '\'' +
                ", authorId=" + authorId + '\'' +
                ", resolverId=" + resolverId + '\'' +
                ", statusId=" + statusId + '\'' +
                '}';
    }

}
