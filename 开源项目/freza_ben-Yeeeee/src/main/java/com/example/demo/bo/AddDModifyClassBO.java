package com.example.demo.bo;

public class AddDModifyClassBO {
    private Integer id;
    private Integer majorId;
    private Integer departmentId;
    private Integer gradeId;
    private Integer classId;
    private Integer teacherId;
    private Boolean classGraduated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Boolean getClassGraduated() {
        return classGraduated;
    }

    public void setClassGraduated(Boolean classGraduated) {
        this.classGraduated = classGraduated;
    }
}
