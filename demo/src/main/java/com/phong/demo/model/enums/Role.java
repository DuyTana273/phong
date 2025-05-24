package com.phong.demo.model.enums;

public enum Role {
    admin("Quản trị viên"),
    employee("Nhân viên"),
    customer("Khách hàng");

    private final String label;
    Role(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }
}
