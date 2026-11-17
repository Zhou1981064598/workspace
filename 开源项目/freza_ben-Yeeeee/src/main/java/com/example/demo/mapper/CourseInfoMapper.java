package com.example.demo.mapper;

import com.example.demo.bo.AddModifyCourseBO;
import com.example.demo.bo.GetCourseBO;
import com.example.demo.entity.CourseInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.vo.CourseVO;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Yeeeeee
 * @since 2021-07-26
 */
@Mapper
public interface CourseInfoMapper extends BaseMapper<CourseInfo> {

    @Select("SELECT COUNT(*) FROM course_info" +
            " WHERE " +
            " course_date = #{courseDate} " +
            " AND " +
            " course_sign_time <= #{courseOut} " +
            " AND " +
            " course_out_time >= #{courseSign} " +
            " AND " +
            " course_classroom = #{classroom}")
    Integer isCourseTimeEnable(String courseDate, String courseSign, String courseOut, String classroom);

    @Select("SELECT COUNT(*) FROM course_info" +
            " WHERE " +
            " course_date = #{courseDate} " +
            " AND " +
            " course_sign_time <= #{courseOut} " +
            " AND " +
            " course_out_time >= #{courseSign} " +
            " AND " +
            " course_classroom = #{classroom} " +
            " AND id != #{courseId}")
    Integer isCourseTimeEnable(String courseDate, String courseSign, String courseOut, String classroom, Integer courseId);

    @Select("SELECT id FROM course_info " +
            " WHERE " +
            " course_date = #{courseDate} " +
            " AND " +
            " course_sign_time = #{courseSign}" +
            " AND " +
            " course_out_time = #{courseOut} " +
            " AND " +
            " course_classroom = #{classroom}")
    Integer getCourseId(String courseDate, String courseSign, String courseOut, String classroom);

    @Insert("INSERT INTO course_info(" +
            " course_date , " +
            " course_name , " +
            " course_teacher_id , " +
            " course_sign_time , " +
            " course_out_time , " +
            " course_classroom , " +
            " course_note , " +
            " last_upgrade_time , " +
            " last_upgrade_person , " +
            " create_time , " +
            " create_person " +
            ")" +
            "VALUE(" +
            " #{course.courseDate} , " +
            " #{course.courseName} , " +
            " #{course.leaderTeacher} , " +
            " #{course.courseSignTime} , " +
            " #{course.courseOutTime} , " +
            " #{course.courseClassroom} , " +
            " #{course.courseNote} , " +
            " #{date} , " +
            " #{operatorId} , " +
            " #{date} , " +
            " #{operatorId}  " +
            ")")
    Integer addCourse(@Param("course") AddModifyCourseBO addModifyCourseBO, Date date, Integer operatorId);

    @Insert("INSERT INTO teacher_course(" +
            " course_id , " +
            " teacher_id " +
            ")" +
            "VALUE(" +
            " #{courseId} , " +
            " #{teacherId}" +
            ")")
    Integer addTeacherIntoTeacherCourse(Integer teacherId, Integer courseId);

    @Select("<script>" +
            "SELECT " +
            " id , " +
            " course_date , " +
            " course_name , " +
            " course_sign_time , " +
            " course_out_time , " +
            " course_classroom , " +
            " course_note , " +
            " leader_teacher , " +
            " teacher " +
            " FROM course_view" +
            " WHERE 1=1 " +
            " <if test=\"list !=null and list.size()>0\">" +
            " AND id IN " +
            "   <foreach item=\"item\" index=\"index\" collection=\"list\" open=\"(\" close=\")\" separator=\",\">" +
            "       #{item}" +
            "   </foreach>" +
            " </if> " +
            " LIMIT #{offset} , #{pageSize} " +
            "</script>")
    List<CourseVO> listGetCourseInfo(List<Integer> list, Integer offset, Integer pageSize);

