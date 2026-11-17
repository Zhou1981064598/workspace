package com.example.demo.service;

import com.example.demo.bo.*;
import com.example.demo.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.exception.ValidationFailException;
import com.example.demo.vo.CheckBoxTeacherVO;
import com.example.demo.vo.PageVO;
import com.example.demo.vo.StudentVO;
import com.example.demo.vo.TeacherVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Yeeeeee
 * @since 2021-07-21
 */
public interface UserInfoService extends IService<UserInfo> {

    UserInfo userLogin(UserBO user);

    Boolean userQuit(Integer userId);

    Boolean addUser(AddModifyUserBO user, Integer operatorId) throws ValidationFailException;

    PageVO<List<TeacherVO>> listGetUser(GetTeacherBO getTeacherBO) throws ValidationFailException;

    PageVO<List<StudentVO>> listGetStudent(GetStudentBO getStudentBO) throws ValidationFailException;

    Boolean modifyUser(AddModifyUserBO addModifyUserBo, Integer id, Integer operatorId) throws ValidationFailException;

    PageVO<List<CheckBoxTeacherVO>> listGetAllTeacher();

    Boolean modifyStudent(AddModifyUserBO addModifyUserBo, Integer id, Integer operatorId) throws ValidationFailException;

    Boolean disableUser(Integer[] id, Integer operatorId) throws ValidationFailException;

    Boolean modifyPassword(ModifyPasswordBO modifyPasswordBO, Integer id) throws ValidationFailException;
}
