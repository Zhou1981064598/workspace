package com.example.SpringCloudStudy.microservice.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.SpringCloudStudy.microservice.goods.eneity.ParamEntity;
import com.example.SpringCloudStudy.microservice.goods.repository.ParamRepository;
import com.example.SpringCloudStudy.microservice.goods.service.ParamService;
import org.springframework.stereotype.Service;

@Service
public class ParamServiceImpl extends ServiceImpl<ParamRepository, ParamEntity> implements ParamService {
}
