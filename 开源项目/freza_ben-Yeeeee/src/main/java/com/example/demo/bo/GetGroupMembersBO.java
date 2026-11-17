package com.example.demo.bo;

public class GetGroupMembersBO {
    private String groupName;
    private String studentName;
    private Integer studentId;
    private Integer groupId;
    private Integer majorId;
    private Integer gradeId;
    private Integer studentClassId;
    private Integer departmentId;
    private Integer courseId;
    private Integer page = 1;
    private Integer pageSize = 10;
    private Integer pageOfSet = 0;

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
        if (page != 0) {
            this.pageSize = pageSize;
            setPageOfSet(this.page, pageSize);
        }
    }

    public Integer getPageOfSet() {
        return pageOfSet;
    }

    private void setPageOfSet(Integer page, Integer pageSize) {
        this.pageOfSet = (page - 1) * pageSize;
    }


    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getStudentClassId() {
        return studentClassId;
    }

    public void setStudentClassId(Integer studentClassId) {
        this.studentClassId = studentClassId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
