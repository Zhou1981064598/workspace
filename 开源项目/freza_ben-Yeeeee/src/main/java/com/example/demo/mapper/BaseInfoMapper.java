package com.example.demo.mapper;


import com.example.demo.entity.ClassBaseInfo;
import com.example.demo.entity.DepartmentInfo;
import com.example.demo.entity.GradeInfo;
import com.example.demo.entity.MajorInfo;
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
public interface BaseInfoMapper {

    @Select("SELECT id , class_base_name FROM class_base_info ")
    List<ClassBaseInfo> listGetClassBaseInfo();


    @Select("SELECT id , department_name FROM department_info ")
    List<DepartmentInfo> listGetDepartmentInfo();


    @Select("SELECT id , major_name FROM major_info WHERE department_id = #{id} ")
    List<MajorInfo> listGetMajorInfo(Integer id);

    @Select("SELECT id , major_name FROM major_info  ")
    List<MajorInfo> listGetMajorInfoNoPram();

    @Select("SELECT id , grade_name FROM grade_info ")
    List<GradeInfo> listGetGradeInfo();

    @Select("SELECT id , major_name FROM major_info WHERE id = #{id} ")
    MajorInfo getMajorInfoByID(Integer id);

    @Select("SELECT id , department_name FROM department_info WHERE id = #{id} ")
    DepartmentInfo getDepartmentInfoByID(Integer id);

    @Select("SELECT id , class_base_name FROM class_base_info WHERE id = #{id} ")
    ClassBaseInfo getClassBaseInfoByID(Integer id);

    @Select("SELECT id , grade_name FROM grade_info WHERE id = #{id} ")
    GradeInfo getGradeInfoByID(Integer id);
}
