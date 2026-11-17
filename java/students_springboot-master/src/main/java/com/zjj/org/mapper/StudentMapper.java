package com.zjj.org.mapper;

import com.zjj.org.base.BaseMapper;
import com.zjj.org.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Student对象持久层
 * @author zjj
 * @date 2022-04-27
 */
@Mapper
@Repository
public interface StudentMapper extends BaseMapper<Student> {

}
