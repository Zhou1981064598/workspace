package com.example.demo.mapper;

import com.example.demo.bo.*;
import com.example.demo.entity.HomeworkInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.vo.StudentHomeworkVO;
import com.example.demo.vo.TeacherHomeworkVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Yeeeeee
 * @since 2021-07-28
 */
@Mapper
public interface HomeworkInfoMapper extends BaseMapper<HomeworkInfo> {

    @Insert("INSERT INTO homework_info(" +
            " course_id , " +
            " homework_title , " +
            " homework_expiry , " +
            " homework_type , " +
            " homework_main , " +
            " last_upgrade_time , " +
            " last_upgrade_person , " +
            " create_time , " +
            " create_person " +
            ") " +
            " VALUES( " +
            " #{homework.courseId} ," +
            " #{homework.homeworkTitle} , " +
            " #{homework.homeworkExpiry} , " +
            " #{homework.homeworkType} , " +
            " #{homework.homeworkMain} , " +
            " #{date} , " +
            " #{operatorId} , " +
            " #{date} , " +
            " #{operatorId} " +
            ")")
    Integer addHomework(AddModifyHomeworkBO homework, Date date, Integer operatorId);

    @Select("SELECT id FROM homework_info  ORDER BY id DESC LIMit 0,1")
    Integer getInsertId();

    @Insert("INSERT INTO student_homework(" +
            " student_id , " +
            " homework_id , " +
            " student_group_id" +
            ")" +
            " VALUES( " +
            " #{studentId} , " +
            " #{homeworkId} ," +
            " #{groupId} " +
            ")")
    Integer addStudentHomework(Integer studentId, Integer homeworkId, Integer groupId);

    @Select("<script>" +
            "SELECT " +
            " id ," +
            " homework_id , " +
            " student_id , " +
            " create_time , " +
            " course_name , " +
            " homework_title , " +
            " homework_type ," +
            " teacher , " +
            " homework_expiry , " +
            " homework_status , " +
            " last_upgrade_time , " +
            " homework_score ," +
            " homework_answer , " +
            " homework_main ," +
            " homework_teacher_note " +
            "FROM" +
            " student_homework_view " +
            "WHERE " +
            " 1=1 " +
            " <if test=\"homework.dateStart!=null\">" +
            " AND  create_time>=#{homework.dateStart}" +
            " </if>" +
            " <if test=\"homework.dateEnd != null\">" +
            " AND  #{homework.dateEnd}>= create_time  " +
            " </if>" +
            " <if test=\"homework.courseId != null\">" +
            " AND course_id = #{homework.courseId} " +
            " </if>" +
            " <if test=\"homework.homeworkTitle != null\">" +
            " AND homework_title like CONCAT('%',CONCAT(#{homework.homeworkTitle},'%'))" +
            " </if>" +
            " <if test=\"homework.teacherId != null\">" +
            " AND teacher_id = #{homework.teacherId} " +
            " </if>" +
            " <if test=\"homework.homeworkType != null\">" +
            " AND homework_type = #{homework.homeworkType} " +
            " </if>" +
            " <if test=\"homework.finishDate != null\">" +
            " AND homework_expiry = #{homework.finishDate} " +
            " </if>" +
            " <if test=\"homework.homeworkStatus != null\">" +
            " AND homework_status = #{homework.homeworkStatus} " +
            " </if>" +
            " AND student_id = #{operatorId} " +
            " LIMIT #{homework.pageOfSet} , #{homework.pageSize} " +
            "</script>")
    List<StudentHomeworkVO> listGetStudentHomework(GetHomeworkBO homework, Integer operatorId);

    @Select("<script>" +
            "SELECT " +
            " COUNT(*) " +
            "FROM" +
            " student_homework_view " +
            "WHERE " +
            " 1=1 " +
            " <if test=\"homework.dateStart!=null\">" +
            " AND  create_time>=#{homework.dateStart}" +
            " </if>" +
            " <if test=\"homework.dateEnd != null\">" +
            " AND #{homework.dateEnd} >= create_time  " +
            " </if>" +
            " <if test=\"homework.courseId != null\">" +
            " AND course_id = #{homework.courseId} " +
            " </if>" +
            " <if test=\"homework.homeworkTitle != null\">" +
            " AND homework_title like CONCAT('%',CONCAT(#{homework.homeworkTitle},'%'))" +
            " </if>" +
            " <if test=\"homework.teacherId != null\">" +
            " AND teacher_id = #{homework.teacherId} " +
            " </if>" +
            " <if test=\"homework.homeworkType != null\">" +
            " AND homework_type = #{homework.homeworkType} " +
            " </if>" +
            " <if test=\"homework.finishDate != null\">" +
            " AND homework_expiry = #{homework.finishDate} " +
            " </if>" +
            " <if test=\"homework.homeworkStatus != null\">" +
            " AND homework_status = #{homework.homeworkStatus} " +
            " </if>" +
            " AND student_id = #{operatorId} " +
            "</script>")
    Integer listGetStudentHomeworkCount(GetHomeworkBO homework, Integer operatorId);

