package com.revature.foundations.models;

import java.util.Objects;

public class ErsReimbursementStatuses {

    private String statusId;
    private String status;

    public ErsReimbursementStatuses() {
        super(); // not required, but it bugs me personally not to have it
    }

    public ErsReimbursementStatuses(String statusId, String status) {
        this.statusId = statusId;
        this.status = status;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErsReimbursementStatuses that = (ErsReimbursementStatuses) o;
        return Objects.equals(statusId, that.statusId) && Objects.equals(status, that.status);
    }


    @Override
    public int hashCode() {
        return Objects.hash(statusId, status);
    }

        @Override
        public String toString() {
            return "ErsReimbursementStatuses{" +
                    "statusId='" + statusId + '\'' +
                    ", status='" + status + '\'' +
                    '}';
        }

}
