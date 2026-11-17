package com.example.demo.bo;

public class AddModifyHomeworkBO {
    private Integer homeworkId;
    private String homeworkTitle;
    private String homeworkExpiry;
    private Integer courseId;
    private Integer homeworkType;
    private String homeworkMain;

    private Integer[] groupId;
    private Integer[] studentId;


    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getHomeworkTitle() {
        return homeworkTitle;
    }

    public void setHomeworkTitle(String homeworkTitle) {
        if (!homeworkTitle.equals(""))
            this.homeworkTitle = homeworkTitle;
    }

    public String getHomeworkExpiry() {
        return homeworkExpiry;
    }

    public void setHomeworkExpiry(String homeworkExpiry) {
        if (!homeworkExpiry.equals(""))
            this.homeworkExpiry = homeworkExpiry;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getHomeworkType() {
        return homeworkType;
    }

    public void setHomeworkType(Integer homeworkType) {
        this.homeworkType = homeworkType;
    }

    public String getHomeworkMain() {
        return homeworkMain;
    }

    public void setHomeworkMain(String homeworkMain) {
        if (!homeworkMain.equals(""))
            this.homeworkMain = homeworkMain;
    }

    public Integer[] getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer[] groupId) {
        this.groupId = groupId;
    }

    public Integer[] getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer[] studentId) {
        this.studentId = studentId;
    }
}
