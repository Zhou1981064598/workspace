package com.example.SpringCloudStudy.microservice.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.SpringCloudStudy.microservice.goods.eneity.SpecEntity;
import com.example.SpringCloudStudy.microservice.goods.repository.SpecRepository;
import com.example.SpringCloudStudy.microservice.goods.service.SpecService;
import org.springframework.stereotype.Service;

@Service
public class SpecServiceImpl extends ServiceImpl<SpecRepository, SpecEntity> implements SpecService {
}
