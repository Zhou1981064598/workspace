package com.zhou.deliverysystem.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhou.deliverysystem.enenty.Address;
import com.zhou.deliverysystem.dao.AddressDao;
import com.zhou.deliverysystem.pojo.LoginUser;
import com.zhou.deliverysystem.service.AddressService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 19810
* @description 针对表【address】的数据库操作Service实现
* @createDate 2023-04-21 18:37:41
*/
@Service
public class AddressServiceImpl extends ServiceImpl<AddressDao, Address> implements AddressService {
    @Resource
    AddressDao addressDao;

    @Override
    public List<Address> getAddress() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser user = (LoginUser) authentication.getPrincipal();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user_id", user.getUser().getId());
        return addressDao.selectByMap(map);
    }

    @Override
    public String addAddress(Address address) {
        if (address.getName()==null){
            return "添加失败";
        }
        int insert = addressDao.insert(address);
        if (insert==1){
            return "添加成功";
        }
        return "添加失败";
    }
}