    @Select("<script>" +
            "SELECT " +
            " id ," +
            " create_time , " +
            " course_name , " +
            " homework_title , " +
            " homework_type ," +
            " teacher , " +
            " homework_expiry , " +
            " group_name , " +
            " total_student , " +
            " un_commit_student , " +
            " un_correct_student , " +
            " correct_Student ," +
            " homework_main  " +
            "FROM" +
            " homework_view " +
            "WHERE " +
            " 1=1 " +
            " <if test=\"homework.id!=null\">" +
            " AND id = #{homework.id}" +
            " </if>" +
            " <if test=\"homework.dateStart!=null\">" +
            " AND  create_time>=#{homework.dateStart}" +
            " </if>" +
            " <if test=\"homework.dateEnd != null\">" +
            " AND  #{homework.dateEnd}>= create_time  " +
            " </if>" +
            " <if test=\"homework.courseId != null\">" +
            " AND course_id = #{homework.courseId} " +
            " </if>" +
            " <if test=\"homework.homeworkTitle != null\">" +
            " AND homework_title like CONCAT('%',CONCAT(#{homework.homeworkTitle},'%'))" +
            " </if>" +
            " <if test=\"homework.teacherId != null\">" +
            " AND teacher_id = #{homework.teacherId} " +
            " </if>" +
            " <if test=\"homework.homeworkType != null\">" +
            " AND homework_type = #{homework.homeworkType} " +
            " </if>" +
            " <if test=\"homework.finishDate != null\">" +
            " AND homework_expiry = #{homework.finishDate} " +
            " </if>" +
            " <if test=\"homework.groupId != null\">" +
            " AND FIND_IN_SET(#{homework.groupId} ,group_id) " +
            " </if>" +
            " <if test=\"homework.homeworkStatus != null\">" +
            " AND homework_status = #{homework.homeworkStatus} " +
            " </if>" +
            " AND   FIND_IN_SET(#{operatorId},teacher_id) " +
            " LIMIT #{homework.pageOfSet} , #{homework.pageSize} " +
            "</script>")
    List<TeacherHomeworkVO> listGetTeacherHomework(GetHomeworkBO homework, Integer operatorId);


    @Select("<script>" +
            "SELECT " +
            " COUNT(*) " +
            "FROM" +
            " homework_view " +
            "WHERE " +
            " 1=1 " +
            " <if test=\"homework.id!=null\">" +
            " AND id = #{homework.id}" +
            " </if>" +
            " <if test=\"homework.dateStart!=null\">" +
            " AND  create_time>=#{homework.dateStart}" +
            " </if>" +
            " <if test=\"homework.dateEnd != null\">" +
            " AND  #{homework.dateEnd}>= create_time  " +
            " </if>" +
            " <if test=\"homework.courseId != null\">" +
            " AND course_id = #{homework.courseId} " +
            " </if>" +
            " <if test=\"homework.homeworkTitle != null\">" +
            " AND homework_title like CONCAT('%',CONCAT(#{homework.homeworkTitle},'%'))" +
            " </if>" +
            " <if test=\"homework.teacherId != null\">" +
            " AND teacher_id = #{homework.teacherId} " +
            " </if>" +
            " <if test=\"homework.homeworkType != null\">" +
            " AND homework_type = #{homework.homeworkType} " +
            " </if>" +
            " <if test=\"homework.finishDate != null\">" +
            " AND homework_expiry = #{homework.finishDate} " +
            " </if>" +
            " <if test=\"homework.groupId != null\">" +
            " AND FIND_IN_SET(#{homework.groupId} ,group_id) " +
            " </if>" +
            " <if test=\"homework.homeworkStatus != null\">" +
            " AND homework_status = #{homework.homeworkStatus} " +
            " </if>" +
            " AND   FIND_IN_SET(#{operatorId},teacher_id) " +
            " LIMIT #{homework.pageOfSet} , #{homework.pageSize} " +
            "</script>")
    Integer listGetTeacherHomeworkCount(GetHomeworkBO homework, Integer operatorId);

    @Select("SELECT create_person FROM homework_info WHERE id = #{id}")
    Integer isHomeworkExistById(Integer id);

    @Update("UPDATE homework_info SET homework_abandon = 1 , last_upgrade_person = #{operatorId} , last_upgrade_time = #{date} WHERE id = #{id}")
    Integer delHomeworkByID(Integer id, Integer operatorId, Date date);

