package com.zhou.deliverysystem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhou.deliverysystem.enenty.PackageSendInf;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.ManagedBean;

/**
* @author 19810
* @description 针对表【package_send_inf】的数据库操作Mapper
* @createDate 2023-04-21 18:37:41
* @Entity generator.domain.PackageSendInf
*/
@Mapper
public interface PackageSendInfDao extends BaseMapper<PackageSendInf> {


}
