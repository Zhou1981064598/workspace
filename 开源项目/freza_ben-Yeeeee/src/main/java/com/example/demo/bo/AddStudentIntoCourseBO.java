package com.example.demo.bo;

public class AddStudentIntoCourseBO {
    private Integer[] studentId;
    private Integer[] groupId;
    private Integer courseId;

    public Integer[] getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer[] studentId) {
        this.studentId = studentId;
    }

    public Integer[] getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer[] groupId) {
        this.groupId = groupId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
