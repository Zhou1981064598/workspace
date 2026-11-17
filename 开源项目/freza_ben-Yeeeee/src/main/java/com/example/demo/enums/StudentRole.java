package com.example.demo.enums;

public enum StudentRole {
    FRONT_DEVELOPMENT(1, "前端开发"),
    BACKEND_DEVELOPMENT(2, "后端开发"),
    TEST(3, "测试"),
    PROJECT_MANAGER(4, "项目经理"),
    PRODUCT_MANAGER(5, "产品经理"),
    UI(6, "UI");

    private final Integer roleId;
    private final String value;

    StudentRole(Integer roleId, String value) {
        this.roleId = roleId;
        this.value = value;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public String getValue() {
        return value;
    }

    public static Boolean isStudentRole(Integer id) {
        return id > 0 && id < 7;
    }
}
