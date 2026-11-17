package com.example.SpringCloudStudy.microservice.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.SpringCloudStudy.microservice.goods.eneity.SpuEntity;
import com.example.SpringCloudStudy.microservice.goods.repository.SpuRepository;
import com.example.SpringCloudStudy.microservice.goods.service.SpuService;
import org.springframework.stereotype.Service;

@Service
public class SpuServiceImpl extends ServiceImpl<SpuRepository, SpuEntity> implements SpuService {
}
