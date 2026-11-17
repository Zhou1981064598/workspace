package com.zjj.org.mapper;

import com.zjj.org.base.BaseMapper;
import com.zjj.org.entity.Subject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author zjj
 * @date 2022-05-25
 */
@Mapper
@Repository
public interface SubjectMapper extends BaseMapper<Subject> {
}
