package com.example.demo.bo;

public class GetHomeworkBO {
    private Integer id;
    private String dateStart;
    private String dateEnd;
    private Integer courseId;
    private String homeworkTitle;
    private Integer teacherId;
    private Integer groupId;
    private Integer homeworkType;
    private Integer homeworkStatus;
    private String finishDate;

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

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        if (!dateStart.equals(""))
            this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        if (!dateEnd.equals(""))
            this.dateEnd = dateEnd;
    }

    public Integer getHomeworkStatus() {
        return homeworkStatus;
    }

    public void setHomeworkStatus(Integer homeworkStatus) {
        this.homeworkStatus = homeworkStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getHomeworkTitle() {
        return homeworkTitle;
    }

    public void setHomeworkTitle(String homeworkTitle) {
        if (!homeworkTitle.equals(""))
            this.homeworkTitle = homeworkTitle;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getHomeworkType() {
        return homeworkType;
    }

    public void setHomeworkType(Integer homeworkType) {
        this.homeworkType = homeworkType;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        if (!finishDate.equals(""))
            this.finishDate = finishDate;
    }
}
