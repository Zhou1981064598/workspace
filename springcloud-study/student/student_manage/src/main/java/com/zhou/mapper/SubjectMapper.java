package com.zhou.mapper;


import com.zhou.base.BaseMapper;
import com.zhou.entity.Subject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface SubjectMapper extends BaseMapper<Subject> {
}
