package com.example.demo.enums;

public enum Sex {
    MALE(1),
    FEMALE(2);
    private final Integer value;

    Sex(Integer sex) {
        this.value = sex;
    }

    public Integer getValue() {
        return value;
    }


    public static Boolean isSex(Integer value) {
        return value == 2 || value == 1;
    }

    public static String getSex(Integer value) {
        switch (value) {
            case 1:
                return "男";
            case 2:
                return "女";
        }
        return null;
    }
}
