package com.example.demo.mapper;

import com.example.demo.bo.GetStudentBO;
import com.example.demo.bo.GetTeacherBO;
import com.example.demo.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.vo.CheckBoxTeacherVO;
import com.example.demo.vo.StudentVO;
import com.example.demo.vo.TeacherVO;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Yeeeeee
 * @since 2021-07-21
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    @Select("SELECT COUNT(*) FROM user_info WHERE id = #{id} AND user_abandon = 0 AND user_role = #{role}")
    Integer isUserExist(Integer id, Integer role);

    @Select("SELECT user_role FROM user_info WHERE id = #{id}")
    Integer getUserRole(Integer id);

    @Select("<script>" +
            "SELECT " +
            " id , " +
            " user_name ," +
            " user_id ," +
            " user_identity , " +
            " user_sex , " +
            " user_phone , " +
            " teacher_role , " +
            " user_abandon " +
            "FROM user_info " +
            "WHERE 1=1 " +
            " <if test=\"user.userName!=null\">" +
            " AND user_name like CONCAT(#{user.userName},'%') " +
            " </if>" +
            " <if test=\"user.userIdentity!=null\">" +
            " AND user_identity = #{user.userIdentity} " +
            " </if>" +
            " <if test=\"user.userPhone!=null\">" +
            " AND user_phone = #{user.userPhone} " +
            " </if>" +
            " <if test=\"user.teacherRole!=null\">" +
            " AND teacher_role = #{user.teacherRole} " +
            " </if>" +
            " <if test=\"user.userAbandon!=null\">" +
            " AND user_abandon = #{user.userAbandon} " +
            " </if>" +
            " <if test=\"user.userRole!=null\">" +
            " AND user_role = #{user.userRole} " +
            " </if>" +
            " LIMIT #{user.pageOfSet} , #{user.pageSize} " +
            "</script>")
    List<TeacherVO> listGetUser(@Param("user") GetTeacherBO userBO);

    @Select("<script>" +
            "SELECT COUNT(*) FROM user_info " +
            "WHERE 1=1 " +
            " <if test=\"user.userName!=null\">" +
            " AND user_name like CONCAT(#{user.userName},'%') " +
            " </if>" +
            " <if test=\"user.userIdentity!=null\">" +
            " AND user_identity = #{user.userIdentity} " +
            " </if>" +
            " <if test=\"user.userPhone!=null\">" +
            " AND user_phone = #{user.userPhone} " +
            " </if>" +
            " <if test=\"user.teacherRole!=null\">" +
            " AND teacher_role = #{user.teacherRole} " +
            " </if>" +
            " <if test=\"user.userAbandon!=null\">" +
            " AND user_abandon = #{user.userAbandon} " +
            " </if>" +
            " <if test=\"user.userRole!=null\">" +
            " AND user_role = #{user.userRole} " +
            " </if> " +
            " </script> ")
    Integer listGetUserCount(@Param("user") GetTeacherBO userBO);

    @Select("<script>" +
            "SELECT " +
            " id , " +
            " user_id ," +
            " user_name ," +
            " user_identity , " +
            " user_sex , " +
            " user_phone , " +
            " student_email , " +
            " student_role , " +
            " class_base_name , " +
            " major_name , " +
            " grade_name , " +
            " department_name , " +
            " teacher_name " +
            "FROM student_view " +
            "WHERE 1=1 " +
            " <if test=\"userName!=null\">" +
            " AND user_name like CONCAT(#{userName} ,'%') " +
            " </if>" +
            " <if test=\"userIdentity!=null\">" +
            " AND user_identity = #{userIdentity} " +
            " </if>" +
            " <if test=\"userPhone!=null\">" +
            " AND user_phone = #{userPhone} " +
            " </if>" +
            " <if test=\"studentRole!=null\">" +
            " AND student_role = #{studentRole} " +
            " </if>" +
            " <if test=\"studentEmail!=null\">" +
            " AND student_email = #{studentEmail} " +
            " </if>" +
            " <if test=\"studentClass!=null\">" +
            " AND class_id = #{studentClass} " +
            " </if>" +
            " LIMIT #{pageOfSet} , #{pageSize} " +
            "</script>")
    List<StudentVO> listGetStudent(GetStudentBO userBO);

    @Select("<script>" +
            "SELECT COUNT(*)  " +
            "FROM student_view " +
            "WHERE 1=1 " +
            " <if test=\"userName!=null\">" +
            " AND user_name like CONCAT(#{userName},'%') " +
            " </if>" +
            " <if test=\"userIdentity!=null\">" +
            " AND user_identity = #{userIdentity} " +
            " </if>" +
            " <if test=\"userPhone!=null\">" +
            " AND user_phone = #{userPhone} " +
            " </if>" +
            " <if test=\"studentRole!=null\">" +
            " AND student_role = #{studentRole} " +
            " </if>" +
            " <if test=\"studentEmail!=null\">" +
            " AND student_email = #{studentEmail} " +
            " </if>" +
            " <if test=\"studentClass!=null\">" +
            " AND class_id = #{studentClass} " +
            " </if>" +
            "</script>")
    Integer listGetStudentCount(GetStudentBO userBO);

    @Select("<script>" +
            "SELECT " +
            " id , " +
            " user_name " +
            " FROM user_info " +
            " WHERE " +
            " user_role = 2 " +
            " AND " +
            " user_abandon = 0 " +
            "</script>")
    List<CheckBoxTeacherVO> listGetAllTeacher();

    @Insert("" +
            " INSERT INTO user_info( " +
            " user_id, " +
            " user_name ," +
            " user_sex , " +
            " user_identity ," +
            " user_phone , " +
            " user_role , " +
            " user_password , " +
            " teacher_role , " +
            " student_graduate ," +
            " student_email , " +
            " create_time , " +
            " create_person , " +
            " last_upgrade_time , " +
            " last_upgrade_person )" +
            " VALUE(" +
            " #{user.userId} ," +
            " #{user.userName} ," +
            " #{user.userSex} , " +
            " #{user.userIdentity} , " +
            " #{user.userPhone} , " +
            " #{user.userRole} , " +
            " #{user.userPassword} , " +
            " #{user.teacherRole} , " +
            " #{user.studentGraduate} , " +
            " #{user.studentEmail} , " +
            " #{user.createTime} , " +
            " #{user.createPerson} , " +
            " #{user.lastUpgradeTime} , " +
            " #{user.lastUpgradePerson} )" +
            "")
    Integer addUser(@Param("user") UserInfo userInfo);

    @Insert("INSERT INTO student_class(" +
            " class_id , " +
            " student_id ," +
            " student_role ) " +
            " VALUE(" +
            " #{classId} , " +
            " #{studentId} ," +
            " #{studentRole} )")
    Integer addStudentClass(Integer studentId, Integer classId, Integer studentRole);

    @Update("UPDATE student_class" +
            " SET student_role = #{studentRole} " +
            " WHERE student_id = #{studentId}" +
            " AND class_id = #{classId}")
    Integer updateStudentClass(Integer studentId, Integer classId, Integer studentRole);

    @Update("UPDATE user_info SET user_abandon = 1 , last_upgrade_time = #{upgradeTime} , last_upgrade_person = #{operatorId} WHERE id = #{id}")
    Integer disableUser(Integer id, Integer operatorId, Date upgradeTime);

    @Update("UPDATE user_info SET user_password = #{newPassword} WHERE id = #{id}")
    Integer updateUserPassword(String newPassword, Integer id);

}
