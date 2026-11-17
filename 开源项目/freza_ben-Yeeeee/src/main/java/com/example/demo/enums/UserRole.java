package com.example.demo.enums;

public enum UserRole {
    STUDENT(1),
    TEACHER(2),
    ADMINISTRATOR(3);

    private final Integer value;

    UserRole(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static Boolean isUserRole(Integer value) {
        return value > 0 && value < 4;
    }
}
