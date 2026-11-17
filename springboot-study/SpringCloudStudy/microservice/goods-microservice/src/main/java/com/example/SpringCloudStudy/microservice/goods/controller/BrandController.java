package com.example.SpringCloudStudy.microservice.goods.controller;

import com.example.SpringCloudStudy.common.microservice.base.BaseController;
import com.example.SpringCloudStudy.microservice.goods.eneity.BrandEntity;

import com.example.SpringCloudStudy.microservice.goods.service.BrandService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods/brand")
public class BrandController extends BaseController<BrandEntity> {
    protected BrandService brandService;

    public BrandController(BrandService brandService) {
        super(brandService);
        this.brandService= brandService;
    }
}
