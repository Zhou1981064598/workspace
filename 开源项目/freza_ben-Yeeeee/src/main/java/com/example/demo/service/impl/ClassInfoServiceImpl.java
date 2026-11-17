package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.bo.AddDModifyClassBO;
import com.example.demo.bo.GetClassBO;
import com.example.demo.entity.ClassInfo;
import com.example.demo.entity.MajorInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.exception.ValidationFailException;
import com.example.demo.mapper.ClassInfoMapper;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.service.BaseInfoService;
import com.example.demo.service.ClassInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.vo.ClassVO;
import com.example.demo.vo.PageVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Yeeeeee
 * @since 2021-07-23
 */
@Service
public class ClassInfoServiceImpl extends ServiceImpl<ClassInfoMapper, ClassInfo> implements ClassInfoService {

    @Resource
    ClassInfoMapper mapper;
    @Resource
    UserInfoMapper userInfoMapper;
    @Resource
    BaseInfoService service;

    @Override
    public synchronized Boolean addClass(AddDModifyClassBO classBo, Integer operatorId) throws ValidationFailException {
        //-------------------------------参数校验-------------------------------
        if (classBo == null) {
            throw new ValidationFailException("未获取到参数");
        }
        //className
        if (classBo.getClassId() == null) {
            throw new ValidationFailException("班级名称不能为空");
        }
        if (service.getClassBaseInfoById(classBo.getClassId()) == null) {
            throw new ValidationFailException("班级不存在");
        }

        //gradeName
        if (classBo.getGradeId() == null) {
            throw new ValidationFailException("年级名称不能为空");
        }
        if (service.getGradeInfoById(classBo.getGradeId()) == null) {
            throw new ValidationFailException("年级不存在");
        }

        //departmentName
        if (classBo.getDepartmentId() == null) {
            throw new ValidationFailException("院系名称不能为空");
        }
        if (service.getDepartmentInfoById(classBo.getDepartmentId()) == null) {
            throw new ValidationFailException("院系不存在");
        }
        //majorName
        if (classBo.getMajorId() == null) {
            throw new ValidationFailException("专业名称不能为空");
        }
        if (service.getMajorInfoById(classBo.getMajorId()) == null) {
            throw new ValidationFailException("专业不存在");
        }
        if (classBo.getTeacherId() == null) {
            throw new ValidationFailException("班主任并不能为空");
        }
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("id", classBo.getTeacherId());
        wrapper.eq("user_abandon", 0);
        wrapper.eq("user_role", 2);
        try {
            if (userInfoMapper.selectOne(wrapper) == null) {
                throw new ValidationFailException("班主任不可用");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new ValidationFailException("班主任不可用");
        }
        if (classBo.getClassGraduated() == null) {
            throw new ValidationFailException("班级状态不能为空");
        }
        //是否重复验证
        if (mapper.isClassNameExist(classBo.getClassId()) != null) {
            throw new ValidationFailException("已存在班级使用该班级名称");
        }
        List<MajorInfo> majorInfo = service.listGetMajorInfo(classBo.getDepartmentId());
        boolean t = false;
        for (MajorInfo item : majorInfo) {
            if (item.getId().equals(classBo.getMajorId())) {
                t = true;
                break;
            }
        }
        if (!t) {
            throw new ValidationFailException("该院系不存在该专业");
        }
        //===============================================================================

        ClassInfo classInfo = new ClassInfo();
        classInfo.setClassGraduate(classBo.getClassGraduated());
        classInfo.setClassNameId(classBo.getClassId());
        classInfo.setMajorId(classBo.getMajorId());
        classInfo.setGradeId(classBo.getGradeId());
        classInfo.setDepartmentId(classBo.getDepartmentId());
        classInfo.setTeacherId(classBo.getTeacherId());

        classInfo.setCreatePerson(operatorId);
        classInfo.setCreateTime(new Date());
        classInfo.setLastUpgradePerson(operatorId);
        classInfo.setLastUpgradeTime(new Date());
        Integer result = mapper.addClassIntoClassInfo(classInfo);
        return result > 0;
    }

    @Override
    public PageVO<List<ClassVO>> listGetClassInfo(GetClassBO classBO) {

        List<ClassVO> list = mapper.listGetClassInfo(classBO);
        if (list == null || list.size() == 0) {
            return new PageVO<>();
        }
        PageVO<List<ClassVO>> result = new PageVO<>(list);
        result.setRecordCount(list.size());
        result.setPageSize(classBO.getPageSize());
        result.setTotalCount(mapper.listGetClassInfoCount(classBO));
        result.setPageNo(classBO.getPage());
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
    public Boolean modifyClass(AddDModifyClassBO classBO, Integer id, Integer operatorId) throws ValidationFailException {
        if (id == null) {
            throw new ValidationFailException("未能指定修改的班级");
        }
        if (mapper.isClassExist(id) == null) {
            throw new ValidationFailException("目标班级不存在");
        }
        if (classBO.getDepartmentId() == null) {
            throw new ValidationFailException("院系不能为空");
        }
        if (service.getDepartmentInfoById(classBO.getDepartmentId()) == null) {
            throw new ValidationFailException("院系不存在");
        }
        if (classBO.getMajorId() == null) {
            throw new ValidationFailException("专业不能为空");
        }
        if (service.getMajorInfoById(classBO.getMajorId()) == null) {
            throw new ValidationFailException("专业不存在");
        }
        List<MajorInfo> majorInfo = service.listGetMajorInfo(classBO.getDepartmentId());
        boolean t = false;
        for (MajorInfo item : majorInfo) {
            if (item.getId().equals(classBO.getMajorId())) {
                t = true;
                break;
            }
        }
        if (!t) {
            throw new ValidationFailException("该院系不存在该专业");
        }
        if (classBO.getGradeId() == null) {
            throw new ValidationFailException("年级不能为空");
        }
        if (service.getGradeInfoById(classBO.getGradeId()) == null) {
            throw new ValidationFailException("年级不存在");
        }
        if (classBO.getClassId() == null) {
            throw new ValidationFailException("班级不能为空");
        }
        if (service.getClassBaseInfoById(classBO.getClassId()) == null) {
            throw new ValidationFailException("班级不存在");
        }
        if (mapper.isClassNameExistModify(classBO.getClassId(), id) != null) {
            throw new ValidationFailException("班级已被使用");
        }
        if (classBO.getClassGraduated() == null) {
            throw new ValidationFailException("是否结业不能为空");
        }
        if (classBO.getTeacherId() == null) {
            throw new ValidationFailException("喂喂，不应该让人家的班主任为空吧 ╭(╯^╰)╮##");
        }
        if (userInfoMapper.isUserExist(classBO.getTeacherId(), 2) == null) {
            throw new ValidationFailException("该班主任不可用");
        }
        ClassInfo classInfo = new ClassInfo();

        classInfo.setTeacherId(classBO.getTeacherId());
        classInfo.setMajorId(classBO.getMajorId());
        classInfo.setDepartmentId(classBO.getDepartmentId());
        classInfo.setGradeId(classBO.getGradeId());
        classInfo.setClassNameId(classBO.getClassId());
        classInfo.setClassGraduate(classBO.getClassGraduated());

        classInfo.setLastUpgradeTime(new Date());
        classInfo.setLastUpgradePerson(operatorId);

        UpdateWrapper<ClassInfo> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id);
        return mapper.update(classInfo, wrapper) > 0;
    }
}
