package com.example.SpringCloudStudy.microservice.rbac.controller;


import com.example.SpringCloudStudy.common.microservice.base.BaseController;
import com.example.SpringCloudStudy.microservice.rbac.entity.JobEntity;
import com.example.SpringCloudStudy.microservice.rbac.service.JobService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 岗位 前端控制器
 * </p>
 *
 * @author star zhang
 * @since /
 */
@RestController
@RequestMapping("/rbac/job")
public class JobController extends BaseController<JobEntity> {
    protected JobService jobService;

    public JobController(JobService service) {
        super(service);
        this.jobService = service;
    }

}
