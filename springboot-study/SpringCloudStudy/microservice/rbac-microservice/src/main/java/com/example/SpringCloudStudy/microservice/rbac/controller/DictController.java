package com.example.SpringCloudStudy.microservice.rbac.controller;

import com.example.SpringCloudStudy.common.microservice.base.BaseController;
import com.example.SpringCloudStudy.microservice.rbac.entity.DictEntity;
import com.example.SpringCloudStudy.microservice.rbac.service.DictService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author star zhang
 * @since /
 */
@RestController
@RequestMapping("/rbac/dict")
public class DictController extends BaseController<DictEntity> {
    protected DictService dictService;

    public DictController(DictService service) {
        super(service);
        this.dictService = service;
    }

}
