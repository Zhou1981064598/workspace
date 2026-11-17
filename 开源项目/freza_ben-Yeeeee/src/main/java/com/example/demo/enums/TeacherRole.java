package com.example.demo.enums;

public enum TeacherRole {
    EXPERIMENTER(1),//实验员
    LECTURER(2),//讲师
    ASSOCIATE_PROFESSOR(3),//副教授
    PROFESSOR(4),//教授
    DIRECTOR(5)//主任
    ;


    private final Integer value;

    TeacherRole(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static Boolean isTeacherRole(Integer value) {
        return value > 0 && value < 6;
    }

    public static String getTeacherByValue(Integer value) {
        switch (value) {
            case 1:
                return "实验员";
            case 2:
                return "讲师";
            case 3:
                return "副教授";
            case 4:
                return "教授";
            case 5:
                return "主任";
        }
        return null;
    }
}
