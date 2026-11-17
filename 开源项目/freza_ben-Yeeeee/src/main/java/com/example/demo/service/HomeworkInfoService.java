package com.example.demo.service;

import com.example.demo.bo.*;
import com.example.demo.entity.HomeworkInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.exception.ValidationFailException;
import com.example.demo.vo.PageVO;
import com.example.demo.vo.StudentHomeworkVO;
import com.example.demo.vo.TeacherHomeworkVO;

import java.text.ParseException;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Yeeeeee
 * @since 2021-07-28
 */
public interface HomeworkInfoService extends IService<HomeworkInfo> {

    Boolean addHomework(AddModifyHomeworkBO homeworkBO, Integer operatorId) throws ValidationFailException;

    PageVO<List<StudentHomeworkVO>> listGetStudentHomework(GetHomeworkBO getHomeworkBO, Integer operatorId, Integer userRole) throws ValidationFailException;

    PageVO<List<TeacherHomeworkVO>> listGetTeacherHomework(GetHomeworkBO getHomeworkBO, Integer operatorId, Integer userRole) throws ValidationFailException;

    Boolean delHomework(Integer id, Integer operatorId) throws ValidationFailException;

    Boolean commitHomework(CommitHomeworkBO homeworkBO, Integer operatorId) throws ValidationFailException, ParseException;

    PageVO<List<StudentHomeworkVO>> listGetTeacherCorrectHomework(GetCorrectHomeworkBO getHomeworkBO, Integer operatorId) throws ValidationFailException;

    Boolean correctHomework(CorrectHomeworkBO homeworkBO, Integer operatorId) throws ValidationFailException;
}
