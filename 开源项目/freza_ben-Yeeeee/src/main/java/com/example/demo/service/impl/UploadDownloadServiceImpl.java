package com.example.demo.service.impl;

import com.example.demo.entity.AttachmentInfo;
import com.example.demo.exception.ValidationFailException;
import com.example.demo.mapper.HomeworkInfoMapper;
import com.example.demo.mapper.UploadDownloadMapper;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.service.UploadDownloadService;
import com.example.demo.vo.AttachmentVO;
import com.example.demo.vo.PageVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UploadDownloadServiceImpl implements UploadDownloadService {
    private static final String FILE_UPLOAD_PATH = "D:\\工作文件\\demo\\src\\main\\resources\\upload\\";
    private static final String[] whiteList = {
            "txt", "pdf", "doc", "docx",
            "gif", "png", "jpg", "jpeg"};
    @Resource
    UploadDownloadMapper mapper;
    @Resource
    UserInfoMapper userInfoMapper;
    @Resource
    HomeworkInfoMapper homeworkInfoMapper;

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_UNCOMMITTED)
    public Boolean upload(MultipartFile[] files, Integer operatorId, Integer id) throws ValidationFailException {
        Integer role = userInfoMapper.getUserRole(operatorId);
        if (role == 3) {
            return false;
        }
        if (id == null) {
            throw new ValidationFailException("未指定上传目标作业");
        }
        if (role == 2) {
            if (homeworkInfoMapper.isHomeworkExistById(id) < 1) {
                throw new ValidationFailException("作业不存在");
            }
        } else {
            if (homeworkInfoMapper.isHomeworkExistByStudentIdAndHomeworkId(id, operatorId) < 1) {
                throw new ValidationFailException("作业不存在");
            }
        }
        for (MultipartFile multipartFile : files) {
            String originalFilename = multipartFile.getOriginalFilename();
            if (originalFilename == null) {
                continue;
            }
            String[] type = originalFilename.split("\\.");
            boolean t = false;
            for (String white : whiteList) {
                if (white.equalsIgnoreCase(type[1])) {
                    t = true;
                    break;
                }
            }
            if (!t) {
                throw new ValidationFailException("不允许上传的格式");
            }
            String fileName = UUID.randomUUID() + "&" + originalFilename;
            try {
                multipartFile.transferTo(new File(FILE_UPLOAD_PATH + fileName));
            } catch (IOException e) {
                throw new ValidationFailException("上传失败");
            }
            if (!mapper.addAttachment(fileName, originalFilename, operatorId, new Date())) {
                throw new ValidationFailException("更新数据库失败");
            }
            int attachmentId = mapper.getIdByPath(fileName);
            if (role == 1) {
                if (!mapper.addStudentAttachment(id, attachmentId)) {
                    throw new ValidationFailException("更新数据库失败");
                }
            } else {
                if (!mapper.addTeacherAttachment(id, attachmentId)) {
                    throw new ValidationFailException("更新数据库失败");
                }
            }
        }
        return true;
    }

    @Override
    public PageVO<List<AttachmentVO>> listGetHomeworkAttachment(Integer homeworkId) throws ValidationFailException {
        if (homeworkId == null) {
            throw new ValidationFailException("未指定作业");
        }
        PageVO<List<AttachmentVO>> result;
        List<AttachmentVO> list = mapper.listGetHomeworkAttachment(homeworkId);
        if (list == null || list.size() == 0) {
            return null;
        }
        result = new PageVO<>(list);
        result.setTotalPage(1);
        result.setTotalCount(1);
        result.setRecordCount(list.size());
        result.setPageSize(list.size());
        result.setPageNo(1);
        return result;
    }

    @Override
    public PageVO<List<AttachmentVO>> listGetStudentAttachment(Integer id) throws ValidationFailException {
        if (id == null) {
            throw new ValidationFailException("未指定作业");
        }
        PageVO<List<AttachmentVO>> result;
        List<AttachmentVO> list = mapper.listGetStudentAttachment(id);
        if (list == null || list.size() == 0) {
            return null;
        }
        result = new PageVO<>(list);
        result.setTotalPage(1);
        result.setTotalCount(1);
        result.setRecordCount(list.size());
        result.setPageSize(list.size());
        result.setPageNo(1);
        return result;
    }

    @Override
    public AttachmentInfo downloadAttachment(Integer id) throws ValidationFailException {
        if (id == null) {
            throw new ValidationFailException("未指定附件");
        }
        AttachmentInfo attachmentInfo = mapper.getPathById(id);
        if (attachmentInfo == null) {
            return null;
        }
        attachmentInfo.setFilePath(FILE_UPLOAD_PATH + attachmentInfo.getFilePath());
        return attachmentInfo;
    }
}
