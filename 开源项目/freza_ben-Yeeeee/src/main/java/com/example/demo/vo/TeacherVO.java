package com.example.demo.vo;

import com.example.demo.enums.Sex;
import com.example.demo.enums.TeacherRole;

public class TeacherVO {
    private Integer id;
    private String userName;
    private Integer userId;
    private String userIdentity;
    private String userSex;
    private String userPhone;
    private String teacherRole;
    private String userAbandon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserSex(Integer sex) {
        this.userSex = Sex.getSex(sex);
    }

    public void setTeacherRole(Integer teacherRole) {
        this.teacherRole = TeacherRole.getTeacherByValue(teacherRole);
    }

    public void setUserAbandon(Boolean userAbandon) {
        if (userAbandon) {
            this.userAbandon = "停用";
        } else {
            this.userAbandon = "有效";
        }
    }


    public String getUserSex() {
        return userSex;
    }

    public String getTeacherRole() {
        return teacherRole;
    }

    public String getUserAbandon() {
        return userAbandon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
