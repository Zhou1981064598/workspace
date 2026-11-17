package com.example.SpringCloudStudy.microservice.rbac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.SpringCloudStudy.microservice.rbac.entity.JobEntity;
import com.example.SpringCloudStudy.microservice.rbac.repository.JobRepository;
import com.example.SpringCloudStudy.microservice.rbac.service.JobService;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 岗位 服务实现类
 * </p>
 *
 * @author star zhang
 * @since  / 
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobRepository, JobEntity> implements JobService {

}
