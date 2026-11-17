package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Userinfo;
import com.example.demo.util.ReturnData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Date:2022-11-07
 * Time:19:50
 *
 * @Author: 东莞呵呵
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public Userinfo login(Userinfo userinfo){
        List<Userinfo> userinfoList=userMapper.login(userinfo);
        for (int i = 0; i < userinfoList.size(); i++) {
            if(userinfoList.get(i).getUser_name().equals(userinfo.getUser_name())
                    &&userinfoList.get(i).getPassword().equals(userinfo.getPassword())){
                return userinfoList.get(i);
            }
        }
        return null;
    }




    public List<Userinfo> paging(String userName, int role, int pageIndex, int pageSize) {
        int pageOffset=(pageIndex-1)*pageSize;
        List<Userinfo> list= userMapper.paging(userName,role,pageOffset,pageSize);
        return list;
    }
}
