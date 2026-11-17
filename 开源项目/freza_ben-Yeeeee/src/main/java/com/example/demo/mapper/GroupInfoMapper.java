package com.example.demo.mapper;

import com.example.demo.bo.GetGroupBO;
import com.example.demo.bo.GetGroupMembersBO;
import com.example.demo.entity.GroupInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.vo.GroupMembersVO;
import com.example.demo.vo.GroupVO;
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
public interface GroupInfoMapper extends BaseMapper<GroupInfo> {
    @Select("SELECT COUNT(*) FROM group_info WHERE group_name = #{groupName} AND group_abandon != 1")
    Integer isGroupExistByName(String groupName);

    @Select("SELECT COUNT(*) FROM group_info WHERE id = #{groupId} AND group_abandon != 1")
    Integer isGroupExistById(Integer groupId);

    @Select("<script>" +
            "SELECT group_name , id FROM group_info " +
            " WHERE 1 = 1 " +
            " <if test=\"groupName!=null \"> " +
            "  AND group_name like CONCAT('%',#{groupName})" +
            " </if>" +
            " <if test=\"groupGraduate!=null \"> " +
            "  AND group_graduate = #{groupGraduate}" +
            " </if> " +
            " AND group_abandon != 1 " +
            " LIMIT #{pageOfSet} , #{pageSize} " +
            "</script>")
    List<GroupVO> listGetGroup(GetGroupBO group);

    @Select("<script>" +
            "SELECT COUNT(*) FROM group_info " +
            " WHERE 1 = 1 " +
            " <if test=\"groupName!=null \"> " +
            "  AND group_name like CONCAT('%',#{groupName})" +
            " </if>" +
            " <if test=\"groupGraduate!=null \"> " +
            "  AND group_graduate = #{groupGraduate}" +
            " </if> " +
            " AND group_abandon != 1 " +
            " LIMIT #{pageOfSet} , #{pageSize} " +
            "</script>")
    Integer listGetGroupCount(GetGroupBO group);

    @Insert("INSERT INTO group_info(group_name , group_graduate , last_upgrade_time , last_upgrade_person , create_time , create_person)" +
            " VALUE(#{groupName} , #{groupGraduate} , #{lastUpgradeTime} , #{lastUpgradePerson},#{createTime},#{createPerson})")
    Integer addGroup(GroupInfo group);

    @Insert("INSERT INTO student_group( student_id , group_id ) " +
            " VALUE(#{studentId} , #{groupId} )")
    Integer addGroupMembers(Integer studentId, Integer groupId);

    @Select("<script>" +
            "SELECT COUNT(*) FROM student_group WHERE 1=1 " +
            " AND student_id =#{studentId} " +
            "<if test=\"groupId!=null\">" +
            "AND group_id = #{groupId}" +
            "</if>" +
            "</script>")
    Integer isGroupMembersExist(Integer groupId, Integer studentId);

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
            " teacher_name , " +
            " group_name " +
            "FROM student_view " +
            "WHERE 1=1 " +
            " <if test=\"studentName!=null\">" +
            " AND user_name like CONCAT(#{studentName} ,'%') " +
            " </if>" +
            " <if test=\"groupName!=null\">" +
            " AND group_name = #{groupName} " +
            " </if>" +
            " <if test=\"groupId!=null\">" +
            " AND group_id = #{groupId} " +
            " </if>" +
            " <if test=\"studentId!=null\">" +
            " AND user_id = #{studentId} " +
            "</if>" +
            " <if test=\"majorId!=null\">" +
            " AND major_id = #{majorId} " +
            "</if>" +
            " <if test=\"departmentId!=null\">" +
            " AND department_id  = #{departmentId} " +
            "</if>" +
            " <if test=\"gradeId!=null\">" +
            " AND grade_id  = #{gradeId} " +
            "</if>" +
            " <if test=\"courseId!=null\">" +
            " AND course_id  = #{courseId} " +
            "</if>" +
            " <if test=\"studentClassId!=null\">" +
            " AND class_id  = #{studentClassId} " +
            "</if>" +
            " LIMIT #{pageOfSet} , #{pageSize} " +
            "</script>")
    List<GroupMembersVO> listGetGroupMembers(GetGroupMembersBO groupMembers);

    @Select("<script>" +
            "SELECT " +
            " COUNT(*) " +
            "FROM student_view " +
            "WHERE 1=1 " +
            " <if test=\"studentName!=null\">" +
            " AND user_name like CONCAT(#{studentName} ,'%') " +
            " </if>" +
            " <if test=\"groupName!=null\">" +
            " AND group_name = #{groupName} " +
            " </if>" +
            " <if test=\"groupId!=null\">" +
            " AND group_id = #{groupId} " +
            " </if>" +
            " <if test=\"studentId!=null\">" +
            " AND user_id = #{studentId} " +
            "</if>" +
            "</script>")
    Integer listGetGroupMembersCount(GetGroupMembersBO groupMembers);

    @Delete("DELETE FROM student_group WHERE student_id = #{studentId} AND group_id = #{groupId}")
    Integer delGroupMembers(Integer studentId, Integer groupId);

    @Delete("DELETE FROM student_group WHERE group_id = #{groupId}")
    Integer delGroupMembersByGroupId(Integer groupId);

    @Update("UPDATE group_info SET group_abandon = 1 , last_upgrade_time = #{date} , last_upgrade_person = #{operatorId} WHERE id = #{groupID} ")
    Integer disableGroupById(Integer groupId, Integer operatorId, Date date);

    @Select("SELECT id FROM student_view WHERE group_id = #{groupId}")
    Integer[] listGetGroupMembersId(Integer groupId);

    @Update("UPDATE group_info SET group_leader_id = #{leaderId} , last_upgrade_time = #{date} , last_upgrade_person = #{operatorId} WHERE id = #{groupId}")
    Integer setGroupLeader(Integer groupId, Integer leaderId, Integer operatorId, Date date);

    @Select("SELECT COUNT(*) FROM group_info " +
            " WHERE " +
            " id = #{groupId} " +
            " AND " +
            " group_leader_id = #{item}")
    Integer isGroupLeader(Integer groupId, Integer item);

    @Select("SELECT COUNT(group_leader_id) FROM group_info WHERE id = #{groupId}")
    Integer getGroupLeader(Integer groupId);

    @Select("SELECT group_leader_id FROM group_info WHERE id = #{groupId}")
    Integer getGroupLeaderById(Integer groupId);

    @Select("SELECT group_id FROM student_group WHERE student_id = #{studentId}")
    Integer getStudentGroupId(Integer studentId);
}
