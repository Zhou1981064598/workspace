package com.example.SpringCloudStudy.microservice.goods.controller;


import com.example.SpringCloudStudy.common.microservice.base.BaseController;
import com.example.SpringCloudStudy.microservice.goods.eneity.SpecEntity;
import com.example.SpringCloudStudy.microservice.goods.service.SpecService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods/spec")
public class SpecController extends BaseController<SpecEntity> {
    protected SpecService specService;

    public SpecController(SpecService service) {
        super(service);
        this.specService = service;
    }
}
