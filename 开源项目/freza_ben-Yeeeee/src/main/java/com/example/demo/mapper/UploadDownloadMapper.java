package com.example.demo.mapper;

import com.example.demo.entity.AttachmentInfo;
import com.example.demo.vo.AttachmentVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface UploadDownloadMapper {
    @Insert("INSERT INTO attachment_info(" +
            " file_path , " +
            " file_origin_name , " +
            " create_time , " +
            " create_person , " +
            " last_upgrade_time , " +
            " last_upgrade_person" +
            ") " +
            " VALUE(" +
            " #{fileName} , " +
            " #{originalFilename} ," +
            " #{date} ," +
            " #{operatorId} ," +
            " #{date} ," +
            " #{operatorId}" +
            ")")
    boolean addAttachment(String fileName, String originalFilename, Integer operatorId, Date date);

    @Insert("INSERT INTO homework_attachment(" +
            " homework_id ," +
            " attachment_id )" +
            " VALUE(" +
            " #{homeworkId} , " +
            " #{attachmentId}" +
            ")")
    boolean addTeacherAttachment(Integer homeworkId, Integer attachmentId);

    @Insert("INSERT INTO student_attachment(" +
            " student_homework_id ," +
            " attachment_id )" +
            " VALUE(" +
            " #{homeworkId} , " +
            " #{attachmentId}" +
            ")")
    boolean addStudentAttachment(Integer homeworkId, Integer attachmentId);

    @Select("SELECT id FROM attachment_info " +
            " WHERE file_path = #{path} ")
    Integer getIdByPath(String path);

    @Select("SELECT attachment_info.id , file_origin_name FROM attachment_info " +
            " LEFT JOIN homework_attachment ON " +
            " homework_attachment.attachment_id = attachment_info.id " +
            " WHERE homework_attachment.homework_id = #{homeworkId} ")
    List<AttachmentVO> listGetHomeworkAttachment(Integer homeworkId);

    @Select("SELECT attachment_info.id , file_origin_name FROM attachment_info " +
            " LEFT JOIN student_attachment ON " +
            " student_attachment.attachment_id = attachment_info.id " +
            " WHERE student_attachment.student_homework_id = #{homeworkId} ")
    List<AttachmentVO> listGetStudentAttachment(Integer homeworkId);

    @Select("SELECT id , file_path ,  file_origin_name FROM attachment_info " +
            " WHERE id = #{id} ")
    AttachmentInfo getPathById(Integer id);
}
