package com.example.SpringCloudStudy.microservice.rbac.controller;

import com.example.SpringCloudStudy.common.microservice.base.BaseController;
import com.example.SpringCloudStudy.microservice.rbac.entity.DeptEntity;
import com.example.SpringCloudStudy.microservice.rbac.service.DeptService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门 前端控制器
 * </p>
 *
 * @author star zhang
 * @since /
 */
@RestController
@RequestMapping("/rbac/dept")
public class DeptController extends BaseController<DeptEntity> {
    protected DeptService deptService;

    public DeptController(DeptService service) {
        super(service);
        this.deptService = service;
    }
}
