package com.example.demo.bo;

import java.io.Serializable;

public class AddModifyUserBO implements Serializable {
    private String userName;
    private Integer userId;
    private String userIdentity;
    private Integer userSex;
    private String userPhone;
    private Integer userRole;
    private Integer teacherRole;
    private String studentEmail;
    private Integer id;
    private Boolean userAbandon;
    private Integer studentRole;
    private Integer studentClassId;

    public Integer getStudentRole() {
        return studentRole;
    }

    public Integer getStudentClassId() {
        return studentClassId;
    }

    public void setStudentClassId(Integer studentClassId) {
        this.studentClassId = studentClassId;
    }

    public void setStudentRole(Integer studentRole) {
        this.studentRole = studentRole;
    }

    public Boolean getUserAbandon() {
        return userAbandon;
    }

    public void setUserAbandon(Boolean userAbandon) {
        this.userAbandon = userAbandon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (!userName.equals("")) {
            this.userName = userName;
        }
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
        if (!userIdentity.equals("")) {
            this.userIdentity = userIdentity;
        }
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getUserPhone() {

        return userPhone;

    }

    public void setUserPhone(String userPhone) {
        if (!userPhone.equals("")) {
            this.userPhone = userPhone;
        }
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public Integer getTeacherRole() {
        return teacherRole;
    }

    public void setTeacherRole(Integer teacherRole) {
        this.teacherRole = teacherRole;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        if (!studentEmail.equals("")) {
            this.studentEmail = studentEmail;
        }
    }
}
