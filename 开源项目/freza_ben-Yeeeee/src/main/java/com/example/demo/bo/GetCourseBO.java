package com.example.demo.bo;

import java.util.List;

public class GetCourseBO {
    private String courseStart;
    private String courseEnd;
    private String courseName;
    private Integer leaderTeacherId;
    private List<Integer> courseId;
    private Integer teacherId;
    private Integer studentId;
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

    public List<Integer> getCourseId() {
        return courseId;
    }

    public void setCourseId(List<Integer> courseId) {
        this.courseId = courseId;
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

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getPageOfSet() {
        return pageOfSet;
    }

    private void setPageOfSet(Integer page, Integer pageSize) {
        this.pageOfSet = (page - 1) * pageSize;
    }


    public String getCourseStart() {
        return courseStart;
    }

    public void setCourseStart(String courseStart) {
        this.courseStart = courseStart;
    }

    public String getCourseEnd() {
        return courseEnd;
    }

    public void setCourseEnd(String courseEnd) {
        this.courseEnd = courseEnd;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getLeaderTeacherId() {
        return leaderTeacherId;
    }

    public void setLeaderTeacherId(Integer leaderTeacherId) {
        this.leaderTeacherId = leaderTeacherId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}
