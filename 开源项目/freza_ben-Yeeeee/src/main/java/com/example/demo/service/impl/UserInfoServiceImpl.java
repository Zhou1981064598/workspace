package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.bo.*;
import com.example.demo.entity.UserInfo;
import com.example.demo.enums.Sex;
import com.example.demo.enums.StudentRole;
import com.example.demo.enums.TeacherRole;
import com.example.demo.enums.UserRole;
import com.example.demo.exception.ValidationFailException;
import com.example.demo.mapper.ClassInfoMapper;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.util.JwtUtil;
import com.example.demo.util.MD5Util;
import com.example.demo.util.RegExpValidatorUtils;
import com.example.demo.vo.CheckBoxTeacherVO;
import com.example.demo.vo.PageVO;
import com.example.demo.vo.StudentVO;
import com.example.demo.vo.TeacherVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Yeeeeee
 * @since 2021-07-21
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Resource
    UserInfoMapper mapper;
    @Resource
    ClassInfoMapper classInfoMapper;

    @Override
    public UserInfo userLogin(UserBO user) {


        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_password", MD5Util.getMd5(user.getUserPassword()));
        wrapper.eq("user_id", user.getUserId());
        wrapper.eq("user_role", user.getUserRole());

        UserInfo userInfo = mapper.selectOne(wrapper);
        try {
            if (userInfo.getUserId() == null) {
                return null;
            }
        } catch (NullPointerException e) {
            return null;
        }
        String token = JwtUtil.InsertToken(userInfo.getUserId(), userInfo.getId());
        userInfo.setUserToken(token);
        userInfo.setHasLogin(true);
        mapper.updateById(userInfo);
        return userInfo;
    }

    @Override
    public Boolean userQuit(Integer id) {
        UpdateWrapper<UserInfo> wrapper = new UpdateWrapper<>();
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        wrapper.set("user_token", "null");
        int result = mapper.update(userInfo, wrapper);
        return result != 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized Boolean addUser(AddModifyUserBO user, Integer operatorId) throws ValidationFailException {
        //------------------------------参数验证-----------------------------------------------
        if (user == null) {
            throw new ValidationFailException("未获取到参数");
        }
        if (user.getUserRole() == null || !UserRole.isUserRole(user.getUserRole())) {
            throw new ValidationFailException("角色权限错误");
        }
        if (user.getUserId() == null || user.getUserId().toString().length() <= 6) {
            throw new ValidationFailException("错误的用户工号格式");
        }
        if (user.getUserName() == null) {
            throw new ValidationFailException("错误的用户姓名");
        }
        if (user.getUserIdentity() == null || !RegExpValidatorUtils.IsIDcard(user.getUserIdentity())) {
            throw new ValidationFailException("错误的身份证号码");
        }
        if (user.getUserSex() == null || !Sex.isSex(user.getUserSex())) {
            throw new ValidationFailException("错误的性别输入");
        }
        if (user.getUserPhone() == null || !RegExpValidatorUtils.IsTelephone(user.getUserPhone())) {
            throw new ValidationFailException("错误的手机号输入");
        }
        if (user.getUserRole() == 2) {
            if (user.getTeacherRole() == null || !TeacherRole.isTeacherRole(user.getTeacherRole())) {
                throw new ValidationFailException("错误的职称输入");
            }
        }
        if (user.getUserRole() == 1) {
            if (user.getStudentEmail() == null || !RegExpValidatorUtils.isEmail(user.getStudentEmail())) {
                throw new ValidationFailException("错误的邮箱输入");
            }
            if (user.getStudentRole() == null || !StudentRole.isStudentRole(user.getStudentRole())) {
                throw new ValidationFailException("错误的学生角色输入");
            }
            if (user.getStudentClassId() == null || classInfoMapper.isClassExist(user.getStudentClassId()) == null) {
                throw new ValidationFailException("错误的学生班级输入");
            }
        }
        //-------------------------------重复验证------------------------------------------------------
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", user.getUserId());
        if (mapper.selectCount(wrapper) > 0) {
            throw new ValidationFailException("用户工号已被使用");
        }
        wrapper = new QueryWrapper<>();
        wrapper.eq("user_identity", user.getUserIdentity());
        if (mapper.selectCount(wrapper) > 0) {
            throw new ValidationFailException("用户身份证号已被使用");
        }
        wrapper = new QueryWrapper<>();
        wrapper.eq("user_phone", user.getUserPhone());
        if (mapper.selectCount(wrapper) > 0) {
            throw new ValidationFailException("用户手机号已被使用");
        }
        if (user.getUserRole() == 1) {
            wrapper = new QueryWrapper<>();
            wrapper.eq("student_email", user.getStudentEmail());
            if (mapper.selectCount(wrapper) > 0) {
                throw new ValidationFailException("邮箱已被占用");
            }
        }
        //---------------------------------------------------------------------------------------------

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getUserId());
        String userPassword = user.getUserIdentity().substring(user.getUserIdentity().length() - 6);
        userInfo.setUserPassword(MD5Util.getMd5(userPassword));
        userInfo.setUserName(user.getUserName());
        userInfo.setUserIdentity(user.getUserIdentity());
        userInfo.setUserSex(user.getUserSex());
        userInfo.setUserPhone(user.getUserPhone());
        userInfo.setUserRole(user.getUserRole());

        userInfo.setCreateTime(new Date());
        userInfo.setCreatePerson(operatorId);
        userInfo.setLastUpgradeTime(new Date());
        userInfo.setLastUpgradePerson(operatorId);

        if (user.getUserRole() == 2) {
            userInfo.setTeacherRole(user.getTeacherRole());
        }
        if (user.getUserRole() == 1) {
            userInfo.setStudentEmail(user.getStudentEmail());
            userInfo.setStudentGraduate(false);
            if (mapper.addStudentClass(user.getId(), user.getStudentClassId(), user.getStudentRole()) < 1) {
                throw new ValidationFailException("插入失败");
            }
        }

        return mapper.addUser(userInfo) > 0;
    }

    @Override
    public PageVO<List<TeacherVO>> listGetUser(GetTeacherBO user) throws ValidationFailException {

        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        if (user.getUserAbandon() != null) {
            wrapper.eq("user_abandon", user.getUserAbandon());
        }
        if (user.getUserId() != null) {
            wrapper.eq("user_id", user.getUserId());
        }
        if (user.getUserIdentity() != null) {
            if (!RegExpValidatorUtils.IsIDcard(user.getUserIdentity())) {
                throw new ValidationFailException("错误的身份证输入");
            }
            wrapper.eq("user_identity", user.getUserIdentity());
        }
        if (user.getUserPhone() != null) {
            if (!RegExpValidatorUtils.IsTelephone(user.getUserPhone())) {
                throw new ValidationFailException("错误的手机号输入");
            }
            wrapper.eq("user_phone", user.getUserPhone());
        }
        if (user.getUserName() != null) {
            wrapper.likeLeft("user_name", user.getUserName());
        }
        if (user.getUserId() != null) {
            if (user.getUserId().toString().length() <= 6) {
                throw new ValidationFailException("错误的工号输入");
            }
        }

        List<TeacherVO> userInfoList;
        userInfoList = mapper.listGetUser(user);
        if (userInfoList == null) {
            return new PageVO<>();
        }
        PageVO<List<TeacherVO>> result = new PageVO<>(userInfoList);
        result.setPageNo(user.getPage());
        result.setPageSize(user.getPageSize());
        result.setRecordCount(userInfoList.size());
        result.setTotalCount(mapper.listGetUserCount(user));
        if (result.getTotalCount() == null) {
            result.setTotalPage(0);
        } else {
            result.setTotalPage(result.getTotalCount() % result.getPageSize() == 0 ?
                    result.getTotalCount() / result.getPageSize()
                    : result.getTotalCount() / result.getPageSize() + 1);
        }
        return result;
    }

    @Override
    public PageVO<List<StudentVO>> listGetStudent(GetStudentBO user) throws ValidationFailException {

        if (user.getUserId() != null) {
            if (user.getUserId().toString().length() <= 6) {
                throw new ValidationFailException("错误的工号输入");
            }
        }
        if (user.getUserIdentity() != null) {
            if (!RegExpValidatorUtils.IsIDcard(user.getUserIdentity())) {
                throw new ValidationFailException("错误的身份证输入");
            }
        }
        if (user.getStudentEmail() != null) {
            if (!RegExpValidatorUtils.isEmail(user.getStudentEmail())) {
                throw new ValidationFailException("错误的邮箱输入");
            }
        }
        if (user.getUserSex() != null) {
            if (!Sex.isSex(user.getUserSex())) {
                throw new ValidationFailException("错误的性别输入");
            }
        }
        if (user.getUserPhone() != null) {
            if (!RegExpValidatorUtils.IsTelephone(user.getUserPhone())) {
                throw new ValidationFailException("错误的手机号输入");
            }
        }
        if (user.getStudentRole() != null) {
            if (!StudentRole.isStudentRole(user.getStudentRole())) {
                throw new ValidationFailException("错误的角色输入");
            }
        }

        PageVO<List<StudentVO>> result;
        List<StudentVO> list = mapper.listGetStudent(user);
        if (list == null) {
            return new PageVO<>();
        }
        result = new PageVO<>(list);
        result.setPageNo(user.getPage());
        result.setPageSize(user.getPageSize());
        result.setRecordCount(list.size());
        result.setTotalCount(mapper.listGetStudentCount(user));
        if (result.getTotalCount() == null) {
            result.setTotalPage(0);
        } else {
            result.setTotalPage(result.getTotalCount() % result.getPageSize() == 0 ?
                    result.getTotalCount() / result.getPageSize()
                    : result.getTotalCount() / result.getPageSize() + 1);
        }
        return result;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized Boolean modifyUser(AddModifyUserBO user, Integer id, Integer operatorId) throws ValidationFailException {
        //------------------------------参数验证-----------------------------------------------
        if (user == null) {
            throw new ValidationFailException("未获取到参数");
        }
        if (id == null) {
            throw new ValidationFailException("未获取到用户id");
        }
        if (user.getUserRole() == null) {
            throw new ValidationFailException("角色权限遗失");
        }
        if (user.getUserIdentity() == null) {
            throw new ValidationFailException("身份证不能为空");
        }
        if (user.getUserSex() == null) {
            throw new ValidationFailException("性别不能为空");
        }
        if (user.getUserPhone() == null) {
            throw new ValidationFailException("手机号不能为空");
        }
        if (user.getTeacherRole() == null) {
            throw new ValidationFailException("教师职称不能为空");
        }
        if (user.getUserRole() != null && !UserRole.isUserRole(user.getUserRole())) {
            throw new ValidationFailException("角色权限错误");
        }

        if (user.getUserIdentity() != null && !RegExpValidatorUtils.IsIDcard(user.getUserIdentity())) {
            throw new ValidationFailException("错误的身份证号码");
        }
        if (user.getUserSex() != null && !Sex.isSex(user.getUserSex())) {
            throw new ValidationFailException("错误的性别输入");
        }
        if (user.getUserPhone() != null && !RegExpValidatorUtils.IsTelephone(user.getUserPhone())) {
            throw new ValidationFailException("错误的手机号输入");
        }
        if (user.getTeacherRole() != null && !TeacherRole.isTeacherRole(user.getTeacherRole())) {
            throw new ValidationFailException("错误的职称输入");
        }
        //-------------------------------重复验证------------------------------------------------------

        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();

        if (user.getUserId() != null) {
            wrapper.eq("user_id", user.getUserId());
            wrapper.ne("id", id);
            if (mapper.selectCount(wrapper) > 0) {
                throw new ValidationFailException("用户工号已被使用");
            }
        }
        if (user.getUserIdentity() != null) {
            wrapper = new QueryWrapper<>();
            wrapper.eq("user_identity", user.getUserIdentity());
            wrapper.ne("id", id);
            if (mapper.selectCount(wrapper) > 0) {
                throw new ValidationFailException("用户身份证号已被使用");
            }
        }
        if (user.getUserPhone() != null) {
            wrapper = new QueryWrapper<>();
            wrapper.eq("user_phone", user.getUserPhone());
            wrapper.ne("id", id);
            if (mapper.selectCount(wrapper) > 0) {
                throw new ValidationFailException("用户手机号已被使用");
            }
        }
        //---------------------------------------------------------------------------------------------
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        int i = 0;
        if (user.getUserId() != null) {
            i++;
            userInfo.setUserId(user.getId());
        }
        if (user.getTeacherRole() != null) {
            i++;
            userInfo.setTeacherRole(user.getTeacherRole());
        }
        if (user.getUserName() != null) {
            i++;
            userInfo.setUserName(user.getUserName());
        }
        if (user.getUserPhone() != null) {
            i++;
            userInfo.setUserPhone(user.getUserPhone());
        }
        if (user.getUserIdentity() != null) {
            i++;
            userInfo.setUserIdentity(user.getUserIdentity());
        }
        if (user.getUserAbandon() != null) {
            i++;
            userInfo.setUserAbandon(user.getUserAbandon());
        }
        if (i == 0) {
            return true;
        }
        return mapper.updateById(userInfo) > 0;
    }

    @Override
    public PageVO<List<CheckBoxTeacherVO>> listGetAllTeacher() {
        PageVO<List<CheckBoxTeacherVO>> result = new PageVO<>();
        List<CheckBoxTeacherVO> teachers = mapper.listGetAllTeacher();
        if (teachers == null) {
            return new PageVO<>();
        }
        result.setData(teachers);
        result.setPageNo(1);
        result.setTotalPage(1);
        result.setTotalCount(teachers.size());
        result.setPageSize(teachers.size());
        result.setRecordCount(teachers.size());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized Boolean modifyStudent(AddModifyUserBO user, Integer id, Integer operatorId) throws ValidationFailException {
        UserInfo userInfo = new UserInfo();
        if (id == null) {
            throw new ValidationFailException("未能获取学生id");
        }
        userInfo.setId(id);
        QueryWrapper<UserInfo> wrapper;
        if (user.getStudentClassId() != null) {
            if (classInfoMapper.isClassExist(user.getStudentClassId()) == null || classInfoMapper.isClassExist(user.getStudentClassId()) == 0) {
                throw new ValidationFailException("不存在该班级");
            }
        } else {
            throw new ValidationFailException("未能找到学生班级");
        }
        if (user.getUserName() != null) {
            userInfo.setUserName(user.getUserName());
        } else {
            throw new ValidationFailException("姓名不能为空");
        }

        if (user.getUserId() != null) {
            if (user.getUserId().toString().length() < 6 || user.getUserId().toString().length() > 18) {
                throw new ValidationFailException("错误的学号输入");
            }
            wrapper = new QueryWrapper<>();
            wrapper.eq("user_id", user.getUserId());
            wrapper.ne("id", id);
            if (mapper.selectCount(wrapper) != 0) {
                throw new ValidationFailException("学号已被使用");
            }
            userInfo.setUserId(user.getUserId());
        } else {
            throw new ValidationFailException("学号不能为空");
        }

        if (user.getUserSex() != null) {
            if (!Sex.isSex(user.getUserSex())) {
                throw new ValidationFailException("错误的性别输入");
            }
            userInfo.setUserSex(user.getUserSex());
        } else {
            throw new ValidationFailException("性别不能为空");
        }

        if (user.getUserIdentity() != null) {
            if (!RegExpValidatorUtils.IsIDcard(user.getUserIdentity())) {
                throw new ValidationFailException("错误的身份证号输入");
            }
            wrapper = new QueryWrapper<>();
            wrapper.eq("user_identity", user.getUserIdentity());
            wrapper.ne("id", id);
            if (mapper.selectCount(wrapper) != 0) {
                throw new ValidationFailException("身份证已被使用");
            }
            userInfo.setUserIdentity(user.getUserIdentity());
        } else {
            throw new ValidationFailException("身份证不能为空");
        }

        if (user.getStudentEmail() != null) {
            if (!RegExpValidatorUtils.isEmail(user.getStudentEmail())) {
                throw new ValidationFailException("错误的邮箱输入");
            }
            wrapper = new QueryWrapper<>();
            wrapper.eq("student_email", user.getStudentEmail());
            wrapper.ne("id", id);
            if (mapper.selectCount(wrapper) != 0) {
                throw new ValidationFailException("邮箱已被使用");
            }
            userInfo.setStudentEmail(user.getStudentEmail());
        } else {
            throw new ValidationFailException("邮箱不能为空");
        }

        if (user.getUserPhone() != null) {
            if (!RegExpValidatorUtils.IsTelephone(user.getUserPhone())) {
                throw new ValidationFailException("错误的手机号输入");
            }
            wrapper = new QueryWrapper<>();
            wrapper.eq("user_phone", user.getUserPhone());
            wrapper.ne("id", id);
            if (mapper.selectCount(wrapper) != 0) {
                throw new ValidationFailException("手机号已被使用");
            }
            userInfo.setUserPhone(user.getUserPhone());
        } else {
            throw new ValidationFailException("手机号不能为空");
        }

        if (user.getStudentRole() != null) {
            if (!StudentRole.isStudentRole(user.getStudentRole())) {
                throw new ValidationFailException("错误的角色输入");
            }
        } else {
            throw new ValidationFailException("项目角色不能为空");
        }
        try {
            if (mapper.updateStudentClass(user.getId(), user.getStudentClassId(), user.getStudentRole()) < 0) {
                throw new ValidationFailException("更新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        userInfo.setLastUpgradeTime(new Date());
        userInfo.setLastUpgradePerson(operatorId);
        return mapper.updateById(userInfo) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public synchronized Boolean disableUser(Integer[] id, Integer operatorId) throws ValidationFailException {
        for (Integer item : id) {
            if (mapper.isUserExist(item, 1) < 1 && mapper.isUserExist(item, 2) < 1) {
                throw new ValidationFailException("用户不存在");
            }
            if (mapper.disableUser(item, operatorId, new Date()) < 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean modifyPassword(ModifyPasswordBO modifyPasswordBO, Integer id) throws ValidationFailException {
        if (modifyPasswordBO.getPassword() == null || modifyPasswordBO.getPassword().length() < 6 || modifyPasswordBO.getPassword().length() > 18) {
            throw new ValidationFailException("错误的密码格式");
        }
        if (modifyPasswordBO.getPasswordConfirm() == null || !modifyPasswordBO.getPasswordConfirm().equals(modifyPasswordBO.getPassword())) {
            throw new ValidationFailException("二次密码验证失败");
        }
        if (modifyPasswordBO.getNewPassword() == null || modifyPasswordBO.getNewPassword().length() < 6 || modifyPasswordBO.getNewPassword().length() > 18) {
            throw new ValidationFailException("错误的密码格式");
        }
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_password", MD5Util.getMd5(modifyPasswordBO.getPassword()));
        wrapper.eq("id", id);
        UserInfo userInfo = mapper.selectOne(wrapper);
        try {
            if (userInfo.getUserId() == null) {
                return null;
            }
        } catch (NullPointerException e) {
            return null;
        }
        if (mapper.updateUserPassword(MD5Util.getMd5(modifyPasswordBO.getNewPassword()), id) < 1) {
            throw new ValidationFailException("更新失败");
        }
        return true;
    }


}
