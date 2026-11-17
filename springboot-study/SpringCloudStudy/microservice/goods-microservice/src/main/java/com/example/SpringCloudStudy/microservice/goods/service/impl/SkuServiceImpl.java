package com.example.SpringCloudStudy.microservice.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.SpringCloudStudy.microservice.goods.eneity.SkuEntity;
import com.example.SpringCloudStudy.microservice.goods.repository.SkuRepository;
import com.example.SpringCloudStudy.microservice.goods.service.SkuService;
import org.springframework.stereotype.Service;

@Service
public class SkuServiceImpl extends ServiceImpl<SkuRepository, SkuEntity> implements SkuService {
}
