package com.example.demo.bo;

import org.springframework.web.multipart.MultipartFile;

public class UploadBO {
    private MultipartFile[] file;
    private Integer homeworkId;

    public MultipartFile[] getFile() {
        return file;
    }

    public void setFile(MultipartFile[] file) {
        this.file = file;
    }

    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }
}
