package com.example.SpringCloudStudy.microservice.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.SpringCloudStudy.microservice.goods.eneity.CategoryEntity;
import com.example.SpringCloudStudy.microservice.goods.repository.CategoryRepository;
import com.example.SpringCloudStudy.microservice.goods.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryRepository, CategoryEntity> implements CategoryService {

}
