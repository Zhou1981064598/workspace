package com.example.springboot02.mapper;

import com.example.springboot02.entity.Test;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface TestMapper {
//    @Select("select * from test")
    List<Test> findAll();
}
