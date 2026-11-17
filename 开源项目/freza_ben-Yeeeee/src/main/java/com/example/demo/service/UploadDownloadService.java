package com.example.demo.service;

import com.example.demo.entity.AttachmentInfo;
import com.example.demo.exception.ValidationFailException;
import com.example.demo.vo.AttachmentVO;
import com.example.demo.vo.PageVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UploadDownloadService {
    Boolean upload(MultipartFile[] files, Integer operatorId, Integer id) throws IOException, ValidationFailException;

    PageVO<List<AttachmentVO>> listGetHomeworkAttachment(Integer homeworkId) throws ValidationFailException;

    PageVO<List<AttachmentVO>> listGetStudentAttachment(Integer id) throws ValidationFailException;

    AttachmentInfo downloadAttachment(Integer id) throws ValidationFailException;
}
