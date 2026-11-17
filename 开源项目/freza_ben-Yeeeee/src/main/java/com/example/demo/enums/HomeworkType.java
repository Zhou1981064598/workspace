package com.example.demo.enums;

public enum HomeworkType {
    DAILY(1, 1, "日常作业"),
    STAGE(2, 1, "阶段性作业"),
    FINAL(3, 2, "最终交付作业");
    private final String values;
    private final Integer code;
    private final Integer permission;

    HomeworkType(Integer code, Integer permission, String values) {
        this.values = values;
        this.permission = permission;
        this.code = code;
    }

    public String getValues() {
        return values;
    }

    public Integer getCode() {
        return code;
    }

    public Integer getPermission() {
        return permission;
    }

    public static Boolean permissionConfirm(Integer homeworkType, Integer permission) {
        switch (homeworkType) {
            case 1: {
                return DAILY.permission <= permission;
            }
            case 2: {
                return STAGE.permission <= permission;
            }
            case 3: {
                return FINAL.permission <= permission;
            }
            default:
                return false;
        }
    }
}
