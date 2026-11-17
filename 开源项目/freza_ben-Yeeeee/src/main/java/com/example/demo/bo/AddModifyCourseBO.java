package com.example.demo.bo;

import com.example.demo.util.TimeUtil;

public class AddModifyCourseBO {
    private Integer courseId;
    private String courseDate;
    private String courseName;
    private Integer[] teacherId;
    private Integer leaderTeacher;
    private String courseSignTime;
    private String courseOutTime;
    private String courseClassroom;
    private String courseNote;
    private Boolean courseAbandon;

    public Boolean getCourseAbandon() {
        return courseAbandon;
    }

    public void setCourseAbandon(Boolean courseAbandon) {
        this.courseAbandon = courseAbandon;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(String courseDate) {
        if (!courseDate.equals("")) {
            this.courseDate = TimeUtil.getYyMmDd(courseDate);
        }
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        if (!courseName.equals(""))
            this.courseName = courseName;
    }

    public Integer[] getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer[] teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getLeaderTeacher() {
        return leaderTeacher;
    }

    public void setLeaderTeacher(Integer leaderTeacher) {
        this.leaderTeacher = leaderTeacher;
    }

    public String getCourseSignTime() {
        return courseSignTime;
    }

    public void setCourseSignTime(String courseSignTime) {
        if (!courseSignTime.equals("")) {
            this.courseSignTime = TimeUtil.getHhMm(courseSignTime);
        }
    }

    public String getCourseOutTime() {
        return courseOutTime;
    }

    public void setCourseOutTime(String courseOutTime) {
        if (!courseOutTime.equals("")) {
            this.courseOutTime = TimeUtil.getHhMm(courseOutTime);
        }
    }

    public String getCourseClassroom() {
        return courseClassroom;
    }

    public void setCourseClassroom(String courseClassroom) {
        if (!courseClassroom.equals(""))
            this.courseClassroom = courseClassroom;
    }

    public String getCourseNote() {
        return courseNote;
    }

    public void setCourseNote(String courseNote) {
        if (!courseNote.equals(""))
            this.courseNote = courseNote;
    }
}
