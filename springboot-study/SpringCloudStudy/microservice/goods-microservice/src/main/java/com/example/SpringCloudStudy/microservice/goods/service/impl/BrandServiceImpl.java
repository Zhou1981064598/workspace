package com.example.SpringCloudStudy.microservice.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.SpringCloudStudy.microservice.goods.eneity.BrandEntity;
import com.example.SpringCloudStudy.microservice.goods.repository.BrandRepository;
import com.example.SpringCloudStudy.microservice.goods.service.BrandService;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl extends ServiceImpl<BrandRepository, BrandEntity> implements BrandService {
}
