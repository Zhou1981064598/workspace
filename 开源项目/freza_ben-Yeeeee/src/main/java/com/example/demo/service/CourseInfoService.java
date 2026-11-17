package com.example.demo.service;

import com.example.demo.bo.AddModifyCourseBO;
import com.example.demo.bo.AddStudentIntoCourseBO;
import com.example.demo.bo.GetCourseBO;
import com.example.demo.entity.CourseInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.exception.ValidationFailException;
import com.example.demo.vo.CourseVO;
import com.example.demo.vo.PageVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Yeeeeee
 * @since 2021-07-26
 */
public interface CourseInfoService extends IService<CourseInfo> {
    Boolean addCourse(AddModifyCourseBO addModifyCourseBO, Integer operatorId) throws Exception;

    PageVO<List<CourseVO>> listGetCourseInfo(GetCourseBO getCourseBO) throws ValidationFailException;

    Boolean addStudentIntoCourse(AddStudentIntoCourseBO addStudentIntoCourseBO) throws Exception;

    Boolean modifyCourse(AddModifyCourseBO courseBO, Integer operatorId) throws Exception;
}
