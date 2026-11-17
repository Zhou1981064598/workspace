package com.zjj.org.service;

import com.zjj.org.base.Result;
import com.zjj.org.entity.Student;
import com.zjj.org.entity.vo.StudentVo;
import org.springframework.stereotype.Service;

/**
 * 接口
 * 学生管理
 * @author zjj
 * @date 2022-05-11
 */
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
