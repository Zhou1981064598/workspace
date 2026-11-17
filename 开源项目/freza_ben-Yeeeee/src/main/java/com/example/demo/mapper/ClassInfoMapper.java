package com.example.demo.mapper;

import com.example.demo.bo.GetClassBO;
import com.example.demo.entity.ClassInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.vo.ClassVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Yeeeeee
 * @since 2021-07-23
 */
@Mapper
public interface ClassInfoMapper extends BaseMapper<ClassInfo> {

    @Select("SELECT id FROM class_info WHERE class_name_id = #{id} ")
    Integer isClassNameExist(Integer id);

    @Select("SELECT id FROM class_info WHERE id = #{id}")
    Integer isClassExist(Integer id);

    @Select("SELECT id FROM class_info WHERE class_name_id = #{className} AND id != #{id}")
    Integer isClassNameExistModify(Integer className, Integer id);

    @Select("<script>" +
            " SELECT " +
            " id , " +
            " department_name , " +
            " grade_name , " +
            " major_name , " +
            " user_name , " +
            " teacher_id , " +
            " total_student , " +
            " class_base_name , " +
            " class_graduate " +
            " FROM " +
            " class_teacher_view " +
            " WHERE 1=1 " +
            " <if test=\"majorId!=null \"> " +
            " AND major_id = #{majorId} " +
            " </if> " +
            " <if test=\"departmentId!=null \"> " +
            " AND department_id = #{departmentId} " +
            " </if> " +
            " <if test=\"gradeId!=null \"> " +
            " AND grade_id = #{gradeId} " +
            " </if> " +
            " <if test=\"classId!=null \"> " +
            " AND class_name_id = #{classId} " +
            " </if> " +
            " <if test=\"teacherId!=null \"> " +
            " AND teacher_id = #{teacherId} " +
            " </if> " +
            " <if test=\"graduate!=null \"> " +
            " AND class_graduate = #{graduate} " +
            " </if> " +
            " LIMIT #{pageOfSet} , #{pageSize} " +
            "</script>  ")
    List<ClassVO> listGetClassInfo(GetClassBO classBO);

    @Select("<script>" +
            " SELECT " +
            " COUNT(*) " +
            " FROM " +
            " class_teacher_view " +
            " WHERE 1=1 " +
            " <if test=\"majorId!=null \"> " +
            " AND major_id = #{majorId} " +
            " </if> " +
            " <if test=\"departmentId!=null \"> " +
            " AND department_id = #{departmentId} " +
            " </if> " +
            " <if test=\"gradeId!=null \"> " +
            " AND grade_id = #{gradeId} " +
            " </if> " +
            " <if test=\"classId!=null \"> " +
            " AND class_name_id = #{classId} " +
            " </if> " +
            " <if test=\"teacherId!=null \"> " +
            " AND teacher_id = #{teacherId} " +
            " </if> " +
            " <if test=\"graduate!=null \"> " +
            " AND class_graduate = #{graduate} " +
            " </if> " +
            "</script>  ")
    Integer listGetClassInfoCount(GetClassBO classBO);

    @Insert("INSERT INTO class_info(" +
            " class_name_id , " +
            " department_id , " +
            " major_id , " +
            " grade_id , " +
            " teacher_id , " +
            " class_graduate , " +
            " last_upgrade_time , " +
            " last_upgrade_person , " +
            " create_time , " +
            " create_person )" +
            " VALUE(" +
            " #{classNameId} , " +
            " #{departmentId} , " +
            " #{majorId} , " +
            " #{gradeId} , " +
            " #{teacherId} , " +
            " #{classGraduate} , " +
            " #{lastUpgradeTime} , " +
            " #{lastUpgradePerson} , " +
            " #{createTime} , " +
            " #{createPerson} ) ")
    Integer addClassIntoClassInfo(ClassInfo classInfo);

}
