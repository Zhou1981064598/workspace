package com.example.demo.mapper;

import com.example.demo.model.Userinfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Date:2022-11-07
 * Time:19:53
 *
 * @Author: 东莞呵呵
 */
@Mapper
public interface UserMapper {
    /***
     * 登录
     * @param userinfo
     * @return
     */
    List<Userinfo> login(Userinfo userinfo);

    /***
     * 分页
     * @param userName
     * @param role
     * @param pageOffset
     * @param pageSize
     * @return
     */
    List<Userinfo> paging(String userName, int role, int pageOffset, int pageSize);
}
