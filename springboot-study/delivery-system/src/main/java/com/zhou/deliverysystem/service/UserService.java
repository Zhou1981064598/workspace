package com.zhou.deliverysystem.service;


import com.zhou.deliverysystem.enenty.Role;
import com.zhou.deliverysystem.enenty.User;

import com.zhou.deliverysystem.pojo.Paging;
import com.zhou.deliverysystem.pojo.ResultCode;


import java.util.HashMap;
import java.util.List;

public interface UserService {
    HashMap<String,String> login(User user);
    ResultCode register(User user);
    User getuser();
    Paging<User> getAllRider(Paging<User> paging);
    User getUserById(Long id);
    void logout();
    String updateNameById(String name);
    String updatePhoneById(String name);
    String updatePasswordById(String password);
    List<Role> selectListByUserId();
    String examine (Long userId,Integer auditResult);
    void addRider(Long userId);
    void setScore(Long courierId);
    void banUser(List<Integer> list);
    void activate(List<Integer> list);
}
