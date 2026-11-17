package com.example.SpringCloudStudy.microservice.goods.controller;


import com.example.SpringCloudStudy.common.microservice.base.BaseController;
import com.example.SpringCloudStudy.microservice.goods.eneity.CategoryEntity;
import com.example.SpringCloudStudy.microservice.goods.service.CategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods/category")
public class CategoryController extends BaseController<CategoryEntity> {
    protected CategoryService categoryService;

    public CategoryController(CategoryService service) {
        super(service);
        this.categoryService = service;
    }
}
