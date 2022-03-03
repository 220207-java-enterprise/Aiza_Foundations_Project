package com.revature.foundations.models;

import java.util.Objects;

public class ErsReimbursementTypes {

    private String typeId;
    private String type;

    public ErsReimbursementTypes() {
        super(); // not required, but it bugs me personally not to have it
    }

    public ErsReimbursementTypes(String typeId, String type) {
        this.typeId = typeId;
        this.type = type;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErsReimbursementTypes that = (ErsReimbursementTypes) o;
        return Objects.equals(typeId, that.typeId) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, type);
    }

    @Override
    public String toString() {
        return "ErsReimbursementTypes{" +
                "typeId='" + typeId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
