package com.example.demo.bo;

public class CorrectHomeworkBO {
    private Integer id;
    private Integer homeworkId;
    private String homeworkTeacherNote;
    private Double homeworkScore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getHomeworkTeacherNote() {
        return homeworkTeacherNote;
    }

    public void setHomeworkTeacherNote(String homeworkTeacherNote) {
        this.homeworkTeacherNote = homeworkTeacherNote;
    }

    public Double getHomeworkScore() {
        return homeworkScore;
    }

    public void setHomeworkScore(Double homeworkScore) {
        this.homeworkScore = homeworkScore;
    }
}
