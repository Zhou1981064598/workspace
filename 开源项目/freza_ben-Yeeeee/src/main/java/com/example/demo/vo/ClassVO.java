package com.example.demo.vo;

public class ClassVO {
    private Integer id;
    private String departmentName;
    private String majorName;
    private String gradeName;
    private String classBaseName;
    private String classGraduate;
    private Integer teacherId;
    private Integer totalStudent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getClassBaseName() {
        return classBaseName;
    }

    public void setClassBaseName(String classBaseName) {
        this.classBaseName = classBaseName;
    }

    public String getClassGraduate() {
        return classGraduate;
    }

    public void setClassGraduate(Boolean classGraduate) {
        if (classGraduate) {
            this.classGraduate = "是";
        } else {
            this.classGraduate = "否";
        }
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getTotalStudent() {
        return totalStudent;
    }

    public void setTotalStudent(Integer totalStudent) {
        this.totalStudent = totalStudent;
    }
}
