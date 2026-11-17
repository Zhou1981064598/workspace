package com.zjj.org.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zjj.org.base.Result;
import com.zjj.org.base.ResultCode;
import com.zjj.org.entity.Student;
import com.zjj.org.entity.vo.StudentVo;
import com.zjj.org.mapper.StudentMapper;
import com.zjj.org.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * StudentService接口实现类
 * 学生管理
 *
 * @author zjj
 * @date 2022-05-11
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 查询学生信息
     *
     * @param studentVo
     * @return
     */
    @Override
    public Result selectStuList(StudentVo studentVo) {
//        分页
        PageHelper.startPage(studentVo.getPage(), studentVo.getLimit());
        //        查询
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(studentVo.getStuNo())) {
            wrapper.eq("stu_no", studentVo.getStuNo());
        }
        if (StringUtils.isNotEmpty(studentVo.getStuName())) {
            wrapper.eq("stu_name", studentVo.getStuName());
        }

        List<Student> userList = studentMapper.selectList(wrapper);
        //        获取分页数据
        PageInfo<Student> pageInfo = new PageInfo<>(userList);
        return Result.success(pageInfo.getList(), (int) pageInfo.getTotal());
    }

    /**
     * 删除学生信息
     *
     * @param id 主键
     * @return
     */
    @Override
    public Result deleteById(int id) {
        int row = studentMapper.deleteById(id);
        if (row <= 0) {
            return Result.failure(ResultCode.PARAM_IS_INVALID);
        }
        return Result.success();
    }

    /**
     * 添加或者修改学生信息
     *
     * @param student 学生信息实体类
     * @return
     */
    @Override
    public Result addAndEditStu(Student student) {
        int row = 0;
        if (student.getId() != null) {
            row = studentMapper.updateById(student);
        } else {
            row = studentMapper.insert(student);
        }
        if (row <= 0) {
            return Result.failure(ResultCode.PARAM_IS_INVALID);
        }
        return Result.success();
    }
}
