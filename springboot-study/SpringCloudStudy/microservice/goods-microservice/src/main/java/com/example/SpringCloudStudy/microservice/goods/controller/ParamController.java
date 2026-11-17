package com.example.SpringCloudStudy.microservice.goods.controller;

import com.example.SpringCloudStudy.common.microservice.base.BaseController;
import com.example.SpringCloudStudy.microservice.goods.eneity.ParamEntity;
import com.example.SpringCloudStudy.microservice.goods.service.ParamService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods/param")
public class ParamController extends BaseController<ParamEntity> {
    protected ParamService paramService;
    public ParamController(ParamService service) {
        super(service);
        this.paramService = service;
    }
}
