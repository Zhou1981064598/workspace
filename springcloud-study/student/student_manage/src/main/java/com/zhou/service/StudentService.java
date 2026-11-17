package com.zhou.service;

import com.zhou.base.Result;
import com.zhou.entity.Student;
import com.zhou.entity.vo.StudentVo;

import org.springframework.stereotype.Service;


@Service
public interface StudentService {
    /**
     * 查询学生信息
     * @return
     */
    Result selectStuList(StudentVo studentVo);

    /**
     * 删除学生信息
     * @param id 主键
     * @return
     */
    Result deleteById(int id);

    /**
     * 添加或者修改学生信息
     * @param student 学生信息实体类
     * @return
     */
    Result addAndEditStu(Student student);
}
