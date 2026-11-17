package com.example.demo.bo;

public class GetStudentBO {
    private Integer userId;
    private String userName;
    private String userIdentity;
    private Integer userSex;
    private String studentEmail;
    private String userPhone;
    private Boolean userAbandon;
    private Integer studentRole;
    private Integer page = 1;
    private Integer pageSize = 10;
    private Integer pageOfSet = 0;
    private Integer userRole;
    private Integer studentClass;

    public Integer getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(Integer studentClass) {
        this.studentClass = studentClass;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public Integer getStudentRole() {
        return studentRole;
    }

    public void setStudentRole(Integer studentRole) {
        this.studentRole = studentRole;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    private void setPageOfSet(Integer page, Integer pageSize) {
        this.pageOfSet = (page - 1) * pageSize;
    }

    public Integer getPageOfSet() {
        return pageOfSet;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        if (page != 0) {
            this.page = page;
            setPageOfSet(page, this.pageSize);
        }
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize != 0) {
            this.pageSize = pageSize;
            setPageOfSet(page, pageSize);
        }
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (!userName.equals(""))
            this.userName = userName;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        if (!userIdentity.equals(""))
            this.userIdentity = userIdentity;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        if (!userPhone.equals(""))
            this.userPhone = userPhone;
    }

    public Boolean getUserAbandon() {
        return userAbandon;
    }

    public void setUserAbandon(Boolean userAbandon) {
        this.userAbandon = userAbandon;
    }
}