    @Select("<script>" +
            " SELECT " +
            " course_info.id " +
            " FROM " +
            " course_info " +
            " LEFT JOIN teacher_course ON teacher_course.course_id = course_info.id " +
            " LEFT JOIN user_info ON teacher_course.teacher_id = user_info.id " +
            " LEFT JOIN student_course ON student_course.course_id = course_info.id " +
            " WHERE " +
            " course_info.course_abandon = 0 " +
            " <if test='courseStart!=null'>" +
            "    AND course_date >= #{courseStart} " +
            " </if>" +
            " <if test='courseEnd!=null'>" +
            "    AND #{courseEnd} >= course_date " +
            " </if>" +
            " <if test='studentId!=null'>" +
            "    AND student_course.student_id = #{studentId} " +
            " </if>" +
            " <if test=\" courseName != null\" >" +
            " AND courseName = #{courseName}" +
            " </if>" +
            " <if test=\"teacherId !=null\"> " +
            " AND teacher_id = #{teacherId}" +
            "</if>" +
            "<if test=\"leaderTeacherId!=null\">" +
            " AND leader_teacher_id = #{leaderTeacherId}" +
            "</if>" +
            " LIMIT #{pageOfSet} , #{pageSize} " +
            "</script>")
    List<Integer> getCourseIdByBO(GetCourseBO getCourseBO);

    @Select("<script>" +
            "SELECT " +
            " COUNT(*) " +
            " FROM " +
            " course_info " +
            " LEFT JOIN teacher_course ON teacher_course.course_id = course_info.id " +
            " LEFT JOIN user_info ON teacher_course.teacher_id = user_info.id  " +
            " WHERE " +
            " course_info.course_abandon = 0 " +
            " <if test='courseStart!=null'>" +
            "    AND course_date >= #{courseStart} " +
            " </if>" +
            " <if test='courseEnd!=null'>" +
            "    AND #{courseEnd} >= course_date " +
            " </if>" +
            " <if test=\" courseName != null\">" +
            " AND courseName = #{courseName}" +
            " </if>" +
            " <if test=\"teacherId !=null\"> " +
            " AND teacher_course.teacher_id = #{teacherId}" +
            "</if>" +
            "<if test=\"leaderTeacherId!=null\">" +
            " AND course_info.course_teacher_id = #{leaderTeacherId}" +
            "</if>" +
            "</script>")
    Integer getCourseIdByBOCount(GetCourseBO getCourseBO);

    @Select("SELECT COUNT(*) FROM course_info WHERE id = #{courseId} and course_abandon = 0 ")
    Integer isCourseExistById(Integer courseId);

    @Insert("INSERT INTO student_course( student_id , course_id ) VALUE(#{item} , #{courseId})")
    Integer addStudentIntoCourse(Integer item, Integer courseId);


    @Update("UPDATE course_info SET" +
            " course_name = #{courseBO.courseName} , " +
            " course_date = #{courseBO.courseDate} , " +
            " course_teacher_id = #{courseBO.leaderTeacher} ," +
            " course_sign_time = #{courseBO.courseSignTime} ," +
            " course_out_time = #{courseBO.courseOutTime} , " +
            " course_classroom = #{courseBO.courseClassroom} ," +
            " course_note = #{courseBO.courseNote} , " +
            " course_abandon = #{courseBO.courseAbandon}  ," +
            " last_upgrade_time = #{date} , " +
            " last_upgrade_person = #{operatorId}" +
            " WHERE id = #{courseBO.courseId} " +
            "")
    Integer updateCourse(AddModifyCourseBO courseBO, Date date, Integer operatorId);

    @Delete("DELETE FROM teacher_course WHERE course_Id = #{courseId}")
    void delAllTeacherByCourseId(Integer courseId);

    @Select("<script>" +
            " SELECT COUNT(*) FROM student_group " +
            " WHERE group_id IN" +
            "  <foreach item=\"item\" index=\"index\" collection=\"array\" open=\"(\" close=\")\" separator=\",\">" +
            "       #{item} " +
            "  </foreach>" +
            " AND student_id = #{studentId} " +
            "</script>")
    Integer isStudentInGroup(Integer studentId, Integer[] array);

    @Select("SELECT COUNT(*) FROM student_course WHERE course_id = #{courseId} AND student_id = #{studentId}")
    Integer isStudentInCourse(Integer studentId, Integer courseId);

    @Select("SELECT COUNT(*) FROM teacher_course WHERE course_id = #{courseId} AND teacher_id = #{id}")
    Integer isTeacherTeachCourseById(Integer courseId, Integer id);

    @Select("SELECT COUNT(*) FROM course_info WHERE id = #{courseId} AND course_teacher_id = #{operatorId}")
    Integer isLeaderTeacher(Integer courseId, Integer operatorId);
}
