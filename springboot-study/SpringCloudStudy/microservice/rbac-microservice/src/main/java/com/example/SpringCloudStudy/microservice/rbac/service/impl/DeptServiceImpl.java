package com.example.SpringCloudStudy.microservice.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.SpringCloudStudy.microservice.rbac.entity.DeptEntity;
import com.example.SpringCloudStudy.microservice.rbac.repository.DeptRepository;
import com.example.SpringCloudStudy.microservice.rbac.service.DeptService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门 服务实现类
 * </p>
 *
 * @author star zhang
 * @since  / 
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptRepository, DeptEntity> implements DeptService {

}
