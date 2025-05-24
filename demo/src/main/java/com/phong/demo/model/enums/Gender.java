package com.phong.demo.model.enums;

public enum Gender {
    male("Nam"),
    female("Nữ"),
    other("Khác");

    private final String label;

    Gender(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
