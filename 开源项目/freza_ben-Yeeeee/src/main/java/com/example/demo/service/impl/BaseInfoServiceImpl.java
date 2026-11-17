package com.example.demo.service.impl;

import com.example.demo.entity.ClassBaseInfo;
import com.example.demo.entity.DepartmentInfo;
import com.example.demo.entity.GradeInfo;
import com.example.demo.entity.MajorInfo;
import com.example.demo.exception.ValidationFailException;
import com.example.demo.mapper.BaseInfoMapper;
import com.example.demo.service.BaseInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class BaseInfoServiceImpl implements BaseInfoService {
    @Resource
    BaseInfoMapper mapper;

    @Override
    public List<MajorInfo> listGetMajorInfo(Integer id) throws ValidationFailException {
        if (id == null) {
            throw new ValidationFailException("请先输入院系");
        }
        if (getDepartmentInfoById(id) == null) {
            throw new ValidationFailException("院系不存在");
        }
        return mapper.listGetMajorInfo(id);
    }

    @Override
    public List<MajorInfo> listGetMajorInfo() {
        return mapper.listGetMajorInfoNoPram();
    }

    @Override
    public List<DepartmentInfo> listGetDepartmentInfo() {
        return mapper.listGetDepartmentInfo();
    }

    @Override
    public List<ClassBaseInfo> listGetClassBaseInfo() {
        return mapper.listGetClassBaseInfo();
    }

    @Override
    public List<GradeInfo> listGetGradeInfo() {
        return mapper.listGetGradeInfo();
    }

    @Override
    public MajorInfo getMajorInfoById(Integer id) {
        return mapper.getMajorInfoByID(id);
    }

    @Override
    public DepartmentInfo getDepartmentInfoById(Integer id) {
        return mapper.getDepartmentInfoByID(id);
    }

    @Override
    public ClassBaseInfo getClassBaseInfoById(Integer id) {
        return mapper.getClassBaseInfoByID(id);
    }

    @Override
    public GradeInfo getGradeInfoById(Integer id) {
        return mapper.getGradeInfoByID(id);
    }


}
