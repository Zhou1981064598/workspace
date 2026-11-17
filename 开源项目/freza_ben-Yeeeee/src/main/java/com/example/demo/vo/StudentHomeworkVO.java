package com.example.demo.vo;

public class StudentHomeworkVO {
    private Integer id;
    private String createTime;
    private String courseName;
    private String homeworkTitle;
    private Integer homeworkType;
    private String teacher;
    private String homeworkExpiry;
    private Integer homeworkStatus;
    private String lastUpgradeTime;
    private String homeworkScore;
    private String homeworkTeacherNote;
    private String homeworkAnswer;

    public String getHomeworkAnswer() {
        return homeworkAnswer;
    }

    public void setHomeworkAnswer(String homeworkAnswer) {
        this.homeworkAnswer = homeworkAnswer;
    }

    public String getHomeworkTeacherNote() {
        return homeworkTeacherNote;
    }

    public void setHomeworkTeacherNote(String homeworkTeacherNote) {
        this.homeworkTeacherNote = homeworkTeacherNote;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getHomeworkTitle() {
        return homeworkTitle;
    }

    public void setHomeworkTitle(String homeworkTitle) {
        this.homeworkTitle = homeworkTitle;
    }

    public Integer getHomeworkType() {
        return homeworkType;
    }

    public void setHomeworkType(Integer homeworkType) {
        this.homeworkType = homeworkType;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getHomeworkExpiry() {
        return homeworkExpiry;
    }

    public void setHomeworkExpiry(String homeworkExpiry) {
        this.homeworkExpiry = homeworkExpiry;
    }

    public Integer getHomeworkStatus() {
        return homeworkStatus;
    }

    public void setHomeworkStatus(Integer homeworkStatus) {
        this.homeworkStatus = homeworkStatus;
    }

    public String getLastUpgradeTime() {
        return lastUpgradeTime;
    }

    public void setLastUpgradeTime(String lastUpgradeTime) {
        this.lastUpgradeTime = lastUpgradeTime;
    }

    public String getHomeworkScore() {
        return homeworkScore;
    }

    public void setHomeworkScore(String homeworkScore) {
        this.homeworkScore = homeworkScore;
    }
}
