package com.example.SpringCloudStudy.microservice.goods.controller;


import com.example.SpringCloudStudy.common.microservice.base.BaseController;
import com.example.SpringCloudStudy.microservice.goods.eneity.SkuEntity;
import com.example.SpringCloudStudy.microservice.goods.service.SkuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods/sku")
public class SkuController extends BaseController<SkuEntity> {
    protected SkuService skuService;

    public SkuController(SkuService service) {
        super(service);
        this.skuService = service;
    }
}
