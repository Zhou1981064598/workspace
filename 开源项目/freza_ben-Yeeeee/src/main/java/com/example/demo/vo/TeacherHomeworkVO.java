package com.example.demo.vo;

public class TeacherHomeworkVO {
    private Integer id;
    private String createTime;
    private String courseName;
    private String homeworkTitle;
    private Integer homeworkType;
    private String teacher;
    private String homeworkExpiry;
    private String groupName;
    private Integer totalStudent;
    private Integer unCommitStudent;
    private Integer unCorrectStudent;
    private Integer correctStudent;
    private String homeworkMain;

    public String getHomeworkMain() {
        return homeworkMain;
    }

    public void setHomeworkMain(String homeworkMain) {
        this.homeworkMain = homeworkMain;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getTotalStudent() {
        return totalStudent;
    }

    public void setTotalStudent(Integer totalStudent) {
        this.totalStudent = totalStudent;
    }

    public Integer getUnCommitStudent() {
        return unCommitStudent;
    }

    public void setUnCommitStudent(Integer unCommitStudent) {
        this.unCommitStudent = unCommitStudent;
    }

    public Integer getUnCorrectStudent() {
        return unCorrectStudent;
    }

    public void setUnCorrectStudent(Integer unCorrectStudent) {
        this.unCorrectStudent = unCorrectStudent;
    }

    public Integer getCorrectStudent() {
        return correctStudent;
    }

    public void setCorrectStudent(Integer correctStudent) {
        this.correctStudent = correctStudent;
    }
}
