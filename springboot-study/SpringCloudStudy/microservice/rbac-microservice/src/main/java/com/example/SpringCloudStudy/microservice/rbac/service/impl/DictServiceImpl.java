package com.example.SpringCloudStudy.microservice.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.SpringCloudStudy.microservice.rbac.entity.DictEntity;
import com.example.SpringCloudStudy.microservice.rbac.repository.DictRepository;
import com.example.SpringCloudStudy.microservice.rbac.service.DictService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author star zhang
 * @since  / 
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictRepository, DictEntity> implements DictService {

}
