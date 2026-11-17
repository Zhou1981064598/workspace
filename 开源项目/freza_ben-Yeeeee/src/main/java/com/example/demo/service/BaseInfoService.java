package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.exception.ValidationFailException;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Yeeeeee
 * @since 2021-07-23
 */
public interface BaseInfoService {
    List<MajorInfo> listGetMajorInfo(Integer departmentId) throws ValidationFailException;

    List<MajorInfo> listGetMajorInfo();

    List<DepartmentInfo> listGetDepartmentInfo();

    List<ClassBaseInfo> listGetClassBaseInfo();

    List<GradeInfo> listGetGradeInfo();

    MajorInfo getMajorInfoById(Integer id);

    DepartmentInfo getDepartmentInfoById(Integer id);

    ClassBaseInfo getClassBaseInfoById(Integer id);

    GradeInfo getGradeInfoById(Integer id);
}
