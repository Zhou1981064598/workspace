package com.example.demo.mapper;

import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Date:2022-11-19
 * Time:22:31
 *
 * @Author: 东莞呵呵
 */
class UserMapperTest {

    @Resource
    private UserMapper userMapper;
    @Test
    void paging() {
        userMapper.paging()
    }
}