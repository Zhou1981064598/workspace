package com.example.SpringCloudStudy.microservice.goods.controller;


import com.example.SpringCloudStudy.common.microservice.base.BaseController;
import com.example.SpringCloudStudy.microservice.goods.eneity.SpuEntity;
import com.example.SpringCloudStudy.microservice.goods.service.SpuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods/spu")
public class SpuController extends BaseController<SpuEntity> {
    protected SpuService spuService;

    public SpuController(SpuService service) {
        super(service);
        this.spuService = service;
    }
}