    @Select("SELECT COUNT(*) FROM student_homework WHERE homework_id = #{homeworkId} AND student_id = #{operatorId}")
    Integer isHomeworkExistByStudentIdAndHomeworkId(Integer homeworkId, Integer operatorId);

    @Select("SELECT homework_expiry FROM homework_info WHERE id = #{homeworkId}")
    String getHomeworkExpiryTime(Integer homeworkId);

    @Update("UPDATE student_homework SET homework_answer = #{homeworkBO.homeworkAnswer} , homework_status = 2 , last_upgrade_time = #{date} WHERE homework_id = #{homeworkBO.homeworkId} AND student_id = #{operatorId}")
    Integer commitHomework(CommitHomeworkBO homeworkBO, Integer operatorId, Date date);

    @Select("<script>" +
            "SELECT " +
            " id ," +
            " homework_id , " +
            " student_id , " +
            " create_time , " +
            " course_name , " +
            " homework_title , " +
            " homework_type ," +
            " teacher , " +
            " homework_expiry , " +
            " homework_status , " +
            " last_upgrade_time , " +
            " homework_score ," +
            " homework_answer , " +
            " homework_main ," +
            " homework_teacher_note " +
            "FROM" +
            " student_homework_view " +
            "WHERE " +
            " 1=1 " +
            " <if test=\"homework.dateStart!=null\">" +
            " AND  create_time>=#{homework.dateStart}" +
            " </if>" +
            " <if test=\"homework.dateEnd != null\">" +
            " AND  #{homework.dateEnd}>= create_time  " +
            " </if>" +
            " <if test=\"homework.courseId != null\">" +
            " AND course_id = #{homework.courseId} " +
            " </if>" +
            " <if test=\"homework.homeworkTitle != null\">" +
            " AND homework_title like CONCAT('%',CONCAT(#{homework.homeworkTitle},'%'))" +
            " </if>" +
            " <if test=\"homework.teacherId != null\">" +
            " AND teacher_id = #{homework.teacherId} " +
            " </if>" +
            " <if test=\"homework.homeworkType != null\">" +
            " AND homework_type = #{homework.homeworkType} " +
            " </if>" +
            " <if test=\"homework.finishDate != null\">" +
            " AND homework_expiry = #{homework.finishDate} " +
            " </if>" +
            " <if test=\"homework.homeworkStatus != null\">" +
            " AND homework_status = #{homework.homeworkStatus} " +
            " </if>" +
            " AND create_person = #{operatorId} " +
            " LIMIT #{homework.pageOfSet} , #{homework.pageSize} " +
            "</script>")
    List<StudentHomeworkVO> listGetCorrectHomework(GetCorrectHomeworkBO homework, Integer operatorId);

    @Select("<script>" +
            "SELECT " +
            " COUNT(*) " +
            "FROM" +
            " student_homework_view " +
            "WHERE " +
            " 1=1 " +
            " <if test=\"homework.dateStart!=null\">" +
            " AND  create_time>=#{homework.dateStart}" +
            " </if>" +
            " <if test=\"homework.dateEnd != null\">" +
            " AND  #{homework.dateEnd}>= create_time  " +
            " </if>" +
            " <if test=\"homework.courseId != null\">" +
            " AND course_id = #{homework.courseId} " +
            " </if>" +
            " <if test=\"homework.homeworkTitle != null\">" +
            " AND homework_title like CONCAT('%',CONCAT(#{homework.homeworkTitle},'%'))" +
            " </if>" +
            " <if test=\"homework.teacherId != null\">" +
            " AND teacher_id = #{homework.teacherId} " +
            " </if>" +
            " <if test=\"homework.homeworkType != null\">" +
            " AND homework_type = #{homework.homeworkType} " +
            " </if>" +
            " <if test=\"homework.finishDate != null\">" +
            " AND homework_expiry = #{homework.finishDate} " +
            " </if>" +
            " <if test=\"homework.homeworkStatus != null\">" +
            " AND homework_status = #{homework.homeworkStatus} " +
            " </if>" +
            " AND create_person = #{operatorId} " +
            "</script>")
    Integer listGetCorrectHomeworkCount(GetCorrectHomeworkBO homework, Integer operatorId);

    @Update("UPDATE student_homework SET homework_score = #{homeworkBO.homeworkScore} , homework_teacher_note = #{homeworkBO.homeworkTeacherNote} , homework_status = 3 WHERE id = #{homeworkBO.id}")
    Integer correctHomework(CorrectHomeworkBO homeworkBO, Integer operatorId);

    @Select("SELECT homework_status FROM student_homework WHERE id = #{id}")
    Integer getHomeworkStatusById(Integer id);
}
