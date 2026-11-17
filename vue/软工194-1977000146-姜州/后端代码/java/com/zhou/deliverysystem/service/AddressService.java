package com.zhou.deliverysystem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhou.deliverysystem.enenty.Address;

import java.util.List;

/**
* @author 19810
* @description 针对表【address】的数据库操作Service
* @createDate 2023-04-21 18:37:41
*/
public interface AddressService extends IService<Address> {
    List<Address> getAddress();
    String addAddress(Address address);
}
